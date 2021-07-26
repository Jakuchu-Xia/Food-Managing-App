package ui;


import model.Food;
import model.FoodStorage;

import java.util.Scanner;

// Food Manager application
public class FoodManagerApp {
    private FoodStorage storage;
    private Scanner input;

    // EFFECTS: run the food manager application
    public FoodManagerApp() {
        runFoodManagerApp();
    }

    // MODIFIES
    // EFFECTS:
    public void runFoodManagerApp() {

        init();

        processMainMenu();

    }

    //
    private void init() {
        storage = new FoodStorage();
        input = new Scanner(System.in);
    }

    private void processMainMenu() {
        boolean keepGoing = true;
        int commend;

        while (keepGoing) {
            displayMainMenu();
            commend = input.nextInt();

            if (commend == 1) {
                processSelectFilter();
            } else if (commend == 2) {
                doStore();
            } else if (commend == 3) {
                doRemove();
            } else if (commend == 4) {
                doRecordDays();
            } else if (commend == 5) {
                keepGoing = false;
            } else {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    private void displayMainMenu() {
        System.out.println("\nSelect the following:");
        System.out.println("\t1 -> View stored food");
        System.out.println("\t2 -> Store food");
        System.out.println("\t3 -> Remove food");
        System.out.println("\t4 -> View and record days that have passed");
        System.out.println("\t5 -> Quit");
    }

    private void processSelectFilter() {
        displaySelectFilter();

        int commend = input.nextInt();

        if (commend == 1) {
            for (String f : storage.displayAllFood()) {
                System.out.println(f);
            }
        } else if (commend == 2) {
            doSearch();
        } else if (commend == 3) {
            processMainMenu();
        } else {
            System.out.println("Please enter a valid number.");
        }
    }

    private void displaySelectFilter() {
        System.out.println("\nSelect the following:");
        System.out.println("\t1 -> Show all");
        System.out.println("\t2 -> By days left");
        System.out.println("\t3 -> Back");
    }

    //
    private void doSearch() {
        System.out.println("Please enter the number of days left");
        int commend = input.nextInt();

        for (String f : storage.displayFoodByDaysLeft(commend)) {
            System.out.println(f);
        }
    }

    private void doStore() {
        System.out.println("Food name?");
        String name = input.next();

        System.out.println("Food price?");
        double price = input.nextDouble();

        System.out.println("Storage condition?");
        String storageCond = input.next();

        System.out.println("Days left?");
        int daysLeft = input.nextInt();

        if (price < 0 || daysLeft < 0) {
            System.out.println("Note: Both of price and number of days cannot be negative ");
        } else {
            Food food = new Food(name, price, storageCond, daysLeft);
            storage.storeFood(food);
            System.out.println("The food has been successfully stored");
        }
    }

    private void doRemove() {
        System.out.println("Please enter the exact name of the food");
        String name = input.next();

        boolean isNameExist = storage.findFoodByName(name);
        if (isNameExist) {
            storage.removeFood(storage.getFoundFood());
        } else {
            System.out.println("The food is not found, check the name again");
        }
    }

    private void doRecordDays() {
        for (Food f : storage.getFoodList()) {
            f.reduceDaysLeftByOne();
        }
        System.out.println("All food states have been updated");
    }
}
