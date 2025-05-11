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
    private int loai;
    private final int soloai=3;

    // Khởi tạo giá trị ban đầu
    public Food(int gameWidth, int gameHeight, int tileSize) {
        this.gameWidth = gameWidth;
        this.gameHeight = gameHeight;
        this.tileSize = tileSize;
        this.random = new Random();
        //Random rd = new Random();
        createRandomPosition(null, null);
    }

    // Trả về vị trí thức ăn
    public Point getPosition() {
        return position;
    }

    // Khởi tạo thức ăn rơi ra ngẫu nhiên và không nằm trên thân rắn
    public final void createRandomPosition(List<Point> snakeBody, List<Point> obstacles) {
        int foodX, foodY;
        boolean onSnake, onObstacle;

        do {
            foodX = random.nextInt(gameWidth / tileSize) * tileSize;
            foodY = random.nextInt(gameHeight / tileSize) * tileSize;
            position = new Point(foodX, foodY);
            onSnake = false;
            onObstacle = false;

            // Kiểm tra thức ăn có nằm trên thân rắn không
            if (snakeBody != null) {
                for (Point snakePart : snakeBody) {
                    if (position.equals(snakePart)) {
                        onSnake = true;
                        break;
                    }
                }
            }

            // Kiểm tra thức ăn có nằm trên vật cản không
            if (obstacles != null) {
                for (Point obstaclePart : obstacles) {
                    if (position.equals(obstaclePart)) {
                        onObstacle = true;
                        break;
                    }
                }
            }
        } while (onSnake || onObstacle);
        loai=random.nextInt(soloai);
    }

    // Thức ăn đã được rắn ăn
    @Override
    public void onEat(Snake snake, GameLogic gameLogic) {
    	if(loai==0)
        {
    		snake.grow();
            gameLogic.increaseScore();
        }
        //createRandomPosition(snake.getBody(), gameLogic.getObstacles());
    	else if(loai==1)
    	{
    		snake.delete(snake.getleght()/2);
            //gameLogic.increaseScore(-snake.getleght());
    	}
    	else
    	{
    		snake.grow(snake.getleght());
    		gameLogic.increaseScore(gameLogic.getScore());
    	}
    }

	public void setPosition(Point position) {
		this.position = position;
	}

	public int getLoai() {
		return loai;
	}

	public void setLoai(int loai) {
		this.loai = loai;
	}
    
}
