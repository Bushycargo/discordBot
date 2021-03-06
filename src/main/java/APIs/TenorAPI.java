package APIs;

import Utility.SettingsOptions;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Random;

//MAJORITY OF CODE FROM https://tenor.com/gifapi/documentation

public class TenorAPI {
    public static String getGif(String searchTerm) throws IOException, ParseException {
        String API_KEY = SettingsOptions.GetSetting("TenorAPIKey");
        if (searchTerm.equals("")){
            searchTerm = "random";
        }
        JSONObject unfiltered = getSearchResults(searchTerm, API_KEY);
        assert unfiltered != null;
        Integer random = new Random().nextInt(unfiltered.length());
        JSONObject results = unfiltered.getJSONArray("results").getJSONObject(random);
        JSONObject media = results.getJSONArray("media").getJSONObject(0);
        return (String) media.getJSONObject("gif").get("url");
    }

    private static JSONObject getSearchResults(String searchTerm, String API_KEY) {

        // make search request - using default locale of EN_US

        final String url = String.format("https://api.tenor.com/v1/search?q=%1$s&key=%2$s&limit=%3$s",
                searchTerm, API_KEY, 30);
        try {
            return get(url);
        } catch (IOException | JSONException ignored) {
        }
        return null;
    }
    private static JSONObject get(String url) throws IOException, JSONException {
        HttpURLConnection connection = null;
        try {
            // Get request
            connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");

            // Handle failure
            int statusCode = connection.getResponseCode();
            if (statusCode != HttpURLConnection.HTTP_OK && statusCode != HttpURLConnection.HTTP_CREATED) {
                String error = String.format("HTTP Code: '%1$s' from '%2$s'", statusCode, url);
                throw new ConnectException(error);
            }

            // Parse response
            return parser(connection);
        } catch (Exception ignored) {
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
        return new JSONObject("");
    }
    private static JSONObject parser(HttpURLConnection connection) throws JSONException {
        char[] buffer = new char[1024 * 4];
        int n;
        InputStream stream = null;
        try {
            stream = new BufferedInputStream(connection.getInputStream());
            InputStreamReader reader = new InputStreamReader(stream, StandardCharsets.UTF_8);
            StringWriter writer = new StringWriter();
            while (-1 != (n = reader.read(buffer))) {
                writer.write(buffer, 0, n);
            }
            return new JSONObject(writer.toString());
        } catch (IOException ignored) {
        } finally {
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException ignored) {
                }
            }
        }
        return new JSONObject("");
    }
}
