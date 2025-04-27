package Entities;
import Main.TextReader;
import Main.The_Hub;
import Main.keyInput2;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Player2 extends Entity{
    The_Hub hb;
    keyInput2 k;
    TextReader t;
    public boolean attackMode;
    public boolean attackMode2;
    public boolean uWon=false;
    public boolean finished=false;
    public boolean healthTaker=false;
    public boolean ded1=false;
    public int dedCounter=0;
    public boolean dedNum=false;
    public boolean load=false;
    public boolean readingSign=false;
    public int objIndex;
    public int I=90;
    NPC npc;
    Player player;
    public boolean fuck=false;
    public boolean fucku=false;
    public Player2(The_Hub hb, keyInput2 k, Player player, NPC npc, TextReader t) {
        super(hb);
        this.hb=hb;
        this.k=k;
        this.t=t;
        colBox=solidArea;
        solidAreaDefaultX=colBox.x;
        solidAreaDefaultY=colBox.y;
        defenseValue=5;
        damage=10;
        ded=false;
        this.npc=npc;
        active=false;
        this.player=player;
        setDefaultValues();
        playerImageLoader();
    }
    public void setDefaultValues() {
        worldX=hb.player.worldX;
        worldY=hb.player.worldY;
        screenX=hb.screenWidth/2+hb.resTileSize;
        screenY=hb.screenHeight/2+hb.resTileSize;
        Health=50;
        plSwitch=false;
        attack=new BufferedImage[6];
        attackMode=false;
        attackMode2=false;
        defendMode=false;
        attackSpriteNum=1;
        attackSpriteCounter=0;
        moveSpeed=4;
        direction="down";
        SpriteNum=1;
        fightMode=false;
            Width=hb.resTileSize;
            Height=hb.resTileSize;

    }
    public void updateLogic() {
        locationx=hb.player.XLevel;
        locationy=hb.player.YLevel;
        if (Health<=0) {
            ded=true;
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
                                            switch (direction) {
                                                case "up":
                                                screenY-=moveSpeed;
                                                worldY-=moveSpeed;
                                                    break;
                                                    case "down":
                                                    screenY+=moveSpeed;
                                                    worldY+=moveSpeed;
                                                    break;
                                                    case "left":
                                                    screenX-=moveSpeed;
                                                    worldX-=moveSpeed;
                                                    break;
                                                    case "right":
                                                    screenX+=moveSpeed;
                                                    worldX+=moveSpeed;
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
                                }
                            if(fightMode==true) {
                                if(Health>50) {
                                    Health=50;
                                }
                                if(attackMode==true) {
                                    if (screenX>hb.gSelectedX) {
                                        if (screenY!=hb.gSelectedY) {
                                            screenY-=10;
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
                                        if (player.cursorX==2 && healthTaker==false) {
                                            if (npc.entity[index].x==hb.resTileSize*13 && npc.entity[index].active==true) {
                                                npc.entity[index].Health-=damage;
                                            healthTaker=true;
                                            }
                                            
                                        }
                                        else if(player.cursorX==3 && healthTaker==false) {
                                            if (npc.entity[index].x==hb.resTileSize*13+100 && npc.entity[index].active==true) {
                                                npc.entity[index].Health-=damage;
                                            healthTaker=true;
                                            }
                                        }
                                    }
                                    if(screenX!=200) {
                                        screenX-=10;
                                    }
                                    else if(screenX==200){
                                        if (screenY!=200) {
                                            screenY+=10;
                                        }
                                        else if(screenY==200) {
                                            npc.attacking=true;
                                            player.cursorX=0;
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
                           
                        }
                        public void playerImageLoader() {
        try {
            up1=ImageIO.read(getClass().getResourceAsStream("/Resources/Player2/Player2Up1.png"));
            up2=ImageIO.read(getClass().getResourceAsStream("/Resources/Player2/Player2Up2.png"));
            left1=ImageIO.read(getClass().getResourceAsStream("/Resources/Player2/Player2Left1.png"));
            left2=ImageIO.read(getClass().getResourceAsStream("/Resources/Player2/Player2Left2.png"));
            down1=ImageIO.read(getClass().getResourceAsStream("/Resources/Player2/Player2Down1.png"));
            down2=ImageIO.read(getClass().getResourceAsStream("/Resources/Player2/Player2Down2.png"));
            right1=ImageIO.read(getClass().getResourceAsStream("/Resources/Player2/Player2Right1.png"));
            right2=ImageIO.read(getClass().getResourceAsStream("/Resources/Player2/Player2Right2.png"));
            bob1=ImageIO.read(getClass().getResourceAsStream("/Resources/Player2/Player2Fight1.png"));
            bob2=ImageIO.read(getClass().getResourceAsStream("/Resources/Player2/Player2Fight2.png"));
            attack[0]=ImageIO.read(getClass().getResourceAsStream("/Resources/Player2/PlayerTwoAttackOne.png"));
            attack[1]=ImageIO.read(getClass().getResourceAsStream("/Resources/Player2/PlayerTwoAttackTwo.png"));
            attack[2]=ImageIO.read(getClass().getResourceAsStream("/Resources/Player2/PlayerTwoAttackThree.png"));
            attack[3]=ImageIO.read(getClass().getResourceAsStream("/Resources/Player2/PlayerTwoAttackFour.png"));
            attack[4]=ImageIO.read(getClass().getResourceAsStream("/Resources/Player2/PlayerTwoAttackFive.png"));
            attack[5]=ImageIO.read(getClass().getResourceAsStream("/Resources/Player2/PlayerTwoAttackSix.png"));
            defenseSprite=ImageIO.read(getClass().getResourceAsStream("/Resources/Buttons/DefenseSprite.png"));
            Dead1=ImageIO.read(getClass().getResourceAsStream("/Resources/Player2/p2Defeat1.png"));
            Dead2=ImageIO.read(getClass().getResourceAsStream("/Resources/Player2/p2Defeat2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void drawLogic(Graphics2D g2) {
        int differenceX;
        int differenceY;
        if (hb.player.p2check==true) {
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
            }
            if (I!=90 && hb.obj[I].yeItCollided==true) {
                hb.obj[I].interaction(g2);
            }
            if (objIndex!=999) {
                I=objIndex;
                hb.obj[objIndex].yeItCollided=true;
            }
        }
        if (Health<=0) {
            if (player.Health<=0) {
                if (dedCounter<240) {
                    dedCounter++;
                    t.draw(g2, "YOU LOST...", 200, 100, hb.resTileSize/2, hb.resTileSize/2);
                }
                else if(dedCounter>=240) {
                    player.screenX=hb.screenWidth/2-hb.resTileSize/2;
                player.screenY=hb.screenHeight/2-hb.resTileSize/2;
                screenX=hb.screenWidth/2-hb.resTileSize/2;
                screenY=hb.screenHeight/2-hb.resTileSize/2;
                    player.fightMode=false;
                    fightMode=false;
                    dedNum=true;
                    hb.stopMusic();
                    hb.playMusic(0);
                    dedCounter=0;
                    player.Health=50;
                    Health=50;
                }
            }
        }
        //Here's the code for the winning animation
        if (fightMode==true) {
            boolean someLeft=false;
        boolean healthLeft=false;
        for(int index=0; index<npc.entity.length; index++) {
            if (dedNum==true) {
                npc.entity[index].active=false;
            }
            if (npc.entity[index].Health>0 && npc.entity[index].Playing==true) {
                healthLeft=true;
            }
            if (npc.entity[index].active==true) {
                someLeft=true;
            }
            else if(index==npc.entity.length-1 && someLeft==false && finished==false && healthLeft==false) {
                uWon=true;
            }
        }
        if (uWon==true) {
                t.draw(g2, "YOU WON!", 100, 100, hb.resTileSize/2, hb.resTileSize/2);
            timer++;
            if (timer>=100) {
                player.screenX=hb.screenWidth/2-hb.resTileSize/2;
                player.screenY=hb.screenHeight/2-hb.resTileSize/2;
                screenX=hb.screenWidth/2-hb.resTileSize/2;
                screenY=hb.screenHeight/2-hb.resTileSize/2;
            player.fightMode=false;
            hb.stopMusic();
            hb.playMusic(0);
            this.fightMode=false;
            someLeft=false;
            uWon=false;
            healthLeft=false;
            finished=true;
            timer=0;
            }
            
        }
        }
        //Winning animation code ends here
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
                g2.drawImage(defenseSprite,(hb.resTileSize*3)/2+200, 200-hb.resTileSize/4, (hb.resTileSize*3)/2, (hb.resTileSize*4)/2, null);
                if (plSwitch==false) {
                    hb.wPlayer=1;
                    plSwitch=true;
                }
                    
                }
            
        }
        if(fightMode==false) {
            Width=hb.resTileSize;
            Height=hb.resTileSize;
            if (player.borderX==false && player.borderY==false) {
                screenX=worldX-hb.Players[0].worldX+hb.Players[0].screenX;
                screenY=worldY-hb.Players[0].worldY+hb.Players[0].screenY;
            }
        }
        else if(fightMode==true) {
            Width=hb.fightWidth;
            Height=hb.fightHeight;
        }
        if (fightMode==true && load==false) {
            screenX=200;
            screenY=200;
            load=true;
        }
        g2.drawImage(image, screenX, screenY, Width, Height, null);
    }
    public void setaction() {
        if (hb.keyBoi2.p2Switch==true) {
            onPath=false;
        }
        else {
            onPath=true;
        if (onPath==true) {
            int goalCol = (hb.player.worldX + hb.player.solidArea.x + hb.player.solidArea.width / 2) / hb.resTileSize;
            int goalRow = (hb.player.worldY + hb.player.solidArea.y + hb.player.solidArea.height / 2) / hb.resTileSize;
            searchPath(goalCol, goalRow);
        }
        }
    }
}
