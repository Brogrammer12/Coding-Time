package Entities;
import java.io.IOException;
import javax.imageio.ImageIO;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import Main.TextReader;
import Main.The_Hub;
public class NPCsRelic extends Entity{
    The_Hub hb;
    Player player;
    int amountSprites;
    String[] bois;
    String[] bois2;
    String[] bois3;
    String[] bois4;
    TextReader t;
    Player2 p2;
    public boolean uWon=false;
    public boolean won=false;
    public boolean npcattack=false;
    public BufferedImage image;
    public BufferedImage attack1;
    public BufferedImage attack2;
    public boolean firstTime=false;
    public boolean necAttacker=false;
    public boolean skelAttacker=false;
    Nec necromancer;
    Skelly skellywag;
    public NPCsRelic(The_Hub hb, Player player, TextReader t, Player2 p2, Nec necromancer, Skelly skellywag) {
        this.hb=hb;
        this.t=t;
        this.p2=p2;
        this.player=player;
        this.necromancer=necromancer;
        this.skellywag=skellywag;
        bois=new String[2];
        bois2=new String[2];
        bois3=new String[2];
        bois4=new String[2];
        amountSprites=bois.length;
        timer=0;
        timeUp=false;
        NPCLoader();
        SpriteNum=1;
    }
    public void NPCLoader() {
        bois[0]="/Resources/NPCs/Necromancer one.png";
        bois[1]="/Resources/NPCs/Skellywag one.png";
        bois2[0]="/Resources/NPCs/Necromancer two.png";
        bois2[1]="/Resources/NPCs/Skellywag two.png";
        bois3[0]="/Resources/NPCs/necAttackone.png";
        bois4[0]="/Resources/NPCs/necAttacktwo.png";
        bois3[1]="/Resources/NPCs/SkellyAttackone.png";
        bois4[1]="/Resources/NPCs/SkellyAttacktwo.png";
    }
    public void update() {
            SpriteCounter++;
                if(SpriteCounter>20) {
                    if(SpriteNum==1) {
                        SpriteNum=2;
                        
                    }
                    else if(SpriteNum==2) {
                        SpriteNum=1;
                        
                    }
                    SpriteCounter=0;
                }
        
    }
    public void draw(Graphics2D g2) {
        if (uWon==true) {
            timer++;
            if (timer<100) {
                t.draw(g2, "YOU WON!", 10*hb.resTileSize/2, 5*hb.resTileSize, hb.resTileSize/2, hb.resTileSize/2);
            }
            else if(timer>=100) {
                timer=0;
                player.fightMode=false;
                p2.fightMode=false;
                won=true;
                uWon=false;
            }
        }
        try{
            int trueHealth=0;
            for(int index=0; index<amountSprites; index++) {
                if(player.fightMode==true) {
                    bob1=ImageIO.read(getClass().getResourceAsStream(bois[index]));
                bob2=ImageIO.read(getClass().getResourceAsStream(bois2[index]));
                }
                else if(player.fightMode==false) {
                    bob1=null;
                    bob2=null;
                }
                if (npcattack==true) {
                    timer++;
                    if (timer>=100) {
                        timeUp=true;
                    }
                }
                if(index==0) {
                    x=hb.resTileSize*10;
                    y=100;
                    trueHealth=hb.necHealth;
                }
                else if(index==1) {
                    x=hb.resTileSize*10+100;
                    y=200;
                    trueHealth=hb.skelHealth;
                }
                if (npcattack==false) {
                    switch(SpriteNum) {
                        case 1:
                        image=bob1;
                        break;
                        case 2:
                        image=bob2;
                    }
                }
                
                if (trueHealth>0) {
                    g2.drawImage(image, x, y, hb.fightWidth, hb.fightHeight, null);
                }
                if (hb.necHealth<=0 && hb.skelHealth<=0 && won==false) {
                    uWon=true;
                }
                
            
            }
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        
    
    }
}
