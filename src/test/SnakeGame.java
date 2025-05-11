package test;

import controller.GameController;
import model.GameLogic;
import view.ControlPN;
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
    public SnakeGame(String listmap) throws IOException {
        addControl(listmap);
    }

    private void addControl(String listmap) throws IOException {
		// TODO Auto-generated method stub
    	int tileSize = 20;
        int gameWidth = 740;
        int gameHeight = 740;
    	try {
            GameLogic logic = new GameLogic(gameWidth, gameHeight, tileSize,listmap);
            //
            MainPanel view = new MainPanel(logic);
            view.setPreferredSize(new Dimension(gameWidth, (gameHeight+view.getGame().getConPN().getHeight()
            		)));
            //
            GameController controller = new GameController(logic, view, 150);
            ControlPN con = new ControlPN(logic);
            con.setPreferredSize(new Dimension(gameWidth, gameHeight/10));
            //
            //setLayout(new BorderLayout());
            add(view);
            //add(con,BorderLayout.NORTH);
            setTitle("Snake Game");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setResizable(false);
            pack(); 
            setLocationRelativeTo(null);
            setVisible(true);      
        }
    	finally {
			
		}
		
	}

	public static void main(String[] args) throws IOException, SQLException {
    	List<String> map = new ArrayList<String>();
    	map.add("src\\text\\map.txt");
    	map.add("src\\text\\map2.txt");
    	map.add("src\\text\\map3.txt");
        new SnakeGame(map.get(0));
    	// GameLogic logic = new GameLogic(740, 740, 20,map);
    	// logic.saveData();
    }
}