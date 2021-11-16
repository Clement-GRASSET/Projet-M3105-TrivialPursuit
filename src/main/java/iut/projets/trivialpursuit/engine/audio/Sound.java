package iut.projets.trivialpursuit.engine.audio;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.BufferedInputStream;
import java.io.InputStream;

public class Sound {

    private Clip clip;

    public Sound(InputStream inputStream) {
        try {
            InputStream bufferedIS = new BufferedInputStream(inputStream);

            AudioInputStream ais = AudioSystem.getAudioInputStream(bufferedIS);
            AudioFormat base_format = ais.getFormat();
            AudioFormat decoded_format = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED,
                    base_format.getSampleRate(),
                    16,
                    base_format.getChannels(),
                    base_format.getChannels() * 2,
                    base_format.getSampleRate(),
                    false);

            AudioInputStream dais = AudioSystem.getAudioInputStream(decoded_format, ais);
            clip = AudioSystem.getClip();
            clip.open(dais);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    Clip getClip() {
        return clip;
    }

    public void setLoop(boolean loop) {
        clip.loop((loop) ? Clip.LOOP_CONTINUOUSLY : 0);
    }

    public void setLoop(boolean loop, double startPoint) {
        setLoop(loop);
        clip.setLoopPoints((int)(clip.getFrameLength()*startPoint/(clip.getMicrosecondLength()/1000000)), -1);
    }
}
