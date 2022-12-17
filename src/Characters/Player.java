package Characters;

import Animators.SpriteAnimator;
import Config.Assets;
import Config.GameConfig;
import de.ur.mi.oop.graphics.Image;
import de.ur.mi.oop.graphics.Rectangle;

public class Player implements Assets, GameConfig {

    private float xPos;
    private float yPos;
    private String[] sprites;
    private SpriteAnimator playerAnimator;
    private Image currentSprite;

    private boolean jumping;
    private boolean falling;
    private boolean flying;
    private int framecounter;

    public Player(){
        framecounter = 0;
        xPos = PLAYERXPOS;
        yPos = GROUNDHEIGHT;
        jumping = false;
        falling = false;
        flying = false;

        sprites = RUDOLPH_SPRITES;
        playerAnimator = new SpriteAnimator(sprites, xPos, yPos);
        currentSprite = playerAnimator.getNextImage();
    }

    public void draw(){
        currentSprite.draw();
    }

    public void update(){
        currentSprite = playerAnimator.getNextImage();
        setSpriteYPos();
        if(jumping && !falling){
            yPos = yPos - JUMPSPEED;
            setSpriteYPos();
            if(currentSprite.getYPos() <= MAXJUMPHEIGHT){
                jumping = false;
                falling = true;
            }
        }
        if(flying == true){
            framecounter++;
            if(framecounter == FLIGHTTIME){
                flying = false;
                falling = true;
                framecounter = 0;
            }
        }
        if(falling){
            yPos = yPos + JUMPSPEED;
            setSpriteYPos();
            if(currentSprite.getYPos() >= GROUNDHEIGHT){
                yPos = GROUNDHEIGHT;
                setSpriteYPos();
                falling = false;
            }
        }
    }

    private void setSpriteYPos(){
        currentSprite.setPosition(currentSprite.getXPos(), yPos);
    }

    public void jump(){
        if(jumping == false){
            jumping = true;
        }
    }

    public void fly() {
        if (flying == false) {
            flying = true;
        }
    }

    public void flyUp(){
        if(flying){
            if(currentSprite.getYPos() - FLIGHTSPEED > 0){
                yPos = yPos - FLIGHTSPEED;
                setSpriteYPos();
            }
        }
    }

    public void flyDown(){
        if(flying){
            if(currentSprite.getYPos() + FLIGHTSPEED < GROUNDHEIGHT){
                yPos = yPos + FLIGHTSPEED;
                setSpriteYPos();
            }
        }
    }

    public boolean hitTest(float x, float y){
        Rectangle hitBox = new Rectangle(xPos, yPos, PLAYER_HITBOX_RADIIUS, PLAYER_HITBOX_RADIIUS);
        if(hitBox.hitTest(x,y)){
            return true;
        }
        return false;
    }

}
