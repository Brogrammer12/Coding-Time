package Main;

import Entities.Entity;

public class CollisionChecker {
    The_Hub hb;
    public CollisionChecker(The_Hub hb) {
        this.hb=hb;
    }
    public void checkTile(Entity entity) {
        int worldX;
        int worldY;
        if (hb.player.mapBorder==true) {
            worldX=hb.player.worldX+(hb.player.screenX-hb.tileguy.ogScreenX);
            worldY=hb.player.worldY+(hb.player.screenY-hb.tileguy.ogScreenY);
        if (worldX<0) {
            worldX=-worldX;
        }
        if (worldY<0) {
            worldY=-worldY;
        }
        }
        else {
            worldX=hb.player.worldX;
            worldY=hb.player.worldY;
        }
        
            int entityLeftWorldX=worldX+entity.colBox.x;
            int entityRightWorldX=worldX+entity.colBox.x+entity.colBox.width;
            int entityTopWorldY=worldY+entity.colBox.y;
            int entityBottomWorldY=worldY+entity.colBox.y+entity.colBox.height;
            int entityLeftCol=entityLeftWorldX/hb.resTileSize;
            int entityRightCol=entityRightWorldX/hb.resTileSize;
            int entityTopRow=entityTopWorldY/hb.resTileSize;
            int entityBottomRow=entityBottomWorldY/hb.resTileSize;
            int tileNum1,tileNum2;
        switch(entity.direction) {
            case "up":
            if (hb.player.mapBorder==true) {
                entityTopRow=(entityTopWorldY-entity.moveSpeed)/hb.resTileSize;
            tileNum1=hb.tileguy.mapTileNum[entityLeftCol] [entityTopRow];
            tileNum2=hb.tileguy.mapTileNum[entityRightCol] [entityTopRow];
            if (hb.tileguy.tile[tileNum1].collision==true || hb.tileguy.tile[tileNum2].collision==true) {
                entity.collisionOn=true;
            }
            }
            else {
                entityTopRow=(entityTopWorldY-entity.moveSpeed)/hb.resTileSize;
            tileNum1=hb.tileguy.mapTileNum[entityLeftCol] [entityTopRow];
            tileNum2=hb.tileguy.mapTileNum[entityRightCol] [entityTopRow];
            if (hb.tileguy.tile[tileNum1].collision==true || hb.tileguy.tile[tileNum2].collision==true) {
                entity.collisionOn=true;
            }
            }
            break;
            case "down":
            entityBottomRow=(entityBottomWorldY+entity.moveSpeed)/hb.resTileSize;
            tileNum1=hb.tileguy.mapTileNum[entityLeftCol] [entityBottomRow];
            tileNum2=hb.tileguy.mapTileNum[entityRightCol] [entityBottomRow];
            if (hb.tileguy.tile[tileNum1].collision==true || hb.tileguy.tile[tileNum2].collision==true) {
                entity.collisionOn=true;
            }
            break;
            case "left":
            entityLeftCol=(entityLeftWorldX-entity.moveSpeed)/hb.resTileSize;
            tileNum1=hb.tileguy.mapTileNum[entityLeftCol] [entityTopRow];
            tileNum2=hb.tileguy.mapTileNum[entityLeftCol] [entityBottomRow];
            if (hb.tileguy.tile[tileNum1].collision==true || hb.tileguy.tile[tileNum2].collision==true) {
                entity.collisionOn=true;
            }
            break;
            case "right":
            entityRightCol=(entityRightWorldX+entity.moveSpeed)/hb.resTileSize;
            tileNum1=hb.tileguy.mapTileNum[entityRightCol] [entityTopRow];
            tileNum2=hb.tileguy.mapTileNum[entityRightCol] [entityBottomRow];
            if (hb.tileguy.tile[tileNum1].collision==true || hb.tileguy.tile[tileNum2].collision==true) {
                entity.collisionOn=true;
            }
            break;
        }
    }
}
