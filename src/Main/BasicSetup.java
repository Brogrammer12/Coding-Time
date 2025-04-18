package Main;

import javax.swing.JFrame;

public class BasicSetup {
    public static JFrame window;
    public static void main(String[] args){
        window=new JFrame(); 
        window.setUndecorated(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
        window.setResizable(false);
        window.setTitle("HUMANOID");
        window.setLocationRelativeTo(null);
        The_Hub JPwindow=new The_Hub();
        window.add(JPwindow);
        window.pack();
        JPwindow.setupGame();
        JPwindow.startGameThread();
    }
}
