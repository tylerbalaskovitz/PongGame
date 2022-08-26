import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

public class Paddle extends Rectangle{

	
	int id;
	int yVelocity;
	int speed = 10;
	
	Paddle(int x, int y, int PADDLE_WIDTH, int PADDLE_HEIGHT, int id){
		super(x, y, PADDLE_WIDTH, PADDLE_HEIGHT);
		this.id = id;
		
		
		
	}
	
	public void keyPressed(KeyEvent e) {
		//the id is the ID of the player, so player 1 is ID 1 runing the switch, and 2 is player 2 running their switch for player control. 
		switch(id) {
		case 1:
			//this tells whether or not the KeyEvent W is pressed then the direction y is changed by the speed integer
			if(e.getKeyCode()==KeyEvent.VK_W) {
				setYDirection(-speed);
				move();
			}
			//this tells whether or not the KeyEvent S is pressed then the direction of y is changed
			if(e.getKeyCode()==KeyEvent.VK_S) {
				setYDirection(speed);
				move();
			}
			break;
		case 2:
			//this tells whether or not the KeyEvent W is pressed then the direction y is changed by the speed integer
			if(e.getKeyCode()==KeyEvent.VK_UP) {
				setYDirection(-speed);
				move();
			}
			//this tells whether or not the KeyEvent S is pressed then the direction of y is changed
			if(e.getKeyCode()==KeyEvent.VK_DOWN) {
				setYDirection(speed);
				move();
			}
			break;
			
		}
		
	}
	public void keyReleased(KeyEvent e) {
	switch(id) {	
	case 1:
		//this tells whether or not the KeyEvent W is pressed then the direction y is changed by the speed integer
		if(e.getKeyCode()==KeyEvent.VK_W) {
			setYDirection(0);
			move();
		}
		//this tells whether or not the KeyEvent S is pressed then the direction of y is changed
		if(e.getKeyCode()==KeyEvent.VK_S) {
			setYDirection(0);
			move();
		}
		break;
	case 2:
		//this tells whether or not the KeyEvent W is pressed then the direction y is changed by the speed integer
		if(e.getKeyCode()==KeyEvent.VK_UP) {
			setYDirection(0);
			move();
		}
		//this tells whether or not the KeyEvent S is pressed then the direction of y is changed
		if(e.getKeyCode()==KeyEvent.VK_DOWN) {
			setYDirection(0);
			move();
		}
		break;
		
	}
		
		
	}
	
	public void setYDirection(int yDirection) {
		yVelocity = yDirection;
		
	}
	
	public void move() {
		y = y + yVelocity;
		
	}
	
	public void draw(Graphics g) {
		if (id ==1 ) 
			g.setColor(Color.blue);
		else 
			g.setColor(Color.red);
		g.fillRect(x, y, width, height);
		}
		

}



