package helseapp.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.tools.javac.Main;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.List;

import helseapp.core.Dag;
import helseapp.core.Dager;


public class Persistance{

    private static HttpURLConnection connection;


    private static ObjectMapper objectMapper = getDefaultObjectMapper();

    private static ObjectMapper getDefaultObjectMapper(){
        ObjectMapper defaultObjectMapper = new ObjectMapper();
        return defaultObjectMapper;
    }


    public static Dager parseJsonToDager(String responseBody) throws  JSONException {
        JSONArray albums = new JSONArray(responseBody);
        Dager dager = new Dager();
        for(int i = 0; i<albums.length(); i++) {
            JSONObject album = albums.getJSONObject(i);
            double vekt = album.getDouble("vekt");
            double fett = album.getDouble("fett");
            double protein = album.getDouble("protein");
            double karbo = album.getDouble("karbo");
            double treningstid = album.getDouble("treningstid");
            double skritt = album.getDouble("skritt");
            LocalDate dato = LocalDate.parse(album.getString("date"));
            Dag dag = new Dag(vekt, skritt, treningstid, protein, karbo, fett, dato);
            dager.addDag(dag);
        }
        return dager;
    }

    public static Dag parseJsonToDag(String responseBody) throws JsonProcessingException {
        JsonNode node = objectMapper.readTree(responseBody);
        LocalDate dato = LocalDate.parse(node.get("date").asText());
        double vekt = node.get("vekt").asDouble();
        double fett = node.get("fett").asDouble();
        double protein = node.get("protein").asDouble();
        double karbo =node.get("karbo").asDouble();
        double treningstid = node.get("treningstid").asDouble();
        double skritt = node.get("skritt").asDouble();
        Dag dag = new Dag(vekt, skritt, treningstid, protein, karbo, fett, dato);
        return dag;
    }

    
    public static Object load(String urlString){
        BufferedReader reader;
        String line;
        StringBuffer responseContent = new StringBuffer();
        try {
            URL url = new URL(urlString);
            connection = (HttpURLConnection) url.openConnection();

            // Request setup
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            int status = connection.getResponseCode();

            if(status > 299){
                reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                while((line = reader.readLine()) != null){
                    responseContent.append(line);
                }
                reader.close();
            }else {
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                while((line = reader.readLine()) != null){
                    responseContent.append(line);
                }
                reader.close();
            }
            String responseBody = responseContent.toString();
            if(urlString.equals("http://localhost:8080/dager")){
                Dager dager = parseJsonToDager(responseBody);
                return dager;
            }else {
                Dag dag = parseJsonToDag(responseBody);
                return dag;
            }
        }catch (MalformedURLException e){
            e.printStackTrace();
        }catch (IOException | JSONException e){
            e.printStackTrace();
        }finally {
            connection.disconnect();
        }
        return null;
    }
    


    
    /*
    public static void main(String[] args) {
        // Get dager
        String urlStringDager = "http://localhost:8080/dager";
        Object dager = load(urlStringDager);
        Dager dgr = (Dager) dager;
        System.out.println("dager: " + dgr.getDag(0).getDate());

        // Get dag
        String urlString = "http://localhost:8080/dager/2030-11-15";
        Object dag = load(urlString);
        Dag d = (Dag) dag;
        System.out.println("Dag: " + d.getDate());

        
        Dag d1 = new Dag(1, 1, 1, 1, 1, 1, LocalDate.parse("0000-01-01"));
        // Post
        try{
            URL url = new URL(urlString);
            connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("POST");
            connection.setRequestProperty("Accept", "application/json");
            connection.setDoOutput(true);

            JSONObject obj = new JSONObject();
            obj.put("date", d1.getDate());
            obj.put("vekt", d1.getVekt());
            obj.put("skritt", d1.getSkritt());
            obj.put("treningstid", d1.getTreningstid());
            obj.put("protein", d1.getProtein());
            obj.put("karbo", d1.getKarbo());
            obj.put("fett", d1.getFett());

            String jsonInputString = obj.toString();

            try(OutputStream os = connection.getOutputStream()){
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            int code = connection.getResponseCode();
            System.out.println(code);

            try(BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"))){
                StringBuilder response = new StringBuilder();
                String responseLine = null;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
                System.out.println(parseJsonToDag(response.toString()));
            }

        }catch (MalformedURLException e){

        }catch (IOException e){

        }catch (JSONException e){
            
        }
        
    }
    */
    
}
