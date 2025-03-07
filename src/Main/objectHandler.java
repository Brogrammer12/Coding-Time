package Main;

import Object.Chest;
import Object.Sign;

public class objectHandler {
    The_Hub hb;
    public objectHandler(The_Hub hb) {
        this.hb=hb;
    }
    public void setObject() {
        hb.obj[0]=new Sign(hb, "HELLO");
        hb.obj[0].worldX=hb.maxWorldWidth/2+4*hb.resTileSize;
        hb.obj[0].worldY=hb.maxWorldHeight/2;
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
    }
}
