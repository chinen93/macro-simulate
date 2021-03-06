import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

public class Robis {
    private Robot robot;

    public Robis(){
	try {
	    this.robot = new Robot();
	    this.robot.setAutoDelay(5);
	} catch (AWTException e) {
	    e.printStackTrace();
	}
    }
    
    public void waitSomeTime(int ms) {
    	robot.delay(ms);
    }
    
    public void pressKey(int key) {
	robot.keyPress(key);
	robot.keyRelease(key);
    }

    public void pressEnter(){
	robot.keyPress(KeyEvent.VK_ENTER);
	robot.keyRelease(KeyEvent.VK_ENTER);
    }

    public void pressTab(){
	robot.keyPress(KeyEvent.VK_TAB);
	robot.keyRelease(KeyEvent.VK_TAB);
    }

    public void pressEsc(){
	robot.keyPress(KeyEvent.VK_ESCAPE);
	robot.keyRelease(KeyEvent.VK_ESCAPE);
    }
    
    public void pressArrowDown(){
	robot.keyPress(KeyEvent.VK_DOWN);
	robot.keyRelease(KeyEvent.VK_DOWN);
    }

    public void selectAndPressKey(int key){
	// Check which key to press.
	switch(key){
	case 0:
	    robot.keyPress(KeyEvent.VK_0);
	    robot.keyRelease(KeyEvent.VK_0);
	    break;
	case 1:
	    robot.keyPress(KeyEvent.VK_1);
	    robot.keyRelease(KeyEvent.VK_1);
	    break;
	case 2:
	    robot.keyPress(KeyEvent.VK_2);
	    robot.keyRelease(KeyEvent.VK_2);
	    break;
	case 3:
	    robot.keyPress(KeyEvent.VK_3);
	    robot.keyRelease(KeyEvent.VK_3);
	    break;
	case 4:
	    robot.keyPress(KeyEvent.VK_4);
	    robot.keyRelease(KeyEvent.VK_4);
	    break;
	case 5:
	    robot.keyPress(KeyEvent.VK_5);
	    robot.keyRelease(KeyEvent.VK_5);
	    break;
	case 6:
	    robot.keyPress(KeyEvent.VK_6);
	    robot.keyRelease(KeyEvent.VK_6);
	    break;
	case 7:
	    robot.keyPress(KeyEvent.VK_7);
	    robot.keyRelease(KeyEvent.VK_7);
	    break;
	case 8:
	    robot.keyPress(KeyEvent.VK_8);
	    robot.keyRelease(KeyEvent.VK_8);
	    break;
	case 9:
	    robot.keyPress(KeyEvent.VK_9);
	    robot.keyRelease(KeyEvent.VK_9);
	    break;
	default:
	    break;
	}
    }
    
