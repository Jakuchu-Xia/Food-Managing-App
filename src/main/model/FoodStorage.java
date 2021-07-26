package model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

// Represent an list of Food
public class FoodStorage {
    private LinkedList<Food> storage;

    // EFFECTS: construct a food storage as an empty storage
    public FoodStorage() {
        storage = new LinkedList<Food>();
    }

    // MODIFIES: this
    // EFFECTS: add given food to the storage
    public void storeFood(Food food) {
        storage.add(food);
    }

    // MODIFIES: this
    // EFFECTS: remove the given food from the storage
    public void removeFood(Food food) {
        storage.remove(food);
    }

    // EFFECTS: return the list of name of all the food
    public List<String> displayAllFood() {
        ArrayList<String> foodList = new ArrayList<String>();

        for (Food f: storage) {
            foodList.add(f.getName());
        }

        return foodList;
    }

    // EFFECTS: return the list of name of all the food by given days left
    public List<String> displayFoodByDaysLeft(int days) {
        ArrayList<String> foodList = new ArrayList<String>();

        for (Food f: storage) {
            if (f.getDaysLeft() == days) {
                foodList.add(f.getName());
            }
        }

        return foodList;
    }

    // EFFECTS: sum up the total price of the food and return
    public double getTotalPrice() {
        double totalPrice = 0;

        for (Food f: storage) {
            totalPrice += f.getPrice();
        }

        return totalPrice;
    }


    // EFFECTS:
    public int totalAmount() {
        int amount = 0;

        for (Food f: storage) {
            amount += 1;
        }

        return amount;
    }

    // REQUIRES: position >= 1
    // EFFECTS:
    public Food position(int position) {
        return storage.get(position - 1);
    }
}
