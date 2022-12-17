package Bossfight;

import Animators.SpriteAnimator;
import Config.Assets;
import Config.GameConfig;
import de.ur.mi.oop.graphics.Image;

public class HearthitHandler implements GameConfig, Assets {

    private int[] framecounter;
    private SpriteAnimator[] heartAnimator;
    private Image[] currentSprite;
    private float[] xPos;
    private float[] yPos;

    public HearthitHandler(){
        //alle Arrays werden initialisiert. Man braucht nie mehr Felder als man Treffer braucht um Krampus zu töten
        framecounter = new int[HITS_TO_KILL];
        heartAnimator = new SpriteAnimator[HITS_TO_KILL];
        currentSprite = new Image[HITS_TO_KILL];
        xPos = new float[HITS_TO_KILL];
        yPos = new float[HITS_TO_KILL];

        for(int i=0; i<HITS_TO_KILL; i++){
            framecounter[i] = 0;
            xPos[i] = 0;
            yPos[i] = 0;
            currentSprite[i] = null;
            heartAnimator[i] = new SpriteAnimator(LOVE_SPRITES, 0,0);
        }
    }

    //falls es eine Stelle im Array gibt in der ein Bild gespeichert ist, wird hier das neue Bild aus dem entsprechenden Animator geladen
    public void update(){
        for(int i=0; i<HITS_TO_KILL; i++){
            if(currentSprite[i] != null){
                currentSprite[i] = heartAnimator[i].getNextImage();
                framecounter[i]++;
                //falls die maximale Laufzeit erreicht ist, wird das Bild gelöscht
                if(framecounter[i] >= SPARKLE_DURATION){
                    framecounter[i] = 0;
                    currentSprite[i] = null;
                }
            }
        }
    }

    //alle existierenden Bilder werden gezeichnet
    public void draw(){
        for(int i=0; i<HITS_TO_KILL; i++){
            if(currentSprite[i] != null){
                currentSprite[i].setPosition(xPos[i], yPos[i]);
                currentSprite[i].draw();
            }
        }
    }

    //erzeugt eine neue Animation an der übergebenen Stelle
    public void onHit(float x, float y){
        int i = 0;
        while(currentSprite[i] != null){
            i++;
        }
        if(i<HITS_TO_KILL){
            xPos[i] = x;
            yPos[i] = y;
            currentSprite[i] = heartAnimator[i].getNextImage();
            currentSprite[i].setPosition(xPos[i], yPos[i]);
        }
    }

}
