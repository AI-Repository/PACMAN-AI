package screenpac.sound;

/**
 * This is a static convenience wrapper around Sound Manager
 */
public class PlaySound {

    // todo: integrate use of sound into the view class
    // and perhaps use a sound queue to manage this

    public static void main(String[] args) throws Exception {
        enable();
        startSiren();
        Thread.sleep(1000);
        eatPill();
        Thread.sleep(500);
        eatPill();
        Thread.sleep(500);
        eatGhost();
        Thread.sleep(500);
        extraLife();
        Thread.sleep(500);
        loseLife();
        Thread.sleep(1500);
        stopSiren();
    }

    static SoundManager sm = new SoundManager();
    // call enable() to set this to true and hence play sounds
    private static boolean playSounds = true;

    public  static void enable() {
        playSounds = true;
    }

    public  static void disable() {
        playSounds = false;
    }



    public static void eatPill() {
        if (playSounds) {
            sm.eatPill();
        }
    }

    public static void eatPower() {
        if (playSounds) {
            sm.play(sm.eatPower);
        }
    }

    public static void eatGhost() {
        if (playSounds) {
            sm.play(sm.eatGhost);
        }
    }

    public static void loseLife() {
        if (playSounds) {
            sm.play(sm.pacmanDies);
        }
    }

    public static void extraLife() {
        if (playSounds) {
            sm.play(sm.extraLife);
        }
    }

    public static void startSiren() {
        if (playSounds) {
            sm.play(sm.siren);
        }
    }

    public static void stopSiren() {
        if (playSounds) {
            sm.stop(sm.siren);
        }
    }
}
