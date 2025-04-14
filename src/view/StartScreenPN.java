package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class StartScreenPN extends JPanel {
	private JButton start;
	public StartScreenPN()
	{
		setLayout(new BorderLayout());
		setBackground(Color.white);
		
		JLabel lbtittle = new JLabel("SNAKE GAME");
		lbtittle.setFont(new Font("Aria", Font.BOLD, 50));
		add(lbtittle,BorderLayout.NORTH);
		
		start =  new JButton("Start");
		start.setFont(new Font("Aria", Font.BOLD, 30));
		//start.addActionListener(startlisten);
		start.setFocusable(false);
		
		JPanel buttonPN = new JPanel();
		buttonPN.setBackground(Color.blue);
		buttonPN.add(start);
		add(buttonPN,BorderLayout.SOUTH);
	}
	public JButton getStartBT() {
		return start;
	}
	public void setStartBT(JButton start) {
		this.start = start;
	}
	
}
