package Object;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import Main.The_Hub;
public class object {
    public void interaction(Graphics2D g2) {
        //do yo shit here
    }
public BufferedImage image;
public String name;
public boolean yeItCollided=false;
public boolean collision=false;
public int worldX, worldY;
public int locationX;
public int locationY;
public Rectangle solidArea=new Rectangle(0, 0, 48, 48);
public int solidAreaDefaultX=0;
public int solidAreaDefaultY=0;
public void draw(Graphics2D g2, The_Hub hb) {
    int screenX=worldX-hb.Players[0].worldX+hb.Players[0].screenX;
    int screenY=worldY-hb.Players[0].worldY+hb.Players[0].screenY;
    if (hb.player.borderX==false && hb.player.borderY==false) {
        g2.drawImage(image, screenX, screenY, hb.resTileSize, hb.resTileSize, null);
    }
    else if(hb.player.borderX==true || hb.player.borderY==true) {
        int finalScreenX=worldX-hb.Players[0].worldX+hb.tileguy.ogScreenX;
    int finalScreenY=worldY-hb.Players[0].worldY+hb.tileguy.ogScreenY;
        g2.drawImage(image, finalScreenX, finalScreenY, hb.resTileSize, hb.resTileSize, null);
    }}
}
