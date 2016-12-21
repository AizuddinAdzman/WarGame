import java.awt.*;
import javax.swing.border.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.Vector;
import java.lang.*;
import javax.swing.JOptionPane;
import java.util.concurrent.TimeUnit;

public  class Board implements KeyListener{
	private JPanel board;								// Board for play field
	private JPanel doc; 
	public JButton[][] tiles = new JButton[10][10];		// 10x10 JPanel to create tiles, array initialize;
	private Tank tank_bot;     							// Computer tank 
	private Tank tank_player;  							// human-player tank
	private Tank tank_temp;        						// to display human-player tank temporarily (insert input purpose). 
	
	public Board(){
		initializeGUI(); // board initialization. 
		//addActionListener();
		initializeTank();
		addKeyListener();

	}
	
	public void initializeGUI(){
		board = new JPanel(new GridLayout(0,10));
		board.setBorder(new LineBorder(Color.BLACK));
		
		// creating the tiles and store them inside the 2d array
		for (int i=0; i<10; i++){
			for (int j=0; j<10; j++){
				tiles[j][i] = new JButton();
				tiles[j][i].setBorder(new LineBorder(Color.BLACK,1));
				tiles[j][i].setBackground(Color.WHITE);
				board.add(tiles[j][i]);
			}
		}
	}
	
	// To return the board (because board is private); 
	public final JComponent getBoard() {
        return board;
    }
	
	public final JComponent getDoc(){
		doc = new JPanel(new GridLayout(0,10));
		doc.setBorder(new LineBorder(Color.BLACK));
		return doc; 
	}

	
	public void addKeyListener(){
		for (int i=0; i<10; i++){ // i = y-axis
			for (int j=0; j<10; j++){ // j = x-axis
				tiles[j][i].addKeyListener(this);
			}
		}
	}
	
	// Handle the key-typed event
	@Override
	public void keyPressed(KeyEvent e) {
		int k = e.getKeyCode();
		String v = e.getKeyText(k); 
		int x=0, y=0;
		
		if(k == KeyEvent.VK_UP)
			v = "up";
		else if(k == KeyEvent.VK_DOWN)
			v = "down";
		else if(k == KeyEvent.VK_RIGHT)
			v = "right";
		else if(k == KeyEvent.VK_LEFT)
			v = "left";
		else if(k == KeyEvent.VK_W)
			v = "UP";
		else if(k == KeyEvent.VK_S)
			v = "DOWN";
		else if(k == KeyEvent.VK_D)
			v = "RIGHT";
		else if(k == KeyEvent.VK_A)
			v = "LEFT";
		System.out.println("Test key event:"+v);
		
		if(tank_player.moveList.size() != 18){
			tank_player.moveList.add(v);
			switch (v){
				case "up":
					if(tank_temp.getY()>0){
						tiles[tank_temp.getX()][tank_temp.getY()-1].setBorder(new LineBorder(Color.BLUE,4));
						tiles[tank_temp.getX()][tank_temp.getY()].setBorder(new LineBorder(Color.BLACK,1));
						tank_temp.setCoor(tank_temp.getX(), tank_temp.getY()-1);
					}
					break; 
				case "down":
					if(tank_temp.getY()<9){
						tiles[tank_temp.getX()][tank_temp.getY()+1].setBorder(new LineBorder(Color.BLUE,4));
						tiles[tank_temp.getX()][tank_temp.getY()].setBorder(new LineBorder(Color.BLACK,1));
						tank_temp.setCoor(tank_temp.getX(), tank_temp.getY()+1);
					}
					break;
				case "right": 
					if(tank_temp.getX()<9){
						tiles[tank_temp.getX()+1][tank_temp.getY()].setBorder(new LineBorder(Color.BLUE,4));
						tiles[tank_temp.getX()][tank_temp.getY()].setBorder(new LineBorder(Color.BLACK,1));
						tank_temp.setCoor(tank_temp.getX()+1, tank_temp.getY());
					}
					break;
				case "left":
					if(tank_temp.getX()>0){
						tiles[tank_temp.getX()-1][tank_temp.getY()].setBorder(new LineBorder(Color.BLUE,4));
						tiles[tank_temp.getX()][tank_temp.getY()].setBorder(new LineBorder(Color.BLACK,1));
						tank_temp.setCoor(tank_temp.getX()-1, tank_temp.getY());
					}
					break;
				case "UP":
					if(tank_temp.getY()>0){
						tiles[tank_temp.getX()][tank_temp.getY()-1].setBorder(new LineBorder(Color.RED,2));
					}
					break;
				case "DOWN":
					if(tank_temp.getY()<9){
						tiles[tank_temp.getX()][tank_temp.getY()+1].setBorder(new LineBorder(Color.RED,2));
					}
					break;
				case "RIGHT":
					if(tank_temp.getX()<9){
						tiles[tank_temp.getX()+1][tank_temp.getY()].setBorder(new LineBorder(Color.RED,2));
					}
					break;
				case "LEFT":
					if(tank_temp.getX()>0){
						tiles[tank_temp.getX()-1][tank_temp.getY()].setBorder(new LineBorder(Color.RED,2));
					}
					break;
				default:
			}
			System.out.println("Input coordinate:"+tank_temp.getX()+","+tank_temp.getY());
		} else{
			//tank_player.moveList.removeElementAt(18);
			for(int i=0; i<tank_player.moveList.size(); i++){
				v = tank_player.moveList.elementAt(i);//tank_player.moveList[i];
				switch (v){
					case "up":
						tank_player.move_up(tiles);
						break; 
					case "down":
						tank_player.move_down(tiles);
						break;
					case "right": 
						tank_player.move_right(tiles);
						break;
					case "left":
						tank_player.move_left(tiles); 
						break;
					case "UP":
						tank_player.shoot_up(tiles);
						if(tank_player.onTarget(tiles, v)){
							i=tank_player.moveList.size();
						}
						break;
					case "DOWN":
						tank_player.shoot_down(tiles);
						if(tank_player.onTarget(tiles, v)){
							i=tank_player.moveList.size();
						}
						break;
					case "RIGHT":
						tank_player.shoot_right(tiles);
						if(tank_player.onTarget(tiles, v)){
							i=tank_player.moveList.size();
						}
						break;
					case "LEFT":
						tank_player.shoot_left(tiles);
						if(tank_player.onTarget(tiles, v)){
							i=tank_player.moveList.size();
						}
						break;
					default:
				}	
				String t = tank_bot.moveList.elementAt(i); 
				switch (t){
					case "up":
						tank_bot.move_up(tiles);
						break; 
					case "down":
						tank_bot.move_down(tiles);
						break;
					case "right": 
						tank_bot.move_right(tiles);
						break;
					case "left":
						tank_bot.move_left(tiles); 
						break;
					case "UP":
						tank_bot.shoot_up(tiles);
						if(tank_bot.onTarget(tiles, v)){
							i=tank_player.moveList.size();
						}
						break;
					case "DOWN":
						tank_bot.shoot_down(tiles);
						if(tank_bot.onTarget(tiles, v)){
							i=tank_player.moveList.size();
						}
						break;
					case "RIGHT":
						tank_bot.shoot_right(tiles);
						if(tank_bot.onTarget(tiles, v)){
							i=tank_player.moveList.size();
						}
						break;
					case "LEFT":
						tank_bot.shoot_left(tiles);
						if(tank_bot.onTarget(tiles, v)){
							i=tank_player.moveList.size();
						}
						break;
					default:
				}	
				
				if (i == tank_player.moveList.size()){
					JOptionPane.showMessageDialog(null,
					"You Win!.",
					"Victory!",
					JOptionPane.PLAIN_MESSAGE);
				}
			}
		}
    }
	
