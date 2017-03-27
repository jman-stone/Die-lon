package main;


import java.awt.*;
import javax.swing.*;

//import level.TestWorld;
import main.Keys;

public class worldWindow extends Canvas implements Runnable {
	
	private boolean running;
	public int scale;
	private final Keys keys = new Keys();
	private InputHandler inputHandler;
	
	public worldWindow(){
		
	}
	
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		worldWindow game = new worldWindow();
		JFrame frame = new JFrame("Die-Lon");
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(game);
		frame.setContentPane(panel);
		frame.pack();
		frame.setResizable(false);
		frame.setSize(480, 432);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//frame.addWindowListener(game.getWindowListener());
		frame.setVisible(true);
		
	}
	

}
