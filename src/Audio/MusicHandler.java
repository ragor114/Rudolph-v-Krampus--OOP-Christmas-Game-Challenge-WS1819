package Audio;

import Config.Assets;
import de.ur.mi.oop.audio.AudioClip;

public class MusicHandler implements Assets {

    private int currentTrackID; //die ID des aktuellen Tracks
    private int maxTrackID; //so viele Musikstücke sind gespeichert
    private AudioClip currentClip; //die aktuelle Musik

    public MusicHandler(){
        currentTrackID = 0; //am Anfang wird der 1. Song abgespielt
        maxTrackID = 4; //aktuell sind 4 Songs verfügbar
        currentClip = new AudioClip(INTRO_MUSIC); //die IntroMusik wird als erstes gespielt
    }

    //beim Aufruf wird der aktuelle Song geloopt
    public void play(){
        currentClip.loop();
    }

    //beim Aufruf stoppt der aktuelle Song
    public void stop(){
        currentClip.stop();
    }

    //der aktuelle Song wird gestoppt, die Track ID entsprechend angepasst und abhängig von dieser ein anderer Song abgespielt
    public void nextTrack(){
        stop();
        currentTrackID++;
        if(currentTrackID > maxTrackID){
            currentTrackID = 0;
        }
        switch(currentTrackID){
            case 0:
                currentClip = new AudioClip(INTRO_MUSIC);
                break;
            case 1:
                currentClip = new AudioClip(JNR_JINGLE);
                break;
            case 2:
                currentClip = new AudioClip(INTRO_MUSIC);
                break;
            case 3:
                currentClip = new AudioClip(BOSS_MUSIC);
                break;
            case 4:
                currentClip = new AudioClip(INTRO_MUSIC);
                break;
        }
        play();
    }

}
