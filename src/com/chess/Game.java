package com.chess;


import javax.swing.*;
import java.awt.*;

public class Game {
    public static void main(String[] args) {

        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        }catch (Exception e){}

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new GameStartWindow();
                frame.setVisible(true);
            }
        });
    }
}
