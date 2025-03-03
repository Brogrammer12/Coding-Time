package Main;

import Object.Chest;
import Object.Sign;

public class objectHandler {
    The_Hub hb;
    public objectHandler(The_Hub hb) {
        this.hb=hb;
    }
    public void setObject() {
        hb.obj[0]=new Sign(hb);
        hb.obj[0].worldX=hb.maxWorldWidth/2+4*hb.resTileSize;
        hb.obj[0].worldY=hb.maxWorldHeight/2;
        hb.obj[0].locationX=1;
        hb.obj[0].locationY=2;
        hb.obj[1]=new Chest(hb);
        hb.obj[1].worldX=hb.maxWorldWidth/2;
        hb.obj[1].worldY=hb.maxWorldHeight/2;
        hb.obj[1].locationX=0;
        hb.obj[1].locationY=3;
    }
}
