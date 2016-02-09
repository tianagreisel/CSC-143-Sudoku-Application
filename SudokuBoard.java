package csc143.sudoku;

import javax.swing.*;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.FocusListener;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.awt.Toolkit;
/**
 * 
 * Draws a basic board for a Sudoku game, indicating the individual rows and columns.
 * 
 * @author Tiana Greisel
 * @version Homework Assignment 6: SudokuView
 *
 */
public class SudokuBoard extends JComponent implements SelectedCell {

	// number of rows of SudokuBoard
	private int row;

	//number of columns of SudokuBoard
	private int column;

	//size of SudokuBord (rows * columns)
	private int size;

	//color of cells (color filled with)
	private Color fillColor;

	//Color white
	private Color white = Color.WHITE;

	//Color Gainsboro (gray)
	private Color Gainsboro = new Color(220, 220, 220);

	//boolean representing if regions/cells of board are gray or not
	private boolean isGray;

	//two-dimensional array representing SudokuBoard and cells that make up board
	public SudokuSquare[][] squares;

	//SudokuBase object retaining information about SudokuBoard and game being played
	private SudokuBase base;
	
	

	/**
	 * Takes a SudokuBase as a parameter. From the SudokuBase object, the size and arrangement of the 
	 * cells and regions is determined. Sets the preferred size of the SudokuBoard component based on 
	 * the size of the board.
	 * 
	 * @param b SudokuBase  Determines the size of the board (rows and columns) and holds information
	 * about its relative state.
	 */
	public SudokuBoard(SudokuBase b) {

		//sets the number of rows of Sudoku Board
		row = b.getRows();

		//sets the number of Columns of Sudoku Board
		column = b.getColumns();

		//sets the size (columns * rows) of the Sudoku Board
		size = b.getSize();

		//sets the SudokuBase object retaining information of game and board
		base = b;

		//sets the PreferredSize of the board 
		setPreferredSize(new Dimension((4 + (52 * size)), (4 + (52 * size))));

		//creates two-dimensional array to hold cells that make up SudokuBoard
		squares = new SudokuSquare[row * column][row * column];

		//color counter (if even columns must change at end of column)
		int colorCounter = 1;

		//x, y coordinates of regions of board (outer for-loop)
		int x = 3 ;
		int y = 3;

		//x, y coordinates of each of the cells in the regions of the board (inner for-loop)
		int xcell = x + 1;
		int ycell = y + 1;

		//iterates over the number of regions in the board (equal to row * column)
		for(int i = 0; i < row * column; i++){

			//starts the regions/cells to always be white in first region of board
			if(i == 0){
				isGray = false;
			}

			//if isGray is false, set the color of regions and cells of board to be white
			if(isGray == false){



				//set color to fill cells of board to white
				fillColor = white;
			}

			//if isGray is not false
			else{


				//set color to fill cells of board to gray
				fillColor = Gainsboro;
			}

			//iterates over the cells of each region of the board
			for(int j= 0; j < row * column; j++){

				//create a new SudokuSquare object representing a cell of SudokuBoard
				SudokuSquare cell = new SudokuSquare(xcell, ycell, 50, 50);

				//add the cell to the board
				add(cell);

				//add the cell to the two-dimensional array representing the SudokuBoard
				squares[ycell/52][xcell/52] = cell;

				//increment xcell over by one cell width
				xcell += 52;

				//when at the end of a region, cells start at next row to add in counter-clockwise direction to region
				if(xcell >= ( (x + (52 * column)) - 3)){

					xcell = x + 1;
					ycell+=  52;

				}

			}


			//if rows are even and at end of board, switch colors to start next region below previous
			if(row % 2 == 0 && colorCounter == row){
				isGray = !isGray;
				colorCounter = 0;

			}

			//switch conditional for colors of regions/cells so alternates every region	
			isGray = !isGray;

			//increment colorCounter    
			colorCounter++;


			//increment x and y to next region of board
			x += 52 * column;

			//when x is at the end of the board, go down to next region of board below previous regions
			if(x >= ((52 * column) * row) - 2){
				x = 3;

				y += 52 * row;
			}

			//x, y values of cells always 1 pixel greater than x, y values of board regions
			ycell = y + 1;
			xcell = x + 1;
		}
	}

