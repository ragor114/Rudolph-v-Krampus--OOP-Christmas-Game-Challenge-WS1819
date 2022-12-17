package Characters;

import Animators.SpriteAnimator;
import Config.Assets;
import Config.GameConfig;
import de.ur.mi.oop.graphics.Image;
import de.ur.mi.oop.graphics.Rectangle;

import java.util.Random;

public class BossEnemy implements GameConfig, Assets {

    private float xPos; //aktuelle X-Position von Krampus
    private float yPos; //Y-Position von Krampus
    private Image currentSprite; //das aktuelle Bild in der Animation von Krampus
    private SpriteAnimator krampusAnimator; //der entsprechene Spriteanimator
    private int framecounter; //aktueller Frame
    private int movespeed; //die aktuelle Bewegungsgeschwindigkeit
    private boolean directionIndicator; //true - Krampus bewegt sich nach rechts / false - Krampus bewegt sich nach links
    private Random rand;

    public BossEnemy(){
        //Krampus startet auf der vorgegeben y-Position und in der Mitte des Bildschirms
        xPos = FINALSCREEN_WIDTH/2 - PLAYER_HITBOX_RADIIUS/2;
        yPos = KRAMPUS_YPOS;
        framecounter = 0;
        //anfangs beweget sich Krampus immer mit der minimal geschwindigkeit
        movespeed = KRAMPUS_MINSPEED;
        rand = new Random();
        directionIndicator = true;
        //das erste Bild wird geladen
        krampusAnimator = new SpriteAnimator(KRAMPUS_SPRITES, xPos, yPos);
        currentSprite = krampusAnimator.getNextImage();
    }

    //das nächste Bild wird geladen und der framecounter hochgezählt, dann wird überprüft ob sich die Geschwindigkeit oder Richtung ändert und das Bild an die entsprechende Position gesetzt
    public void update(){
        currentSprite = krampusAnimator.getNextImage();
        framecounter++;
        newMoveSpeed();
        newDirection();
        move();
        //Bugfix:
        if(xPos < 0 || xPos + PLAYER_HITBOX_RADIIUS > FINALSCREEN_WIDTH){
            framecounter = KRAMPUS_CHANGE_FREQ-1;
            update();
        }
    }

    //zum zeichnen wird das aktuelle Bild gezeichnet
    public void draw(){
        currentSprite.draw();
    }

    //ändert die Bewegungsrichtung von Krampus
    private void directionChange(){
        directionIndicator = !directionIndicator;
    }

    //die entsprechende Methode wird aufgerufen und das aktuelle Bild an die entsprechende Position gesetzt
    private void move(){
        if(directionIndicator == true){
            moveRight();
        } else{
            moveLeft();
        }
        currentSprite.setPosition(xPos, yPos);
    }

    //falls Krampus nicht das Bild verlassen würde wird die x-Position um movespeed erhöht. Ansonsten ändert er seine Bewegungsrichtung
    private void moveRight(){
        if(xPos + PLAYER_HITBOX_RADIIUS + movespeed < FINALSCREEN_WIDTH){
            xPos = xPos + movespeed;
        } else{
            directionChange();
            move();
        }
    }

    //analog zu moveRight
    private void moveLeft(){
        if(xPos - movespeed > 0){
            xPos = xPos - movespeed;
        } else{
            directionChange();
            move();
        }
    }

    //falls ein bestimmter Wert erreicht ist ändert Krampus mit einer Wahrscheinlichkeit von 50% seine Richtung, also scheinbar zufällig
    private void newDirection(){
        if(framecounter % KRAMPUS_CHANGE_FREQ/3 == 0){
            int random = rand.nextInt(100);
            if(random < 50){
                directionChange();
            }
        }
    }

    //falls ein bestimmter Wert erreicht ist erhält Krampus eine neue zufällige Geschwindigkeit
    private void newMoveSpeed(){
        if(framecounter == KRAMPUS_CHANGE_FREQ){
            int newSpeed = rand.nextInt(KRAMPUS_MINSPEED+KRAMPUS_MAXSPEED)-KRAMPUS_MINSPEED;
            movespeed = newSpeed;
            framecounter = 0;
        }
    }

    //gibt die aktuelle x-Position zurück
    public float getxPos(){return xPos;}

    //da die y-Position konstant bleibt muss nur die x-Position überprüft werden. Gibt true zurück falls die übergeben Position in dem hittest Rechteckt an Krampus Position liegt
    public boolean hittest(float xHit){
        Rectangle hitbox = new Rectangle(xPos, yPos, PLAYER_HITBOX_RADIIUS, PLAYER_HITBOX_RADIIUS);
        if(hitbox.hitTest(xHit, yPos)){
            return true;
        }
        return false;
    }
}
