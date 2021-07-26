package model;

// Represent a food having its name, price (in dollars), storage condition, days left etc.
public class Food {
    private String name;
    private double price;
    private String storageCond;
    private int daysLeft;
    private boolean isInStock;

    // REQUIRES: price >= 0, daysLeft >= 0
    // EFFECTS: construct a food by given parameter
    public Food(String name, double price, String storageCond, int daysLeft, boolean isInStock) {
        this.name = name;
        this.price = price;
        this.storageCond = storageCond;
        this.daysLeft = daysLeft;
        this.isInStock = isInStock;
    }

    //EFFECTS: return name
    public String getName() {
        return name;
    }

    // EFFECTS: return price
    public double getPrice() {
        return price;
    }

    // EFFECTS: return storage condition
    public String getStorageCond() {
        return storageCond;
    }

    // EFFECTS: return days left
    public int getDaysLeft() {
        return daysLeft;
    }

    // EFFECTS: return true if food is in stock
    public boolean getIsInStock() {
        return isInStock;
    }

    // MODIFIED: this
    // EFFECTS: if DaysLeft is > 0
    //              - reduce DaysLeft by one
    //              - return true
    //          otherwise, return false
    public boolean reduceDaysLeftByOne() {
        if (daysLeft > 0) {
            daysLeft -= 1;
            return true;
        } else {
            return false;
        }
    }
}
