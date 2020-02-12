package com.sliit.research.blockchainbasedapplication.service;

import com.sliit.research.blockchainbasedapplication.dto.DeliveryRouteDto;
import com.sliit.research.blockchainbasedapplication.model.DeliveryRoute;
import com.sliit.research.blockchainbasedapplication.utils.RouteElement;
import com.sliit.research.blockchainbasedapplication.utils.RoutePath;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

public interface DeliveryRouteService {
    RoutePath calculateRoute(DeliveryRouteDto deliveryRoute) throws IOException, JSONException;
}
