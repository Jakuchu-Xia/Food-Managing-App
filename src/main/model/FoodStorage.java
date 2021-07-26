package model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

// Represent an list of Food
public class FoodStorage {
    private LinkedList<Food> foodList;
    private Food foundFood;

    // EFFECTS: construct a food storage as an empty storage
    public FoodStorage() {
        foodList = new LinkedList<Food>();
    }

    // MODIFIES: this
    // EFFECTS: add given food to the storage
    public void storeFood(Food food) {
        foodList.add(food);
    }

    // MODIFIES: this
    // EFFECTS: remove the given food from the storage
    public void removeFood(Food food) {
        foodList.remove(food);
    }

    // EFFECTS: return the list of name of all the food
    public List<String> displayAllFood() {
        ArrayList<String> displayFoodList = new ArrayList<String>();

        for (Food f : this.foodList) {
            displayFoodList.add(f.getName());
        }

        return displayFoodList;
    }

    // REQUIRES: days >= 0
    // EFFECTS: return the list of name of all the food by given days left
    public List<String> displayFoodByDaysLeft(int days) {
        ArrayList<String> displayFoodList = new ArrayList<String>();

        for (Food f: this.foodList) {
            if (f.getDaysLeft() == days) {
                displayFoodList.add(f.getName());
            }
        }

        return displayFoodList;
    }

    // EFFECTS: sum up the total price of the food and return
    public double getTotalPrice() {
        double totalPrice = 0;

        for (Food f: foodList) {
            totalPrice += f.getPrice();
        }

        return totalPrice;
    }


    // EFFECTS: return the number of elements in foodList
    public int totalAmount() {
        int amount = 0;

        for (Food f: foodList) {
            amount += 1;
        }

        return amount;
    }

    // REQUIRES: position >= 1
    // EFFECTS: return the food of given position
    public Food position(int position) {
        return foodList.get(position - 1);
    }

    // EFFECTS: return true if the food is contained in the storage, false otherwise
    public boolean isContain(Food food) {
        return foodList.contains(food);
    }

    // MODIFIES: this
    // EFFECTS: return true and assign the given name to found name if the food is found, false if not found
    public boolean findFoodByName(String name) {
        for (Food f : foodList) {
            if (f.getName().equals(name)) {
                foundFood = f;
                return true;
            }
        }
        return false;
    }

    // EFFECTS: return foodList
    public LinkedList<Food> getFoodList() {
        return foodList;
    }

    // EFFECTS: return found food
    public Food getFoundFood() {
        return foundFood;
    }
}