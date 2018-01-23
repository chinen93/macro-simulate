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

    // Some default configurations.
    private Timer timer;
    private Robis robis;
    private int width = 600;
    private int heigh = 300;
    private int counter = 0;
    private int countDown = 3;
    private int countRepetitions = 1;
    private int countDownKeyPress = 500;

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
		lblTimer.setText("Code is numeric only. Try Again!");
		return new int[0];
	    }
	       
	}
	
	return numbers;
    }

    // Function to use the Robis class to press the keys.
    public void executeMacro(String code){
	int counter = 0;
	int[] numbers = getNumbers(code);

	while(counter < countRepetitions){
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
	    // Sleep for some time to let the program to register those keypress.
	    try {
		Thread.sleep(countDownKeyPress);
	    } catch (InterruptedException e) {
		e.printStackTrace();
	    }
	    robis.pressArrowDown();
	    counter += 1;
	}
    }


    public MacroSimulate()
    {

	// Instanciate Robis;
	robis = new Robis();

	// Put some defaults.
	countDown = 3;         
	countRepetitions = 1; 
        countDownKeyPress = 500;

	// Create the frame.
	frame = new JFrame("Macro Simulatre");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(width, heigh);


	// ===========================================================
	// Macro Panel  
	// ===========================================================
	
        macroPanel = new JPanel(new GridLayout(4, 1));

	lblCode = new JLabel("Code to be keypressed automaticaly");
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
			
			String text = txtCode.getText();

			// input text is not empty.
			if(!text.isEmpty()){
			    executeMacro(text);

			    lblTimer.setText("Macro Done!");
			}

			// input text is empty.
			else{
			    
			    lblTimer.setText("Empty code, Try again.");
			}
			
			timer.stop();
		    }
		}
	    });
	timer.setInitialDelay(0);
        timer.setRepeats(true);
	
	btnCode = new JButton("Start Countdown to Macro!");
        btnCode.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
		    lblConfig.setText("");
		    timer.start();
		}
	    });
	macroPanel.add(btnCode);


	lblTimer = new JLabel("Timer for macro execution");
	macroPanel.add(lblTimer);


	// ===========================================================
	// Config Panel
	// ===========================================================

	configPanel = new JPanel(new GridLayout(4, 2));

	lblTimerCountDown = new JLabel("Timer Count Down");
	configPanel.add(lblTimerCountDown);

	txtTimerCountDown = new JTextField(10);
	txtTimerCountDown.setText(""+countDown);
	configPanel.add(txtTimerCountDown);

	lblNumRepetitions = new JLabel("Number or Repetitions");
	configPanel.add(lblNumRepetitions);

	txtNumRepetitions = new JTextField(10);
	txtNumRepetitions.setText(""+countRepetitions);
	configPanel.add(txtNumRepetitions);

	lblIntervalToPressDown = new JLabel("Interval to Press Down (1s = 1000)");
	configPanel.add(lblIntervalToPressDown);

	txtIntervalToPressDown = new JTextField(10);
	txtIntervalToPressDown.setText(""+countDownKeyPress);
	configPanel.add(txtIntervalToPressDown);

	btnConfig = new JButton("Set Configurations");
        btnConfig.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {

		    // Put the inputs on the right variables.
		    try{
			countDown = Integer.parseInt(txtTimerCountDown.getText());
			countRepetitions = Integer.parseInt(txtNumRepetitions.getText());
			countDownKeyPress = Integer.parseInt(txtIntervalToPressDown.getText());
			lblConfig.setText("Configurations Updated");

			// If timer is running when the config is updated, stop timer.
			if(timer.isRunning()){
			    counter = 0;
			    timer.stop();
			    lblTimer.setText("Macro cancelled. Try Again.");
			}
		    }

		    // Some input has a non numeric text. return everyone to the default value.
		    catch (NumberFormatException nfe){
			countDown = 3;
			countRepetitions = 1;
			countDownKeyPress = 500;
			txtTimerCountDown.setText(""+countDown);
			txtNumRepetitions.setText(""+countRepetitions);
			txtIntervalToPressDown.setText(""+countDownKeyPress);
			lblConfig.setText("Numbers Only, Try Again");
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
