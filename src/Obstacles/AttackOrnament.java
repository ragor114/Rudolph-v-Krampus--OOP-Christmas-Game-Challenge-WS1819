package Obstacles;

import Animators.SpriteAnimator;
import Config.Assets;
import Config.GameConfig;
import de.ur.mi.oop.graphics.Image;

public class AttackOrnament implements GameConfig, Assets {

    private SpriteAnimator attackAnimator;
    private Image currentSprite;
    private float xPos;
    private float yPos;
    private float moveSpeed;

    public AttackOrnament(float xPos){
        yPos = KRAMPUS_YPOS;
        this.xPos = xPos;
        attackAnimator = new SpriteAnimator(SHOT_SPRITES, this.xPos, yPos);
        currentSprite = attackAnimator.getNextImage();
        moveSpeed = ATTACKSPEED;
    }

    public void update(){
        currentSprite = attackAnimator.getNextImage();
        move();
    }

    private void move(){
        yPos = yPos + moveSpeed;
        currentSprite.setPosition(xPos, yPos);
    }

    public void draw(){
        currentSprite.setWidth(PRESENT_WIDTH/2);
        currentSprite.setHeight(PRESENT_HEIGHT/2);
        currentSprite.draw();
    }

    public boolean leftScreen(){
        if(yPos >= FINALSCREEN_HEIGHT){
            return true;
        }
        return false;
    }

    public float getyPos(){
        return yPos;
    }

    public float getxPos(){
        return xPos + PRESENT_WIDTH/4;
    }

}
