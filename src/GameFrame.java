import java.awt.Color;

import javax.swing.JFrame;

public class GameFrame extends JFrame{

	GamePanel panel;
	
	GameFrame(){
		//this refers to the GameFrame method that is inheriting its additional methods from the JFrame class part of Swing
		panel = new GamePanel();
		this.add(panel);
		this.setTitle("Pong Game");
		this.setResizable(false);
		this.setBackground(Color.black);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
		this.setLocationRelativeTo(null);
	}
	
}
