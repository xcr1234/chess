package com.chess.util;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class ResourceUtil {



    public static InputStream getStream(String name){
        return ResourceUtil.class.getResourceAsStream("/"+name);
    }



    public static Image getImage(String name){

        try (InputStream inputStream = ResourceUtil.class.getResourceAsStream("/"+name)){
            return ImageIO.read(inputStream);
        }catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }

    public static ImageIcon getIcon(String name){
        return new ImageIcon(getImage(name));
    }
}
