package org.example;

import java.util.ArrayList;
import java.util.Scanner;


public class App {


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Double> priser = new ArrayList<>();
        ArrayList<String> timestamps = new ArrayList<>();
        boolean avslut = true;
        while (avslut) {

            String menu = """
                    Elpriser
                    ========
                    1. Inmatning
                    2. Min, Max och Medel
                    3. Sortera
                    4. Bästa Laddningstid (4h)
                    e. Avsluta
                    """;
            System.out.print(menu);
            String val = sc.nextLine().toLowerCase();

            switch (val.toLowerCase()) {
                case "1":
                    System.out.print("Ange elpriserna för varje timme (hela öre):\n");
                    for (int hour = 0; hour < 24; hour++) {
                        String timestamp = String.format("%02d-%02d", hour, hour + 1);
                        timestamps.add(timestamp);
                        System.out.print("Timme " + timestamp + ": ");
                        double price = sc.nextDouble();
                        priser.add(price);
                        sc.nextLine();
                    }
                    break;

                case "2":
                    int findMinIndex = findMinIndex(priser);
                    int findMaxIndex = findMaxIndex(priser);

                    System.out.printf("Lägsta pris: %s, %.0f öre/kWh\n", timestamps.get(findMinIndex), priser.get(findMinIndex));
                    System.out.printf("Högsta pris: %s, %.0f öre/kWh\n", timestamps.get(findMaxIndex), priser.get(findMaxIndex));
                    System.out.printf("Medelpris: " + medelVarde(priser) + " öre/kWh\n");
                    break;

                case "3":
                    bubbleSort(priser, timestamps);
                    for (int i = 0; i < priser.size(); i++) {
                        System.out.print(timestamps.get(i) + " " + String.format("%.0f", priser.get(i)) + " öre\n");
                    }
                    break;

                case "4":
                    String result = cheapest4Hours(priser, timestamps);
                    System.out.println(result);
                    break;


                case "e":

                    avslut = false;
                    return;


                default:
                    System.out.println("Ogiltigt val.");
            }
        }
    }

    public static String formatPrice(double price) {
        return String.format("%.2f", price).replace('.', ',');
    }


    public static int findMinIndex(ArrayList<Double> priser) {
        int minIndex = 0;
        double minPrice = priser.get(0);
        for (int i = 1; i < priser.size(); i++) {
            if (priser.get(i) < minPrice) {
                minIndex = i;
                minPrice = priser.get(i);
            }
        }
        return minIndex;
    }

    public static int findMaxIndex(ArrayList<Double> priser) {
        int maxIndex = 0;
        double maxPrice = priser.get(0);
        for (int i = 1; i < priser.size(); i++) {
            if (priser.get(i) > maxPrice) {
                maxIndex = i;
                maxPrice = priser.get(i);
            }
        }
        return maxIndex;
    }

    public static String medelVarde(ArrayList<Double> priser) {
        double sum = 0;
        for (double pris : priser) {
            sum += pris;
        }
        double average = sum / priser.size();
        return formatPrice(average);
    }

    public static void bubbleSort(ArrayList<Double> priser, ArrayList<String> timestamps) {
        boolean swapped;
        for (int i = 0; i < priser.size() - 1; i++) {
            swapped = false;
            for (int j = 0; j < priser.size() - i - 1; j++) {
                if (priser.get(j) < priser.get(j + 1)) {
                    double tempPrice = priser.get(j);
                    priser.set(j, priser.get(j + 1));
                    priser.set(j + 1, tempPrice);

                    String tempTimestamp = timestamps.get(j);
                    timestamps.set(j, timestamps.get(j + 1));
                    timestamps.set(j + 1, tempTimestamp);

                    swapped = true;
                }
            }
            if (!swapped) {
                break;


            }
        }
    }

    public static String cheapest4Hours(ArrayList<Double> priser, ArrayList<String> timestamps) {

        int cheapestStartIndex = 0;
        double minTotal = Double.MAX_VALUE;

        for (int i = 0; i < priser.size() - 3; i++) {
            double currentTotal = 0;
            for (int j = i; j < i + 4; j++) {
                currentTotal += priser.get(j);
            }

            if (currentTotal < minTotal) {
                cheapestStartIndex = i;
                minTotal = currentTotal;
            }
        }

        double averagePrice = minTotal / 4;

        // Build the result string
        String startTime = timestamps.get(cheapestStartIndex).split("-")[0];
        String endTime = timestamps.get(cheapestStartIndex + 3);
        return String.format("Påbörja laddning klockan %s\nMedelpris 4h: %.1f öre/kWh\n", startTime, averagePrice);
    }
}

