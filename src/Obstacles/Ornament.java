package Obstacles;

import Animators.SpriteAnimator;
import Config.Assets;
import Config.GameConfig;
import JumpNRun.SpeedListener;
import de.ur.mi.oop.graphics.Image;

public class Ornament implements GameConfig, Assets {

    private float xPos;
    private float yPos;
    private int framecounter;
    private SpeedListener speedListener;
    private SpriteAnimator ornamentAnimator;
    private Image currentSprite;

    public Ornament(SpeedListener speedListener, float yPos){
        framecounter = 0;
        this.speedListener = speedListener;
        this.yPos = yPos;
        xPos = FINALSCREEN_WIDTH + 10;
        ornamentAnimator = new SpriteAnimator(ORNAMENT_SPRITES, this.xPos, this.yPos);
        currentSprite = ornamentAnimator.getNextImage();
    }

    public float getxPos(){
        return currentSprite.getXPos();
    }

    public float getyPos(){
        return currentSprite.getYPos();
    }

    public void update(){
        currentSprite = ornamentAnimator.getNextImage();
        currentSprite.setHeight(PRESENT_WIDTH);
        currentSprite.setWidth(PRESENT_WIDTH);
        xPos = xPos - speedListener.getcurrentSpeed() - ADD_ORNAMENT_SPEED;
        currentSprite.setXPos(xPos);
    }

    public void draw(){
        currentSprite.draw();
    }

    public int getType(){
        return 5;
    }

}
