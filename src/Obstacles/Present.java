package Obstacles;

import Characters.TargetListener;
import Config.Assets;
import Config.GameConfig;
import JumpNRun.SpeedListener;
import de.ur.mi.oop.graphics.Image;

public class Present implements GameConfig, Assets, ObstacleLeftListener {

    private SpeedListener listener;
    private TargetListener targetListener;
    public Image icon;
    private float xPos;
    private float yPos;

    public Present (SpeedListener listener, TargetListener targetListener, float xPos, float yPos){
        this.listener = listener;
        this.targetListener = targetListener;
        this.xPos = xPos;
        this.yPos = yPos;
        icon = new Image(xPos, yPos, GOOD_PRESENT);
        icon.setWidth(PRESENT_WIDTH);
        icon.setHeight(PRESENT_HEIGHT);
    }

    public void update(){
        icon.move(-listener.getcurrentSpeed(), 0);
    }

    public void draw(){
        icon.draw();
    }

    @Override
    public boolean obstacleLeftScreen() {
        if(icon.getXPos()<=0-PRESENT_WIDTH){
            return true;
        }
        return false;
    }

    public float getxPos(){
        return icon.getXPos();
    }

    public float getyPos(){
        return icon.getYPos();
    }

    public int getType(){
        return 0;
    }
}
