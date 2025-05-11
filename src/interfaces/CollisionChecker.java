package interfaces;

import Ex.ObstacleEX;
import Ex.SelfEX;
import Ex.WallEX;
import model.Snake;

// Kiểm tra tồn tại
public interface CollisionChecker {
    void checkCollision(Snake snake, int gameWidth, int gameHeight) throws SelfEX, ObstacleEX, WallEX;
}
