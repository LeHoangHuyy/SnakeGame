package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

import javax.swing.JPanel;

import model.GameLogic;

public class ControlPN extends JPanel {
	private  GameLogic logic;
	private int width;
	private int height;
	public ControlPN(GameLogic logic)
	{
		this.logic=logic;
		this.width=logic.getGameWidth();
		this.height=40;
		addControl();
		addEvent();
		setPreferredSize(new Dimension(width,height));
	}
	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }
	private void draw(Graphics g) {
		// TODO Auto-generated method stub
		if(logic.isRunning())
		{
			g.setColor(Color.BLACK);
            g.setFont(new Font("Ink Free", Font.BOLD, 30));
            FontMetrics metrics = getFontMetrics(g.getFont());
            g.drawString("Score: " + logic.getScore(),
                    (logic.getGameWidth() - metrics.stringWidth("Score: " + logic.getScore())) / 2,
                    g.getFont().getSize());
		}
	}
	private void addEvent() {
		// TODO Auto-generated method stub
		
	}
	private void addControl() {
		// TODO Auto-generated method stub
		setBackground(Color.white);
		
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	
}
