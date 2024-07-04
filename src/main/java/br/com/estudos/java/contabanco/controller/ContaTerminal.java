package br.com.estudos.java.contabanco;

import java.util.HashMap;
import java.util.Scanner;

import br.com.estudos.java.contabanco.model.BankAccount;
import br.com.estudos.java.contabanco.service.BankAccountUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ContaTerminal {

	private HashMap<String, BankAccount> accountsDataBase;
	private Scanner scanner;

	public ContaTerminal(HashMap<String, BankAccount> accountsDataBase, Scanner scanner) {
		this.accountsDataBase = accountsDataBase;
		this.scanner = scanner;
	}

	public void createAccount() {
		System.out.println("Digite o nome do cliente: ");
		String name = scanner.nextLine();

		System.out.println("Digite o endereço de cobrança: ");
		String address = scanner.nextLine();

		System.out.println("Digite o valor a ser depositado: ");
		int initialBalance = scanner.nextInt();
		scanner.nextLine();

		String password;
		do {
			System.out.println("Digite a senha: ");
			password = scanner.nextLine();
			if (password.length() != 4) {
				System.out.println("A senha precisa ter 4 digitos!");
			}
		} while (password.length() != 4);

//		BankAccount bankAccount = new BankAccount(name, address, initialBalance, password);
//		System.out.println("Conta criada com sucesso!");
//		System.out.println("Obrigada por ser nosso cliente " + bankAccount.getCustomerName());
//		System.out.println("Anote a sua agência e conta. Esses dados serão usados para realizar o login!");
//		bankAccount.getAccountDetails();
//		String numAccount = bankAccount.getNumAccount() + "-" + bankAccount.getDigitCheckAccount();
//		accountsDataBase.put(numAccount, bankAccount);
	}

	public void login(String identifier, String password) {
		if (accountsDataBase.containsKey(identifier)) {
			BankAccount bankAccount = accountsDataBase.get(identifier);
			if (bankAccount.verifyPassword(password)) {
				System.out.println("Usuário logado com sucesso!");
				accountMenu(bankAccount);
			} else {
				System.out.println("Senha ou conta inválida!");
			}
		} else {
			System.out.println("Senha ou conta inválida!");
		}
	}

	public void accountMenu(BankAccount account) {
		int option;
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
					System.out.println("Informe o valor a ser depositado (em centavos): ");
					int valueDeposit = scanner.nextInt();
					scanner.nextLine();
					account.deposit(valueDeposit);
					break;
				case 2:
					System.out.println("Informe o valor a ser sacado (em centavos): ");
					int valueWithdraw = scanner.nextInt();
					scanner.nextLine();
					account.withdraw(valueWithdraw);
					break;
				case 3:
					account.printStatement();
					break;
				case 4:
					String numAccount = account.getNumAccount() + "-" + account.getDigitCheckAccount();
					int balance = account.getBalance();
					if (balance > 0) {
						System.out.println("Você possui um saldo de " + BankAccountUtil.formatBalance(balance));
						System.out.println("Realize o saque antes de continuar a exclusão da conta");
					} else {
						accountsDataBase.remove(numAccount);
						System.out.println("Conta deletada com sucesso!");
						option = 0;
					}
					break;
				default:
					System.out.println("Operação inválida. Tente novamente.");
			}
		} while (option != 0);
	}

	public static void main(String[] args) {
		SpringApplication.run(ContaTerminal.class, args);
		HashMap<String, BankAccount> accounts = new HashMap<>();
		Scanner scanner = new Scanner(System.in);

		ContaTerminal contaTerminal = new ContaTerminal(accounts, scanner);

		int option;
		do {
			System.out.println("MENU");
			System.out.println("1 - Criar uma nova conta");
			System.out.println("2 - Login");
			System.out.println("0 - Sair");
			System.out.println("Digite uma opção: ");
			option = scanner.nextInt();
			scanner.nextLine();

			switch (option) {
				case 0:
					System.out.println("Saindo...");
					break;
				case 1:
					contaTerminal.createAccount();
					break;
				case 2:
					System.out.println("Informe o número da conta com o digito (xxxxxxxx-x)");
					String numAccount = scanner.nextLine();
					System.out.println("Digite a senha: ");
					String password = scanner.nextLine();
					contaTerminal.login(numAccount, password);
					break;
				default:
					System.out.println("Opção inválida!");
			}

		} while (option != 0);
	}

}
