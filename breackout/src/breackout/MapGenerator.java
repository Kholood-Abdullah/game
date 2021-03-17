package breackout;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;


public class MapGenerator {
	 public int map[][];
	 public int brickwidth;
	 public int brickHight;
	 
	 public MapGenerator(int row, int col){
		 map = new int[row][col];
		 for(int i = 0; i< map.length; i++){
			 for(int j = 0; j< map[0].length; j++){
	           map[i][j] = 1;
			 } // end inner for
		} // end outer for
		 brickwidth = 540/col;
		 brickHight = 150/row;
	} // end MapGenerator method
	 public void draw(Graphics2D g){
		 for(int i = 0; i < map.length; i++){
			 for(int j = 0; j < map[0].length; j++){
	           if(map[i][j] > 0){
	        	g.setColor(Color.white);  
	        	g.fillRect(j* brickwidth + 80, i * brickHight + 50, brickwidth, brickHight);
	              
	        	g.setStroke(new BasicStroke(3));
	        	g.setColor(Color.black);
	        	g.drawRect(j* brickwidth + 80, i * brickHight + 50, brickwidth, brickHight);
	           } // end if
	        } // end inner for 
        } // end outer for
	} // end draw method
	public void setBrickValue(int value, int row, int col) {
		
			map[row][col] = value;
		
	    } // end setBrickValue
		
		
	 } // end class MapGenerator method
	 



