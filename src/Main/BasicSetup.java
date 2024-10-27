package Main;

import javax.swing.JFrame;

public class BasicSetup {
    
    public static void main(String[] args){
        JFrame JFwindow=new JFrame(); 
        JFwindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JFwindow.setVisible(true);
        JFwindow.setResizable(false);
        JFwindow.setTitle("HUMANOID");
        JFwindow.setLocationRelativeTo(null);
        The_Hub JPwindow=new The_Hub();
        JFwindow.add(JPwindow);
        JFwindow.pack();
        JPwindow.startGameThread();
    }
}
