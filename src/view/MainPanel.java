package view;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import model.GameLogic;

public class MainPanel extends JPanel {
	//private final GameLogic logic ;
	private CardLayout card;
	private STscreenPN startsreen;
	private GamePanel game;
	private OVscreenPN oversreen;
	public MainPanel(GameLogic logic)
	{
		this.setFocusable(true);
		card = new CardLayout();
		setLayout(card);
		startsreen = new STscreenPN(logic);
		game = new GamePanel(logic);
		oversreen = new OVscreenPN(logic);
		
		add(startsreen,"startsreen");
		add(game,"game");
		add(oversreen,"oversreen");
		
		showstartsreen();
	}
	
	public STscreenPN getstartsreen() {
		return startsreen;
	}

	public void setstartsreen(STscreenPN startsreen) {
		this.startsreen = startsreen;
	}

	public GamePanel getGame() {
		return game;
	}

	public void setGame(GamePanel game) {
		this.game = game;
	}

	public OVscreenPN getoversreen() {
		return oversreen;
	}

	public void setoversreen(OVscreenPN oversreen) {
		this.oversreen = oversreen;
	}

	public void showstartsreen()
	{
		card.show(this, "startsreen");
	}
	public void showGame()
	{
		card.show(this, "game");
	}
	public void showoversreen()
	{
		card.show(this, "oversreen");
	}
	public CardLayout getCard() {
		return card;
	}
	public void setCard(CardLayout card) {
		this.card = card;
	}
	
	
	

}
