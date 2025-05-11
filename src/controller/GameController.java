package controller;

import enums.Direction;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import javax.swing.Timer;

import Ex.ObstacleEX;
import Ex.SelfEX;
import Ex.WallEX;
import model.GameLogic;
import view.GamePanel;
import view.MainPanel;

public class GameController implements ActionListener, KeyListener {

    private final GameLogic logic;
    private final MainPanel view;
    private final Timer timer;

    public GameController(GameLogic logic, MainPanel view, int delay) {
        this.logic = logic;
        this.view = view;
        this.view.addKeyListener(this);
        this.timer = new Timer(delay, this);
        addEvent();
        
    }

    private void addEvent() {
		// TODO Auto-generated method stub
    	view.getstartsreen().getStart().addActionListener(e ->
        {
            view.showGame();
            start();
        });
        view.getoversreen().getRestart().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					logic.resetGame();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} // Reset game trước khi bắt đầu
	            view.showGame();
	            start();
			}
		});
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
				logic.setRunning(false);        	
			}
            catch(Exception ex)
            {
            	ex.printStackTrace();
            }
            
            view.repaint();
            if (!logic.isRunning()) {
                stop();
                view.showoversreen();
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
            case KeyEvent.VK_C ->
            {
            	if(logic.getFood().getLoai()==1)
            	{
            		logic.getFood().setLoai(2);
            	}
            }
            
          
        }
        
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

	public Timer getTimer() {
		return timer;
	}
    public void start()
    {
    	this.timer.start();
    }
    public void stop()
    {
    	this.timer.stop();
    }

}
