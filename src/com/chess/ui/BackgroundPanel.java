package com.chess.ui;


import com.chess.util.ResourceUtil;

import javax.swing.*;
import java.awt.*;

public class BackgroundPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    private String knight ; //要加载的图片资源的文件名

    private ImageIcon icon;

    public String getKnight() {
        return knight;
    }

    public void setKnight(String knight) {
        this.knight = knight;
    }

    public BackgroundPanel(String name){
        this.knight = name;
        Image image = ResourceUtil.getImage(name);
        if(image!=null){
            this.icon = new ImageIcon(image);
        }

    }

    //重写绘制组件方法
    @Override
    public void paintComponent(Graphics g)
    {
        if(this.icon!=null){
            int x = 0,y = 0;
            //绘制窗口
            g.drawImage(icon.getImage(),x,y,icon.getIconWidth(),icon.getIconHeight(),this);
        }

    }
}