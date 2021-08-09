package ui.panel;

import model.Food;
import model.FoodStorage;
import ui.SoundEffect;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StoreFrame extends JFrame {

    public static final int WIDTH = 500;
    public static final int HEIGHT = 200;

    private FoodStorage foodStorage;

    private SpringLayout layout;
    private Container contentPane;

    private JLabel labelName;
    private JLabel labelPrice;
    private JLabel labelStorageCond;
    private JLabel labelDaysLeft;

    private JTextField textFieldName;
    private JTextField textFieldPrice;
    private JTextField textFieldStorageCond;
    private JTextField textFieldDaysLeft;

    private JButton confirmButton;

    private SoundEffect sound;

    // MODIFIES: this
    // EFFECTS: create and initialize a store frame
    public StoreFrame(String name, FoodStorage foodStorage) {
        super(name);
        this.foodStorage = foodStorage;
        initializeFields();
        initializeGraphics();
        initializeSound();
        initializeInteraction();
    }

    // MODIFIES: this
    // EFFECTS: initialize fields
    private void initializeFields() {
        layout = new SpringLayout();
        contentPane = this.getContentPane();

        labelName = new JLabel("Name:");
        labelPrice = new JLabel("Price:");
        labelStorageCond = new JLabel("Storage Condition:");
        labelDaysLeft = new JLabel("Days Left:");

        textFieldName = new JTextField(16);
        textFieldPrice = new JTextField(16);
        textFieldStorageCond = new JTextField(16);
        textFieldDaysLeft = new JTextField(16);

        confirmButton = new JButton("Confirm");
    }

    // MODIFIES: this
    // EFFECTS: initialize graphics
    private void initializeGraphics() {
        setLocation(700, 400);
        contentPane.setLayout(layout);

        setLayout(layout);
        setMinimumSize(new Dimension(WIDTH, HEIGHT));

        setNameLayout();
        setPriceLayout();
        setStorageCondLayout();
        setDaysLeftLayout();
        setConfirmButton();

        pack();
        setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: initialize sound
    private void initializeSound() {
        sound = new SoundEffect();
    }

    // MODIFIES: this
    // EFFECTS: initialize interaction of the buttons
    private void initializeInteraction() {
        confirmButton.addActionListener(new ConfirmClickHandler());
    }

    // MODIFIES: this
    // EFFECTS: set the location of confirm button on layout
    private void setConfirmButton() {
        contentPane.add(confirmButton);

        layout.putConstraint(SpringLayout.WEST, confirmButton,
                WIDTH / 2,
                SpringLayout.WEST, contentPane);
        layout.putConstraint(SpringLayout.NORTH, confirmButton,
                5,
                SpringLayout.SOUTH, labelDaysLeft);
    }

    // MODIFIES: this
    // EFFECTS: set the location of days left tool on layout
    private void setDaysLeftLayout() {
        contentPane.add(labelDaysLeft);
        contentPane.add(textFieldDaysLeft);

        layout.putConstraint(SpringLayout.WEST, labelDaysLeft,
                5,
                SpringLayout.WEST, contentPane);
        layout.putConstraint(SpringLayout.NORTH, labelDaysLeft,
                10,
                SpringLayout.SOUTH, labelStorageCond);
        layout.putConstraint(SpringLayout.WEST, textFieldDaysLeft,
                5,
                SpringLayout.EAST, labelDaysLeft);
        layout.putConstraint(SpringLayout.NORTH, textFieldDaysLeft,
                5,
                SpringLayout.SOUTH, textFieldStorageCond);
    }

    // MODIFIES: this
    // EFFECTS: set the location of storage condition tool on layout
    private void setStorageCondLayout() {
        contentPane.add(labelStorageCond);
        contentPane.add(textFieldStorageCond);

        layout.putConstraint(SpringLayout.WEST, labelStorageCond,
                5,
                SpringLayout.WEST, contentPane);
        layout.putConstraint(SpringLayout.NORTH, labelStorageCond,
                10,
                SpringLayout.SOUTH, labelPrice);
        layout.putConstraint(SpringLayout.WEST, textFieldStorageCond,
                5,
                SpringLayout.EAST, labelStorageCond);
        layout.putConstraint(SpringLayout.NORTH, textFieldStorageCond,
                5,
                SpringLayout.SOUTH, textFieldPrice);
    }

    // MODIFIES: this
    // EFFECTS: set the location of price tool on layout
    private void setPriceLayout() {
        contentPane.add(labelPrice);
        contentPane.add(textFieldPrice);

        layout.putConstraint(SpringLayout.WEST, labelPrice,
                5,
                SpringLayout.WEST, contentPane);
        layout.putConstraint(SpringLayout.NORTH, labelPrice,
                10,
                SpringLayout.SOUTH, labelName);
        layout.putConstraint(SpringLayout.WEST, textFieldPrice,
                5,
                SpringLayout.EAST, labelPrice);
        layout.putConstraint(SpringLayout.NORTH, textFieldPrice,
                5,
                SpringLayout.SOUTH, textFieldName);
    }

    // MODIFIES: this
    // EFFECTS: set the location of name tool on layout
    private void setNameLayout() {
        contentPane.add(labelName);
        contentPane.add(textFieldName);

        layout.putConstraint(SpringLayout.WEST, labelName,
                5,
                SpringLayout.WEST, contentPane);
        layout.putConstraint(SpringLayout.NORTH, labelName,
                5,
                SpringLayout.NORTH, contentPane);
        layout.putConstraint(SpringLayout.WEST, textFieldName,
                5,
                SpringLayout.EAST, labelName);
        layout.putConstraint(SpringLayout.NORTH, textFieldName,
                5,
                SpringLayout.NORTH, contentPane);
    }

    // Represent a click handler for confirm button
    private class ConfirmClickHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String name = textFieldName.getText();
            double price = Double.parseDouble(textFieldPrice.getText());
            String storageCond = textFieldStorageCond.getText();
            int daysLeft = Integer.parseInt(textFieldDaysLeft.getText());

            doStore(name, price, storageCond, daysLeft);

            textFieldName.setText("");
            textFieldPrice.setText("");
            textFieldStorageCond.setText("");
            textFieldDaysLeft.setText("");
        }

        // MODIFIES: this
        // EFFECTS: store the given food to storage
        private void doStore(String name, Double price, String storageCond, int daysLeft) {

            if (price < 0 || daysLeft < 0) {
                System.out.println("Failed: Both of price and number of days cannot be negative");
                sound.getSoundFail().play();
            } else {
                Food food = new Food(name, price, storageCond, daysLeft);
                foodStorage.storeFood(food);
                System.out.println("The food has been successfully stored");
                sound.getSoundSuccess().play();
            }
        }
    }
}
