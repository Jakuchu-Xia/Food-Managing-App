package model;

import exceptions.NegativeValueException;
import exceptions.UnitMismatchException;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static model.Unit.convertAmount;

// Represent an list of Food
public class FoodStorage implements Writable {
    private LinkedList<Food> foodList;
    private Food foundFood;

    // EFFECTS: construct a food storage as an empty storage
    public FoodStorage() {
        foodList = new LinkedList<>();
    }

    // MODIFIES: this
    // EFFECTS: add given food to the storage
    public void storeFood(Food food) {
        foodList.add(food);
    }

    // MODIFIES: this
    // EFFECTS: reduce the given food by given amount, remove it if amount is zero
    public void reduceFoodByAmount(Food food, double amount, Unit unit) {
        Food foodToReduce = foodList.get(foodList.indexOf(food));
        double convertedAmount = convertAmount(amount, unit);

        if (food.getAmount() - convertedAmount == 0) {
            removeFood(food);
        } else {
            try {
                foodToReduce.reduceAmount(amount, unit);
            } catch (NegativeValueException | UnitMismatchException e) {
                e.printStackTrace();
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: remove the given food
    public void removeFood(Food food) {
        foodList.remove(food);
    }

    // EFFECTS: return the list of name of all the food
    public List<String> displayAllFood() {
        ArrayList<String> displayFoodList = new ArrayList<>();

        for (Food f : this.foodList) {
            displayFoodList.add(f.getName());
        }

        return displayFoodList;
    }

    // REQUIRES: days >= 0
    // EFFECTS: return the list of name of all the food by given days left
    public List<String> displayFoodByDaysLeft(int days) {
        ArrayList<String> displayFoodList = new ArrayList<>();

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

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("food list", foodToJson());
        return json;
    }

    //EFFECTS: put all food into a json array and return it
    private JSONArray foodToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Food f : foodList) {
            jsonArray.put(f.toJson());
        }

        return jsonArray;
    }
}
