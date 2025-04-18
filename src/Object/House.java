package Object;

import java.io.IOException;
import java.awt.Graphics2D;
import javax.imageio.ImageIO;

import Main.The_Hub;

public class House extends object{
    The_Hub hb;
    public House(The_Hub hb) {
        this.hb=hb;
        name="House";
        try {
            image=ImageIO.read(getClass().getResourceAsStream("/Resources/Objects/house.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        collision=true;
    }
    @Override
    public void interaction(Graphics2D g2) {
        if (hb.player.XLevel==locationX && hb.player.YLevel==locationY) {
        }
    }
}
