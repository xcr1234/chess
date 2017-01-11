package com.chess.util;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

/**
 * @author Brandon B. Lin
 * 2013-1-25
 */
public class MP3Player {

    private byte mp3Bytes[];


    public MP3Player(InputStream in) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            int l = 0;
            byte[] bytes = new byte[1024];
            while ((l=in.read(bytes))!=-1){
                byteArrayOutputStream.write(bytes,0,l);
            }
            mp3Bytes = byteArrayOutputStream.toByteArray();
        }catch (Exception e){
            e.printStackTrace();
        }
}

    public void playSync(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                play();
            }
        }).start();
    }


    public void play() {
        try {
            Player player = new Player(new ByteArrayInputStream(mp3Bytes));
            player.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




}