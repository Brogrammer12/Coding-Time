package Object;

import java.awt.Graphics2D;

import javax.imageio.ImageIO;

import Main.The_Hub;

public class Sign extends object{
    The_Hub hb;
    public String text;
    public boolean readingSign=false;
    public Sign(The_Hub hb, String text) {
        this.hb=hb;
        name="Sign";
        this.text=text;
        try{
            image=ImageIO.read(getClass().getResourceAsStream("/Resources/Objects/Sign.png"));
           
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        collision=true;
    }
    @Override
    public void interaction(Graphics2D g2) {
        System.out.println("Im working");
        if (hb.player.XLevel==locationX && hb.player.YLevel==locationY) {
            System.out.println("Im working");
            if (readingSign==true) {
                hb.textboi.draw(g2, text, 100, 100, hb.resTileSize/2, hb.resTileSize/2);
            }
            if (hb.keyBoi.enterPressed==true && readingSign==true && hb.keyBoi.hasMoved==false) {
                readingSign=false;
                hb.keyBoi.hasMoved=true;
            }
            else if(hb.keyBoi.enterPressed==true && readingSign==false && hb.keyBoi.hasMoved==false) {
                readingSign=true;
                hb.keyBoi.hasMoved=true;
            }
        }
    }
}
