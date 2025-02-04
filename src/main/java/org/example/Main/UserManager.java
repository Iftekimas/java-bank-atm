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

    public void handleUserCreation(Scanner scanner) {
        if (!createUser(scanner)) {
            System.out.println("Returning to main menu.");
        }
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
        if (findUserByUsername(username) != null) {
            System.out.println("Username already exists. Choose a different one.");
            return false;
        }

        System.out.print("Enter new password: ");
        String password = scanner.nextLine();
        System.out.print("Enter initial balance: ");
        
        if (!scanner.hasNextDouble()) {
            System.out.println("Invalid input. Initial balance must be a number.");
            scanner.nextLine(); // Clear buffer
            return false;
        }
        
        double balance = scanner.nextDouble();
        if (balance < 0) {
            System.out.println("Initial balance cannot be negative.");
            scanner.nextLine(); // Clear buffer
            return false;
        }
        scanner.nextLine(); // Consume newline
        String currency;
        while (true) {
            System.out.print("Enter currency (USD, COP, GBP): ");
            currency = scanner.nextLine().toUpperCase();
            if (currency.equals("USD") || currency.equals("COP") || currency.equals("GBP")) {
                break;
            }
            System.out.println("Invalid currency. Please enter USD, COP, or GBP.");
        }


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
            userMenu(scanner, user);
        } else {
            System.out.println("Returning to main menu.");
        }
    }

    private void userMenu(Scanner scanner, User user) {
        while (true) {
            System.out.println("\nUser Menu - " + user.getUsername());
            System.out.println("1. Balance");
            System.out.println("2. Transfer");
            System.out.println("3. Change Password");
            System.out.println("4. Log");
            System.out.println("5. Withdraw");
            System.out.println("6. Main Menu");
            System.out.println("7. Exit");
            System.out.print("Select an option: ");

            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // Clear buffer
                continue;
            }
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.println("Your balance: " + user.getBalance() + " " + user.getCurrency());
                    break;
                case 2:
                    System.out.print("Enter recipient username: ");
                    String recipientName = scanner.nextLine();
                    User recipient = findUserByUsername(recipientName);
                    if (recipient != null) {
                        System.out.print("Enter amount to transfer: ");
                        if (!scanner.hasNextDouble()) {
                            System.out.println("Invalid input. Amount must be a number.");
                            scanner.nextLine(); // Clear buffer
                            break;
                        }
                        double amount = scanner.nextDouble();
                        if (amount <= 0) {
                            System.out.println("Transfer amount must be positive.");
                        } else if (user.transfer(recipient, amount)) {
                            System.out.println("Transfer successful!");
                        } else {
                            System.out.println("Transfer failed. Insufficient funds.");
                        }
                        scanner.nextLine(); // Consume newline
                    } else {
                        System.out.println("User not found.");
                    }
                    break;
                case 3:
                    System.out.print("Enter new password: ");
                    String newPassword = scanner.nextLine();
                    user.setPassword(newPassword);
                    System.out.println("Password changed successfully.");
                    break;
                case 4:
                    System.out.println("Transaction Log: \n" + user.getTransactionLog());
                    break;
                case 5:
                    System.out.print("Enter amount to withdraw: ");
                    if (!scanner.hasNextDouble()) {
                        System.out.println("Invalid input. Amount must be a number.");
                        scanner.nextLine(); // Clear buffer
                        break;
                    }
                    double withdrawalAmount = scanner.nextDouble();
                    if (withdrawalAmount <= 0) {
                        System.out.println("Withdrawal amount must be positive.");
                    } else if (user.withdraw(withdrawalAmount)) {
                        System.out.println("Withdrawal successful! New balance: " + user.getBalance() + " " + user.getCurrency());
                    } else {
                        System.out.println("Withdrawal failed. Insufficient funds.");
                    }
                    scanner.nextLine(); // Consume newline
                    break;
                case 6:
                    System.out.println("Logging out and returning to main menu.");
                    return;
                case 7:
                    System.out.println("Exiting system. Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private User findUserByUsername(String username){
        for(User user : users){
            if ( user.getUsername().equals(username)){
                return user;
            }
        }
        return null;
    }
}
