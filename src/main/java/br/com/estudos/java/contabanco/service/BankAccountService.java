package br.com.estudos.java.contabanco.service;

import br.com.estudos.java.contabanco.model.BankAccount;
import br.com.estudos.java.contabanco.model.CheckingAccount;
import br.com.estudos.java.contabanco.model.SavingAccount;
import br.com.estudos.java.contabanco.repository.BankAccountRepository;
import br.com.estudos.java.contabanco.util.BankAccountUtil;

import java.util.Scanner;

public class BankAccountService implements IBankAccountService{
    private BankAccountRepository repository;

    public BankAccountService(BankAccountRepository repository) {
        this.repository = repository;
    }

    @Override
    public void createAccount(String customerName, String billingAddress, int initialBalance, String password, int accountType) {
        BankAccount bankAccount;

        switch (accountType){
            case 1:
                bankAccount = new CheckingAccount(customerName, billingAddress, initialBalance, password);
                break;
            case 2:
                bankAccount = new SavingAccount(customerName, billingAddress, initialBalance, password);
                break;
            default:
                throw new IllegalArgumentException("Tipo de conta inválido.");
        }

        repository.saveAccount(bankAccount);
        System.out.println("Conta criada com sucesso.");
        System.out.println("Nós agradecemos a sua confiança em nós " + bankAccount.getCustomerName());
        System.out.println("Anota o número de sua agência e conta. Esses dados serão usados para realizar o login!");
        bankAccount.getAccountDetails();
        System.out.println("----------------------------- Fim da operação!");
    }

    @Override
    public void login(String identifier, String password) {
        if (repository.accountExists(identifier)){
            BankAccount bankAccount = repository.findAccount(identifier);
            if (bankAccount.verifyPassword(password)){
                System.out.println("Usuário logado com sucesso!");
                accountMenu(bankAccount);
            }else{
                System.out.println("Senha ou conta inválida!");
            }
        }else{
            System.out.println("Senha ou conta inválida!");
        }
    }

    public void accountMenu(BankAccount bankAccount){
        int option;
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("1 - Depósito");
            System.out.println("2 - Saque");
            System.out.println("3 - Extrato");
            System.out.println("4 - Deletar conta");
            System.out.println("0 - Sair");
            System.out.println("Digite a opção desejada: ");
            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 0:
                    System.out.println("Saindo...");
                    break;
                case 1:
                    System.out.println("Informe o valor a ser depositado: ");
                    double valueDep = scanner.nextDouble();
                    scanner.nextLine();
                    int valueDeposit = (int) (valueDep * 100);
                    bankAccount.deposit(valueDeposit);
                    break;
                case 2:
                    System.out.println("Informe o valor a ser sacado: ");
                    double value = scanner.nextDouble();
                    scanner.nextLine();
                    int valueWithdraw = (int) (value * 100);
                    bankAccount.withdraw(valueWithdraw);
                    break;
                case 3:
                    bankAccount.printStatement();
                    break;
                case 4:
                    String numAccount = bankAccount.getNumAccount() + "-" + bankAccount.getDigitCheckAccount();
                    double balance = (double) (bankAccount.getBalance() / 100);
                    if (balance > 0) {
                        System.out.println("Você possui um saldo de " + BankAccountUtil.formatBalance(balance));
                        System.out.println("Realize o saque antes de continuar a exclusão da conta");
                    } else {
                        repository.deleteAccount(numAccount);
                        option = 0;
                    }
                    break;
                default:
                    System.out.println("Opção inválida!");
                    break;
            }
        } while (option != 0);
    }
}
