package csc143.sudoku;

import javax.swing.*;
import csc143.sudoku.SudokuBase.State;
import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.awt.event.WindowEvent;
import java.awt.Toolkit;


/**
 * This class extends JPanel and sets up the control panel of a Sudoku Game window.
 * The class adds what appears to be buttons that represent the different symbols the
 * board can take on, based on its size, and from these "button" the user can click
 * on the button and the selected cell on the board will change to that value.  If the user
 * tries to set a fixed givens cell, a beep will sound.  There is a preset game in Normal
 * Play Mode and a Set-Up mode where the user can chose the values and fix them to givens
 * by clicking the "set givens" button.  This class also adds indicator buttons which show
 * the state of the rows, columns, and regions.  Yellow marks an incomplete state, red an 
 * error state, and green a column state.  The constructor takes a view as its parameter
 * and constructs a sudoku board of the appropriate size from that view object and determines
 * the appropriate number of buttons for the controller.  
 * 
 * 
 * @author Tiana Greisel
 * @version Homework Assignment 7: Sudoku, Controller and Integration
 *
 */
public class SudokuController extends JPanel{

	//space around components in the window
	private static final int space = 20;

	//the size of a cell on the SudokuBoard
	private static final int cellSize = 52;

	//size of board (rows * columns)
	private static int size;

	//SudokuView object that is the SudokuBoard
	static SudokuView v;

	//row indicators - holds panel objects making up rows in indicator buttons
	JPanel[] rowIndicator;

	//column indicators - holds panel objects making up columns in indicator buttons
	JPanel[] columnIndicator;

	//region indicators - holds panel objects making up regions in indicator buttons
	JPanel[] regionIndicator;

	//main window holding game
	static JFrame win;

	//user input dialog box
	static JFrame winx;

	//main panel holder controller and board
	static JPanel panel;

	//option menu
	static JMenuBar menuBar;

	//panel holding the JLabel labeling the mode game is in
	static JPanel modePanel;

	//label of text for mode in
	static JLabel mode;

	//button that allows user to set given values
	static JButton setGivens;

	private Color Gainsboro = new Color(220, 220, 220);

	//panel holding ControllerButtons
	private JPanel buttons;

	//main panel of the controller
	private JPanel controllerDisplay;

	//panel holding "set givens" and "create new game" button
	private JPanel tools;


	//text field to enter rows
	static JTextField rows;

	//string entered by user in textfield 
	static String rowInputString;

	//string converted to int entered by user
	static int rowInput;

	//text field to enter columns
	static JTextField columns;

	//string entered by user in textfield 
	static String columnInputString;

	//string converted to int entered by user
	static int columnInput;


	/**
	 * Constructs a SudokuController.  Takes a SudokuView object as
	 * a parameter which the controller uses to determine the
	 * corrects cells to update and so forth.  Constructor adds view 
	 * within its component and uses the associated model (SudokuBase)
	 * to set the size instance field, which is rows times columns.  
	 * Also instantiates row, column, and region arrays that hold the
	 * associated row, column, and region components in the indicator
	 * buttons that show the state of the associated row, column, and 
	 * regions.
	 * 
	 * @param v SudokuView displays SudokuBoard for game
	 */
	public SudokuController(SudokuView v){

		//SudokuView representing SudokuBoard
		this.v = v;

		//add view to SudokuController panel
		add(v);

		//gets SudokuBase object from model to get size of board from
		SudokuBase b = v.getBase();

		//sets size equal to number of rows and columns (get from view)
		size = b.getSize();

		//initial row, column, and region indicator arrays
		rowIndicator = new JPanel[size];
		columnIndicator = new JPanel[size];
		regionIndicator = new JPanel[size];
	}


