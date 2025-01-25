package org.example;

public class TestLinkShortener {
    public static void main(String[] args) {
        LinkShortener linkShortener = new LinkShortener();

        // Test shortening a URL
        String longUrl = "https://www.example.com";
        String shortUrl = linkShortener.shortenUrl(longUrl);
        System.out.println("Shortened URL: " + shortUrl);

        // Test expanding the shortened URL
        String originalUrl = linkShortener.expandUrl(shortUrl);
        System.out.println("Original URL: " + originalUrl);

        // Test invalid short URL
        String invalidUrl = linkShortener.expandUrl("http://short.ly/invalid");
        System.out.println("Invalid URL test: " + invalidUrl);
    }
}

