package Entities;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Entity {
    public int x, y;
    public int moveSpeed;
    BufferedImage up1, up2, left1, left2, down1, down2, right1, right2;
    BufferedImage bob1, bob2;
    BufferedImage[] attack;
    BufferedImage defenseSprite;
    public String direction;
    int SpriteCounter=0;
    int SpriteNum;
    public boolean fightMode;
    int Width;
    int Height;
    public int buttonX;
    public int Health;
    public int attackSpriteNum;
    public int attackSpriteCounter;
    public int timer;
    public boolean timeUp;
    public boolean active;
    public boolean moveToFight;
    public boolean attacking;
    public int maxHealth;
    public int startX;
    public int damage;
    public boolean defendMode;
    public int defenseValue;
    public boolean plSwitch;
    public boolean ded;
    public BufferedImage Dead1;
    public BufferedImage Dead2;
    public Rectangle colBox;
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionOn=false;
    public int worldX;
    public int worldY;
    public int screenX;
    public int screenY;
}
