package Main;

import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {
    private Clip backgroundClip;
    private Clip effectClip;
    private URL soundURL[] = new URL[30];

    public Sound() {
        soundURL[0] = getClass().getResource("/Resources/Sound/Blegh music.wav");
        soundURL[1] = getClass().getResource("/Resources/Sound/Fight music.wav");
        soundURL[2] = getClass().getResource("/Resources/Sound/Coin pick up.wav");
        soundURL[3] = getClass().getResource("/Resources/Sound/Sword swing.wav");
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
        backgroundClip = loadClip(i);
        if (backgroundClip != null) {
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
        if (effectClip != null && effectClip.isOpen()) {
            effectClip.stop();
            effectClip.close(); // Release previous clip resources
        }
        effectClip = loadClip(i);
        if (effectClip != null) {
            effectClip.start();
        }
    }
}
