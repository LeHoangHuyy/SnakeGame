package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

import javax.swing.JPanel;

import model.GameLogic;

public class FooterPanel extends JPanel {
	private final GameLogic logic ;
	public FooterPanel(GameLogic logic)
	{
		this.logic=logic;
		setPreferredSize(new Dimension(logic.getGameWidth(),20));
        setBackground(Color.WHITE);
        setFocusable(true);
	}
	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }
	private void draw(Graphics g) {
		// Vẽ điểm số
        g.setColor(Color.RED);
        g.setFont(new Font("Ink Free", Font.BOLD, 30));
        FontMetrics metrics = getFontMetrics(g.getFont());
        g.drawString("Score: " + logic.getScore(),
                (logic.getGameWidth() - metrics.stringWidth("Score: " + logic.getScore())) / 2,
                g.getFont().getSize());
		
	}
}
