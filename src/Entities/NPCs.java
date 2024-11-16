package Entities;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import Main.The_Hub;
public class NPCs extends Entity{
    The_Hub hb;
    int amountSprites;
    String[] bois;
    String[] bois2;
    public BufferedImage image;
    public NPCs(The_Hub hb) {
        this.hb=hb;
        bois=new String[2];
        bois2=new String[2];
        amountSprites=bois.length;
        NPCLoader();
        SpriteNum=1;
    }
    public void NPCLoader() {
        bois[0]="/Resources/NPCs/Necromancer one.png";
        bois[1]="/Resources/NPCs/Skellywag one.png";
        bois2[0]="/Resources/NPCs/Necromancer two.png";
        bois2[1]="/Resources/NPCs/Skellywag two.png";
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
        try{
            for(int index=0; index<amountSprites; index++) {
                bob1=ImageIO.read(getClass().getResourceAsStream(bois[index]));
                bob2=ImageIO.read(getClass().getResourceAsStream(bois2[index]));
                if(index==0) {
                    x=hb.resTileSize*10;
                    y=100;
                }
                else if(index==1) {
                    x=hb.resTileSize*10+100;
                    y=200;
                }
                switch(SpriteNum) {
                    case 1:
                    image=bob1;
                    break;
                    case 2:
                    image=bob2;
                }
                g2.drawImage(image, x, y, hb.fightWidth, hb.fightHeight, null);
            
            }
        }
        catch(IOException e) {
            e.printStackTrace();
        }
            //switch(SpriteNum) {
            //    case 1:
            //    image=bob1;
            //    break;
            //    case 2:
             //   image=bob2;
            //}
            //g2.drawImage(image, x, y, hb.fightWidth, hb.fightHeight, null);
        
    
    }
}
