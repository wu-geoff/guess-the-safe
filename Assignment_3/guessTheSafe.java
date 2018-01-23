//===========================================================================
//Guess the safe 
//Geoffrey Wu
//January 20, 2015	
//Java
//===========================================================================
//Problem Definition - Find the 5 digit  sequence of numbers to open the safe.
//===========================================================================
//List of Identifiers    - let SIZE represent how may button I need for my num pad (type int)
//                                 - let INPUTSIZE represent the size of my input (type int)
//                                 - let userNameString represent the string for user name (type String)
//                                 - let random[] represent the array of my random numbers (type int[]) 
//                                 - let menu[] represent the array for the name of buttons in menu (type String[])
//                                 - let JB[] represent the array for the buttons of the num pad (type JButton[])
//                                 - let JM[] represent the array for the buttons of the menu(type JButton[])
//                                 - let JL[] represent the array for the input JLabels (type JLabel[])
//                                 - let lblTitle represent the title of the game (type JButton)
//                                 - let lblCurrentAttemptLevel represent the Current Attempt Level of the user (type JButton)
//                                 - let lblPoints represent the points user earned (type JLabel) 
//                                 - let lblFailAttempt represent the amount of failed attempt (type JLabel)
//                                 - let btnSubmit represent the button that submits user's input (type JButton)
//                                 - let btnClear represent the button that clears the user's input (type JButton)
//                                 - let failAttempt represent the fail attempt level of the user (type int)
//                                 - let currentAttempt represent the current attempt level of the user (type int)
//                                 - let points represents how many points user earned (type int)
//===========================================================================
import java.awt.*; //allow access to the coding within the awt library
import java.awt.event.*; //allow access to the coding within the awt.event library
import java.util.*; //allow access to the coding within the util library 
import javax.swing.*; //allow access to the coding within the swing library 
public class guessTheSafe extends JApplet implements ActionListener { //Start of guessTheSafe class
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	final int SIZE = 10, INPUTSIZE = 5; //declare two int primitive data type named SIZE, INPUTSIZE
	boolean login = false; //declare a boolean object data typed named login
	int random[] = new int [INPUTSIZE], points = 160, failAttempt = 0,currentAttempt = failAttempt + 1;  //declare 5 int primitive data type named random[],points,failAttempt,currentAttempt
	String menu[] = {"Cheat", "How to play", "Exit", "Player stats", "Login","Start Game"}, userNameString = ""; //declare 2 String object data type named menu[], userNameString
	JLabel lblTitle = new JLabel("PASSWORD BROKER"), lblCurrentAttemptLevel = new JLabel("Current Attempt Level: " + currentAttempt), lblPoints = new JLabel("Points:" + points), lblFailAttempt = new JLabel("Fail Attempt: " + failAttempt), lblUser = new JLabel("User name:" + userNameString),JL[] = new JLabel [INPUTSIZE];
	//declare 5 JLabel object data type named lblTitle, lblCurrentAttemptLevel, lblPoints,lblFailAttempt,lblUser,JL[] 
	JButton JB[] = new JButton [SIZE], btnSubmit = new JButton("Submit"), btnClear = new JButton("Clear All"), JM[] = new JButton[menu.length];
	//declare 4 JButton object data type named JB[], btnSubmit, btnClear, btnClear
	Font defaultFont = new Font("SansSerif", Font.ITALIC, 15); //set the default font of the program
	
	public static void main(String[] args) { //main method header
		run(new guessTheSafe(),1000,1000); //run the game
	} //end main method 
	
    public guessTheSafe(){
    	Container cp = getContentPane(); //Declaration and instantiation of a Container object
    	cp.setLayout(new BorderLayout()); //set the layout of the container
    	cp.add(mainTitlePanel() , BorderLayout.NORTH); //add the mainTitlePanel and place it on the top
    	cp.add(getInputPanel(),BorderLayout.CENTER); //add the getInputPanel and place it in the middle
    	cp.add(messagePanel(), BorderLayout.EAST); //add the messagePanel and place it on the right side
    	cp.add(numPadPanel(), BorderLayout.SOUTH); //add the numPadPanel and place it on the bottom side
    	ArrayList<Integer> ran = new ArrayList<Integer>(); //Declaration and instantiation of a array list named ran
    	for (int i = 0; i < SIZE; i++){ //counted loop used put 0 - 9 into the arraylist
    		ran.add(i); //put number 0 - 9 in the array list
    	}
    	Collections.shuffle(ran); //shuffle the arraylist
    	for(byte cnt = 0; cnt < INPUTSIZE; cnt++){ //counted loop used to get next 5 random numbers 
    		random[cnt] = ran.get(cnt); //get the random numbers from the shuffled arraylist
    	}
    }
    
