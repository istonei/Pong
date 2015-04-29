import java.awt.*;


public class bot {
	//X is horizontal on screen
		int positionX;
		//Y is vertical on screen
		int positionY;
		//Paddle height and width
		int width = 15;
		int height = 40;
		
		boolean up = false;
		boolean down = false;
		
		int speed = 2;
		
		Rectangle box;
		
		public bot(int positionX, int positionY)
		{
			this.positionX = positionX;
			this.positionY = positionY;
			
			//creates rectangle around paddle
			box = new Rectangle(positionX, positionY, width, height);
			//sets the sides for the rectangle
			box.setBounds(positionX, positionY, width, height);
		}
		//Take instance from game
		public void update(Game game)
		{
			//needs to be here to set the bounds at all times
			box.setBounds(positionX, positionY, width, height);
			
			//bot follows the ball
			//if ball position is higher than position of bot paddle in terms of Y, bot goes up
			if(game.ball.positionY < positionY)
			{
				positionY -= speed;
			}
			//if ball position is lower than position of bot paddle in terms of Y, bot goes down
			if(game.ball.positionY > positionY)
			{
				positionY += speed;
			}
			
		}
		public void render(Graphics g)
		{
			//Set color of paddle to white
			g.setColor(Color.WHITE);
			//fills paddle with white using the variables
			g.fillRect(positionX, positionY, width, height);
		}
}
