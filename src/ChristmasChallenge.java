import Audio.MusicHandler;
import Audio.SoundeffektHandler;
import Bossfight.Bossfight;
import Config.GameConfig;
import FinalScene.GameOver;
import FinalScene.YouWon;
import IntroScene.Intro;
import JumpNRun.JNR;
import Scenes.Scene;
import Scenes.SceneListener;
import SecondExplanation.SecondExplanation;
import de.ur.mi.oop.app.GraphicsApp;
import de.ur.mi.oop.colors.Colors;
import de.ur.mi.oop.events.KeyPressedEvent;

public class ChristmasChallenge extends GraphicsApp implements GameConfig, SceneListener {

    private static final int SCREEN_WIDTH = FINALSCREEN_WIDTH;
    private static final int SCREEN_HEIGHT = FINALSCREEN_HEIGHT;
    private Scene currentScene; //hier wird die aktuelle Szene gespeichert
    private int sceneID; //hier wird die ID der aktuellen Szene gespeichert
    private int pointsAfterSceneFinished = 0; //zur Übergabe der Punktzahl an die nächste Szene
    private MusicHandler musicHandler; //über diesen lässt sich die Musik steuern


    @Override
    public void initialize() {
        setCanvasSize(SCREEN_WIDTH, SCREEN_HEIGHT); //die größe des Fensters wird auf die in GameConfig eingstellten Werte gesetzt
        setFrameRate(FPS); //die Framerate wird auf die in GameConfig definierte Framerate gesetzt
        currentScene = new Intro("Intro", this); //die erste Szene ist das Intro und hat dieses Objekt als Scenelistener
        sceneID = 0; //die ID des Intros ist 0
        musicHandler = new MusicHandler(); //Der Musichandler wird initialisiert...
        musicHandler.play(); //und spielt den ersten Song ab.
    }

    @Override
    public void draw() {
        //immer wenn das Spiel gezeichnet wird wird die aktuelle Szene geupdatet und dann gezeichnet
        drawBackground(Colors.WHITE);
        currentScene.update();
        currentScene.draw();
    }

    @Override
    //wird eine diese Methode aufgerufen wird in die entsprechende nächste Szene übergegangen, ein Soundeffekt aufgerufen, die Musik gewechselt und ein die SzenenID entsprechend angepasst.
    public void onSceneFinished() {
        if(sceneID == 0){
            currentScene = new JNR("JumpNRun", this);
            sceneID = 1;
        } else if(sceneID == 1){
            //pointsAfterSceneFinished = 100;
            pointsAfterSceneFinished = currentScene.returnPoints();
            currentScene = new SecondExplanation("second", this);
            sceneID = 2;
        } else if(sceneID == 2){
            currentScene = new Bossfight("Bossfight", this, pointsAfterSceneFinished);
            musicHandler.nextTrack();
        } else if(sceneID == 3){
            currentScene = new Intro("Intro", this);
            sceneID = 0;
        }
        musicHandler.nextTrack();
        SoundeffektHandler.loveSparkleEffect();
    }

    @Override
    //wird diese Methode aufgerufen wird ein Soundeffekt abgespielt, die Musikgewechselt und ein Gemeover Screen als neue Szene angezeigt.
    public void gameOver() {
        SoundeffektHandler.allPointsLostEffect();
        musicHandler.nextTrack();
        currentScene = new GameOver("Game Over", this);
        sceneID = 3;
    }

    @Override
    //wird diese Methode aufgerufen wird ein Soundeffekt aufgerufen und ein YouWon-Screen als neue Szene mit der aktuellen Punktzahl angezeigt
    public void youWon() {
        SoundeffektHandler.loveSparkleEffect();
        musicHandler.nextTrack();
        pointsAfterSceneFinished = currentScene.returnPoints();
        currentScene = new YouWon("Won", this, pointsAfterSceneFinished);
        sceneID = 3;
    }

    @Override
    //wird eine Taste gedrückt wird dieses Ereignis an die aktuelle Szene übergeben und diese reagiert darauf.
    public void onKeyPressed(KeyPressedEvent event){
        currentScene.onKeyPressed(event);
    }
}