    private JComponent mainTitlePanel(){ //mainTitlePanel method header
    	JPanel tp = new JPanel(); //Declaration and instantiation of a JPanel object
    	tp.setLayout(new GridLayout(0,1)); //set the layout of the JPanel as gridLayout(0 rows, 1 columns)
    	lblTitle.setFont(new Font("SansSerif", Font.BOLD, 35)); //set the font of the title
    	tp.add(lblTitle); // add the title into the JPanel
    	lblTitle.setHorizontalAlignment((int) 0.4); //place the title to the middle
		return tp; //return the JPanel
    }// end mainTitlePanel method
    
    private JComponent getInputPanel(){ //getInputPanel method header
    	JPanel ip = new JPanel(); //Declaration and instantiation of a JPanel object
    	ip.setLayout(new GridLayout(0,5)); //set the layout of the JPanel as gridLayout(0 rows, 5 columns)
    	for(byte j = 0; j < JL.length; j++){// counted loop for the next 5 JLabel
        		JL[j] = new JLabel(); //declare a new JLabel
        		JL[j].setFont(new Font("Arial", Font.BOLD, 20)); //set the font of the JLabel 
        		ip.add(JL[j]);// add the JLabel to the JPanel
        }
		return ip;//return the JPanel
    }// end getInputPanel method
    
    private JComponent numPadPanel(){// numPadPanel method header
    	JPanel np = new JPanel(); //Declaration and instantiation of a JPanel object
    	np.setLayout(new GridLayout(7,3)); //set the layout of the JPanel as gridLayout(7 rows, 3 columns)
    	for(byte h = 0; h < JB.length; h++){ //counted loop for creating JButtons of the num pad
    		JB[h] = new JButton("" + h); //declare a new JButton
    		JB[h].setFont(defaultFont); //set the font of the JButton as the default font
    		JB[h].setEnabled(false); //disable the JButton  
    		np.add(JB[h]); //add the JButton to the JPanel
    		JB[h].addActionListener(this); //add ActionLinster to the button
    	}
    	btnClear.setFont(defaultFont);// set the font of btnClear as the default font
    	np.add(btnClear);// add the btnClear to the JPanel
    	btnClear.setEnabled(false); // disable btnClear
    	btnClear.addActionListener(this); // add ActionLinster to btnClear
    	np.add(JB[0]); // add JB[0] to the JPanel
    	btnSubmit.setFont(defaultFont);// set the font of JB[0]  as the default font
    	np.add(btnSubmit); // add btnSubmit to JPanel
    	btnSubmit.setEnabled(false); // disable btnSubmit
    	btnSubmit.addActionListener(this); // add ActionLinster to btnSubmit
    	for(int x = 0; x < menu.length;x++){// counted loop for creating JButtons of the menu
    		JM[x] = new JButton("" + menu[x]); //declare a new JButton
    		JM[x].setFont(defaultFont); //set the font of the JButton as the default font
    		np.add(JM[x]); //add the JButton to the JPanel
    		JM[x].setEnabled(false); //disable the JButton  
    		JM[x].addActionListener(this); //add ActionLinster to the JButton
    	}
    	np.add(JM[5]); //add JM[5] to the JPanel
		JM[5].setEnabled(true); //enable JM[5]
		JM[5].addActionListener(this); //add ActionLinster to the JM[5]
		return np;//return the JPanel
    }//end numPadPanel method
    
