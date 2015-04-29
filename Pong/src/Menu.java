import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;


public class Menu extends JFrame 
{
	private static final long serialVersionUID = 1L;
	
	int screenWidth = 275;
	int screenHeight = 125;
	
	int buttonWidth = 100;
	int buttonHeight = 40;
	
	JButton Play, Quit;
	
	public Menu()
	{
		addButtons();
		addActions();
		
		//allows to set the bounds of the buttons
		getContentPane().setLayout(null);
		
		//Positions play button and quit button
		Play.setBounds(90, 5, 100, 40); //x,y,width,height
		Quit.setBounds(90, 50, 100, 40);//x,y,width,height
		
		
		//adds buttons
		getContentPane().add(Play);
		getContentPane().add(Quit);
		
		//Takes everything and packs it into jframe
		pack();
		setVisible(true);
		//Basically sets screen to center
		setLocationRelativeTo(null);
		setSize(screenWidth, screenHeight);
		setTitle("Pong");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		
	}
	
	private void addButtons()
	{
		Play = new JButton("Play");
		Quit = new JButton("Quit");
	}
	
	private void addActions()
	{
		//play button starts game
		Play.addActionListener(new ActionListener()
		{
			//Take action performed into a variable for usage
			public void actionPerformed(ActionEvent e)
			{
				//Wipes out jframe of menu
				dispose();
				//then  creates new jframe for pong game
				Game game = new Game();
				
				game.go();
			}
		});
		
		//quit button quits game
		Quit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				System.exit(0);
			}
		});
	}
}
