package com.chess.ui;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * JTextArea自动滚动到底部
 */
public class ScrollBotListener implements DocumentListener{
    private JTextArea jTextArea;

    public ScrollBotListener(JTextArea jTextArea) {
        this.jTextArea = jTextArea;
    }

    @Override
    public void insertUpdate(DocumentEvent e) {

    }

    @Override
    public void removeUpdate(DocumentEvent e) {

    }

    @Override
    public synchronized void changedUpdate(DocumentEvent e) {
        jTextArea.setCaretPosition(jTextArea.getText().length());
    }
}
