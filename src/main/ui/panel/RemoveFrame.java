package ui.panel;

import model.FoodStorage;
import ui.SoundEffect;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RemoveFrame extends JFrame {

    public static final int WIDTH = 300;
    public static final int HEIGHT = 100;

    private FoodStorage foodStorage;

    private SpringLayout layout;
    private Container contentPane;

    private JLabel labelName;
    private JTextField textFieldName;
    private JButton confirmButton;

    private SoundEffect sound;

    // MODIFIES: this
    // EFFECTS: create a remove frame with fields and graphics initialized
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

        textFieldName = new JTextField(16);

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
                5,
                SpringLayout.SOUTH, labelName);
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

            boolean isNameExist = foodStorage.findFoodByName(name);

            if (isNameExist) {
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
