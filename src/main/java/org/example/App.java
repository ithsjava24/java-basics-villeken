package org.example;

import java.util.ArrayList;
import java.util.Scanner;

public class App {


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Double> priser = new ArrayList<>();

        while (true) {
            System.out.println("""
                    Elpriser
                    ============
                    1. Inmatning
                    2. Min, Max och Medel
                    3. Sortera
                    4. Bästa laddningstid (4h)
                    e. Avsluta
                    """);

            String val = sc.nextLine().toLowerCase();

            switch (val) {
                case "1":
                    System.out.print("Ange elpriserna för varje timme (hela öre):\n");
                    for (int hour = 0; hour < 24; hour++) {
                        System.out.print("Hour " + String.format("%02d", hour) + "-" + String.format("%02d", hour + 1) + ": ");
                        double price = sc.nextDouble();
                        priser.add(price);
                        sc.nextLine();
                    }
                    break;
                case "2":
                    System.out.print("Lägsta pris: " + findMin(priser) + " öre/kWh\n");
                    System.out.print("Högsta pris: " + findMax(priser) + " öre/kWh\n");
                    System.out.printf("Medelpris: " + medelVarde(priser) + " öre/kWh\n");
                    break;

                case "3":


                case "e":
                    System.out.println("Programmet avslutas.");
                    return;
                default:
                    System.out.println("Ogiltigt val.");
            }
        }
    }

    public static double findMin(ArrayList<Double> priser) {
        double min = priser.get(0);
        for (double pris : priser) {
            if (pris < min) {
                min = pris;
            }
        }
        return min;
    }

    public static double findMax(ArrayList<Double> priser) {
        double max = priser.get(0);
        for (double pris : priser) {
            if (pris > max) {
                max = pris;
            }
        }
        return max;
    }

    public static double medelVarde(ArrayList<Double> priser) {
        double sum = 0;
        for (double pris : priser) {
            sum += pris;
        }
        return sum / priser.size();
    }
}






