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
	private StartScreenPN start;
	private GamePanel game;
	private OverScreenPN over;
	public MainPanel(GameLogic logic)
	{
		this.setFocusable(true);
		card = new CardLayout();
		setLayout(card);
		start = new StartScreenPN();
		game = new GamePanel(logic);
		over = new OverScreenPN(logic.getScore());
		
		add(start,"start");
		add(game,"game");
		add(over,"over");
		
		showStart();
	}
	public void showStart()
	{
		card.show(this, "start");
	}
	public void showGame()
	{
		card.show(this, "game");
	}
	public void showOver()
	{
		card.show(this, "over");
	}
	public CardLayout getCard() {
		return card;
	}
	public void setCard(CardLayout card) {
		this.card = card;
	}
	public StartScreenPN getStart() {
		return start;
	}
	public void setStart(StartScreenPN start) {
		this.start = start;
	}
	public GamePanel getGame() {
		return game;
	}
	public void setGame(GamePanel game) {
		this.game = game;
	}
	public OverScreenPN getOver() {
		return over;
	}
	public void setOver(OverScreenPN over) {
		this.over = over;
	}
	

}
