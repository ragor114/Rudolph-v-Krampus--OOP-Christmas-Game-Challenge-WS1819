package FinalScene;

import Scenes.Scene;
import Scenes.SceneListener;
import de.ur.mi.oop.events.KeyPressedEvent;
import de.ur.mi.oop.graphics.Image;

public class GameOver extends FinalScene {

    public GameOver(String name, SceneListener listener) {
        super(name, listener);
    }

    @Override
    public void initialiseText(){
        text = new Image(0, 0, GAMEOVER_TEXT);
    }

}
