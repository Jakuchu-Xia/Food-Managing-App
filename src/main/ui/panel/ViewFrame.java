package ui.panel;

import model.FoodStorage;
import ui.tools.*;

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

    public ViewFrame(String name, FoodStorage foodStorage) {
        super(name);
        initializeFields();
        initializeGraphics();
        this.foodStorage = foodStorage;
    }

    private void initializeFields() {
        label = new JLabel("Stored food:");
        editorPane = new JEditorPane();
        scroll = new JScrollPane(editorPane);
    }

    private void initializeGraphics() {
        setLayout(new BorderLayout());
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        add(label, BorderLayout.NORTH);

        add(scroll);
        editorPane.setEditable(false);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        createTools();

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void createTools() {
        JPanel toolArea = new JPanel();
        toolArea.setLayout(new BorderLayout(0,1));
        toolArea.setSize(new Dimension(WIDTH, 100));
        add(toolArea, BorderLayout.SOUTH);

        createViewAllButton(toolArea);
    }

    private void createViewAllButton(JComponent parent) {
        JButton button = new JButton("View All");
        parent.add(button);
        button.addActionListener(new ViewAllToolClickHandler());
    }

    private class ViewAllToolClickHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            editorPane.setText(text());
        }

        public String text() {
            StringBuilder text = new StringBuilder();
            for (String f : foodStorage.displayAllFood()) {
                text.append(f + "\n");
            }
            return text.toString();
        }
    }
}
