package  org.example;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        LinkShortener linkShortener = new LinkShortener(); // Create an instance of LinkShortener
        Scanner scanner = new Scanner(System.in); // For user input

        System.out.println("Welcome to the Link Shortener!");

        while (true) {
            // Display the menu
            System.out.println("\n--- MENU ---");
            System.out.println("1. Shorten a URL");
            System.out.println("2. Expand a URL");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt(); // Get the user's choice
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1: // Shorten a URL
                    System.out.print("Enter the long URL: ");
                    String longUrl = scanner.nextLine();
                    String shortUrl = linkShortener.shortenUrl(longUrl);
                    System.out.println("Shortened URL: " + shortUrl);
                    break;

                case 2: // Expand a URL
                    System.out.print("Enter the short URL: ");
                    String shortUrlToExpand = scanner.nextLine();
                    String originalUrl = linkShortener.expandUrl(shortUrlToExpand);
                    System.out.println("Original URL: " + originalUrl);
                    break;

                case 3: // Exit
                    System.out.println("Thank you for using the Link Shortener! Goodbye.");
                    scanner.close(); // Close the scanner
                    System.exit(0); // Exit the program
                    break;

                default: // Invalid choice
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
