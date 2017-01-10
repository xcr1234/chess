package com.chess.game;

import java.util.List;


public interface IChessboard {
    //取得棋盘最大横坐标
    public int getMaxX();
    //最大纵坐标
    public int getMaxY();
    //取得当前所有空白点，这些点才可以下棋
    public List<Point> getFreePoints();
}