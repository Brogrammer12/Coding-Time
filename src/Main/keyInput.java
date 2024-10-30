package Main;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
public class keyInput implements KeyListener{
    public boolean upPressed, leftPressed, downPressed, rightPressed;
    public boolean hasMoved=false;
    public boolean enterPressed;
    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int KeyCode=e.getKeyCode();
            if(KeyCode==KeyEvent.VK_W) {
                upPressed=true;
            }
            if(KeyCode==KeyEvent.VK_A) {
                leftPressed=true;
                
            }
            if(KeyCode==KeyEvent.VK_S) {
                downPressed=true;
            }
            if(KeyCode==KeyEvent.VK_D) {
                rightPressed=true;
                
            }
            if(KeyCode==KeyEvent.VK_ENTER) {
                enterPressed=true;
            }
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int KeyCode=e.getKeyCode();
        if(KeyCode==KeyEvent.VK_W) {
            upPressed=false;
        }
        if(KeyCode==KeyEvent.VK_A) {
            leftPressed=false;
            hasMoved=false;
        }
        if(KeyCode==KeyEvent.VK_S) {
            downPressed=false;
        }
        if(KeyCode==KeyEvent.VK_D) {
            rightPressed=false;
            hasMoved=false;
        }
        if(KeyCode==KeyEvent.VK_ENTER) {
            enterPressed=false;
        }
    }

}
