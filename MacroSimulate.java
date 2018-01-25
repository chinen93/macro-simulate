import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MacroSimulate implements ActionListener{

    // Frame of program.
    private JFrame frame;

    // Panel for the Macro core inputs.
    private JPanel macroPanel;
    private JLabel lblCode;
    private JLabel lblTimer;
    private JLabel lblRepetitions;
    private JTextField txtCode;
    private JButton btnCode;

    // Panel for Config inputs.
    private JPanel configPanel;
    private JLabel lblConfig;
    private JLabel lblTimerCountDown;
    private JLabel lblNumRepetitions;
    private JLabel lblIntervalToPressDown;
    private JTextField txtTimerCountDown;
    private JTextField txtNumRepetitions;
    private JTextField txtIntervalToPressDown;
    private JButton btnConfig;

    // Default values
    private final int DEFAULT_WIDTH = 600;
    private final int DEFAULT_HEIGH = 300;
    private final int DEFAULT_COUNT_DOWN = 5;
    private final int DEFAULT_COUNT_REPETITION = 1;
    private final int DEFAULT_COUNT_INTERVAL = 250;

    // Some default configurations.
    private Timer timer;
    private Timer macroTimer;
    private Robis robis;
    private String code;
    private int counter = 0;
    private int counterRepetitions = 0;
    private boolean isMacroActive = false;
    
    private int width = DEFAULT_WIDTH;
    private int heigh = DEFAULT_HEIGH;
    private int countDown = DEFAULT_COUNT_DOWN;
    private int countRepetitions = DEFAULT_COUNT_REPETITION;
    private int countInterval = DEFAULT_COUNT_INTERVAL;


    // Systems Strings.
    private String CANCEL_MACRO = "Macro Cancelled. Try Again.";
    private String CODE_ERROR = "Empty code, Try again.";
    private String CODE_INFO = "Code to be keypressed automaticaly";
    private String CONFIG_BUTTON = "Set Configurations";
    private String CONFIG_ERROR = "Numbers Only, Try Again";
    private String CONFIG_INTERVAL = "Interval to Press Down (1s = 1000)";
    private String CONFIG_REPETITION = "Number or Repetitions";
    private String CONFIG_TIMER = "Timer Count Down";
    private String CONFIG_UPDATE = "Configurations Updated";
    private String MACRO_DONE = "Macro Done";
    private String START_COUNTDOWN = "Set Config and Start Countdown to Macro!";
    private String STOP_MACRO = "Stop Macro!";
    private String SYSTEM_TITLE = "Macro Simulatre";
    private String TIMER_ERROR = "Code is numeric only. Try Again!";
    private String TIMER_INFO = "Timer for Macro Execution";

    // Main function to start the program.
    public static void main(String[] args) {
	new MacroSimulate();
    }

    // Function to tranform a String of
    //Numbers into a Array of int.
    public int[] getNumbers(String numString){

	// Array of int to return.
	int[] numbers = new int[numString.length()];

	for(int i = 0; i < numbers.length; i++){

	    // Try to convert every char to a int.
	    try{
		numbers[i] = Integer.parseInt(""+numString.charAt(i));
	    }

	    // Convertion doesn't work. Return an empty array.
	    catch (NumberFormatException e){
		lblTimer.setText(TIMER_ERROR);
		return new int[0];
	    }
	       
	}
	
	return numbers;
    }

    // Function to use the Robis class to press the keys.
    public void executeMacro(String code){
	int[] numbers = getNumbers(code);

	for(int i = 0; i < numbers.length; i++){

	    // Check which key to press.
	    switch(numbers[i]){
	    case 0:
		robis.pressKey(KeyEvent.VK_0);
		break;
	    case 1:
		robis.pressKey(KeyEvent.VK_1);
		break;
	    case 2:
		robis.pressKey(KeyEvent.VK_2);
		break;
	    case 3:
		robis.pressKey(KeyEvent.VK_3);
		break;
	    case 4:
		robis.pressKey(KeyEvent.VK_4);
		break;
	    case 5:
		robis.pressKey(KeyEvent.VK_5);
		break;
	    case 6:
		robis.pressKey(KeyEvent.VK_6);
		break;
	    case 7:
		robis.pressKey(KeyEvent.VK_7);
		break;
	    case 8:
		robis.pressKey(KeyEvent.VK_8);
		break;
	    case 9:
		robis.pressKey(KeyEvent.VK_9);
		break;
	    default:
		break;
	    }
	}
	robis.pressArrowDown();
    }


    public MacroSimulate()
    {

	// Instanciate Robis;
	robis = new Robis();

	// Create the frame.
	frame = new JFrame(SYSTEM_TITLE);
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
		    if(counterRepetitions >= countRepetitions){
			macroTimer.stop();
			counterRepetitions = 0;
			lblRepetitions.setText("");
			lblTimer.setText(MACRO_DONE);
			isMacroActive = false;
			btnCode.setText(START_COUNTDOWN);
		    }

		    // Macro has not ended yet.
		    else{
			executeMacro(code);
			int repetitions = (countRepetitions - counterRepetitions - 1);
			lblRepetitions.setText(""+ repetitions + " repetitions left.");
			counterRepetitions += 1;
		    }
		}
	    });
	macroTimer.setInitialDelay(0);
        macroTimer.setRepeats(true);
	

	// ===========================================================
	// Macro Panel  
	// ===========================================================
	
        macroPanel = new JPanel(new GridLayout(5, 1));

	lblCode = new JLabel(CODE_INFO);
	macroPanel.add(lblCode);

	txtCode = new JTextField(10);
	macroPanel.add(txtCode);

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

			code = txtCode.getText();
			
			// input text is not empty.
			if(!code.isEmpty()){
			    macroTimer.setDelay(countInterval);
			    counterRepetitions = 0;
			    macroTimer.start();
			}

			// input text is empty.
			else{
			    
			    lblTimer.setText(CODE_ERROR);
			    macroTimer.stop();
			    counterRepetitions = 0;
			    lblRepetitions.setText("");
			    isMacroActive = false;
			    btnCode.setText(START_COUNTDOWN);
			}
			
			lblRepetitions.setText("");
			counter = 0;
			timer.stop();
		    }
		}
	    });
	timer.setInitialDelay(0);
        timer.setRepeats(true);
	
	btnCode = new JButton(START_COUNTDOWN);
        btnCode.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {

		    // Start Macro.
		    if(!isMacroActive){
			
			// Put the inputs on the right variables.
			try{
			    countDown = Integer.parseInt(txtTimerCountDown.getText());
			    countRepetitions = Integer.parseInt(txtNumRepetitions.getText());
			    countInterval = Integer.parseInt(txtIntervalToPressDown.getText());
			    lblConfig.setText(CONFIG_UPDATE);

			    isMacroActive = true;
			    lblConfig.setText("");
			    btnCode.setText(STOP_MACRO);
			    macroTimer.stop();
			    timer.start();
			    
			}

			// Some input has a non numeric text. return
			// everyone to the default value.
			catch (NumberFormatException nfe){
			    txtTimerCountDown.setText(""+countDown);
			    txtNumRepetitions.setText(""+countRepetitions);
			    txtIntervalToPressDown.setText(""+countInterval);
			    lblConfig.setText(CONFIG_ERROR);
			}
		    }
		    
		    // Stop Macro.
		    else{
			macroTimer.stop();
			timer.stop();
			counter = 0;
			counterRepetitions = 0;
			isMacroActive = false;
			btnCode.setText(START_COUNTDOWN);
			lblRepetitions.setText("");
			lblTimer.setText(TIMER_INFO);
		    }
		}
	    });
	macroPanel.add(btnCode);


	lblTimer = new JLabel(TIMER_INFO);
	macroPanel.add(lblTimer);

	lblRepetitions = new JLabel("");
	macroPanel.add(lblRepetitions);

	// ===========================================================
	// Config Panel
	// ===========================================================

	configPanel = new JPanel(new GridLayout(4, 2));

	lblTimerCountDown = new JLabel(CONFIG_TIMER);
	configPanel.add(lblTimerCountDown);

	txtTimerCountDown = new JTextField(10);
	txtTimerCountDown.setText(""+countDown);
	configPanel.add(txtTimerCountDown);

	lblNumRepetitions = new JLabel(CONFIG_REPETITION);
	configPanel.add(lblNumRepetitions);

	txtNumRepetitions = new JTextField(10);
	txtNumRepetitions.setText(""+countRepetitions);
	configPanel.add(txtNumRepetitions);

	lblIntervalToPressDown = new JLabel(CONFIG_INTERVAL);
	configPanel.add(lblIntervalToPressDown);

	txtIntervalToPressDown = new JTextField(10);
	txtIntervalToPressDown.setText(""+countInterval);
	configPanel.add(txtIntervalToPressDown);

	btnConfig = new JButton(CONFIG_BUTTON);
        btnConfig.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {

		    // Put the inputs on the right variables.
		    try{
			countDown = Integer.parseInt(txtTimerCountDown.getText());
			countRepetitions = Integer.parseInt(txtNumRepetitions.getText());
			countInterval = Integer.parseInt(txtIntervalToPressDown.getText());
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
			txtTimerCountDown.setText(""+countDown);
			txtNumRepetitions.setText(""+countRepetitions);
			txtIntervalToPressDown.setText(""+countInterval);
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
