import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.Timer;


import javax.swing.JFileChooser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.LinkedList;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LocationSimulate implements ActionListener{
    
    // Frame of program.
    private JFrame frame;

    // File variables
    private JFileChooser chooser;
    private LinkedList<String> listCodes = new LinkedList<String>();
    private JButton btnOpen;
    private JLabel lblFile;
    private boolean isFileLoaded;
    
    // Panel for Informations about File content
    private JPanel filePanel;
    private JTextArea txtAreaFileInfo;
    private JScrollPane scroll;

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
    private final int DEFAULT_WIDTH = 500;
    private final int DEFAULT_HEIGH = 800;
    private final int DEFAULT_COUNT_DOWN = 5;
    private final int DEFAULT_COUNT_REPETITION = 1;
    private final int DEFAULT_COUNT_INTERVAL = 100;

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
	new LocationSimulate();
    }
    
    // Function to use the Robis class to press the keys.
    public void executeMacro(int number, char level){
	int hundred = (int) number / 100;
	int decimal = (int) number / 10;
	int unit = number %10;

	// Codigo
	//
	
	robis.pressEnter();
	robis.pressEnter();
	robis.pressEnter();

	for(int i = 0; i < 32; i++){
	    robis.pressTab();
	}

	robis.selectAndPressKey(firstLetter.charAt(0));
	robis.selectAndPressKey(secondLetter.charAt(0));
	robis.selectAndPressKey(hundred);
	robis.selectAndPressKey(decimal);
	robis.selectAndPressKey(unit);
	robis.selectAndPressKey(level);

	for(int i = 0; i < 10; i++){
	    robis.pressTab();
	}

	robis.pressEnter();
	
	robis.selectAndPressKey('p');

	robis.pressEsc();
	robis.pressEsc();
    }
    
    public LocationSimulate()
    {
	// Instanciate Robis;
	robis = new Robis();

	// Create the frame.
	frame = new JFrame("Location Macro");
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

		    if(isFileLoaded){
		    
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
		    
		    else{
			lblFile.setText("Load a File First.");
		    }
		}
	    });
	macroPanel.add(btnMacro);
	
	lblTimer = new JLabel(TIMER_INFO);
	macroPanel.add(lblTimer);

	lblRepetitions = new JLabel(REPETITION_INFO);
	macroPanel.add(lblRepetitions);

	// ===========================================================
	// Informations Panel
	// ===========================================================
	filePanel = new JPanel(new GridLayout(1, 1));

	txtAreaFileInfo = new JTextArea(10, 13);
	txtAreaFileInfo.setEditable(false);

	scroll = new JScrollPane(txtAreaFileInfo);
	scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
	filePanel.add(scroll);	

	// ===========================================================
	// Config Panel
	// ===========================================================

	configPanel = new JPanel(new GridLayout(8, 2));

	lblLevels = new JLabel("Levels to include:");
	configPanel.add(lblLevels);

	levelPanel = new JPanel(new GridLayout(1, 3));
	chbLevelA = new JCheckBox("A");
	levelPanel.add(chbLevelA);
	chbLevelB = new JCheckBox("B");
	levelPanel.add(chbLevelB);
	chbLevelC = new JCheckBox("C");
	levelPanel.add(chbLevelC);
	configPanel.add(levelPanel);

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

	btnOpen = new JButton("Open File");
	btnOpen.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {

		    chooser = new JFileChooser();
		    chooser.setCurrentDirectory(new java.io.File("."));
		    chooser.setSelectedFile(new File(""));
		    chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		    // chooser.setAcceptAllFileFilterUsed(false);
		    if (chooser.showOpenDialog(frame) == JFileChooser.OPEN_DIALOG) {
			File file = chooser.getSelectedFile();
			try{
			    BufferedReader reader = new BufferedReader(new FileReader(file));
			    String line;
			    String[] split;
			    while ((line = reader.readLine()) != null){
				split = line.split(";");
				listCodes.add(split[0]);
				System.out.println(split[0]);
				txtAreaFileInfo.append(split[0] + "\n");
			    }
			    reader.close();
			    isFileLoaded = true;
			    lblFile.setText("File Loaded.");
			}
			catch (Exception fe){
			    System.err.format("Exception occurred trying to read '%s'.", file);
			    fe.printStackTrace();
			    lblFile.setText("");
			    isFileLoaded = false;
			}
		    }
		}
	    });
	configPanel.add(btnOpen);
	
	lblConfig = new JLabel("");
	configPanel.add(lblConfig);

	lblFile = new JLabel("");
	configPanel.add(lblFile);


	// ===========================================================
	// Put Panels into frame
	// ===========================================================
	
	frame.getContentPane().add(macroPanel, BorderLayout.NORTH);
	frame.getContentPane().add(filePanel, BorderLayout.CENTER);
	frame.getContentPane().add(configPanel, BorderLayout.SOUTH);
	frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

    
    @Override
    public void actionPerformed(ActionEvent arg0) {
	// TODO Auto-generated method stub
		
    }


}