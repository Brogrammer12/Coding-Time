package Main;

import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.util.HashMap;
import java.util.Map;

public class Sound {
    private Clip backgroundClip;
    private Map<Integer, Clip> soundEffects = new HashMap<>();
    private URL soundURL[] = new URL[30];

    public Sound() {
        soundURL[0] = getClass().getResource("/Resources/Sound/zelda2.wav");
        soundURL[1] = getClass().getResource("/Resources/Sound/pokemon2.wav");
        soundURL[2] = getClass().getResource("/Resources/Sound/Coin pick up.wav");
        soundURL[3] = getClass().getResource("/Resources/Sound/Sword swing.wav");
        soundURL[4] = getClass().getResource("/Resources/Sound/damage.wav");
        soundURL[5] = getClass().getResource("/Resources/Sound/magic.wav");
        soundURL[6]=getClass().getResource("/Resources/Sound/skelly.wav");
        soundURL[7]=getClass().getResource("/Resources/Sound/select.wav");

        // Preload all sound effects
        for (int i = 0; i < soundURL.length; i++) {
            if (soundURL[i] != null) {
                soundEffects.put(i, loadClip(i));
            }
        }
    }

    private Clip loadClip(int i) {
        try {
            if (soundURL[i] != null) {
                AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
                Clip newClip = AudioSystem.getClip();
                newClip.open(ais);
                return newClip;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void playBackgroundMusic(int i) {
        if (backgroundClip != null && backgroundClip.isOpen()) {
            backgroundClip.stop();
            backgroundClip.close(); // Release previous clip resources
        }
        backgroundClip = soundEffects.get(i);
        if (backgroundClip != null) {
            backgroundClip.setFramePosition(0); // Reset to start
            backgroundClip.start();
            backgroundClip.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }

    public void stopBackgroundMusic() {
        if (backgroundClip != null && backgroundClip.isOpen()) {
            backgroundClip.stop();
            backgroundClip.close(); // Release current clip resources
        }
    }

    public void playSoundEffect(int i) {
        Clip effectClip = soundEffects.get(i);
        if (effectClip != null) {
            effectClip.setFramePosition(0); // Reset to start
            effectClip.start();
        }
    }
}
