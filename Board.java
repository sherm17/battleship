import java.util.Random;

public class Board{

  static final int ROWS = 10;
  static final int COLS = 10;

  private Point[][] grid = new Point[ROWS][COLS];

  public Board(){
    for (int r = 0; r < ROWS; r++){
      for (int c = 0; c < ROWS; c++){
        grid[r][c] = new Point(r, c, this);
      }
    }
  }

  // display board
  public void display(){
    for (int r = ROWS-1; r >= 0; r--){
      System.out.print(r);
      for (int c = 0; c < COLS; c++){
        System.out.print(" " + grid[r][c].displayCharacter());
      }
      System.out.println();
    }
    System.out.print("  ");

    for (int i = 0; i < 10; i++){
      System.out.print(i+ " ");
    }
    System.out.println();
  }

  // replace cells with ship points
  public void replaceCell(Ship s){
    Point temp[] = s.getShipPoints();

    for (int i =0; i < s.getShipLength(); i++ ){
      grid[temp[i].getColumn()][temp[i].getRow()] = temp[i];
    }
  }
  
  // determines grid for next move
  public void advanceToNextMove(Point shot){
	  Point[][] nextGrid = new Point[ROWS][COLS];

	  for (int r = ROWS-1; r >= 0; r--){
	    for (int c = 0; c < COLS; c++){
			  nextGrid[r][c] = grid[r][c].determineNextTurn(shot);
	    }
	  }
  }
  
  // function to generate ships on the 
  // board with no collision with other ships
  public static Ship generateShip(Ship[] ships, int i, Board b){
    boolean onMap = true;
    boolean generate = true;
      
      //generate first ship
    Ship currentShip = new Ship(getRandomStartPoint(), getOrientation(), getRandomShipLength());

    while(generate){
      boolean noCollision = true;
      currentShip.createShipPointArrays();
      for(int j = 0; j < i; j++){
        if (currentShip.collidesWith(ships[j])){
          noCollision = false;
          break;
        }
      }

      onMap = shipInsideBoard(currentShip, currentShip.getOrigin() , currentShip.getShipLength(), currentShip.isVertical());
      if (onMap && noCollision){
        generate = false;
        b.replaceCell(currentShip);
      }
      else{
        	// make a new ship
        currentShip = new Ship(getRandomStartPoint(), getOrientation(), getRandomShipLength());
      }
    }
    return currentShip;
  }
    
    // checks to see if ship is inside board
  public static boolean shipInsideBoard(Ship s, Point p, int l, boolean v){
    for(int i = 0; i < l; i++){
      if (v){ // ship is vertical
        Point currentPoint = new Point(p.getRow(), p.getColumn()+i);
        if (!s.containsPoint(currentPoint)){
          return false;
        }
      }
      else{ // ship is horizontal
        Point currentPoint = new Point(p.getRow()+i, p.getColumn());
        if (!s.containsPoint(currentPoint)){
          return false;
        }
      }
    }
    return true;
  }
  // check to see if player sunk all ships
  public boolean checkGameStatus(Ship[] ships) {
  		boolean gameOver = true;
  		for(int i = 0; i < ships.length; i++) {
  			if(ships[i].getShipLength() != ships[i].hitCount()) {
  				gameOver = false;
  			}
  		}
  		if(gameOver) {
  			System.out.println("You win, you sunk all ship!");
  			return false;
  		}
  		// return true to keep game going
  		return true;
  }

  // return random point on board as the start of ship
  public static Point getRandomStartPoint(){
    Random rand = new Random();
    int x = rand.nextInt(10);
    int y = rand.nextInt(10);
    return new Point(x,y);
  }

    	// return random length for ship length
  public static int getRandomShipLength(){
    Random rand = new Random();
    return rand.nextInt(3) + 2;
  }

  // generate random orientation for ship
  public static boolean getOrientation(){
    Random rand = new Random();
    boolean vertical = true;
      
    if (rand.nextInt(2) == 1){
      vertical = false;
    }
    return vertical;
  }
}
