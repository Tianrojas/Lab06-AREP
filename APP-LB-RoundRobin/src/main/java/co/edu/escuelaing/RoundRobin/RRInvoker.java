package co.edu.escuelaing.RoundRobin;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class RRInvoker {
    private static final String USER_AGENT = "Mozilla/5.0";
    private static final List<String> BASE_URLS = new ArrayList<>();
    private static int currentIndex = 0;

    static {
        // Agregar las URLs base
        BASE_URLS.add("http://logservice1:34000");
        BASE_URLS.add("http://logservice2:34000");
        BASE_URLS.add("http://logservice3:34000");
    }

    public static String listLogs(int page) throws IOException {
        String baseUrl = getCurrentBaseUrl();
        String url = baseUrl + "/logs/" + page;
        return sendGetRequest(url);
    }

    public static void addLog(String message) throws IOException {
        String baseUrl = getCurrentBaseUrl();
        String url = baseUrl + "/logs";
        sendPostRequest(url, message);
    }

    private static String getCurrentBaseUrl() {
        String baseUrl = BASE_URLS.get(currentIndex);
        currentIndex = (currentIndex + 1) % BASE_URLS.size();
        return baseUrl;
    }

    private static String sendGetRequest(String url) throws IOException {
        System.out.println(url);
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);

        int responseCode = con.getResponseCode();
        System.out.println("GET Response Code :: " + responseCode);
        StringBuilder response = new StringBuilder();

        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            System.out.println("GET Response :: " + response.toString());
        } else {
            System.out.println("GET request not worked");
        }

        return response.toString();
    }

    private static void sendPostRequest(String url, String message) throws IOException {
        System.out.println(url);
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("User-Agent", USER_AGENT);
        con.setRequestProperty("Content-Type", "application/json");

        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(message);
        wr.flush();
        wr.close();

        int responseCode = con.getResponseCode();
        System.out.println("POST Response Code :: " + responseCode);

        if (responseCode == HttpURLConnection.HTTP_OK) {
            System.out.println("POST request worked");
        } else {
            System.out.println("POST request not worked");
        }
    }
}
