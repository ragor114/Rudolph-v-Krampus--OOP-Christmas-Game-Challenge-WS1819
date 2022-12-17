package JumpNRun;

import Config.Assets;
import Config.GameConfig;
import de.ur.mi.oop.graphics.Image;

public class Background implements GameConfig, Assets {

    private Image ground;
    private Image movingBackground;
    private Image movingBackground2;
    private Image farBack;
    private SpeedListener listener;

    public Background(SpeedListener listener){
        this.listener = listener;
        ground = new Image(0,0,JNR_Ground);
        movingBackground = new Image(0,0,JNR_movingBack);
        setUpSecondMovingBackground();
        farBack = new Image(0,0,JNR_farBack);
    }

    private void setUpSecondMovingBackground(){
        movingBackground2 = new Image(FINALSCREEN_WIDTH, 0, JNR_movingBack);
    }

    public void draw(){
        farBack.draw();
        movingBackground.draw();
        movingBackground2.draw();
        ground.draw();
    }

    public void update(){
        movingBackground.move(-listener.getcurrentSpeed(), 0);
        if(getImageEndX(movingBackground)<=FINALSCREEN_WIDTH){
            movingBackground2.move(-listener.getcurrentSpeed(), 0);
            if(getImageEndX(movingBackground)<=0){
                movingBackground = movingBackground2;
                setUpSecondMovingBackground();
            }
        }
    }

    private float getImageEndX(Image toCheck){
        return toCheck.getXPos() + 2*FINALSCREEN_WIDTH;
    }

}
