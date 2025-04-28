package model;

import interfaces.CollisionChecker;
import java.awt.Point;
import java.sql.ResultSet;
import java.util.List;

import Ex.SelfEX;

public class SelfCollisionChecker implements CollisionChecker {

    // Kiểm tra va chạm bản  thân 

    public void checkCollision(Snake snake, int gameWidth, int gameHeight) throws SelfEX {
        if(snake.getBody().size()>3)
        {
        	Point head = snake.getHeadPosition();
            List<Point> body = snake.getBody();
            for (int i = 1; i < body.size(); i++) {
                if (head.equals(body.get(i))) {
                    throw new SelfEX("va cham ban than");
                    
                }
            }
        }
       
       
    }
}
