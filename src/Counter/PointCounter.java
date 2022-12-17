package Counter;

import Config.Assets;
import Config.GameConfig;
import de.ur.mi.oop.colors.Colors;
import de.ur.mi.oop.graphics.Image;
import de.ur.mi.oop.graphics.Label;

public class PointCounter implements GameConfig, Assets {

    private int points;
    private String pointString;
    private Label pointLabel;
    private int zeroLength;
    private int labelLength;
    private boolean changed;
    private Image present;

    public PointCounter(int start){
        points = start;
        pointString = "";
        changed = false;
        setUpLabel();
        present = new Image(COUNTER_XPOS-COUNTER_FONTSIZE, COUNTER_YPOS-COUNTER_FONTSIZE, GOOD_PRESENT);
        present.setHeight(COUNTER_FONTSIZE);
        present.setWidth(COUNTER_FONTSIZE);
    }

    private void setUpLabel(){
        pointString = "";
        labelLength = Integer.toString(MAXPRESENTS).length();
        zeroLength = labelLength - Integer.toString(points).length();
        for(int i = 0; i<zeroLength; i++){
            pointString = pointString + "0";
        }
        pointString = pointString + points;
        pointLabel = new Label(COUNTER_XPOS, COUNTER_YPOS, pointString, Colors.WHITE);
        pointLabel.setFontSize(COUNTER_FONTSIZE);
    }

    public void addOne(){
        points++;
        changed = true;
    }

    public void removeOne(){
        points--;
        changed = true;
    }

    public void removePoints(int toRemove){
        points = points-toRemove;
        changed = true;
    }

    public void reset(){
        points = 0;
        changed = true;
    }

    public int getPoints(){
        return points;
    }

    public void update(){
        if(changed){
            setUpLabel();
            changed = false;
        }
    }

    public void draw(){
        present.draw();
        pointLabel.draw();
    }

}
