package Entities;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import Main.The_Hub;

public class Nec extends Entity{
The_Hub hb;
    public Nec(The_Hub hb) {
        this.hb=hb;
        active=false;
        attack=new BufferedImage[2];
        moveToFight=false;
        attacking=false;
        maxHealth=50;
        x=hb.resTileSize*10;
        y=100;
        Health=10;
        necLoader();
    }
    public void necLoader() {
        try{
            bob1=ImageIO.read(getClass().getResourceAsStream("/Resources/NPCs/Necromancer one.png"));
            bob2=ImageIO.read(getClass().getResourceAsStream("/Resources/NPCs/Necromancer two.png"));
            attack[0]=ImageIO.read(getClass().getResourceAsStream("/Resources/NPCs/necAttackone.png"));
            attack[1]=ImageIO.read(getClass().getResourceAsStream("/Resources/NPCs/necAttacktwo.png"));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
