package ui.panel;

import model.Food;
import model.FoodStorage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SearchFrame extends JFrame {

    public static final int WIDTH = 300;
    public static final int HEIGHT = 700;

    private FoodStorage foodStorage;

    private JTextField textFieldSearch;
    private JButton searchButton;
    private JEditorPane editorPane;
    private JScrollPane scroll;

    // MODIFIES: this
    // EFFECTS: create and initialize a search frame
    public SearchFrame(String name, FoodStorage foodStorage) {
        super(name);
        this.foodStorage = foodStorage;
        initializeFields();
        initializeGraphics();
        initializeInteraction();
    }

    // MODIFIES: this
    // EFFECTS: initialize fields
    private void initializeFields() {
        textFieldSearch = new JTextField(16);
        searchButton = new JButton("Search");
        editorPane = new JEditorPane();
        scroll = new JScrollPane(editorPane);
    }

    // MODIFIES: this
    // EFFECTS: initialize graphics
    private void initializeGraphics() {
        setLayout(new BorderLayout());
        setMinimumSize(new Dimension(WIDTH, HEIGHT));

        add(scroll);
        editorPane.setEditable(false);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        createSearchArea();

        setLocationRelativeTo(null);
        setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: initialize interaction
    private void initializeInteraction() {
        searchButton.addActionListener(new SearchClickHandler());
    }

    // MODIFIES: this
    // EFFECTS: create an area for search
    private void createSearchArea() {
        JPanel searchArea = new JPanel();
        searchArea.setSize(new Dimension(WIDTH, 30));

        searchArea.add(textFieldSearch);
        searchArea.add(searchButton);

        add(searchArea, BorderLayout.NORTH);
    }

    // Represent a click handler for search button
    private class SearchClickHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            editorPane.setText(text());
        }

        public String text() {
            String text;

            String name = textFieldSearch.getText();
            boolean isNameExist = foodStorage.findFoodByName(name);

            if (isNameExist) {
                Food foundFood = foodStorage.getFoundFood();
                text = "\nName: " + foundFood.getName() + "\nPrice: " + foundFood.getPrice()
                        + "\nAmount:" + foundFood.getAmount() + " " + foundFood.getUnit()
                        + "\nDays Left: " + foundFood.getDaysLeft()
                        + "\nStorage Condition: " + foundFood.getStorageCond();
            } else {
                text = "No result is found, check the name again";
            }
            return text;
        }
    }
}
