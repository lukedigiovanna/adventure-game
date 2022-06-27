package utils;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

public class Keyboard {
    private static boolean[] keys = new boolean[256];
    private static boolean[] keyUp = new boolean[256];

    public static void init(JPanel panel) {
        panel.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {
                keys[e.getKeyCode()] = true;
                keyUp[e.getKeyCode()] = true;
            }

            @Override
            public void keyReleased(KeyEvent e) {
                keys[e.getKeyCode()] = false;
                keyUp[e.getKeyCode()] = false;
            }   
        });
    }

    public static boolean isKeyDown(int keyCode) {
        return keys[keyCode];
    }

    public static boolean isKeyPressed(int keyCode) {
        boolean ret = keyUp[keyCode];
        keyUp[keyCode] = false;
        return ret;
    }
}
