package model;

import exceptions.NegativeAmountException;
import exceptions.UnitMismatchException;
import org.json.JSONObject;
import persistence.Writable;

import static model.Unit.*;

// Represent a food having its name, price (in dollars), storage condition, days left etc.
public class Food implements Writable {

    private String name;
    private double price;
    private double amount;
    private Unit unit;
    private String storageCond;
    private int daysLeft;
    private boolean isPassedExpDate = false;

    // REQUIRES: price >= 0, daysLeft >= 0
    // EFFECTS: construct a food by given parameter
    public Food(String name, double price, double amount, Unit unit, String storageCond, int daysLeft) {
        this.name = name;
        this.price = price;
        this.storageCond = storageCond;
        this.daysLeft = daysLeft;
        this.unit = convertUnit(unit);
        this.amount = convertAmount(amount, unit);
    }

    //EFFECTS: return name
    public String getName() {
        return name;
    }

    // EFFECTS: return price
    public double getPrice() {
        return price;
    }

    // EFFECTS: return amount
    public double getAmount() {
        return amount;
    }

    // EFFECTS: return unit
    public Unit getUnit() {
        return unit;
    }

    // EFFECTS: return days left
    public int getDaysLeft() {
        return daysLeft;
    }

    // EFFECTS: return isPassedExpDate
    public boolean getIsPassedExpDate() {
        return isPassedExpDate;
    }

    // EFFECTS: return storage condition
    public String getStorageCond() {
        return storageCond;
    }

    // MODIFIES: this
    // EFFECTS: reduce the given amount of food by given unit
    public void reduceAmount(double amount, Unit unit) throws NegativeAmountException, UnitMismatchException {
        Unit convertedUnit = convertUnit(unit);

        if (convertedUnit != this.unit) {
            throw new UnitMismatchException("Unit not match!");
        }

        double convertedAmount = convertAmount(amount, unit);

        double newAmount = this.amount - convertedAmount;
        if (newAmount >= 0) {
            this.amount = newAmount;
        } else {
            throw new NegativeAmountException("Amount cannot be negative!");
        }
    }

    // MODIFIED: this
    // EFFECTS: if DaysLeft is > 0
    //              - reduce DaysLeft by one
    //          otherwise, set isPassedExpDate to true
    public void reduceDaysLeftByOne() {
        if (daysLeft > 0) {
            daysLeft -= 1;
        } else {
            isPassedExpDate = true;
        }
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("price", price);
        json.put("storageCond", storageCond);
        json.put("daysLeft", daysLeft);
        json.put("amount", amount);
        json.put("unit", unit);
        return json;
    }
}

