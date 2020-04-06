package com.sliit.research.blockchainbasedapplication.service.impl;

import com.sliit.research.blockchainbasedapplication.dto.DeliveryRouteDto;
import com.sliit.research.blockchainbasedapplication.model.Delivery;
import com.sliit.research.blockchainbasedapplication.model.DeliveryRoute;
import com.sliit.research.blockchainbasedapplication.service.DeliveryRouteService;
import com.sliit.research.blockchainbasedapplication.utils.HttpUtils;
import com.sliit.research.blockchainbasedapplication.utils.RouteElement;
import com.sliit.research.blockchainbasedapplication.utils.RoutePath;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Service("deliveryRouteService")
public class DeliveryRouteServiceImpl implements DeliveryRouteService {
    @Override
    public RoutePath calculateRoute(DeliveryRouteDto deliveryRoute) throws IOException, JSONException {
        RoutePath routePath = new RoutePath();
        List<RouteElement> route = new ArrayList<>();
        List<List<Integer>> distanceMatrix = getDistanceMatrix(deliveryRoute);
        JSONObject routeJson = new JSONObject(HttpUtils.sendPostRequest("http://127.0.0.1:5000/calculateRoute", distanceMatrix.toString()));
        routePath.setDistance(routeJson.getInt("distance"));
        JSONArray pathArray = routeJson.getJSONArray("route");
        for (int i = 0; i < pathArray.length(); i++) {
            int position = pathArray.getInt(i);
            List<String> destinations = new ArrayList<>();
            destinations.add(deliveryRoute.getDeliveries().get(0).getOrigin());
            for (Delivery delivery : deliveryRoute.getDeliveries()){
                destinations.add(delivery.getDestination());
            }
            String destination =destinations.get(position);
            route.add(new RouteElement(destination));
        }
        routePath.setPath(route);
        return routePath;
    }

    private List<List<Integer>> getDistanceMatrix(DeliveryRouteDto deliveryRoute) throws IOException, JSONException {
        List<List<Integer>> distanceMatrix = new ArrayList<>();
        if (deliveryRoute.getDeliveries().size() > 0){
            List<String> destinations = new ArrayList<>();
            for (Delivery delivery : deliveryRoute.getDeliveries()){
                destinations.add(delivery.getDestination());
            }
            destinations.add(deliveryRoute.getDeliveries().get(0).getOrigin());
            for (int i = 0; i < (deliveryRoute.getDeliveries().size() + 1); i++){
                List<RouteElement> routeElements;

                destinations.add(destinations.get(0));
                destinations.remove(0);

                routeElements = getDistanceList(destinations.get(i), destinations);
                List<Integer> matrixElement = new ArrayList<>();
                for (RouteElement routeElement : routeElements){
                    matrixElement.add(routeElement.getDistance());
                }
                distanceMatrix.add(matrixElement);
            }
        }
        return distanceMatrix;
    }

    public List<RouteElement> getDistanceList(String origin, List<String> destinations) throws IOException, JSONException {
        List<RouteElement> distances = new ArrayList<>();
        if (origin != null){
            origin = origin.trim();
            origin = origin.replaceAll(" ", "%20");
        }
        String requestUrl = "https://maps.googleapis.com/maps/api/distancematrix/json?units=metric&origins="+origin+"&destinations=";
        for (String destination : destinations){
            if (destination != null){
                destination = destination.trim();
                destination = destination.replaceAll(" ", "%20");
            }
            requestUrl += destination+"|";
        }
        requestUrl += "&key=AIzaSyAKXsRBGniwZd_0Vsm6jh2jqCGaw0u2TN4";
        String response = HttpUtils.sendGetRequest(requestUrl);
        JSONObject responseObject = new JSONObject(response);
        JSONArray rows = responseObject.getJSONArray("rows");
        JSONArray elements = rows.getJSONObject(0).getJSONArray("elements");
        for (int i = 0; i < elements.length(); i++){
            try {
                distances.add(
                        new RouteElement(
                                elements.getJSONObject(i).getJSONObject("distance").getInt("value")/1000,
                                elements.getJSONObject(i).getJSONObject("duration").getInt("value"),
                                elements.getJSONObject(i).getJSONObject("duration").getString("text"),
                                destinations.get(i)
                        )
                );
            }catch (Exception e){}
        }
        return distances;
    }
}
