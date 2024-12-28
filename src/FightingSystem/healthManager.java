package FightingSystem;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import Entities.Player;
import Main.The_Hub;

public class healthManager {
    public BufferedImage[] helath;
    The_Hub hb;
    Player player;
    public healthManager(The_Hub hb, Player player) {
    this.hb=hb;
    helath=new BufferedImage[29];
    this.player=player;
    healthLoader();
    }
    public void healthLoader() {
        try{
            helath[0]=ImageIO.read(getClass().getResourceAsStream("/Resources/Buttons/HealthBar.png"));
            helath[1]=ImageIO.read(getClass().getResourceAsStream("/Resources/Buttons/HealthBartwo.png"));
            helath[2]=ImageIO.read(getClass().getResourceAsStream("/Resources/Buttons/HealthBarthree.png"));
            helath[3]=ImageIO.read(getClass().getResourceAsStream("/Resources/Buttons/HealthBarfour.png"));
            helath[4]=ImageIO.read(getClass().getResourceAsStream("/Resources/Buttons/HealthBarfive.png"));
            helath[5]=ImageIO.read(getClass().getResourceAsStream("/Resources/Buttons/HealthBarsix.png"));
            helath[6]=ImageIO.read(getClass().getResourceAsStream("/Resources/Buttons/HealthBarseven.png"));
            helath[7]=ImageIO.read(getClass().getResourceAsStream("/Resources/Buttons/HealthBareight.png"));
            helath[8]=ImageIO.read(getClass().getResourceAsStream("/Resources/Buttons/HealthBarnine.png"));
            helath[9]=ImageIO.read(getClass().getResourceAsStream("/Resources/Buttons/HealthBarten.png"));
            helath[10]=ImageIO.read(getClass().getResourceAsStream("/Resources/Buttons/HealthBareleven.png"));
            helath[11]=ImageIO.read(getClass().getResourceAsStream("/Resources/Buttons/HealthBartwelve.png"));
            helath[12]=ImageIO.read(getClass().getResourceAsStream("/Resources/Buttons/HealthBarthirteen.png"));
            helath[13]=ImageIO.read(getClass().getResourceAsStream("/Resources/Buttons/HealthBarfourteen.png"));
            helath[14]=ImageIO.read(getClass().getResourceAsStream("/Resources/Buttons/HealthBarfifteen.png"));
            helath[15]=ImageIO.read(getClass().getResourceAsStream("/Resources/Buttons/HealthBarsixteen.png"));
            helath[16]=ImageIO.read(getClass().getResourceAsStream("/Resources/Buttons/HealthBarseventeen.png"));
            helath[17]=ImageIO.read(getClass().getResourceAsStream("/Resources/Buttons/HealthBareighteen.png"));
            helath[18]=ImageIO.read(getClass().getResourceAsStream("/Resources/Buttons/HealthBarnineteen.png"));
            helath[19]=ImageIO.read(getClass().getResourceAsStream("/Resources/Buttons/HealthBartwenty.png"));
            helath[20]=ImageIO.read(getClass().getResourceAsStream("/Resources/Buttons/HealthBartwentyone.png"));
            helath[21]=ImageIO.read(getClass().getResourceAsStream("/Resources/Buttons/HealthBartwentytwo.png"));
            helath[22]=ImageIO.read(getClass().getResourceAsStream("/Resources/Buttons/HealthBartwentythree.png"));
            helath[23]=ImageIO.read(getClass().getResourceAsStream("/Resources/Buttons/HealthBartwentyfour.png"));
            helath[24]=ImageIO.read(getClass().getResourceAsStream("/Resources/Buttons/HealthBartwentysix.png"));
            helath[25]=ImageIO.read(getClass().getResourceAsStream("/Resources/Buttons/HealthBartwentyseven.png"));
            helath[26]=ImageIO.read(getClass().getResourceAsStream("/Resources/Buttons/HealthBartwentyeight.png"));
            helath[27]=ImageIO.read(getClass().getResourceAsStream("/Resources/Buttons/HealthBartwentynine.png"));
            helath[28]=ImageIO.read(getClass().getResourceAsStream("/Resources/Buttons/HealthBarthirty.png"));
        }
        catch(IOException e) {

        }
    }
}
