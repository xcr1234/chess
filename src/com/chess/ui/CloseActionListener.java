package com.chess.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 关闭
 */
public class CloseActionListener implements ActionListener{

    private Window window;
    private boolean confirm = true;

    public CloseActionListener(Window window) {
        this.window = window;
    }

    public CloseActionListener(Window window, boolean confirm) {
        this.window = window;
        this.confirm = confirm;
    }

    public void setConfirm(boolean confirm) {
        this.confirm = confirm;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(!confirm||MyOptionPane.showConfirmDialog(window,"退出","您真要退出吗？","是","否")==JOptionPane.OK_OPTION){
            window.dispose();
        }
    }
}
