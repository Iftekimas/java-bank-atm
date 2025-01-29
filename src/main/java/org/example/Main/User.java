package org.example.Main;

public class User {
    private String username;
    private String password;
    private double balance;
    private String currency;

    public User(String username, String password, double balance, String currency) {
        this.username = username;
        this.password = password;
        this.balance = balance;
        this.currency = currency;
    }

    public String getUsername() {
        return username;
    }

    public boolean checkPassword(String inputPassword) {
        return this.password.equals(inputPassword);
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getCurrency() {
        return currency;
    }

    public void changePassword(String newPassword) {
        this.password = newPassword;
    }
}
