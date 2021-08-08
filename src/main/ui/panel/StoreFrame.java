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

    public StoreFrame(String name, FoodStorage foodStorage) {
        super(name);
        this.foodStorage = foodStorage;
        initializeFields();
        initializeGraphics();
        initializeSound();
        initializeInteraction();
    }

    private void initializeInteraction() {
        confirmButton.addActionListener(new ConfirmClickHandler());
    }

    private void initializeSound() {
    }

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

    private void setConfirmButton() {
        contentPane.add(confirmButton);

        layout.putConstraint(SpringLayout.WEST, confirmButton,
                WIDTH / 2,
                SpringLayout.WEST, contentPane);
        layout.putConstraint(SpringLayout.NORTH, confirmButton,
                5,
                SpringLayout.SOUTH, labelDaysLeft);
    }

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

    private class ConfirmClickHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String name = textFieldName.getText();
            double price = Double.parseDouble(textFieldPrice.getText());
            String storageCond = textFieldStorageCond.getText();
            int daysLeft = Integer.parseInt(textFieldDaysLeft.getText());

            doStore(name, price, storageCond, daysLeft);
        }

        private void doStore(String name, Double price, String storageCond, int daysLeft) {
            SoundEffect sound = new SoundEffect();

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
