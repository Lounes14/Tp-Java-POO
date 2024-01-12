package org.example.TPJavaMachineASous;

import java.util.Random;
import java.util.Scanner;
import com.google.gson.Gson;

public class Machine {
    private String[][] matrix;
    private int coins;
    private String[][] columns;
    private int partieJouer;
    private int partieGagner;
    private int jetonsUtiliser;

    public Machine(Columns columns, int initialCoins) {
        this.columns = columns.getColumns();
        this.matrix = initialiser(this.columns);
        this.coins = initialCoins;
        this.partieJouer = 0;
        this.partieGagner = 0;
        this.jetonsUtiliser = 0;
    }

    private String[][] initialiser(String[][] columns) {
        Random random = new Random();
        String[][] matrix = new String[3][3];

        matrix[0][0] = columns[0][random.nextInt(columns[0].length)];
        matrix[1][1] = columns[1][random.nextInt(columns[1].length)];
        matrix[2][2] = columns[2][random.nextInt(columns[2].length)];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (matrix[j][i] == null) {
                    int randomPosition = random.nextInt(columns[i].length);
                    matrix[j][i] = columns[i][randomPosition];
                }
            }
        }
        return matrix;
    }

    public void jouer(int bet) {
        Scanner scanner = new Scanner(System.in);

        while (true) {

            spin();

            displayCurrentState();

            int winnings = verfierGagne(bet);

            coins += winnings - bet;

            partieJouer++;
            if (winnings > 0) {
                partieGagner++;
                System.out.println("Bravo vous avez gagné des jetons ! ");
            }
            jetonsUtiliser += bet;

            System.out.println("Gains : " + winnings);
            System.out.println("Jetons possédés : " + coins);

            System.out.print("Voulez-vous jouer encore ? (oui/non) : ");
            String playAgain = scanner.nextLine().toLowerCase();
            System.out.print("\n");
            System.out.println("-----------------------------------");

            if (!playAgain.equals("")) {
                sauvgarderStat();
                break;
            }
        }
    }

    void displayCurrentState() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print("(" + matrix[j][i] + ") | ");
            }
            System.out.println();
        }
    }

    private void spin() {
        Random random = new Random();

        for (int i = 0; i < 3; i++) {
            int randomPosition = random.nextInt(matrix[i].length);

            for (int j = 0; j < 3; j++) {
                matrix[j][i] = columns[i][(randomPosition + j) % columns[i].length];
            }
        }
    }


    int Symbole(String winValue) {
        // Tableau clé-valeur avec la valeur de chaque symbole
        String[][] symboleValues = {
                {"300", "7"},
                {"100", "BAR"},
                {"15", "R"},
                {"15", "P"},
                {"15", "T"},
                {"8", "C"}
        };

        for (int i = 0; i < symboleValues.length; i++) {
            if (symboleValues[i][1].equals(String.valueOf(winValue))) {
                return Integer.parseInt(symboleValues[i][0]);
            }
        }

        return 0;
    }


    int verfierGagne(int bet) {

        int winnings = 0;

        for (int i = 0; i < 3; i++) {

            if (matrix[0][i].equals(matrix[1][i]) && matrix[1][i].equals(matrix[2][i])) {
                System.out.println("WIN 1");

                Symbole(String.valueOf(matrix[0][i]));
                winnings += Symbole(String.valueOf(matrix[0][i]));
            }
        }

        if (matrix[0][0].equals(matrix[1][1]) && matrix[1][1].equals(matrix[2][2])) {
            System.out.println("WIN 2");
            Symbole(String.valueOf(matrix[0][0]));
            winnings += Symbole(String.valueOf(matrix[0][0]));
        }

        if (matrix[0][2].equals(matrix[1][1]) && matrix[1][1].equals(matrix[2][0])) {
            System.out.println("WIN 3");
            Symbole(String.valueOf(matrix[0][2]));
            winnings += Symbole(String.valueOf(matrix[0][2]));
        }
        return winnings;
    }

    private void sauvgarderStat() {
        Gson gson = new Gson();
        String statisticsJson = gson.toJson(getStatistics());
        System.out.println("Statistiques enregistrées : \n" + statisticsJson);
    }

    private Statistics getStatistics() {
        return new Statistics(coins, partieJouer, partieGagner, jetonsUtiliser);
    }

    public int getCoins() {
        return coins;
    }

    private static class Statistics {
        private int jetons;
        private int partieJouer;
        private int partieGagner;
        private int jetonsPerdu;

        public Statistics(int jetons, int partieJouer, int partieGagner, int jetonsPerdu) {
            this.jetons = jetons;
            this.partieJouer = partieJouer;
            this.partieGagner = partieGagner;
            this.jetonsPerdu = jetonsPerdu;
        }
    }
}

