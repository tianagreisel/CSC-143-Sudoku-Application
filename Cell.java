package csc143.sudoku;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.*;

import java.awt.*;

/**
 * This class represents the graphical value symbols drawn on a SudokuBoard 
 * cell.
 * 
 * @author Tiana Greisel
 *
 */
public class Cell extends JPanel implements SymbolRenderer{
	
	int value;
	boolean isGivenValue;
	
	/**
	 * Constructs a Cell and sets the preferred size of the component.
	 *  
	 */
	public Cell(){
		
		setPreferredSize(new Dimension(50, 50));
		
		
	}

	/**
	 * Draws component. Invokes drawSymbol method which determines which
	 * symbol to draw on the Symbol component based on its value parameter.
	 * 
	 * @param g Graphics Allows drawing onto components
	 */
	public void paintComponent(Graphics g){
		
		super.paintComponent(g);
		
		//set Color of pen stroke
		if(isGivenValue){
			
			g.setColor(Color.MAGENTA);
			
		}
		else{
			g.setColor(Color.BLACK);
		}

		drawSymbol(g, 5, 5, value);	
	}
	
	/**
	 * Renders a single symbol for the Sudoku game.
	 * @param x The x-coordinate for the upper-left corner 
	 * of the symbol area (40x40 pixels)
	 * @param y The y-coordinate for the upper-left corner 
	 * of the symbol area (40x40 pixels)
	 * @param g The Graphics object used to draw the symbol
	 * @param value The value to be drawn, between 0 and 12,
	 * inclusive
	 */
	public void drawSymbol(Graphics g, int x, int y, int value){

		//cast g to Graphics2D object to make a thicker pen stroke
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(7));

		//based on numeric value of value, draw appropriate symbol

		if(value == 0){
			//do nothing
		}

		//draw number One
		else if(value == 1){
			drawOne(g2, x, y);
		}

		//draw number Two
		else if(value == 2){
			drawTwo(g2, x, y);
		}

		//draw number Three
		else if (value == 3){

			drawThree(g2, x, y);
		}

		//draw number Four
		else if (value == 4){
			drawFour(g2, x, y);
		}

		//draw number Five
		else if(value == 5){
			drawFive(g2, x, y);
		}

		//draw number Six
		else if(value == 6){
			drawSix(g2, x, y);
		}

		//draw number Seven
		else if(value == 7){
			drawSeven(g2, x, y);
		}

		//draw number Eight
		else if(value == 8){
			drawEight(g2, x, y);
		}

		//draw number Nine
		else if(value == 9){
			drawNine(g2, x, y);
		}

		//draw number Ten
		else if(value == 10){
			drawTen(g2, x, y);

		}

		//draw number Eleven
		else if(value == 11){
			drawEleven(g2, x, y);
		}

