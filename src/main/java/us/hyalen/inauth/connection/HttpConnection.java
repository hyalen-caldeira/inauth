package us.hyalen.inauth.connection;

import javax.validation.constraints.NotNull;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URLEncoder;
import java.util.Map;

public class HttpConnection {
    private static final String USER_AGENT = "Mozilla/5.0";
    private static final String BASE_URI_STRING = "https://maps.googleapis.com/maps/api/geocode/json";
    private static final String GET_REQUEST = "GET";
    private static final int CONNECTION_TIMEOUT = 10000;
    private static final int READ_TIMEOUT = 10000;
    private HttpURLConnection connection;

    protected String getRequest(String method, String baseUri, Map<String, String> parameters) throws Exception {
        URI uri = new URI(baseUri);
        uri = setParameters(uri, parameters);

        // Get http connection
        connection = (HttpURLConnection) uri.toURL().openConnection();
        // Set REST method
        connection.setRequestMethod(method);
        // Set request header
        connection.setRequestProperty("User-Agent", USER_AGENT);
        connection.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
        connection.setRequestProperty("Content-Type", "application/json");
        // Configure timeout
        connection.setConnectTimeout(CONNECTION_TIMEOUT);
        connection.setReadTimeout(READ_TIMEOUT);

        // Get result
        String result = connection.getResponseCode() == 200 ? readFromConnection() : "";

        // Close connection
        connection.disconnect();

        return result;
    }

    @NotNull
    private String readFromConnection() throws Exception {
        InputStreamReader inputStream = new InputStreamReader(connection.getInputStream());
        StringBuilder response = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(inputStream)) {
            String output;

            while ((output = reader.readLine()) != null)
                response.append(output);
        } catch (Exception exc) {
            exc.printStackTrace();
        }

        return response.toString();
    }

    private URI setParameters(@NotNull URI uri, @NotNull Map<String, String> parameters) throws Exception {
        return new URI(
                uri.getScheme(),
                uri.getAuthority(),
                uri.getPath(),
                getParametersString(parameters),
                null);
    }

    private String getParametersString(@NotNull Map<String, String> params) throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();

        for (Map.Entry<String, String> entry : params.entrySet()) {
            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
            result.append("&");
        }

        String resultString = result.toString();

        return resultString.length() > 0
                ? resultString.substring(0, resultString.length() - 1)
                : resultString;
    }
}
