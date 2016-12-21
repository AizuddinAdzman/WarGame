import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.util.Vector;

public class Tank{
	private int x;
	private int y; 
	private String name;
	Vector<String> moveList = new Vector<String>(0,1);
	
	public int getX(){ return x; }
	public int getY(){ return y; }
	public String getName(){ return name; }
	
	// set tank's coordinate. 
	public void setCoor(int x, int y){
		this.x=x;
		this.y=y; 
	}
	
	public Tank(){}	                  // blank constructor
	public Tank(String nm){ name=nm;} // constructor with string name
	
	// Storing player's movement. 
	//Vector<<String>> moveList = new Vector<<String>>();
	
	ImageIcon bot_up = new ImageIcon(getClass().getResource("png/Tank1_Up.png"));
	ImageIcon bot_down = new ImageIcon(getClass().getResource("png/Tank1_Down.png"));
	ImageIcon bot_right = new ImageIcon(getClass().getResource("png/Tank1_Right.png"));
	ImageIcon bot_left = new ImageIcon(getClass().getResource("png/Tank1_Left.png")); 
	
	ImageIcon player_up = new ImageIcon(getClass().getResource("png/Tank2_Up.png"));
	ImageIcon player_down = new ImageIcon(getClass().getResource("png/Tank2_Down.png"));
	ImageIcon player_right = new ImageIcon(getClass().getResource("png/Tank2_Right.png"));
	ImageIcon player_left = new ImageIcon(getClass().getResource("png/Tank2_Left.png")); 
	
	ImageIcon explode = new ImageIcon(getClass().getResource("png/explosion.png"));
	
	// To get a picture for specific-current-tank-status or explosion tile
	public ImageIcon getPicture(String s){
		ImageIcon tank = new ImageIcon();
		switch (s){
			case "BOT_UP": 
				tank = bot_up;
				break;
			case "BOT_DOWN": 
				tank = bot_down;
				break;
			case "BOT_RIGHT": 
				tank = bot_right;
				break;
			case "BOT_LEFT": 
				tank = bot_left;
				break;
			case "P_UP": 
				tank = player_up;
				break;
			case "P_DOWN": 
				tank = player_down;
				break;
			case "P_RIGHT": 
				tank = player_right;
				break;
			case "P_LEFT": 
				tank = player_left;
				break;
			case "BOOM":
				tank = explode;
			default:
		}
		return tank;
	}
	
	
	// move the tank position downwards; 
	public void move_down(JButton[][] g){
		boolean found = false;
		while(!found){
			if(this.getY() == 9){
				found = true; 
			} else if(g[this.getX()][this.getY()+1].getIcon() == null){
				g[this.getX()][this.getY()].setIcon(null);
				this.setCoor(this.getX(),this.getY()+1);
				if (this.getName() == "bot"){
					g[this.getX()][this.getY()].setIcon(bot_down);
				} else {
					g[this.getX()][this.getY()].setIcon(player_down);
				}
				found = true;
			} else{
				found = true;
			}
		}
	}
	
	// move the tank coordinate only; 
	public void move_up(JButton[][] g){
		boolean found = false;
		while(!found){
			if(this.getY() == 0){
				found = true; 
			} else if (g[this.getX()][this.getY()-1].getIcon() == null){
				g[this.getX()][this.getY()].setIcon(null);
				this.setCoor(this.getX(),this.getY()-1);
				if (this.getName() == "bot"){
					g[this.getX()][this.getY()].setIcon(bot_up);
				} else{
					g[this.getX()][this.getY()].setIcon(player_up);
				}
				found = true;
			} else{
				found = true;
			}
		}
	}
	
	// move the tank coordinate only; 
	public void move_right(JButton[][] g){
		boolean found = false;
		while(!found){
			if(this.getX() == 9){
				found = true; 
			} else if (g[this.getX()+1][this.getY()].getIcon() == null){
				g[this.getX()][this.getY()].setIcon(null);
				this.setCoor(this.getX()+1,this.getY());
				if (this.getName() == "bot"){
					g[this.getX()][this.getY()].setIcon(bot_right);
				} else{
					g[this.getX()][this.getY()].setIcon(player_right);
				}
				found = true;
			} else{
				found = true;
			}
		}
	}
	
