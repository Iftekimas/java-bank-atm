package org.example.Main;

public class User {
    private String username;
    private String password;
    private double balance;
    private String currency;
    private StringBuilder transactionLog;

    public User(String username, String password, double balance, String currency) {
        this.username = username;
        this.password = password;
        this.balance = balance;
        this.currency = currency;
        this.transactionLog = new StringBuilder();
    }

    public String getUsername() {
        return username;
    }

    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }

    public double getBalance() {
        return balance;
    }

    public String getCurrency() {
        return currency;
    }

    public void setPassword(String newPassword) {
        this.password = newPassword;
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Deposit amount must be positive.");
            return;
        }
        this.balance += amount;
        transactionLog.append("Deposited: ").append(amount).append(" ").append(currency).append("\n");
    }

    public boolean withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Withdrawal amount must be positive.");
            return false;
        }
        if (amount > balance) {
            return false;
        }
        this.balance -= amount;
        transactionLog.append("Withdrew: ").append(amount).append(" ").append(currency).append("\n");
        return true;
    }

    public boolean transfer(User recipient, double amount) {
        if (amount <= 0) {
            System.out.println("Transfer amount must be positive.");
            return false;
        }
        if (amount > balance) {
            return false;
        }
        double convertedAmount = convertCurrency(amount, this.currency, recipient.getCurrency());
        this.balance -= amount;
        recipient.deposit(convertedAmount);
        transactionLog.append("Transferred: ").append(amount).append(" ").append(currency)
                .append(" to ").append(recipient.getUsername()).append(" (Converted to ")
                .append(convertedAmount).append(" ").append(recipient.getCurrency()).append(")\n");
        return true;
    }

    public String getTransactionLog() {
        return transactionLog.toString();
    }

    private double convertCurrency(double amount, String fromCurrency, String toCurrency) {
        if (fromCurrency.equals(toCurrency)) {
            return amount;
        }
        
        // Sample conversion rates (should be updated dynamically)
        double usdToCop = 4142.57;
        double usdToGbp = 0.78;
        double copToUsd = 1 / usdToCop;
        double copToGbp = copToUsd * usdToGbp;
        double gbpToUsd = 1 / usdToGbp;
        double gbpToCop = gbpToUsd * usdToCop;
        
        if (fromCurrency.equals("USD") && toCurrency.equals("COP")) return amount * usdToCop;
        if (fromCurrency.equals("USD") && toCurrency.equals("GBP")) return amount * usdToGbp;
        if (fromCurrency.equals("COP") && toCurrency.equals("USD")) return amount * copToUsd;
        if (fromCurrency.equals("COP") && toCurrency.equals("GBP")) return amount * copToGbp;
        if (fromCurrency.equals("GBP") && toCurrency.equals("USD")) return amount * gbpToUsd;
        if (fromCurrency.equals("GBP") && toCurrency.equals("COP")) return amount * gbpToCop;
        
        return amount;
    }
}


