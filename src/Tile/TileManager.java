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
    public int WorldX;
    public int WorldY;
    public int tileCoordX;
    public int tileCoordY;
    public boolean loadDone=false;
    public boolean firstLoad=false;
    public String realFile="/Resources/tileMaps/startingarea.txt";
    public int ogScreenX;
    public int ogScreenY;
    public int ogWorldX;
    public int ogWorldY;
    public boolean up=false;
    public boolean left=false;
    public boolean right=false;
    public boolean down=false;
    public double leftBorder=360;
    public double rightBorder=430;
    public double upBorder=320;
    public double downBorder=320;
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
            tile[6]=new Tiles();
            tile[6].image=ImageIO.read(getClass().getResourceAsStream("/Resources/Tiles/Tall Grass.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void loadMap() {
        try {
            InputStream is=getClass().getResourceAsStream(realFile);
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
    public void newMap(String fileName) {
        try {
            int numCol = 0;
            int numRow = 0;
    
            // Read the map file to determine the number of columns and rows
            InputStream is = getClass().getResourceAsStream(fileName);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line;
            while ((line = br.readLine()) != null) {
                String numbers[] = line.split(" ");
                numRow++;
                numCol = numbers.length;
            }
            br.close();
    
            // Load the map data into a new array
            is = getClass().getResourceAsStream(fileName);
            br = new BufferedReader(new InputStreamReader(is));
            int[][] newMapTileNum = new int[numCol][numRow];
            int row = 0;
            while ((line = br.readLine()) != null) {
                String numbers[] = line.split(" ");
                for (int col = 0; col < numCol; col++) {
                    newMapTileNum[col][row] = Integer.parseInt(numbers[col]);
                }
                row++;
            }
            br.close();
    
            if (numCol > 0 && numRow > 0) {
                realFile = fileName;
                hb.maxWorldVert = numRow;
                hb.maxWorldHoriz = numCol;
                hb.maxWorldHeight = hb.maxWorldVert * hb.resTileSize;
                hb.maxWorldWidth = hb.maxWorldHoriz * hb.resTileSize;
    
                // Update the mapTileNum array dimensions and copy data
                mapTileNum = new int[numCol][numRow];
                for (int i = 0; i < numCol; i++) {
                    for (int j = 0; j < numRow; j++) {
                        mapTileNum[i][j] = newMapTileNum[i][j];
                    }
                }
    
                // Reset player's coordinates
                hb.Players[0].screenX = ogScreenX;
                hb.Players[0].screenY = ogScreenY;
                firstLoad = false;
                hb.player.mapBorder = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public synchronized void draw(Graphics2D g2) {
        if (loadDone==false) {
            WorldX=0*hb.resTileSize;
    WorldY=0*hb.resTileSize;
    tileCoordX=WorldX-hb.Players[0].worldX+hb.Players[0].screenX;
    tileCoordY=WorldY-hb.Players[0].worldY+hb.Players[0].screenY;
    loadDone=true;
        }
        if (hb.Players[0].fightMode==false) {
            int col=0;
            int row=0;
            if (hb.Players[0].worldY<=320) {
                
                if (hb.Players[0].screenY<=hb.screenHeight/2) {
                    hb.player.mapBorder=true;
                }
                else {
                    hb.Players[0].screenX=ogScreenX;
                    hb.Players[0].screenY=ogScreenY;
                    firstLoad=false;
                    hb.player.mapBorder=false;
                }
            }
            
            else if (hb.Players[0].worldY>=hb.maxWorldHeight-320) {
                if (hb.Players[0].screenY>=hb.screenHeight/2-hb.resTileSize) {
                    hb.player.mapBorder=true;
                }
                else {
                    hb.Players[0].screenX=ogScreenX;
                    hb.Players[0].screenY=ogScreenY;
                    firstLoad=false;
                    hb.player.mapBorder=false;
                }
            }
            
            if (hb.Players[0].worldX<=500-2*hb.resTileSize) {
                if (hb.Players[0].screenX<=hb.screenWidth/2) {
                    hb.player.mapBorder=true;
                }
                else {
                    hb.Players[0].screenX=ogScreenX;
                    hb.Players[0].screenY=ogScreenY;
                    firstLoad=false;
                    hb.player.mapBorder=false;
                }
            }
            
            else if (hb.Players[0].worldX>=hb.maxWorldWidth-500+hb.resTileSize/2) {
                if (hb.Players[0].screenX>=hb.screenWidth/2-hb.resTileSize) {
                    hb.player.mapBorder=true;
                }
                else {
                    hb.Players[0].screenX=ogScreenX;
                    hb.Players[0].screenY=ogScreenY;
                    firstLoad=false;
                    hb.player.mapBorder=false;
                }
            }
                    while(col<hb.maxWorldHoriz && row<hb.maxWorldVert) {
                        int tileNum=mapTileNum[col][row];
                        int worldX=col*hb.resTileSize;
                        int worldY=row*hb.resTileSize;
                        int screenX=worldX-hb.Players[0].worldX+hb.Players[0].screenX;
                        int screenY=worldY-hb.Players[0].worldY+hb.Players[0].screenY;
                        if (hb.player.mapBorder==true && firstLoad==false) {
                            ogWorldX=hb.Players[0].worldX;
                            ogWorldY=hb.Players[0].worldY;
                            ogScreenX=hb.Players[0].screenX;
                            ogScreenY=hb.Players[0].screenY;
                            firstLoad=true;
                        }
                        if (col==0) {
                            if (row==0) {
                                tileCoordX=screenX;
                                tileCoordY=screenY;
                            }
                        }
                            if (hb.player.mapBorder==false) {
                                g2.drawImage(tile[tileNum].image, screenX, screenY, hb.resTileSize, hb.resTileSize, null);
                            }
                            else if(hb.player.mapBorder==true) {
                                int finalScreenX=worldX-hb.Players[0].worldX+ogScreenX;
                            int finalScreenY=worldY-hb.Players[0].worldY+ogScreenY;
                                g2.drawImage(tile[tileNum].image, finalScreenX, finalScreenY, hb.resTileSize, hb.resTileSize, null);
                            }
                        col++;
                        if(col==hb.maxWorldHoriz) {
                            col=0;
                            row++;
                        }
                    }
            
        }

        else if(hb.Players[0].fightMode==true) {
            int col=0;
        int row=0;
        int x=tileCoordX;
        int y=tileCoordY;
        while(col<hb.maxWorldHoriz && row<hb.maxWorldVert) {
            int tileNum=mapTileNum[col][row];
            g2.drawImage(tile[tileNum].image, x, y, hb.resTileSize, hb.resTileSize, null);
            col++;
            x+=hb.resTileSize;
            if(col==hb.maxWorldHoriz) {
                col=0;
                x=tileCoordX;
                row++;
                y+=hb.resTileSize;
            }
        }
        }
        
    }
}
