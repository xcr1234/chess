package com.chess.util;

import com.chess.GameWindow;
import com.chess.game.BaseComputerAi;
import com.chess.game.ChessBoard;
import com.chess.game.HumanPlayer;
import com.chess.game.IPlayer;
import com.chess.game.Point;

import java.awt.image.BufferedImage;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.List;

/**
 * 保存棋局
 */
public class ChessSave implements Externalizable{
    private static final long serialVersionUID = -6072628761919175938L;
    private IPlayer humanPlayer;
    private IPlayer baseComputerAi;
    private boolean black;
    private boolean iswin;
    private ChessBoard chessBoard;
    private BufferedImage image;

    public ChessSave() {
    }

    public ChessSave(IPlayer humanPlayer, IPlayer baseComputerAi, boolean black, boolean iswin) {
        this.humanPlayer = humanPlayer;
        this.baseComputerAi = baseComputerAi;
        this.black = black;
        this.iswin = iswin;
    }

    public boolean isIswin() {
        return iswin;
    }

    public void setIswin(boolean iswin) {
        this.iswin = iswin;
    }

    public IPlayer getHumanPlayer() {
        return humanPlayer;
    }

    public void setHumanPlayer(IPlayer humanPlayer) {
        this.humanPlayer = humanPlayer;
    }

    public IPlayer getBaseComputerAi() {
        return baseComputerAi;
    }

    public void setBaseComputerAi(IPlayer baseComputerAi) {
        this.baseComputerAi = baseComputerAi;
    }

    public ChessBoard getChessBoard() {
        return chessBoard;
    }

    public void setChessBoard(ChessBoard chessBoard) {
        this.chessBoard = chessBoard;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public boolean isBlack() {
        return black;
    }

    public void setBlack(boolean black) {
        this.black = black;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeBoolean(black);
        out.writeObject(humanPlayer.getMyPoints());
        out.writeObject(baseComputerAi.getMyPoints());
        out.writeBoolean(iswin);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        black = in.readBoolean();
        List<Point> human  = (List<Point>) in.readObject();
        List<Point> ai = (List<Point>) in.readObject();
        iswin = in.readBoolean();
        chessBoard = new ChessBoard();
        image = (BufferedImage) ResourceUtil.getImage("chess.jpg");
        for(Point point:human){
            chessBoard.getFreePoints().remove(point);
            GameWindow.drawImage(image,point.getX(),point.getY(),black);
        }
        for(Point point:ai){
            chessBoard.getFreePoints().remove(point);
            GameWindow.drawImage(image,point.getX(),point.getY(),!black);
        }
        this.humanPlayer = new HumanPlayer();
        humanPlayer.setChessboard(chessBoard);
        humanPlayer.getMyPoints().addAll(human);
        this.baseComputerAi = new BaseComputerAi();
        baseComputerAi.setChessboard(chessBoard);
        baseComputerAi.getMyPoints().addAll(ai);
    }
}
