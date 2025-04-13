package model;

public class SpecialFood extends Food {

    private final int pointValue = 5;

    public SpecialFood(int gameWidth, int gameHeight, int tileSize) {
        super(gameWidth, gameHeight, tileSize);
    }

    @Override
    public void onEat(Snake snake, GameLogic gameLogic) {
        snake.grow();
        gameLogic.increaseScore(pointValue);
        createRandomPosition(snake.getBody(), gameLogic.getObstacles());
    }

}
