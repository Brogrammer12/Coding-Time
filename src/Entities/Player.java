package Entities;
import Main.The_Hub;
import Main.keyInput;
import Tile.TileManager;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Player extends Entity{
    The_Hub hb;
    keyInput k;
    NPC npc;
    TileManager tileguy;
    public int Selector;
    public int timer=0;
    public int selectorX;
    public String[] items;
    public String[] weapons;
    public String[] defenses;
    public String[] running;
    public int arrayLength;
    public int cursorX;
    public int dedCounter=0;
    public boolean healthTaker=false;
    public boolean ded1;
    public boolean attackMode;
    public boolean attackMode2;
    public boolean mapBorder=false;
    public boolean load=false;
    public boolean readingSign=false;
    public boolean menu=false;
    String biome;
    public int I=90;
    public int objIndex;
    public boolean interact;
    public String[][] location=new String[10][4];
    public int YLevel=0;
    public int XLevel=1;
    public int goldNuggets=0;
    public boolean fuck=false;
    public boolean fucku=false;
    public boolean p2check=false;
    public String Armor="NONE";
    public String weapon="SWORD";
    public int menuSelectorY;
    public int menuSelectorX;
    public int menuX;
    public int menuY;
    public boolean settings=false;
    public boolean equip=false;
    public boolean borderX=false;
    public boolean borderY=false;
    public Player(The_Hub hb, keyInput k, NPC npc, TileManager tileguy) {
        super(hb);
        menuSelectorY=0;
        menuSelectorX=0;
        menuX=0;
        menuY=0;
        location[1] [0]="/Resources/tileMaps/startingarea.txt";
        location[1] [1]="/Resources/tileMaps/the_path.txt";
        location[1] [2]="/Resources/tileMaps/worldMap.txt";
        location[1] [3]="/Resources/tileMaps/forest.txt";
        location[0] [3]="/Resources/tileMaps/forestTreasure.txt";
        location[2] [2]="/Resources/tileMaps/nope.txt";
        location[0] [2]="/Resources/tileMaps/yup.txt";
        this.tileguy=tileguy;
        this.hb=hb;
        this.k=k;
        this.npc=npc;
        active=true;
        damage=10;
        Health=50;
        ded1=false;
        ded=false;
        plSwitch=false;
        defenseValue=5;
        attackMode=false;
        attackMode2=false;
        defendMode=false;
        items=new String[4];
        weapons=new String[2];
        defenses=new String[2];
        running=new String[2];
        weapons[0]="YES";
        weapons[1]="NO";
        defenses[0]="YES";
        defenses[1]="NO";
        items[0]="APPLE";
        items[1]=" BANANA";
        items[2]=" HEALTH POTION";
        items[3]="BANDAGE";
        running[0]="YES";
        running[1]="NO";
        setDefaultValues();
        playerImageLoader();
    }
    public void setDefaultValues() {
        //note to self:mapborder for up and down are 320, left and right 520 for worldmap
        worldX=hb.maxWorldWidth/2;
        worldY=hb.maxWorldHeight/2;
        screenX=hb.screenWidth/2-hb.resTileSize/2;
        screenY=hb.screenHeight/2-hb.resTileSize/2;
        colBox=new Rectangle(12, 21,30,27);
        solidAreaDefaultX=colBox.x;
        solidAreaDefaultY=colBox.y;
        attack=new BufferedImage[7];
        attackSpriteNum=1;
        attackSpriteCounter=0;
        moveSpeed=4;
        direction="down";
        SpriteNum=1;
        fightMode=true;
        buttonX=0;
        Selector=0;
        cursorX=0;
        selectorX=4*hb.resTileSize/2;
        if(fightMode==false) {
            Width=hb.resTileSize;
            Height=hb.resTileSize;
        }
        else if(fightMode==true) {
            Width=hb.fightWidth;
            Height=hb.fightHeight;
            //worldX=100;
            //worldY=100;
        }

    }
    public void update() {
        if (screenY<=hb.resTileSize) {
            if (YLevel!=location[0].length-1 && location[XLevel] [YLevel+1]!=null) {
                YLevel++;
                tileguy.newMap(location[XLevel][YLevel]);
            tileguy.loadMap();
            worldX=hb.maxWorldWidth/2;
            worldY=hb.maxWorldHeight-320;
            screenX=hb.screenWidth/2-hb.resTileSize/2;
            screenY=hb.screenHeight/2-hb.resTileSize/2;
                hb.Players[1].worldX=hb.maxWorldWidth/2;
                hb.Players[1].worldY=hb.maxWorldHeight-320;
            }
            
        }
        else if(screenY>=(hb.maxScreenVert*hb.resTileSize)-hb.resTileSize) {
            if (YLevel!=0 && location[XLevel] [YLevel-1]!=null) {
                YLevel--;
                tileguy.newMap(location[XLevel][YLevel]);
            tileguy.loadMap();
            screenX=hb.screenWidth/2-hb.resTileSize/2;
            screenY=hb.screenHeight/2-hb.resTileSize/2;
            worldX=hb.maxWorldWidth/2;
            worldY=320;
            hb.Players[1].worldX=hb.maxWorldWidth/2;
            hb.Players[1].worldY=320;
            }
        }
        else if(screenX<=hb.resTileSize) {
            if (XLevel!=0 && location[XLevel-1] [YLevel]!=null) {
                XLevel--;
                tileguy.newMap(location[XLevel][YLevel]);
            tileguy.loadMap();
            worldX=hb.maxWorldWidth-520;
            worldY=hb.maxWorldHeight/2;
            screenX=hb.screenWidth/2-hb.resTileSize/2;
            screenY=hb.screenHeight/2-hb.resTileSize/2;
                hb.Players[1].worldX=hb.maxWorldWidth-520;
                hb.Players[1].worldY=hb.maxWorldHeight/2;
            }
        }
        else if(screenX>=(hb.maxScreenHoriz*hb.resTileSize)-hb.resTileSize) {
            if (XLevel!=location.length-1 && location[XLevel+1] [YLevel]!=null) {
                XLevel++;
                tileguy.newMap(location[XLevel][YLevel]);
            tileguy.loadMap();
            screenX=hb.screenWidth/2-hb.resTileSize/2;
            screenY=hb.screenHeight/2-hb.resTileSize/2;
            worldX=520;
            worldY=hb.maxWorldHeight/2;
            hb.Players[1].worldX=520;
            hb.Players[1].worldY=hb.maxWorldHeight/2;
            }
        }
        if (Health<=0) {
            ded=true;
        }
        if(hb.fight==true) {
            arrayLength=weapons.length;
        }
        else if(hb.defend==true) {
            arrayLength=defenses.length;
        }
        else if(hb.item==true) {
            arrayLength=items.length;
        }
        else if(hb.flee==true) {
            arrayLength=running.length;
        }
            if(k.upPressed==true || k.leftPressed==true || k.downPressed==true || k.rightPressed==true) {
                if(fightMode==false) {
                    if(k.upPressed==true) {
                        direction="up";
                    }
                    else if (k.leftPressed==true) {
                        direction="left";
                    }
                    else if(k.downPressed==true) {
                        direction="down";
                    }
                    else if(k.rightPressed==true) {
                        direction="right";
                    }
                    collisionOn=false;
                    hb.cChecker.checkTile(this);
                    objIndex=hb.cChecker.checkObject(this, true);
                    interactWithObject(objIndex);
                    if (collisionOn==false) {
                        switch(direction) {
                            case "up":
                            if (borderY==false) {
                                worldY-=moveSpeed;
                            }
                            else if(borderY==true) {
                                screenY-=moveSpeed;
                            }
                            break;
                            case "down":
                            if (borderY==false) {
                                worldY+=moveSpeed;
                            }
                            else if(borderY==true) {
                                screenY+=moveSpeed;
                            }
                            break;
                            case "left":
                            if (borderX==false) {
                                worldX-=moveSpeed;
                            }
                            else if(borderX==true) {
                                screenX-=moveSpeed;
                            }
                            break;
                            case "right":
                            if (borderX==false) {
                                worldX+=moveSpeed;
                            }
                            else if(borderX==true) {
                                screenX+=moveSpeed;
                            }
                            break;
                        }
                    }
                    SpriteCounter++;
                    if(SpriteCounter>12) {
                        if(SpriteNum==1) {
                            SpriteNum=2;
                            
                        }
                        else if(SpriteNum==2) {
                            SpriteNum=1;
                            
                        }
                        SpriteCounter=0;
                    }
                }
                
                else if(fightMode==true) {
                    if(k.rightPressed==true && k.hasMoved==false) {
                        if(hb.fight==true || hb.defend==true || hb.item==true || hb.flee==true) {
                            if(Selector==arrayLength-1) {
                                Selector=0;
                            }
                            else {
                                Selector+=1;
                            }
                        }
                        else if(hb.charSelected==false){
                            if(buttonX==3) {
                                buttonX=0;
                            }
                            else{
                                buttonX+=1;
                               }
                        }
                        else if(hb.charSelected==true) { 
                            if (cursorX==1) {
                                for (int i=0; i<npc.entity.length; i++) {
                                    if (npc.entity[i].active==true && npc.entity[i].startX==hb.resTileSize*10) {
                                        cursorX=2;
                                    }
                                    else if(npc.entity[i].active==false && npc.entity[i].startX==hb.resTileSize*10 && npc.entity[i].Playing==true) {
                                        if (npc.entity[i].active==true && npc.entity[i].startX==hb.resTileSize*10+100) {
                                            cursorX=3;
                                        }
                                        else if(npc.entity[i].active==false && npc.entity[i].startX==hb.resTileSize*10+100 && npc.entity[i].Playing==true) {
                                            cursorX=0;
                                        }
                                        
                                    }
                                }
                            }
                            else if(cursorX==2) {
                                for (int i=0; i<npc.entity.length; i++) {
                                    if (npc.entity[i].active==true && npc.entity[i].startX==hb.resTileSize*10+100) {
                                        cursorX=3;
                                    }
                                    else if(npc.entity[i].active==false && npc.entity[i].startX==hb.resTileSize*10+100 && npc.entity[i].Playing==true) {
                                        if (this.active==true) {
                                            cursorX=0;
                                        }
                                        else if(this.active==false) {
                                            cursorX=1;
                                        }
                                    }
                                }
                            }
                            else {
                                if(cursorX==3) {
                                    cursorX=0;
                                }
                                else {
                                    cursorX+=1;
                                }
                            }
                            
                        }
                        k.hasMoved=true;
                    }
                    if(k.leftPressed==true && k.hasMoved==false) {
                        
                        if(hb.fight==true || hb.defend==true || hb.item==true || hb.flee==true) {
                            if(Selector==0) {
                                Selector=arrayLength-1;
                            }
                            else {
                                Selector-=1;
                            }
                        }
                        else if(hb.charSelected==false){
                            if(buttonX==0) {
                                buttonX=3;
                            }
                            else{
                                buttonX-=1;
                            }
                        }
                        else if(hb.charSelected==true) {
                            if (cursorX==3) {
                                for (int i=0; i<npc.entity.length; i++) {
                                    if (npc.entity[i].active==true && npc.entity[i].startX==hb.resTileSize*10) {
                                        cursorX=2;
                                    }
                                    else if(npc.entity[i].active==false && npc.entity[i].startX==hb.resTileSize*10) {
                                        if (hb.p2Active==true) {
                                            cursorX=1;
                                        }
                                        else if(hb.p2Active==false) {
                                            cursorX=0;
                                        }
                                    }
                                }
                            }
                            else if(cursorX==2) {
                                if (hb.p2Active==true) {
                                    cursorX=1;
                                }
                                else if(hb.p2Active==false) {
                                    if (this.active==true) {
                                        cursorX=0;
                                    }
                                    else if(this.active==false) {
                                        cursorX=3;
                                    }
                                }
                            }
                            else {
                                if(cursorX==0) {
                                    for (int i=0; i<npc.entity.length; i++) {
                                        if (npc.entity[i].active==true && npc.entity[i].startX==hb.resTileSize*10) {
                                            cursorX=3;
                                        }
                                        else if(npc.entity[i].active==false && npc.entity[i].startX==hb.resTileSize*10+100 && npc.entity[i].Playing==true) {
                                            if (npc.entity[i].active==true && npc.entity[i].startX==hb.resTileSize*10) {
                                                cursorX=2;
                                            }
                                            else if(npc.entity[i].active==false && npc.entity[i].startX==hb.resTileSize*10 && npc.entity[i].Playing==true) {
                                                cursorX=1;
                                            }
                                        }
                                    }
                                }
                                else {
                                    cursorX-=1;
                                }
                            }
                            
                        }
                        k.hasMoved=true;
                    }
                }
            }
            if(fightMode==true){
                if (Health>50) {
                    Health=50;
                }
                if(attackMode==true) {
                    if (screenX>hb.gSelectedX) {
                        if (screenY!=hb.gSelectedY) {
                            screenY+=10;
                        }
                        else {
                            if (fuck==false) {
                                hb.soundEffect(3);
                                fuck=true;
                            }
                            attackSpriteCounter++;
                    if(attackSpriteCounter>4) {
                        attackSpriteNum++;
                        if(attackSpriteNum>6) {
                            attackSpriteNum=1;
                            attackMode2=true;
                            attackMode=false;
                        }
                        attackSpriteCounter=0;
                    }
                        }
                        
                    }
                    else if(attackMode2==true) {  
                        if(screenX!=100) {
                            screenX-=10;
                        }
                        
                    }
                    else {
                        SpriteNum=1;
                        screenX+=10;
                    }

                    
                }
                else if(attackMode2==true) {
                    if (fucku==false) {
                        hb.soundEffect(4);
                        fucku=true;
                    }  
                    for(int index=0; index<npc.entity.length; index++) {
                        if (cursorX==2 && healthTaker==false) {
                            if (npc.entity[index].x==hb.resTileSize*10 && npc.entity[index].active==true) {
                                npc.entity[index].Health-=damage;
                            healthTaker=true;
                            }
                            
                        }
                        else if(cursorX==3 && healthTaker==false) {
                            if (npc.entity[index].x==hb.resTileSize*10+100 && npc.entity[index].active==true) {
                                npc.entity[index].Health-=damage;
                            healthTaker=true;
                            }
                        }
                    }
                    if(screenX!=100) {
                        screenX-=10;
                    }
                    else if(screenX==100){
                        if (screenY!=100) {
                            screenY-=10;
                        }
                        else if(screenY==100) {
                            if (hb.Players[1].Health<=0) {
                                npc.attacking=true;
                            }
                            cursorX=0;
                            healthTaker=false;
                            attackMode2=false;
                            fuck=false;
                            fucku=false;
                        }
                    }
                    else {
                        SpriteNum=1;
                        screenX+=10;
                    }
                }
                    SpriteCounter++;
                    if(SpriteCounter>20) {
                        if(SpriteNum==1) {
                            SpriteNum=2;
                            
                        }
                        else if(SpriteNum==2) {
                            SpriteNum=1;
                            
                        }
                        SpriteCounter=0;
                    }
                
                
            }
        
        }
        public void interactWithObject(int i) {
            interact=true;
        }
       
    
    public void playerImageLoader() {
        try {
            up1=ImageIO.read(getClass().getResourceAsStream("/Resources/Player1/PlayerUp1.png"));
            up2=ImageIO.read(getClass().getResourceAsStream("/Resources/Player1/PlayerUp2.png"));
            left1=ImageIO.read(getClass().getResourceAsStream("/Resources/Player1/PlayerLeft1.png"));
            left2=ImageIO.read(getClass().getResourceAsStream("/Resources/Player1/PlayerLeft2.png"));
            down1=ImageIO.read(getClass().getResourceAsStream("/Resources/Player1/PlayerDown1.png"));
            down2=ImageIO.read(getClass().getResourceAsStream("/Resources/Player1/PlayerDown2.png"));
            right1=ImageIO.read(getClass().getResourceAsStream("/Resources/Player1/PlayerRight1.png"));
            right2=ImageIO.read(getClass().getResourceAsStream("/Resources/Player1/PlayerRight2.png"));
            bob1=ImageIO.read(getClass().getResourceAsStream("/Resources/Player1/PlayerFight1.png"));
            bob2=ImageIO.read(getClass().getResourceAsStream("/Resources/Player1/PlayerFight2.png"));
            attack[0]=ImageIO.read(getClass().getResourceAsStream("/Resources/Player1/PlayerAttackOne.png"));
            attack[1]=ImageIO.read(getClass().getResourceAsStream("/Resources/Player1/PlayerAttackTwo.png"));
            attack[2]=ImageIO.read(getClass().getResourceAsStream("/Resources/Player1/PlayerAttackThree.png"));
            attack[3]=ImageIO.read(getClass().getResourceAsStream("/Resources/Player1/PlayerAttackFour.png"));
            attack[4]=ImageIO.read(getClass().getResourceAsStream("/Resources/Player1/PlayerAttackFive.png"));
            attack[5]=ImageIO.read(getClass().getResourceAsStream("/Resources/Player1/PlayerAttackSix.png"));
            defenseSprite=ImageIO.read(getClass().getResourceAsStream("/Resources/Buttons/DefenseSprite.png"));
            Dead1=ImageIO.read(getClass().getResourceAsStream("/Resources/Player1/p1Defeat1.png"));
            Dead2=ImageIO.read(getClass().getResourceAsStream("/Resources/Player1/p1Defeat2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void draw(Graphics2D g2) {
        int differenceX;
        int differenceY;
            if (I!=90) {
                differenceX=(worldX+hb.resTileSize/2)-(hb.obj[I].worldX+hb.resTileSize/2);
             differenceY=(worldY+hb.resTileSize/2)-(hb.obj[I].worldY+hb.resTileSize/2);
            }
            else {
                differenceX=0;
                differenceY=0;
            }
            if (Math.abs(differenceX)>hb.resTileSize || Math.abs(differenceY)>hb.resTileSize) {
                hb.obj[I].yeItCollided=false;
                p2check=true;
            }
            if (I!=90 && hb.obj[I].yeItCollided==true) {
                hb.obj[I].interaction(g2);
            }
            if (objIndex!=999) {
                I=objIndex;
                hb.obj[objIndex].yeItCollided=true;
                p2check=false;
            }
        
        BufferedImage image=null;
        if(fightMode==false) {
            switch(direction) {
                case "up":
                if(SpriteNum==1) {
                    image=up1;
                }
                if(SpriteNum==2) {
                    image=up2;
                }
                break;
                case "left":
                if(SpriteNum==1) {
                    image=left1;
                }
                if(SpriteNum==2) {
                    image=left2;
                }
                break;
                case "down":
                if(SpriteNum==1) {
                    image=down1;
                }
                if(SpriteNum==2) {
                    image=down2;
                }
                break;
                case "right":
                if(SpriteNum==1) {
                    image=right1;
                }
                if(SpriteNum==2) {
                    image=right2;
                }
                break;
            }
        }
        else if(fightMode==true) {
            if(attackMode==true) {
                switch(attackSpriteNum) {
                    case 1:
                    image=attack[0];
                    break;
                    case 2:
                    image=attack[1];
                    break;
                    case 3:
                    image=attack[2];
                    break;
                    case 4:
                    image=attack[3];
                    break;
                    case 5:
                    image=attack[4];
                    break;
                    case 6:
                    image=attack[5];
                    break;
                }
            }
            else if(ded==true) {
                if (ded1==false) {
                    dedCounter++;
                    if (dedCounter<30) {
                        image=Dead1;
                    }
                    else if(dedCounter>=30) {
                        ded1=true;
                    }
                }
                else if(ded1==true) {
                    image=Dead2;
                }
            }
            else {
                switch(SpriteNum) {
                    case 1:
                    image=bob1;
                    break;
                    case 2:
                    image=bob2;
                    break;
                }
            }
            if (defendMode==true) {
            g2.drawImage(defenseSprite, (hb.resTileSize*3)/2+100, 100-hb.resTileSize/4, (hb.resTileSize*3)/2, (hb.resTileSize*4)/2, null);
                if (plSwitch==false) {
                    hb.wPlayer=2;
                    plSwitch=true;
                }
                
            }
            
        }
        if(fightMode==false) {
            Width=hb.resTileSize;
            Height=hb.resTileSize;
        }
        else if(fightMode==true) {
            Width=hb.fightWidth;
            Height=hb.fightHeight;
        }
        if (fightMode==true && load==false) {
            screenX=100;
            screenY=100;
            load=true;
            System.out.println("I worked");
        }
                g2.drawImage(image, screenX, screenY, Width, Height, null);
                if (fightMode==false) {
                    if (k.escPressed==true && k.hasMoved==false && settings==false && equip==false) {
                        if (menu==false) {
                            hb.gameState=hb.pauseState;
                            menu=true;
                        }
                        else if(menu==true) {
                            hb.gameState=hb.playState;
                            menu=false;
                        }
                        k.hasMoved=true;
                    }
                    if (menu==true) {
                        if (k.downPressed==true && k.hasMoved==false) {
                            if (menuSelectorY>=2) {
                                menuSelectorY=0;
                            }
                            else {
                                menuSelectorY++;
                            }
                            k.hasMoved=true;
                        }
                        else if(k.upPressed==true && k.hasMoved==false) {
                            if (menuSelectorY<=0) {
                                menuSelectorY=2;
                            }
                            else {
                                menuSelectorY--;
                            }
                            k.hasMoved=true;
                        }
                        g2.setColor(Color.BLACK);
                        g2.fillRect((hb.maxScreenHoriz*hb.resTileSize)/2-13*hb.resTileSize/2, (hb.maxScreenVert*hb.resTileSize)/2-6*hb.resTileSize, 12*hb.resTileSize, 13*hb.resTileSize);
                        if (settings==false && equip==false) {
                            if (k.enterPressed==true && k.hasMoved==false) {
                                switch(menuSelectorY) {
                                    case 0:
                                    equip=true;
                                    break;
                                    case 1:
                                    settings=true;
                                    break;
                                    case 2:
                                    System.exit(0);
                                    break;
                                }
                                k.hasMoved=true;
                            }
                            hb.textboi.draw(g2, "NUGGETS:"+goldNuggets, 100, 20, hb.resTileSize/2, hb.resTileSize/2);
                            hb.textboi.draw(g2, "HP:"+Health, 100, 80, hb.resTileSize/2, hb.resTileSize/2);
                            hb.textboi.draw(g2, "ARMOR:"+Armor, 350, 20, hb.resTileSize/2, hb.resTileSize/2);
                            hb.textboi.draw(g2, "WEAPON:"+weapon, 350, 80, hb.resTileSize/2, hb.resTileSize/2);
                            hb.textboi.draw(g2, "EQUIP", 270, 230, hb.resTileSize/2, hb.resTileSize/2);
                            hb.textboi.draw(g2, "SETTINGS", 245, 320, hb.resTileSize/2, hb.resTileSize/2);
                            hb.textboi.draw(g2, "EXIT GAME", 240, 410, hb.resTileSize/2, hb.resTileSize/2);
                            switch (menuSelectorY) {
                                case 0:
                                menuX=200;
                                menuY=220;
                                break;
                                case 1:
                                menuX=180;
                                menuY=310;
                                break;
                                case 2:
                                menuX=170;
                                menuY=400;
                            }
                        }
                        else if(settings==true) {
                            if (k.escPressed==true && k.hasMoved==false) {
                                settings=false;
                                k.hasMoved=true;
                            }
                            hb.textboi.draw(g2, "NONE FOR NOW", menuX, menuY, hb.resTileSize/2, hb.resTileSize/2);
                            g2.drawImage(hb.fightingboi.selector, menuX, menuY,hb.resTileSize, hb.resTileSize, null);
                        }
                        else if(equip==true) {
                             if (k.escPressed==true && k.hasMoved==false) {
                                equip=false;
                                k.hasMoved=true;
                            }
                            else if(k.leftPressed==true && k.hasMoved==false) {
                                if (menuSelectorX<=1) {
                                    menuSelectorX=items.length;
                                }
                                else {
                                    menuSelectorX--;
                                }
                                k.hasMoved=true;
                            }
                            else if(k.rightPressed==true && k.hasMoved==false) {
                                if (menuSelectorX>=items.length) {
                                    menuSelectorX=1;
                                }
                                else {
                                    menuSelectorX++;
                                }
                                k.hasMoved=true;
                            }
                            int[] XCounter=new int[items.length];
            int firstX=0;
            int e=0;
            for(int index=0; index<items.length; index++) {
                String urmom=items[index];
                int pixelLength=urmom.length()*16;
                if(index>0) {
                    XCounter[index]=XCounter[index-1]+pixelLength;
                }
                if(index==0) {
                    firstX=hb.resTileSize*2;
                    XCounter[0]=pixelLength+hb.resTileSize;
                }
                else {
                    firstX=XCounter[index-1]+(index*(hb.resTileSize))+hb.resTileSize;
                }
                hb.textboi.draw(g2, urmom, firstX, 7*hb.resTileSize/2, hb.resTileSize/3, hb.resTileSize/3);
                if(index==menuSelectorX) {
                    e=firstX-hb.resTileSize;
                }
            }
            g2.drawImage(hb.fightingboi.selector, e, 7*hb.resTileSize/2, hb.resTileSize, hb.resTileSize, null);
                        }
                        if (settings==false && equip==false) {
                            g2.drawImage(hb.fightingboi.selector, menuX, menuY, hb.resTileSize, hb.resTileSize, null);
                        }

                    }
                }
            
    }
}
