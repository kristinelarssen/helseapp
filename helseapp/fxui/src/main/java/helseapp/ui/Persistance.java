package helseapp.ui;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.*;
import java.net.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import helseapp.core.Dag;
import helseapp.core.Dager;


public class Persistance{

    private HttpURLConnection connection;


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
            LocalDate dato = LocalDate.parse(album.getString("date"));
            double vekt = album.getDouble("vekt");
            double fett = album.getDouble("fett");
            double protein = album.getDouble("protein");
            double karbo = album.getDouble("karbo");
            double treningstid = album.getDouble("treningstid");
            double skritt = album.getDouble("skritt");
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

    public Object load(String urlString){
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
                reader = new BufferedReader(new InputStreamReader(connection.getErrorStream(), StandardCharsets.UTF_8));
                while((line = reader.readLine()) != null){
                    responseContent.append(line);
                }
                reader.close();
            }else {
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));
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

    public static  String createJsonDagString(Dag dag) {
        try{
            JSONObject obj = new JSONObject();
            obj.put("vekt", dag.getVekt());
            obj.put("skritt", dag.getSkritt());
            obj.put("treningstid", dag.getTreningstid());
            obj.put("protein", dag.getProtein());
            obj.put("karbo", dag.getKarbo());
            obj.put("fett", dag.getFett());
            obj.put("date", dag.getDate());

            String jsonDagString = obj.toString();
            System.out.println(jsonDagString);
            return jsonDagString;
        }catch(JSONException e){
            return null;
        }
    }

    public boolean addDag(final Dag dag) {
        try {
            URL url = new URL ("http://localhost:8080/dager");
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json; utf-8");
            con.setRequestProperty("Accept", "application/json");
            con.setDoOutput(true);

            String jsonInputString = createJsonDagString(dag);

            try(OutputStream os = con.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            try(BufferedReader br = new BufferedReader(
                    new InputStreamReader(con.getInputStream(), "utf-8"))) {
                StringBuilder response = new StringBuilder();
                String responseLine = null;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
                System.out.println(response.toString());
            }

        }catch (MalformedURLException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }



    /*
    public static void main(String[] args) {
        // Tester post dag:
        Dag dag = new Dag(1, 1, 1, 1, 1, 1, LocalDate.parse("9758-01-01"));
        boolean bool = addDag(dag);
        System.out.println(bool);

        // Tester get dager:
        String urlStringDager = "http://localhost:8080/dager";
        Object dager = load(urlStringDager);
        Dager dgr = (Dager) dager;
        System.out.println("dager: " + dgr.getDag(0).getDate());

        // Tester get dag:
        String urlString = "http://localhost:8080/dager/2030-11-15";
        Object d1 = load(urlString);
        Dag d = (Dag) dag;
        System.out.println("Dag: " + d.getDate());

    }
    */
    
}
