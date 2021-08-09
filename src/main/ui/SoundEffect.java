package ui;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;

// Represent sound effect collection stored in data
public class SoundEffect {
    private AudioClip soundSuccess = null;
    private AudioClip soundFail = null;

    // MODIFIES: this
    // EFFECTS: create a new SoundEffect, initialize each sound effect
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

    // EFFECTS: return soundSuccess
    public AudioClip getSoundSuccess() {
        return soundSuccess;
    }

    // EFFECTS: return soundFail
    public AudioClip getSoundFail() {
        return soundFail;
    }
}
