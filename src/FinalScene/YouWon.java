package FinalScene;

import Scenes.Scene;
import Scenes.SceneListener;
import de.ur.mi.oop.colors.Colors;
import de.ur.mi.oop.events.KeyPressedEvent;
import de.ur.mi.oop.graphics.Image;
import de.ur.mi.oop.graphics.Label;

public class YouWon extends FinalScene {

    private int score;
    private Label scoreLabel;

    public YouWon(String name, SceneListener listener, int score) {
        super(name, listener);
        this.score = score;
        initialiseScore();
    }

    private void initialiseScore(){
        String scoreText = "Dein Score ist: ";
        String scoreCountText = Integer.toString(score);
        scoreText = scoreText + scoreCountText;
        scoreLabel = new Label(SCORE_XPOS, SCORE_YPOS, scoreText);
    }

    @Override
    public void initialiseText(){
        text = new Image(0,0, YOUWON_TEXT);
    }

    @Override
    public void drawText(){
        scoreLabel.setFontSize(SCORE_SIZE);
        scoreLabel.setColor(Colors.WHITE);
        scoreLabel.setFont("Curlz MT");
        text.draw();
        scoreLabel.draw();
    }
}
