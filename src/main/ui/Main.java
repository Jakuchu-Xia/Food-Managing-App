package ui;

import ui.panel.MainFrame;

import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) {
//        try {
//            new FoodManagerApp();
//        } catch (FileNotFoundException e) {
//            System.out.println("Unable to run application: file not found");
//        }
        new MainFrame("Home Page");
    }
}
