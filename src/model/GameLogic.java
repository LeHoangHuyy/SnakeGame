package model;

import enums.Direction;
import interfaces.CollisionChecker;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import model.collision.ObstacleCollisionChecker;
import model.collision.SelfCollisionChecker;
import model.collision.WallCollisionChecker;

public class GameLogic {

    private final int gameWidth;            // Chiều ngang màn hình game
    private final int gameHeight;           // Chiều cao màn hình game
    private final int tileSize;             // Kích cỡ ô vuông chứa 1 đốt rắn
    private int score;                      // Điểm
    private boolean isRunning;              // Kiểm tra di chuyển 
    private Snake snake;
    private final int snakeLength = 6;      // Khởi tạo chiều dài rắn ban đầu
    private Food normalFood;
    private SpecialFood specialFood;
    private int foodCounter;
    private final int specialFoodInterval = 3;
    private List<CollisionChecker> collisionChecker;      // Kiểm tra va chạm gồm bản thân, tường,...
    private List<Point> obstacles;

    // Khởi tạo giá trị ban đầu
    public GameLogic(int gameWidth, int gameHeight, int tileSize) throws IOException {
        this.gameWidth = gameWidth;
        this.gameHeight = gameHeight;
        this.tileSize = tileSize;
        initGame();
    }

    // Khởi tạo game
    public final void initGame() throws IOException {
        collisionChecker = new ArrayList<>();
        initialObstracles();
        collisionChecker.add(new WallCollisionChecker());      // Va chạm với tường
        collisionChecker.add(new SelfCollisionChecker());      // Va cạm với bản thân
        collisionChecker.add(new ObstacleCollisionChecker(obstacles));      // Va chạm vật cản

        int startX = gameWidth / 2 / tileSize * tileSize;
        int startY = gameHeight / 2 / tileSize * tileSize;
        snake = new Snake(startX, startY, snakeLength, tileSize);       // Khởi tạo rắn ở giữa màn hình

        normalFood = new Food(gameWidth, gameHeight, tileSize);
        normalFood.createRandomPosition(snake.getBody(), obstacles);

        specialFood = new SpecialFood(gameWidth, gameHeight, tileSize);

        score = 0;
        isRunning = true;
    }

    // Tạo vật cả từ file
    public void initialObstracles() throws IOException {
        obstacles = new ArrayList<>();
        File file = new File("src\\resources\\map.txt");     // Đường dẫn tương đối
        //System.out.println(System.getProperty("user.dir"));     // Kiểm tra mày đang ở vị trí nào trên cấu trúc thư mục

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            int row = 0;

            while ((line = br.readLine()) != null) {
                String[] elements = line.split("\\s+");

                for (int col = 0; col < elements.length; col++) {
                    if (elements[col].equals("1")) {
                        obstacles.add(new Point(col * tileSize, row * tileSize));
                    }
                }
                row++;
            }
        } catch (IOException e) {
            throw new FileNotFoundException();
        }
    }

    // Cập nhật con răn khi di chuyển
    public void update() {
        if (!isRunning) {
            return;
        }

        snake.move();

        if (snake.isEating(normalFood.getPosition())) {     // Nếu rắn ăn phải thức ăn thường
            normalFood.onEat(snake, this);          // Rắn ăn thức ăn
            foodCounter++;                      // Tăng biến đếm nếu rắn không ăn thức ăn đặc biệt

            if (foodCounter % specialFoodInterval == 0) {        // Đến 1 mức điểm nào đó thì xuất hiện thức ăn đặc biệt
                specialFood.createRandomPosition(snake.getBody(), obstacles);
            } else {
                normalFood.createRandomPosition(snake.getBody(), obstacles);
            }
        } else if (snake.isEating(specialFood.getPosition())) {      // Nếu ăn ăn thức ăn đặc biệt
            specialFood.onEat(snake, this);     // Rắn ăn thức ăn
            foodCounter = 0;                // Rest biến đếm về 0
            normalFood.createRandomPosition(snake.getBody(), obstacles);  // Xuất hiện thức ăn thường sau khi ăn food đặc biệt
        }
        // Check  va chạm
        if (checkCollision()) {          
            isRunning = false;
        }
    }

    // Kiểm tra va chạm
    public boolean checkCollision() {
        for (CollisionChecker checker : collisionChecker) {
            if (checker.checkCollision(snake, gameWidth, gameHeight)) {
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

    // Trả về con rắn
    public List<Point> getSnakeBody() {
        return snake.getBody();
    }

    // Tăng 1 điểm
    public void increaseScore() {
        score++;
    }

    // Tăng pointValue điểm
    public void increaseScore(int pointValue) {
        score += pointValue;
    }

    // Trả về vị trí thức ăn
    public Point getFoodPosition(){
        if (foodCounter % specialFoodInterval == 0 && foodCounter != 0){
            return specialFood.getPosition();
        }
        return normalFood.getPosition();
    }
    
    // Trả về số điểm đã đếm
    public int getFoodCounter() {
        return foodCounter;
    }

    // Trả về điểm đặc biệt để xuất hiện thức ăn đặc biệt
    public int getSpecialFoodInterval() {
        return specialFoodInterval;
    }

    // Trả về list vị trí vật cản
    public List<Point> getObstacles() {
        return obstacles;
    }
}
