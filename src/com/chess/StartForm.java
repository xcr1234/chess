package com.chess;

import com.chess.ui.CloseActionListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class StartForm {
    private JRadioButton radioLow;
    private JRadioButton radioHigh;
    private JRadioButton radioMid;
    private JRadioButton radioBlack;
    private JRadioButton radioWhite;
    private JButton btnOk;
    private JButton btnClose;
    private JPanel panel;




    public JFrame getFrame(){
        final JFrame frame = new JFrame();
        frame.setResizable(false);
        frame.setTitle("五子棋游戏");
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setSize(205,212);
        frame.setLocationRelativeTo(null);

        btnClose.addActionListener(new CloseActionListener(frame));
        btnOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int diff = 0;
                if(radioMid.isSelected()){
                    diff=1;
                }else if(radioLow.isSelected()){
                    diff = 0;
                }else if(radioHigh.isSelected()){
                    diff=2;
                }
                GameWindow gameWindow = new GameWindow(radioBlack.isSelected(),diff);
                gameWindow.setVisible(true);
                frame.dispose();

            }
        });

        return frame;
    }






}
