package ui;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;

public class SoundEffect {
    private AudioClip soundSuccess = null;
    private AudioClip soundFail = null;

    public SoundEffect() {
        try {
            File wavFileSuccess = new File("./data/success.wav");
            soundSuccess = Applet.newAudioClip(wavFileSuccess.toURI().toURL());
            File wavFileFail = new File("./data/fail.wav");
            soundFail = Applet.newAudioClip(wavFileFail.toURI().toURL());
        } catch (Exception expt) {
            expt.printStackTrace();
        }
    }

    public AudioClip getSoundSuccess() {
        return soundSuccess;
    }

    public AudioClip getSoundFail() {
        return soundFail;
    }
}
