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
	private GscreenPN gamecreen;
	private OVscreenPN oversreen;
	public MainPanel(GameLogic logic)
	{
		this.setFocusable(true);
		card = new CardLayout();
		setLayout(card);
		startsreen = new STscreenPN(logic);
		gamecreen = new GscreenPN(logic);
		oversreen = new OVscreenPN(logic);
		
		add(startsreen,"startsreen");
		add(gamecreen,"gamecreen");
		add(oversreen,"oversreen");
		
		showstartsreen();
	}
	
	public STscreenPN getstartsreen() {
		return startsreen;
	}

	public void setstartsreen(STscreenPN startsreen) {
		this.startsreen = startsreen;
	}

	public GscreenPN getGame() {
		return gamecreen;
	}

	public void setGame(GscreenPN game) {
		this.gamecreen = game;
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
		card.show(this, "gamecreen");
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
