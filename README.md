# Projeto de Conta Bancária

Este é um projeto simples de gerenciamento de contas bancárias, desenvolvido em Java para fins de estudos..

## Funcionalidades

- Criação de novas contas bancárias
- Login de contas existentes
- Depósito de valores
- Saque de valores
- Exibição de extrato
- Deleção de contas

## Estrutura do Projeto

- `ContaTerminal`: Classe principal que contém a lógica de criação de contas, login e operações bancárias.
- `BankAccount`: Classe que representa uma conta bancária com funcionalidades como depósito, saque, e exibição de extrato.

## Pré-requisitos

- Java 11 ou superior
- Maven
- Spring Boot

## Como executar

1. Clone o repositório:
    ```sh
    git clone https://github.com/NanneSantin/java-dio-contabanco.git
    ```
2. Navegue até o diretório do projeto ou abra com a sua IDE de preferência.
3. Compile e execute o projeto.


## Uso

1. Após iniciar a aplicação, você verá um menu com as seguintes opções:
    - `1 - Criar uma nova conta`
    - `2 - Login`
    - `0 - Sair`

2. Para criar uma nova conta:
    - Selecione a opção 1.
    - Insira os detalhes solicitados (nome, endereço, valor inicial e senha).
    - Anote o número da conta informada, será necessário para realizar o login.

3. Para fazer login em uma conta existente:
    - Selecione a opção 2.
    - Insira o número da conta e a senha.

4. Após o login, você verá um menu com as opções para depósito, saque, exibir extrato e deletar conta.
    - Os valores para depósito e saque devem ser inseridos em centavos. Exemplo: para depositar R$ 100,00 deve ser informado 10000. 

## Exemplo de Uso

```sh
MENU
1 - Criar uma nova conta
2 - Login
0 - Sair
Digite uma opção: 1

Digite o nome do cliente: João
Digite o endereço de cobrança: Rua ABC, 123
Digite o valor a ser depositado: 5000
Digite a senha: 1234

Conta criada com sucesso!
Obrigada por ser nosso cliente João
Anote a sua agência e conta. Esses dados serão usados para realizar o login!
Personal Account
CUSTOMER: João
ADDRESS: Rua ABC, 123
AGENCY: 0001
NUMBER ACCOUNT: 77397366-4
MENU
1 - Criar uma nova conta
2 - Login
0 - Sair
Digite uma opção: 0
Saindo...
```

## Contribuição

Contribuições são bem-vindas! Sinta-se à vontade para abrir um issue ou enviar um pull request.

## Contato
Se você tiver alguma dúvida ou sugestão, entre em contato:
- [GitHub: @NanneSantin](https://github.com/NanneSantin) 
- [LinkedIn: Elaine Santin](https://www.linkedin.com/in/elaine-stefani/)
