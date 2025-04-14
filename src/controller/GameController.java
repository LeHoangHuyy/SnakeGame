package controller;

import enums.Direction;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.Timer;
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
        view.addKeyListener(this);
        this.timer = new Timer(delay, this);
        view.getStart().getStartBT().addActionListener(e ->
        {
        	logic.resetGame(); // Reset game trước khi bắt đầu
            view.showGame();
            onstart();
        });
        view.getOver().getRestartBT().addActionListener(e ->
        {
        	logic.resetGame(); // Reset game trước khi restart
            view.showGame();
            onstart();
        
        });
    }
    public void onstart()
    {
    	this.timer.start();
    }
    public void stop()
    {
    	this.timer.stop();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (logic.isRunning()) {
            logic.update();
            view.repaint();
            if (!logic.isRunning()) {
                stop();
                view.showOver();
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    // Bắt lấy phím mà mình gõ trên bàn phím
    //private boolean keyProcessed = false;
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
		// TODO Auto-generated method stub
		//keyProcessed = false;
	}



}
