package Obstacles;

import Config.Assets;
import Config.GameConfig;
import de.ur.mi.oop.graphics.Image;

public class ShootPresent implements GameConfig, Assets {

    float xPos;
    float yPos;
    Image spriteImage;

    public ShootPresent(float xPos, float yPos){
        this.xPos = xPos;
        this.yPos = yPos;
        spriteImage = new Image(this.xPos, this.yPos, GOOD_PRESENT);
        spriteImage.setHeight(PRESENT_HEIGHT);
        spriteImage.setWidth(PRESENT_WIDTH);
    }

    public void update(){
        movePresent();
        spriteImage.setPosition(xPos, yPos);
    }

    public void draw(){
        spriteImage.draw();
    }

    public boolean leftScreen(){
        if(yPos <= -PRESENT_HEIGHT){
            return true;
        }
        return false;
    }

    private void movePresent(){
        yPos = yPos - SHOOTSPEED;
    }

    public float getyPos(){
        return yPos;
    }

    public float getxPos(){
        return xPos + PRESENT_WIDTH/2;
    }

}
