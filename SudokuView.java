package csc143.sudoku;


import java.awt.*;
import java.util.*;

/**
 * This class displays the SudokuBoard and it renders the cells in a square grid, with each cell 
 * displaying the corresponding value from the model. This class displays the values in the 
 * Sudoku Board game which are held within the SudokuBase object, which is taken as a parameter 
 * to the constructor.  This class is responsive to changes within the SudokuBase object, so as 
 * changes to values are made the SudokuView will repaint itself.  This class implements the Observer 
 * interface to be notified of changes to the Observable SudokuBase object.  Values within a cell that are
 * Given values are magenta and those supplied by the player are black.
 * 
 *  @author Tiana Greisel
 * @version Homework Assignment 6: SudokuView
 */
public class SudokuView extends SudokuBoard implements Observer, NumericSupport{

	//SudokuBase object
	SudokuBase b;

	/**
	 * Constructs a SudokuBoard by taking a SudokuBase object as a parameter.  This
	 * object holds information about a Sudoku Board and sudoku game being played.  The
	 * SudokuBase (model) is an Observable object and will notify the SudokuView of changes
	 * to the SudokuBase since it registers with this Observable in this constructor.  The instance
	 * field referencing the SudokuBase parameter is set here, as is a reference to the two-dimensional
	 * array holding the SudokuSquare objects that make up the cells of the SudokuBoard.
	 * 
	 * @param b SudokuBase object storing information about the Sudoku game being played
	 */
	public SudokuView(SudokuBase b){

		//invokes SudokuBoard constructor
		super(b);

		//sets value of instance field b to parameter
		this.b = b;

		//register with Observable object (SudokuBase b)
		this.b.addObserver(this);

		//iterate through my squares array representing cells in board and set their isGivenVaue to false
		//unless the value of isGiven of that row and column cell is a given value, it will be set to true
		for(int i = 0; i < b.getSize(); i++){

			for(int j = 0; j < b.getSize(); j++){

				squares[i][j].isGivenValue = false;

				if(b.isGiven(i,j)){

					squares[i][j].isGivenValue = true;
				}
			}
		}
	}


	/**
	 * Draws the sudoku board by invoking super paint component of sudoku board to get outline
	 * of board and then invokes addValues() method which will add the values within each cell
	 * onto the board by using the Symbol class and setting its color property to blue or
	 * black based on the number being a given value or not.  Also, gives focus to selected cell
	 * which makes the selected cell yellow.
	 * 
	 *  @param g Graphics Allows drawing onto components
	 */
	public void paintComponent(Graphics g){

		//invokes back of the board in SudokuBoard
		super.paintComponent(g);

		//adds values to board
		//addValues();
		
		//iterate over the cells on the sudoku board
				for(int i = 0; i < b.getSize(); i++){

					for(int j = 0; j < b.getSize(); j++){

						//get the value of the cell from the model, SudokuBase b
						int v = b.getValue(i, j);

						//if cell has a given value
						//if(squares[i][j].isGivenValue){

							//add the corresponding symbol and paint it blue
							//squares[i][j].add(new Symbol(v, Color.MAGENTA));
							squares[i][j].value = v;
							//squares[i][j].paintComponent(g);

						//}

						//if not a given value
						//else{

							//add the corresponding symbol and paint it black
							//squares[i][j].add(new Symbol(v, Color.BLACK));
							//squares[i][j].value = v;
							//squares[i][j].paintComponent(g);

						//}
					}	
					
				}		
		
		

		//iterates over cells in board and if isSelectedCell property is true, gives cell focus and turns it yellow
		for(int i = 0; i < b.getSize(); i++){

			for(int j = 0; j < b.getSize(); j++){

				if(squares[i][j].isSelectedCell){

					squares[i][j].requestFocusInWindow();
				}
			}
		}
	}

	/**
	 * This method adds the values onto the Sudoku Board.  It gets the values from the model, SudokuBase
	 * and based on the value for each cell of the SudokuBoard, it will check to see if the value is
	 * a given value or not and if it is a given value it will be painted in magenta and if it is a 
	 * player value it will be painted in black.
	 * 
	 */
	/*public void addValues(){

		//iterate over the cells on the sudoku board
		for(int i = 0; i < b.getSize(); i++){

			for(int j = 0; j < b.getSize(); j++){

				//get the value of the cell from the model, SudokuBase b
				int v = b.getValue(i, j);

				//if cell has a given value
				if(squares[i][j].isGivenValue){

					//add the corresponding symbol and paint it blue
					//squares[i][j].add(new Symbol(v, Color.MAGENTA));
					squares[i][j].value = v;
					squares[i][j].paintComponent(g);

				}

				//if not a given value
				else{

					//add the corresponding symbol and paint it black
					//squares[i][j].add(new Symbol(v, Color.BLACK));
					squares[i][j].value = v;
					squares[i][j].paintComponent(g);

				}
			}	 
		}		
	}*/

	/**
	 * Sets the output type: True for numeric output, False
	 * for symbol output.
	 * @param flag The desired output type
	 */
	public void setNumeric(boolean flag){

	}

	/**
	 * Retrieve the current output type, numeric or graphic
	 * @return True if numeric output, False if symbols are output
	 */
	public boolean showsNumeric(){
		return false;
	}


	/**
	 * This method is called whenever the observed object (SudokuBase) is changed. 
	 * An application calls an Observable object's notifyObservers method to have 
	 * all the object's observers notified of the change.  When the SudokuBase is changed
	 * this method will be called so the SudokuView knows there has been changes to
	 * the model and that it needs to update itself (repaint).  Specifically, checks each
	 * SudokuSquare cell and if it isGivenValue property has been changed to true, then the
	 * value was changed by the model and now the view knows to update this value when paintComponent
	 * called when view repaints itself.
	 * 
	 * @param obj java.util.Observable the observable object.
	 * @param o Object an argument passed to the notifyObservers method.
	 */
	public void update(Observable obj, Object o){

		//this.b =getBase();
		//iterates over cells on the board
		for(int i = 0; i < b.getSize(); i++){

			for(int j = 0; j < b.getSize(); j++){

				//if the cell is a given value
				if(b.isGiven(i, j)){

					//sets the isGivenValue property of the cell to true so painted black
					squares[i][j].isGivenValue = true;
				}
		
			}
		}
	}
	
	/**
	 * Returns SudokuBase object that SudokuBoard is based off of.  SudokuBase holds information
	 * about the state of the game as the game changes, like rows being complete and so forth.  
	 * Gives access to the SudokuBase object game is based on and its instance fields.
	 * 
	 * 
	 * @return base SudokuBase object 
	 */
	SudokuBase getBase() {
		return b;
	}

}