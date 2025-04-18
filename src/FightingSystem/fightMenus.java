package FightingSystem;
import Entities.NPC;
import Entities.NPCsRelic;
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
    public int timer=0;
    public int failTimer=0;
    public boolean runAway=false;
    public boolean runFailed=false;
    public boolean itemBoi=false;
    public int ITimer=0;
    The_Hub hb;
    Player player;
    keyInput k;
    TextReader t;
    Player2 p2;
    NPC npc;
    healthManager healthy;
    public BufferedImage fight, fightSelected, defend, defendSelected, item, itemSelected, flee, fleeSelected, selector, health;
    public boolean othersound=false;
public fightMenus(The_Hub hb, Player player, keyInput k, TextReader t, Player2 p2, healthManager healthy, NPC npc) {
this.hb=hb;
this.player=player;
this.k=k;
this.t=t;
this.npc=npc;
this.p2=p2;
this.healthy=healthy;
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
   if (hb.Players[0].Health<=0) {
       hb.wPlayer=2;
   }
    else if(hb.Players[1].Health<=0) {
        hb.wPlayer=1;
    }
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
        if (itemBoi==true) {
            ITimer++;
            if (ITimer<100) {
                t.draw(g2, "+10HP", 10*hb.resTileSize/2, 5*hb.resTileSize, hb.resTileSize/2, hb.resTileSize/2);
            }
            else if(ITimer>=100) {
                ITimer=0;
                if(player.cursorX==0) {
                    player.Health+=10;
                }
                else if(player.cursorX==1) {
                    p2.Health+=10;
                }
                if (hb.wPlayer==1) {
                    if (hb.Players[1].Health<=0) {
                        npc.attacking=true;
                    }
                    else if(hb.Players[1].Health>0) {
                        hb.wPlayer=2;
                    }
                }
                else if(hb.wPlayer==2) {
                    if (hb.Players[0].Health>0) {
                        hb.wPlayer=1;
                    }
                    npc.attacking=true;
                } 
                itemBoi=false;
            }
        }
        if(hb.flee==false && hb.item==false && hb.defend==false && hb.fight==false && k.enterPressed==true && k.hasMoved==false && hb.charSelected==false && npc.attacking==false) {
                hb.soundEffect(7);
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
                int healthLoss=50-player.Health;
                double healthLost=(healthLoss/1.8)/hBoi;
                int totHealthLost=(int) Math.round(healthLost);
                for(int index=0; index<=29; index++) {
                    if(index==totHealthLost) {
                        imageMenu1=healthy.helath[index];
                    }
                }

                double hBoi2=50/29;
                int healthLoss2=50-p2.Health;
                double healthLost2=(healthLoss2/1.8)/hBoi2;
                int totHealthLost2=(int) Math.round(healthLost2);
                for(int index=0; index<=29; index++) {
                    if(index==totHealthLost2) {
                        imageMenu2=healthy.helath[index];
                    }
                }
                if(hb.wPlayer==1) {
                    imageMenu=imageMenu1;
                }
                else if(hb.wPlayer==2) {
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
                for(int index=0; index<npc.entity.length; index++) {
                    if (npc.entity[index].x==hb.resTileSize*13 && npc.entity[index].active==true) {
                        double hBoiN=npc.entity[index].maxHealth/29;
                int healthLossN=npc.entity[index].maxHealth-npc.entity[index].Health;
                double healthLostN=(healthLossN/1.8)/hBoiN;
                int totHealthLostN=(int) Math.round(healthLostN);
                for(int i=0; i<=29; i++) {
                    if(i==totHealthLostN) {
                        image=healthy.helath[i];
                    }
                    }
                }
                
                }
                x=hb.resTileSize*12;
                y=100;
                g2.drawImage(image, x,150,48*hb.scale, 20*hb.scale, null);
                break;
                case 3:
                for(int index=0; index<npc.entity.length; index++) {
                    if (npc.entity[index].x==hb.resTileSize*13+100 &&  npc.entity[index].active==true) {
                        double hBoiNp=npc.entity[index].maxHealth/29;
                int healthLossNp=npc.entity[index].maxHealth-npc.entity[index].Health;
                double healthLostNp=(healthLossNp/1.8)/hBoiNp;
                int totHealthLostNp=(int) Math.round(healthLostNp);
                for(int i=0; i<=29; i++) {
                    if(i==totHealthLostNp) {
                        image=healthy.helath[i];
                    }
                }
                    }
                }
                x=hb.resTileSize*12+100;
                y=200;
                g2.drawImage(image, x,250,48*hb.scale, 20*hb.scale, null);
                break;

            }
            g2.drawImage(selectImage,x ,y, hb.resTileSize, hb.resTileSize, null);
            if(k.enterPressed==true && k.hasMoved==false) {
                if (hb.charFight==true) {
                    if (player.cursorX>1) {
                        if (player.cursorX==2) {
                            hb.gSelectedX=hb.resTileSize*11;
                            hb.gSelectedY=100;
                        }
                        else if(player.cursorX==3) {
                            hb.gSelectedX=hb.resTileSize*11+100;
                            hb.gSelectedY=200;
                        }
                        if(hb.wPlayer==1) {
                            player.attackMode=true;
                        }
                        else if(hb.wPlayer==2) {
                            p2.attackMode=true;
                        }
                        if(hb.wPlayer==1) {
                            if (hb.Players[1].Health>0) {
                                hb.wPlayer=2;
                            }
                        }
                        else if(hb.wPlayer==2) {
                            if (hb.Players[0].Health>0) {
                                hb.wPlayer=1;
                            }
                        }
                        
                    }
                    
                }
                else if(hb.charItem==true) {
                    itemBoi=true;
                }
                
                k.hasMoved=true;
                hb.charFight=false;
                hb.charDefend=false;
                hb.charItem=false;
                hb.charSelected=false;
            }
            
        }
        int plHealth=1;
        if(hb.wPlayer==1) {
            plHealth=player.Health;
        }
        else if(hb.wPlayer==2) {
            plHealth=p2.Health;
        }
        if(hb.flee==false && hb.fight==false && hb.item==false && hb.defend==false && hb.charSelected==false) {
            t.draw(g2, "HP:"+plHealth+"/50", (hb.screenWidth/2)-hb.resTileSize*2, 14*hb.resTileSize/2, hb.resTileSize/2, hb.resTileSize/2);
            t.draw(g2, "XP:10/50", 3*hb.resTileSize/2, 15*hb.resTileSize/2, hb.resTileSize/2, hb.resTileSize/2);
            t.draw(g2, "LVL:1", 5*hb.resTileSize/2, 17*hb.resTileSize/2, hb.resTileSize/3, hb.resTileSize/3);
            t.draw(g2, "INV:"+player.items.length+"/10", 29*hb.resTileSize/2, 15*hb.resTileSize/2, hb.resTileSize/2, hb.resTileSize/2);
            t.draw(g2, "PL:"+hb.wPlayer, 31*hb.resTileSize/2, 17*hb.resTileSize/2, hb.resTileSize/3, hb.resTileSize/3);
            g2.drawImage(fightImage,2*hb.resTileSize/2, 440, 128, 128, null);
            g2.drawImage(defendImage,12*hb.resTileSize/2, 440, 128, 128, null);
            g2.drawImage(itemImage,hb.screenWidth-17*hb.resTileSize/2, 440, 128, 128, null);
            g2.drawImage(fleeImage,hb.screenWidth-7*hb.resTileSize/2, 440, 128, 128, null);
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
                    firstX=5*hb.resTileSize;
                    XCounter[0]=pixelLength;
                }
                else {
                    firstX=XCounter[index-1]+(index*(5*hb.resTileSize))+7*hb.resTileSize;
                }
                t.draw(g2, urmom, firstX, 19*hb.resTileSize/2, hb.resTileSize/2, hb.resTileSize/2);
                if(index==player.Selector) {
                    e=firstX-hb.resTileSize;
                }
            }
            g2.drawImage(selectImage, e, 10*hb.resTileSize, hb.resTileSize, hb.resTileSize, null);
            t.draw(g2, "ATTACK?", 16*hb.resTileSize/2, 7*hb.resTileSize, hb.resTileSize/2, hb.resTileSize/2);
            if(player.Selector==0 && k.enterPressed==true && k.hasMoved==false) {
                System.out.println("IT WORKED(FIGHT)");
                hb.charSelected=true;
                hb.charFight=true;
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
                    firstX=5*hb.resTileSize;
                    XCounter[0]=pixelLength;
                }
                else {
                    firstX=XCounter[index-1]+(index*(5*hb.resTileSize))+7*hb.resTileSize;
                }
                t.draw(g2, urmom, firstX, 19*hb.resTileSize/2, hb.resTileSize/2, hb.resTileSize/2);
                if(index==player.Selector) {
                    e=firstX-hb.resTileSize;
                }
            }
            g2.drawImage(selectImage, e, 10*hb.resTileSize, hb.resTileSize, hb.resTileSize, null);
            t.draw(g2, "DEFEND?", 16*hb.resTileSize/2, 7*hb.resTileSize, hb.resTileSize/2, hb.resTileSize/2);
            if(player.Selector==0 && k.enterPressed==true && k.hasMoved==false) {
                System.out.println("IT WORKED(DEFEND)");
                if (hb.wPlayer==2) {
                    hb.Players[1].defendMode=true;
                    if (hb.Players[0].Health>0) {
                        hb.wPlayer=1;
                    }
                    npc.attacking=true;
                }
                else if(hb.wPlayer==1) {
                    hb.Players[0].defendMode=true;
                    if (hb.Players[1].Health>0) {
                        hb.wPlayer=2;
                    }
                    else if(hb.Players[1].Health<=0) {
                        npc.attacking=true;
                    }
                }
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
                    firstX=XCounter[index-1]+(index*(2*hb.resTileSize))+hb.resTileSize;
                }
                t.draw(g2, urmom, firstX, 20*hb.resTileSize/2, hb.resTileSize/3, hb.resTileSize/3);
                if(index==player.Selector) {
                    e=firstX-hb.resTileSize;
                }
            }
            g2.drawImage(selectImage, e, 10*hb.resTileSize, hb.resTileSize, hb.resTileSize, null);
            t.draw(g2, "USE ITEM?", 16*hb.resTileSize/2, 7*hb.resTileSize, hb.resTileSize/2, hb.resTileSize/2);
            if(player.Selector==0 && k.enterPressed==true && k.hasMoved==false) {
                System.out.println("You ate an apple. Crunchy. +5hp!");
                hb.charSelected=true;
                hb.charItem=true;
                hb.item=false;
                player.Selector=0;
                k.hasMoved=true;
        }
        else if(player.Selector==1 && k.enterPressed==true && k.hasMoved==false) {
            System.out.println("You ate a banana. tastes rotten. -5hp.");
            hb.charSelected=true;
            hb.charItem=true;
            hb.item=false;
            player.Selector=0;
            k.hasMoved=true;
        }
        else if(player.Selector==2 && k.enterPressed==true && k.hasMoved==false) {
            System.out.println("You drink the concoction. You start to feel better. +5atk, +5hp");
            hb.charSelected=true;
            hb.charItem=true;
            hb.item=false;
            player.Selector=0;
            k.hasMoved=true;
        }
        else if(player.Selector==3 && k.enterPressed==true && k.hasMoved==false) {
            System.out.println("You put on the bandage. It's still sticky. +20hp.");
            hb.charSelected=true;
            hb.charItem=true;
            hb.item=false;
            player.Selector=0;
            k.hasMoved=true;
        }
        }
        else if(hb.flee==true) {
            double randomValue=Math.random();
    double realValue=Math.floor(randomValue*10)/10;
        if(runFailed==true) {
        failTimer++;
        if(failTimer<100) {
            t.draw(g2, "YOU TRIED TO RUN BUT FAILED.", 10*hb.resTileSize/2, 5*hb.resTileSize, hb.resTileSize/2, hb.resTileSize/2);
        }
        else if(failTimer>=100) {
            npc.attacking=true;
            failTimer=0;
            runFailed=false;
            hb.flee=false;
        }
        }
            if(runAway==true) {
                timer++;
                t.draw(g2, "YOU RAN AWAY.", 10*hb.resTileSize/2, 5*hb.resTileSize, hb.resTileSize/2, hb.resTileSize/2);
            }
                if(realValue>=0.5) {
                    if(player.screenX>-100 && runAway==true) {
                        player.screenX-=10;
                    }
                    if(p2.screenX>-100 && runAway==true) {
                        p2.screenX-=10;
                    }
                    if(timer>=100) {
                        for(int index=0; index<npc.entity.length; index++) {
                            npc.entity[index].active=false;
                        }
                        player.fightMode=false;
                        hb.stopMusic();
                        hb.playMusic(0);
                    p2.fightMode=false;
                    player.screenX=hb.screenWidth/2-hb.resTileSize/2;
                player.screenY=hb.screenHeight/2-hb.resTileSize/2;
                p2.screenX=hb.screenWidth/2-hb.resTileSize/2;
                p2.screenY=hb.screenHeight/2-hb.resTileSize/2;
                    hb.flee=false;
                    runAway=false;
                    }
                    
                }
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
                    firstX=5*hb.resTileSize;
                    XCounter[0]=pixelLength;
                }
                else {
                    firstX=XCounter[index-1]+(index*(5*hb.resTileSize))+7*hb.resTileSize;
                }
                t.draw(g2, urmom, firstX, 19*hb.resTileSize/2, hb.resTileSize/2, hb.resTileSize/2);
                if(index==player.Selector) {
                    e=firstX-hb.resTileSize;
                }
            }
            g2.drawImage(selectImage, e, 10*hb.resTileSize, hb.resTileSize, hb.resTileSize, null);
            t.draw(g2, "RUN AWAY?", 15*hb.resTileSize/2, 7*hb.resTileSize, hb.resTileSize/2, hb.resTileSize/2);
            if(player.Selector==0 && k.enterPressed==true && k.hasMoved==false) {
                if (runAway==false && runFailed==false) {
                    if(realValue<0.5) {
                        runFailed=true;
                }
                else {
                        runAway=true;
                    
                    
                    
                }
                }
                k.hasMoved=true;
                
                
            }
            else if(player.Selector==1 && k.enterPressed==true && k.hasMoved==false) {
                if (runAway==false && runFailed==false) {
                    hb.flee=false;
                    player.Selector=0;
                    k.hasMoved=true;
                }
            }
        } 
            
    }
   
}
}
