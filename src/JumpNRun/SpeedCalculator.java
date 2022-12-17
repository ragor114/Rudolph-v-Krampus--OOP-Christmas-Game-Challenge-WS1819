package JumpNRun;

import Config.GameConfig;

public class SpeedCalculator implements GameConfig {
    private double currentSpeed;
    private PointListener listener;

    public SpeedCalculator(PointListener listener){
        currentSpeed = STARTGAMESPEED;
        this.listener = listener;
    }

    public double getCurrentSpeed(){
        currentSpeed = STARTGAMESPEED + listener.getPoints() * SPEEDINCREASEPERPOINT;
        return currentSpeed;
    }

    public void update(){

    }

    public void reset(){
        currentSpeed = STARTGAMESPEED;
    }
}