    private JComponent messagePanel(){ //messagePanel method header
    	JPanel mp = new JPanel(); //Declaration and instantiation of a JPanel object
    	mp.setLayout(new GridLayout(0,1)); //set the layout of the JPanel as gridLayout(0 rows, 1 columns)
    	mp.add(lblUser); //add lblUser to the JPanel
    	mp.add(lblPoints); //add lblPoints to the JPanel
    	mp.add(lblFailAttempt); //add lblFailAttempt to the JPanel
    	lblCurrentAttemptLevel.setText("Current Attempt Level:" + currentAttempt ); //set the text of lblCurrentAttemptLevel to display the Current Attempt Level of the user
    	mp.add(lblCurrentAttemptLevel); //add lblCurrentAttemptLevel to the JPanel
		return mp;//return the JPanel
    }// end messagePanel method
    
    public static void run(JApplet applet, int width, int height){ // run method header
    	JFrame frame = new JFrame(); //Declaration and instantiation of a JFrame object
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //set the close operation
        frame.getContentPane().add(applet); //get the container of the JApplet 
        frame.setSize(width, height);	 //set the size of JApplet
        frame.setVisible(true); //make the JApplet visible
    }// end run method

	public void actionPerformed(ActionEvent e) { //actionPerformed method header
		String theCommand = e.getActionCommand(); //get the user input
	     if(theCommand.equals("Start Game")){ //if the user pressed "Start Game"
			if (login == true){ //if the user did log in
				for(byte i = 0; i < JB.length; i++){ //counted loop for activate all JButtons of num pad
					JB[i].setEnabled(true); //enable JB[i]
				}
				btnClear.setEnabled(true); //enable btnClear
				JB[0].setEnabled(true); //enable JB[0]
				btnSubmit.setEnabled(true); //enable btnSubmit
				for(byte j = 0; j < JM.length;j++){ //counted loop for activate all JButtons of  the menu
					JM[j].setEnabled(true); //enable JM[i]
				}
			}
			else{ //if the user didn't log in
				int option = JOptionPane.showConfirmDialog(null, "You didn't login yet, do you want to start the game without login?", "Login",JOptionPane.YES_NO_OPTION,2); //UFP the user to log in, or start the game without login
				if(option != JOptionPane.YES_NO_OPTION){ //if the user chose to log in
					 userNameString = JOptionPane.showInputDialog(null, "Enter the user name:", "Login",3); //UFP the user to enter a user name, and save the user input to userNameString
					 if(userNameString.equals("") || userNameString.equals(" ")){ //if the user input is blank
						 JOptionPane.showInputDialog(null,"You didn't type anything :(","Warning",2); //UFP the user that his/her input is blank
					 }
					 else{ // if the user input is not blank
						 JOptionPane.showMessageDialog(null,"Welcome "+userNameString+" :)", "Welcome",3); //output a message to welcome the user
						 lblUser.setText("User name:"+userNameString); //add the userNameString to lblUser 
						 login = true; //user is log in 
					 }
				}
				else{
					JOptionPane.showMessageDialog(null, "Welcome Guest :)"); //output a message to welcome the user
					userNameString = "Guest"; //set the user name as "guest"
					login = true; //user is log in
					lblUser.setText("User name:"+userNameString); //add the userNameString to lblUser 
				}
			}
		}
		else if(theCommand.equals("Submit")){ //if the user presses "Submit"
			for (int i=0;i<10;i++){// counted loop for disabling all JButtons of num pad
			      JB[i].setEnabled(false); //disable JB[i]
			}
			if(JL[0].getText().equals("") && JL[1].getText().equals("")  && JL[2].getText().equals("")  && JL[3].getText().equals("")  && JL[4].getText().equals("") ){ //if all 5 input JLabel is blank
				JOptionPane.showMessageDialog(null, "You didn't fill in all five blocks!!!"); //output a message that the user didn't fill all 5 blocks
			}
			else{
				int green = 0, red = 0; //declare two int primitive data type named green and red
				for(byte cnt = 0; cnt < JL.length;cnt++){ //counted loop for check each user input
					if(JL[cnt].getText().equals(String.valueOf(random[cnt]))){ // if the user input is equal to the randomized integer
						JL[cnt].setForeground(Color.green); //set the foreground of the input JLabel to green
						green++; //increase the value of variable green
					}
					else if(!JL[cnt].getText().equals(String.valueOf(random[cnt]))){ // if the user input is not equal to the randomized integer
						JL[cnt].setForeground(Color.red); //set the foreground of the input JLabel to red
						red++; //increase the value of variable red
					}
				}
				points -= 20;// reduce 20 points
				lblPoints.setText("Points:"+points); //display the points user earned
				failAttempt++; //increase the fail attempt by 1
				lblFailAttempt.setText("Fail Attempt: " + failAttempt); //display the fail attempt
				currentAttempt = failAttempt +1;  //increase variable currentAttempt by adding 1 to failAttempt
				lblCurrentAttemptLevel.setText("Current Attempt Level: " + currentAttempt); //display the current attempt
				if(green == 5 && failAttempt <= 8 && failAttempt != 0&& points > 0){ //if the user guess all 5 numbers correct in 8 attempts
					points += 20; // increase 20 points
					lblPoints.setText("Points:"+points); //display the points user earned
					failAttempt--; //reduce the fail attempt by 1
					lblFailAttempt.setText("Fail Attempt: " + failAttempt);//display the updated fail attempt
					currentAttempt = failAttempt +1; //increase variable currentAttempt by adding 1 to failAttempt
					lblCurrentAttemptLevel.setText("Current Attempt Level: " + currentAttempt); //display the current attempt
					JOptionPane.showMessageDialog(null, "You did it !!!!!!!!!! You earned "+points+" points!" ,"Win",1); //output a message and display how many points he/she earned
					for (int i = 0;i< SIZE ;i++){ //counted loop for enabling the num pad
						   JB[i].setEnabled(false); //enable JB[i]
						}
				    }
					else if(red >= 1 && failAttempt == 8 && points == 0){ //if the user cannot guess all 5 numbers correct in 8 attempts
						    points = 0; //points equals 0
							lblPoints.setText("Points:"+points); //display the points user earned
							JOptionPane.showMessageDialog(null, "U JUST TRIGGERED THE ALARM, GET THE HELL OUTTA HERE!!!!!!!!!!!!!!!!","Fail",2); //display a message that the user failed
					}
			}
		}
		else if (theCommand.equals("Clear All")){ //if the user pressed "Clear All"
			for (int i = 0;i < 10;i++){ //counted loop for activating all JButtons of the num pad
				JB[i].setEnabled(true); //enable JB[i]
			}
			for(byte j = 0; j < JL.length; j++){ //counted loop to clear all JLabels of the input
	    		JL[j].setText(""); //clear JL[j]
	         }
		}
		else if(theCommand.equals("Login")){ //if the user pressed "Login"
			 String userNameString = JOptionPane.showInputDialog(null, "Enter the user name:", "Login",3); //UFP the user to enter a user name, and save the user input to userNameString
			 if(userNameString.equals("") || userNameString.equals(" ")){ //if the user input is blank
				 JOptionPane.showInputDialog(null, "You didn't type anything :(","Warning",2); //UFP the user that his/her input is blank
			 }
			 else{ // if the user input is not blank
				 JOptionPane.showMessageDialog(null, "Welcome " + userNameString + " :)" , "Welcome", 1); //output a message to welcome the user
				 lblUser.setText("User name:"+userNameString);
				 login = true; //user is log in
			 }
	   }
		else if(theCommand.equals("Cheat")){  //if the user pressed "Cheat"
			final String CHEATCODE = "77777"; //Finalize a String named CHEATCODE
			String input = JOptionPane.showInputDialog(null, "Enter the cheat code: \n(In fact, are you sure you gonna do this?)", "Cheat"); //UFP the user enter cheat code
			if (input.equals(CHEATCODE)){ //if the user input is correct
				JOptionPane.showMessageDialog(null, "The answer is : "+random[0]+random[1]+random[2]+random[3]+random[4],"Cheat code", 1); //display the answer
			}
			else{ //if the user input is not correct
				JOptionPane.showMessageDialog(null, "Wrong cheat code, try again :P \n (U mad???!!!)"); //display a message that the input is wrong 
			}
		}
		else if (theCommand.equals("Exit")){ //if the user pressed "Exit"
			 System.exit(0); //exit the program
		}
		else if(theCommand.equals("Player stats")){ //if the user pressed "Play stats"
			JOptionPane.showMessageDialog(null,messagePanel(), "Player stats", 1); //display the player stats
		}
		else if(theCommand.equals("How to play")){ //if the user pressed "How to play"
			JOptionPane.showMessageDialog(null, "Each round starts with a new random combination sequence to lock the safe. \nWithin each round there are 8 attempts and if the 8th attempt is a fail, the alarm is set off and you must escape. \nEACH NUMBER CAN ONLY BE USED ONCE.  (i.e  3 can only appear once, therefore 34338 is invalid) \nPlayer's guess the correct 5 digit sequence to open the lock to the safe. \n Correct guesses would indicated in green and wrong guesses would indicated in red. \nGood luck! *^_^*", "How to play", 1);
			//display the introduction of gameplay
		}
	     
	    for(byte j = 0; j < JL.length; j++){ //counted loop for get text from the array JL
    		JL[j].getText(); //get text from JL[j]
        }
		if (theCommand.equals("0")){ //if the user pressed "0"
			int valuei=Integer.parseInt(theCommand); //variable valuei equals to theCommand 
			JB[valuei].setEnabled(false); //disable the JButton
			if (JL[0].getText().equals("")){ //if JL[0] is blank 
				JL[0].setText(theCommand); //JL[0] = valuei
			}
			else if(JL[0].getText() != ("")&&(JL[1].getText().equals(""))){ //if JL[0] is not blank and JL[1] is blank
				JL[1].setText(theCommand); //JL[1] = valuei
			}
			else if(JL[0].getText() != ("")&&JL[1].getText() != ("")&&JL[2].getText().equals("")){ //if JL[0], JL[1] are not blank, but JL[2] is blank
				JL[2].setText(theCommand); //JL[2] = valuei
			}
			else if(JL[0].getText() != ("")&&JL[1].getText() != ("")&&JL[2].getText() != ("")&&JL[3].getText().equals("")){//if JL[0], JL[1],JL[2] are not blank, but JL[3] is blank
				JL[3].setText(theCommand); //JL[3] = valuei
			}
			else{ //if JL[0], JL[1],JL[2], JL[3] are not blank, but JL[4] is blank
				JL[4].setText(theCommand); //JL[4] = valuei
			}
		}
		else if (theCommand.equals("1")){
			int valuei=Integer.parseInt(theCommand); //variable valuei equals to theCommand 
			JB[valuei].setEnabled(false); //disable the JButton
			if (JL[0].getText().equals("")){ //if JL[0] is blank 
				JL[0].setText(theCommand); //JL[0] = valuei
			}
			else if(JL[0].getText() != ("")&&(JL[1].getText().equals(""))){ //if JL[0] is not blank and JL[1] is blank
				JL[1].setText(theCommand); //JL[1] = valuei
			}
			else if(JL[0].getText() != ("")&&JL[1].getText() != ("")&&JL[2].getText().equals("")){ //if JL[0], JL[1] are not blank, but JL[2] is blank
				JL[2].setText(theCommand); //JL[2] = valuei
			}
			else if(JL[0].getText() != ("")&&JL[1].getText() != ("")&&JL[2].getText() != ("")&&JL[3].getText().equals("")){//if JL[0], JL[1],JL[2] are not blank, but JL[3] is blank
				JL[3].setText(theCommand); //JL[3] = valuei
			}
			else{ //if JL[0], JL[1],JL[2], JL[3] are not blank, but JL[4] is blank
				JL[4].setText(theCommand); //JL[4] = valuei
			}
		}
		else if (theCommand.equals("2")){
			int valuei=Integer.parseInt(theCommand); //variable valuei equals to theCommand 
			JB[valuei].setEnabled(false); //disable the JButton
			if (JL[0].getText().equals("")){ //if JL[0] is blank 
				JL[0].setText(theCommand); //JL[0] = valuei
			}
			else if(JL[0].getText() != ("")&&(JL[1].getText().equals(""))){ //if JL[0] is not blank and JL[1] is blank
				JL[1].setText(theCommand); //JL[1] = valuei
			}
			else if(JL[0].getText() != ("")&&JL[1].getText() != ("")&&JL[2].getText().equals("")){ //if JL[0], JL[1] are not blank, but JL[2] is blank
				JL[2].setText(theCommand); //JL[2] = valuei
			}
			else if(JL[0].getText() != ("")&&JL[1].getText() != ("")&&JL[2].getText() != ("")&&JL[3].getText().equals("")){//if JL[0], JL[1],JL[2] are not blank, but JL[3] is blank
				JL[3].setText(theCommand); //JL[3] = valuei
			}
			else{ //if JL[0], JL[1],JL[2], JL[3] are not blank, but JL[4] is blank
				JL[4].setText(theCommand); //JL[4] = valuei
			}
		}
		else if (theCommand.equals("4")){
			int valuei=Integer.parseInt(theCommand); //variable valuei equals to theCommand 
			JB[valuei].setEnabled(false); //disable the JButton
			if (JL[0].getText().equals("")){ //if JL[0] is blank 
				JL[0].setText(theCommand); //JL[0] = valuei
			}
			else if(JL[0].getText() != ("")&&(JL[1].getText().equals(""))){ //if JL[0] is not blank and JL[1] is blank
				JL[1].setText(theCommand); //JL[1] = valuei
			}
			else if(JL[0].getText() != ("")&&JL[1].getText() != ("")&&JL[2].getText().equals("")){ //if JL[0], JL[1] are not blank, but JL[2] is blank
				JL[2].setText(theCommand); //JL[2] = valuei
			}
			else if(JL[0].getText() != ("")&&JL[1].getText() != ("")&&JL[2].getText() != ("")&&JL[3].getText().equals("")){//if JL[0], JL[1],JL[2] are not blank, but JL[3] is blank
				JL[3].setText(theCommand); //JL[3] = valuei
			}
			else{ //if JL[0], JL[1],JL[2], JL[3] are not blank, but JL[4] is blank
				JL[4].setText(theCommand); //JL[4] = valuei
			}
		}
		else if (theCommand.equals("5")){
			int valuei=Integer.parseInt(theCommand); //variable valuei equals to theCommand 
			JB[valuei].setEnabled(false); //disable the JButton
			if (JL[0].getText().equals("")){ //if JL[0] is blank 
				JL[0].setText(theCommand); //JL[0] = valuei
			}
			else if(JL[0].getText() != ("")&&(JL[1].getText().equals(""))){ //if JL[0] is not blank and JL[1] is blank
				JL[1].setText(theCommand); //JL[1] = valuei
			}
			else if(JL[0].getText() != ("")&&JL[1].getText() != ("")&&JL[2].getText().equals("")){ //if JL[0], JL[1] are not blank, but JL[2] is blank
				JL[2].setText(theCommand); //JL[2] = valuei
			}
			else if(JL[0].getText() != ("")&&JL[1].getText() != ("")&&JL[2].getText() != ("")&&JL[3].getText().equals("")){//if JL[0], JL[1],JL[2] are not blank, but JL[3] is blank
				JL[3].setText(theCommand); //JL[3] = valuei
			}
			else{ //if JL[0], JL[1],JL[2], JL[3] are not blank, but JL[4] is blank
				JL[4].setText(theCommand); //JL[4] = valuei
			}
		}
		else if (theCommand.equals("6")){
			int valuei=Integer.parseInt(theCommand); //variable valuei equals to theCommand 
			JB[valuei].setEnabled(false); //disable the JButton
			if (JL[0].getText().equals("")){ //if JL[0] is blank 
				JL[0].setText(theCommand); //JL[0] = valuei
			}
			else if(JL[0].getText() != ("")&&(JL[1].getText().equals(""))){ //if JL[0] is not blank and JL[1] is blank
				JL[1].setText(theCommand); //JL[1] = valuei
			}
			else if(JL[0].getText() != ("")&&JL[1].getText() != ("")&&JL[2].getText().equals("")){ //if JL[0], JL[1] are not blank, but JL[2] is blank
				JL[2].setText(theCommand); //JL[2] = valuei
			}
			else if(JL[0].getText() != ("")&&JL[1].getText() != ("")&&JL[2].getText() != ("")&&JL[3].getText().equals("")){//if JL[0], JL[1],JL[2] are not blank, but JL[3] is blank
				JL[3].setText(theCommand); //JL[3] = valuei
			}
			else{ //if JL[0], JL[1],JL[2], JL[3] are not blank, but JL[4] is blank
				JL[4].setText(theCommand); //JL[4] = valuei
			}
		}
		else if (theCommand.equals("7")){
			int valuei=Integer.parseInt(theCommand); //variable valuei equals to theCommand 
			JB[valuei].setEnabled(false); //disable the JButton
			if (JL[0].getText().equals("")){ //if JL[0] is blank 
				JL[0].setText(theCommand); //JL[0] = valuei
			}
			else if(JL[0].getText() != ("")&&(JL[1].getText().equals(""))){ //if JL[0] is not blank and JL[1] is blank
				JL[1].setText(theCommand); //JL[1] = valuei
			}
			else if(JL[0].getText() != ("")&&JL[1].getText() != ("")&&JL[2].getText().equals("")){ //if JL[0], JL[1] are not blank, but JL[2] is blank
				JL[2].setText(theCommand); //JL[2] = valuei
			}
			else if(JL[0].getText() != ("")&&JL[1].getText() != ("")&&JL[2].getText() != ("")&&JL[3].getText().equals("")){//if JL[0], JL[1],JL[2] are not blank, but JL[3] is blank
				JL[3].setText(theCommand); //JL[3] = valuei
			}
			else{ //if JL[0], JL[1],JL[2], JL[3] are not blank, but JL[4] is blank
				JL[4].setText(theCommand); //JL[4] = valuei
			}
		}
		else if(theCommand.equals("8")){
			int valuei=Integer.parseInt(theCommand); //variable valuei equals to theCommand 
			JB[valuei].setEnabled(false); //disable the JButton
			if (JL[0].getText().equals("")){ //if JL[0] is blank 
				JL[0].setText(theCommand); //JL[0] = valuei
			}
			else if(JL[0].getText() != ("")&&(JL[1].getText().equals(""))){ //if JL[0] is not blank and JL[1] is blank
				JL[1].setText(theCommand); //JL[1] = valuei
			}
			else if(JL[0].getText() != ("")&&JL[1].getText() != ("")&&JL[2].getText().equals("")){ //if JL[0], JL[1] are not blank, but JL[2] is blank
				JL[2].setText(theCommand); //JL[2] = valuei
			}
			else if(JL[0].getText() != ("")&&JL[1].getText() != ("")&&JL[2].getText() != ("")&&JL[3].getText().equals("")){//if JL[0], JL[1],JL[2] are not blank, but JL[3] is blank
				JL[3].setText(theCommand); //JL[3] = valuei
			}
			else{ //if JL[0], JL[1],JL[2], JL[3] are not blank, but JL[4] is blank
				JL[4].setText(theCommand); //JL[4] = valuei
			}
		}
		else if(theCommand.equals("9")){		
			int valuei=Integer.parseInt(theCommand); //variable valuei equals to theCommand 
			JB[valuei].setEnabled(false); //disable the JButton
			if (JL[0].getText().equals("")){ //if JL[0] is blank 
				JL[0].setText(theCommand); //JL[0] = valuei
			}
			else if(JL[0].getText() != ("")&&(JL[1].getText().equals(""))){ //if JL[0] is not blank and JL[1] is blank
				JL[1].setText(theCommand); //JL[1] = valuei
			}
			else if(JL[0].getText() != ("")&&JL[1].getText() != ("")&&JL[2].getText().equals("")){ //if JL[0], JL[1] are not blank, but JL[2] is blank
				JL[2].setText(theCommand); //JL[2] = valuei
			}
			else if(JL[0].getText() != ("")&&JL[1].getText() != ("")&&JL[2].getText() != ("")&&JL[3].getText().equals("")){//if JL[0], JL[1],JL[2] are not blank, but JL[3] is blank
				JL[3].setText(theCommand); //JL[3] = valuei
			}
			else{ //if JL[0], JL[1],JL[2], JL[3] are not blank, but JL[4] is blank
				JL[4].setText(theCommand); //JL[4] = valuei
			}
		}
	}
} //end guesstheSafe class