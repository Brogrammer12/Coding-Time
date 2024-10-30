package Entities;

import java.awt.image.BufferedImage;

public class Entity {
    int x, y;
    int moveSpeed;
    BufferedImage up1, up2, left1, left2, down1, down2, right1, right2;
    BufferedImage bob1, bob2;
    String direction;
    int SpriteCounter=0;
    int SpriteNum;
    public boolean fightMode;
    int Width;
    int Height;
    public int buttonX;
}
