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

    public RemoveFrame(String name, FoodStorage foodStorage) {
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
                SpringLayout.SOUTH, labelName);
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

        labelName = new JLabel("Remove food:");

        textFieldName = new JTextField(16);

        confirmButton = new JButton("Confirm");
    }

    private class ConfirmClickHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String name = textFieldName.getText();
            SoundEffect sound = new SoundEffect();

            boolean isNameExist = foodStorage.findFoodByName(name);

            if (isNameExist) {
                foodStorage.removeFood(foodStorage.getFoundFood());
                sound.getSoundSuccess().play();
                setVisible(false);
            } else {
                System.out.println("The food is not found, check the name again");
                sound.getSoundFail().play();
                setVisible(false);
            }
        }
    }
}
