public class Point{
  private int row;
  private int column;
  private boolean isShip = false;
  private boolean isHit;
  private Point p;
  protected Board board;



  public Point(int r, int c){
    row = r;
    column = c;
  }

  public Point(int r, int c, Board b){
    row = r;
    column = c;
    board = b;
  }

  public int getRow(){
    return row;
  }

  public int getColumn(){
    return column;
  }

  public char displayCharacter(){
	  if(isShip) {
		  if(isHit) {
			  return 'x';
		  }
	  }
	  else if(!isShip){
		  if(isHit) {
			  return '.';
		  }
	  }
	  return '~';
  }

  public void setIsShip(boolean value){
     isShip = value;
  }

  public boolean getIsShip(){
    return isShip;
  }
  
  public void setIsHit(boolean value) {
	  isHit = value;
  }
  
  public boolean getIsHit() {
	  return isHit;
  }
  
  // determines status on grid based on shot fired
  public Point determineNextTurn(Point shot) {
	Point next = new Point(getRow(), getColumn(), board);
	  // if current point is a ship
		if(getIsShip()) {
			if(shot.getRow() == getRow() && shot.getColumn() == getColumn()) {
				  setIsHit(true);
			}
		}
	  else {
		  
		  if(shot.getRow() == getColumn() && shot.getColumn() == getRow()) {
			 setIsHit(true);
		  }
	  }
	  return next;
  }
}
  


