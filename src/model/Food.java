package model;

import interfaces.Eatable;
import java.awt.Point;
import java.util.List;
import java.util.Random;

public class Food implements Eatable {

    private Point position;                 // Vị trí rơi ra thức ăn
    private final int tileSize;             // Kích cỡ ô vuông chứa 1 đốt rắn
    private final int gameWidth;            // Chiều ngang màn hình game
    private final int gameHeight;           // Chiều cao màn hình game
    private final Random random;            // Random

    // Khởi tạo giá trị ban đầu
    public Food(int gameWidth, int gameHeight, int tileSize) {
        this.gameWidth = gameWidth;
        this.gameHeight = gameHeight;
        this.tileSize = tileSize;
        this.random = new Random();
        createRandomPosition(null);
    }

    // Trả về vị trí thức ăn
    public Point getPosition() {
        return position;
    }  

    // Khởi tạo thức ăn rơi ra ngẫu nhiên và không nằm trên thân rắn
    public final void createRandomPosition(List<Point> snakeBody) {
        int foodX, foodY;
        boolean onSnake;
        do {
            foodX = random.nextInt(gameWidth / tileSize) * tileSize;
            foodY = random.nextInt(gameHeight / tileSize) * tileSize;
            position = new Point(foodX, foodY);
            onSnake = false;

            // Kiểm tra thức ăn có nằm trên thân rắn không
            if (snakeBody != null) {
                for (Point snakePart : snakeBody) {
                    if (position.equals(snakePart)) {
                        onSnake = true;
                        break;
                    }
                }
            }
        } while (onSnake);
    }

    // Thức ăn đã được rắn ăn
    @Override
    public void onEat(Snake snake, GameLogic gameLogic) {
        snake.grow();
        gameLogic.increaseScore();
        createRandomPosition(snake.getBody());
    }
}
