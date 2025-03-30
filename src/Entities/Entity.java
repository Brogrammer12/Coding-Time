package Entities;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import Main.The_Hub;

public class Entity {
    public int x, y;
    public int moveSpeed;
    public int locationx;
    public int locationy;
    BufferedImage up1, up2, left1, left2, down1, down2, right1, right2;
    BufferedImage bob1, bob2;
    BufferedImage[] attack;
    BufferedImage defenseSprite;
    public String direction;
    int SpriteCounter=0;
    int SpriteNum=1;
    public boolean fightMode;
    int Width;
    int Height;
    public int buttonX;
    public int Health;
    public int attackSpriteNum;
    public int attackSpriteCounter;
    public int timer;
    public boolean timeUp;
    public boolean active;
    public boolean moveToFight;
    public boolean attacking;
    public int maxHealth;
    public int startX;
    public int damage;
    public boolean defendMode;
    public int defenseValue;
    public boolean plSwitch;
    public boolean ded;
    public BufferedImage Dead1;
    public BufferedImage Dead2;
    public Rectangle colBox;
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionOn=false;
    public Rectangle solidArea=new Rectangle(0, 0, 48, 48);
    public int worldX;
    public int worldY;
    public int screenX;
    public int screenY;
    public int soundurl;
    public int actionLockCounter=0;
    public The_Hub hb;
    public boolean Playing;
    int screenx=100;
    int screeny=100;
    public Entity (The_Hub hb) {
        this.hb=hb;
    }
    public void setaction() {}
    public void update() {
      /*   if (hb.player.fightMode==false) {
            setaction();
            collisionOn=false;
            hb.cChecker.checkTile(this);
            if (collisionOn==false) {
                switch(direction) {
                    case "up":
                    if (hb.player.borderX==false && hb.player.borderY==false) {
                        worldY-=moveSpeed;
                    }
                        screenY-=moveSpeed;
                    
                    break;
                    case "down":
                    if (hb.player.borderX==false && hb.player.borderY==false) {
                        worldY+=moveSpeed;
                    }
                        screenY+=moveSpeed;
                    
                    break;
                    case "left":
                    if (hb.player.borderX==false && hb.player.borderY==false) {
                        worldX-=moveSpeed;
                    }
                        screenx-=moveSpeed;
                    break;
                    case "right":
                    if (hb.player.borderX==false && hb.player.borderY==false) {
                        worldX+=moveSpeed;
                    }
                        screenx+=moveSpeed;
                    break;
                }
            }
            SpriteCounter++;
            if(SpriteCounter>12) {
                if(SpriteNum==1) {
                    SpriteNum=2;
                    
                }
                else if(SpriteNum==2) {
                    SpriteNum=1;
                    
                }
                SpriteCounter=0;
            }
        }*/
    }
    public void draw(Graphics2D g2) {
        if (hb.player.fightMode==false) {
            BufferedImage image=null;
            switch(direction) {
                case "up":
                if(SpriteNum==1) {
                    image=up1;
                }
                if(SpriteNum==2) {
                    image=up2;
                }
                break;
                case "left":
                if(SpriteNum==1) {
                    image=left1;
                }
                if(SpriteNum==2) {
                    image=left2;
                }
                break;
                case "down":
                if(SpriteNum==1) {
                    image=down1;
                }
                if(SpriteNum==2) {
                    image=down2;
                }
                break;
                case "right":
                if(SpriteNum==1) {
                    image=right1;
                }
                if(SpriteNum==2) {
                    image=right2;
                }
                break;
            }
            if (hb.player.borderX==false && hb.player.borderY==false) {
                screenx=worldX-hb.player.worldX+hb.player.screenX;
                screeny=worldY-hb.player.worldY+hb.player.screenY;
            }
           // if (locationx==hb.player.XLevel && locationy==hb.player.YLevel) {
           
            g2.drawImage(image, screenx, screeny, hb.resTileSize, hb.resTileSize, null);
           
           // }
        }
    }
}
