package Entities;
import Main.The_Hub;
import Main.keyInput;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Player extends Entity{
    The_Hub hb;
    keyInput k;
    public Player(The_Hub hb, keyInput k) {
        this.hb=hb;
        this.k=k;
        setDefaultValues();
        playerImageLoader();
    }
    public void setDefaultValues() {
        x=100;
        y=100;
        moveSpeed=4;
        direction="down";
        SpriteNum=1;
        fightMode=true;
        buttonX=0;
        if(fightMode==false) {
            Width=hb.resTileSize;
            Height=hb.resTileSize;
        }
        else if(fightMode==true) {
            Width=hb.fightWidth;
            Height=hb.fightHeight;
        }

    }
    public void update() {
        
            if(k.upPressed==true || k.leftPressed==true || k.downPressed==true || k.rightPressed==true) {
                if(fightMode==false) {
                    if(k.upPressed==true) {
                        y-=moveSpeed;
                        direction="up";
                    }
                    else if (k.leftPressed==true) {
                        x-=moveSpeed;
                        direction="left";
                    }
                    else if(k.downPressed==true) {
                        y+=moveSpeed;
                        direction="down";
                    }
                    else if(k.rightPressed==true) {
                        x+=moveSpeed;
                        direction="right";
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
                }
                else if(fightMode==true) {
                    if(k.rightPressed==true && k.hasMoved==false) {
                        if(buttonX==3) {
                            buttonX=0;
                        }
                        else{
                            buttonX+=1;
                           }
                        k.hasMoved=true;
                    }
                    if(k.leftPressed==true && k.hasMoved==false) {
                        if(buttonX==0) {
                            buttonX=3;
                        }
                        else{
                            buttonX-=1;
                        }
                        k.hasMoved=true;
                    }
                }
            }
            if(fightMode==true){
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
        
        }
       
    
    public void playerImageLoader() {
        try {
            up1=ImageIO.read(getClass().getResourceAsStream("/Resources/Player1/PlayerUp1.png"));
            up2=ImageIO.read(getClass().getResourceAsStream("/Resources/Player1/PlayerUp2.png"));
            left1=ImageIO.read(getClass().getResourceAsStream("/Resources/Player1/PlayerLeft1.png"));
            left2=ImageIO.read(getClass().getResourceAsStream("/Resources/Player1/PlayerLeft2.png"));
            down1=ImageIO.read(getClass().getResourceAsStream("/Resources/Player1/PlayerDown1.png"));
            down2=ImageIO.read(getClass().getResourceAsStream("/Resources/Player1/PlayerDown2.png"));
            right1=ImageIO.read(getClass().getResourceAsStream("/Resources/Player1/PlayerRight1.png"));
            right2=ImageIO.read(getClass().getResourceAsStream("/Resources/Player1/PlayerRight2.png"));
            bob1=ImageIO.read(getClass().getResourceAsStream("/Resources/Player1/PlayerFight1.png"));
            bob2=ImageIO.read(getClass().getResourceAsStream("/Resources/Player1/PlayerFight2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void draw(Graphics2D g2) {
        BufferedImage image=null;
        if(fightMode==false) {
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
        }
        else if(fightMode==true) {
            switch(SpriteNum) {
                case 1:
                image=bob1;
                break;
                case 2:
                image=bob2;
                break;
            }
        }
        g2.drawImage(image, x, y, Width, Height, null);
    }
}
