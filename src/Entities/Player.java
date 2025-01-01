package Entities;
import Main.The_Hub;
import Main.keyInput;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Player extends Entity{
    The_Hub hb;
    keyInput k;
    NPC npc;
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
    public Player(The_Hub hb, keyInput k, NPC npc) {
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
        worldX=hb.resTileSize*16;
        worldY=hb.resTileSize*19;
        screenX=hb.screenWidth/2-hb.resTileSize/2;
        screenY=hb.screenHeight/2-hb.resTileSize/2;
        colBox=new Rectangle(12, 21,30,27);
        attack=new BufferedImage[7];
        attackSpriteNum=1;
        attackSpriteCounter=0;
        moveSpeed=4;
        direction="down";
        SpriteNum=1;
        fightMode=false;
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
            worldX=100;
            worldY=100;
        }

    }
    public void update() {
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
                        worldY-=moveSpeed;
                        direction="up";
                    }
                    else if (k.leftPressed==true) {
                        worldX-=moveSpeed;
                        direction="left";
                    }
                    else if(k.downPressed==true) {
                        worldY+=moveSpeed;
                        direction="down";
                    }
                    else if(k.rightPressed==true) {
                        worldX+=moveSpeed;
                        direction="right";
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
                                if (npc.entity[0].active==true) {
                                    cursorX=2;
                                }
                                else if(npc.entity[0].active==false) {
                                    if (npc.entity[1].active==true) {
                                        cursorX=3;
                                    }
                                    else if(npc.entity[1].active==false) {
                                        cursorX=0;
                                    }
                                    
                                }
                            }
                            else if(cursorX==2) {
                                if (npc.entity[1].active==true) {
                                    cursorX=3;
                                }
                                else if(npc.entity[1].active==false) {
                                    if (this.active==true) {
                                        cursorX=0;
                                    }
                                    else if(this.active==false) {
                                        cursorX=1;
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
                                if (npc.entity[0].active==true) {
                                    cursorX=2;
                                }
                                else if(npc.entity[0].active==false) {
                                    if (hb.p2Active==true) {
                                        cursorX=1;
                                    }
                                    else if(hb.p2Active==false) {
                                        cursorX=0;
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
                                    if (npc.entity[1].active==true) {
                                        cursorX=3;
                                    }
                                    else if(npc.entity[1].active==false) {
                                        if (npc.entity[0].active==true) {
                                            cursorX=2;
                                        }
                                        else if(npc.entity[0].active==false) {
                                            cursorX=1;
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
                    if (worldX>hb.gSelectedX) {
                        if (worldY!=hb.gSelectedY) {
                            worldY+=10;
                        }
                        else {
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
                        if(worldX!=100) {
                            worldX-=10;
                        }
                        
                    }
                    else {
                        SpriteNum=1;
                        worldX+=10;
                    }

                    
                }
                else if(attackMode2==true) {
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
                    if(worldX!=100) {
                        worldX-=10;
                    }
                    else if(worldX==100){
                        if (worldY!=100) {
                            worldY-=10;
                        }
                        else if(worldY==100) {
                            if (hb.Players[1].Health<=0) {
                                npc.attacking=true;
                            }
                            cursorX=0;
                            healthTaker=false;
                            attackMode2=false;
                        }
                    }
                    else {
                        SpriteNum=1;
                        worldX+=10;
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
        if (fightMode==false) {
            g2.drawImage(image, screenX, screenY, Width, Height, null);
        }
        else if(fightMode==true) {
            g2.drawImage(image, worldX, worldY, Width, Height, null);
        }
    }
}
