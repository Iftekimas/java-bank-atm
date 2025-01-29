package org.example.Main;

import java.util.Scanner;
import java.util.ArrayList;

public class PinVerifier {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String pin;

        do {
            System.out.print("Set your 4-digit PIN: ");
            pin = scanner.nextLine();
        } while (!isValidPin(pin));

        System.out.println("Pin Successfully added.");

        int attempts = 3;

        while (true) {

            System.out.println("################");
            System.out.println("Select an option: ");
            System.out.println("1. Change Password.");
            System.out.println("2. Exit.");

            int option = scanner.nextInt();
            scanner.nextLine();
            
            

            if (option == 2) {
                System.out.println("Goodbye!");
                break;
            }

            if (option == 1) {

                boolean verified = false;

                for (int i = 0; i < attempts; i++) {

                    System.out.println("Insert your current pin: ");

                    String insertedPin = scanner.nextLine();

                    if (insertedPin.equals(pin)) {
                        verified = true;
                        break;
                    } else {
                        System.out.println("Incorrect PIN. Attempts left: " + (attempts - i - 1));

                    }

                }

                if (!verified) {
                    System.out.println("Too many attempts: ");
                    break;
                }

                String newPin;
                do{
                    System.out.println("Insert your new 4-digit Pin: ");
                    newPin = scanner.nextLine();
                }while (!isValidPin(newPin));

                pin = newPin;
                System.out.println("Your pin has been successfully changed");
                break;

            }

        }

        scanner.close();
    }

    public static boolean isValidPin(String pin){
        return pin.matches("\\d{4}");
    }

}