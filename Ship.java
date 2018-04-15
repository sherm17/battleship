/*
 Your game should store ships in objects of type Ship.
 This ship class should keep two ArrayLists of points:
 one to store the points that the ship covers, and one
 to store the points that have been “hit” by the player.
*/
import java.util.*;

public class Ship{

  private boolean isVertical;
  private int length;
  private Point origin;

  private Point shipPoints[];
  List <Point> shipHitPoints = new ArrayList(length);

  
  // constructor
  public Ship(Point origin, boolean isVertical, int length){

    this.isVertical = isVertical;
    this.origin = origin;
    this.length = length;
    shipPoints = new Point[length];
  }

  // generate array of ship points
  public void createShipPointArrays(){
    for (int i = 0; i < length; i++){
      if (isVertical){
        shipPoints[i] = new Point(origin.getRow(), origin.getColumn()+i);
      }
      else{
        shipPoints[i] = new Point(origin.getRow()+i, origin.getColumn());
     }
        shipPoints[i].setIsShip(true);
    }
  }

  public Point getOrigin(){
    return origin;
  }
  public Point[] getShipPoints(){
    return shipPoints;
  }

  public int getShipLength(){
    return length;
  }

  public boolean isVertical(){
    return isVertical;
  }

  // returns true of ship points is on board, false otherwise
  boolean containsPoint(Point p){
    if (p.getRow() < 10 && p.getRow() >= 0 && p.getColumn() < 10 && p.getColumn() >= 0){
    		return true;
    }
    return false;
  }
  
  // checks for collision between ships
  boolean collidesWith(Ship s){
    // check each point and if they are equal than there
    // is a collision
    Point temp[] = s.getShipPoints();
    for (int i = 0; i < length; i++){
      for( int j = 0; j < s.length; j++ ){
        if ( shipPoints[i].getRow() == temp[j].getRow() &&
        shipPoints[i].getColumn() == temp[j].getColumn() ){
          //System.out.println("The two ships collide");
          return true;
        }
      }
    }
    return false;
  }


   // fires at ship
   void shotFiredAtPoint(Point p){
	   if(isHitAtPoint(p)) {
		   shipHitPoints.add(p);   
	   }
	   else {
	   }
   }

   // returns true if ship got hit, false otherwise
   boolean isHitAtPoint(Point p){
	   for(int i = 0; i < shipPoints.length; i++) {
		   if(shipPoints[i].getRow() == p.getRow() && shipPoints[i].getColumn() == p.getColumn()) {
			   return true;
		   }
	   }
	   return false;  
   }


   // return number of hits that ship received
   int hitCount(){
	   return shipHitPoints.size();
   }
   
   // checks to see if a ship has sunk
   void sunkStatus(int numOfShips) {
  	   if( getShipLength()  == hitCount()) {
  	  	 	 numOfShips--;
  	  	 	 System.out.println("You have sunked a ship with length " + getShipLength());
  	  	 	 System.out.println(numOfShips + " ships left on the board");
  	   }  	 
   }
   
}
