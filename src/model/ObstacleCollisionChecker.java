package model;

import interfaces.CollisionChecker;
import java.awt.Point;
import java.util.List;

import Ex.ObstacleEX;
import enums.Direction;

public class ObstacleCollisionChecker implements CollisionChecker {

    private final List<Point> obstacles;

    public ObstacleCollisionChecker(List<Point> obstacles) {
        this.obstacles = obstacles;
    }

    
    public void checkCollision(Snake snake, int gameWidth, int gameHeight) throws ObstacleEX {
    	Point head = snake.getHeadPosition();
//        Direction diheadnext = snake.getDirection();
//        //Point headnext;
//        int newX = (int) head.getX();
//        int newY = (int) head.getY();
//        switch (diheadnext) {
//        case UP ->
//            newY -= snake.gettitle();
//        case DOWN ->
//            newY += snake.gettitle();
//        case LEFT ->
//            newX -= snake.gettitle();
//        case RIGHT ->
//            newX += snake.gettitle();
//        }
//        Point headnext = new Point(newX, newY);
        for (Point obstacle : obstacles) {
            if (head.equals(obstacle)) {
                throw new ObstacleEX("Va cham vat can");
            }
        }
        
    }
}
