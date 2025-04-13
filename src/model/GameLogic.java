package model;

import enums.Direction;
import interfaces.CollisionChecker;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GameLogic {

    private final int gameWidth;            // Chiều ngang màn hình game
    private final int gameHeight;           // Chiều cao màn hình game
    private final int tileSize;             // Kích cỡ ô vuông chứa 1 đốt rắn
    private int score;                      // Điểm
    private boolean isRunning;              // Kiểm tra di chuyển 
    private Snake snake;            
    private final int snakeLength = 6;      // Khởi tạo chiều dài rắn ban đầu
    private Food normalfood;
    private SpecialFood specialFood;
    private int foodCounter = 0;
    private final int specialFoodInterval = 5;
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

        normalfood = new Food(gameWidth, gameHeight, tileSize);
        specialFood = new SpecialFood(gameWidth, gameHeight, tileSize);
        normalfood.createRandomPosition(snake.getBody(), obstacles);
        specialFood.getPosition().setLocation(-1, -1);          // Đặt thức ăn đặc biệt ở ngoài map

        score = 0;
        isRunning = true;
    }

    // Tạo vật cả từ file
    public void initialObstracles() throws IOException {
        obstacles = new ArrayList<>();
        File file = new File("src\\text\\map.txt");     // Đường dẫn tương đối
        System.out.println(System.getProperty("user.dir"));     // Kiểm mày đang ở vị trí nào trên cấu trúc thư mục
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            int row = 0;
            while ((line = br.readLine()) != null){
                String[] elements = line.split("\\s+");
                for (int i = 0; i < elements.length; i++){
                    if (elements[i].equals("1")){
                        obstacles.add(new Point(row * tileSize, i * tileSize));
                    }
                }
                row++;
            }
        } catch (IOException e){
            throw new IOException("File Not Found", e);
        }
    }

    // Cập nhật con răn khi di chuyển
    public void update() {
        if (!isRunning) {
            return;
        }

        snake.move();

        if (snake.isEating(normalfood.getPosition())) {     // Nếu rắn ăn phải thức ăn thường
            normalfood.onEat(snake, this);          // Rắn ăn thức ăn
            foodCounter++;                      // Tăng biến đếm nếu rắn không ăn thức ăn đặc biệt
            if (foodCounter % specialFoodInterval == 0){        // Đến 1 mức điểm nào đó thì xuất hiện thức ăn đặc biệt
                specialFood.createRandomPosition(snake.getBody(), obstacles);     
            } else {                          // Không đến mức điểm nào đó thì thức ăn xuất hiện ở ngoài map
                specialFood.getPosition().setLocation(-1, -1);
            }
        } else if (snake.isEating(specialFood.getPosition())){      // Nếu ăn ăn thức ăn đặc biệt
            specialFood.onEat(snake, this);     // Rắn ăn thức ăn
            foodCounter = 0;                // Rest biến đếm về 0
            normalfood.createRandomPosition(snake.getBody(), obstacles);  // Xuất hiện thức ăn thường sau khi ăn food đặc biệt
            specialFood.getPosition().setLocation(-1, -1);     // Food đặc biệt xuất hiện ở ngoài map
        }

        if (checkCollision()){          // Check  va chạm
            isRunning = false;
        }
    }

    // Kiểm tra va chạm
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
    public void increaseScore(int pointValue){
        score += pointValue;
    }

    // Trả về vị trí thức ăn
    public Point getFoodPosition() {
        return normalfood.getPosition();
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
