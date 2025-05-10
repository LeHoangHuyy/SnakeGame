package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import model.GameLogic;

public class OVscreenPN extends JPanel {
	private JButton restart;
	private JButton exit;
	private JLabel score;
	private GameLogic logic;
	//private Icon icon;
	public OVscreenPN(GameLogic logic)
	{
		this.logic=logic;
		//initIcon();
		this.setBackground(Color.black);
		this.setLayout(null);
		int w=120;
		int h=50;
		int x=(logic.getGameWidth()-w)/2;
		int y=logic.getGameHeight()-200;
		
		ImageIcon originalIcon = new ImageIcon(getClass().getResource("/Image/restart.png"));
        Image originalImage = originalIcon.getImage();
        int newWidth = 120;
        int newHeight = 50;
        Image resizedImage = originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resizedImage);
        restart = new JButton(resizedIcon);
        restart.setPreferredSize(new Dimension(newWidth, newHeight));
        restart.setBackground(Color.black);
		restart.setBounds(x,y,w,h);
		
		ImageIcon exitIcon = new ImageIcon(getClass().getResource("/Image/exit.png"));
        Image exitImage = exitIcon.getImage();
        Image resizedExit = exitImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
        ImageIcon resizedExitIcon = new ImageIcon(resizedExit);
        exit = new JButton(resizedExitIcon);
        exit.setPreferredSize(new Dimension(newWidth, newHeight));
        exit.setBounds(x,y+60,w,h);
		
		add(restart);
		add(exit);
		addEvent();
	}

	private void addEvent() {
		// TODO Auto-generated method stub
		exit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});
		
	}

	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }
	private void draw(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(Color.RED);
        g.setFont(new Font("Ink Free", Font.BOLD, 75));
        FontMetrics metrics1 = getFontMetrics(g.getFont());
        g.drawString("Game Over",
                (logic.getGameWidth() - metrics1.stringWidth("Game Over")) / 2,
                logic.getGameHeight() / 2);

        // Thông báo điểm số
        g.setColor(Color.WHITE);
        g.setFont(new Font("Ink Free", Font.BOLD, 40));
        FontMetrics metrics2 = getFontMetrics(g.getFont());
        g.drawString("Score: " + logic.getScore(),
                (logic.getGameWidth() - metrics2.stringWidth("Score: " + logic.getScore())) / 2,
                g.getFont().getSize() * 2);
	}
	public JButton getRestart() {
		return restart;
	}
	public void setRestart(JButton restart) {
		this.restart = restart;
	}
	public JLabel getScore() {
		return score;
	}
	public void setScore(JLabel score) {
		this.score = score;
	}
//	public void drawRestartButton(Graphics g) {
//	    int buttonWidth = 200;
//	    int buttonHeight = 60;
//	    int x = (logic.getGameWidth() - buttonWidth) / 2;
//	    int y = logic.getGameHeight() / 2 + 100;
//
//	    // Vẽ nền nút
//	    g.setColor(new Color(70, 130, 180)); // Màu xanh dương nhạt
//	    g.fillRoundRect(x, y, buttonWidth, buttonHeight, 30, 30);
//
//	    // Viền nút
//	    g.setColor(Color.WHITE);
//	    g.drawRoundRect(x, y, buttonWidth, buttonHeight, 30, 30);
//
//	    // Chữ "Restart"
//	    g.setFont(new Font("Arial", Font.BOLD, 24));
//	    FontMetrics fm = g.getFontMetrics();
//	    String text = "Restart";
//	    int textX = x + (buttonWidth - fm.stringWidth(text)) / 2;
//	    int textY = y + ((buttonHeight - fm.getHeight()) / 2) + fm.getAscent();
//
//	    g.setColor(Color.WHITE);
//	    g.drawString(text, textX, textY);
//	}
	
}
