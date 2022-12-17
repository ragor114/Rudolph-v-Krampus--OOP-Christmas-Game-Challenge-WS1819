package JumpNRun;

import Audio.SoundeffektHandler;
import Characters.Player;
import Characters.TargetListener;
import Config.Assets;
import Config.GameConfig;
import Counter.PointCounter;
import ObstacleDispensers.OrnamentDispenser;
import ObstacleDispensers.PresentDispenser;
import Scenes.Scene;
import Scenes.SceneListener;
import de.ur.mi.oop.events.KeyPressedEvent;


public class JNR extends Scene implements GameConfig, Assets, SpeedListener, PointListener, TargetListener {

    private Background background;
    private SpeedCalculator speedCalculator;
    private Player Rudolph;
    private PointCounter presentCounter;
    private PresentDispenser presentDispenser;
    private OrnamentDispenser ornamentDispenser;

    public JNR(String name, SceneListener listener) {
        super(name, listener);
        background = new Background(this);
        speedCalculator = new SpeedCalculator(this);
        Rudolph = new Player();
        presentCounter = new PointCounter(0);
        presentDispenser = new PresentDispenser(this, this);
        ornamentDispenser = new OrnamentDispenser(this, this, this);
    }

    @Override
    public void update(){
        background.update();
        Rudolph.update();
        presentCounter.update();
        presentDispenser.update();
        ornamentDispenser.update();
    }

    @Override
    public void draw(){
        background.draw();
        presentDispenser.draw();
        Rudolph.draw();
        presentCounter.draw();
        ornamentDispenser.draw();
    }

    @Override
    public float getcurrentSpeed() {
        return (float) speedCalculator.getCurrentSpeed();
    }

    @Override
    public int getPoints() {
        return presentCounter.getPoints();
    }

    @Override
    public void onKeyPressed(KeyPressedEvent event){
        if(event.getKeyCode() == KeyPressedEvent.VK_SPACE){
            Rudolph.jump();
        }
        if(event.getKeyCode() == KeyPressedEvent.VK_UP){
            Rudolph.flyUp();
        }
        if(event.getKeyCode() == KeyPressedEvent.VK_DOWN){
            Rudolph.flyDown();
        }
        if(event.getKeyCode() == KeyPressedEvent.VK_ENTER){
            if(presentCounter.getPoints() >= HITS_TO_KILL){
                getListener().onSceneFinished();
            }
        }
    }

    @Override
    public boolean hitTarget() {
        if(Rudolph.hitTest(presentDispenser.getCurrentX(), presentDispenser.getCurrentY())){
            return true;
        }
        return false;
    }

    @Override
    public void onTargetHit(int type) {
        switch(type){
            case 1:
                presentCounter.addOne();
                SoundeffektHandler.oneUpEffect();
                break;
            case 2:
                SoundeffektHandler.allPointsLostEffect();
                presentCounter.reset();
                break;
            case 3:
                SoundeffektHandler.oneUpEffect();
                Rudolph.fly();
                break;
            case 5:
                SoundeffektHandler.playerHitEffect();
                int toRemove = (int) (1 + ORNAMENT_REMOVE_PERCENTAGE*presentCounter.getPoints());
                presentCounter.removePoints(toRemove);
                break;
        }
    }

    @Override
    public boolean ornamentHitTarget() {
        if(Rudolph.hitTest(ornamentDispenser.getCurrentX(), ornamentDispenser.getCurrentY())){
            return true;
        }
        return false;
    }

    @Override
    public int returnPoints(){
        return getPoints();
    }
}
