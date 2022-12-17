package Characters;

import Bossfight.PositionListener;
import Config.GameConfig;
import ObstacleDispensers.AttackDispenser;

import java.util.Random;

public class BossEnemyAttackHandler implements GameConfig {

    private int framecounter;
    private AttackDispenser attacker;
    private Random rand;
    private PositionListener positionListener;
    private boolean tripleAttackActive;
    private int tripleAttackCounter;
    private TargetListener targetListener;
    private float currentX;

    public BossEnemyAttackHandler(PositionListener positionListener, TargetListener targetListener){
        framecounter = 0;
        tripleAttackCounter = 0;
        tripleAttackActive = false;
        attacker = new AttackDispenser();
        rand = new Random();
        this.positionListener = positionListener;
        this.targetListener = targetListener;
    }

    public void update(){
        attacker.update();
        framecounter++;
        if(tripleAttackActive){
            tripleAttack();
        } else if(framecounter == ATTACK_FREQ){
            attackChoose();
        }
        hitHandler();
    }

    private void hitHandler(){
        updateCurrentX();
        if(targetListener.hitTarget()){
            attacker.removeAttackOnPosition(currentX);
            targetListener.onTargetHit(0);
        }
    }

    private void attackChoose(){
        int random = rand.nextInt(100);
        if(random<50){
            singleAttack();
            framecounter = 0;
        } else{
            startTripleAttack();
        }
    }

    private void singleAttack(){
        attacker.spawnAttack(getEnemyPos());
    }

    private void startTripleAttack(){
        tripleAttackActive = true;
        framecounter = 0;
        tripleAttackCounter = 0;
    }

    private void tripleAttack(){
        if(framecounter%TRIPLE_ATTACK_FREQ == 0){
            if(tripleAttackCounter == 0 || tripleAttackCounter == 1){
                tripleAttackCounter++;
            } else{
                tripleAttackActive = false;
                framecounter = 0;
            }
            attacker.spawnAttack(getEnemyPos());
        }
    }

    private float getEnemyPos(){
        return positionListener.getEnemyXPos();
    }

    public void draw(){
        attacker.draw();
    }

    private void updateCurrentX(){
        currentX = attacker.updatecurrentX();
        //System.out.println(currentX);
    }

    public float getCurrentX(){
        return currentX;
    }

}
