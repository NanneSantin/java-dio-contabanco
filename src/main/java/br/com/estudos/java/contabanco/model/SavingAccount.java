package br.com.estudos.java.contabanco.model;

public class SavingAccount extends BankAccount {
    private static final double INTEREST_RATE = 0.005; // Taxa de juros de 0.5%

    public SavingAccount(String customerName, String billingAddress, int initialBalance, String password) {
        super(customerName, billingAddress, initialBalance, password);
    }

    @Override
    public void getAccountDetails() {
        System.out.println("Conta Poupança");
        System.out.println("Cliente: " + getCustomerName());
        System.out.println("Endereço: " + getBillingAddress());
        System.out.println("Agência: " + getBankBranch());
        System.out.println("Conta: " + getNumAccount() + "-" + getDigitCheckAccount());
    }

    @Override
    public void applyMonthlyFee() {
        int interest = (int) (getBalance() * INTEREST_RATE);
        deposit(interest);
    }
}