    /** Handle the key-pressed event. */
    @Override
	public void keyTyped(KeyEvent e) {}

    /** Handle the key-released event. */
    @Override
	public void keyReleased(KeyEvent e) {}
	
	// initialize the tanks in the game 
	public void initializeTank(){
		// computer's tank
		tank_bot = new Tank("bot"); 
		System.out.println("Bot-Tank is created");
		tank_bot.setCoor(0, 0);
		tiles[0][0].setIcon(tank_bot.getPicture("BOT_DOWN"));
		tank_bot.moveList.add("down"); 
		tank_bot.moveList.add("right"); 
		tank_bot.moveList.add("right");
		tank_bot.moveList.add("down");
		tank_bot.moveList.add("right");
		tank_bot.moveList.add("down");
		tank_bot.moveList.add("down");
		tank_bot.moveList.add("right");
		tank_bot.moveList.add("down");
		tank_bot.moveList.add("down");
		tank_bot.moveList.add("d");
		tank_bot.moveList.add("left");
		tank_bot.moveList.add("s");
		tank_bot.moveList.add("down");
		tank_bot.moveList.add("right");
		tank_bot.moveList.add("right");
		tank_bot.moveList.add("right");
		tank_bot.moveList.add("w");
		System.out.println(" "+tank_bot.getX()+","+tank_bot.getY());
		
		// human-player's tank
		tank_player = new Tank("player");
		System.out.println("Human-Tank is created");
		tank_player.setCoor(9,9);
		tiles[9][9].setIcon(tank_player.getPicture("P_UP"));
		System.out.println(" "+tank_player.getX()+","+tank_player.getY());
		
		tank_temp = new Tank();
		System.out.println("Ghost tank created"); 
		tank_temp.setCoor(tank_player.getX(), tank_player.getY());
		tiles[tank_temp.getX()][tank_temp.getY()].setBorder(new LineBorder(Color.BLUE));
	
	}
	
}


