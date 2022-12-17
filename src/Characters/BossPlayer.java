package Characters;

import Animators.SpriteAnimator;
import Config.Assets;
import Config.GameConfig;
import JumpNRun.PointListener;
import de.ur.mi.oop.graphics.Image;
import de.ur.mi.oop.graphics.Rectangle;


public class BossPlayer implements GameConfig, Assets {

    private float xPos;
    private float yPos;
    private Image currentSprite;
    private SpriteAnimator rightAnimator;
    private SpriteAnimator leftAnimator;
    private PointListener pointListener;

    public BossPlayer(PointListener pointListener){
        xPos = (FINALSCREEN_WIDTH/2) - (PLAYER_HITBOX_RADIIUS/2);
        yPos = GROUNDHEIGHT;
        this.pointListener = pointListener;
        currentSprite = new Image(xPos, yPos, RUDOLPH_STANDING);
        rightAnimator = new SpriteAnimator(RUDOLPH_SPRITES, xPos, yPos);
        rightAnimator.setAnimationspeed(BOSS_ANIMATION_SPEED);
        leftAnimator = new SpriteAnimator(RUDOLPH_SPRITES_LEFT, xPos, yPos);
        leftAnimator.setAnimationspeed(BOSS_ANIMATION_SPEED);
    }

    public void update(){
        updateSpritePos();
    }

    public void draw(){
        currentSprite.draw();
    }

    public void updateSpritePos(){
        currentSprite.setPosition(xPos, yPos);
    }

    public void moveLeft(){
        if(xPos - PLAYER_MOVESPEED >= 0){
            xPos = xPos - PLAYER_MOVESPEED;
            currentSprite = leftAnimator.getNextImage();
        }
    }

    public void moveRight(){
        if(xPos + PLAYER_HITBOX_RADIIUS + PLAYER_MOVESPEED <= FINALSCREEN_WIDTH){
            xPos = xPos + PLAYER_MOVESPEED;
            currentSprite = rightAnimator.getNextImage();
        }
    }

    public float getxPos(){
        return xPos;
    }

    public float getyPos(){
        return yPos;
    }

    public boolean hitTest(float xHit){
        Rectangle hitbox = new Rectangle(xPos, yPos, PLAYER_HITBOX_RADIIUS, PLAYER_HITBOX_RADIIUS);
        if(hitbox.hitTest(xHit, yPos)){
            return true;
        }
        return false;
    }

}
