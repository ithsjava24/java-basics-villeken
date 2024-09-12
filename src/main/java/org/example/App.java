package org.example;

import java.util.ArrayList;
import java.util.Scanner;


public class App {


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Double> priser = new ArrayList<>();
        do {
            System.out.print("""
                    Elpriser
                    ============
                    1. Inmatning
                    2. Min, Max och Medel
                    3. Sortera
                    4. Bästa laddningstid (4h)
                    e. Avsluta
                    """);
            String val = sc.nextLine().toLowerCase();

            switch (val.toLowerCase()) {
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
                    System.out.printf("Lägsta pris: " + findMin(priser) + " öre/kWh\n");
                    System.out.printf("Högsta pris: " + findMax(priser) + " öre/kWh\n");
                    System.out.printf("Medelpris: " + medelVarde(priser) + " öre/kWh\n");
                    break;

                case "3":
                    bubbleSort(priser);
                    System.out.println("Elpriser sorterade efter pris:");
                    for (int i = 0; i < priser.size(); i++) {
                        System.out.println(String.format("%02d-%02d", i, i + 1) + ": " + priser.get(i) + " öre");
                    }
                    break;


                case "e":
                    System.out.println("Programmet avslutas.");

                    return;
                default:
                    System.out.println("Ogiltigt val.");
            }
        } while (true);
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

    public static void bubbleSort(ArrayList<Double> list) {
        boolean swapped;
        for (int i = 0; i < list.size() - 1; i++) {
            swapped = false;
            for (int j = 0; j < list.size() - i - 1; j++) {
                if (list.get(j) < list.get(j + 1))
                {
                    double temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);


                    swapped = true;
                }
            }
            if (!swapped) {
                break;


            }
        }
    }
}












