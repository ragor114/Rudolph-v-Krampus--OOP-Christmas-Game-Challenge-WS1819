package Bossfight;

import Audio.SoundeffektHandler;
import Characters.BossEnemy;
import Characters.BossEnemyAttackHandler;
import Characters.BossPlayer;
import Characters.TargetListener;
import Config.Assets;
import Config.GameConfig;
import Counter.PointCounter;
import JumpNRun.PointListener;
import ObstacleDispensers.ShootPresentDispenser;
import Scenes.Scene;
import Scenes.SceneListener;
import de.ur.mi.oop.events.KeyPressedEvent;
import de.ur.mi.oop.graphics.Image;

import java.awt.*;

public class Bossfight extends Scene implements GameConfig, Assets, PointListener, PositionListener, TargetListener, BossHitListener {

    private Image background; //der Hintergrund als Objekt der Image-Klasse
    private BossPlayer rudolph; //der Spieler
    private BossEnemy krampus; //der Gegener
    private PointCounter presentCounter; //ein Punkte Counter
    private ShootPresentDispenser shooter; //"schießt" Geschenke auf Krampus
    private BossEnemyAttackHandler attackHandler; //"schießt" Christbaumkugeln auf Rudolph
    private HearthitHandler hearthitHandler; //fügt eine Herzanimation hinzu wenn Krampus getroffen wurde
    private int bossHits; //hier wird gespeichert wie oft Krampus schon getroffen wurde

    //points wird übergeben und entspricht der Anzahl der in JNR gesammelten Geschenke, so viel Schuss hat man
    public Bossfight(String name, SceneListener listener, int points) {
        super(name, listener);
        bossHits = 0;
        background = new Image(0,0, BOSSFIGHT_BACKGROUND);
        rudolph = new BossPlayer(this);
        krampus = new BossEnemy();
        presentCounter = new PointCounter(points);
        shooter = new ShootPresentDispenser(this, this);
        attackHandler = new BossEnemyAttackHandler(this, this);
        hearthitHandler = new HearthitHandler();
    }

    //alle Bestandteile der Szene müssen in jedem Frame geupdatet werden
    @Override
    public void update(){
        rudolph.update();
        krampus.update();
        presentCounter.update();
        shooter.update();
        attackHandler.update();
        hearthitHandler.update();
    }

    @Override
    //alle Bestandteile der Szene müssen gezeichnet werden wenn die Szene gezeichnet wird
    public void draw(){
        background.draw();
        shooter.draw();
        presentCounter.draw();
        rudolph.draw();
        krampus.draw();
        attackHandler.draw();
        hearthitHandler.draw();
    }

    @Override
    public void onKeyPressed(KeyPressedEvent event){
        if(event.getKeyCode() == KeyPressedEvent.VK_LEFT){ //falls man Pfeiltaste nach links drückt bewegt man Rudolph nach links
            rudolph.moveLeft();
        } else if(event.getKeyCode() == KeyPressedEvent.VK_RIGHT){ //falls man Pfeiltaste nach rechts drückt bewegt man Rudolph nach rechts
            rudolph.moveRight();
        } else if(event.getKeyCode() == KeyPressedEvent.VK_SPACE){ //falls man die Leertaste drückt schießt man ein Geschenk
            shoot();
        }
    }

    //falls man mehr als 0 Geschenke hat spawnt man ein neues Geschenk und zieht eins vom Counter ab
    private void shoot(){
        if(presentCounter.getPoints() > 0){
            if(shooter.spawnPresent()){
                presentCounter.removeOne();
            }
        }
    }


    @Override
    //gibt die Anzahl der Punkte zurück
    public int getPoints() {
        return presentCounter.getPoints();
    }

    @Override
    //gibt die aktuelle X-Position von Rudolph zurück
    public float getXPos() {
        return rudolph.getxPos();
    }

    @Override
    //gibt die aktuelle X-Position von Krampus zurück
    public float getEnemyXPos() {
        return krampus.getxPos();
    }


    //TargetListener:
    @Override
    //gibt true zurück falls Rudolph getroffen wurde
    public boolean hitTarget() {
        if(rudolph.hitTest(attackHandler.getCurrentX())){
            return true;
        }
        return false;
    }

    @Override
    //spielt einen Soundeffekt ab und reagiert entsprechend der Art des Treffers
    public void onTargetHit(int type) {
        SoundeffektHandler.playerHitEffect();
        switch (type){
            case 0:
                if((int) (0.2 * presentCounter.getPoints()) > 1){
                    presentCounter.removePoints((int) (0.2 * presentCounter.getPoints()));
                } else{
                    presentCounter.removeOne();
                }
                break;
        }
        //falls Rudolph weniger Geschenke hat als er braucht um Krampus zu besiegen und getroffen wird hat er verloren
        if(presentCounter.getPoints() < HITS_TO_KILL){
            getListener().gameOver();
        }
    }

    @Override
    //hier unnötig
    public boolean ornamentHitTarget() {
        return false;
    }

    @Override
    //gibt zurück wie viele Punkte man aktuell hat
    public int returnPoints(){
        return presentCounter.getPoints();
    }


    //BossHitListener:
    @Override
    //gibt true zurück wenn krampus getroffen wurde
    public boolean hitBoss(float x) {
        if(krampus.hittest(x)){
            return true;
        }
        return false;
    }

    @Override
    //spielt einen Soundeffekt ab, fügt eine Herzanimation hinzu und erhöht die Anzahl der Treffer
    public void onHitBoss() {
        SoundeffektHandler.loveSparkleEffect();
        hearthitHandler.onHit(krampus.getxPos(), KRAMPUS_YPOS);
        bossHits++;
        //falls Krampus so oft getroffen wurde wie in der GameConfig angegeben gewinnt man
        if(bossHits == HITS_TO_KILL){
            getListener().youWon();
        }
    }
}
