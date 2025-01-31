package org.example.Main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserManager {
    private List<User> users;
    private static final int MAX_USERS = 10; // Increased max user limit

    public UserManager() {
        users = new ArrayList<>();
        // Predefined users
        users.add(new User("user1", "pass1", 1000.0, "USD"));
        users.add(new User("user2", "pass2", 5000000.0, "COP"));
        users.add(new User("user3", "pass3", 750.0, "GBP"));
    }

    public User loginUser(Scanner scanner) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        for (User user : users) {
            if (user.getUsername().equals(username) && user.checkPassword(password)) {
                System.out.println("Login successful! Welcome " + username);
                return user;
            }
        }
        System.out.println("Invalid credentials. Try again.");
        return null; // Invalid credentials
    }

    public boolean createUser(Scanner scanner) {
        System.out.println("Current user count: " + users.size()); // Debug statement
        if (users.size() >= MAX_USERS) {
            System.out.println("User limit reached. Cannot create more users.");
            return false; 
        }
        
        System.out.print("Enter new username: ");
        String username = scanner.nextLine();
        System.out.print("Enter new password: ");
        String password = scanner.nextLine();
        System.out.print("Enter initial balance: ");
        double balance = scanner.nextDouble();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter currency (USD, COP, GBP): ");
        String currency = scanner.nextLine();

        users.add(new User(username, password, balance, currency));
        System.out.println("User created successfully! Current user count: " + users.size()); // Debug statement
        return true;
    }

    public void handleUserLogin(Scanner scanner) {
        User user = null;
        int attempts = 0;
        while (attempts < 3 && user == null) {
            user = loginUser(scanner);
            if (user == null) {
                attempts++;
                System.out.println("Invalid credentials. Attempts left: " + (3 - attempts));
            }
        }

        if (user != null) {
            System.out.println("\nWelcome, " + user.getUsername() + "!");
            System.out.println("Your balance: " + user.getBalance() + " " + user.getCurrency());
        } else {
            System.out.println("Returning to main menu.");
        }
    }

    public void handleUserCreation(Scanner scanner) {
        if (!createUser(scanner)) {
            System.out.println("Returning to main menu.");
        }
    }
}
