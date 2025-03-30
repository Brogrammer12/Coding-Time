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
        if (hb.player.borderX==true || hb.player.borderY==true) {
            worldX=entity.worldX+(entity.screenX-hb.tileguy.ogScreenX);
            worldY=entity.worldY+(entity.screenY-hb.tileguy.ogScreenY);
        if (worldX<0) {
            worldX=-worldX;
        }
        if (worldY<0) {
            worldY=-worldY;
        }
        }
        else {
            worldX=entity.worldX;
            worldY=entity.worldY;
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
            if (hb.player.borderX==true || hb.player.borderY==true) {
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
    public int checkObject(Entity entity, boolean player) {
        int index=999;
        for (int i=0; i<hb.obj.length; i++) {
            if (hb.obj[i]!=null) {
                if (hb.obj[i].locationX==hb.player.XLevel && hb.obj[i].locationY==hb.player.YLevel) {
                entity.colBox.x+=entity.worldX;
                entity.colBox.y+=entity.worldY;
                hb.obj[i].solidArea.x+=hb.obj[i].worldX;
                hb.obj[i].solidArea.y+=hb.obj[i].worldY;
                switch (entity.direction) {
                    case "up":
                    entity.colBox.y-=entity.moveSpeed;
                    if (entity.colBox.intersects(hb.obj[i].solidArea)) {
                        if (hb.obj[i].collision==true) {
                            entity.collisionOn=true;
                        }
                        if (player==true) {
                            index=i;
                        }
                    }
                    break;
                    case "down":
                    entity.colBox.y+=entity.moveSpeed;
                    if (entity.colBox.intersects(hb.obj[i].solidArea)) {
                        if (hb.obj[i].collision==true) {
                            entity.collisionOn=true;
                        }
                        if (player==true) {
                            index=i;
                        }
                    }
                    break;
                    case "left":
                    entity.colBox.x-=entity.moveSpeed;
                    if (entity.colBox.intersects(hb.obj[i].solidArea)) {
                        if (hb.obj[i].collision==true) {
                            entity.collisionOn=true;
                        }
                        if (player==true) {
                            index=i;
                        }
                    }
                    break;
                    case "right":
                    entity.colBox.x+=entity.moveSpeed;
                    if (entity.colBox.intersects(hb.obj[i].solidArea)) {
                        if (hb.obj[i].collision==true) {
                            entity.collisionOn=true;
                        }
                        if (player==true) {
                            index=i;
                        }
                    }
                    break;
                }
                entity.colBox.x=entity.solidAreaDefaultX;
                entity.colBox.y=entity.solidAreaDefaultY;
                hb.obj[i].solidArea.x=hb.obj[i].solidAreaDefaultX;
                hb.obj[i].solidArea.y=hb.obj[i].solidAreaDefaultY;
                }
            }
        }
        return index;
    }
    public int checkEntity(Entity entity, Entity[] target) {
        int index=999;
        for (int i=0; i<target.length; i++) {
            if (target[i]!=null) {
              //  if (entity.locationx==hb.player.XLevel && entity.locationy==hb.player.YLevel) {
                entity.colBox.x+=entity.worldX;
                entity.colBox.y+=entity.worldY;
                target[i].solidArea.x+=target[i].worldX;
                target[i].solidArea.y+=target[i].worldY;
                switch (entity.direction) {
                    case "up":
                    entity.colBox.y-=entity.moveSpeed;
                    if (entity.colBox.intersects(target[i].solidArea)) {
                            entity.collisionOn=true;
                            index=i;
                        
                       
                    }
                    break;
                    case "down":
                    entity.colBox.y+=entity.moveSpeed;
                    if (entity.colBox.intersects(target[i].solidArea)) {
                        index=i;
                            entity.collisionOn=true;
                        
                      
                    }
                    break;
                    case "left":
                    entity.colBox.x-=entity.moveSpeed;
                    if (entity.colBox.intersects(target[i].solidArea)) {
                            index=i;
                            entity.collisionOn=true;
                        
                      
                    }
                    break;
                    case "right":
                    entity.colBox.x+=entity.moveSpeed;
                    if (entity.colBox.intersects(target[i].solidArea)) {
                        index=i;
                            entity.collisionOn=true;
                     
                    }
                    break;
                }
                entity.colBox.x=entity.solidAreaDefaultX;
                entity.colBox.y=entity.solidAreaDefaultY;
                target[i].solidArea.x=target[i].solidAreaDefaultX;
                target[i].solidArea.y=target[i].solidAreaDefaultY;
                //}
            }
        }
        return index;
    }
}
