package ObstacleDispensers;

import Config.GameConfig;
import Obstacles.AttackOrnament;

public class AttackDispenser implements GameConfig {

    private AttackOrnament[] currentAttacks;
    private int numberOfCurrentAttacks;

    public AttackDispenser(){
        numberOfCurrentAttacks = 0;
        currentAttacks = new AttackOrnament[MAXATTACKS];
        setUpArray();
    }

    private void setUpArray(){
        for(int i = 0; i<MAXATTACKS; i++){
            currentAttacks[i] = null;
        }
    }

    public void update(){
        for(int i = 0; i<MAXATTACKS; i++){
            if(currentAttacks[i] != null){
                currentAttacks[i].update();
                if(currentAttacks[i].leftScreen()){
                    currentAttacks[i] = null;
                }
            }

        }
    }

    public void spawnAttack(float xPos){
        int i = 0;
        while(i < MAXATTACKS && currentAttacks[i] != null){
            i++;
            if(i >= MAXATTACKS){
                i = 100;
            }
        }
        if(i<MAXATTACKS){
            currentAttacks[i] = new AttackOrnament(xPos);
        }
    }

    public void draw(){
        for(int i = 0; i<MAXATTACKS; i++){
            if(currentAttacks[i] != null){
                currentAttacks[i].draw();
            }
        }
    }

    public float updatecurrentX(){
        for(int i = 0; i<MAXATTACKS; i++){
            if(currentAttacks[i] != null){
                if(currentAttacks[i].getyPos() >= GROUNDHEIGHT - (PLAYER_HITBOX_RADIIUS-30) && currentAttacks[i].getyPos() < GROUNDHEIGHT){
                    return currentAttacks[i].getxPos();
                }
            }
        }
        return -1;
    }

    public void removeAttackOnPosition(float x){
        for(int i = 0; i<MAXATTACKS; i++){
            if(currentAttacks[i] != null){
                if(x == currentAttacks[i].getxPos()){
                    currentAttacks[i] = null;
                }
            }
        }
    }
}
