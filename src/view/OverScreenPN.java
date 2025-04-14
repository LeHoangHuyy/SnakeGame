package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class OverScreenPN extends JPanel {
	private JButton restart;
	private JLabel score;
	public OverScreenPN( int score)
	{
		this.setLayout(new BorderLayout());
		this.setBackground(Color.black);
		
		JLabel overlb = new JLabel("OVER");
		overlb.setFont(new Font("aria", Font.BOLD, 50));
		add(overlb,BorderLayout.CENTER);
		
		JLabel scorelb = new JLabel("Score : "+score);
		scorelb.setFont(new Font("aria", Font.BOLD, 10));
		add(scorelb,BorderLayout.SOUTH);
		
		restart = new JButton("Restart");
		restart.setFont(new Font("aria", Font.BOLD, 12));
		
		JPanel buttonPN = new JPanel();
		buttonPN.setBackground(Color.blue);
		buttonPN.add(restart);
		add(buttonPN,BorderLayout.NORTH);
	}
	public JButton getRestartBT() {
		return restart;
	}
	public void setRestartBT(JButton restart) {
		this.restart = restart;
	}
	public JLabel getScore() {
		return score;
	}
	public void setScore(JLabel score) {
		this.score = score;
	}
	
	
}
