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

    private JFrame frame;
    private JPanel macroPanel;
    private JLabel lblCode;
    private JLabel lblTimer;
    private JTextField txtCode;
    private JButton btnCode;

    private JPanel configPanel;
    private JLabel lblConfig;
    private JLabel lblTimerCountDown;
    private JLabel lblNumRepetitions;
    private JLabel lblIntervalToPressDown;
    private JTextField txtTimerCountDown;
    private JTextField txtNumRepetitions;
    private JTextField txtIntervalToPressDown;
    private JButton btnConfig;
    
    private int width = 600;
    private int heigh = 300;
    private int counter = 0;
    private int counterKeyPress = 0;
    private Timer timer;
    private Robis robis;
    
    private int countDown = 3;
    private int countRepetitions = 1;
    private int countDownKeyPress = 500;

    
    public static void main(String[] args) {
	new MacroSimulate();
    }

    public int[] getNumbers(String numString){
	int[] numbers = new int[numString.length()];

	for(int i = 0; i < numbers.length; i++){
	    try{
		numbers[i] = Integer.parseInt(""+numString.charAt(i));
	    }catch (NumberFormatException e){
		lblTimer.setText("Code is numeric only. Try Again!");
		return new int[0];
	    }
	       
	}
	
	return numbers;
    }

    public void executeMacro(String code){
	int counter = 0;
	int[] numbers = getNumbers(code);

	while(counter < countRepetitions){
	    for(int i = 0; i < numbers.length; i++){
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
	robis = new Robis();

	countDown = 3;         
	countRepetitions = 1; 
        countDownKeyPress = 500;

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
		public void actionPerformed(ActionEvent e) {
		    if(counter <= countDown){
			lblTimer.setText("Timer: "+ (countDown - counter) + "s");
			counter+=1;
		    }else{
			counter = 0;
			
			String text = txtCode.getText();

			executeMacro(text);

			lblTimer.setText("Macro Done!");
			
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
		    try{
			countDown = Integer.parseInt(txtTimerCountDown.getText());
			countRepetitions = Integer.parseInt(txtNumRepetitions.getText());
			countDownKeyPress = Integer.parseInt(txtIntervalToPressDown.getText());
			lblConfig.setText("Configurations Updated");
			if(timer.isRunning()){
			    counter = 0;
			    timer.stop();
			    lblTimer.setText("Macro cancelled. Try Again.");
			}
		    }catch (NumberFormatException nfe){
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
