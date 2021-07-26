package model;

// Represent a food having its name, price (in dollars), storage condition, days left etc.
public class Food {
    private String name;
    private double price;
    private String storageCond;
    private int daysLeft;
    private boolean isPassedExpDate = false;

    // REQUIRES: price >= 0, daysLeft >= 0
    // EFFECTS: construct a food by given parameter
    public Food(String name, double price, String storageCond, int daysLeft) {
        this.name = name;
        this.price = price;
        this.storageCond = storageCond;
        this.daysLeft = daysLeft;
    }

    //EFFECTS: return name
    public String getName() {
        return name;
    }

    // EFFECTS: return price
    public double getPrice() {
        return price;
    }

    // EFFECTS: return days left
    public int getDaysLeft() {
        return daysLeft;
    }

    //EFFECTS: return isPassedExpDate
    public boolean getIsPassedExpDate() {
        return isPassedExpDate;
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
}
