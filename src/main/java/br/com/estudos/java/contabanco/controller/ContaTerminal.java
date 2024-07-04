package br.com.estudos.java.contabanco.controller;

import java.util.Scanner;
import br.com.estudos.java.contabanco.repository.BankAccountRepository;
import br.com.estudos.java.contabanco.service.BankAccountService;
import br.com.estudos.java.contabanco.service.IBankAccountService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ContaTerminal {

	public static void main(String[] args) {
		SpringApplication.run(ContaTerminal.class, args);
		Scanner scanner = new Scanner(System.in);
		BankAccountRepository repository = new BankAccountRepository();
		IBankAccountService service = new BankAccountService(repository);


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
					System.out.println("Digite o nome do cliente: ");
					String name = scanner.nextLine();
					System.out.println("Digite o endereço de cobrança: ");
					String address = scanner.nextLine();
					System.out.println("Digite o valor a ser depositado na abertura da conta: ");
					double value = scanner.nextDouble();
					scanner.nextLine();

					int initialBalance = (int) (value * 100);
					String password;
					do {
						System.out.println("Digite uma senha de 4 dígitos: ");
						password = scanner.nextLine();
						if (password.length() != 4){
							System.out.println("A senha precisa ser de 4 dígitos.");
						}
					}while (password.length() != 4);

					System.out.println("Qual o tipo da conta a ser aberta?");
					int accountType;
					do {
						System.out.println("Digite 1 para Conta Corrente e 2 para Conta Poupança:");
						accountType = scanner.nextInt();
						scanner.nextLine();
						if (accountType != 1 && accountType != 2){
							System.out.println("Opção inválida!");
						}
					}while (accountType != 1 && accountType != 2);

					service.createAccount(name, address, initialBalance, password, accountType);
					break;
				case 2:
					System.out.println("Informe o número da conta com o digito (xxxxxxxx-x)");
					String numAccount = scanner.nextLine();
					System.out.println("Digite a senha: ");
					String pass = scanner.nextLine();
					service.login(numAccount, pass);
					break;
				default:
					System.out.println("Opção inválida!");
					break;
			}

		} while (option != 0);
	}
}
