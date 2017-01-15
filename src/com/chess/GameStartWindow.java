/*
 * Created by JFormDesigner on Sun Jan 15 14:54:03 CST 2017
 */

package com.chess;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import com.chess.ui.CloseActionListener;
import com.intellij.uiDesigner.core.*;

/**
 * @author abc
 */
public class GameStartWindow extends JFrame {
    public GameStartWindow() {
        initComponents();
        btnClose.addActionListener(new CloseActionListener(this));
        setLocationRelativeTo(null);
    }

    private void btn_gameStart(ActionEvent e) {
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
        dispose();
    }

    private void btnOkActionPerformed(ActionEvent e) {
        // TODO add your code here
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        panel = new JPanel();
        radioLow = new JRadioButton();
        radioHigh = new JRadioButton();
        radioMid = new JRadioButton();
        JLabel label1 = new JLabel();
        radioBlack = new JRadioButton();
        radioWhite = new JRadioButton();
        btnOk = new JButton();
        btnClose = new JButton();
        JLabel label2 = new JLabel();

        //======== this ========
        setTitle("\u4e94\u5b50\u68cb\u6e38\u620f");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //======== panel ========
        {
            panel.setLayout(new GridLayoutManager(5, 3, new Insets(0, 0, 0, 0), -1, -1));

            //---- radioLow ----
            radioLow.setText("\u4f4e");
            panel.add(radioLow, new GridConstraints(0, 1, 1, 2,
                GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
                GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                GridConstraints.SIZEPOLICY_FIXED,
                null, null, null));

            //---- radioHigh ----
            radioHigh.setText("\u9ad8");
            panel.add(radioHigh, new GridConstraints(2, 1, 1, 2,
                GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
                GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                GridConstraints.SIZEPOLICY_FIXED,
                null, null, null));

            //---- radioMid ----
            radioMid.setSelected(true);
            radioMid.setText("\u4e2d");
            panel.add(radioMid, new GridConstraints(1, 1, 1, 2,
                GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
                GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                GridConstraints.SIZEPOLICY_FIXED,
                null, null, null));

            //---- label1 ----
            label1.setText("\u5148\u624b");
            panel.add(label1, new GridConstraints(3, 0, 1, 1,
                GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
                GridConstraints.SIZEPOLICY_FIXED,
                GridConstraints.SIZEPOLICY_FIXED,
                null, null, null));

            //---- radioBlack ----
            radioBlack.setSelected(true);
            radioBlack.setText("\u9ed1\u68cb");
            panel.add(radioBlack, new GridConstraints(3, 1, 1, 1,
                GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
                GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                GridConstraints.SIZEPOLICY_FIXED,
                null, null, null));

            //---- radioWhite ----
            radioWhite.setText("\u767d\u68cb");
            panel.add(radioWhite, new GridConstraints(3, 2, 1, 1,
                GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
                GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                GridConstraints.SIZEPOLICY_FIXED,
                null, null, null));

            //---- btnOk ----
            btnOk.setText("\u5f00\u59cb");
            btnOk.addActionListener(e -> {
			btn_gameStart(e);
			btnOkActionPerformed(e);
			btn_gameStart(e);
		});
            panel.add(btnOk, new GridConstraints(4, 1, 1, 1,
                GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
                GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                GridConstraints.SIZEPOLICY_FIXED,
                null, null, null));

            //---- btnClose ----
            btnClose.setText("\u5173\u95ed");
            panel.add(btnClose, new GridConstraints(4, 2, 1, 1,
                GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
                GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                GridConstraints.SIZEPOLICY_FIXED,
                null, null, null));

            //---- label2 ----
            label2.setText("\u96be\u5ea6");
            panel.add(label2, new GridConstraints(0, 0, 1, 1,
                GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
                GridConstraints.SIZEPOLICY_FIXED,
                GridConstraints.SIZEPOLICY_FIXED,
                null, null, null));
        }
        contentPane.add(panel);
        panel.setBounds(10, 0, 203, 213);

        contentPane.setPreferredSize(new Dimension(220, 220));
        pack();
        setLocationRelativeTo(getOwner());

        //---- buttonGroup1 ----
        ButtonGroup buttonGroup1 = new ButtonGroup();
        buttonGroup1.add(radioLow);
        buttonGroup1.add(radioHigh);
        buttonGroup1.add(radioMid);

        //---- buttonGroup2 ----
        ButtonGroup buttonGroup2 = new ButtonGroup();
        buttonGroup2.add(radioBlack);
        buttonGroup2.add(radioWhite);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JPanel panel;
    private JRadioButton radioLow;
    private JRadioButton radioHigh;
    private JRadioButton radioMid;
    private JRadioButton radioBlack;
    private JRadioButton radioWhite;
    private JButton btnOk;
    private JButton btnClose;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