	/**
	 * This method is used to add the main controller panel to the associated window displaying 
	 * the controller object (which displays the view).  This method creates a JPanel and adds
	 * the buttons which represent the various values the user can select and add to the board.
	 * This method also adds the indicator buttons which show the row, column, and region states
	 * of the values currently on the board.  This method returns a JPanel which is meant to be 
	 * used to add the JPanel to the main controller panel.  This method also adds the set givens
	 * button which will fix the values the user has currently entered on the board and a new game
	 * button which will give the user the option of entering an associated row and column number
	 * to create a new game in set-up mode.
	 * 
	 * 
	 * 
	 * @return JPanel controllerDisplay which holds controller, indicator, and set givens buttons
	 */
	public JPanel addControllerPanel(){

		//get SudokuBase object from view
		SudokuBase b = v.getBase();

		//set up main controller display panel
		controllerDisplay = new JPanel();
		controllerDisplay.setPreferredSize(new Dimension(cellSize * size, cellSize * size));
		controllerDisplay.setLayout(new BorderLayout());

		//panel holding ControllerButtons
		buttons = new JPanel();


		//set preferred size of panel holding controller buttons
		buttons.setPreferredSize(new Dimension(cellSize * size, ((cellSize * size)/2) + 10));

		//add all sudoku cell value buttons (ControllerButton)
		for(int i = 0; i <= size; i++ ){


			ControllerButton c = new ControllerButton();
			c.value = i;
			buttons.add(c);



		}

		//add buttons to controller display
		controllerDisplay.add(buttons, BorderLayout.NORTH);

		//make a component to be graphical indicator of row state
		JPanel rowStateIndicator = new JPanel();
		rowStateIndicator.setLayout(new GridLayout(size, 0));

		rowStateIndicator.setPreferredSize(new Dimension(10 * size, 10 * size));

		rowStateIndicator.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		for(int i = 0; i < size; i++){

			JPanel row = new JPanel();
			row.setPreferredSize(new Dimension(10 * size, 10));

			//incomplete state yello
			if(b.getRowState(i).equals(State.INCOMPLETE)){

				row.setBackground(Color.yellow);

			}

			//error state red
			else if(b.getRowState(i).equals(State.ERROR)){

				row.setBackground(Color.red);
			}

			//complete state green
			else if(b.getRowState(i).equals(State.COMPLETE)){

				row.setBackground(Color.green);
			}

			//add row to rowIndicator array
			rowIndicator[i] = row;

			rowStateIndicator.add(row);
		}

		//make a component to be graphical indicator of column state
		JPanel columnStateIndicator = new JPanel();

		columnStateIndicator.setLayout(new GridLayout(0, size));

		columnStateIndicator.setPreferredSize(new Dimension(10 * size, 10 * size));

		columnStateIndicator.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		for(int i = 0; i < size; i++){

			JPanel column = new JPanel();
			column.setPreferredSize(new Dimension(10, 10 * size));

			//incomplete state yellow
			if(b.getColumnState(i).equals(State.INCOMPLETE)){
				column.setBackground(Color.YELLOW);

			}

			//error state red
			else if(b.getColumnState(i).equals(State.ERROR)){

				column.setBackground(Color.RED);
			}
			//complete state green
			else if(b.getColumnState(i).equals(State.COMPLETE)){

				column.setBackground(Color.GREEN);
			}

			//add row to rowIndicator array
			columnIndicator[i] =column;

			columnStateIndicator.add(column);	
		}

		//make a graphical component to check the region state
		JPanel regionStateIndicator = new JPanel();
		regionStateIndicator.setLayout(new GridLayout(b.getColumns(), b.getRows()));
		regionStateIndicator.setPreferredSize(new Dimension(10 * size, 10 * size));

		regionStateIndicator.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		for(int i = 0; i < size; i++){

			JPanel region = new JPanel();
			region.setPreferredSize(new Dimension(10 * b.getColumns(), 10 * b.getRows()));

			//incomplete state yellow
			if(b.getRegionState(i).equals(State.INCOMPLETE)){
				region.setBackground(Color.YELLOW);

			}

			//error state red
			else if(b.getRegionState(i).equals(State.ERROR)){

				region.setBackground(Color.RED);
			}

			//complete state green
			else if(b.getRegionState(i).equals(State.COMPLETE)){

				region.setBackground(Color.GREEN);
			}

			//add row to rowIndicator array
			regionIndicator[i] =region;

			regionStateIndicator.add(region);	
		}

		//panel to hold indicator buttons
		JPanel indicators = new JPanel();

		//add indicator buttons to indicator panel
		indicators.add(rowStateIndicator);
		indicators.add(columnStateIndicator);
		indicators.add(regionStateIndicator);

		//add indicator panel to controller panel
		controllerDisplay.add(indicators);

		//Set-Up mode set givens and cancel button panel
		JPanel setUpModeTools = new JPanel();

		//set givens button
		setGivens = new JButton("Set Givens");

		//set up set givens button to set values on the board to givens values
		setGivens.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e){

				SudokuBase model = v.getBase();
				model.fixGivens();
				v.repaint();
				setGivens.setEnabled(false);  //once user sets given values button disabled so cant do again

			}	
		});

		//add to panel holding set givens and new Game button
		setUpModeTools.add(setGivens);

		tools = new JPanel();

		//create new game button- click on starts new game
		JButton newGame = new JButton();
		newGame.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e){

				//ask user for input to start new game (dialog box)
				userInputNum();
			}	
		});

		newGame.setText("New Game");
		tools.add(newGame);

		setUpModeTools.add(tools);
		controllerDisplay.add(setUpModeTools, BorderLayout.SOUTH);


		return controllerDisplay;
	}

	/**
	 * This class represents the buttons on the controller panel that
	 * look like graphical representations of the values the SudokuBoard
	 * can take on.  The user can click on these buttons and the selected 
	 * cell on the SudokuBoard will take on the buttons value.  The controller
	 * buttons also turn white when the mouse hovers over them, becomes an active
	 * color of cyan if the mouse becomes depressed on the button and remains 
	 * depressed until the mouse is no longer over the button.  If the mouse is
	 * clicked, the button turns cyan, and the selected cell on the board changes
	 * to the value on the clicked button.
	 * 
	 *  @author Tiana Greisel
	 * @version Homework Assignment 7: Sudoku, Controller and Integration 
	 *
	 */
	public class ControllerButton extends Cell implements MouseListener {



		/**
		 * Constructs a ControllerButton.  Sets the preferred size to 50 by 50, draws
		 * a black border around the button and sets its color to gray (Gainsboro).  
		 * Adds a mouseListener to the button, so the button can respond to mouse
		 * events.
		 * 
		 */
		public ControllerButton(){

			setPreferredSize(new Dimension(50, 50));

			//draws a black border around component that makes up square
			setBorder(BorderFactory.createLineBorder(Color.BLACK));
			setBackground(Gainsboro);
			addMouseListener(this);

		}

		/**
		 * When the mouse hovers over the button, the color of the button changes
		 * to white.
		 * 
		 * @param e MouseEvent Object that holds information of MouseEvent
		 * 
		 */
		public void mouseEntered(MouseEvent e){

			setBackground(Color.WHITE);
		}

		/**
		 * When the mouse was previously hovering over the button, when it is
		 * no longer over the button, the color of the button goes back to its
		 * original gray (Gainsboro) color.
		 * 
		 * @param e MouseEvent Object that holds information of MouseEvent
		 */
		public void mouseExited(MouseEvent e){

			setBackground(Gainsboro);
		}

		/**
		 * When a mouse is pressed (depressed) on the button and remains so, the color
		 * of the button changes to cyan as long as the mouse remains depressed over
		 * and on the button.
		 * 
		 * @param e MouseEvent Object that holds information of MouseEvent
		 */
		public void mousePressed(MouseEvent e){

			setBackground(Color.CYAN);
		}

		/**
		 * When a mouse was previously depressed on top of a button, as soon
		 * as the mouse is released, the color of the button goes back to its
		 * original gray (Gainsboro) color.
		 * 
		 * 
		 * @param e MouseEvent Object that holds information of MouseEvent
		 */
		public void mouseReleased(MouseEvent e){

			setBackground(Gainsboro);
		}

		/**
		 * When a mouse is clicked on top of a button, this method sets the
		 * value of the selected value on the SudokuBoard to the value on the
		 * button.  The method also invokes the checkRowIndicator(), checkColumnIndicator(),
		 * and checkRegionIndicator() methods which will change the indicator buttons
		 * for the row, column, and region states based on what the newly set value on the
		 * board will change in the states of the row, column, and regions on the board.
		 * 
		 * @param e MouseEvent Object that holds information of MouseEvent
		 */
		public void mouseClicked(MouseEvent e){

			setBackground(Color.CYAN);

			//get model from view 
			SudokuBase model = v.getBase();

			//from model get current selected row and column
			int selectedRow = v.getSelectedRow();
			int selectedColumn = v.getSelectedColumn();

			//get the current ControllerButton clicked on
			ControllerButton currentButton = (ControllerButton)e.getSource();

			//get value from current clicked ControllerButton
			int buttonValue = currentButton.value;

			//if the clicked Controller is a givens value beep
			if(model.isGiven(selectedRow, selectedColumn)){

				Toolkit.getDefaultToolkit().beep();
			}

			//if not given value, set the value on the board to clicked ControllerButton value
			else{

				model.setValue(selectedRow, selectedColumn, buttonValue);

			}

			//change state indicator buttons to display new board change
			checkRowIndicator();
			checkColumnIndicator();
			checkRegionIndicator();
			v.repaint();
		}


		/**
		 * This method gets information from the SudokuBase model and uses that model to 
		 * check the row state of the rows on the SudokuBoard.  If the row is incomplete,
		 * the row representing the row on the indicator button is changed to yellow, if the
		 * row is in the state of error, the row representing that row on the indicator button
		 * is changed to red.  If the row is complete, the row representing the row on the 
		 * indicator button is changed to green.
		 * 
		 */
		public void checkRowIndicator(){

			//get model from view 
			SudokuBase model = v.getBase();

			//iterate over row indicator panels and change color based on same row state on board
			for(int i = 0; i < rowIndicator.length; i++){

				JPanel row = rowIndicator[i];

				//if row state incomplete yellow
				if(model.getRowState(i).equals(State.INCOMPLETE)){

					row.setBackground(Color.YELLOW);	
				}

				//if row state error red
				else if(model.getRowState(i).equals(State.ERROR)){

					row.setBackground(Color.RED);

				}
				//if row state complete green
				else if(model.getRowState(i).equals(State.COMPLETE)){

					row.setBackground(Color.GREEN);
				}
			}
		}


		/**
		 * This method gets information from the SudokuBase model and uses that model to 
		 * check the column state of the columns on the SudokuBoard.  If the column is incomplete,
		 * the column representing the column on the indicator button is changed to yellow, if the
		 * column is in the state of error, the column representing that column on the indicator button
		 * is changed to red.  If the column is complete, the column representing the column on the 
		 * indicator button is changed to green.
		 * 
		 */
		public void checkColumnIndicator(){

			//get model from view 
			SudokuBase model = v.getBase();

			//iterate over column indicator panels and change color based on same column state on board
			for(int i = 0; i < columnIndicator.length; i++){

				JPanel column = columnIndicator[i];

				//if column state incomplete yello
				if(model.getColumnState(i).equals(State.INCOMPLETE)){

					column.setBackground(Color.YELLOW);
				}

				//if column state error red
				else if(model.getColumnState(i).equals(State.ERROR)){

					column.setBackground(Color.RED);

				}
				//if column state complete green
				else if(model.getColumnState(i).equals(State.COMPLETE)){

					column.setBackground(Color.GREEN);	
				}
			}	
		}


		/**
		 * This method gets information from the SudokuBase model and uses that model to 
		 * check the region state of the regions on the SudokuBoard.  If the region is incomplete,
		 * the region representing the region on the indicator button is changed to yellow, if the
		 * region is in the state of error, the region representing that region on the indicator button
		 * is changed to red.  If the region is complete, the region representing the region on the 
		 * indicator button is changed to green.
		 * 
		 */
		public void checkRegionIndicator(){

			//get model from view 
			SudokuBase model = v.getBase();

			//iterate over region indicator panels and change color based on same region state on board
			for(int i = 0; i < regionIndicator.length; i++){

				JPanel region = regionIndicator[i];

				//if region state incomplete yello
				if(model.getRegionState(i).equals(State.INCOMPLETE)){

					region.setBackground(Color.YELLOW);

				}

				//if region state error red
				else if(model.getRegionState(i).equals(State.ERROR)){

					region.setBackground(Color.RED);

				}

				//if region state complete green
				else if(model.getRegionState(i).equals(State.COMPLETE)){

					region.setBackground(Color.GREEN);	
				}
			}	
		}
	}


	/**
	 * This method returns a SudokuBase object which can be passed to a SudokuView 
	 * object and used to create a preset Sudoku game with preset fixed given
	 * values on the board.
	 * 
	 * @return SudokuBase holding the values for a preset Sudoku game.
	 */
	public static SudokuBase makeBoard() {
		SudokuBase board = new SudokuModel(2, 3);
		board.setValue(0, 3, 6);
		board.setValue(0, 5, 1);
		board.setValue(1, 2, 4);
		board.setValue(1, 4, 5);
		board.setValue(1, 5, 3);
		board.setValue(2, 3, 3);
		board.setValue(3, 2, 6);
		board.setValue(4, 0, 2);
		board.setValue(4, 1, 3);
		board.setValue(4, 3, 1);
		board.setValue(5, 0, 6);
		board.setValue(5, 2, 1);
		board.fixGivens();
		return board;
	}

	/**
	 * This method creates a dialog box with two buttons, two texts boxes for the user
	 * to enter the desired number of rows and columns for a new game.  The two buttons are 
	 * Create New Game and Cancel. The Create New Game button reads the values from the text
	 * fields and if the values are greater than zero but less than or equal to 12, a new game
	 * is started.  If the values are less than zero or greater than 12, an alert message is 
	 * prompted.  An alert is also prompted if the value entered is not an integer.
	 * 
	 * 
	 */
	public static void userInputNum(){

		//new dialog window
		winx = new JFrame("NEW SUDOKU GAME");

		//panel inside dialog window to hold everything (border layout)
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());

		//inner panel to hold JTextfields
		JPanel innerPanel = new JPanel();

		//set size and location of window
		winx.setSize(250, 200);
		winx.setLocation(50, 50);

		//JTextFields to get rows and columns number from user
		rows = new JTextField(10);
		columns = new JTextField(10);

		//label to ask user for number of rows, add to inner panel with JTextField
		JLabel rowLabel = new JLabel("Enter number of rows: ");
		innerPanel.add(rowLabel);
		innerPanel.add(rows);

		//label to ask user for number of columns, add to inner panel with JTextField
		JLabel columnLabel = new JLabel("Enter number of columns: ");
		innerPanel.add(columnLabel);
		innerPanel.add(columns);
		panel.add(innerPanel);

		//create new game button and add functionality
		JButton createNewGame = new JButton("Create New Game");

		createNewGame.addActionListener(new ActionListener() {

			/**
			 * Gets text as a string that user enters, converts to an integer and checks if value
			 * is greater than zero but less than or equal to 12.  If valid entry, new game started by
			 * calling newGame() method.  If entry not valid, gives user warning messages indicating why 
			 * not valid.
			 * 
			 * @parame e ActionEvent object holding information regarding action event
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				try{

					//converts user entry to integer
					rowInputString = rows.getText();
					columnInputString = columns.getText();
					rowInput = Integer.parseInt(rowInputString);
					columnInput = Integer.parseInt(columnInputString);

				}
				//if entry isnt an integer
				catch(NumberFormatException n){

					JOptionPane.showMessageDialog(null, "Must enter an integer value", "Alert", JOptionPane.ERROR_MESSAGE);

					winx.dispatchEvent(new WindowEvent(winx,WindowEvent.WINDOW_CLOSING));
					userInputNum();
				}

				//is entry <= 0
				if(rowInput <= 0 || columnInput <= 0){

					JOptionPane.showMessageDialog(null, "row and column value must be positive integers greater than zero", "Alert", JOptionPane.ERROR_MESSAGE);


					winx.dispatchEvent(new WindowEvent(winx,WindowEvent.WINDOW_CLOSING));
					userInputNum();
				}

				//if entry > 12
				else if(rowInput * columnInput > 12){

					JOptionPane.showMessageDialog(null, "row times column cannot be greater than 12", "Alert", JOptionPane.ERROR_MESSAGE);

					winx.dispatchEvent(new WindowEvent(winx,WindowEvent.WINDOW_CLOSING));
					userInputNum();

				}

				//if valid entry start new game
				else{
					newGame();
					winx.dispatchEvent(new WindowEvent(winx,WindowEvent.WINDOW_CLOSING) );
				}	
			}
		});

		//panel to hold "create new game" and "canel" button
		JPanel tools = new JPanel();

		tools.add(createNewGame);

		//create cancel button and add functionality
		JButton cancel = new JButton("Cancel");
		cancel.addActionListener(new ActionListener() {

			/**
			 * If user hits cancel button, close window.
			 * 
			 * @param e ActionEvent object holding information about action event
			 */
			@Override
			public void actionPerformed(ActionEvent e) {

				winx.dispatchEvent(new WindowEvent(winx,WindowEvent.WINDOW_CLOSING));
			}
		});
		//add cancel button to tools panel and tools panel to main panel
		tools.add(cancel);
		panel.add(tools, BorderLayout.SOUTH);

		//add panel to window
		winx.add(panel);
		winx.setVisible(true);
	}


	/**
	 * This method takes the number of rows and columns entered by the user and
	 * creates a new game with those values.  This method creates a new window
	 * to house the game and sets up a JMenu bar with the options to change between
	 * Normal Play mode and Set-Up Mode.  There is also a cancel option.  The normal play
	 * option starts a new game in normal play mode with a fixed game, the set up mode
	 * allows the user to enter their own fixed values on the board.  The normal play
	 * and set up mode options are clearly marked on the window and on the background as
	 * to which mode the board is in.  Set-Up mode has an additional "set givens" button
	 * which is disabled on normal play mode.
	 * 
	 * 
	 */
	public static void newGame(){

		//SudokuBase object holding values of game
		SudokuBase board = new SudokuModel(rowInput, columnInput);

		//SudokuView object which displays board
		SudokuView view = new SudokuView(board);

		//SudokuController 
		SudokuController controller = new SudokuController(view);

		//window to hold game
		win = new JFrame("SUDOKU - SET-UP MODE");

		//main panel holding view and controller
		panel = new JPanel();

		//set size and location of window
		win.setSize((3 * space + cellSize * size * 2)  , (5 * space + cellSize * size + 40));
		win.setLocation(30, 30);
		win.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		//create options men bar and add functionality
		menuBar = new JMenuBar();
		JMenu changeModes = new JMenu("Options");
		JMenuItem setUpMode = new JMenuItem("Set-Up Mode");
		JMenuItem normalPlayMode = new JMenuItem("Normal Play Mode");
		JMenuItem cancel = new JMenuItem("Cancel");

		normalPlayMode.addActionListener(new ActionListener() {

			/**
			 * Removes main panel holding controller and view from window.  Sets up a 
			 * new view and controller based on preset game from makeBoard() method.  Disables
			 * "set givens" button and changes JLabel on window to display normal play mode
			 * and for window title to say normal play mode.
			 * 
			 * @param e ActionEvent object holding information about action event
			 */
			@Override
			public void actionPerformed(ActionEvent e) {				

				//remove main panel 
				win.remove(panel);

				//create new inner panel to hold controller and view
				JPanel newPanel = new JPanel();

				//make a new game with preset game, pass to view and view to controller
				SudokuBase board = makeBoard();
				SudokuView view = new SudokuView(board);
				SudokuController newController = new SudokuController(view);

				//change size and title of window
				win.setSize((3 * space + cellSize * size * 2)  , (5 * space + cellSize * size + 40));
				win.repaint();
				win.setTitle("SUDOKU - Normal Play Mode");

				//add the new SudokuController and add new controller buttons and controller panel
				newPanel.add(newController);
				newPanel.add(newController.addControllerPanel());

				//remove panel with previous JLable of mode in
				modePanel.remove(mode);
				win.remove(modePanel);

				//make a new JLabel to display new normal play mode
				mode = new JLabel("NORMAL PLAY MODE");
				mode.setFont(new Font("Dialog",Font.BOLD,15));   
				mode.setForeground(Color.BLUE);
				modePanel.add(mode);

				//add label to window
				win.add(modePanel, BorderLayout.SOUTH);

				//add new panel to window
				win.getContentPane().add(newPanel);

				//disable set givens button in normal-play mode
				setGivens.setEnabled(false);


				newController.repaint();
				win.validate();
				win.repaint();	
			}
		});

		//when in set up mode
		setUpMode.addActionListener(new ActionListener() {

			/**
			 * When user chooses set up mode option, dialog box appears asking user
			 * for row and column number to start a new game.
			 *
			 * @param e ActionEvent object holding information about action event
			 */
			@Override
			public void actionPerformed(ActionEvent e) {

				win.dispatchEvent(new WindowEvent(win, WindowEvent.WINDOW_CLOSING));

				//ask user for row and column number to start a new game
				userInputNum();

			}
		});

		//cancel option
		cancel.addActionListener(new ActionListener(){

			/**
			 * When user chooses cancel option close window and ask user to enter new
			 * row and column number.  Cancel on dialog box closes all.
			 * 
			 * @param e ActionEvent object holding information about action event
			 */
			public void actionPerformed(ActionEvent e){

				win.dispatchEvent(new WindowEvent(winx,WindowEvent.WINDOW_CLOSING));

				//ask user to enter new row and column info to start new game
				userInputNum();	
			}
		});

		//add options items to JMenu and add JMenu to JMenuBar
		changeModes.add(normalPlayMode);
		changeModes.add(setUpMode);
		changeModes.add(cancel);
		menuBar.add(changeModes);

		//add JMenuBar to window
		win.setJMenuBar(menuBar);

		//add controller to panel
		panel.add(controller);

		//add panel with all controller buttons to main panel
		panel.add(controller.addControllerPanel());

		//add panel to window
		win.add(panel);

		//add panel to hold JLabel with a JLabel displaying current mode in
		modePanel = new JPanel();
		mode = new JLabel("SET-UP MODE");
		mode.setFont(new Font("Dialog",Font.BOLD,15));   
		mode.setForeground(Color.BLUE);
		modePanel.add(mode);

		//add JLabel mode panel to window
		win.add(modePanel, BorderLayout.SOUTH);

		win.setVisible(true);

	}


	/**
	 * This SudokuMain application starts a Sudoku game.  A message dialog box is first shown describing the
	 * game and then a dialog box prompting the user to enter the number of rows and columns to create a new 
	 * game is shown.  Once the user enters this a new Sudoku game is started in set-up mode where the user
	 * can enter values on the board and click the "set givens" button to fix the given values on the board
	 * and start a new sudoku game.  A options menu allows the user to go to Normal Play mode where a preset
	 * game is set up for the user to play.  In this mode the "set givens" button is disabled.  The user can
	 * also click the "create new game" button to bring up the dialog box to start a new game by entering the
	 * number of desired rows and columns.  The cancel option in the options bar will cancel whatever the user
	 * was currently doing.
	 * 
	 * 
	 * @param args console input
	 */
	public static void main(String[]args){


		//display information to user about game
		JOptionPane.showMessageDialog(null, "To play the Sudoku Game, enter the number of rows and columns for the Sudoku Board.\n  "
				+ "This will start the game in set-up mode where you can select the cells on the board to be set to\n "
				+ "givens values to make up your own game. From the options menu you can go to Normal Play Mode\n"
				+ "to play a pre-set game. You may cancel at anytime from the options menu and this will allow you to\n "
				+ "start a new game.  Within set-up mode, click the create new game to start a new game.","SUDOKU GAME", JOptionPane.INFORMATION_MESSAGE);


		//start dialog box to ask user for input to start a new game
		userInputNum();

	}
}
