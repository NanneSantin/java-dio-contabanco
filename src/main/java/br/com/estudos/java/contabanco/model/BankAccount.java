package br.com.estudos.java.contabanco.model;

import br.com.estudos.java.contabanco.util.BankAccountUtil;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public abstract class BankAccount {
    @Getter
    private String customerName;
    @Getter
    private String billingAddress;
    @Getter
    private int balance;
    @Getter
    private List<String> transactions;
    private String password;
    @Getter
    private final String numAccount;
    @Getter
    private final int digitCheckAccount;
    private final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    @Getter
    private final String bankBranch;
    @Getter
    private boolean monthlyFeeApply;

    public BankAccount(String customerName, String billingAddress, int initialBalance,
            String password) {
        this.customerName = customerName;
        this.billingAddress = billingAddress;
        this.balance = initialBalance;
        this.transactions = new ArrayList<>();
        this.password = password;
        this.numAccount = BankAccountUtil.generateNumAccount();
        this.digitCheckAccount = BankAccountUtil.calculateCheckDigit(this.numAccount);
        this.bankBranch = "0001";

        if (initialBalance > 0) {
            this.transactions.add("Depósito inicial de " + BankAccountUtil.formatBalance((double)initialBalance / 100) + " realizado em "
                    + LocalDateTime.now().format(dateFormat));
        }
    }

    public abstract void getAccountDetails();

    public void deposit(int amount) {
        if (amount > 0) {
            balance += amount;
            transactions.add("Depósito de " + BankAccountUtil.formatBalance((double) amount / 100) + " realizado em "
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
                transactions.add("Saque de " + BankAccountUtil.formatBalance((double) amount / 100) + " realizado em "
                        + LocalDateTime.now().format(dateFormat));
                System.out.println("Saque realizado com sucesso.");
            } else {
                System.out.println("Saldo insuficiente!");
            }
        } else {
            System.out.println("Falha na tentativa de saque. Valor inválido!");
        }
    }

    public void withdraw(int monthlyFee, boolean monthlyFeeWithdraw){
        balance -= monthlyFee;
        transactions.add("Cobrança de " + BankAccountUtil.formatBalance(monthlyFee) + " referente a taxa mensal da conta.");
    }

    public void printStatement() {
        System.out.println("Extrato de " + customerName);
        System.out.println("em " + LocalDateTime.now().format(dateFormat));
        for (String transaction : transactions) {
            System.out.println(transaction);
        }
        System.out.println("Saldo atual: " + BankAccountUtil.formatBalance((double) balance / 100));
    }

    public boolean verifyPassword(String typedPassword) {
        return typedPassword.equals(password);
    }

    public abstract void applyMonthlyFee();
}
