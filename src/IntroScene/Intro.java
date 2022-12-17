package IntroScene;

import Audio.SoundeffektHandler;
import Config.Assets;
import Config.GameConfig;
import Scenes.Scene;
import Scenes.SceneListener;
import de.ur.mi.oop.events.KeyPressedEvent;
import de.ur.mi.oop.graphics.Image;

public class Intro extends Scene implements Assets, GameConfig {

    private Image backGround;
    private Image text;

    public Intro(String name, SceneListener listener) {
        super(name, listener);
        backGround = new Image(0,0,INTRO_BACKGROUND);
        text = new Image(0,0,INTRO_TEXT);
    }

    @Override
    public void draw(){
        backGround.draw();
        text.draw();
    }

    @Override
    public void onKeyPressed(KeyPressedEvent event){
        if(event.getKeyCode() == KeyPressedEvent.VK_SPACE){
            getListener().onSceneFinished();
        }
    }

}
