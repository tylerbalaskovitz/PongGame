import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable{

	static final int GAME_WIDTH = 1000;
	static final int GAME_HEIGHT =(int)(GAME_WIDTH * (0.5555));
	static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH, GAME_HEIGHT);
	static final int BALL_DIAMETER = 20;
	static final int PADDLE_WIDTH = 25;
	static final int PADDLE_HEIGHT = 100;
	
	Thread gameThread;
	Image image;
	Graphics graphics;
	Random random;
	Paddle paddle1;
	Paddle paddle2;
	Score score;
	Ball ball;
	
	
	
	
	GamePanel(){
		newPaddles();
		newBall();
		score = new Score(GAME_WIDTH, GAME_HEIGHT);
		this.setFocusable(true);
		this.addKeyListener(new AL());
		this.setPreferredSize(SCREEN_SIZE);
		
		gameThread = new Thread(this);
		gameThread.start();
		
		
	}
	
	public void newBall() {
		random = new Random();
		ball = new Ball((GAME_WIDTH/2)-(BALL_DIAMETER/2), random.nextInt(GAME_HEIGHT - (BALL_DIAMETER)), BALL_DIAMETER, BALL_DIAMETER);
		
	}
	
	public void newPaddles() {
		paddle1 = new Paddle(0, (GAME_HEIGHT/2-(PADDLE_HEIGHT/2)), PADDLE_WIDTH, PADDLE_HEIGHT, 1);
		paddle2 = new Paddle((GAME_WIDTH-PADDLE_WIDTH), ((GAME_HEIGHT/2-(PADDLE_HEIGHT/2))), PADDLE_WIDTH, PADDLE_HEIGHT, 2);
		
	}
	
	public void paint(Graphics g) {
		image = createImage(getWidth(), getHeight());
		graphics = image.getGraphics();
		draw(graphics);
		g.drawImage(image, 0, 0, this);
		
	}
	
	//draws/renders the paddle ball and score from the paddle, ball and score classes. 
	//Also note that each of the classes have a draw() method inside of it. This allows for an easy naming convention to occur so that way all the draw methods
	//will be rendered in the draw method of the GamePanel
	public void draw(Graphics g) {
		paddle1.draw(g);
		paddle2.draw(g);
		ball.draw(g);
		score.draw(g);
	}
	
	public void move() {
		paddle1.move();
		paddle2.move();
		ball.move();
		
	}
	
	public void checkCollision() {
		//the check collision also keeps score in the game based on the location of the ball and whether or not the ball crosses a certain point on the x axis
		
		//bounces the ball off of the top and bottom of the window edges. This is based on the perimeter of the window and when the ball crosses these 
		//points on the y axis it reverse the direction the fall is going and thus will make the ball 'bounce' in the opposite direction.
		if (ball.y <= 0 ) {
			ball.setYDirection(-ball.yVelocity);
		}
		if (ball.y >= (GAME_HEIGHT-BALL_DIAMETER)) {
			ball.setYDirection(-ball.yVelocity);
		}
		
		//bounces the ball off of the paddles. This is done with the intersect method from the ball. The intersect method comes
		//from the fact that the ball class extends rectangle allowing for the implementation of the intersect method.
		if (ball.intersects(paddle1)) {
			ball.xVelocity = Math.abs(ball.xVelocity);
			ball.xVelocity++; //increases speed each time the ball hits a paddle
			if (ball.yVelocity>0)
				ball.yVelocity++;
			else
				ball.yVelocity--;
			ball.setXDirection(ball.xVelocity);
			ball.setYDirection(ball.yVelocity);
		
		}
		
		if (ball.intersects(paddle2)) {
			ball.xVelocity = Math.abs(ball.xVelocity);
			ball.xVelocity++; //increases speed each time the ball hits a paddle
			if (ball.yVelocity>0)
				ball.yVelocity++;
			else
				ball.yVelocity--;
			ball.setXDirection(-ball.xVelocity);
			ball.setYDirection(ball.yVelocity);
		
		}
		
		
		
		//stops paddles at the window edges. This is done similar to the way the ball bounces off the top of the screen. It prevents the paddle from
		//crossing a certain point on the upper and lower part of the screen
		if (paddle1.y<=0)
			paddle1.y = 0;
		if (paddle1.y >= (GAME_HEIGHT - PADDLE_HEIGHT))
			paddle1.y = (GAME_HEIGHT-PADDLE_HEIGHT);
		
		if (paddle2.y<=0)
			paddle2.y = 0;
		if (paddle2.y >= (GAME_HEIGHT - PADDLE_HEIGHT))
			paddle2.y = (GAME_HEIGHT-PADDLE_HEIGHT);
		
		//gives player a point and creates a new paddles & ball. This is done by having the ball cross the distance of the screen and resets the game by having the
		//paddles rerendered and a ball is rendered again so that way the game can continue.
		if (ball.x <= 0) {
			score.player2++;
			newPaddles();
			newBall();
			
		}
		if (ball.x >= (GAME_WIDTH - BALL_DIAMETER)) {
			score.player1++;
			newPaddles();
			newBall();
			
		}
		
		
	}
	
	public void run() {
		//game loop that is used 
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000/amountOfTicks;
		double delta = 0;
		while (true) {
			
			long now = System.nanoTime();
			delta += (now - lastTime)/ns;
			lastTime = now;
			if (delta >= 1) {
				move();
				checkCollision();
				repaint();
				delta--;
				
				
			}
			
			
			
		}
		
		
		
	}
	
	// this is an inner class that allows things to be passed in by key presses.
	public class AL extends KeyAdapter{
		public void keyPressed(KeyEvent e) {
			paddle1.keyPressed(e);
			paddle2.keyPressed(e);
			
			
		}
		
		public void keyReleased(KeyEvent e) {
			paddle1.keyReleased(e);
			paddle2.keyReleased(e);
			
		}
		
		
		
	}
	
}
