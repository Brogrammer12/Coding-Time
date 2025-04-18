package Main;

import Object.Chest;
import Object.Entrance_Exit;
import Object.House;
import Object.Sign;

public class objectHandler {
    The_Hub hb;
    public objectHandler(The_Hub hb) {
        this.hb=hb;
    }
    public void setObject() {
        hb.obj[0]=new Sign(hb, "HELLO");
        hb.obj[0].worldX=hb.maxWorldWidth/2+2*hb.resTileSize;
        hb.obj[0].worldY=hb.maxWorldHeight/2-300;
        hb.obj[0].locationX=1;
        hb.obj[0].locationY=2;
        hb.obj[1]=new Chest(hb);
        hb.obj[1].worldX=hb.maxWorldWidth/2;
        hb.obj[1].worldY=hb.maxWorldHeight/2;
        hb.obj[1].locationX=0;
        hb.obj[1].locationY=3;
        hb.obj[2]=new Sign(hb, "NOPE");
        hb.obj[2].worldX=hb.maxWorldWidth/2+4*hb.resTileSize;
        hb.obj[2].worldY=hb.maxWorldHeight/2;
        hb.obj[2].locationX=2;
        hb.obj[2].locationY=2;
        hb.obj[3]=new Sign(hb, "YUP");
        hb.obj[3].worldX=hb.maxWorldWidth/2+2*hb.resTileSize;
        hb.obj[3].worldY=hb.maxWorldHeight/2;
        hb.obj[3].locationX=0;
        hb.obj[3].locationY=2;
        hb.obj[4]=new House(hb);
        hb.obj[4].locationX=1;
        hb.obj[4].locationY=4;
        hb.obj[4].worldX=hb.maxWorldWidth/2+hb.resTileSize;
        hb.obj[4].worldY=hb.maxWorldHeight/2;
        hb.obj[5]=new Entrance_Exit(hb, "one");
        hb.Exits[0]=hb.obj[5];//save exits to another array for later
        hb.obj[5].locationX=1;
        hb.obj[5].locationY=0;
        hb.obj[5].worldX=hb.maxWorldWidth/2;
        hb.obj[5].worldY=600;
        hb.obj[6]=new Entrance_Exit(hb, "one");
        hb.Exits[1]=hb.obj[6];
        hb.obj[6].locationX=1;
        hb.obj[6].locationY=1;
        hb.obj[6].worldX=hb.maxWorldWidth/2;
        hb.obj[6].worldY=hb.maxWorldHeight;
        hb.obj[7]=new Entrance_Exit(hb, "path");
        hb.Exits[2]=hb.obj[7];
        hb.obj[7].worldX=hb.maxWorldWidth/2;
        hb.obj[7].worldY=0;
        hb.obj[7].locationX=1;
        hb.obj[7].locationY=1;
        hb.obj[8]=new Entrance_Exit(hb, "path");
        hb.Exits[3]=hb.obj[8];
        hb.obj[8].worldX=hb.maxWorldWidth/2;
        hb.obj[8].worldY=300;
        hb.obj[8].locationX=1;
        hb.obj[8].locationY=2;
    }
}
