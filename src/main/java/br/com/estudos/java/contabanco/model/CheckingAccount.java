package br.com.estudos.java.contabanco.model;

public class CheckingAccount extends BankAccount {
    private static final int MONTHLY_FEE = 500; //Valor da taxa mensal em centavos.

    public CheckingAccount(String customerName, String billingAddress, int initialBalance, String password) {
        super(customerName, billingAddress, initialBalance, password);
    }

    @Override
    public void getAccountDetails() {
        System.out.println("Conta Corrente");
        System.out.println("Cliente: " + getCustomerName());
        System.out.println("Endereço: " + getBillingAddress());
        System.out.println("Agência: " + getBankBranch());
        System.out.println("Conta: " + getNumAccount() + "-" + getDigitCheckAccount());
    }

    @Override
    public void applyMonthlyFee() {
        withdraw(MONTHLY_FEE, true);
    }
}
