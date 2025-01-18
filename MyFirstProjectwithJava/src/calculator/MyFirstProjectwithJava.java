package calculator;

import java.util.Scanner;

public class MyFirstProjectwithJava {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("""
                Earned amount:
                Bubblegum: $202
                Toffee: $118
                Ice cream: $2250
                Milk chocolate: $1680
                Doughnut: $1075
                Pancake: $80""");
        int totalEarning = 202 + 118 + 2250 + 1680 + 1075 + 80;
        System.out.println("Income: $" + totalEarning);

        System.out.println("Staff expenses:");
        int staffExpenses = scanner.nextInt();
        System.out.println("Other expenses:");
        int otherExpenses = scanner.nextInt();

        int netIncome = totalEarning - staffExpenses - otherExpenses;
        System.out.println("Net income: $" + netIncome);
    }
}