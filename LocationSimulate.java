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
import javax.swing.text.BadLocationException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.LinkedList;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class LocationSimulate implements ActionListener{

    // Frame of program.
    private JFrame frame;

    // File variables
    private JFileChooser chooser;
    private LinkedList<String> listCodes = new LinkedList<String>();
    private JButton btnOpen;
    private JLabel lblFile;
    private boolean isFileLoaded = false;

    private JPanel colorPanel;
	
    // Panel for Information's about File content
    private JPanel filePanel;
    private JTextArea txtAreaFileInfo;

    // Panel for the Macro core inputs.
    private JPanel macroPanel;
    private JLabel lblInfo;
    private JLabel lblTimer;
    private JLabel lblCode;
    private JLabel lblLocation;
    private JLabel lblPosition;
    private JButton btnInputCode;
    private JButton btnInputLocation;
    private JButton btnMacro;

    // Panel for Config inputs.
    private JPanel configPanel;
    private JLabel lblConfig;
    private JLabel lblFirstLetter;
    private JLabel lblSecondLetter;
    private JLabel lblFromDigit;
    private JLabel lblIsSide;
    private JCheckBox chbSide;
    private JTextField txtFirstLetter;
    private JTextField txtSecondLetter;
    private JTextField txtFromDigit;
    private JButton btnConfig;

    // Panel for CheckBox Config
    private JPanel levelPanel;
    private JLabel lblLevels;
    private JCheckBox chbLevelA;
    private JCheckBox chbLevelB;
    private JCheckBox chbLevelC;

    // Default values
    private final int DEFAULT_WIDTH = 650;
    private final int DEFAULT_HEIGH = 600;
    private final int DEFAULT_COUNT_DOWN = 3;
    private final int DEFAULT_COUNT_REPETITION = 1;
    private final int DEFAULT_COUNT_INTERVAL = 5000;

    // Some default configurations.
    private Timer timer;
    private Timer automaticTimer;
    private Timer macroTimer;
    private Robis robis;
    private int counter = 0, counterLevel;
    private int counterRepetitions = 0;
    private boolean isMacroActive = false;
    private boolean isConfigurated = false;
    private int numberFrom, numberTo;
    private String firstLetter, secondLetter;
    private boolean isA, isB, isC;
    private int position = 1;
    private int option = 0;
    private boolean isPaused = false;

    private int width = DEFAULT_WIDTH;
    private int heigh = DEFAULT_HEIGH;
    private int countDown = DEFAULT_COUNT_DOWN;
    private int countRepetitions = DEFAULT_COUNT_REPETITION;
    private int countInterval = DEFAULT_COUNT_INTERVAL;


    // Systems Strings.
    private final String CANCEL_MACRO = "Macro Cancelled. Try Again.";
    private final String CODE_ERROR = "Empty code, Try again.";
    private final String MACRO_INFO = "Macro to set the location for various codes.";
    private final String CONFIG_BUTTON = "Set Configurations";
    private final String CONFIG_ERROR = "Numbers Only, Try Again";
    private final String CONFIG_UPDATE = "Configurations Updated";
    private final String MACRO_DONE = "Macro Done";
    private final String START_COUNT_CODE = "Start Countdown to Input CODE!";
    private final String START_COUNT_LOCATION = "Start Countdown to Input LOCATION!";
    private final String STOP_MACRO = "Stop Macro!";
    private final String TIMER_INFO = "Timer for Macro Execution";
    private final String REPETITION_INFO = "";
    private String START_COUNTDOWN = "Set Config and Start Countdown to Macro!";

    // Main function to start the program.
    public static void main(String[] args) {
        new LocationSimulate();
    }	
	
    // Function to use the Robis class to press the keys.
    public void executeMacro(String serial, int hundred, int decimal, int unit, char level, int position){

        for(int i=0; i<serial.length(); i++){
            robis.selectAndPressKey(Integer.parseInt(""+serial.charAt(i)));
        }

        robis.pressEnter();
        robis.pressEnter();
        robis.pressEnter();
        
        robis.pressTab();
        robis.pressTab();
        robis.pressTab();
        robis.pressTab();
        robis.pressTab();
        robis.pressTab();
        robis.pressTab();
        robis.pressTab();
        robis.pressTab();
        robis.pressTab();
        
        robis.pressTab();
        robis.pressTab();
        robis.pressTab();
        robis.pressTab();
        robis.pressTab();
        robis.pressTab();
        robis.pressTab();
        robis.pressTab();
        robis.pressTab();
        robis.pressTab();
        
        robis.pressTab();
        robis.pressTab();
        robis.pressTab();
        robis.pressTab();
        robis.pressTab();
        robis.pressTab();
        robis.pressTab();
        robis.pressTab();
        robis.pressTab();
        robis.pressTab();
        
        robis.pressTab();
        robis.pressTab();
        robis.pressTab();
        robis.pressTab();
        robis.pressTab();
        
        robis.selectAndPressKey(firstLetter.charAt(0));
        robis.selectAndPressKey(secondLetter.charAt(0));
        robis.selectAndPressKey(hundred);
        robis.selectAndPressKey(decimal);
        robis.selectAndPressKey(unit);
        robis.selectAndPressKey(level);
        
        robis.pressTab();
        robis.pressTab();
        robis.pressTab();
        robis.pressTab();
        robis.pressTab();
        robis.pressTab();
        robis.pressTab();
        robis.pressTab();
        robis.pressTab();
        robis.pressTab();
        
        robis.pressEnter();
        
        robis.waitSomeTime(2000);

        robis.selectAndPressKey('p');

        robis.pressEsc();
        robis.pressEsc();
    }
	
    public void typeCode(String serial) {

        for(int i=0; i<serial.length(); i++){
            robis.selectAndPressKey(Integer.parseInt(""+serial.charAt(i)));
        }

        robis.pressEnter();
        robis.pressEnter();
        robis.pressEnter();
        
        robis.pressTab();
        robis.pressTab();
        robis.pressTab();
        robis.pressTab();
        robis.pressTab();
        robis.pressTab();
        robis.pressTab();
        robis.pressTab();
        robis.pressTab();
        robis.pressTab();
        
        robis.pressTab();
        robis.pressTab();
        robis.pressTab();
        robis.pressTab();
        robis.pressTab();
        robis.pressTab();
        robis.pressTab();
        robis.pressTab();
        robis.pressTab();
        robis.pressTab();
        
        robis.pressTab();
        robis.pressTab();
        robis.pressTab();
        robis.pressTab();
        robis.pressTab();
        robis.pressTab();
        robis.pressTab();
        robis.pressTab();
        robis.pressTab();
        robis.pressTab();
        
        robis.pressTab();
        robis.pressTab();
        robis.pressTab();
        robis.pressTab();
        robis.pressTab();
    }
	
    public void typeLocation( int number, char level) {
        int hundred = (int) number / 100;
        int decimal = (int) number / 10;
        int unit = number %10;
		
        robis.selectAndPressKey(firstLetter.charAt(0));
        robis.selectAndPressKey(secondLetter.charAt(0));
        robis.selectAndPressKey(hundred);
        robis.selectAndPressKey(decimal);
        robis.selectAndPressKey(unit);
        robis.selectAndPressKey(level);
        
        robis.pressTab();
        robis.pressTab();
        robis.pressTab();
        robis.pressTab();
        robis.pressTab();
        robis.pressTab();
        robis.pressTab();
        robis.pressTab();
        robis.pressTab();
        robis.pressTab();
        
        robis.pressEnter();

        robis.selectAndPressKey('p');
        
        robis.pressEsc();
        robis.pressEsc();
    }	

    public LocationSimulate(){
        // Instantiate Robis;
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
                    if(listCodes.isEmpty() || !isMacroActive){
                        macroTimer.stop();
                        counterRepetitions = 0;
                        position = 1;
                        lblCode.setText(REPETITION_INFO);
                        lblLocation.setText(REPETITION_INFO);
                        lblPosition.setText(REPETITION_INFO);
                        lblTimer.setText(MACRO_DONE);
                        isMacroActive = false;
                        btnMacro.setText(START_COUNTDOWN);
                        isFileLoaded = false;
                        txtAreaFileInfo.selectAll();
                        txtAreaFileInfo.replaceSelection("");
                        lblFile.setText("");
                    }

                    // Macro has not ended yet.
                    else{

                        String code = listCodes.remove();
                        int hundred = (int) counterRepetitions / 100;
                        int decimal = (int) counterRepetitions / 10;
                        int unit = counterRepetitions %10;

                        String location =   firstLetter +
                            secondLetter +
                            Integer.toString(hundred) +
                            Integer.toString(decimal) +
                            Integer.toString(unit);

                        try {
                            int end = txtAreaFileInfo.getLineEndOffset(0);
                            txtAreaFileInfo.replaceRange("", 0, end);
                        }
                        catch (BadLocationException ble) {

                        }

                        if(code.equals("1111111111111")){
                            counterLevel += 1;
                            position = 1;

                            if( (counterLevel > 1 && isA && !isB && !isC) ||
                                (counterLevel > 2 && isB && !isC) ||
                                (counterLevel > 3 && isC)) {

                                if(isC){
                                    counterLevel = 3;
                                }
                                if(isB){
                                    counterLevel = 2;
                                }
                                if(isA){
                                    counterLevel = 1;
                                }

                                if(chbSide.isSelected()) {
                                    counterRepetitions += 2;
                                }else{
                                    counterRepetitions += 1;
                                }
                            }
                        
                            lblTimer.setText("Changing Location...");
                        
                        }else {

                            lblTimer.setText(""); 
                    	
                            if (counterLevel == 1 && isA) {
                        	location += "A";
                                lblCode.setText("Code: "+ code);
                                lblLocation.setText("Loc:  "+ location);
                                lblPosition.setText("Pos:  "+ position);
                                executeMacro(code, hundred, decimal, unit, 'a', position);
                            }

                            if (counterLevel == 2 && isB) {
                        	location += "B";
                                lblCode.setText("Code: "+ code);
                                lblLocation.setText("Loc:  "+ location);
                                lblPosition.setText("Pos:  "+ position);
                                executeMacro(code, hundred, decimal, unit, 'b', position);
                            }

                            if (counterLevel == 3 && isC) {
                        	location += "C";
                                lblCode.setText("Code: "+ code);
                                lblLocation.setText("Loc:  "+ location);
                                lblPosition.setText("Pos:  "+ position);
                                executeMacro(code, hundred, decimal, unit, 'c', position);
                            }

                            position += 1;
                        }
                    }
                }
            });
        macroTimer.setInitialDelay(0);
        macroTimer.setRepeats(true);

        // ===========================================================
        // Macro Panel
        // ===========================================================

        macroPanel = new JPanel(new GridLayout(7, 1));

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
                        timer.stop();

                        // Input Code
                        if(option == 1) {                    	
                            String code = listCodes.remove();

                            try {
                                int end = txtAreaFileInfo.getLineEndOffset(0);
                                txtAreaFileInfo.replaceRange("", 0, end);
                            }
                            catch (BadLocationException ble) {
		
                            }
		
                            if(code.equals("1111111111111")){
                                counterLevel += 1;
                                position = 1;
		
                                if( (counterLevel > 1 && isA && !isB && !isC) ||
                                    (counterLevel > 2 && isB && !isC) ||
                                    (counterLevel > 3 && isC)) {
		
                                    if(isC){
                                        counterLevel = 3;
                                    }
                                    if(isB){
                                        counterLevel = 2;
                                    }
                                    if(isA){
                                        counterLevel = 1;
                                    }
		
                                    if(chbSide.isSelected()) {
                                        counterRepetitions += 2;
		                     
                                    }else{
		                        
                                        counterRepetitions += 1;
                                    }
	                    	}
		                     
                                lblTimer.setText("Changing Location...");
		                         
                            }else {
                                position += 1;
	                 		
                                typeCode(code);
                            }
                    	
                            option = 0;
                        }
                    
                        // Input Location
                        if(option == 2) {

                            if (counterLevel == 1 && isA) {
                                typeLocation(counterRepetitions, 'a');
                            }

                            if (counterLevel == 2 && isB) {
                        	typeLocation(counterRepetitions, 'b');
                            }

                            if (counterLevel == 3 && isC) {
                        	typeLocation(counterRepetitions, 'c');
                            }
                    
                            option = 0;
                        }
                    }
                }
            });
        timer.setInitialDelay(0);
        timer.setRepeats(true);

        btnInputCode = new JButton(START_COUNT_CODE);
        btnInputCode.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {            	
            	
                    if(listCodes.isEmpty()){
            		lblFile.setText("Load a File First.");
                    }
            	
                    if(!isConfigurated){
            		lblConfig.setText("Configurate before usage.");
                    }

                    // Start Macro.
                    if(!isMacroActive && !listCodes.isEmpty() && isConfigurated){
                        lblFile.setText("");
                        lblConfig.setText("");
                    
                        option = 1;
                        timer.start();
                    }
    		}
            });
        btnInputCode.setEnabled(false);
        macroPanel.add(btnInputCode);
        
        btnInputLocation = new JButton(START_COUNT_LOCATION);
        btnInputLocation.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    if(listCodes.isEmpty()){
            		lblFile.setText("Load a File First.");
                    }
            	
                    if(!isConfigurated){
            		lblConfig.setText("Configurate before usage.");
                    }

                    // Start Macro.
                    if(!isMacroActive && !listCodes.isEmpty() && isConfigurated){
                        lblFile.setText("");
                        lblConfig.setText("");
                    
                        option = 2;
                        timer.start();
                    }
    		}
            });
        btnInputLocation.setEnabled(false);
        macroPanel.add(btnInputLocation);

        lblTimer = new JLabel(TIMER_INFO);
        macroPanel.add(lblTimer);

        lblCode = new JLabel(REPETITION_INFO);
        lblCode.setFont(new Font("Serif", Font.BOLD, 22));
        lblCode.setForeground(Color.BLUE);
        macroPanel.add(lblCode);
        lblLocation = new JLabel(REPETITION_INFO);
        lblLocation.setFont(new Font("Serif", Font.BOLD, 22));
        lblLocation.setForeground(Color.BLUE);
        macroPanel.add(lblLocation);
        lblPosition = new JLabel(REPETITION_INFO);
        lblPosition.setFont(new Font("Serif", Font.BOLD, 22));
        lblPosition.setForeground(Color.BLUE);
        macroPanel.add(lblPosition);

        // ===========================================================
        // Informations Panel
        // ===========================================================
        filePanel = new JPanel(new GridLayout(1, 1));

        txtAreaFileInfo = new JTextArea(10, 13);
        txtAreaFileInfo.setEditable(false);
        filePanel.add(txtAreaFileInfo);


        // ===========================================================
        // Config Panel
        // ===========================================================

        configPanel = new JPanel(new GridLayout(8, 2));

        lblLevels = new JLabel("Levels to include:");
        configPanel.add(lblLevels);

        levelPanel = new JPanel(new GridLayout(1, 3));
        chbLevelA = new JCheckBox("A (Low)");
        levelPanel.add(chbLevelA);
        chbLevelB = new JCheckBox("B (Middle)");
        chbLevelB.setSelected(true);
        levelPanel.add(chbLevelB);
        chbLevelC = new JCheckBox("C (High)");
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

        lblIsSide = new JLabel("");
        configPanel.add(lblIsSide);

        chbSide = new JCheckBox("Is Side?");
        chbSide.setSelected(true);
        configPanel.add(chbSide);

        btnConfig = new JButton(CONFIG_BUTTON);
        btnConfig.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    // Put the inputs on the right variables.
               
                    lblConfig.setText(CONFIG_UPDATE);

                    try{
                        numberFrom = Integer.parseInt(txtFromDigit.getText());
                        firstLetter = txtFirstLetter.getText();
                        secondLetter = txtSecondLetter.getText();
                        isA = chbLevelA.isSelected();
                        isB = chbLevelB.isSelected();
                        isC = chbLevelC.isSelected();

                        isConfigurated = true;
                     
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

                        counterRepetitions = numberFrom;
                        countRepetitions = numberTo;                             
                    }
                    catch (Exception error){
                        lblTimer.setText(CODE_ERROR);
                        counterRepetitions = 0;
                        lblCode.setText(REPETITION_INFO);
                        lblLocation.setText(REPETITION_INFO);
                        lblPosition.setText(REPETITION_INFO);
                        isMacroActive = false;
                        btnInputCode.setText(START_COUNT_CODE);
                    }
                }
               
            });
        configPanel.add(btnConfig);

        btnOpen = new JButton("Open File");
        btnOpen.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    listCodes.clear();
                    txtAreaFileInfo.selectAll();
                    txtAreaFileInfo.replaceSelection("");
            	
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

                                if(split[0].equals("1111111111111")) {
                                    txtAreaFileInfo.append(split[0] + " ------------------ \n");
                                }else {
                                    txtAreaFileInfo.append(split[0] + " \n");
                                }
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
        
        configPanel.add(new JLabel(""));
        
        
        automaticTimer = new Timer(1000, new ActionListener() {

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
                   
                        macroTimer.setDelay(countInterval);
                        macroTimer.start();
                    
                        counter = 0;
                        automaticTimer.stop();
                    }
                }
            });
        automaticTimer.setInitialDelay(0);
        automaticTimer.setRepeats(true);
        
        btnMacro = new JButton(START_COUNTDOWN);
        btnMacro.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	
                    if(listCodes.isEmpty()){
                        lblFile.setText("Load a File First.");
                    }
	        	
                    if(!isConfigurated){
                        lblConfig.setText("Configurate before usage.");
                    }
	
                    // Start Macro.
                    if(!isMacroActive && !listCodes.isEmpty() && isConfigurated){
            		lblCode.setForeground(Color.BLUE);
            		lblLocation.setForeground(Color.BLUE);
            		lblPosition.setForeground(Color.BLUE);
                        isFileLoaded = false;
                        isPaused = false;
                        lblFile.setText("");
                        isMacroActive = true;
                        lblConfig.setText("");
                        btnMacro.setText("Pause Macro.");
                        macroTimer.stop();
                        automaticTimer.start();
                    }
	
	            else{
	                if(isMacroActive){
	                    // Stop Macro.
                            //listCodes.clear();
	                    //macroTimer.stop();
	                    //automaticTimer.stop();
	                    //counter = 0;
	                    //counterRepetitions = 0;
	                    //isMacroActive = false;
	                    //btnMacro.setText(START_COUNTDOWN);
	                    //lblRepetitions.setText("");
	                    //lblTimer.setText(TIMER_INFO);
	                    //txtAreaFileInfo.selectAll();
	                    //txtAreaFileInfo.replaceSelection("");
                            if(!isPaused) {
                                // Pause
                                lblCode.setForeground(Color.RED);
                                lblLocation.setForeground(Color.RED);
                                lblPosition.setForeground(Color.RED);
                                macroTimer.stop();
                                automaticTimer.stop();
                                counter = 0;
                                lblTimer.setText("");
                                isPaused = true;
                                btnMacro.setText("Resume Macro.");
                            }else {
                                // Resume
                                lblCode.setForeground(Color.BLUE);
                                lblLocation.setForeground(Color.BLUE);
                                lblPosition.setForeground(Color.BLUE);
                                automaticTimer.start();
                                isPaused = false;
                                btnMacro.setText("Pause Macro.");
                            }
	                }
	            }
                }
            });
        configPanel.add(btnMacro);


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
