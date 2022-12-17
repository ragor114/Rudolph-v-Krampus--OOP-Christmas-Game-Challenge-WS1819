package Obstacles;

import Characters.TargetListener;
import JumpNRun.SpeedListener;
import de.ur.mi.oop.graphics.Image;

public class SuperPresent extends Present {
    public SuperPresent(SpeedListener listener, TargetListener targetListener, float xPos, float yPos) {
        super(listener, targetListener, xPos, yPos);
        icon = new Image(xPos, yPos, SUPER_PRESENT);
        icon.setWidth(PRESENT_WIDTH);
        icon.setHeight(PRESENT_HEIGHT);
    }

    @Override
    public int getType(){
        return 3;
    }

}
