package Entities;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import Main.TextReader;
import Main.The_Hub;
import Main.keyInput;

public class NPC extends Entity{
    The_Hub hb;
    public Entity[] entity;
    public Entity[] Players;
    public BufferedImage image;
    public BufferedImage Attack;
    public int fighter=1;
    public int attackX;
    public int attackY;
    public double Pl;
    public boolean Switch=false;
    public boolean moveDone=false;
    public boolean f1=false;
    public boolean f2=false;
    public boolean entity1=false;
    public boolean entity2=false;
    TextReader t;
    public NPC(The_Hub hb, Skelly Skellywag, Nec Necromancer, TextReader t) {
        this.hb=hb;
        this.t=t;
        timer=0;
        Pl=0;
        timeUp=false;
        attacking=false;
        SpriteNum=1;
        this.entity=new Entity[]{
            new Nec(hb),
            new Skelly(hb)
        };
    }
    public void update() {
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
                for(int index=0; index<entity.length; index++) {
                    if(entity[index].Health<=0) {
                        entity[index].active=false;
                    }
                }
        }
        public void draw(Graphics2D g2) {
            Attack=null;
            if (timeUp==true) {
                timer++;
            }
            for(int index=0; index<entity.length; index++) {
                    switch (SpriteNum) {
                        case 1:
                        image=entity[index].bob1;
                        break;
                        case 2:
                        image=entity[index].bob2;
                        break;
                    }
                
                if(attacking==true) {
                    if (fighter==1) {
                        if (entity[index].active==false && entity[index].startX==hb.resTileSize*10) {
                            fighter=2;
                        }
                        if (f1==false) {
                            Pl=Math.random();
                        Pl=Math.round(Pl);
                        f1=true;
                        }
                        if(entity[index].moveToFight==true && entity[index].active==true && entity[index].startX==hb.resTileSize*10) {
                                if (Pl==0) {
                                    if (hb.Players[0].ded==true) {
                                        if (hb.Players[1].ded==true) {
                               
                                            System.out.println("I worked");
                                        }
                                        else if(hb.Players[1].ded==false) {
                                            Pl=1;
                                        }
                                    }
                                    if (moveDone==false) {
                                        entity[index].x-=10;
                                        if (entity[index].x==100) {
                                            moveDone=true;
                                            }
                                    }
                                    else if(moveDone==true) {
                                        switch(SpriteNum) {
                                            case 1:
                                            image=entity[index].attack[0];
                                            break;
                                            case 2:
                                            image=entity[index].attack[1];
                                        }
                                        timeUp=true;
                                        if (timer==60) {
                                            timeUp=false;
                                            entity[index].x+=10;
                                        if (entity[index].x==hb.resTileSize*10) {
                                            timer=0;
                                            hb.Players[0].Health-=entity[index].damage;
                                            if (hb.Players[0].defendMode==true) {
                                                hb.Players[0].Health+=hb.Players[0].defenseValue;
                                            }
                                            if (hb.Players[1].defendMode==true) {
                                                hb.Players[1].Health+=hb.Players[1].defenseValue;
                                            }
                                            moveDone=false;
                                            f1=false;
                                            Switch=true;
                                            fighter=2;
                                            
                                        }
                                        }
                                    }
                                }
                                else if(Pl==1) {
                                    if (hb.Players[1].ded==true) {
                                        if (hb.Players[0].ded==true) {
                                  
                                            System.out.println("I worked");
                                        }
                                        else if(hb.Players[0].ded==false) {
                                            Pl=0;
                                        }
                                    }
                                    if (moveDone==false) {
                                        if (entity[index].x!=200) {
                                            entity[index].x-=10;
                                        }
                                        else if (entity[index].x==200) {
                                            if (entity[index].y!=200) {
                                                entity[index].y+=10;
                                            }
                                            else if(entity[index].y==200) {
                                                moveDone=true;
                                            }
                                            }
                                    }
                                    else if(moveDone==true) {
                                        switch(SpriteNum) {
                                            case 1:
                                            image=entity[index].attack[0];
                                            break;
                                            case 2:
                                            image=entity[index].attack[1];
                                            break;
                                        }
                                        timeUp=true;
                                        if (timer==60) {
                                            timeUp=false;
                                            if (entity[index].x!=hb.resTileSize*10) {
                                                entity[index].x+=10;
                                            }
                                            else if(entity[index].x==hb.resTileSize*10) {
                                                if (entity[index].y!=100) {
                                                    entity[index].y-=10;
                                                }
                                                else if(entity[index].y==100) {
                                                    hb.Players[1].Health-=entity[index].damage;
                                                    if (hb.Players[1].defendMode==true) {
                                                        hb.Players[1].Health+=hb.Players[1].defenseValue;
                                                    }
                                                    if (hb.Players[0].defendMode==true) {
                                                    hb.Players[0].Health+=hb.Players[0].defenseValue;
                                                    }
                                                    timer=0;
                                                    f1=false;
                                                    moveDone=false;
                                                    fighter=2;
                                                }
                                            }
                                        }
                                    }
                                }
                            
                        }
                        else if(entity[index].moveToFight==false && entity[index].active==true && entity[index].startX==hb.resTileSize*10) {
                            if (Pl==0) {
                                attackX=100;
                                attackY=100;
                                if (hb.Players[0].ded==true) {
                                    if (hb.Players[1].ded==true) {
                                    }
                                    else if(hb.Players[1].ded==false) {
                                        Pl=1;
                                    }
                                }
                            }
                            else if(Pl==1) {
                                attackX=200;
                                attackY=200;
                                if (hb.Players[1].ded==true) {
                                    if (hb.Players[0].ded==true) {
                                    }
                                    else if(hb.Players[0].ded==false) {
                                        Pl=0;
                                    }
                                }
                            }
                            timeUp=true;
                            if (timer!=60) {
                                switch(SpriteNum) {
                                    case 1:
                                    Attack=entity[index].attack[0];
                                    break;
                                    case 2:
                                    Attack=entity[index].attack[1];
                                }
                            }
                            else if(timer==60) {
                                if (Pl==0) {
                                    hb.Players[0].Health-=entity[index].damage;
                                    if (hb.Players[0].defendMode==true) {
                                        hb.Players[0].Health+=hb.Players[0].defenseValue;
                                    }
                                }
                                else if(Pl==1) {
                                    hb.Players[1].Health-=entity[index].damage;
                                    if (hb.Players[1].defendMode==true) {
                                        hb.Players[1].Health+=hb.Players[1].defenseValue;
                                    }
                                }
                                timeUp=false;
                                f1=false;
                                timer=0;
                                fighter=2;
                            }
                        }
                    }
                    else if(fighter==2) {
                        if (entity[index].active==false && entity[index].startX==hb.resTileSize*10+100) {
                            hb.Players[0].defendMode=false;
                            hb.Players[1].defendMode=false;
                            attacking=false;
                            fighter=1;
                        }
                        if (f2==false) {
                        Pl=Math.random();
                        Pl=Math.round(Pl);
                        f2=true;
                        }
                            if(entity[index].moveToFight==true && entity[index].active==true && entity[index].startX==hb.resTileSize*10+100) {
                                    if (Pl==1) {
                                        if (hb.Players[1].ded==true) {
                                            if (hb.Players[0].ded==true) {
                                            }
                                            else if(hb.Players[0].ded==false) {
                                                Pl=0;
                                            }
                                        }
                                        if (moveDone==false) {
                                            entity[index].x-=10;
                                            if (entity[index].x<=210) {
                                                moveDone=true;
                                                }
                                        }
                                        else if(moveDone==true) {
                                            switch(SpriteNum) {
                                                case 1:
                                                image=entity[index].attack[0];
                                                break;
                                                case 2:
                                                image=entity[index].attack[1];
                                            }
                                            timeUp=true;
                                            if (timer==60) {
                                                timeUp=false;
                                                entity[index].x+=10;
                                            if (entity[index].x==hb.resTileSize*10+100) {
                                                hb.Players[1].Health-=entity[index].damage;
                                                if (hb.Players[1].defendMode==true) {
                                                    hb.Players[1].Health+=hb.Players[1].defenseValue;
                                                    hb.Players[1].defendMode=false;
                                                }
                                                if(hb.Players[0].defendMode==true) {
                                                    hb.Players[0].defendMode=false;
                                                }
                                                timer=0;
                                                f2=false;
                                                moveDone=false;
                                                fighter=1;
                                                attacking=false;
                                            }
                                            }
                                        }
                                    }
                                    else if(Pl==0) {
                                        if (hb.Players[0].ded==true) {
                                            if (hb.Players[1].ded==true) {
                                            }
                                            else if(hb.Players[1].ded==false) {
                                                Pl=1;
                                            }
                                        }
                                        if (moveDone==false) {
                                            if (entity[index].x!=100) {
                                                entity[index].x-=10;
                                            }
                                            else if (entity[index].x==100) {
                                                if (entity[index].y!=100) {
                                                    entity[index].y-=10;
                                                }
                                                else if(entity[index].y==100) {
                                                        moveDone=true;
                                                    
                                                }
                                                }
                                        }
                                        else if(moveDone==true) {
                                            switch(SpriteNum) {
                                                case 1:
                                                image=entity[index].attack[0];
                                                break;
                                                case 2:
                                                image=entity[index].attack[1];
                                                break;
                                            }
                                            timeUp=true;
                                            if (timer==60) {
                                                timeUp=false;
                                                if (entity[index].x!=hb.resTileSize*10+100) {
                                                    entity[index].x+=10;
                                                }
                                                else if(entity[index].x==hb.resTileSize*10+100) {
                                                    if (entity[index].y!=200) {
                                                        entity[index].y+=10;
                                                    }
                                                    else if(entity[index].y==200) {
                                                        hb.Players[0].Health-=entity[index].damage;
                                                        if (hb.Players[0].defendMode==true) {
                                                            hb.Players[0].Health+=hb.Players[0].defenseValue;
                                                            hb.Players[0].defendMode=false;
                                                        }
                                                        if(hb.Players[1].defendMode==true) {
                                                            hb.Players[1].defendMode=false;
                                                        }
                                                        timer=0;
                                                        f2=false;
                                                        moveDone=false;
                                                        fighter=1;
                                                        attacking=false;
                                                    }
                                                }
                                            }
                                        }
                                    }
                                
                            }
                            else if(entity[index].moveToFight==false && entity[index].active==true && entity[index].startX==hb.resTileSize*10+100) {
                                if (Pl==0) {
                                    attackX=100;
                                    attackY=100;
                                    if (hb.Players[0].ded==true) {
                                        if (hb.Players[1].ded==true) {
                                        }
                                        else if(hb.Players[1].ded==false) {
                                            Pl=1;
                                        }
                                    }
                                }
                                else if(Pl==1) {
                                    attackX=200;
                                    attackY=200;
                                    if (hb.Players[1].ded==true) {
                                        if (hb.Players[0].ded==true) {
                                        }
                                        else if(hb.Players[0].ded==false) {
                                            Pl=0;
                                        }
                                    }
                                }
                                timeUp=true;
                                if (timer!=60) {
                                    switch(SpriteNum) {
                                        case 1:
                                        Attack=entity[index].attack[0];
                                        break;
                                        case 2:
                                        Attack=entity[index].attack[1];
                                    }
                                }
                                else if(timer==60) {
                                    if (Pl==0) {
                                        hb.Players[0].Health-=entity[index].damage;
                                        if (hb.Players[0].defendMode==true) {
                                            hb.Players[0].Health+=hb.Players[0].defenseValue;
                                            hb.Players[0].defendMode=false;
                                        }
                                    }
                                    else if(Pl==1) {
                                        hb.Players[1].Health-=entity[index].damage;
                                        if (hb.Players[1].defendMode==true) {
                                            hb.Players[1].Health+=hb.Players[1].defenseValue;
                                            hb.Players[1].defendMode=false;
                                        }
                                    }
                                    timeUp=false;
                                    f2=false;
                                    timer=0;
                                    attacking=false;
                                }
                            }
                        
                    }
                }
                if (entity[index].active==true) {
                    g2.drawImage(image, entity[index].x,entity[index].y,hb.fightWidth,hb.fightHeight, null);
                    g2.drawImage(Attack, attackX, attackY, hb.fightWidth, hb.fightHeight, null);
                }
                
            }
        }
    }
    
  

