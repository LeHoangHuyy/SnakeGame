package view;

import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.OverlayLayout;

import model.GameLogic;

public class STscreenPN extends JPanel {
	private JButton start;
	private GameLogic logic;
	private JButton remuse;
	private Image image;
	public STscreenPN(GameLogic logic)
	{
		this.logic=logic;
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
	public JButton getStart() {
		return start;
	}
	public void setStart(JButton start) {
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
