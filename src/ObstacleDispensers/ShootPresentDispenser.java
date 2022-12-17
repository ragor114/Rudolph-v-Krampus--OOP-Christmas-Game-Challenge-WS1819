package ObstacleDispensers;

import Bossfight.BossHitListener;
import Bossfight.PositionListener;
import Config.GameConfig;
import Obstacles.ShootPresent;

public class ShootPresentDispenser implements GameConfig {

    private ShootPresent[] shotPresents;
    private int numberOfPresents;
    private PositionListener positionListener;
    private BossHitListener bossHitListener;

    public ShootPresentDispenser(PositionListener positionListener, BossHitListener bossHitListener){
        this.bossHitListener = bossHitListener;
        this.positionListener = positionListener;
        shotPresents = new ShootPresent[MAXSHOTS];
        setUpArray();
        numberOfPresents = 0;
    }

    private void setUpArray(){
        for(int i = 0; i<shotPresents.length; i++){
            shotPresents[i] = null;
        }
    }

    public boolean spawnPresent(){
        if(numberOfPresents < MAXSHOTS){
            int i = 0;
            while(shotPresents[i] != null){
                i++;
            }
            shotPresents[i] = new ShootPresent(positionListener.getXPos(),GROUNDHEIGHT - PLAYER_HITBOX_RADIIUS);
            numberOfPresents++;
            return true;
        }
        return false;
    }

    public void update(){
        for(int i = 0; i<shotPresents.length; i++){
            if(shotPresents[i] != null){
                shotPresents[i].update();
                if(shotPresents[i].leftScreen()){
                    shotPresents[i] = null;
                    numberOfPresents--;
                }
            }
        }
        hitHandler();
    }

    public void draw(){
        for(int i = 0; i<shotPresents.length; i++){
            if(shotPresents[i] != null){
                shotPresents[i].draw();
            }
        }
    }

    private void hitHandler(){
        for(int i = 0; i<shotPresents.length; i++){
            if(shotPresents[i] != null){
                if(shotPresents[i].getyPos() < KRAMPUS_YPOS + PLAYER_HITBOX_RADIIUS && shotPresents[i].getyPos() > KRAMPUS_YPOS-1){
                    if(bossHitListener.hitBoss(shotPresents[i].getxPos())){
                        bossHitListener.onHitBoss();
                        shotPresents[i] = null;
                    }
                }
            }
        }
    }

}
