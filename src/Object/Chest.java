package Object;

import java.awt.Graphics2D;
import java.io.IOException;

import javax.imageio.ImageIO;

import Main.The_Hub;

public class Chest extends object{
    The_Hub hb;
    public Chest(The_Hub hb) {
        this.hb=hb;
        name="Chest";
        try{
            image=ImageIO.read(getClass().getResourceAsStream("/Resources/Objects/Chest.png"));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        collision=true;
    }
    @Override
    public void interaction(Graphics2D g2) {
        if (hb.player.XLevel==locationX && hb.player.YLevel==locationY) {
            if (hb.keyBoi.enterPressed==true && hb.keyBoi.hasMoved==false) {
                try {
                    image=ImageIO.read(getClass().getResourceAsStream("/Resources/Objects/openChest.png"));
                    hb.soundEffect(2);
                    hb.player.goldNuggets+=1;
                    hb.keyBoi.hasMoved=true;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
