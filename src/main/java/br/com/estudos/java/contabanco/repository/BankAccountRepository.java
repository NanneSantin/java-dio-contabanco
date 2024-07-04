package br.com.estudos.java.contabanco.repository;

import br.com.estudos.java.contabanco.model.BankAccount;

import java.util.HashMap;
import java.util.Map;

public class BankAccountRepository {
    private Map<String, BankAccount> accountsDatabase = new HashMap<>();

    public void saveAccount(BankAccount account){
        String numAccount = account.getNumAccount() + "-" + account.getDigitCheckAccount();
        accountsDatabase.put(numAccount, account);
    }

    public void deleteAccount(String numAccount){
        accountsDatabase.remove(numAccount);
        System.out.println("Conta deletada com sucesso!");
    }

    public boolean accountExists(String numAccount){
        return accountsDatabase.containsKey(numAccount);
    }

    public BankAccount findAccount(String numAccount){
        return accountsDatabase.get(numAccount);
    }
}