	/**
	 * Draws the SudokuBoard indicating the cells and regions of the board.
	 * 
	 * @param Graphics g  Allows drawing onto components (SudokuBoard). 
	 * 
	 */
	public void paintComponent(Graphics g){


		super.paintComponent(g);

		//sets up main white rectangle that makes up SudokuBoard 
		g.setColor(Color.WHITE);
		g.fillRect(0,0, (4 + (52 * (row * column))),(4 + (52 * (row * column))));

		//draws a black border around the main SudokuBoard
		g.setColor(Color.BLACK);
		g.drawRect(0,0, (4 + (52 * (row * column))), (4 + (52 * (row * column))));


		//color counter (if even columns must change at end of column)
		int colorCounter = 1;

		//x, y coordinates of regions of board (outer for-loop)
		int x = 3 ;
		int y = 3;

		//iterates over the number of regions in the board (equal to row * column)
		for(int i = 0; i < row * column; i++){

			//starts the regions/cells to always be white in first region of board
			if(i == 0){
				isGray = false;
			}

			//if isGray is false, set the color of regions and cells of board to be white
			if(isGray == false){

				//draws a white rectangle representing a region of the board 
				g.setColor(white);
				g.fillRect(x, y, 52 * column, 52 * row);

				//set color to fill cells of board to white
				fillColor = white;
			}

			//if isGray is not false
			else{
				//draws a gray rectangle representing a region of the board
				g.setColor(Gainsboro);
				g.fillRect(x, y, 52 * column, 52 * row);

				//set color to fill cells of board to gray
				fillColor = Gainsboro;
			}

			//if rows are even and at end of board, switch colors to start next region below previous
			if(row % 2 == 0 && colorCounter == row){
				isGray = !isGray;
				colorCounter = 0;

			}

			//switch conditional for colors of regions/cells so alternates every region	
			isGray = !isGray;

			//increment colorCounter    
			colorCounter++;


			//increment x and y to next region of board
			x += 52 * column;

			//when x is at the end of the board, go down to next region of board below previous regions
			if(x >= ((52 * column) * row) - 2){
				x = 3;

				y += 52 * row;
			}
		}

		//sets default of selected cell to be row and column 0
		setSelected(0,0);
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
		return base;

	}
	

	
	
	/**
	 * A square object representing an individual cell of a Sudoku Board.
	 * 
	 * @author Tiana Greisel
	 * @version Homework Assignment 2:  Sudoku Board
	 *
	 */
	public class SudokuSquare extends Cell{

		//x,y coordinates SudokuSquare drawn from
		public int x;
		public int y;

		//width and height of SudokuSquare
		public int width;
		public int height;

		//holds original color of SudokuSquare (when loses focus, returns to this color)
		public Color originalColor;

		//is SudokuSquare active/ selected cell
		boolean isSelectedCell;

		//two dimensional array representing cells in SudokuBoard
		SudokuSquare[][] Squares;

		//for keyListener
		int row = 0;
		int column = 0;

		//is cell given value or not (for SudokuView)
		//boolean isGivenValue;
		
		//value on cell, to be drawn by drawSymbol
		//int value;


