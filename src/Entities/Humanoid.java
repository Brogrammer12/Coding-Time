package Entities;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import Main.The_Hub;

public class Humanoid extends Entity{
    The_Hub hb;
    public Humanoid(The_Hub hb) {
        super(hb);
        this.hb=hb;
        Playing=true;
        active=true;
        direction="down";
        moveSpeed=1;
        soundurl=5;
        attack=new BufferedImage[2];
        moveToFight=true;
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
        humanoidLoader();
    }
    public void humanoidLoader() {
            try {
                bob1=ImageIO.read(getClass().getResourceAsStream("/Resources/NPCs/HumanoidFight1.png"));
                bob2=ImageIO.read(getClass().getResourceAsStream("/Resources/NPCs/HumanoidFight2.png"));
                attack[0]=ImageIO.read(getClass().getResourceAsStream("/Resources/NPCs/HumanoidAttack1.png"));
                attack[1]=ImageIO.read(getClass().getResourceAsStream("/Resources/NPCs/HumanoidAttack2.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
    }
}
