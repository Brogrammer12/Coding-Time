package Object;

import javax.imageio.ImageIO;

public class Chest extends object{
    public Chest() {
        name="Chest";
        try{
            image=ImageIO.read(getClass().getResourceAsStream("/Resources/Objects/Chest.png"));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        collision=true;
    }
}
