package interfaces;

import model.GameLogic;
import model.Snake;

// Hành động ăn
public interface Eatable {
    void onEat(Snake snake, GameLogic gameLogic);
}
