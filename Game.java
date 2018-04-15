import java.util.*;
import java.util.Random;

public class Game{

  public static void main(String[] args){
    Board b = new Board();
    int numShips = 5;
    Ship ships[] = new Ship[numShips];

    // generate 5 ships with random lengths from 2-4
    for (int i = 0; i < ships.length; i++){
      ships[i] = b.generateShip(ships, i, b);
      System.out.printf("ship %d at (%d, %d)\n", i+1, ships[i].getOrigin().getRow(), ships[i].getOrigin().getColumn());
    }
    
    boolean goAgain = true;
    Scanner sc = new Scanner(System.in);
    
    // start game
    while(goAgain) {
		b.display();
			
			System.out.println("Format for coordinate is '# #', e.g '5 6' ");
    		System.out.println("Enter a coordinate: ");
    		String coordinates = sc.nextLine();
    		
    		int xVal = Integer.parseInt(coordinates.split(" ")[0]);
    		int yVal = Integer.parseInt(coordinates.split(" ")[1]);
    		
    		Point shot = new Point(xVal, yVal, b);
    		
    		int misses = 0;
    		
    		//each ship checks for shot that was fired
    		for(int i = 0; i < ships.length; i++) {
    			ships[i].shotFiredAtPoint(shot);
    			if(!ships[i].isHitAtPoint(shot)) {
    				misses++;
    			}
    			ships[i].sunkStatus(numShips);
    		}
    	    
    		hitStatus(misses, numShips);
    		goAgain = b.checkGameStatus(ships);
    		b.advanceToNextMove(shot);    
    	}
    // display final board
		b.display();

  }
  
  public static void hitStatus(int misses, int numShips) {
		if(misses == numShips) {
			System.out.println("You missed");
		}
		else {
			System.out.println("You hit");
		}
  }
}

