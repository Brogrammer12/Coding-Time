package Tile;

import java.awt.Graphics2D;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import java.io.InputStreamReader;
import java.io.BufferedReader;

import Main.The_Hub;

public class TileManager extends Tiles{
    The_Hub hb;
    Tiles[] tile;
    int mapTileNum[][];
    public boolean check1=false;
    public TileManager(The_Hub hb) {
        this.hb=hb;
        tile=new Tiles[10];
        mapTileNum=new int[hb.maxWorldHoriz][hb.maxWorldVert];
        tileLoader();
        loadMap();
        
    }
    
        
    public void tileLoader() {
        try {
            tile[0]=new Tiles();
            tile[0].image=ImageIO.read(getClass().getResourceAsStream("/Resources/Tiles/Grass.png"));
            tile[1]=new Tiles();
            tile[1].image=ImageIO.read(getClass().getResourceAsStream("/Resources/Tiles/Water.png"));
            tile[2]=new Tiles();
            tile[2].image=ImageIO.read(getClass().getResourceAsStream("/Resources/Tiles/Tree_Trunk.png"));
            tile[3]=new Tiles();
            tile[3].image=ImageIO.read(getClass().getResourceAsStream("/Resources/Tiles/Tree_Top.png"));
            tile[4]=new Tiles();
            tile[4].image=ImageIO.read(getClass().getResourceAsStream("/Resources/Tiles/Sand.png"));
            tile[5]=new Tiles();
            tile[5].image=ImageIO.read(getClass().getResourceAsStream("/Resources/Tiles/Dirt.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void loadMap() {
        try {
            InputStream is=getClass().getResourceAsStream("/Resources/tileMaps/worldMap.txt");
        BufferedReader br=new BufferedReader(new InputStreamReader(is));
            int col=0;
            int row=0;
            while(col<hb.maxWorldHoriz && row<hb.maxWorldVert) {
                String line=br.readLine();
                while(col<hb.maxWorldHoriz) {
                    String numbers[]=line.split(" ");
                    int num=Integer.parseInt(numbers[col]);
                    mapTileNum[col][row]=num;
                    col++;
                    
                    }
                    if(col==hb.maxWorldHoriz) {
                        col=0;
                        row++;
                }
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void draw(Graphics2D g2) {
        if (hb.Players[0].fightMode==false) {
            int col=0;
            int row=0;
            if (hb.Players[0].worldY < hb.Players[0].screenY) {
                hb.Players[0].worldY = hb.Players[0].screenY;
                check1 = false;
            }
            
            else if (hb.Players[0].worldY > (hb.maxWorldVert * hb.resTileSize) - (hb.resTileSize * (hb.maxScreenVert - hb.Players[0].screenY / hb.resTileSize))) {
                hb.Players[0].worldY = (hb.maxWorldVert * hb.resTileSize) - (hb.resTileSize * (hb.maxScreenVert - hb.Players[0].screenY / hb.resTileSize));
                check1 = false;
            }            
            else {
                check1=true;
            }
            
            if (hb.Players[0].worldX < hb.Players[0].screenX) {
                hb.Players[0].worldX = hb.Players[0].screenX;
                check1 = false;
            }
            
            else if (hb.Players[0].worldX > (hb.maxWorldHoriz * hb.resTileSize) - (hb.resTileSize * (hb.maxScreenHoriz - hb.Players[0].screenX / hb.resTileSize))) {
                hb.Players[0].worldX = (hb.maxWorldHoriz * hb.resTileSize) - (hb.resTileSize * (hb.maxScreenHoriz - hb.Players[0].screenX / hb.resTileSize));
                check1 = false;
            }
            
            else {
                if (check1==true) {
                    while(col<hb.maxWorldHoriz && row<hb.maxWorldVert) {
                        int tileNum=mapTileNum[col][row];
                        int worldX=col*hb.resTileSize;
                        int worldY=row*hb.resTileSize;
                        int screenX=worldX-hb.Players[0].worldX+hb.Players[0].screenX;
                        int screenY=worldY-hb.Players[0].worldY+hb.Players[0].screenY;
                        if (worldX+hb.resTileSize>hb.Players[0].worldX-hb.Players[0].screenX &&
                            worldX-hb.resTileSize<hb.Players[0].worldX+hb.Players[0].screenX && 
                            worldY+hb.resTileSize>hb.Players[0].worldY-hb.Players[0].screenY && 
                            worldY-hb.resTileSize<hb.Players[0].worldY+hb.Players[0].screenY) {
                                g2.drawImage(tile[tileNum].image, screenX, screenY, hb.resTileSize, hb.resTileSize, null);
                            }
                        col++;
                        if(col==hb.maxWorldHoriz) {
                            col=0;
                            row++;
                        }
                    }
                }
            }
        }
        else if(hb.Players[0].fightMode==true) {
            int col=0;
        int row=0;
        int x=0;
        int y=0;
        while(col<hb.maxScreenHoriz && row<hb.maxScreenVert) {
            int tileNum=mapTileNum[col][row];
            g2.drawImage(tile[tileNum].image, x, y, hb.resTileSize, hb.resTileSize, null);
            col++;
            x+=hb.resTileSize;
            if(col==hb.maxScreenHoriz) {
                col=0;
                x=0;
                row++;
                y+=hb.resTileSize;
            }
        }
        }
        
    }
}
