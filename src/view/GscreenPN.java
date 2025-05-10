package view;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import model.GameLogic;

public class GscreenPN extends JPanel {
	private GamePanel gamePN;
	private ControlPN conPN;
	public GscreenPN(GamePanel gamePN , ControlPN conPN)
	{
		this.gamePN=gamePN;
		this.conPN=conPN;
		addControl();
	}
	public GscreenPN(GameLogic logic)
	{
		gamePN= new GamePanel(logic);
		conPN = new ControlPN(logic);
		addControl();
	}
	private void addControl() {
		// TODO Auto-generated method stub
		setLayout(new BorderLayout());
		add(gamePN,BorderLayout.CENTER);
		add(conPN,BorderLayout.NORTH);
		
	}
	public GamePanel getGamePN() {
		return gamePN;
	}
	public void setGamePN(GamePanel gamePN) {
		this.gamePN = gamePN;
	}
	public ControlPN getConPN() {
		return conPN;
	}
	public void setConPN(ControlPN conPN) {
		this.conPN = conPN;
	}
	

}
