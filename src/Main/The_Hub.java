package Main;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import Entities.Entity;
import Entities.Humanoid;
import Entities.NPC;
import Entities.NPCsRelic;
import Entities.Nec;
import Entities.Player;
import Entities.Player2;
import Entities.Skelly;
import FightingSystem.fightMenus;
import FightingSystem.healthManager;
import Object.object;
import Tile.TileManager;

import java.awt.AlphaComposite;
import java.awt.Color;
public class The_Hub extends JPanel  implements Runnable{
    public final int ogTileSize=16;
    public final int scale=3;
    public final int resTileSize=ogTileSize*scale;
    public final int maxScreenHoriz=20;
    public final int maxScreenVert=12;
    public final int screenWidth=resTileSize*maxScreenHoriz;
    public final int screenHeight=resTileSize*maxScreenVert;
    public int maxWorldVert=19;
    public int maxWorldHoriz=25;
    public int maxWorldHeight=maxWorldVert*resTileSize;
    public int maxWorldWidth=maxWorldHoriz*resTileSize;
    public int fightHeight=72;
    public int fightWidth=85;
    public int skelHealth=25;
    public int necHealth=10;
    public boolean fight=false;
    public boolean defend=false;
    public boolean item=false;
    public boolean flee=false;
    public boolean charSelected=false;
    public boolean charFight=false;
    public boolean charDefend=false;
    public boolean charItem=false;
    public boolean charFlee=false;
    public boolean p2Active=true;
    public int wPlayer=1;
    public int gSelectedX=0;
    public int gSelectedY=0;
    public int gameState;
    public int playState=1;
    public int pauseState=2;
    public int screenWidth2=screenWidth;
    public int screenHeight2=screenHeight;
    public BufferedImage tempScreen;
    Graphics2D g2;
    final int FPS=60;
    Thread gameThread;
    public Sound sound=new Sound();
    public TileManager tileguy=new TileManager(this);
    public TextReader textboi=new TextReader(this);
    public keyInput keyBoi=new keyInput();
    NPC npc=new NPC(this, textboi);
    public Player player=new Player(this, keyBoi, npc, tileguy);
    keyInput2 keyBoi2=new keyInput2();
    public Player2 player2=new Player2(this, keyBoi2, player, npc, textboi);
    healthManager health=new healthManager(this, player);
    public fightMenus fightingboi=new fightMenus(this, player, keyBoi, textboi, player2, health, npc);
    public objectHandler objHandler=new objectHandler(this);
    public CollisionChecker cChecker=new CollisionChecker(this);
    public object obj[]=new object[10];
    public object Exits[]=new object[10];
    public Entity[] Players=new Entity[]{
        player,
        player2
    };
    public The_Hub() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyBoi);
        this.addKeyListener(keyBoi2);
        this.setFocusable(true);
    }
    public void setupGame() {
        objHandler.setObject();
        if (player.fightMode==false) {
            playMusic(0);
        }
        else {
            playMusic(1);
        }
        gameState=playState;
        tempScreen=new BufferedImage(screenWidth, screenHeight, BufferedImage.TYPE_INT_ARGB);
        g2=(Graphics2D)tempScreen.getGraphics();
        //setFullScreen();
    }
    //note: Setting it to full screen will lag tremendously. In order to switch to fullscreen, dont use paintcomponent. use drawtotempscreen and drawtoscreen.
    public void setFullScreen() {
        GraphicsEnvironment ge=GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd=ge.getDefaultScreenDevice();
        gd.setFullScreenWindow(BasicSetup.window);
        screenWidth2=BasicSetup.window.getWidth();
        screenHeight2=BasicSetup.window.getHeight();
    }
    public void startGameThread() {
        gameThread=new Thread(this);
        gameThread.start();
    }
    @Override
    public void run() {
        double drawInterval=1000000000/FPS;
        double nextDrawTime=System.nanoTime()+drawInterval;
        while (gameThread!=null) {
            update();
            repaint();
            //drawToTempScreen();
            //drawToScreen();
            try {
                double remainingTime=nextDrawTime-System.nanoTime();
                remainingTime/=1000000;
                if(remainingTime<0) {
                    remainingTime=0;
                }
                Thread.sleep((long) remainingTime);
                nextDrawTime+=drawInterval;
            } catch (InterruptedException e) {
                
                e.printStackTrace();
            }
            
        }
    }
    public void update() {
        if (gameState==playState) {
            player2.update();
        player.update();
        npc.update();
        for (int i=0; i<npc.entity.length; i++) {
            if (npc.entity[i]!=null) {
                npc.entity[i].update();
            }
        }
        }

    }
    public void drawToTempScreen() {
        if (player.fadeToBlack==true) {
            float opacity = Math.max(0.0f, Math.min(1.0f, player.opacity));
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));
            g2.setColor(Color.BLACK);
            g2.fillRect(0, 0, screenWidth, screenHeight);
        }
        tileguy.draw(g2);
        for (int i=0;i<obj.length; i++) {
            if (obj[i]!=null) {
                if (obj[i].locationX==player.XLevel && obj[i].locationY==player.YLevel) {
                    obj[i].draw(g2,this);
                }
            }
        }
        for (int i=0;i<npc.entity.length; i++) {
            if (npc.entity[i]!=null) {
                npc.entity[i].draw(g2);
            }
        }
        player2.draw(g2);
        player.draw(g2);
        npc.draw(g2);
            fightingboi.draw(g2);
    }
     public void paintComponent(Graphics g)  {
        
       
        super.paintComponent(g);
        Graphics2D g2=(Graphics2D)g;
        if (player.fadeToBlack==true) {
            float opacity = Math.max(0.0f, Math.min(1.0f, player.opacity));
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));
            g2.setColor(Color.BLACK);
            g2.fillRect(0, 0, screenWidth, screenHeight);
        }
        tileguy.draw(g2);
        for (int i=0;i<obj.length; i++) {
            if (obj[i]!=null) {
                if (obj[i].locationX==player.XLevel && obj[i].locationY==player.YLevel) {
                    obj[i].draw(g2,this);
                }
            }
        }
        for (int i=0;i<npc.entity.length; i++) {
            if (npc.entity[i]!=null) {
                npc.entity[i].draw(g2);
            }
        }
        player2.draw(g2);
        player.draw(g2);
        npc.draw(g2);
            fightingboi.draw(g2);
        g2.dispose();

    }
    public void drawToScreen() {
        Graphics g=getGraphics();
        g.drawImage(tempScreen, 0, 0, screenWidth2, screenHeight2, null);
        g.dispose();
    }
    public void playMusic(int i) {
        sound.playBackgroundMusic(i);
    }
    public void stopMusic() {
        sound.stopBackgroundMusic();
    }
    public void soundEffect(int i) {
        sound.playSoundEffect(i);
    }

}
