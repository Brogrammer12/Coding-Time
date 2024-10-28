package Tile;

import java.awt.Graphics2D;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.FileInputStream;

import Main.The_Hub;

public class TileManager extends Tiles{
    The_Hub hb;
    Tiles[] tile;
    int mapTileNum[][];
    public TileManager(The_Hub hb) {
        this.hb=hb;
        tile=new Tiles[10];
        mapTileNum=new int[hb.maxScreenHoriz][hb.maxScreenVert];
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
            InputStream is=getClass().getResourceAsStream("/Resources/tileMaps/firstMap.txt");
        BufferedReader br=new BufferedReader(new InputStreamReader(is));
            int col=0;
            int row=0;
            while(col<hb.maxScreenHoriz && row<hb.maxScreenVert) {
                String line=br.readLine();
                while(col<hb.maxScreenHoriz) {
                    String numbers[]=line.split(" ");
                    int num=Integer.parseInt(numbers[col]);
                    mapTileNum[col][row]=num;
                    col++;
                    
                    }
                    if(col==hb.maxScreenHoriz) {
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
