package ui.tools;


import ui.panel.ViewFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FilterTool {
    protected JButton button;
    private ViewFrame panel;
    private SpringLayout layout;

    public FilterTool(ViewFrame panel, JComponent parent) {
        this.panel = panel;
        createLayout(parent);
//        addListener();
    }

    private void createLayout(JComponent parent) {
        button = new JButton("Filter");

        addToParent(parent, layout);
    }

    public void addToParent(JComponent parent, SpringLayout layout) {
    }

//    public void addListener() {
//        button.addActionListener(new FilterDaysLeftToolClickHandler());
//    }
//
//    private class FilterDaysLeftToolClickHandler implements ActionListener {
//
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            panel.getEditorPane().setText(text());
//        }
//
//        public String text() {
//            StringBuilder text = new StringBuilder();
//            for (int i = 0; i < 5; i++) {
//                text.append("new text\n");
//            }
//            String string = text.toString();
//            return string;
//        }
//    }
}
