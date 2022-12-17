package SecondExplanation;

import Config.Assets;
import Scenes.Scene;
import Scenes.SceneListener;
import de.ur.mi.oop.events.KeyPressedEvent;
import de.ur.mi.oop.graphics.Image;

public class SecondExplanation extends Scene implements Assets {

    Image background;
    private Image text;

    public SecondExplanation(String name, SceneListener listener) {
        super(name, listener);
        background = new Image(0,0,INTRO_BACKGROUND);
        text = new Image(0,0,SECOND_TEXT);
    }

    @Override
    public void draw(){
        background.draw();
        text.draw();
    }

    @Override
    public void onKeyPressed(KeyPressedEvent event){
        if(event.getKeyCode() == KeyPressedEvent.VK_SPACE){
            getListener().onSceneFinished();
        }
    }

}
