package Entities;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import Main.The_Hub;

public class Skelly extends Entity{
The_Hub hb;
    public Skelly(The_Hub hb) {
this.hb=hb;
active=false;
moveToFight=true;
attacking=false;
maxHealth=50;
damage=10;
attack=new BufferedImage[2];
x=hb.resTileSize*10+100;
startX=hb.resTileSize*10+100;
y=200;
Health=50;
skelLoader();
    }
    public void skelLoader() {
        try {
            bob1=ImageIO.read(getClass().getResourceAsStream("/Resources/NPCs/Skellywag one.png"));
            bob2=ImageIO.read(getClass().getResourceAsStream("/Resources/NPCs/Skellywag two.png"));
            attack[0]=ImageIO.read(getClass().getResourceAsStream("/Resources/NPCs/SkellyAttackone.png"));
            attack[1]=ImageIO.read(getClass().getResourceAsStream("/Resources/NPCs/SkellyAttacktwo.png"));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
