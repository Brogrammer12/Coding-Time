package FightingSystem;
import Entities.Player;
import Main.TextReader;
import Main.The_Hub;
import Main.keyInput;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.awt.Graphics2D;
import javax.imageio.ImageIO;
import java.awt.Color;
public class fightMenus {
    The_Hub hb;
    Player player;
    keyInput k;
    TextReader t;
    BufferedImage fight, fightSelected, defend, defendSelected, item, itemSelected, flee, fleeSelected;
public fightMenus(The_Hub hb, Player player, keyInput k, TextReader t) {
this.hb=hb;
this.player=player;
this.k=k;
this.t=t;
menuLoader();
}
public void menuLoader() {
    try {
        fight=ImageIO.read(getClass().getResourceAsStream("/Resources/Buttons/FightButton.png"));
        fightSelected=ImageIO.read(getClass().getResourceAsStream("/Resources/Buttons/FightButtonSelected.png"));
        defend=ImageIO.read(getClass().getResourceAsStream("/Resources/Buttons/DefendButton.png"));
        defendSelected=ImageIO.read(getClass().getResourceAsStream("/Resources/Buttons/DefendButtonSelected.png"));
        item=ImageIO.read(getClass().getResourceAsStream("/Resources/Buttons/ItemButton.png"));
        itemSelected=ImageIO.read(getClass().getResourceAsStream("/Resources/Buttons/ItemButtonSelected.png"));
        flee=ImageIO.read(getClass().getResourceAsStream("/Resources/Buttons/FleeButton.png"));
        fleeSelected=ImageIO.read(getClass().getResourceAsStream("/Resources/Buttons/FleeButtonSelected.png"));
    } catch (IOException e) {
        e.printStackTrace();
    }
}
public void draw(Graphics2D g2) {
    BufferedImage fightImage=null;
    BufferedImage defendImage=null;
    BufferedImage itemImage=null;
    BufferedImage fleeImage=null;
    switch(player.buttonX) {
        case 0:
        fightImage=fightSelected;
        defendImage=defend;
        itemImage=item;
        fleeImage=flee;
        break;
        case 1:
        fightImage=fight;
        defendImage=defendSelected;
        itemImage=item;
        fleeImage=flee;
        break;

        case 2:
        fightImage=fight;
        defendImage=defend;
        itemImage=itemSelected;
        fleeImage=flee;
        break;

        case 3:
        fightImage=fight;
        defendImage=defend;
        itemImage=item;
        fleeImage=fleeSelected;
        break;
    }
    if (player.fightMode==true) {
        if(k.enterPressed==true && k.hasMoved==false) {
            if(fleeImage==fleeSelected) {
                hb.flee=true;
                hb.fight=false;
                hb.defend=false;
                hb.item=false;
                System.out.println("fleeing...");
                k.hasMoved=true;
            }
            else if(itemImage==itemSelected) {
                hb.flee=false;
                hb.fight=false;
                hb.defend=false;
                hb.item=true;
                System.out.println("selecting item...");
                k.hasMoved=true;
            }
            else if(defendImage==defendSelected) {
                hb.flee=false;
                hb.fight=false;
                hb.defend=true;
                hb.item=false;
                System.out.println("defending...");
                k.hasMoved=true;
            }
            else if(fightImage==fightSelected) {
                hb.flee=false;
                hb.fight=true;
                hb.defend=false;
                hb.item=false;
                System.out.println("fighting...");
                k.hasMoved=true;
            }
        }
        if(k.escPressed==true && k.hasMoved==false) {
            hb.fight=false;
            hb.defend=false;
            hb.item=false;
            hb.flee=false;
            t.draw(g2, "", 5*hb.resTileSize, 7*hb.resTileSize);
            k.hasMoved=true;
        }
        g2.setColor(Color.BLACK);
        g2.fillRect(0, 420, hb.screenWidth, 156);
        if(hb.flee==false && hb.fight==false && hb.item==false && hb.defend==false) {
            g2.drawImage(fightImage,hb.resTileSize/2, 440, 128, 128, null);
            g2.drawImage(defendImage,9*hb.resTileSize/2, 440, 128, 128, null);
            g2.drawImage(itemImage,hb.screenWidth-14*hb.resTileSize/2, 440, 128, 128, null);
            g2.drawImage(fleeImage,hb.screenWidth-6*hb.resTileSize/2, 440, 128, 128, null);
        }
        else if(hb.fight==true) {
            t.draw(g2, "ATTACK?", 12*hb.resTileSize/2, 7*hb.resTileSize);
            t.draw(g2, "YES", 6*hb.resTileSize/2, 10*hb.resTileSize);
            t.draw(g2, "NO", 24*hb.resTileSize/2, 10*hb.resTileSize);
        }
        else if(hb.defend==true) {
            t.draw(g2, "DEFEND?", 12*hb.resTileSize/2, 7*hb.resTileSize);
        }
        else if(hb.item==true) {
            t.draw(g2, "USE ITEM?", 12*hb.resTileSize/2, 7*hb.resTileSize);
        }
        else if(hb.flee==true) {
            t.draw(g2, "RUN AWAY?", 12*hb.resTileSize/2, 7*hb.resTileSize);
        } 
            
    }
   
}
}
