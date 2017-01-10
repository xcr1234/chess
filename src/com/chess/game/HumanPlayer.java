package com.chess.game;

import java.util.List;

public class HumanPlayer extends BasePlayer {


    @Override
    public void run(List<Point> enemyPoints,Point p) {
        getMyPoints().add(p);
        allFreePoints.remove(p);
    }
}