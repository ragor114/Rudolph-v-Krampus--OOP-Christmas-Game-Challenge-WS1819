package ObstacleDispensers;

import Characters.TargetListener;
import Config.GameConfig;
import JumpNRun.SpeedListener;
import Obstacles.BadPresent;
import Obstacles.GoodPresent;
import Obstacles.Present;
import Obstacles.SuperPresent;

import java.util.Random;

public class PresentDispenser implements GameConfig {

    SpeedListener listener;
    TargetListener targetListener;
    Present currentPresent;
    int frameCounter;
    Random rand;

    public PresentDispenser(SpeedListener listener, TargetListener targetListener){
        frameCounter = 0;
        rand = new Random();
        this.listener = listener;
        this.targetListener = targetListener;
    }

    public void draw(){
        if(currentPresent != null){
            currentPresent.draw();
        }
    }

    public void update(){
        frameCounter++;
        if(currentPresent != null && currentPresent.obstacleLeftScreen()){
            currentPresent = null;
            frameCounter = 0;
        }
        if(frameCounter == PRESENT_SPAWNFREQ_FRAMES){
            spawnPresent();
        }
        if(currentPresent != null){
            currentPresent.update();
        }
        if(targetListener.hitTarget()){
            targetListener.onTargetHit(currentPresent.getType());
            currentPresent = null;
            spawnPresent();
        }
    }

    private void spawnPresent(){
        if (currentPresent != null) {
            return;
        }
        int newRandom = rand.nextInt(100);
        int newHeight = (int) (rand.nextInt((int) (PRESENT_MINHEIGHT-PRESENT_MAXHEIGHT))+PRESENT_MAXHEIGHT);
        if(newRandom<GOODPRESENT_RANGEMAX){
            currentPresent = new GoodPresent(listener, targetListener, FINALSCREEN_WIDTH, newHeight);
        } else if(newRandom<BADPRESENT_RANGEMAX){
            currentPresent = new BadPresent(listener, targetListener, FINALSCREEN_WIDTH, newHeight);
        } else{
            currentPresent = new SuperPresent(listener, targetListener, FINALSCREEN_WIDTH, newHeight);
        }
    }

    public float getCurrentX(){
        if(currentPresent != null){
            return currentPresent.getxPos();
        }
        return -1;
    }

    public float getCurrentY(){
        if(currentPresent != null){
            return currentPresent.getyPos();
        }
        return -1;
    }

}
