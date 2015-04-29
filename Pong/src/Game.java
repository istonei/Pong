import java.awt.*;
import java.awt.image.*;

import javax.swing.*;


//Extends canvas uses methods of canvas
//Canvas used to draw stuff
//Runnable lets you make your game run into a thread, so basically a process in task manager
public class Game extends Canvas implements Runnable {
	
	private static final long serialVersionUID = 1L;
	
	public static PlayerPaddle player;
	public static bot ai;
	public static Ball ball;
	InputHandler IH;
	
	//Window of the game
	JFrame frame;
	//this will be width and height of game, they are final because they are not going to change
	public final int WIDTH = 400;
	//Easier way to make the game 16 by 9 (widescreen), by dividing the width by 16*9
	public final int HEIGHT = WIDTH / 16 * 9;
	//this makes height and width a single variable
	public final Dimension gameSize = new Dimension(WIDTH, HEIGHT);
	//Title of application
	public final String TITLE = "Pong";
	
	//Similar to youtube, how images buffer. In this case I'm creating an image to put stuff on, and uses RGB values
	BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);

	 //Whether the game is running, game isn't running yet so set to false
	static boolean gameOperating = false;
	
	int score1, score2;
	
	//Can't implement Runnable without having a run method
	//run, runs the process
	public void run()
	{
		//game/logic loop, if gameRunning = true, then do the logic
		while (gameOperating==true)
		{
			update();
			render();
			
			//prevents paddle from going too fast, program will base ball speed on cpu speed without this
			try
			{
				//puts 7 milliseconds between each tick or update
				Thread.sleep(7);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}
	
	//need these methods to run game, can't without it
	//Start method
	public synchronized void go()
	{
		gameOperating = true;
		new Thread(this).start();
	}
	
	//Stops process when game is not running
	public static synchronized void end()
	{
		gameOperating = false;
		System.exit(0);
	}
	
	public Game()
	{
		frame = new JFrame();
		//this references Canvas, this will set size of canvas
		setMinimumSize(gameSize);
		setPreferredSize(gameSize);
		setMaximumSize(gameSize);
		
		//In the frame container, add this(reference to canvas), basically what you're drawing inside the frame or window
		frame.add(this, BorderLayout.CENTER);
		frame.pack();
		
		//exits application when you hit X
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//makes frame or window visible
		frame.setVisible(true);
		//prevents resizing of game, you can't extend window, this helps prevent it from being glitchy or messed up
		frame.setResizable(false);
		//sets the windows title to the title variable
		frame.setTitle(TITLE);
		//Sets the location of the window to nothing (null), basically puts window in center of the screen
		frame.setLocationRelativeTo(null);
		
		
		//Creates instance of the inputhandler
		IH = new InputHandler(this);
		
		//positions player 1 paddle on screen
		player = new PlayerPaddle(10, 60);
		//used getwidth to find width, then did some adjusts to make it fit better
		ai = new bot(getWidth() - 20, 60);
		//used getwidth / 2 and getheight /2 to make it the size it is
		ball = new Ball(getWidth() / 2, getHeight() / 2);
		
	}
	
	//updates any changes in the game
	public void update()
	{
		//this class is called game
		player.update(this);
		ai.update(this);
		ball.update(this);
	}
	
	public void render()
	{
		//BufferStrategy is how it buffers it, it prevents flickering
		BufferStrategy bs = getBufferStrategy();
		//If there is no buffer strategy, create a buffer strategy
		if(bs == null)
		{
			//Buffers 3 times to reduce tearing
			createBufferStrategy(3);
			//this makes it go back to render and continues from there
			return;
		}
		
		/*Use g to draw stuff to screen, get bufferstrategy tells getDrawgraphics how to draw stuff 
		 * on the bufferstrategy, then getDrawGraphics on the buffer screen*/
		Graphics g = bs.getDrawGraphics();
		
		/*Draw buffered image (image from top of code), and draw it from 0,0 (top left) and uses getwidth
		and getheight to determine how much needs to be drawn*/
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		
		g.setColor(Color.WHITE);
		g.drawString("Player 1: " + score1, 50, 10);
		g.drawString("Player 2: " + score2, 300, 10);
		g.drawString("*Click on screen first to be able to move*", 100, 232);
		
		//Uses graphics of the bufferstrategy (which draws it), to draw player
		player.render(g);
		ai.render(g);
		ball.render(g);
		
		//Dispose graphics
		g.dispose();
		//buffer the graphics then play it
		bs.show();
	}
	
	public static void main(String[] args)
	{
		Game game = new Game();
		//game.run() doesn't work, so have to use synchronized go and stop method
		game.go();
	}
	
}
