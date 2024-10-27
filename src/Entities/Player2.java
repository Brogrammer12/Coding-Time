package Entities;
import Main.The_Hub;
import Main.keyInput;
import Main.keyInput2;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Player2 extends Entity{
    The_Hub hb;
    keyInput2 k;
    public Player2(The_Hub hb, keyInput2 k) {
        this.hb=hb;
        this.k=k;
        setDefaultValues();
        playerImageLoader();
    }
    public void setDefaultValues() {
        x=200;
        y=200;
        moveSpeed=4;
        direction="down";
        SpriteNum=1;

    }
    public void update() {
        if(k.upPressed==true || k.leftPressed==true || k.downPressed==true || k.rightPressed==true) {
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
       
    }
    public void playerImageLoader() {
        try {
            up1=ImageIO.read(getClass().getResourceAsStream("/Resources/Player2/Player2Up1.png"));
            up2=ImageIO.read(getClass().getResourceAsStream("/Resources/Player2/Player2Up2.png"));
            left1=ImageIO.read(getClass().getResourceAsStream("/Resources/Player2/Player2Left1.png"));
            left2=ImageIO.read(getClass().getResourceAsStream("/Resources/Player2/Player2Left2.png"));
            down1=ImageIO.read(getClass().getResourceAsStream("/Resources/Player2/Player2Down1.png"));
            down2=ImageIO.read(getClass().getResourceAsStream("/Resources/Player2/Player2Down2.png"));
            right1=ImageIO.read(getClass().getResourceAsStream("/Resources/Player2/Player2Right1.png"));
            right2=ImageIO.read(getClass().getResourceAsStream("/Resources/Player2/Player2Right2.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void draw(Graphics2D g2) {
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
        g2.drawImage(image, x, y, hb.resTileSize, hb.resTileSize, null);
    }
}
