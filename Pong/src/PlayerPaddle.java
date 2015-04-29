import java.awt.*;

public class PlayerPaddle {

	//X is horizontal on screen
	int positionX;
	//Y is vertical on screen
	int positionY;
	//Paddle height and width
	int width = 15;
	int height = 40;
	
	int speed = 1;
	
	Rectangle box;
	
	boolean up = false;
	boolean down = false;
	
	public PlayerPaddle(int positionX, int positionY)
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
		
		//positionY>=0 prevents it from going off screen
		if(up && positionY>0)
		{
			//To go up, position Y must decrease (minus)
			positionY -= speed;
		}
		if(down && positionY < game.getHeight() - height)
		{
			//To go down, position Y must increase
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
