package com.ZippoPotamApi.zippopotam.Controller;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Zippo {
    public static void main(String[] args) throws IOException {
        URL getUrl = new URL("https://api.zippopotam.us/us/33162");

        HttpURLConnection connection = (HttpURLConnection) getUrl.openConnection();
        connection.setRequestMethod("GET");


        int responseCode = connection.getResponseCode();

        if (responseCode == 200) {


            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder jsonResponseData = new StringBuilder();
            String readLine;


            while ((readLine = in.readLine()) != null) {
                jsonResponseData.append(readLine);
            }

            in.close();
            JSONObject masterData = new JSONObject(jsonResponseData.toString());

            System.out.println("Current post code : " + masterData.get("post code"));
            System.out.println("Current country : " + masterData.get("country"));
            System.out.println("Current country abbreviation :"+ masterData.get("country abbreviation"));
            JSONArray currentPlace = (JSONArray) masterData.get("places");
            JSONObject currentZero = currentPlace.getJSONObject(0);

            System.out.println("Current place name : " + currentZero.get("place name"));
            System.out.println("Current longitude : " + currentZero.get("longitude"));
            System.out.println("Current state : " + currentZero.get("state"));
            System.out.println("Current state abbreviation : " + currentZero.get("state abbreviation"));
            System.out.println("Current latitude : " + currentZero.get("latitude"));
        } else {
            System.out.println("This is not valid URL- " + responseCode);
        }
    }
}
