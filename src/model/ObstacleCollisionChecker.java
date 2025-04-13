package model;

import interfaces.CollisionChecker;
import java.awt.Point;
import java.util.List;

public class ObstacleCollisionChecker implements CollisionChecker {

    private final List<Point> obstacles;

    public ObstacleCollisionChecker(List<Point> obstacles) {
        this.obstacles = obstacles;
    }

    @Override
    public boolean checkCollision(Snake snake, int gameWidth, int gameHeight) {
        Point head = snake.getHeadPosition();
        for (Point obstacle : obstacles) {
            if (head.equals(obstacle)) {
                return true;
            }
        }
        return false;
    }
}
