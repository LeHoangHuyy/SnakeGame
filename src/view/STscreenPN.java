package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.OverlayLayout;
import javax.swing.border.Border;

import model.GameLogic;

public class STscreenPN extends JPanel {
	private JButton start;
	private GameLogic logic;
	private JButton exit;
	private Image image;
	public STscreenPN(GameLogic logic)
	{
		this.logic=logic;
		setLayout(new OverlayLayout(this));
        setImage("/Image/gamesnake.png");

        // Panel chứa nút, trong suốt
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false); // trong suốt
        Border border = BorderFactory.createLineBorder(Color.BLACK, 2);
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setAlignmentX(CENTER_ALIGNMENT);
        buttonPanel.setAlignmentX(0.5f); // Căn giữa trong overlay
        buttonPanel.setAlignmentY(0.97f);
        //buttonPanel.setBorder(border);
        
        ImageIcon originalIcon = new ImageIcon(getClass().getResource("/Image/start.png"));
        Image originalImage = originalIcon.getImage();
        int newWidth = 120;
        int newHeight = 50;
        Image resizedImage = originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resizedImage);
        start = new JButton(resizedIcon);
        start.setPreferredSize(new Dimension(newWidth,newHeight));
        
        
        ImageIcon exitIcon = new ImageIcon(getClass().getResource("/Image/exit.png"));
        Image exitImage = exitIcon.getImage();
        Image resizedExit = exitImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
        ImageIcon resizedExitIcon = new ImageIcon(resizedExit);
        exit = new JButton(resizedExitIcon);
        exit.setPreferredSize(new Dimension(newWidth, newHeight));
        
        buttonPanel.add(start);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        buttonPanel.add(exit);
        add(buttonPanel);
        
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
