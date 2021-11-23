package iut.projets.trivialpursuit.engine.audio;

import javax.sound.sampled.*;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Sound {

    private Clip clip;
    private AudioInputStream dais;

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

            dais = AudioSystem.getAudioInputStream(decoded_format, ais);
            clip = AudioSystem.getClip();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }

        /*clip.addLineListener(new LineListener() {
            @Override
            public void update(LineEvent event) {
                System.out.println(event);
            }
        });*/
    }

    Clip getClip() {
        return clip;
    }

    public void play() {
        if (!clip.isOpen()) {
            try {
                clip.open(dais);
            } catch (Exception e) {
                e.printStackTrace();
                System.exit(1);
            }
        }
        clip.start();
    }

    public void stop() {
        clip.stop();
    }

    public void pause() {
        clip.stop();
    }

    public void setLoop(boolean loop) {
        clip.loop((loop) ? Clip.LOOP_CONTINUOUSLY : 0);
    }

    public void setLoop(boolean loop, double startPoint) {
        setLoop(loop);
        clip.setLoopPoints((int)(clip.getFrameLength()*startPoint/(clip.getMicrosecondLength()/1000000)), -1);
    }
}
