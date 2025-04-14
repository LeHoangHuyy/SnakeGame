package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.GameLogic;
import view.MainPanel;

public class MainController  implements ActionListener{
	private final MainPanel mainPanel;
    private final GameLogic logic;
    private final GameController gameController;

    public MainController(MainPanel mainPanel, GameLogic logic,int delay) {
        this.mainPanel = mainPanel;
        this.logic = logic;
        this.gameController = new GameController(logic, mainPanel, delay);

        // Thêm ActionListener cho các nút
        mainPanel.getStart().getStartBT().addActionListener(this);
        mainPanel.getOver().getRestartBT().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == mainPanel.getStart().getStartBT()) {
            logic.resetGame(); // Reset game trước khi bắt đầu
            mainPanel.showGame();
            gameController.onstart();
        } else if (e.getSource() == mainPanel.getOver().getRestartBT()) {
            logic.resetGame(); // Reset game trước khi restart
            mainPanel.showGame();
            gameController.onstart();
        }
    }

	
}
