package Object;

import javax.imageio.ImageIO;

public class Sign extends object{
    public Sign() {
        name="Sign";
        try{
            image=ImageIO.read(getClass().getResourceAsStream("/Resources/Objects/Sign.png"));
           
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        collision=true;
    }
}