    public void selectAndPressKey(char key){
	// Check which key to press.
	switch(Character.toLowerCase(key)){
	case '0':
	    robot.keyPress(KeyEvent.VK_0);
	    robot.keyRelease(KeyEvent.VK_0);
	    break;
	case '1':
	    robot.keyPress(KeyEvent.VK_1);
	    robot.keyRelease(KeyEvent.VK_1);
	    break;
	case '2':
	    robot.keyPress(KeyEvent.VK_2);
	    robot.keyRelease(KeyEvent.VK_2);
	    break;
	case '3':
	    robot.keyPress(KeyEvent.VK_3);
	    robot.keyRelease(KeyEvent.VK_3);
	    break;
	case '4':
	    robot.keyPress(KeyEvent.VK_4);
	    robot.keyRelease(KeyEvent.VK_4);
	    break;
	case '5':
	    robot.keyPress(KeyEvent.VK_5);
	    robot.keyRelease(KeyEvent.VK_5);
	    break;
	case '6':
	    robot.keyPress(KeyEvent.VK_6);
	    robot.keyRelease(KeyEvent.VK_6);
	    break;
	case '7':
	    robot.keyPress(KeyEvent.VK_7);
	    robot.keyRelease(KeyEvent.VK_7);
	    break;
	case '8':
	    robot.keyPress(KeyEvent.VK_8);
	    robot.keyRelease(KeyEvent.VK_8);
	    break;
	case '9':
	    robot.keyPress(KeyEvent.VK_9);
	    robot.keyRelease(KeyEvent.VK_9);
	    break;
	case 'a':
	    robot.keyPress(KeyEvent.VK_A);
	    robot.keyRelease(KeyEvent.VK_A);
	    break;
	case 'b':
	    robot.keyPress(KeyEvent.VK_B);
	    robot.keyRelease(KeyEvent.VK_B);
	    break;
	case 'c':
	    robot.keyPress(KeyEvent.VK_C);
	    robot.keyRelease(KeyEvent.VK_C);
	    break;
	case 'd':
	    robot.keyPress(KeyEvent.VK_D);
	    robot.keyRelease(KeyEvent.VK_D);
	    break;
	case 'e':
	    robot.keyPress(KeyEvent.VK_E);
	    robot.keyRelease(KeyEvent.VK_E);
	    break;
	case 'f':
	    robot.keyPress(KeyEvent.VK_F);
	    robot.keyRelease(KeyEvent.VK_F);
	    break;
	case 'g':
	    robot.keyPress(KeyEvent.VK_G);
	    robot.keyRelease(KeyEvent.VK_G);
	    break;
	case 'h':
	    robot.keyPress(KeyEvent.VK_H);
	    robot.keyRelease(KeyEvent.VK_H);
	    break;
	case 'i':
	    robot.keyPress(KeyEvent.VK_I);
	    robot.keyRelease(KeyEvent.VK_I);
	    break;
	case 'j':
	    robot.keyPress(KeyEvent.VK_J);
	    robot.keyRelease(KeyEvent.VK_J);
	    break;
	case 'k':
	    robot.keyPress(KeyEvent.VK_K);
	    robot.keyRelease(KeyEvent.VK_K);
	    break;
	case 'l':
	    robot.keyPress(KeyEvent.VK_L);
	    robot.keyRelease(KeyEvent.VK_L);
	    break;
	case 'm':
	    robot.keyPress(KeyEvent.VK_M);
	    robot.keyRelease(KeyEvent.VK_M);
	    break;
	case 'n':
	    robot.keyPress(KeyEvent.VK_N);
	    robot.keyRelease(KeyEvent.VK_N);
	    break;
	case 'o':
	    robot.keyPress(KeyEvent.VK_O);
	    robot.keyRelease(KeyEvent.VK_O);
	    break;
	case 'p':
	    robot.keyPress(KeyEvent.VK_P);
	    robot.keyRelease(KeyEvent.VK_P);
	    break;
	case 'q':
	    robot.keyPress(KeyEvent.VK_Q);
	    robot.keyRelease(KeyEvent.VK_Q);
	    break;
	case 'r':
	    robot.keyPress(KeyEvent.VK_R);
	    robot.keyRelease(KeyEvent.VK_R);
	    break;
	case 's':
	    robot.keyPress(KeyEvent.VK_S);
	    robot.keyRelease(KeyEvent.VK_S);
	    break;
	case 't':
	    robot.keyPress(KeyEvent.VK_T);
	    robot.keyRelease(KeyEvent.VK_T);
	    break;
	case 'u':
	    robot.keyPress(KeyEvent.VK_U);
	    robot.keyRelease(KeyEvent.VK_U);
	    break;
	case 'v':
	    robot.keyPress(KeyEvent.VK_V);
	    robot.keyRelease(KeyEvent.VK_V);
	    break;
	case 'w':
	    robot.keyPress(KeyEvent.VK_W);
	    robot.keyRelease(KeyEvent.VK_W);
	    break;
	case 'x':
	    robot.keyPress(KeyEvent.VK_X);
	    robot.keyRelease(KeyEvent.VK_X);
	    break;
	case 'y':
	    robot.keyPress(KeyEvent.VK_Y);
	    robot.keyRelease(KeyEvent.VK_Y);
	    break;
	case 'z':
	    robot.keyPress(KeyEvent.VK_Z);
	    robot.keyRelease(KeyEvent.VK_Z);
	    break;
	default:
	    break;
	}
    }
}
