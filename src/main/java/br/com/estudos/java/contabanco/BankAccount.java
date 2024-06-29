package br.com.estudos.java.contabanco;

import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class BankAccount {
    private String customerName;
    private String billingAddress;
    private int balance;
    private List<String> transactions;
    private String password;
    private String numAccount;
    private int digitCheckAccount;

    private static List<String> createdAccounts = new ArrayList<>();
    private DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    private String bankBranch;

    public BankAccount(String customerName, String billingAddress, int initialBalance,
            String password) {
        this.customerName = customerName;
        this.billingAddress = billingAddress;
        this.balance = initialBalance;
        this.transactions = new ArrayList<>();

        if (initialBalance > 0) {
            this.transactions.add("Depósito inicial de R$ " + formatarSaldo(initialBalance / 100) + " realizado em "
                    + LocalDateTime.now().format(dateFormat));
        }
        this.password = password;

        this.numAccount = generatNumAccount();
        this.digitCheckAccount = calculateCheckDigit(this.numAccount);
        this.bankBranch = "0001";
    }

    private static String generatNumAccount() {
        Random random = new Random();
        String numAccount;

        do {
            StringBuilder numAccountBuilder = new StringBuilder();

            for (int i = 0; i < 8; i++) {
                numAccountBuilder.append(random.nextInt(10));
            }
            numAccount = numAccountBuilder.toString();

        } while (createdAccounts.contains(numAccount));

        createdAccounts.add(numAccount);
        return numAccount;
    }

    private static int calculateCheckDigit(String numAccount) {
        int sum = 0;
        int weight = 2;

        for (int i = numAccount.length() - 1; i >= 0; i--) {
            int digit = Character.getNumericValue(numAccount.charAt(i));
            sum += digit * weight;
            weight++;
            if (weight > 9) {
                weight = 2;
            }
        }

        int remainder = sum % 11;
        int checkDigit = 11 - remainder;

        if (checkDigit == 10 || checkDigit == 11) {
            checkDigit = 0;
        }

        return checkDigit;
    }

    public void getAccountDetails() {
        System.out.println("Personal Account");
        System.out.println("CUSTOMER: " + customerName);
        System.out.println("ADDRESS: " + billingAddress);
        System.out.println("AGENCY: " + bankBranch);
        System.out.println("NUMBER ACCOUNT: " + numAccount + "-" + digitCheckAccount);
    }

    public static String formatarSaldo(double balance) {
        @SuppressWarnings("deprecation")
        NumberFormat formatoMonetario = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        return formatoMonetario.format(balance);
    }

    public void deposit(int amount) {
        if (amount > 0) {
            balance += amount;
            transactions.add("Depósito de R$ " + formatarSaldo(amount / 100) + " realizado em "
                    + LocalDateTime.now().format(dateFormat));
            System.out.println("Depósito realizado com sucesso.");
        } else {
            System.out.println("Falha na tentativa de depósito. Valor inválido!");
        }
    }

    public void withdraw(int amount) {
        if (amount > 0) {
            if (amount <= balance) {
                balance -= amount;
                transactions.add("Saque de R$ " + formatarSaldo(amount / 100) + " realizado em "
                        + LocalDateTime.now().format(dateFormat));
                System.out.println("Saque realizado com sucesso.");
            } else {
                System.out.println("Saldo insuficiente!");
            }
        } else {
            System.out.println("Falha na tentativa de saque. Valor inválido!");
        }
    }

    public void printStatement() {
        System.out.println("Extrato de " + customerName);
        System.out.println("em " + LocalDateTime.now().format(dateFormat));
        for (String transaction : transactions) {
            System.out.println(transaction);
        }
        System.out.println("Saldo atual: R$ " + formatarSaldo(balance / 100));
    }

    public boolean verifyPassword(String typedPassword) {
        return typedPassword.equals(password);
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getBillingAddress() {
        return billingAddress;
    }

    public int getBalance() {
        return balance;
    }

    public String getNumAccount() {
        return numAccount;
    }

    public int getDigitCheckAccount() {
        return digitCheckAccount;
    }

    public List<String> getTransactions() {
        return transactions;
    }
}
