package Entities;
import Main.The_Hub;
import Main.keyInput2;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Player2 extends Entity{
    The_Hub hb;
    keyInput2 k;
    public int p2Health;
    public boolean attackMode;
    public boolean attackMode2;
    public boolean defendMode;
    public boolean healthTaker=false;
    NPC npc;
    Player player;
    public Player2(The_Hub hb, keyInput2 k, Player player, NPC npc) {
        this.hb=hb;
        this.k=k;
        this.npc=npc;
        active=true;
        this.player=player;
        setDefaultValues();
        playerImageLoader();
    }
    public void setDefaultValues() {
        x=200;
        y=200;
        p2Health=25;
        attack=new BufferedImage[6];
        attackMode=false;
        attackMode2=false;
        defendMode=false;
        attackSpriteNum=1;
        attackSpriteCounter=0;
        moveSpeed=4;
        direction="down";
        SpriteNum=1;
        fightMode=true;
            Width=hb.resTileSize;
            Height=hb.resTileSize;

    }
    public void update() {
            if(k.upPressed==true || k.leftPressed==true || k.downPressed==true || k.rightPressed==true) {
                if(fightMode==false) {
                    if(k.upPressed==true) {
                        y-=moveSpeed;
                        direction="up";
                    }
                    else if (k.leftPressed==true) {
                        x-=moveSpeed;
                        direction="left";
                    }
                    else if(k.downPressed==true) {
                        y+=moveSpeed;
                        direction="down";
                    }
                    else if(k.rightPressed==true) {
                        x+=moveSpeed;
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
            }
        if(fightMode==true) {
            if(p2Health>50) {
                p2Health=50;
            }
            if(attackMode==true) {
                if (x>hb.gSelectedX) {
                    if (y!=hb.gSelectedY) {
                        y-=10;
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
                    if(x!=100) {
                        x-=10;
                    }
                }
                else {
                    SpriteNum=1;
                    x+=10;
                }

                
            }
            else if(attackMode2==true) {
                for(int index=0; index<npc.entity.length; index++) {
                    if (player.cursorX==2 && healthTaker==false) {
                        if (npc.entity[index].x==hb.resTileSize*10 && npc.entity[index].active==true) {
                            npc.entity[index].Health-=10;
                        healthTaker=true;
                        }
                        
                    }
                    else if(player.cursorX==3 && healthTaker==false) {
                        if (npc.entity[index].x==hb.resTileSize*10+100 && npc.entity[index].active==true) {
                            npc.entity[index].Health-=10;
                        healthTaker=true;
                        }
                    }
                }
                if(x!=200) {
                    x-=10;
                }
                else if(x==200){
                    if (y!=200) {
                        y+=10;
                    }
                    else if(y==200) {
                        player.cursorX=0;
                        healthTaker=false;
                        attackMode2=false;
                    }
                    
                }
                else {
                    SpriteNum=1;
                    x+=10;
                }
            }
            else {
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
            
        }
        if(fightMode==false) {
            Width=hb.resTileSize;
            Height=hb.resTileSize;
        }
        else if(fightMode==true) {
            Width=hb.fightWidth;
            Height=hb.fightHeight;
        }
        g2.drawImage(image, x, y, Width, Height, null);
    }
}