	// move the tank coordinate only; 
	public void move_left(JButton[][] g){
		boolean found = false;
		while(!found){			
			if(this.getX() == 0){
				found = true; 
			}
			else if (g[this.getX()-1][this.getY()].getIcon() == null){
				g[this.getX()][this.getY()].setIcon(null);
				this.setCoor(this.getX()-1,this.getY());
				if (this.getName() == "bot"){
					g[this.getX()][this.getY()].setIcon(bot_left);
				} else {
					g[this.getX()][this.getY()].setIcon(player_left);
				}
				found = true;
			} else{
				found = true;
			}
		}
	} 
	
	// tank shoot up
	public void shoot_down(JButton[][] a){ 
		if (this.getY() != 9){
			if (this.getName() == "bot"){
				a[this.getX()][this.getY()].setIcon(bot_down); 
			} else{
				a[this.getX()][this.getY()].setIcon(player_down); 
			}
			a[this.getX()][this.getY()+1].setBorder(new LineBorder(Color.RED));
		}
	}
	
	// tank shoot down 
	public void shoot_up(JButton[][] a){
		if (this.getY() != 0){
			if (this.getName() == "bot"){
				a[this.getX()][this.getY()].setIcon(bot_up); 
			} else{
				a[this.getX()][this.getY()].setIcon(player_up); 
			}
			a[this.getX()][this.getY()-1].setBorder(new LineBorder(Color.RED));
		}
	}
	
	// tank shoot right 
	public void shoot_right(JButton[][] a){
		if (this.getX() != 9){
			if (this.getName() == "bot"){
				a[this.getX()][this.getY()].setIcon(bot_right);
			} else{ 
				a[this.getX()][this.getY()].setIcon(player_right);
			} 
			a[this.getX()+1][this.getY()].setBorder(new LineBorder(Color.RED));
		}
	}
	
	// tank shoot left
	public void shoot_left(JButton[][] a){
		if (this.getX() != 0){
			if (this.getName() == "bot"){
				a[this.getX()][this.getY()].setIcon(bot_left);
			} else{
				a[this.getX()][this.getY()].setIcon(player_left);
			} 
			a[this.getX()-1][this.getY()].setBorder(new LineBorder(Color.RED));
		}
	}
	
	// Checking tank's shot (shooting purpose)
	public boolean onTarget(JButton[][] j, String t){
		boolean shot = false; 
		switch (t){
			case "UP":
				if(this.getY()>0){
					if(j[this.getX()][this.getY()-1].getIcon() != null){
						j[this.getX()][this.getY()-1].setIcon(null);
						j[this.getX()][this.getY()-1].setIcon(explode);
						shot = true;
					}
				} 
				break;
			case "DOWN": 
				if(this.getY()<9){
					if(j[this.getX()][this.getY()+1].getIcon() != null){
						j[this.getX()][this.getY()+1].setIcon(null);
						j[this.getX()][this.getY()+1].setIcon(explode);
						shot = true;
					}
					
				}
				break;
			case "RIGHT":
				if(this.getX()<9){
					if(j[this.getX()+1][this.getY()].getIcon() != null){
						j[this.getX()+1][this.getY()].setIcon(null);
						j[this.getX()+1][this.getY()].setIcon(explode);
						shot = true;
					}
				}
				break;
			case "LEFT": 
				if(this.getX()>0){
					if(j[this.getX()-1][this.getY()].getIcon() != null){
						j[this.getX()-1][this.getY()].setIcon(null);
						j[this.getX()-1][this.getY()].setIcon(explode);
						shot = true;
					}
				}
				break;
			default:
		}
		return shot;
		
	}
	
}