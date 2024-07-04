package br.com.estudos.java.contabanco.service;

public interface IBankAccountService {
    void createAccount(String customerName, String billingAddress, int initialBalance, String password, int accountType);
    void login(String identifier, String password);
}
