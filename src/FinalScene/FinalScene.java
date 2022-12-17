package FinalScene;

import Config.Assets;
import Config.GameConfig;
import Scenes.Scene;
import Scenes.SceneListener;
import de.ur.mi.oop.events.KeyPressedEvent;
import de.ur.mi.oop.graphics.Image;

public class FinalScene extends Scene implements Assets, GameConfig {

    private Image background;
    public Image text;

    public FinalScene(String name, SceneListener listener) {
        super(name, listener);
        background = new Image(0,0, INTRO_BACKGROUND);
        initialiseText();
    }

    @Override
    public void onKeyPressed(KeyPressedEvent event){
        if(event.getKeyCode() == KeyPressedEvent.VK_SPACE){
            getListener().onSceneFinished();
        }
    }

    public void draw(){
        background.draw();
        drawText();
    }

    public void drawText(){
        text.draw();
    }

    public void initialiseText(){

    }

}
