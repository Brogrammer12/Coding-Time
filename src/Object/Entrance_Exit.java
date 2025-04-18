package Object;

import Main.The_Hub;
import java.awt.Graphics2D;
public class Entrance_Exit extends object{
    The_Hub hb;
    public Entrance_Exit(The_Hub hb, String connector) {
        this.hb=hb;
        name="Entrance_Exit";
        this.connector=connector;
    }
    @Override
    public void interaction(Graphics2D g2) {
        if (hb.player.XLevel==locationX && hb.player.YLevel==locationY) {
        }
    }

}