		/**
		 * 
		 * Constructs a SudokuSquare cell in a SudokuBoard.  Takes x, y coordinates for the upper
		 * left corner of the square and width and height of the square as parameters.
		 * 
		 * @param x  x coordinates of upper left of square
		 * @param y  y coordinates of upper left of square
		 * @param width   width of square
		 * @param height  height of square
		 */
		public SudokuSquare(int x, int y, int width, int height){

			//sets x, y coordinate values of SudokuSquare
			this.x = x;
			this.y = y;

			//sets width and height of SudokuSquare
			this.width = width;
			this.height = height;

			//draws a black border around component that makes up square
			setBorder(BorderFactory.createLineBorder(Color.BLACK));

			//sets location of square
			setLocation(x, y);

			//sets size of square
			setSize(width, height);

			//sets color of square
			setBackground(fillColor);

			//sets original color of square to be its initial color
			originalColor = fillColor;

			//makes reference to two-dimensional array of cells of SudokuBoard
			this.Squares = squares;
			
		
			
			//register to receive notification of mouse events
			addMouseListener(new MouseAdapter(){

				/**
				 * Method invoked when a mouse clicks on the window.  Gives focuse to the SudokuSquare object
				 * that is clicked on.
				 * 
				 * @param  MouseEvent e   An event which indicates that a mouse action occurred in a component.
				 */
				public void mouseClicked(MouseEvent e) {
					requestFocusInWindow();
				}
			});

			//register to receive notification of focus
			addFocusListener(new FocusListener() {

				Color c = getBackground(); 


				/**
				 * Invoked when SudokuSquare has lost focus.  Sets the color of
				 * SudokuSquare to its original color before becoming active.
				 * 
				 * @param FocusEvent e  An event which indicates that a Component has gained or lost the input focus.
				 */
				public void focusLost(FocusEvent e) {
					setBackground(c);
				}

				/**
				 * Invoked when SudokuSquare has gained focus.  Sets the background 
				 * of SudokuSquare to yellow.
				 *
				 * @param FocusEvent e  An event which indicates that a Component has gained or lost the input focus.
				 */
				public void focusGained(FocusEvent e) {
					setBackground(Color.yellow);
					isSelectedCell = true;
				}

			});

			//register to receive notification of key events
			addKeyListener(new KeyAdapter(){

				/**
				 * This method sets the row and column instance fields to the
				 * current row and column number of the focused cell.
				 * 
				 */
				public void getFocusedSquare(){

					for(int i = 0; i < Squares.length; i++){

						for(int j = 0; j < Squares[0].length; j++){
							if(Squares[i][j].isSelectedCell){

								row = i;
								column = j;

								//current cell will lose focus
								Squares[i][j].isSelectedCell = false;
							}
						}
					}
				}

				/**
				 * Makes a beep sound if keyboard move is outside of the bounds of the
				 * SudokuBoard.
				 * 
				 */
				public void beep(){

					if(row < 0 || row >= size || column < 0 || column >= size){
						Toolkit.getDefaultToolkit().beep();
					}
				}

				/**
				 * This method is invoked when a key is pressed on the keyboard.  The 
				 * method responds to the up, down, left, and right arrow.  Relative to
				 * the currently selected cell, this method sets focus to the cell that is
				 * to the right, left, up, or down according to the keys being pressed.  If the
				 * key pressed moves outside the bounds of the SudokuBoard, a beep sound is made.
				 * 
				 * @param KeyEvent e Object that holds information of KeyEvent
				 */
				public void keyPressed(KeyEvent e){

					//get keycode of key event
					int keyCode = e.getKeyCode();

					//Left Arrow
					if(keyCode == 37){

						//find currently focused cell
						getFocusedSquare();

						//move to the left
						column--;

						//check to make sure row and column not off of Board, if so beep
						beep();

						//give cell to the left focus
						Squares[row][column].requestFocusInWindow();

					}

					//Up Arrow
					else if(keyCode == 38){

						getFocusedSquare();
						row--;
						beep();
						Squares[row][column].requestFocusInWindow();

					}

					//Right Arrow
					else if(keyCode == 39){

						getFocusedSquare();
						column++;
						beep();
						Squares[row][column].requestFocusInWindow();
					}

					//Down Arrow
					else if(keyCode == 40){

						getFocusedSquare();
						row++;
						beep();
						Squares[row][column].requestFocusInWindow();
					}
				}	
			});
		}
		
//
		
		public void paintComponent(Graphics g){
			
			super.paintComponent(g);
		}
		//
	}	

	/**
	 * Set the selected cell to the given row and column.
	 * 
	 * @param row The indicated row  (0 <= row < this.size
	 * @param col The indicated column (0 <= column
	 * @throw IllegalArgumentException (parameters must be 0 <= row < this.size, 0 <= column < this.size)
	 */
	public void setSelected(int row, int col){

		//if row, col parameters less than 0 or greater than size of board throw an exception
		if(row < 0 || row > this.size || column < 0 || column > this.size){

			throw new IllegalArgumentException("Unacceptable row or column value");

		}

		else{

			//change isSelectedCell variable to true because it now is the selected cell
			squares[row][col].isSelectedCell = true;

			//gives focus to the specified SudokuSquare cell
			squares[row][col].requestFocusInWindow();

		}
	}


	/**
	 * Retrieve the row of the currently selected cell.
	 * 
	 * @return The row in which the selected cell is located.
	 */
	public int getSelectedRow(){


		int row = 0;

		//iterate through all SudokuSquares in two-dimensional array and find SudokuSquare that is active
		for(int i = 0; i < squares.length; i++){

			SudokuSquare[] mainArray = squares[i];

			for(int j = 0; j < mainArray.length; j++){

				SudokuSquare cell = mainArray[j];

				//if cell is the selected cell
				if(cell.isSelectedCell){

					//row is equal to the index value of cell in two-dimensional array	
					row = i;
				} 

			}
		}
		//return row number selected cell is at
		return row;
	}

	/**
	 * Retrieve the column of the currently selected cell.
	 * 
	 * @return The column in which the selected cell is located.
	 */
	public int getSelectedColumn(){

		int column = 0;

		//set cell to null so if no selected cell doesn't give a null pointer exception
		SudokuSquare cell = null;

		//iterate through all SudokuSquares in two-dimensional array and find SudokuSquare that is active
		for(int i = 0; i < squares.length; i++){

			SudokuSquare[] mainArray = squares[i];

			for(int j = 0; j < mainArray.length; j++){

				//grab each cell in two-dimensional array
				cell = mainArray[j];

				//if cell is the selected cell
				if(cell.isSelectedCell){

					//column number is equal to index value of column number of selected cell in 2D array
					column = j;

					//set boolean instance field to false so not still active cell 
					cell.isSelectedCell = false;

				} 
			}
		}
		//return column number selected cell is at
		return column;
	}
	
}	