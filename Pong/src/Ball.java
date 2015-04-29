import java.awt.*;


public class Ball {
	
	int positionX, positionY;
	int size = 16;
	int speed = 1;
	
	int velocityX, velocityY;
	
	Rectangle box;
	
	public Ball(int positionX, int positionY)
	{
		//sets ball's arguments equal to the positionX and Y from this class
		this.positionX = positionX;
		this.positionY = positionY;
		
		//Positive X goes right, negative X goes left
		velocityX = speed;
		//Positive Y goes down, negative Y goes up
		velocityY = speed;
		
		//creates bounds around ball
		box = new Rectangle(positionX, positionY, size, size);
		//sets the sides for the rectangle, use "this" to prevent use of ball's arguments
		box.setBounds(this.positionX, this.positionY, this.size, this.size);
	}
	
	public void update(Game game)
	{
		//sets bounds around ball at all times
		box.setBounds(positionX, positionY, size, size);
		
		/*if the top left corner of the ball is at 0 (very left of screen), 
		then set it to positive X (so it bounces off and goes right). Basically determines if it hits left side */
		if(positionX <= 0)
		{
			//add points to player 2
			game.score2++;
			velocityX = speed;
		}
		/*If the top left corner of the ball, plus size (top right corner of screen) is greater than game width (the far right of the screen) 
		  make the ball go the opposite way (negative) or (left). Basically determines if it hits the right side of the wall */
		else if(positionX + size >= game.getWidth())
		{
			//add points to player 1
			game.score1++;
			velocityX = -speed;
		}
		
		/*Samething like X, except for top and bottom walls. */
		if(positionY <=0)
		{
			velocityY = speed;
		}
		else if(positionY + size >= game.getHeight())
		{
			velocityY = -speed;
		}
		
		positionX += velocityX;
		positionY += velocityY;
		
		//put collision detection here after ball moves
		paddleCollide(game);
	}
	
	private void paddleCollide(Game game)
	{
		if(box.intersects(game.player.box))
		{
			//ball goes to right
			velocityX = speed;
		}
		else if (box.intersects(game.ai.box))
		{
			//ball goes to left
			velocityX = -speed;
		}
	}
	
	public void render (Graphics g)
	{
		g.setColor(Color.WHITE);
		g.fillOval(positionX, positionY, size, size);
	}
}
