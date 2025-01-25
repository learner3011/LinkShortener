package org.example;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class LinkShortener {
    private final Map<String, String> urlMap = new HashMap<>();
    private final String baseUrl = "http://short.ly/";
    private final String storageFile = "urlMappings.txt";

    public LinkShortener() {
        loadMappingsFromFile();
    }

    // Method to shorten a URL
    public String shortenUrl(String longUrl) {
        String uniqueId = generateUniqueId(longUrl);
        String shortUrl = baseUrl + uniqueId;

        // Store the mapping only if it doesn't already exist
        if (!urlMap.containsKey(shortUrl)) {
            urlMap.put(shortUrl, longUrl);
            saveMappingsToFile();
        }

        return shortUrl;
    }

    // Method to expand a short URL
    public String expandUrl(String shortUrl) {
        return urlMap.getOrDefault(shortUrl, "Invalid short URL");
    }

    // Generate a unique identifier for the URL
    private String generateUniqueId(String longUrl) {
        StringBuilder uniqueId = new StringBuilder(Integer.toHexString(longUrl.hashCode()));

        // Handle collisions by appending a UUID if necessary
        while (urlMap.containsKey(baseUrl + uniqueId)) {
            uniqueId.append("-").append(UUID.randomUUID().toString(), 0, 5);
        }

        return uniqueId.toString();
    }

    private void loadMappingsFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(storageFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ", 2);
                if (parts.length == 2) {
                    urlMap.put(parts[0], parts[1]);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Storage file not found. Starting fresh.");
        } catch (IOException e) {
            System.out.println("Error reading storage file: " + e.getMessage());
        }
    }

    private void saveMappingsToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(storageFile))) {
            for (Map.Entry<String, String> entry : urlMap.entrySet()) {
                writer.write(entry.getKey() + " " + entry.getValue());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing to storage file: " + e.getMessage());
        }
    }
}
