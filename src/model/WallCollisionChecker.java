package model;

import java.awt.Point;

import Ex.WallEX;
import interfaces.CollisionChecker;

public class WallCollisionChecker implements CollisionChecker {

    public void checkCollision(Snake snake, int gameWidth, int gameHeight) throws WallEX {
        Point head = snake.getHeadPosition();
        int headX = head.x;
        int headY = head.y;
        if(headX < 0 || headY < 0 || headX >= gameWidth || headY >= gameHeight)
        {
        	throw new WallEX("vuot khoi map");
        }
    }
}
