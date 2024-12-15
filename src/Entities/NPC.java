package Entities;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import Main.The_Hub;

public class NPC extends Entity{
    The_Hub hb;
    public Entity[] entity;
    public BufferedImage image;
    public NPC(The_Hub hb, Skelly Skellywag, Nec Necromancer) {
        this.hb=hb;
        SpriteNum=1;
        this.entity=new Entity[]{
            new Nec(hb),
            new Skelly(hb)
        };
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
                for(int index=0; index<entity.length; index++) {
                    if(entity[index].Health<=0) {
                        entity[index].active=false;
                    }
                }
        }
        public void draw(Graphics2D g2) {
            for(int index=0; index<entity.length; index++) {
                switch (SpriteNum) {
                    case 1:
                    image=entity[index].bob1;
                    break;
                    case 2:
                    image=entity[index].bob2;
                    break;
                }
                if (entity[index].active==true) {
                    g2.drawImage(image, entity[index].x,entity[index].y,hb.fightWidth,hb.fightHeight, null);
                }
                
            }
        }
    }
    
  

