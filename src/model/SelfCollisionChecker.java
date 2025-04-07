package model;

import java.awt.Point;
import java.util.List;

import interfaces.CollisionChecker;

public class SelfCollisionChecker implements CollisionChecker {

    // Kiểm tra va chạm bản  thân 
    @Override
    public boolean checkCollision(Snake snake, int gameWidth, int gameHeight) {
        Point head = snake.getHeadPosition();
        List<Point> body = snake.getBody();
        for (int i = 1; i < body.size(); i++){
            if (head.equals(body.get(i))){
                return true;
            }
        }
        return false;
    }
}
