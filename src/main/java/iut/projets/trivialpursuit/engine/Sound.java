package iut.projets.trivialpursuit.engine;

import javax.sound.sampled.*;
import java.io.BufferedInputStream;
import java.io.InputStream;

/**
 * Représente un son
 */
public class Sound {

    private Clip clip;
    float volume;
    double soundLength;

    /**
     * Construit un son
     * @param inputStream InputStream chargé depuis le disque.
     */
    Sound(InputStream inputStream) {
        volume = 1;
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
            clip.stop();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }

        soundLength = clip.getMicrosecondLength()/1000000.0;

        /*clip.addLineListener(new LineListener() {
            @Override
            public void update(LineEvent event) {
                System.out.println(event);
            }
        });*/
    }

    /**
     * Joue le son.
     * Ne peut pas être joué plusieurs fois en même temps
     */
    public void play() {
        clip.start();
    }

    /**
     * Arrête le son et revient en début
     */
    public void stop() {
        clip.stop();
        clip.setMicrosecondPosition(0);
    }

    /**
     * Met en pause le son
     */
    public void pause() {
        clip.stop();
    }

    /**
     * Définit si le son devrait être joué en boucle ou non
     * @param loop Vrai si le son devrait être joué en boucle
     */
    public void setLoop(boolean loop) {
        clip.loop((loop) ? Clip.LOOP_CONTINUOUSLY : 0);
    }

    /**
     * Définit si le son devrait être joué en boucle ou non
     * @param loop Vrai si le son devrait être joué en boucle
     * @param startPoint Le point de début de la boucle en secondes.
     */
    public void setLoop(boolean loop, double startPoint) {
        setLoop(loop);
        int startFrame = (int) (clip.getFrameLength()*startPoint/soundLength);
        clip.setLoopPoints(startFrame, -1);
    }

    /**
     * Renvoie le volume du son.
     * @return Le volume du son.
     */
    public float getVolume() {
        return volume;
    }

    /**
     * Définit le volume du son.
     * @param volume Le volume du son.
     */
    public void setVolume(float volume) {
        this.volume = Math.max(volume, 0);
        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        float gain = 20f * (float) Math.log10(this.volume);
        gainControl.setValue( (gain > -80) ? gain : -80 );
    }
}
