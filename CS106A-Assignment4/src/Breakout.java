// TODO: A program to create breakout game

import acm.graphics.*;     // GOval, GRect, etc.

import acm.program.*;      // GraphicsProgram
import acm.util.*;         // RandomGenerator
import java.awt.*;         // Color
import java.awt.event.*;   // MouseEvent

public class Breakout extends BreakoutProgram {

	public void run() {
		setTitle("CS 106A Breakout");
		setCanvasSize(CANVAS_WIDTH, CANVAS_HEIGHT);
		
		// Set Initial turn to zero
		n_turn=0;
		
		// Breakout game plan while it is not win and not excess max turn yet
		while(n_turn<NTURNS && winBoolean!=true) {
			breakout();
			n_turn++;	
		}
		
		//Display game over or win banner
		if (winBoolean) {
			GLabel winLabel = new GLabel("YOU WIN!");
			winLabel.setCenterLocation(CANVAS_WIDTH/2, CANVAS_HEIGHT/2);
			winLabel.setFont(SCREEN_FONT);
			add(winLabel);
		}else {
			GLabel lossLabel = new GLabel("GAME OVER!");
			lossLabel.setFont(SCREEN_FONT);
			lossLabel.setCenterLocation(CANVAS_WIDTH/2, CANVAS_HEIGHT/2);
			add(lossLabel);
		
		}
		
	}
	
	// Play one round of breakout
	private void breakout() {
		Bricks();
		initBall();
		ballMoved();
		removeBrick();
		score=0;
	}
	
	
	// initial Bricks
	public void Bricks() {
		Color[] color = {Color.RED, Color.RED, Color.ORANGE, Color.ORANGE, 
				Color.YELLOW, Color.YELLOW, Color.GREEN,Color.GREEN, Color.CYAN,
				Color.CYAN};
		int color_count=0;
		for (double j=BRICK_Y_OFFSET;j<=BRICK_HEIGHT*NBRICK_ROWS+BRICK_Y_OFFSET;j+=BRICK_HEIGHT){
			for (double i=0;i<=BRICK_WIDTH*NBRICK_COLUMNS;i+=BRICK_WIDTH){
				rect = new GRect(i,j,BRICK_WIDTH,BRICK_HEIGHT);
				rect.setFilled(true);
				rect.setColor(color[color_count]);
				add(rect);
			}
			color_count++;
			if (color_count>9) {
				color_count=0;
			}
		}
	}
	
	// initial Ball
	private void initBall() {
		ball = new GOval((CANVAS_WIDTH/2)-BALL_RADIUS,(CANVAS_HEIGHT/2)-BALL_RADIUS, BALL_RADIUS,BALL_RADIUS);
		ball.setFilled(true);
		ball.setColor(Color.BLACK);
		add(ball);
	}
	
	/* Ball + Bouncing */
	public void ballMoved() {
		
		//reset the game statistic banner
		myLabel = new GLabel("Score: "+Integer.toString(score)+", Turns: "+Integer.toString(NTURNS-n_turn), 5, 20);
		myLabel.setFont(SCREEN_FONT);
		add(myLabel);
		
		// Ball movement
		while(brick_count>0 && ball.getY()<=getHeight()) {
			ball.move(speed_x, speed_y);
			collide();// Check for Collision 
			pause(DELAY);
			if (ball.getX()>=getWidth()-(2*BALL_RADIUS) || ball.getX()<=0) {
				speed_x = -speed_x;
			}
			if (ball.getY()<=0) {
				speed_y = -speed_y;
			}
			if (ball.getY()>=getHeight()) {
				break;
			}
		}
		
		// Win when there is no bricks left
		if (brick_count==0) {
			winBoolean=true;
		}
		
		// Remove ball and game statistic banner
		remove(ball);
		remove(myLabel);
		}

/* Paddle Moving */
	
	
	private GRect paddle = new GRect(0, CANVAS_HEIGHT- PADDLE_Y_OFFSET, PADDLE_WIDTH, PADDLE_HEIGHT);
	public void mouseMoved(MouseEvent event) {
		double mouse_X = event.getX();
		paddle.setLocation(mouse_X-(PADDLE_WIDTH/2), CANVAS_HEIGHT- PADDLE_Y_OFFSET);
		add(paddle);
	}
	
	/* Update Turns and Score on Game Statistic Banner*/
	
	private void updateLabel() {
		remove(myLabel);
		myLabel = new GLabel("Score: "+Integer.toString(score)+", Turns: "+Integer.toString(NTURNS-n_turn), 5, 20);
		myLabel.setFont(SCREEN_FONT);
		add(myLabel);
	}
	
	
	/* Ball Collision */
	public void collide () {
		GObject collider = getCollidingObject();
		if (collider != null) {
			if (collider!=paddle) {
				remove(collider);
				speed_y = -speed_y;
				brick_count--;
				score++;
				updateLabel();
			}else {
				speed_y = -1*Math.abs(speed_y);
			}
		}
		}
	
	/*Get Collision Object*/
	private GObject getCollidingObject() {
		double location_x = ball.getX();
		double location_y = ball.getY();
		
		if (getElementAt(location_x,location_y) != null) {
			return getElementAt(location_x, location_y);
		}else if(getElementAt(location_x+2*BALL_RADIUS,location_y) != null) {
			return getElementAt(location_x+2*BALL_RADIUS,location_y);
		}else if(getElementAt(location_x,location_y+2*BALL_RADIUS) != null) {
			return getElementAt(location_x,location_y+2*BALL_RADIUS);
		}else if(getElementAt(location_x+2*BALL_RADIUS,location_y+2*BALL_RADIUS) != null) {
			return getElementAt(location_x+2*BALL_RADIUS,location_y+2*BALL_RADIUS);
		}
		return null;
	}
	
	
	/*Remove Brick at the end of the game*/
	private void removeBrick() {
		
		for (double j=BRICK_Y_OFFSET;j<=BRICK_HEIGHT*NBRICK_ROWS+BRICK_Y_OFFSET;j+=BRICK_HEIGHT){
			for (double i=0;i<=BRICK_WIDTH*NBRICK_COLUMNS;i+=BRICK_WIDTH){
				if (getElementAt(i,j)!=null) {
					remove(getElementAt(i,j));
				}
			}
			}	
	}
	
	
	//Private Variable 
	private int brick_count = NBRICK_COLUMNS*NBRICK_ROWS;
	private GOval ball;
	private double speed_x = Math.random()*VELOCITY_X_MAX+VELOCITY_X_MIN ;
	private double speed_y = VELOCITY_Y;
	private int score;
	private int n_turn;
	private boolean winBoolean = false;
	private GLabel myLabel;
	private GRect rect;
		
}


	
	
	
	

