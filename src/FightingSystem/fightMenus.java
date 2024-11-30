package FightingSystem;
import Entities.Player;
import Entities.Player2;
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
    Player2 p2;
    healthManager healthy;
    BufferedImage fight, fightSelected, defend, defendSelected, item, itemSelected, flee, fleeSelected, selector, health;
    int wPlayer;
public fightMenus(The_Hub hb, Player player, keyInput k, TextReader t, Player2 p2, healthManager healthy) {
this.hb=hb;
this.player=player;
this.k=k;
this.t=t;
this.p2=p2;
this.healthy=healthy;
wPlayer=1;
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
        selector=ImageIO.read(getClass().getResourceAsStream("/Resources/Buttons/Selector.png"));
        health=ImageIO.read(getClass().getResourceAsStream("/Resources/Buttons/HealthBar.png"));
    } catch (IOException e) {
        e.printStackTrace();
    }
}
public void draw(Graphics2D g2) {
    BufferedImage fightImage=null;
    BufferedImage defendImage=null;
    BufferedImage itemImage=null;
    BufferedImage fleeImage=null;
    BufferedImage selectImage=selector;
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
        if(hb.flee==false && hb.item==false && hb.defend==false && hb.fight==false && k.enterPressed==true && k.hasMoved==false && hb.charSelected==false) {
                if(fleeImage==fleeSelected) {
                    hb.flee=true;
                    hb.fight=false;
                    hb.defend=false;
                    hb.item=false;
                    System.out.println("fleeing...");
                }
                else if(itemImage==itemSelected) {
                    hb.flee=false;
                    hb.fight=false;
                    hb.defend=false;
                    hb.item=true;
                    System.out.println("selecting item...");
                }
                else if(defendImage==defendSelected) {
                    hb.flee=false;
                    hb.fight=false;
                    hb.defend=true;
                    hb.item=false;
                    System.out.println("defending...");
                }
                else if(fightImage==fightSelected) {
                    hb.flee=false;
                    hb.fight=true;
                    hb.defend=false;
                    hb.item=false;
                    System.out.println("fighting...");
                }
                k.hasMoved=true;
            
            
        
        }
        if(k.escPressed==true && k.hasMoved==false) {
            hb.charSelected=false;
            hb.fight=false;
            hb.defend=false;
            hb.item=false;
            hb.flee=false;
            t.draw(g2, "", 5*hb.resTileSize, 7*hb.resTileSize, hb.resTileSize/2, hb.resTileSize/2);
            player.Selector=1;
            k.hasMoved=true;
        }
        g2.setColor(Color.BLACK);
        g2.fillRect(0, (hb.resTileSize/3)*19, hb.screenWidth, 300);
        BufferedImage imageMenu=null;
        BufferedImage imageMenu1=null;
        BufferedImage imageMenu2=null;
        double hBoi=50/29;
                int healthLoss=50-player.p1Health;
                double healthLost=(healthLoss/1.8)/hBoi;
                int totHealthLost=(int) Math.round(healthLost);
                for(int index=0; index<=29; index++) {
                    if(index==totHealthLost) {
                        imageMenu1=healthy.helath[index];
                    }
                }

                double hBoi2=50/29;
                int healthLoss2=50-p2.p2Health;
                double healthLost2=(healthLoss2/1.8)/hBoi2;
                int totHealthLost2=(int) Math.round(healthLost2);
                for(int index=0; index<=29; index++) {
                    if(index==totHealthLost2) {
                        imageMenu2=healthy.helath[index];
                    }
                }
                if(wPlayer==1) {
                    imageMenu=imageMenu1;
                }
                else if(wPlayer==2) {
                    imageMenu=imageMenu2;
                }
        g2.drawImage(imageMenu, (hb.screenWidth/2)-hb.resTileSize*3, 13*hb.resTileSize/2,96*hb.scale, 40*hb.scale, null);
        if (hb.charSelected==true) {
            BufferedImage image=null;
            int x=0;
            int y=0;
            switch(player.cursorX) {
                case 0:
                for(int index=0; index<=29; index++) {
                    if(index==totHealthLost) {
                        image=healthy.helath[index];
                    }
                }
                x=100-hb.resTileSize;
                y=100;
                g2.drawImage(image, x,150,48*hb.scale, 20*hb.scale, null);
                break;
                case 1:
                for(int index=0; index<=29; index++) {
                    if(index==totHealthLost2) {
                        image=healthy.helath[index];
                    }
                }
                x=200-hb.resTileSize;
                y=200;
                g2.drawImage(image, x,250,48*hb.scale, 20*hb.scale, null);
                break;
                case 2:
                x=hb.resTileSize*9;
                y=100;
                g2.drawImage(health, x,150,48*hb.scale, 20*hb.scale, null);
                break;
                case 3:
                x=hb.resTileSize*9+100;
                y=200;
                g2.drawImage(health, x,250,48*hb.scale, 20*hb.scale, null);
                break;

            }
            g2.drawImage(selectImage,x ,y, hb.resTileSize, hb.resTileSize, null);
            if(k.enterPressed==true && k.hasMoved==false) {
                if (player.cursorX>1) {
                    if(wPlayer==1) {
                        player.attackMode=true;
                    }
                    else if(wPlayer==2) {
                        p2.attackMode=true;
                    }
                    
                }
                System.out.println("You choose this guy to attack");
                if(wPlayer==1) {
                    wPlayer=2;
                }
                else if(wPlayer==2) {
                    wPlayer=1;
                }
                k.hasMoved=true;
                hb.charSelected=false;
            }
            
        }
        int plHealth=1;
        if(wPlayer==1) {
            plHealth=player.p1Health;
        }
        else if(wPlayer==2) {
            plHealth=p2.p2Health;
        }
        if(hb.flee==false && hb.fight==false && hb.item==false && hb.defend==false && hb.charSelected==false) {
            t.draw(g2, "HP:"+plHealth+"/50", (hb.screenWidth/2)-hb.resTileSize*2, 14*hb.resTileSize/2, hb.resTileSize/2, hb.resTileSize/2);
            t.draw(g2, "XP:10/50", hb.resTileSize/2, 15*hb.resTileSize/2, hb.resTileSize/2, hb.resTileSize/2);
            t.draw(g2, "LVL:1", 3*hb.resTileSize/2, 17*hb.resTileSize/2, hb.resTileSize/3, hb.resTileSize/3);
            t.draw(g2, "INV:"+player.items.length+"/10", 23*hb.resTileSize/2, 15*hb.resTileSize/2, hb.resTileSize/2, hb.resTileSize/2);
            t.draw(g2, "PL:"+wPlayer, 25*hb.resTileSize/2, 17*hb.resTileSize/2, hb.resTileSize/3, hb.resTileSize/3);
            g2.drawImage(fightImage,hb.resTileSize/2, 440, 128, 128, null);
            g2.drawImage(defendImage,9*hb.resTileSize/2, 440, 128, 128, null);
            g2.drawImage(itemImage,hb.screenWidth-14*hb.resTileSize/2, 440, 128, 128, null);
            g2.drawImage(fleeImage,hb.screenWidth-6*hb.resTileSize/2, 440, 128, 128, null);
        }
        else if(hb.fight==true) {
            int[] XCounter=new int[player.weapons.length];
            int e=0;
            for(int index=0; index<player.weapons.length; index++) {
                int firstX=0;
                String urmom=player.weapons[index];
                int pixelLength=urmom.length()*24;
                if(index>0) {
                    XCounter[index]=XCounter[index-1]+pixelLength;
                }
                if(index==0) {
                    firstX=4*hb.resTileSize;
                    XCounter[0]=pixelLength;
                }
                else {
                    firstX=XCounter[index-1]+(index*(5*hb.resTileSize))+4*hb.resTileSize;
                }
                t.draw(g2, urmom, firstX, 19*hb.resTileSize/2, hb.resTileSize/2, hb.resTileSize/2);
                if(index==player.Selector) {
                    e=firstX-hb.resTileSize;
                }
            }
            g2.drawImage(selectImage, e, 10*hb.resTileSize, hb.resTileSize, hb.resTileSize, null);
            t.draw(g2, "ATTACK?", 12*hb.resTileSize/2, 7*hb.resTileSize, hb.resTileSize/2, hb.resTileSize/2);
            if(player.Selector==0 && k.enterPressed==true && k.hasMoved==false) {
                System.out.println("IT WORKED(FIGHT)");
                hb.charSelected=true;
                hb.fight=false;
                k.hasMoved=true;
            
        }
        else if(player.Selector==1 && k.enterPressed==true && k.hasMoved==false) {
            hb.fight=false;
            k.hasMoved=true;
        }
        }
        else if(hb.defend==true) {
            int[] XCounter=new int[player.defenses.length];
            int e=0;
            for(int index=0; index<player.weapons.length; index++) {
                int firstX=0;
                String urmom=player.defenses[index];
                int pixelLength=urmom.length()*24;
                if(index>0) {
                    XCounter[index]=XCounter[index-1]+pixelLength;
                }
                if(index==0) {
                    firstX=4*hb.resTileSize;
                    XCounter[0]=pixelLength;
                }
                else {
                    firstX=XCounter[index-1]+(index*(5*hb.resTileSize))+4*hb.resTileSize;
                }
                t.draw(g2, urmom, firstX, 19*hb.resTileSize/2, hb.resTileSize/2, hb.resTileSize/2);
                if(index==player.Selector) {
                    e=firstX-hb.resTileSize;
                }
            }
            g2.drawImage(selectImage, e, 10*hb.resTileSize, hb.resTileSize, hb.resTileSize, null);
            t.draw(g2, "DEFEND?", 12*hb.resTileSize/2, 7*hb.resTileSize, hb.resTileSize/2, hb.resTileSize/2);
            if(player.Selector==0 && k.enterPressed==true && k.hasMoved==false) {
                System.out.println("IT WORKED(DEFEND)");
                hb.charSelected=true;
                hb.defend=false;
                k.hasMoved=true;
            
        }
        else if(player.Selector==1 && k.enterPressed==true && k.hasMoved==false) {
            hb.defend=false;
            player.Selector=0;
            k.hasMoved=true;
        }
        }
        else if(hb.item==true) {
            int[] XCounter=new int[player.items.length];
            int firstX=0;
            int e=0;
            for(int index=0; index<player.items.length; index++) {
                String urmom=player.items[index];
                int pixelLength=urmom.length()*16;
                if(index>0) {
                    XCounter[index]=XCounter[index-1]+pixelLength;
                }
                if(index==0) {
                    firstX=hb.resTileSize;
                    XCounter[0]=pixelLength;
                }
                else {
                    firstX=XCounter[index-1]+(index*(hb.resTileSize))+hb.resTileSize;
                }
                t.draw(g2, urmom, firstX, 20*hb.resTileSize/2, hb.resTileSize/3, hb.resTileSize/3);
                if(index==player.Selector) {
                    e=firstX-hb.resTileSize;
                }
            }
            g2.drawImage(selectImage, e, 10*hb.resTileSize, hb.resTileSize, hb.resTileSize, null);
            t.draw(g2, "USE ITEM?", 12*hb.resTileSize/2, 7*hb.resTileSize, hb.resTileSize/2, hb.resTileSize/2);
            if(player.Selector==0 && k.enterPressed==true && k.hasMoved==false) {
                System.out.println("You ate an apple. Crunchy. +5hp!");
                hb.charSelected=true;
                hb.item=false;
                player.Selector=0;
                k.hasMoved=true;
            
        }
        else if(player.Selector==1 && k.enterPressed==true && k.hasMoved==false) {
            System.out.println("You ate a banana. tastes rotten. -5hp.");
            hb.charSelected=true;
            hb.item=false;
            player.Selector=0;
            k.hasMoved=true;
        }
        else if(player.Selector==2 && k.enterPressed==true && k.hasMoved==false) {
            System.out.println("You drink the concoction. You start to feel better. +5atk, +5hp");
            hb.charSelected=true;
            hb.item=false;
            player.Selector=0;
            k.hasMoved=true;
        }
        else if(player.Selector==3 && k.enterPressed==true && k.hasMoved==false) {
            System.out.println("You put on the bandage. It's still sticky. +20hp.");
            hb.charSelected=true;
            hb.item=false;
            player.Selector=0;
            k.hasMoved=true;
        }
        }
        else if(hb.flee==true) {
            int[] XCounter=new int[player.running.length];
            int e=0;
            for(int index=0; index<player.weapons.length; index++) {
                int firstX=0;
                String urmom=player.running[index];
                int pixelLength=urmom.length()*24;
                if(index>0) {
                    XCounter[index]=XCounter[index-1]+pixelLength;
                }
                if(index==0) {
                    firstX=4*hb.resTileSize;
                    XCounter[0]=pixelLength;
                }
                else {
                    firstX=XCounter[index-1]+(index*(5*hb.resTileSize))+4*hb.resTileSize;
                }
                t.draw(g2, urmom, firstX, 19*hb.resTileSize/2, hb.resTileSize/2, hb.resTileSize/2);
                if(index==player.Selector) {
                    e=firstX-hb.resTileSize;
                }
            }
            g2.drawImage(selectImage, e, 10*hb.resTileSize, hb.resTileSize, hb.resTileSize, null);
            t.draw(g2, "RUN AWAY?", 12*hb.resTileSize/2, 7*hb.resTileSize, hb.resTileSize/2, hb.resTileSize/2);
            if(player.Selector==0 && k.enterPressed==true && k.hasMoved==false) {
                hb.charSelected=true;
                    double randomValue=Math.random();
                    double realValue=Math.floor(randomValue*10)/10;
                    if(realValue>=0.5) {
                        player.fightMode=false;
                        p2.fightMode=false;
                        System.out.println("IT WORKED(FLEE)");
                        hb.flee=false;
                        if(wPlayer==1) {
                            wPlayer=2;
                        }
                        else if(wPlayer==2) {
                            wPlayer=1;
                        }
                    }
                    else if(realValue<0.5) {
                        System.out.println("Womp Womp.");
                        hb.flee=false;
                    }
                    k.hasMoved=true;
                
            }
            else if(player.Selector==1 && k.enterPressed==true && k.hasMoved==false) {
                hb.flee=false;
                player.Selector=0;
                k.hasMoved=true;
            }
        } 
            
    }
   
}
}
