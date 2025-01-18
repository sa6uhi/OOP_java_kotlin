import java.util.Scanner;

class CoffeeMachine {
    private static int moneyInCM;
    private static int waterInCM;
    private static int milkInCM;
    private static int beansInCM;
    private static int disposableCupsInCM ;

    private static final Scanner scanner = new Scanner(System.in);
    private static String choice = "";

    static {
        moneyInCM = 550;
        waterInCM = 400;
        milkInCM = 540;
        beansInCM = 120;
        disposableCupsInCM = 9;
    }

    protected static void selectAction() {


        while (!choice.equals("exit")) {
            System.out.println("Write action (buy, fill, take, remaining, exit): ");
            choice = scanner.nextLine();
            switch (choice) {
                case "buy" -> buy();
                case "fill" -> fill();
                case "take" -> take();
                case "remaining" -> remaining();
            }
        }

    }

    private static void buy() {
        System.out.println("\nWhat do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu: ");
        String choice = scanner.nextLine();
        switch (choice) {
            case "1" -> checkIngredients(250, 0, 16, 4);
            case "2" -> checkIngredients(350, 75, 20, 7);
            case "3" -> checkIngredients(200, 100, 12, 6);
            case "back" -> selectAction();
        }
    }

    private static void checkIngredients(int water, int milk, int beans, int moneyForCoffee) {
        if (waterInCM >= water && milkInCM >= milk && beansInCM >= beans && disposableCupsInCM > 0) {
            System.out.println("I have enough resources, making you a coffee!");
            waterInCM -= water;
            milkInCM -= milk;
            beansInCM -= beans;
            disposableCupsInCM -= 1;
            moneyInCM += moneyForCoffee;
        } else if (waterInCM < water) {
            System.out.println("Sorry, not enough water!");
        } else if (milkInCM < milk) {
            System.out.println("Sorry, not enough milk!");
        } else if (beansInCM < beans) {
            System.out.println("Sorry, not enough coffee beans!");
        } else if (disposableCupsInCM < 1) {
            System.out.println("Sorry, not enough disposable cups!");
        }
        System.out.println();

    }

    private static void fill() {
        System.out.println("\nWrite how many ml of water you want to add:");
        waterInCM += scanner.nextInt();
        System.out.println("Write how many ml of milk you want to add:");
        milkInCM += scanner.nextInt();
        System.out.println("Write how many grams of coffee beans you want to add:");
        beansInCM += scanner.nextInt();
        System.out.println("Write how many disposable cups you want to add:");
        disposableCupsInCM += scanner.nextInt();
    }

    private static void take() {
        System.out.println("I gave you $" + moneyInCM);
        System.out.println();
        moneyInCM = 0;
    }

    private static void remaining() {
        System.out.println("\nThe coffee machine has:");
        System.out.printf("%d ml of water\n", waterInCM);
        System.out.printf("%d ml of milk\n", milkInCM);
        System.out.printf("%d g of coffee beans\n", beansInCM);
        System.out.printf("%d disposable cups\n", disposableCupsInCM);
        System.out.printf("$%d of money\n\n", moneyInCM);
    }
}