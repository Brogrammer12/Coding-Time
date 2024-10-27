package Main;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
public class keyInput2 implements KeyListener{
    public boolean upPressed, leftPressed, downPressed, rightPressed;

    @Override
    public void keyTyped(KeyEvent e) {
        
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int KeyCode=e.getKeyCode();
        if(KeyCode==KeyEvent.VK_I) {
            upPressed=true;
        }
        if(KeyCode==KeyEvent.VK_J) {
            leftPressed=true;
        }
        if(KeyCode==KeyEvent.VK_K) {
            downPressed=true;
        }
        if(KeyCode==KeyEvent.VK_L) {
            rightPressed=true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int KeyCode=e.getKeyCode();
        if(KeyCode==KeyEvent.VK_I) {
            upPressed=false;
        }
        if(KeyCode==KeyEvent.VK_J) {
            leftPressed=false;
        }
        if(KeyCode==KeyEvent.VK_K) {
            downPressed=false;
        }
        if(KeyCode==KeyEvent.VK_L) {
            rightPressed=false;
        }
    }

}
