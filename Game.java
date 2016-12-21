import java.awt.*;
import javax.swing.*;

public class Game{
	
	public Game(){
		Board b = new Board(); 
		JFrame f = new JFrame("War Game");
		f.add(b.getBoard());
		f.setSize(700,650);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.pack();
		f.setVisible(true);
	}
	
	public static void main(String[] args){
		new Game();
	}
}