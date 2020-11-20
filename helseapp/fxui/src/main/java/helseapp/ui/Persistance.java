package helseapp.ui;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import helseapp.core.Dag;
import helseapp.core.Dager;


class Persistance{

    private HttpURLConnection connection;


    private static ObjectMapper objectMapper = getDefaultObjectMapper();

    /**
     * Creating a default ObjectMapper-object.
     * 
     * @return new ObjectMapper() - ObjectMapper
     */
    static ObjectMapper getDefaultObjectMapper(){
        return new ObjectMapper();
    }


    /**
     * Parse a the responsebody from the GET request to a Dager-object.
     * 
     * @param responseBody - String
     * @return dager - Dager
     */
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

    /**
     * Parse a the responsebody from the GET request to a Dag-object.
     * 
     * @param responseBody - String
     * @return dager - Dager
     */
    public static Dag parseJsonToDag(String responseBody) throws JsonProcessingException {
        JsonNode node = objectMapper.readTree(responseBody);
        LocalDate dato = LocalDate.parse(node.get("date").asText());
        double vekt = node.get("vekt").asDouble();
        double fett = node.get("fett").asDouble();
        double protein = node.get("protein").asDouble();
        double karbo =node.get("karbo").asDouble();
        double treningstid = node.get("treningstid").asDouble();
        double skritt = node.get("skritt").asDouble();
        return new Dag(vekt, skritt, treningstid, protein, karbo, fett, dato);
    }

    /**
     * Performs a GET-request which either loads a Dager or a Dag object from file. This is decided by the URL input.
     * 
     * @param urlString - String
     * @return Object 
     */
    public Object load(String urlString){
        BufferedReader reader;
        String line;
        StringBuilder responseContent = new StringBuilder();
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
                return parseJsonToDager(responseBody);
            }else {
                return parseJsonToDag(responseBody);
            }
        } catch (IOException | JSONException e){
            e.printStackTrace();
        } finally {
            connection.disconnect();
        }
        return null;
    }

    /**
     * Use a Dag-object creates a json-formatted string.
     * 
     * @param dag - Dag
     * @return String
     */
    public static String createJsonDagString(Dag dag) {
        try{
            JSONObject obj = new JSONObject();
            obj.put("vekt", dag.getVekt());
            obj.put("skritt", dag.getSkritt());
            obj.put("treningstid", dag.getTreningstid());
            obj.put("protein", dag.getProtein());
            obj.put("karbo", dag.getKarbo());
            obj.put("fett", dag.getFett());
            obj.put("date", dag.getDate());

            return obj.toString();
        }catch(JSONException e){
            return null;
        }
    }

    /**
     * Performs a POST-request, which saves the Dag-object.
     * 
     * @param dag - Dag
     */
    public void addDag(final Dag dag) {
        try {
            URL url = new URL ("http://localhost:8080/dager");
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json; utf-8");
            con.setRequestProperty("Accept", "application/json");
            con.setDoOutput(true);

            String jsonInputString = createJsonDagString(dag);

            try(OutputStream os = con.getOutputStream()) {
                assert jsonInputString != null;
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            try(BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"))) {
                StringBuilder response = new StringBuilder();
                String responseLine = null;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
                System.out.println(response.toString());
            }
            con.disconnect();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
