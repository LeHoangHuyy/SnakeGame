package model;

import enums.Direction;
import interfaces.CollisionChecker;
import interfaces.SelfCollisionChecker;
import interfaces.WallCollisionChecker;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class GameLogic {

    private Snake snake;            
    private final int snakeLength = 6;      // Khởi tạo chiều dài rắn ban đầu
    private Food food;
    private int score;                      // Điểm
    private boolean isRunning;              // Kiểm tra di chuyển 
    private final int gameWidth;            // Chiều ngang màn hình game
    private final int gameHeight;           // Chiều cao màn hình game
    private final int tileSize;             // Kích cỡ ô vuông chứa 1 đốt rắn
    private final List<CollisionChecker> collisionChecker;      // Kiểm tra va chạm gồm bản thân, tường,...

    // Khởi tạo giá trị ban đầu
    public GameLogic(int gameWidth, int gameHeight, int tileSize) {
        this.gameWidth = gameWidth;
        this.gameHeight = gameHeight;
        this.tileSize = tileSize;
        this.collisionChecker = new ArrayList<>();
        this.collisionChecker.add(new WallCollisionChecker());      // Va chạm với tường
        this.collisionChecker.add(new SelfCollisionChecker());      // Va cạm với bản thân
        initGame();
    }

    // Trả về điểm
    public int getScore() {
        return score;
    }

    // Kiểm tra có đang di chuyển không
    public boolean isRunning() {
        return isRunning;
    }

    // Dừng game
    public void stopGame() {
        isRunning = false;
    }

    // Trả về chiều ngang game
    public int getGameWidth() {
        return gameWidth;
    }

    // Trả về chiều cao game
    public int getGameHeight() {
        return gameHeight;
    }

    // Trả về kích cỡ ô vuông chứa 1 đốt rắn
    public int getTileSize() {
        return tileSize;
    }

    // Tăng điểm
    public void increaseScore() {
        score++;
    }

    // Trả về con rắn
    public List<Point> getSnakeBody() {
        return snake.getBody();
    }

    // Trả về vị trí thức ăn
    public Point getFoodPosition() {
        return food.getPosition();
    }

    // Khởi tạo game
    public final void initGame() {
        int startX = gameWidth / 2 / tileSize * tileSize;
        int startY = gameHeight / 2 / tileSize * tileSize;
        snake = new Snake(startX, startY, snakeLength, tileSize);
        food = new Food(gameWidth, gameHeight, tileSize);
        score = 0;
        isRunning = true;
        food.createRandomPosition(snake.getBody());
    }

    // Cập nhật con răn khi di chuyển
    public void update() {
        if (!isRunning) {
            return;
        }

        snake.move();

        if (snake.isEating(food.getPosition())) {
            food.onEat(snake, this);
        }

        if (checkCollision()){
            isRunning = false;
        }
    }

    // Kiểm tra va đập
    public boolean checkCollision(){
        for (CollisionChecker checker : collisionChecker){
            if (checker.checkCollision(snake, gameWidth, gameHeight)){
                return true;
            }
        }
        return false;
    }

    // Thay đổi hướng di chuyển
    public void changeDirection(Direction newDirection) {
        Direction currDirection = snake.getDirection();
        if ((newDirection == Direction.UP && currDirection != Direction.DOWN)
                || (newDirection == Direction.DOWN && currDirection != Direction.UP)
                || (newDirection == Direction.LEFT && currDirection != Direction.RIGHT)
                || (newDirection == Direction.RIGHT && currDirection != Direction.LEFT)) {
            snake.setDirection(newDirection);
        }
    }
}
