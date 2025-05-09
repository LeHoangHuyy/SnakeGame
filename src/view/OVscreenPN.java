package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import model.GameLogic;

public class OVscreenPN extends JPanel {
	private JButton restart;
	private JLabel score;
	private GameLogic logic;
	//private Icon icon;
	public OVscreenPN(GameLogic logic)
	{
		this.logic=logic;
		//initIcon();
		this.setBackground(Color.black);
		this.setLayout(null);
		int w=100;
		int h=50;
		int x=(logic.getGameWidth()-w)/2;
		int y=logic.getGameHeight()/3;
		
		ImageIcon originalIcon = new ImageIcon(getClass().getResource("/Image/restart.png"));
        // Lấy đối tượng Image từ icon
        Image originalImage = originalIcon.getImage();

        // Resize ảnh
        int newWidth = 160;
        int newHeight = 50;
        Image resizedImage = originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resizedImage);
        restart = new JButton(resizedIcon);
        restart.setBackground(Color.black);
		restart.setBounds(x,y,w,h);
		
//		restart.setContentAreaFilled(false);
//		restart.setBorderPainted(false);
//		restart.setFocusPainted(false);
		
		add(restart);
		}
	
//	private void initIcon() {
//		// TODO Auto-generated method stub
//		icon = new Icon() {
//			int width=160;
//			int height=60;
//			@Override
//			public void paintIcon(Component c, Graphics g, int x, int y) {
//				g.setColor(new Color(70, 130, 180)); // Xanh dương
//		        g.fillRoundRect(x, y, width, height, 20, 20);
//
//		        g.setColor(Color.WHITE);
//		        g.drawRoundRect(x, y, width, height, 20, 20);
//
//		        // Vẽ chữ "Restart"
//		        g.setFont(new Font("Arial", Font.BOLD, 20));
//		        FontMetrics fm = g.getFontMetrics();
//		        String text = "Restart";
//		        int textX = x + (width - fm.stringWidth(text)) / 2;
//		        int textY = y + (height - fm.getHeight()) / 2 + fm.getAscent();
//		        g.drawString(text, textX, textY);
//				
//			}
//			
//			@Override
//			public int getIconWidth() {
//				// TODO Auto-generated method stub
//				return width;
//			}
//			
//			@Override
//			public int getIconHeight() {
//				// TODO Auto-generated method stub
//				return height;
//			}
//		};
//	}

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
	public void drawRestartButton(Graphics g) {
	    int buttonWidth = 200;
	    int buttonHeight = 60;
	    int x = (logic.getGameWidth() - buttonWidth) / 2;
	    int y = logic.getGameHeight() / 2 + 100;

	    // Vẽ nền nút
	    g.setColor(new Color(70, 130, 180)); // Màu xanh dương nhạt
	    g.fillRoundRect(x, y, buttonWidth, buttonHeight, 30, 30);

	    // Viền nút
	    g.setColor(Color.WHITE);
	    g.drawRoundRect(x, y, buttonWidth, buttonHeight, 30, 30);

	    // Chữ "Restart"
	    g.setFont(new Font("Arial", Font.BOLD, 24));
	    FontMetrics fm = g.getFontMetrics();
	    String text = "Restart";
	    int textX = x + (buttonWidth - fm.stringWidth(text)) / 2;
	    int textY = y + ((buttonHeight - fm.getHeight()) / 2) + fm.getAscent();

	    g.setColor(Color.WHITE);
	    g.drawString(text, textX, textY);
	}
	
}
