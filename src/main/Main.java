package main;

import javax.swing.*;

import utils.Keyboard;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Hello, World");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.requestFocus();
        Panel p = new Panel();
        Keyboard.init(p);
        frame.setContentPane(p);
        frame.setVisible(true);
    }
}
