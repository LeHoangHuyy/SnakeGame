package interfaces;

import model.Snake;

// Kiểm tra tồn tại
public interface CollisionChecker {
    boolean checkCollision(Snake snake, int gameWidth, int gameHeight);
}
