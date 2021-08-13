package ui.panel;

import model.FoodStorage;
import model.Unit;
import exceptions.NegativeValueException;
import exceptions.UnitMismatchException;
import ui.SoundEffect;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class RemoveFrame extends JFrame {

    public static final int WIDTH = 300;
    public static final int HEIGHT = 150;

    String[] unitEnum = Unit.getList();

    private FoodStorage foodStorage;

    private SpringLayout layout;
    private Container contentPane;

    private JLabel labelName;
    private JLabel labelAmount;
    private JTextField textFieldName;
    private JTextField textFieldAmount;
    private JComboBox unitList;
    private JButton confirmButton;

    private SoundEffect sound;

    // MODIFIES: this
    // EFFECTS: create and initialize a remove frame
    public RemoveFrame(String name, FoodStorage foodStorage) {
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
        labelName = new JLabel("Remove food:");
        labelAmount = new JLabel("By amount of");
        textFieldName = new JTextField(16);
        textFieldAmount = new JTextField(10);
        unitList = new JComboBox(unitEnum);
        unitList.setSelectedIndex(unitEnum.length - 1);
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
        setAmountLayout();
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
    // EFFECTS: initialize interaction
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
                15,
                SpringLayout.SOUTH, labelAmount);
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

    // MODIFIES: this
    // EFFECTS: set the location of amount tool on layout
    private void setAmountLayout() {
        contentPane.add(labelAmount);
        contentPane.add(textFieldAmount);
        contentPane.add(unitList);

        layout.putConstraint(SpringLayout.WEST, labelAmount,
                5,
                SpringLayout.WEST, contentPane);
        layout.putConstraint(SpringLayout.NORTH, labelAmount,
                5,
                SpringLayout.SOUTH, labelName);
        layout.putConstraint(SpringLayout.WEST, textFieldAmount,
                5,
                SpringLayout.EAST, labelAmount);
        layout.putConstraint(SpringLayout.NORTH, textFieldAmount,
                5,
                SpringLayout.SOUTH, textFieldName);
        layout.putConstraint(SpringLayout.WEST, unitList,
                5,
                SpringLayout.EAST, textFieldAmount);
        layout.putConstraint(SpringLayout.NORTH, unitList,
                5,
                SpringLayout.SOUTH, textFieldName);
    }

    // Represent a click handler for confirm button
    private class ConfirmClickHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String name = textFieldName.getText();
            boolean isNameExist = foodStorage.findFoodByName(name);

            if (isNameExist && !textFieldAmount.getText().isEmpty()) {
                double amount = Double.parseDouble(textFieldAmount.getText());
                Unit unit = Unit.parseUnit((String) Objects.requireNonNull(unitList.getSelectedItem()));
                foodStorage.reduceFoodByAmount(foodStorage.getFoundFood(), amount, unit);
                sound.getSoundSuccess().play();
            } else if (textFieldAmount.getText().isEmpty()) {
                foodStorage.removeFood(foodStorage.getFoundFood());
                sound.getSoundSuccess().play();
            } else {
                System.out.println("The food is not found, check the name again");
                sound.getSoundFail().play();
            }
            setVisible(false);
        }
    }
}
