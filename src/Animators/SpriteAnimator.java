package Animators;

import Config.GameConfig;
import de.ur.mi.oop.graphics.Image;

public class SpriteAnimator implements GameConfig {

    private String[] sprites; //Dateipfade zu den Bildern
    private Image[] images; //korrespondierende Bilder
    private int currentImage; //die ID des aktuellen Bildes
    private float xPos; //die X-Position an der alle Bilder erzeugt werden
    private float yPos; //die X-Position an der alle Bilder erzeugt werden
    private int framecounter; //der aktuelle Frame
    private int animationspeed; //die Geschwindigkeit mit der die Bilder gewechselt werden

    public SpriteAnimator(String[] sprites, float xPos, float yPos){
        //die Übergebenen Werte werden in Instanzvariablen gespeichert
        this.sprites = sprites;
        this.xPos = xPos;
        this.yPos = yPos;
        //das Bilder Array wird bereit gemacht
        images = new Image[sprites.length];
        framecounter = 0;
        setupImages();
        //das aktuelle Bild ist natürlich das an Position 0
        currentImage = 0;
        //standardmäßig ist die Geschwindigkeit der Animation die in der GameConfig
        animationspeed = ANIMATION_SPEED;
    }

    //jedes Bild wird initialisiert
    private void setupImages(){
        for(int i = 0; i<sprites.length; i++){
            images[i]  = new Image(xPos, yPos, sprites[i]);
        }
    }

    //falls man eine andere Animationsgeschwindigkeit will, kann man diese mit dieser Methode ändern
    public void setAnimationspeed(int animationspeed){
        this.animationspeed = animationspeed;
    }

    //erhöht den framecounter und gibt das aktuell benötigte Bild zurück
    public Image getNextImage(){
        framecounter++;
        if(framecounter%animationspeed == 0){
            currentImage++;
            //falls man die Länge des Arrays erreicht hat nimmt man wieder das erste Bild an Position 0
            if(currentImage==images.length){
                currentImage = 0;
            }
        }
        return images[currentImage];
    }

}
