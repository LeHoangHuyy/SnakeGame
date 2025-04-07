package model;

import java.awt.Point;

import interfaces.CollisionChecker;

public class WallCollisionChecker implements CollisionChecker {

    // Kiểm tra va chạm tường hay không
    @Override
    public boolean checkCollision(Snake snake, int gameWidth, int gameHeight) {
        Point head = snake.getHeadPosition();
        int headX = head.x;
        int headY = head.y;
        return headX < 0 || headY < 0 || headX >= gameWidth || headY >= gameHeight;
    }
}
