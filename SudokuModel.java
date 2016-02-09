package csc143.sudoku;

import java.util.ArrayList;
import java.util.Iterator;


/**
 * A subclass of SudokuCore, which implements storage for the values of a Sudoku game.  
 * This class takes the layout dimensions for a Sudoku board and passes it to the
 * superclass constructor SudokuCore which keeps track of the values of the Sudoku game.
 * SudokuModel checks the state of a Sudoku game, determining if a row, column, or region
 * is complete (having exactly one of each number 1 - size), incomplete (blanks but no errors), 
 * or error (duplicates of any number or numbers greater than 1 - size).  
 * 
 * @author Tiana Greisel
 * @version  Homework Assignment 3:  Sudoku Model
 */
public class SudokuModel extends SudokuCore {


	/**
	 * The constructor takes the layout dimensions for a Sudoku board. It passes these 
	 * dimensions to the superclass constructor, SudokuCore, which creates a private array, grid, 
	 * to store the values for the individual cells.
	 * 
	 * @param r number of rows in Sudoku board
	 * @param c number of columns Sudoku board
	 */
	public SudokuModel(int r, int c) {
		
		super(r, c);
		
	}

	
	/**
	 * This method creates an ArrayList of the integer values in the entered row (n)
	 * on the Sudoku board.  This method is meant to easily be used to create an
	 * Iterator in the checkState() method of this class, however the method does
	 * not return an Iterator so that the original ArrayList of row values can also
	 * be used if needed.
	 * 
	 * @param n row number creating an ArrayList of the values for
	 * @return ArrayList<Integer> of a row on the Sudoku Board
	 */
	private ArrayList<Integer> getRowIterator(int n){

		//ArrayList to hold values in row
		ArrayList<Integer> rowCheck = new ArrayList<Integer>(getSize());

		//iterate through all column values, keeping the row number the same(n) 
		for(int i = 0; i < getSize(); i++){

			//add values to rowCheck 
			rowCheck.add(getValue(n, i));

		}

		//return all the values in the row for the Iterator to iterate over
		return rowCheck;
	}

	/**
	 * This method creates an ArrayList of the integer values in the entered column (n)
	 * on the Sudoku board.  This method is meant to easily be used to create an
	 * Iterator in the checkState() method of this class, however the method does
	 * not return an Iterator so that the original ArrayList of column values can also
	 * be used if needed.
	 * 
	 * @param n column number creating an ArrayList of the values for
	 * @return ArrayList<Integer> of a column on the Sudoku Board
	 */
	private ArrayList<Integer> getColumnIterator(int n){

		//ArrayList to hold values in column
		ArrayList<Integer> columnCheck = new ArrayList<Integer>(getSize());

		//iterate through all rows, keeping column number the same(n)
		for(int i = 0; i < getSize(); i++){

			//add value to columnCheck (all values in column)
			columnCheck.add(getValue(i, n));

		}

		//return all values in the column for the Iterator to iterate over
		return columnCheck;

	}

	/**
	 * This method creates an ArrayList of the integer values in the entered region (n)
	 * on the Sudoku board.  This method is meant to easily be used to create an
	 * Iterator in the checkState() method of this class, however the method does
	 * not return an Iterator so that the original ArrayList of region values can also
	 * be used if needed.
	 * 
	 * @param n region number creating an ArrayList of the values for
	 * @return ArrayList<Integer> of a column on the Sudoku Board
	 */		
	private ArrayList<Integer> getRegionIterator(int n){

		//ArrayList to hold values in region to check the state of
		ArrayList<Integer> regionCheck = new ArrayList <Integer> (getSize());

		//row, col number of starting cell in each region
		int rowStart = ( n / getRows()) * getRows();
		int colStart = n % getRows() * getColumns();

		//add values in region to array regionCheck to check the state of
		for(int i = 0; i < getRows(); i++){

			for(int j = 0; j < getColumns(); j++){

				//add value to ArrayList regionCheck	
				regionCheck.add(getValue(rowStart + i, colStart + j));
				
			}
		}

		//returns ArrayList of all values in the region for the Iterator to iterator over
		return regionCheck;
	}		

