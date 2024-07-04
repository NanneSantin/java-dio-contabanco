package br.com.estudos.java.contabanco.util;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class BankAccountUtil {
    private static List<String> createdAccounts = new ArrayList<>();

    public static String generateNumAccount() {
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

    public static int calculateCheckDigit(String numAccount) {
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

    public static String formatBalance(double balance) {
        @SuppressWarnings("deprecation")
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        return currencyFormat.format(balance);
    }
}
