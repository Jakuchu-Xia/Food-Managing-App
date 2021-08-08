package ui.panel;

import model.FoodStorage;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MainFrame extends JFrame {

    private static final String JSON_STORE = "./data/foodStorage.json";

    public static final int WIDTH = 300;
    public static final int HEIGHT = 500;

    private FoodStorage foodStorage = new FoodStorage();
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    private JLabel label = new JLabel();

    public MainFrame(String name) {
        super(name);
        initializeFields();
        initializeGraphics();
        initializeSound();
    }

    private void initializeSound() {
    }

    private void initializeGraphics() {
        setLayout(new BorderLayout());
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        add(label, BorderLayout.NORTH);
        createButtonArea();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initializeFields() {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        label.setText("Select the following:");
    }

    private void createButtonArea() {
        JPanel buttonArea = new JPanel();
        buttonArea.setLayout(new GridLayout(0,1));
        buttonArea.setSize(new Dimension(0, 0));
        add(buttonArea, BorderLayout.CENTER);

        createMainButtons(buttonArea, "View", new ViewToolClickHandler());
        createMainButtons(buttonArea, "Store", new StoreToolClickHandler());
        createMainButtons(buttonArea, "Remove", new RemoveToolClickHandler());
        createMainButtons(buttonArea, "Save", new SaveToolClickHandler());
        createMainButtons(buttonArea, "Update", new UpdateToolClickHandler());
    }

    private void createMainButtons(JComponent parent, String name, ActionListener listener) {
        JButton button = new JButton(name);
        parent.add(button);
        button.addActionListener(listener);
    }

    private class ViewToolClickHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            new ViewFrame("View Panel", foodStorage);

        }
    }

    private class StoreToolClickHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            new StoreFrame("Store Panel", foodStorage);
        }
    }

    private class RemoveToolClickHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            new RemoveFrame("Remove Panel", foodStorage);
        }
    }

    private class SaveToolClickHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                jsonWriter.open();
                jsonWriter.write(foodStorage);
                jsonWriter.close();
                System.out.println("Saved your food storage to " + JSON_STORE);
            } catch (FileNotFoundException fnfe) {
                System.out.println("Unable to write to file: " + JSON_STORE);
            }
        }
    }

    private class UpdateToolClickHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                foodStorage = jsonReader.read();
                System.out.println("Loaded your food storage from " + JSON_STORE);
            } catch (IOException ioe) {
                System.out.println("Unable to read from file: " + JSON_STORE);
            }
        }
    }
}
