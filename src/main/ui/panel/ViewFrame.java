package ui.panel;

import model.FoodStorage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewFrame extends JFrame {

    public static final int WIDTH = 300;
    public static final int HEIGHT = 700;

    private FoodStorage foodStorage;

    private JLabel label;
    private JEditorPane editorPane;
    private JScrollPane scroll;

    // MODIFIES: this
    // EFFECTS: create a view frame with fields and graphics initialized
    public ViewFrame(String name, FoodStorage foodStorage) {
        super(name);
        initializeFields();
        initializeGraphics();
        this.foodStorage = foodStorage;
    }

    // MODIFIES: this
    // EFFECTS: initialize fields
    private void initializeFields() {
        label = new JLabel("Stored food:");
        editorPane = new JEditorPane();
        scroll = new JScrollPane(editorPane);
    }

    // MODIFIES: this
    // EFFECTS: initialize graphics
    private void initializeGraphics() {
        setLayout(new BorderLayout());
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        add(label, BorderLayout.NORTH);

        add(scroll);
        editorPane.setEditable(false);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        createButtonArea();

        setLocationRelativeTo(null);
        setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: create an area for buttons
    private void createButtonArea() {
        JPanel toolArea = new JPanel();
        toolArea.setLayout(new BorderLayout(0,1));
        toolArea.setSize(new Dimension(WIDTH, 100));
        add(toolArea, BorderLayout.SOUTH);

        createViewAllButton(toolArea);
    }

    // MODIFIES: this
    // EFFECTS: create a view all button
    private void createViewAllButton(JComponent parent) {
        JButton button = new JButton("View All");
        parent.add(button);
        button.addActionListener(new ViewAllToolClickHandler());
    }

    // Represent a click handler for view all button
    private class ViewAllToolClickHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            editorPane.setText(text());
        }

        // MODIFIES: this
        // EFFECTS: print list of all food in editorPane
        public String text() {
            StringBuilder text = new StringBuilder();
            for (String f : foodStorage.displayAllFood()) {
                text.append(f).append("\n");
            }
            return text.toString();
        }


    }
}
