package Main;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

import Entities.Entity;
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

import java.awt.Color;
public class The_Hub extends JPanel  implements Runnable{
    public final int ogTileSize=16;
    public final int scale=3;
    public final int resTileSize=ogTileSize*scale;
    public final int maxScreenHoriz=16;
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
    final int FPS=60;
    Thread gameThread;
    public Sound sound=new Sound();
    public TileManager tileguy=new TileManager(this);
    public TextReader textboi=new TextReader(this);
    public keyInput keyBoi=new keyInput();
    Nec necromancer=new Nec(this);
    Skelly skellywag=new Skelly(this);
    NPC npc=new NPC(this, skellywag, necromancer, textboi);
    public Player player=new Player(this, keyBoi, npc, tileguy);
    keyInput2 keyBoi2=new keyInput2();
    public Player2 player2=new Player2(this, keyBoi2, player, npc, textboi);
    healthManager health=new healthManager(this, player);
    public fightMenus fightingboi=new fightMenus(this, player, keyBoi, textboi, player2, health, npc);
    public objectHandler objHandler=new objectHandler(this);
    public CollisionChecker cChecker=new CollisionChecker(this);
    public object obj[]=new object[10];
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
        player2.update();
        player.update();
        npc.update();

    }
    public void paintComponent(Graphics g)  {
        
       
        super.paintComponent(g);
        Graphics2D g2=(Graphics2D)g;
        tileguy.draw(g2);
        for (int i=0;i<obj.length; i++) {
            if (obj[i]!=null) {
                if (obj[i].locationX==player.XLevel && obj[i].locationY==player.YLevel) {
                    obj[i].draw(g2,this);
                }
            }
        }
        player2.draw(g2);
        player.draw(g2);
        npc.draw(g2);
            fightingboi.draw(g2);
        g2.dispose();

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