		//draw number Twelve
		else if(value == 12){
			drawTwelve(g2, x, y);
		}	    	

	}



	/**
	 * Draws a graphical representation of the number One.
	 * 
	 * @param g Graphics Allows drawing onto components
	 * @param x x-coordinate of upper left hand corner of component 
	 * @param y y-coordinate of upper left hand corner of component 
	 */
	public void drawOne(Graphics g, int x, int y){

		g.drawLine(x + 40/2, y + 4, x + 40/2, y + 40 - 4);

	}

	/**
	 * Draws a graphical representation of the number Two.
	 * 
	 * @param g Graphics Allows drawing onto components
	 * @param x x-coordinate of upper left hand corner of component 
	 * @param y y-coordinate of upper left hand corner of component 
	 */
	public void drawTwo(Graphics g, int x, int y){

		int[] xlist = {x + 4, x + 40 - 4, x + 40 - 4, x + 4, x+ 4,x + 40 - 4 };
		int[] ylist = {y + 4, y + 4, y + (40/ 2), y + (40/ 2) , y + 40 - 4, y + 40 - 4 };
		g.drawPolyline(xlist, ylist, 6);
	}

	/**
	 * Draws a graphical representation of the number Three.
	 * 
	 * @param g  Graphics Allows drawing onto components
	 * @param x x-coordinate of upper left hand corner of component 
	 * @param y y-coordinate of upper left hand corner of component 
	 */
	public void drawThree(Graphics g, int x, int y){

		int[] xlist = {x + 4,x + 40 - 4, x + 40 - 4, x + 4 };
		int[] ylist = {y + 4, y + 4,y + 40 - 4, y + 40 - 4 };
		g.drawPolyline(xlist, ylist, 4);
		g.drawLine(x + 4, y + (40/ 2), x + 40 - 4, y + (40/ 2));
	}

	/**
	 * Draws a graphical representation of the number Four.
	 * 
	 * @param g Graphics Allows drawing onto components
	 * @param x x-coordinate of upper left hand corner of component 
	 * @param y y-coordinate of upper left hand corner of component 
	 */
	public void drawFour(Graphics g, int x, int y){

		int[] xlist = {x + 4, x + 4,x + 40 - 4 };
		int[] ylist = {y + 4, y + (40/ 2), y + (40/ 2) };
		g.drawPolyline(xlist, ylist, 3);
		g.drawLine(x + 40 - 4, y + 4, x + 40 - 4, y + 40 -4);
	}

	/**
	 * Draws a graphical representation of the number Five.
	 * 
	 * @param g  Graphics Allows drawing onto components
	 * @param x x-coordinate of upper left hand corner of component 
	 * @param y y-coordinate of upper left hand corner of component 
	 */
	public void drawFive(Graphics g, int x, int y){

		int[] xlist ={x + 40 - 4, x + 4, x + 4,  x + 40 - 4, x + 40 - 4, x + 4 };
		int[] ylist = {y + 4, y + 4, y + (40/ 2), y + (40/ 2), y + 40 - 4, y + 40 - 4};
		g.drawPolyline(xlist, ylist, 6);

	}

	/**
	 * Draws a graphical representation of the number Six.
	 * 
	 * @param g Graphics Allows drawing onto components
	 * @param x x-coordinate of upper left hand corner of component 
	 * @param y y-coordinate of upper left hand corner of component 
	 */
	public void drawSix(Graphics g, int x, int y){

		int[] xlist = {x + 40 - 4, x + 4, x + 4, x + 40 - 4, x + 40 - 4, x + 4};
		int[] ylist = {y + 4, y + 4, y + 40 - 4, y + 40 - 4, y + (40/ 2), y + (40/ 2)};
		g.drawPolyline(xlist, ylist, 6);

	}

	/**
	 * Draws a graphical representation of the number Seven.
	 * 
	 * @param g Graphics Allows drawing onto components
	 * @param x x-coordinate of upper left hand corner of component 
	 * @param y y-coordinate of upper left hand corner of component 
	 */
	public void drawSeven(Graphics g, int x, int y){

		int[] xlist = {x + 4, x + 40 - 4, x + 40 - 4};
		int[] ylist = {y + 4, y + 4, y + 40 -4};
		g.drawPolyline(xlist, ylist, 3);	
	}

	/**
	 * Draws a graphical representation of the number Eight.
	 * 
	 * @param g Graphics Allows drawing onto components
	 * @param x x-coordinate of upper left hand corner of component 
	 * @param y y-coordinate of upper left hand corner of component 
	 */
	public void drawEight(Graphics g, int x, int y){

		int[] xlist = {x + 4, x  + 40 - 4, x + 40 - 4, x + 4, x + 4};
		int[] ylist = {y + 4, y + 4, y + 40 - 4, y + 40 - 4, y + 4};
		g.drawPolyline(xlist, ylist, 5);
		g.drawLine(x + 4, y + (40/2), x + 40 - 4, y + (40/2));
	}

	/**
	 * Draws a graphical representation of the number Nine.
	 * 
	 * @param g Graphics Allows drawing onto components
	 * @param x x-coordinate of upper left hand corner of component 
	 * @param y y-coordinate of upper left hand corner of component 
	 */
	public void drawNine(Graphics g, int x, int y){

		int[] xlist = {x + 4, x + 4, x  + 40 - 4, x  + 40 - 4, x  + 4 };
		int[] ylist = {y + (40/2), y + 4,  y + 4, y + 40 - 4, y + 40 -4};
		g.drawPolyline(xlist, ylist, 5);
		g.drawLine(x + 4,y + (40/ 2) , x + 40 - 4, y + (40/ 2));    	
	}

	/**
	 * Draws a graphical representation of the number Ten.
	 * 
	 * @param g  Graphics Allows drawing onto components
	 * @param x x-coordinate of upper left hand corner of component 
	 * @param y y-coordinate of upper left hand corner of component 
	 */
	public void drawTen(Graphics g, int x, int y){

		//g.drawLine(x + 4, y + 4, x + 4, y + getHeight() - 4);

		//int[] xlist = {x + (getWidth()/2), x + getWidth() - 4, x + getWidth() - 4, x + (getWidth()/2), x + (getWidth()/2)};
		//int[] ylist = {y + 4, y + 4,y + getHeight() - 4, y + getHeight() - 4, y + 4 };
		//g.drawPolyline(xlist, ylist, 5);
		g.drawLine(x + 4, y + 4, x + 4, y + 40 - 4);

		int[] xlist = {x + (40/2), x + 40 - 4, x + 40 - 4, x + (40/2), x + (40/2)};
		int[] ylist = {y + 4, y + 4,y + 40 - 4, y + 40 - 4, y + 4 };
		g.drawPolyline(xlist, ylist, 5);
	}

	/**
	 * Draws a graphical representation of the number Eleven.
	 * 
	 * @param g Graphics Allows drawing onto components
	 * @param x x-coordinate of upper left hand corner of component 
	 * @param y y-coordinate of upper left hand corner of component 
	 */
	public void drawEleven(Graphics g, int x, int y){

		//g.drawLine(x + (getWidth()/4), y + 4, x + (getWidth()/4), y + getHeight() - 4);
		//g.drawLine(x + ((3 * getWidth())/4), y + 4, x + ((3 * getWidth())/4), y + getHeight() - 4);
		g.drawLine(x + (40/4), y + 4, x + (40/4), y + 40 - 4);
		g.drawLine(x + ((3 * 40)/4), y + 4, x + ((3 * 40)/4), y + 40 - 4);

	}

	/**
	 * Draws a graphical representation of the number Twelve.
	 * 
	 * @param g Graphics Allows drawing onto components
	 * @param x x-coordinate of upper left hand corner of component 
	 * @param y y-coordinate of upper left hand corner of component 
	 */
	public void drawTwelve(Graphics g, int x, int y){

		//g.drawLine(x + 4, y + 4, x + 4, y + getHeight() - 4);

		//int[] xlist = {x + (getWidth()/2), x + getWidth() - 4, x + getWidth() - 4, x + (getWidth()/2), x + (getWidth()/2), x + getWidth() - 4};
		//int[] ylist = {y + 4, y + 4, y + (getHeight() / 2), y + (getHeight() / 2), y + getHeight() - 4, y + getHeight() - 4 };
		//g.drawPolyline(xlist, ylist, 6);	
		g.drawLine(x + 4, y + 4, x + 4, y + 40 - 4);

		int[] xlist = {x + (40/2), x + 40 - 4, x + 40 - 4, x + (40/2), x + (40/2), x + 40 - 4};
		int[] ylist = {y + 4, y + 4, y + (40 / 2), y + (40 / 2), y + 40 - 4, y + 40 - 4 };
		g.drawPolyline(xlist, ylist, 6);	
	} 

}

