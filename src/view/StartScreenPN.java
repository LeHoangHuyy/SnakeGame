package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionListener;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.OverlayLayout;
import javax.swing.border.LineBorder;

public class StartScreenPN extends JPanel {
	private JButton start;
	private Image image;
	public StartScreenPN()
	{
		setLayout(new OverlayLayout(this));
        setImage("/Image/a.jpg");

        // Panel chứa nút, trong suốt
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false); // trong suốt
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 500)); // đặt nút ở giữa và cách trên 500px
        
        ImageIcon originalIcon = new ImageIcon(getClass().getResource("/Image/start.png"));
        // Lấy đối tượng Image từ icon
        Image originalImage = originalIcon.getImage();

        // Resize ảnh
        int newWidth = 160;
        int newHeight = 50;
        Image resizedImage = originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resizedImage);
        start = new JButton(resizedIcon);

        
        
        buttonPanel.add(start);
        add(buttonPanel);

	}
	public JButton getStartBT() {
		return start;
	}
	public void setStartBT(JButton start) {
		this.start = start;
	}
	public void setImage(String imagePath) {
        try {
            image = ImageIO.read(getClass().getResource(imagePath));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image != null) {
            g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
