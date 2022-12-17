package Scenes;

import de.ur.mi.oop.events.KeyPressedEvent;

public class Scene {
    public String name;
    private SceneListener listener;

    public Scene(String name, SceneListener listener){
        this.name = name;
        this.listener = listener;
    }

    public void draw(){

    }

    public void update(){

    }

    public SceneListener getListener(){
        return listener;
    }

    public void onKeyPressed(KeyPressedEvent event){

    }

    public int returnPoints(){
        return -1;
    }
}
