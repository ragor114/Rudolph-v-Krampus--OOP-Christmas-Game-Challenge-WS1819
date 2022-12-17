package Audio;

import Config.Assets;
import de.ur.mi.oop.audio.AudioClip;

public class SoundeffektHandler implements Assets {

    //alle Methoden sind static, damit man sie ohne Objekterzeugung in jeder Klasse verwenden kann


    //Alle Soundeffekte als AudioClips:
    private static final AudioClip ONEUP = new AudioClip(ONEUP_SOUNDEFFEKT);
    private static final AudioClip HIT = new AudioClip(ENEMYHIT_SOUNDEFFEKT);
    private static final AudioClip LOOSE_POINTS = new AudioClip(ALLPOINTS_SOUNDEFFEKT);
    private static final AudioClip SPARKLE = new AudioClip(LOVE_SOUNDEFFEKT);


    //jede dieser Methoden spielt den entsprechenden Soundeffekt genau einmal ab:
    public static void oneUpEffect(){
        ONEUP.play();
    }

    public static void playerHitEffect(){
        HIT.play();
    }

    public static void allPointsLostEffect(){
        LOOSE_POINTS.play();
    }

    public static void loveSparkleEffect(){
        SPARKLE.play();
    }

}
