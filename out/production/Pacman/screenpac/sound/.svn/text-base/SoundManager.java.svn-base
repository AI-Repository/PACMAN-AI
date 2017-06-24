package screenpac.sound;

import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.AudioInputStream;
import java.io.File;

public class SoundManager {

    public static void main(String[] args) throws Exception {

        SoundManager sm = new SoundManager();
        for (int i = 0; i < 50; i++) {
            sm.eatPill();
            Thread.sleep(100);
        }
        Clip[] clips = {sm.eatGhost, sm.eatPill, sm.extraLife,
                sm.pacmanDies, sm.siren};

        for (Clip clip : clips) {
            sm.play(clip);
            Thread.sleep(500);
        }
    }

    // the sound manager class

    // this may need modifying
    static String path = "sounds/";
//    static String path = "screenpac/data/sounds/";
    Clip[] eats = loadEatSounds(50);
    int nEats = 0;
    Clip eatPill = getClip("eatingShort");
    Clip eatPower = getClip("eatingPowerPill");
    Clip eatGhost = getClip("eatingGhost");
    Clip extraLife = getClip("extraLife");
    Clip pacmanDies = getClip("pacmanDies");
    Clip siren = getClip("siren");

    public SoundManager() {
    }

    public void extraLife() {
        play(extraLife);
    }

    public void play(Clip clip) {
        clip.setFramePosition(0);
        clip.start();
    }

    public void stop(Clip clip) {
        clip.stop();
        clip.setFramePosition(0);
    }

    private Clip[] loadEatSounds(int n) {
        Clip[] clip = new Clip[n];
        for (int i = 0; i < n; i++) {
            clip[i] = getClip("eatingShort");
        }
        return clip;
    }

    public void eatPill() {
        // fire the n-th bullet and increments the index
        Clip clip = eats[nEats];
        clip.setFramePosition(0);
        clip.start();
        nEats = (nEats + 1) % eats.length;
    }

    public static Clip getClip(String filename) {
        Clip clip = null;
        try {
            clip = AudioSystem.getClip();
            AudioInputStream sample =
                    AudioSystem.getAudioInputStream(new File(path + filename + ".wav"));
            clip.open(sample);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return clip;
    }
}
