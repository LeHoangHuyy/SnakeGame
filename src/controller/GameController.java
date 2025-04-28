package controller;

import enums.Direction;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.Timer;

import Ex.ObstacleEX;
import Ex.SelfEX;
import Ex.WallEX;
import model.GameLogic;
import view.GamePanel;

public class GameController implements ActionListener, KeyListener {

    private final GameLogic logic;
    private final GamePanel view;
    private final Timer timer;

    public GameController(GameLogic logic, GamePanel view, int delay) {
        this.logic = logic;
        this.view = view;
        this.view.addKeyListener(this);
        this.timer = new Timer(delay, this);
        this.timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (logic.isRunning()) {
            try {
				logic.update();
			} catch (SelfEX e1) {
				
				logic.setRunning(false);
				e1.printStackTrace();
			} catch (WallEX e1) {
				
				logic.setRunning(false);
				e1.printStackTrace();
			} catch (ObstacleEX e1) {
				
				logic.getSnake().delete();
				
	        	//score--;
	        	if(logic.getScore() < 0 || logic.getSnake().getBody().isEmpty())
	        	{
	        		logic.setScore(0);
	        		logic.setRunning(false);
	        		
	        	}
	        	if(logic.getRunning())
	        	logic.quaydau();
	        	
			}
            view.repaint();
            if (!logic.isRunning()) {
                timer.stop();
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    // Bắt lấy phím mà mình gõ trên bàn phím
    @Override
    public void keyPressed(KeyEvent e) {
        if (!logic.isRunning()) {
            return;
        }

        int keyCode = e.getKeyCode();

        switch (keyCode) {
            case KeyEvent.VK_W ->
                logic.changeDirection(Direction.UP);
            case KeyEvent.VK_S ->
                logic.changeDirection(Direction.DOWN);
            case KeyEvent.VK_A ->
                logic.changeDirection(Direction.LEFT);
            case KeyEvent.VK_D ->
                logic.changeDirection(Direction.RIGHT);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

}
