package test;

import controller.GameController;
import model.GameLogic;
import view.GamePanel;
import view.MainPanel;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SnakeGame extends JFrame {
    @SuppressWarnings("CallToPrintStackTrace")
    public SnakeGame(List<String> linkmap) {
        int tileSize = 20;
        int gameWidth = 740;
        int gameHeight = 740;
        try {
            GameLogic logic = new GameLogic(gameWidth, gameHeight, tileSize,linkmap);
            MainPanel view = new MainPanel(logic);
            view.setPreferredSize(new Dimension(gameWidth, gameHeight));
            GameController controller = new GameController(logic, view, 150);
            add(view);
           
            setTitle("Snake Game");
            //setPreferredSize(new Dimension(gameWidth, gameHeight));
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setResizable(false);
            pack(); 
            setLocationRelativeTo(null);
            setVisible(true);      
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException, SQLException {
    	List<String> map = new ArrayList<String>();
    	map.add("src\\text\\map.txt");
//        new SnakeGame(map);
    	GameLogic logic = new GameLogic(740, 740, 20,map);
    	logic.saveData();
    }
}