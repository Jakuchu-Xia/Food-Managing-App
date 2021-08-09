package ui.panel;

import model.Food;
import model.FoodStorage;
import persistence.JsonReader;
import persistence.JsonWriter;
import ui.SoundEffect;

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

    private FoodStorage foodStorage;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    private JLabel label = new JLabel();

    private SoundEffect sound;

    // MODIFIES: this
    // EFFECTS: create and initialize a main frame
    public MainFrame(String name) {
        super(name);
        initializeFields();
        initializeGraphics();
        initializeSound();
    }

    // MODIFIES: this
    // EFFECTS: initialize sound
    private void initializeSound() {
        sound = new SoundEffect();
    }

    // MODIFIES: this
    // EFFECTS: initialize graphics
    private void initializeGraphics() {
        setLayout(new BorderLayout());
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        add(label, BorderLayout.NORTH);
        createButtonArea();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: initialize fields
    private void initializeFields() {
        foodStorage = new FoodStorage();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        label = new JLabel("Select the following:");
    }

    // MODIFIES: this
    // EFFECTS: create an area for buttons
    private void createButtonArea() {
        JPanel buttonArea = new JPanel();
        buttonArea.setLayout(new GridLayout(0,1));
        buttonArea.setSize(new Dimension(0, 0));
        add(buttonArea, BorderLayout.CENTER);

        createMainButtons(buttonArea, "View", new ViewToolClickHandler());
        createMainButtons(buttonArea, "Search", new SearchToolClickHandler());
        createMainButtons(buttonArea, "Store", new StoreToolClickHandler());
        createMainButtons(buttonArea, "Remove", new RemoveToolClickHandler());
        createMainButtons(buttonArea, "Update", new UpdateToolClickHandler());
        createMainButtons(buttonArea, "Save", new SaveToolClickHandler());
        createMainButtons(buttonArea, "Load", new LoadToolClickHandler());
    }

    // MODIFIES: this
    // EFFECTS: create a button based on given name and listener
    private void createMainButtons(JComponent parent, String name, ActionListener listener) {
        JButton button = new JButton(name);
        parent.add(button);
        button.addActionListener(listener);
    }

    // Represent a click handler for view button
    private class ViewToolClickHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            new ViewFrame("View Panel", foodStorage);
        }
    }

    // Represent a click handler for Search button
    private class SearchToolClickHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            new SearchFrame("Search Panel", foodStorage);
        }
    }

    // Represent a click handler for store button
    private class StoreToolClickHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            new StoreFrame("Store Panel", foodStorage);
        }
    }

    // Represent a click handler for remove button
    private class RemoveToolClickHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            new RemoveFrame("Remove Panel", foodStorage);
        }
    }

    // Represent a click handler for save button
    private class SaveToolClickHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                jsonWriter.open();
                jsonWriter.write(foodStorage);
                jsonWriter.close();
                System.out.println("Saved your food storage to " + JSON_STORE);
                sound.getSoundSuccess().play();
            } catch (FileNotFoundException fnfe) {
                System.out.println("Unable to write to file: " + JSON_STORE);
            }
        }
    }

    // Represent a click handler for load button
    private class LoadToolClickHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                foodStorage = jsonReader.read();
                System.out.println("Loaded your food storage from " + JSON_STORE);
                sound.getSoundSuccess().play();
            } catch (IOException ioe) {
                System.out.println("Unable to read from file: " + JSON_STORE);
            }
        }
    }

    // Represent a click handler for update button
    private class UpdateToolClickHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            for (Food f : foodStorage.getFoodList()) {
                f.reduceDaysLeftByOne();
            }
            System.out.println("All food states have been updated");
            sound.getSoundSuccess().play();
        }
    }
}
