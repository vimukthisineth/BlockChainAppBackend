package com.sliit.research.blockchainbasedapplication.utils;

import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUtils {
    public static String sendGetRequest(String requestUrl) throws IOException {
        URL url = new URL(requestUrl);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setConnectTimeout(3000);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream())
        );
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null ){
            content.append(inputLine);
        }
        return content.toString();
    }

    public static String sendPostRequest(String requestUrl, String data) throws IOException {
        String responseStr = "";
        URL url = new URL(requestUrl);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setConnectTimeout(20*1000);
        con.setRequestProperty("Content-Type", "application/json; utf-8");
        con.setDoOutput(true);
        try(OutputStream os = con.getOutputStream()) {
            byte[] input = data.getBytes("utf-8");
            os.write(input, 0, input.length);
        }
        try(BufferedReader br = new BufferedReader(
                new InputStreamReader(con.getInputStream(), "utf-8"))) {
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            responseStr = response.toString();
        }
        return responseStr;
    }

    public static String sendMLRequest(int visits, int purchases, int positiveComments, int negativeComments) throws IOException, ParseException {
        JSONObject obj = new JSONObject();
        // In this case, it's an array of arrays
        JSONArray dataItems = new JSONArray();
        // Inner array has 10 elements
        JSONArray item1 = new JSONArray();
        item1.add("");
        item1.add(visits);
        item1.add(purchases);
        item1.add(positiveComments);
        item1.add(negativeComments);
        item1.add(positiveComments+negativeComments);
        // Add the first set of data to be scored
        dataItems.add(item1);
        JSONObject input1 = new JSONObject();
        JSONArray columnName = new JSONArray();
        columnName.add("valid");
        columnName.add("visits_per_day");
        columnName.add("purchase_per_day");
        columnName.add("positive_comments");
        columnName.add("negative_comments");
        columnName.add("comments_per_day");
        input1.put("ColumnNames", columnName);
        input1.put("Values", dataItems);
        JSONObject inputs = new JSONObject();
        inputs.put("input1", input1);
        obj.put("Inputs", inputs);
        obj.put("GlobalParameters", new JSONObject());

        String uri = "https://ussouthcentral.services.azureml.net/workspaces/f109c04a3b4a45cc8e03f355a55f52d8/services/fd711283bb5f4d85874f71c14b6c18a4/execute?api-version=2.0&details=true";
        // If using authentication, replace with the auth key or token
        String key = "HFaupougZCztZIqxTbgV1zCr5Yllga9BsD3o3m7YNIh6wX2kyAI8GiHv926IceLQWZ4mj+WkCs2IMqVMpYGR4g==";
        Content content = null;
        // Create the request
        content = Request.Post(uri)
                .addHeader("Content-Type", "application/json")
                // Only needed if using authentication
                .addHeader("Authorization", "Bearer " + key)
                // Set the JSON data as the body
                .bodyString(obj.toJSONString(), ContentType.APPLICATION_JSON)
                // Make the request and display the response.
                .execute().returnContent();
        JSONParser parser = new JSONParser();
        JSONObject outputJson = (JSONObject) parser.parse(content.asString());
        JSONObject results = (JSONObject) outputJson.get("Results");
        JSONObject output1 = (JSONObject) results.get("output1");
        JSONObject value = (JSONObject) output1.get("value");
        JSONArray values = (JSONArray) value.get("Values");
        JSONArray resultArray = (JSONArray) values.get(0);
        return String.valueOf(resultArray.get(resultArray.size()-1));
    }
}
