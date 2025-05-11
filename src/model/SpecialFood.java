package model;

public class SpecialFood extends Food {

    private final int pointValue = 5;

    public SpecialFood(int gameWidth, int gameHeight, int tileSize) {
        super(gameWidth, gameHeight, tileSize);
    }

    @Override
    public void onEat(Snake snake, GameLogic gameLogic) {
        snake.grow(pointValue);
        //snake.getBody().set(0, getPosition());
        gameLogic.increaseScore(pointValue);
       
    }
}
