package ui;

import javax.sound.sampled.*;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
//Creates a class that plays sound.
//Code taken from multiple posts in:
// "https://stackoverflow.com/questions/26305/how-can-i-play-sound-in-java"

public class PlaySound {

    public PlaySound(String file) {
        try {

            File f = new File(file);
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(f.toURI().toURL());
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }
}