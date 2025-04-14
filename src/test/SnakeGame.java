package test;

import controller.GameController;
import controller.MainController;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.HeadlessException;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import model.GameLogic;
import view.MainPanel;



public class SnakeGame extends JFrame {
	private int tileSize;
    private int gameWidth;
    private int gameHeight;
    private GameLogic logic;

    public SnakeGame(int tileSize, int gameWidth, int gameHeight)  {
		super();
		this.tileSize = tileSize;
		this.gameWidth = gameWidth;
		this.gameHeight = gameHeight;
		addControl();
	}

	private void addControl() {
		// TODO Auto-generated method stub
		logic = new GameLogic(gameWidth, gameHeight, tileSize);
		//logic = new GameLogic(gameWidth, gameHeight, tileSize);
        MainPanel main = new MainPanel(logic);
        MainController con= new MainController(main, logic, 150);
        add(main);
	}

	public void showWindow() {
        
        this.setPreferredSize(new Dimension(600,650));
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        setBackground(Color.black);
	}

	public static void main(String[] args){
        SnakeGame a= new SnakeGame(20,600,600);
        a.showWindow();
    }
}