	/**
	 * This method returns the state for the specified row, column, or region. It is complete 
	 * if it contains one instance of each of the values from 1 to size. It has an error if it 
	 * contains duplicates of any value, 1 to size. It is incomplete if there are blanks, 0, but no errors.
	 * 
	 * @param rowColumnRegionList ArrayList<Integer> of values in row, column, or region checking the state of
	 * @return the state of the row, column, or region.  Can be COMPLETE, INCOMPLETE, ERROR.
	 */
	private State checkState(ArrayList<Integer> rowColumnRegionList){

		//array holds all the values 1 - size to compare row, column, or region values to
		int[] completeNums = new int[getSize()];

		//set completeNums array to hold one of each value from 1 to size
		for(int i = 0; i < completeNums.length; i++){
			completeNums[i] = i + 1;

		}


		//create iterator to iterate over row, column, or Region list
		Iterator<Integer> it = rowColumnRegionList.iterator();


		//counter to see that value from 1 - size appears in the row, column, or region only once
		int completeCounter = 0;


		//while there is another value in the rowColumnRegionList
		while(it.hasNext()){

			//get next value in row, column, or region
			int value = it.next();


			//First, check that all value in row, column, or region are in the range of 1 - size, if not return ERROR
			if(value < 0 || value > getSize()){
				return State.ERROR;
			}


			//how many times value appears in row, column, or region
			int valueAppears = 0;


			//Check to make sure no duplicate values in row, column, or region
			for(int j = 0; j < rowColumnRegionList.size(); j++){

				//if value already appeared once, but isn't zero (not checking for non-zero duplicates)
				if(value == rowColumnRegionList.get(j) && value != 0){

					//increase valueAppears accumulator variable
					valueAppears++;

				}
			}

			//if value appeared more than once, return an ERROR
			if(valueAppears > 1){
				return State.ERROR;
			}



			//check to see if row, column, or region is complete (one instance of each number 1 - size)
			for(int k =  0; k < completeNums.length; k++){

				if(value == completeNums[k]){

					completeCounter++;

				}

			}

		}

		//if each value appears exactly once in column (completeCounter equals getSize())
		if(completeCounter == getSize()){

			return State.COMPLETE;
		}


		//if no errors and not complete (one of each # from 1 - size) and no duplicates of numbers, then incomplete
		return State.INCOMPLETE; 
	}

	/**
	 * This method takes a row number and returns the State of the row, being
	 * COMPLETE, INCOMPLETE, or ERROR.  It is complete if it contains one instance of 
	 * each of the values from 1 to size. It has an error if it contains duplicates of 
	 * any value, 1 to size. It is incomplete if there are blanks, 0, but no errors.
	 * 
	 * @param n row number checking the state of
	 */
	public State getRowState(int n){


		return checkState(getRowIterator(n));


	}	 

	/**
	 * This method takes a column number and returns the State of the row, being
	 * COMPLETE, INCOMPLETE, or ERROR.  It is complete if it contains one instance of 
	 * each of the values from 1 to size. It has an error if it contains duplicates of 
	 * any value, 1 to size. It is incomplete if there are blanks, 0, but no errors.
	 * 
	 * @param n column number checking the state of
	 */
	public State getColumnState(int n){

		return checkState(getColumnIterator(n));

	}		 

	/**
	 * This method takes a region number and returns the State of the row, being
	 * COMPLETE, INCOMPLETE, or ERROR.  It is complete if it contains one instance of 
	 * each of the values from 1 to size. It has an error if it contains duplicates of 
	 * any value, 1 to size. It is incomplete if there are blanks, 0, but no errors.
	 * 
	 * @param n region number checking the state of
	 */
	public State getRegionState(int n){

		return checkState(getRegionIterator(n));

	}

}


