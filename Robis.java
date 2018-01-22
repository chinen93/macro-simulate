import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

public class Robis {
    private Robot robot;

    public Robis(){
	try {
	    this.robot = new Robot();
	} catch (AWTException e) {
	    e.printStackTrace();
	}
    }
    
    public void pressKey(int key) {
	robot.keyPress(key);
	robot.keyRelease(key);
    }

    public void pressEnter(){
	robot.keyPress(KeyEvent.VK_ENTER);
	robot.keyRelease(KeyEvent.VK_ENTER);	
    }
    
    public void pressArrowDown(){
	robot.keyPress(KeyEvent.VK_DOWN);
	robot.keyRelease(KeyEvent.VK_DOWN);	
    }
}
