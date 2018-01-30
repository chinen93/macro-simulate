import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.Timer;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterSimulate implements ActionListener{
    
    // Frame of program.
    private JFrame frame;

    // Panel for the Macro core inputs.
    private JPanel macroPanel;
    private JLabel lblInfo;
    private JLabel lblTimer;
    private JLabel lblRepetitions;
    private JButton btnMacro;

    // Panel for Config inputs.
    private JPanel configPanel;
    private JLabel lblConfig;
    private JLabel lblFirstLetter;
    private JLabel lblSecondLetter;
    private JLabel lblFromDigit;
    private JLabel lblToDigit;
    private JLabel lblIsSide;
    private JCheckBox chbSide;
    private JTextField txtFirstLetter;
    private JTextField txtSecondLetter;
    private JTextField txtFromDigit;
    private JTextField txtToDigit;
    private JButton btnConfig;

    // Panel for CheckBox Config
    private JPanel levelPanel;
    private JLabel lblLevels;
    private JCheckBox chbLevelA;
    private JCheckBox chbLevelB;
    private JCheckBox chbLevelC;
    
    // Default values
    private final int DEFAULT_WIDTH = 400;
    private final int DEFAULT_HEIGH = 400;
    private final int DEFAULT_COUNT_DOWN = 5;
    private final int DEFAULT_COUNT_REPETITION = 1;
    private final int DEFAULT_COUNT_INTERVAL = 250;

    // Some default configurations.
    private Timer timer;
    private Timer macroTimer;
    private Robis robis;
    private int counter = 0, counterLevel;
    private int counterRepetitions = 0;
    private boolean isMacroActive = false;
    private int numberFrom, numberTo;
    private String firstLetter, secondLetter;
    private boolean isA, isB, isC;

    private int width = DEFAULT_WIDTH;
    private int heigh = DEFAULT_HEIGH;
    private int countDown = DEFAULT_COUNT_DOWN;
    private int countRepetitions = DEFAULT_COUNT_REPETITION;
    private int countInterval = DEFAULT_COUNT_INTERVAL;

    
    // Systems Strings.
    private String CANCEL_MACRO = "Macro Cancelled. Try Again.";
    private String CODE_ERROR = "Empty code, Try again.";
    private String MACRO_INFO = "Macro to Press Enter Twice, Some chars, and Enter twice again.";
    private String CONFIG_BUTTON = "Set Configurations";
    private String CONFIG_ERROR = "Numbers Only, Try Again";
    private String CONFIG_UPDATE = "Configurations Updated";
    private String MACRO_DONE = "Macro Done";
    private String START_COUNTDOWN = "Set Config and Start Countdown to Macro!";
    private String STOP_MACRO = "Stop Macro!";
    private String TIMER_INFO = "Timer for Macro Execution";
    private String REPETITION_INFO = "Number of Repetitions left: ";
    
    // Main function to start the program.
    public static void main(String[] args) {
	new RegisterSimulate();
    }
    
    // Function to use the Robis class to press the keys.
    public void executeMacro(int number, char level){
	int hundred = (int) number / 100;
	int decimal = (int) number / 10;
	int unit = number %10;

	robis.pressEnter();
	robis.pressEnter();

	robis.selectAndPressKey(firstLetter.charAt(0));
	robis.selectAndPressKey(secondLetter.charAt(0));
	robis.selectAndPressKey(hundred);
	robis.selectAndPressKey(decimal);
	robis.selectAndPressKey(unit);
	robis.selectAndPressKey(level);

	robis.pressEnter();
	robis.pressEnter();
    }
    
    public RegisterSimulate()
    {
	// Instanciate Robis;
	robis = new Robis();

	// Create the frame.
	frame = new JFrame("Register Macro");
	frame.setAlwaysOnTop(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(width, heigh);

	// ===========================================================
	// Macro Timer  
	// ===========================================================
	macroTimer = new Timer(DEFAULT_COUNT_INTERVAL, new ActionListener() {

		// Timer for each macro action.
		public void actionPerformed(ActionEvent e) {

		    // End of macro, return everything to normal for
		    // the next macro.
		    if(counterRepetitions > countRepetitions){
			macroTimer.stop();
			counterRepetitions = 0;
			lblRepetitions.setText(REPETITION_INFO);
			lblTimer.setText(MACRO_DONE);
			isMacroActive = false;
			btnMacro.setText(START_COUNTDOWN);
		    }

		    // Macro has not ended yet.
		    else{
			if(counterLevel == 1 && isA){
			    executeMacro(counterRepetitions, 'a');

			    if(!isB && !isC){				
				if(chbSide.isSelected()){
				    counterRepetitions += 2;
				}else{
				    counterRepetitions += 1;
				}
			    }
			}
			
			if(counterLevel == 2 && isB){
			    executeMacro(counterRepetitions, 'b');

			    if(!isC){				
				if(chbSide.isSelected()){
				    counterRepetitions += 2;
				}else{
				    counterRepetitions += 1;
				}
			    }
			}
			
			if(counterLevel == 3 && isC){
			    executeMacro(counterRepetitions, 'c');

			    if(chbSide.isSelected()){
				counterRepetitions += 2;
			    }else{
				counterRepetitions += 1;
			    }
			    
			}

			int repetitions = (countRepetitions - counterRepetitions);
			lblRepetitions.setText(REPETITION_INFO + repetitions + ".");
			
			counterLevel += 1;

			if(counterLevel > 3){
			    if(isC){
				counterLevel = 3;
			    }
			
			    if(isB){
				counterLevel = 2;
			    }

			    if(isA){
				counterLevel = 1;
			    }
			}
			
		    }
		}
	    });
	macroTimer.setInitialDelay(0);
        macroTimer.setRepeats(true);

	// ===========================================================
	// Macro Panel  
	// ===========================================================

	macroPanel = new JPanel(new GridLayout(4, 1));

	lblInfo = new JLabel(MACRO_INFO);
	macroPanel.add(lblInfo);

	timer = new Timer(1000, new ActionListener() {

		// Timer function to cound down and change the message.
		// When the timer is 0, Execute the macro using the user input.
		public void actionPerformed(ActionEvent e) {

		    // Check if timer is still counting.
		    if(counter <= countDown){
			lblTimer.setText("Timer: "+ (countDown - counter) + "s");
			counter+=1;
		    }

		    // Counting down is over.
		    else{
			counter = 0;

			try{
			    numberFrom = Integer.parseInt(txtFromDigit.getText());
			    numberTo = Integer.parseInt(txtToDigit.getText());
			    firstLetter = txtFirstLetter.getText();
			    secondLetter = txtSecondLetter.getText();
			    isA = chbLevelA.isSelected();
			    isB = chbLevelB.isSelected();
			    isC = chbLevelC.isSelected();

			    if(firstLetter.isEmpty() || secondLetter.isEmpty()){
				throw new Exception();
			    }

			    if(isC){
				counterLevel = 3;
			    }
			
			    if(isB){
				counterLevel = 2;
			    }

			    if(isA){
				counterLevel = 1;
			    }
			    
			    macroTimer.setDelay(countInterval);
			    counterRepetitions = numberFrom;
			    countRepetitions = numberTo;
			    macroTimer.start();
			    
			}
			catch (Exception error){
			    lblTimer.setText(CODE_ERROR);
			    macroTimer.stop();
			    counterRepetitions = 0;
			    lblRepetitions.setText(REPETITION_INFO);
			    isMacroActive = false;
			    btnMacro.setText(START_COUNTDOWN);
			}
			
			lblRepetitions.setText(REPETITION_INFO);
			counter = 0;
			timer.stop();
		    }
		}
	    });
	timer.setInitialDelay(0);
        timer.setRepeats(true);

	btnMacro = new JButton(START_COUNTDOWN);
        btnMacro.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {

		    // Start Macro.
		    if(!isMacroActive){
			
			isMacroActive = true;
			lblConfig.setText("");
			btnMacro.setText(STOP_MACRO);
			macroTimer.stop();
			timer.start();
		    }
		    
		    // Stop Macro.
		    else{
			macroTimer.stop();
			timer.stop();
			counter = 0;
			counterRepetitions = 0;
			isMacroActive = false;
			btnMacro.setText(START_COUNTDOWN);
			lblRepetitions.setText("");
			lblTimer.setText(TIMER_INFO);
		    }
		}
	    });
	macroPanel.add(btnMacro);
	
	lblTimer = new JLabel(TIMER_INFO);
	macroPanel.add(lblTimer);

	lblRepetitions = new JLabel(REPETITION_INFO);
	macroPanel.add(lblRepetitions);

	// ===========================================================
	// CheckBox Levels Panel
	// ===========================================================
	levelPanel = new JPanel(new GridLayout(1, 3));

	lblLevels = new JLabel("Levels to include:");
	levelPanel.add(lblLevels);	

	chbLevelA = new JCheckBox("A");
	levelPanel.add(chbLevelA);

	chbLevelB = new JCheckBox("B");
	levelPanel.add(chbLevelB);

	chbLevelC = new JCheckBox("C");
	levelPanel.add(chbLevelC);
	
	
	// ===========================================================
	// Config Panel
	// ===========================================================

	configPanel = new JPanel(new GridLayout(6, 2));

	lblFirstLetter = new JLabel("First Letter: ");
	configPanel.add(lblFirstLetter);

	txtFirstLetter = new JTextField(10);
	txtFirstLetter.setText("A");
	configPanel.add(txtFirstLetter);

	lblSecondLetter = new JLabel("Second Letter");
	configPanel.add(lblSecondLetter);

	txtSecondLetter = new JTextField(10);
	txtSecondLetter.setText("A");
	configPanel.add(txtSecondLetter);
	
	lblFromDigit = new JLabel("From: ");
	configPanel.add(lblFromDigit);

	txtFromDigit = new JTextField(10);
	txtFromDigit.setText("1");
	configPanel.add(txtFromDigit);
	
	lblToDigit = new JLabel("To: ");
	configPanel.add(lblToDigit);

	txtToDigit = new JTextField(10);
	txtToDigit.setText("2");
	configPanel.add(txtToDigit);

	lblIsSide = new JLabel("");
	configPanel.add(lblIsSide);
		
	chbSide = new JCheckBox("Is Side?");
	configPanel.add(chbSide);

	btnConfig = new JButton(CONFIG_BUTTON);
        btnConfig.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {

		    // Put the inputs on the right variables.
		    try{
			lblConfig.setText(CONFIG_UPDATE);

			// If timer is running when the config is updated, stop timer.
			if(timer.isRunning()){
			    counter = 0;
			    timer.stop();
			    lblTimer.setText(CANCEL_MACRO);
			}
		    }

		    // Some input has a non numeric text. return everyone to the default value.
		    catch (NumberFormatException nfe){
			lblConfig.setText(CONFIG_ERROR);
		    }
		}
	    });
	configPanel.add(btnConfig);

	lblConfig = new JLabel("");
	configPanel.add(lblConfig);	
	

	// ===========================================================
	// Put Panels into frame
	// ===========================================================
	
	frame.getContentPane().add(macroPanel, BorderLayout.NORTH);
	frame.getContentPane().add(levelPanel, BorderLayout.CENTER);
	frame.getContentPane().add(configPanel, BorderLayout.SOUTH);
	frame.setResizable(false);
	frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

    
    @Override
    public void actionPerformed(ActionEvent arg0) {
	// TODO Auto-generated method stub
		
    }


}
