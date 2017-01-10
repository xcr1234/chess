package com.chess.ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 最小化
 */
public class MinActionListener implements ActionListener{

    private JFrame window;

    public MinActionListener(JFrame window) {
        this.window = window;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.window.setExtendedState(JFrame.ICONIFIED);
    }
}
