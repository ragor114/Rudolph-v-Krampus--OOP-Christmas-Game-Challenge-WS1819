package Obstacles;

import Characters.TargetListener;
import JumpNRun.SpeedListener;
import de.ur.mi.oop.graphics.Image;

public class GoodPresent extends Present {

    public GoodPresent(SpeedListener listener, TargetListener targetListener, float xPos, float yPos) {
        super(listener, targetListener, xPos, yPos);
    }

    @Override
    public int getType(){
        return 1;
    }

}
