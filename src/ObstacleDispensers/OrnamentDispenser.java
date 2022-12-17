package ObstacleDispensers;

import Characters.TargetListener;
import Config.GameConfig;
import JumpNRun.PointListener;
import JumpNRun.SpeedListener;
import Obstacles.Ornament;

import java.util.Random;

public class OrnamentDispenser implements GameConfig {

    SpeedListener speedListener;
    TargetListener targetListener;
    PointListener pointListener;
    int framecounter;
    Random rand;
    Ornament currentOrnament;

    public OrnamentDispenser(SpeedListener speedListener, TargetListener targetListener, PointListener pointListener){
        this.speedListener = speedListener;
        this.targetListener = targetListener;
        this.pointListener = pointListener;
        framecounter = 0;
        rand = new Random();
        currentOrnament = null;
    }

    public void draw(){
        if(currentOrnament != null){
            currentOrnament.draw();
        }
    }

    public void update(){
        if(currentOrnament == null){
            spawnOrnament();
        }
        if(currentOrnament != null && currentOrnament.getxPos() <= 0){
            currentOrnament = null;
        }
        if(currentOrnament != null){
            currentOrnament.update();
        }
        if(targetListener.ornamentHitTarget() && currentOrnament != null){
            targetListener.onTargetHit(currentOrnament.getType());
            currentOrnament = null;
            spawnOrnament();
        }
    }

    private void spawnOrnament(){
        if(pointListener.getPoints() >= POINTS_BEFORE_ORNAMENTS){
            framecounter++;
            if(framecounter % ORNAMENT_SPAWNFREQ_FRAMES == 0){
                int newHeight = (int) (rand.nextInt((int) (PRESENT_MINHEIGHT-PRESENT_MAXHEIGHT))+PRESENT_MAXHEIGHT);
                currentOrnament = new Ornament(speedListener, newHeight);
            }
        }
    }

    public float getCurrentX(){
        if(currentOrnament == null){
            return -1;
        }
        return currentOrnament.getxPos();
    }

    public float getCurrentY(){
        if(currentOrnament == null){
            return -1;
        }
        return currentOrnament.getyPos();
    }

}
