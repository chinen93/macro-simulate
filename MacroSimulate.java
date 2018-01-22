import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class MacroSimulate implements ActionListener{

    private JFrame frame;
    private JPanel buttonPanel;
    private JLabel lblCode;
    private JLabel lblTimer;
    private JTextField txtCode;
    private JButton btnCode;
    
    private int width = 600;
    private int heigh = 300;
    private int counter = 0;
    private int counterKeyPress = 0;
    private Timer timer;
    private Timer timerKeyPress;
    private Robis robis;
    
    private final int countDown = 3;
    private final int countRepetitions = 5;
    private final int countDownKeyPress = 1;
    
    public static void main(String[] args) {
	new MacroSimulate();
    }

    public int[] getNumbers(String numString){
	int[] numbers = new int[numString.length()];

	for(int i = 0; i < numbers.length; i++){
	    numbers[i] = Integer.parseInt(""+numString.charAt(i));
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
	    
	    robis.pressArrowDown();
	    counter += 1;
	}
    }


    public MacroSimulate()
    {
	robis = new Robis();
	
        frame = new JFrame("Macro Simulatre");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(width, heigh);

        buttonPanel = new JPanel();
	lblCode = new JLabel("Code to be keypressed automaticaly");
	buttonPanel.add(lblCode);

	txtCode = new JTextField(10);
	buttonPanel.add(txtCode);

	timer = new Timer(1000, new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    if(counter < countDown){
			lblTimer.setText("Timer: "+ (countDown - counter) + "s");
			counter+=1;
		    }else{
			counter = 0;
			
			lblTimer.setText("Execute Macro!");
			
			String text = txtCode.getText();

			executeMacro(text);
			
			timer.stop();
		    }
		}
	    });
	timer.setInitialDelay(0);
        timer.setRepeats(true);

	timerKeyPress = new Timer(10000, new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    if(counter < countDownKeyPress){
			counterKeyPress += 1;
		    }else{
			counterKeyPress = 0;			
			timerKeyPress.stop();
		    }
		}
	    });
	timerKeyPress.setInitialDelay(0);
        timerKeyPress.setRepeats(true);
	
	btnCode = new JButton("Click");
        btnCode.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
		    timer.start();
		}
	    });
	buttonPanel.add(btnCode);


	lblTimer = new JLabel("Algo");
	buttonPanel.add(lblTimer);

        frame.getContentPane().add(buttonPanel, BorderLayout.NORTH);
        frame.setVisible(true);
    }

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}


}
