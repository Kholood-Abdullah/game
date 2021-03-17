package breackout;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
import javax.swing.Timer;


public class Gameplay extends JPanel implements KeyListener , ActionListener {
 // DEFINE AND INTIALIZE THE DATA MEMBERS
	private boolean play = false;
	private int score = 0 ;
	
	private int totalBricks = 21;
	
	private Timer timer;
	private int delay = 8;
	
	private int playerX = 310;
	
	private int ballposx = 120;
	private int ballposy = 350;
	private int ballXdir = -1;
	private int ballYdir = -2;
	
	private MapGenerator map;
	
	
	
	public Gameplay(){
	map = new MapGenerator(3,7);
	addKeyListener(this);
	setFocusable(true);
	setFocusTraversalKeysEnabled(false);
	 timer = new Timer(delay , this);
	 timer.start();
	} //end constructor
	
   public void paint(Graphics g){
	   //background
	   g.setColor(Color.black);
	   g.fillRect(1,1, 692, 592);
	   
	   // drawing map
	      map.draw((Graphics2D)g);
	   
	   // borders
	   g.setColor(Color.yellow);
	   g.fillRect(0,0, 3, 592);
	   g.fillRect(0,0, 692, 3);
	   g.fillRect(692,0, 3, 592);
	   
	   // SCORES
	   g.setColor(Color.white);
	   g.setFont(new Font("serif", Font.BOLD, 30));
	   g.drawString(""+score, 590, 30);
	  
	   // the paddle
	   g.setColor(Color.green);
	   g.fillRect(playerX,550, 100, 8);
	   
	   // the ball
	   g.setColor(Color.yellow);
	   g.fillOval(ballposx,ballposy, 20, 20);
	   
	   if (totalBricks <= 0){
		   play = false;
		   ballXdir = 0;
		   ballYdir = 0;
		   
		   g.setColor(Color.white);
		   g.setFont(new Font("serif", Font.BOLD, 30));
		   g.drawString("**You Won** ", 260, 300);
		  
		   g.setFont(new Font("serif", Font.BOLD , 20));
		   g.drawString("Press Enter to Restart", 230, 350);	 
	   }//end if
	   
	   if(ballposy > 570){
		   play = false;
		   ballXdir = 0;
		   ballYdir = 0;
		   
		   g.setColor(Color.white);
		   g.setFont(new Font("serif", Font.BOLD, 30));
		   g.drawString("Game Over , Scores:"+score, 190, 300);
		  
		   g.setFont(new Font("serif", Font.BOLD , 20));
		   g.drawString("Press Enter to Restart", 230, 350);
		 
	   } //end if
	   g.dispose();
   }//end paint
	
	@Override
	public void keyTyped(KeyEvent e) { }

	@Override
	public void keyReleased(KeyEvent e) { }
	
	@Override
	public void actionPerformed(ActionEvent e) {
       timer.start();
       
       if(play){
    	   if(new Rectangle(ballposx,ballposy, 20, 20).intersects(new Rectangle(playerX, 550, 100, 8))) {
    		   ballYdir = -ballYdir;
    	   }//end if
    	   A:for(int i =0; i< map.map.length; i++){
    		   for(int j =0; j< map.map[0].length; j++){
    		   if(map.map[i][j] >0){
    			   int brickx = j* map.brickwidth+ 80;
    			   int bricky = i* map.brickHight + 50;
    			   int brickwidth = map.brickwidth;
    			   int brickHight = map.brickHight;
    			   
    			 Rectangle rect = new Rectangle(brickx,bricky, brickwidth, brickHight );
    			 Rectangle ballRect = new Rectangle(ballposx, ballposy, 20, 20);
    			 Rectangle brickRect = rect;
    			 
    			 if(ballRect.intersects(brickRect)){
    				 map.setBrickValue(0, i, j);
    				 totalBricks--;
    				 score += 5;
    				 
    				if(ballposx +19 <= brickRect.x || ballposx +1 >= brickRect.x + brickRect.width ){
    				ballXdir = -ballXdir;	
    				} //end subinner if
    				else{
    					ballYdir = -ballYdir;
    				}//end else
    				break A;
    			 } //end inner if
    		   }//end outer if
    	   }//end inner for
    	   }//end label A
    	   ballposx += ballXdir;
    	   ballposy += ballYdir;
    	   if( ballposx < 0){
    		   ballXdir = -ballXdir; 
    	   }//end if
    	   if( ballposy < 0){
    		   ballYdir = -ballYdir; 
    	   }//end if
    	   if( ballposx > 670){
    		   ballXdir = -ballXdir; 
    	   } //end if
       }//end outer if
       
       repaint();
	} //end actionPerformed

	@Override
	public void keyPressed(KeyEvent e) {
		 if(e.getKeyCode() == KeyEvent.VK_RIGHT){
			 if(playerX >= 600){
				 playerX = 600;
			 } //end inner if
			 else{
				 moveRight();
			 } // end else
		 }//end outer if
		 
       if(e.getKeyCode() == KeyEvent.VK_LEFT){
    	   if(playerX < 10){
				 playerX = 10;
			 }  // end inner if
    	   else{
				 moveLeft();
			 } // end else
		 } //end outer if
       
	  if(e.getKeyCode() == KeyEvent.VK_ENTER){
		  if(!play){
			  play = true;
			  ballposx = 120;
			  ballposy = 350;
			  ballXdir = -1;
			  ballYdir = -2;
			  playerX = 310;
			  score = 0;
			  totalBricks = 21;
			  map = new MapGenerator(3,7);
			  repaint();
		  }// end inner if
		  
	  } // end outer if 
		
	}// end keyPressed
   public void moveRight(){
	   play = true;
	   playerX += 20;
   } //end moveRight

   public void moveLeft(){
	   play = true;
	   playerX -= 20;
   } //end moveLeft

   
} //end class Gameplay
