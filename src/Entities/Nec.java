package Entities;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import Main.The_Hub;

public class Nec extends Entity{
The_Hub hb;
    public Nec(The_Hub hb) {
        this.hb=hb;
        super(hb);
        active=true;
        direction="down";
        moveSpeed=1;
        soundurl=5;
        attack=new BufferedImage[2];
        moveToFight=false;
        attacking=false;
        damage=10;
        colBox=solidArea;
        maxHealth=50;
        x=hb.resTileSize*10;
        startX=hb.resTileSize*10;
        worldX=hb.maxWorldWidth/2;
        worldY=hb.maxWorldHeight/2;
        y=100;
        Health=50;
        necLoader();
    }
    public void necLoader() {
        try{
            bob1=ImageIO.read(getClass().getResourceAsStream("/Resources/NPCs/Necromancer one.png"));
            bob2=ImageIO.read(getClass().getResourceAsStream("/Resources/NPCs/Necromancer two.png"));
            attack[0]=ImageIO.read(getClass().getResourceAsStream("/Resources/NPCs/necAttackone.png"));
            attack[1]=ImageIO.read(getClass().getResourceAsStream("/Resources/NPCs/necAttacktwo.png"));
            up1=ImageIO.read(getClass().getResourceAsStream("/Resources/NPCs/necUp1.png"));
            up2=ImageIO.read(getClass().getResourceAsStream("/Resources/NPCs/necUp2.png"));
            left1=ImageIO.read(getClass().getResourceAsStream("/Resources/NPCs/necLeft1.png"));
            left2=ImageIO.read(getClass().getResourceAsStream("/Resources/NPCs/necLeft2.png"));
            right1=ImageIO.read(getClass().getResourceAsStream("/Resources/NPCs/necRight1.png"));
            right2=ImageIO.read(getClass().getResourceAsStream("/Resources/NPCs/necRight2.png"));
            down1=ImageIO.read(getClass().getResourceAsStream("/Resources/NPCs/necDown1.png"));
            down2=ImageIO.read(getClass().getResourceAsStream("/Resources/NPCs/necDown2.png"));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void setaction() {
        actionLockCounter++;
        if (actionLockCounter==120) {
            Random random=new Random();
        int i=random.nextInt(100)+1;
        if (i<=25) {
            direction="up";
        }
        else if(i>25 && i<=50) {
            direction="left";
        }
        else if(i>50 && i<=75) {
            direction="right";
        }
        else if(i>75) {
            direction="down";
        }
        actionLockCounter=0;
        }
    }
}
