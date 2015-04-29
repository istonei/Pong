import java.awt.event.*;


public class InputHandler implements KeyListener
{
	public InputHandler(Game game)
	{
		game.addKeyListener(this);
	}
	
	public void keyPressed(KeyEvent e) 
	{
		//Grab keycode of key
		int keyCode = e.getKeyCode();
		
		//If keycode is W, make paddle go up
		if(keyCode == KeyEvent.VK_W)
		{
			Game.player.up = true;
		}
		//If keycode is S, make paddle go down
		if(keyCode == KeyEvent.VK_S)
		{
			Game.player.down = true;
		}
	}

	public void keyReleased(KeyEvent e) 
	{
		//Grab keycode of key
				int keyCode = e.getKeyCode();
				
				//prevents paddle from continuing to go up when W key is released
				if(keyCode == KeyEvent.VK_W)
				{
					Game.player.up = false;
				}
				//prevents paddle from continuing to go down when S key is released
				if(keyCode == KeyEvent.VK_S)
				{
					Game.player.down = false;
				}
	}

	public void keyTyped(KeyEvent e) 
	{

	}

}
