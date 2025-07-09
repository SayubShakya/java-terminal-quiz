package com.sayub;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.sayub.Main2.API_URL;

public class Main2 {
    public static final List<String> paths = List.of("users", "roles", "recipes", "cuisines");
    public static final String API_URL = "http://localhost:9000/api/rest/"; // Example API
    public static final int THREAD_COUNT = 100; // Number of threads

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(THREAD_COUNT);

        while (true) {

            for (int i = 0; i < new Random().nextInt(500) + 1; i++) {

                executor.execute(new APIRequestTask(i + 1));
            }

            Thread.sleep(1000);
        }
    }

    public static String randomApiUrl() {
       return API_URL+ paths.get(new Random().nextInt(paths.size()));
    }
}

class APIRequestTask implements Runnable {
    private int threadId;

    public APIRequestTask(int threadId) {
        this.threadId = threadId;
    }

    @Override
    public void run() {
        try {

            Instant before = Instant.now();
// do stuff

            URL url = new URL(Main2.randomApiUrl());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            int responseCode = conn.getResponseCode();
            System.out.println("Thread " + threadId + " - Response Code: " + responseCode);

            if (responseCode == 200) {
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                System.out.println("Thread " + threadId + " - Response: " + response.toString());
            }

            Instant after = Instant.now();
            long delta = Duration.between(before, after).toMillis(); // .toWhatsoever()
            System.out.println("Thread " + threadId + " - Elapsed time: " + delta + " ms");
        } catch (Exception e) {
            System.out.println("Thread " + threadId + " - Error: " + e.getMessage());
        }
    }

}
