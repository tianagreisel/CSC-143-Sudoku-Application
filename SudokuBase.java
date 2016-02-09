package csc143.sudoku;

/**
 * 
 * This abstract class holds information about a Sudoku Board and sudoku game being
 * played.  The rows, columns, size, and arrangement of the regions and cells of the 
 * SudokuBoard are held in the instance fields.  As is data about the State of the game, 
 * i.e. if all the rows are filled, etc.
 * 
 * 
 * @author Tiana Greisel
 * @version  Homework Assignment 2:  Sudoku Board
 *
 */
public abstract class SudokuBase extends java.util.Observable {
    
	//set value of the number of rows making up the Sudoku Board
    private final int rows;
    
    //set value of the number of columns making up the Sudoku Board
    private final int columns;
    
    //set value of the number of rows * columns making up the Sudoku Board
    private final int size;
    
    //State of the board components
    public enum State {COMPLETE, INCOMPLETE, ERROR};
    
    
    /**
     * Takes as parameters the number of rows and columns the Sudoku Board
     * will be made up of and sets the values into private final instance
     * fields that cannot be changed for the instance of the object.  It also
     * sets the size of the SudokuBase object, which is equal to the rows * columns.
     * 
     * @param layoutRows  Number of rows Sudoku Board will be made up of
     * @param layoutColumns  Number of columns Sudoku Board will be made up of
     */
    public SudokuBase(int layoutRows, int layoutColumns) {
    	
    	//sets number of rows making up Sudoku Board
        rows = layoutRows;
        
        //sets number of columns making up Sudoku Board
        columns = layoutColumns;
        
        //sets size (columns * rows) making up Sudoku Board
        size = columns * rows;
        
    }
    
    /**
     * Returns the number of rows that make up the Sudoku Board this SudokuBase object
     * is retaining the data for (the game).
     * 
     * @return  rows number of rows that make up Sudoku Board
     */
    public int getRows() {
    	
        return rows;
        
    }
    
    
    /**
     * Returns the number of columns that make up the Sudoku Board this SudokuBase object
     * is retaining the data for (the game).
     * 
     * @return columns number of columns that make up Sudoku Board
     */
    public int getColumns() {
    	
        return columns;
        
    }
    
    
    /**
     * Returns the rows * columns or overall size of the Sudoku Board this SudokuBase object 
     * is retaining the data for (the game).
     * 
     * 
     * @return size rows * columns of Sudoku Board
     */
    public int getSize() {
    	
        return size;
        
    }
    
    
    /**
     * Takes as a parameter a row number and a column number of a Sudoku Board and
     * returns the value at that particular row and column.
     * 
     * @param row  row number of a cell in a Sudoku Board
     * @param col  column number of a cell in a Sudoku Board
     * @return  integer value of the number at the given row and column number of Sudoku Board
     */
    public abstract int getValue(int row, int col);
    
    
    /**
     * Takes a integer value as a parameter and sets the cell in the Sudoku Board at
     * the given row and column number to that value.
     * 
     * @param row row number of a cell in a Sudoku Board
     * @param col column number of a cell in a Sudoku Board
     * @param value integer value of number to be placed at the given row and column number
     */
    public abstract void setValue(int row, int col, int value);
    
    
    /**
     * Takes a given row and column number of a cell of a SudokuBoard and return
     * if the value at that cell is a given number that can't be changed.
     * 
     * @param row row number of a cell in a Sudoku Board
     * @param col column number of a cell in a Sudoku Board
     * @return boolean  if the value at row and column number is a set, given value of the game
     */
    public abstract boolean isGiven(int row, int col);
    
    
    /**
     * Sets the values in the board to be the given values that can't be 
     * changed in a Sudoku Game on the board.
     *
     */
    public abstract void fixGivens();
    
    
    /**
     * Takes an integer value as a parameter and returns the State type value
     * (COMPLETE, INCOMPLETE, ERROR) associated with the integer value.  Checks
     * the State of the rows of the Sudoku board to see if all the values have 
     * been filled in the rows or not and if there is an error in the values.
     * 
     * @param n integer value representing enum State type value
     * @return  returns a State type (COMPLETE, INCOMPLETE, ERROR)
     */
    public abstract State getRowState(int n);
    

    /**
     * Takes an integer value as a parameter and returns the State type value
     * (COMPLETE, INCOMPLETE, ERROR) associated with the integer value.  Checks
     * the State of the columns of the Sudoku board to see if all the values have 
     * been filled in the columns or not and if there is an error in the values.
     * 
     * @param n integer value representing enum State type value
     * @return  returns a State type (COMPLETE, INCOMPLETE, ERROR)
     */
    public abstract State getColumnState(int n);
    

    /**
     * Takes an integer value as a parameter and returns the State type value
     * (COMPLETE, INCOMPLETE, ERROR) associated with the integer value.  Checks
     * the State of the regions of the Sudoku board to see if all the values have 
     * been filled in the regions or not and if there is an error in the values.
     * 
     * @param n integer value representing enum State type value
     * @return  returns a State type (COMPLETE, INCOMPLETE, ERROR)
     */
    public abstract State getRegionState(int n);
    
}
