package Tile;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class brTestRelic {
    public int numCol=0;
    public int numRow=0;
    public brTestRelic() {

    }
    public void loadMap() {
        try {
            InputStream is=getClass().getResourceAsStream("/Resources/tileMaps/worldMap.txt");
            BufferedReader br=new BufferedReader(new InputStreamReader(is));
            while (br!=null) {
                String line=br.readLine();
                String numbers[]=line.split(" ");
                numCol=numbers.length;
                numRow++;
            }
        }
        catch(Exception e) {

        }

        
    }
}
