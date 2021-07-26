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

    // MODIFIES: this
    // EFFECTS: initialize the storage and process the main menu
    public void runFoodManagerApp() {

        init();

        processMainMenu();

    }

    // MODIFIES: this
    // EFFECTS: initialize storage
    private void init() {
        storage = new FoodStorage();
        input = new Scanner(System.in);
    }

    // MODIFIES: this
    // EFFECTS: process commend in main menu
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
                doSearch();
            } else if (commend == 5) {
                doRecordDays();
            } else if (commend == 6) {
                keepGoing = false;
            } else {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    // EFFECTS: display menu of options to user
    private void displayMainMenu() {
        System.out.println("\nSelect the following:");
        System.out.println("\t1 -> View stored food");
        System.out.println("\t2 -> Store food");
        System.out.println("\t3 -> Remove food");
        System.out.println("\t4 -> Search food");
        System.out.println("\t5 -> Update all food by one day");
        System.out.println("\t6 -> Quit");
    }

    // MODIFIES: this
    // EFFECTS: process commend in select filter page
    private void processSelectFilter() {
        displaySelectFilter();

        int commend = input.nextInt();

        if (commend == 1) {
            for (String f : storage.displayAllFood()) {
                System.out.println(f);
            }
        } else if (commend == 2) {
            doFilterByLeftDays();
        } else if (commend == 3) {
            processMainMenu();
        } else {
            System.out.println("Please enter a valid number.");
        }
    }

    // EFFECTS: display menu of options to user
    private void displaySelectFilter() {
        System.out.println("\nSelect the following:");
        System.out.println("\t1 -> Show all");
        System.out.println("\t2 -> By days left");
        System.out.println("\t3 -> Back");
    }

    // EFFECTS: print all food with given days left
    private void doFilterByLeftDays() {
        System.out.println("Please enter the number of days left");
        int commend = input.nextInt();

        for (String f : storage.displayFoodByDaysLeft(commend)) {
            System.out.println(f);
        }
    }

    // MODIFIES: this
    // EFFECTS: store the given food to storage
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
            System.out.println("Failed: Both of price and number of days cannot be negative");
        } else {
            Food food = new Food(name, price, storageCond, daysLeft);
            storage.storeFood(food);
            System.out.println("The food has been successfully stored");
        }
    }

    // MODIFIES: this
    // EFFECTS: remove the given food from storage
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

    // MODIFIES: this
    // EFFECTS: search and display the food
    private void doSearch() {
        System.out.println("Please enter the exact name of the food");
        String name = input.next();

        boolean isNameExist = storage.findFoodByName(name);

        if (isNameExist) {
            Food foundFood = storage.getFoundFood();
            System.out.println("\nName: " + foundFood.getName() + "\nPrice: " + foundFood.getPrice()
                    + "\nDays Left: " + foundFood.getDaysLeft()
                    + "\nStorage Condition: " + foundFood.getStorageCond());
        } else {
            System.out.println("The food is not found, check the name again");
        }
    }

    // MODIFIES: this
    // EFFECTS: Reduce days left of all food
    private void doRecordDays() {
        for (Food f : storage.getFoodList()) {
            f.reduceDaysLeftByOne();
        }
        System.out.println("All food states have been updated");
    }
}
