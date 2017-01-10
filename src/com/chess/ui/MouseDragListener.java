package com.chess.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * 支持鼠标任意拖动
 */
public class MouseDragListener  extends MouseAdapter{

    private JPanel panel;
    private JFrame window;

    public MouseDragListener(JPanel panel, JFrame window) {
        this.panel = panel;
        this.window = window;
    }

    private Point draggingAnchor = null;
    @Override
    public void mouseMoved(MouseEvent e) {
        draggingAnchor = new Point(e.getX() + panel.getX(), e.getY() + panel.getY());
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        window.setLocation(e.getLocationOnScreen().x - draggingAnchor.x, e.getLocationOnScreen().y - draggingAnchor.y);
    }
}
