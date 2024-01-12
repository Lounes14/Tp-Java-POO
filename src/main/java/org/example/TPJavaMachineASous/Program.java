package org.example.TPJavaMachineASous;

import java.util.Scanner;

public class Program {

    public static void main(String[] args) {

        String jsonColumns =
                "[[\"7\", \"C\", \"BAR\", \"T\", \"P\", \"7\", \"C\", \"BAR\", \"T\", \"R\", \"7\", \"C\", \"BAR\", \"R\", \"P\"]," +
                " [\"7\", \"P\", \"C\", \"T\", \"R\", \"BAR\", \"C\", \"T\", \"R\", \"C\", \"BAR\", \"P\", \"T\", \"C\", \"R\"]," +
                " [\"7\", \"BAR\", \"T\", \"P\", \"C\", \"R\", \"T\", \"P\", \"C\", \"R\", \"T\", \"P\", \"C\", \"R\", \"T\"]]";

        Columns columns = new Columns(jsonColumns);

        Scanner scanner = new Scanner(System.in);

        Machine machine = new Machine(columns, 50);

        System.out.println("°º¤ø,¸¸,ø¤º°`°º¤ø,¸,ø¤°º¤ø,¸¸,ø¤º°`");
        System.out.println("Bienvenue au Casino de Céladopole !");
        System.out.println("°º¤ø,¸¸,ø¤º°`°º¤ø,¸,ø¤°º¤ø,¸¸,ø¤º°`");

        while (true) {

            System.out.println("Jetons possédés : " + machine.getCoins());
            System.out.print("Combien de jetons ? (1, 2 ou 3) : ");

            String userInput = scanner.nextLine();

            int bet = Integer.parseInt(userInput);

            System.out.println("-----------------------------------");

            machine.jouer(bet);

            System.out.print("Voulez-vous jouer encore ? (oui/non) : ");
            String playAgain = scanner.nextLine().toLowerCase();

            if (playAgain.equals("non")) {

                break;
            }
        }
    }
}
