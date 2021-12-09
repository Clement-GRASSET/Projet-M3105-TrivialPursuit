package iut.projets.trivialpursuit.engine;

import iut.projets.trivialpursuit.game.scenes.MainMenuScene;

import javax.sound.sampled.*;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Vector;

public class AudioManager
{
    private float general_volume, music_volume, SFX_volume;
    private final List<Clip> audio_list;

    public AudioManager()
    {
        this.general_volume = 1;
        this.music_volume = 1;
        this.SFX_volume = 1;

        audio_list = new Vector<>();
    }

    private void updateClips()
    {
        for (Clip clip : audio_list){
            FloatControl volumeFC = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            volumeFC.setValue(general_volume*music_volume);
        }
    }

    public void playMusic(Sound sound) {
        sound.getClip().start();
    }

    public void playMusic(String music_name, boolean loop, double start_point)
    {
        Clip clip;

        try {
            InputStream IS = MainMenuScene.class.getResourceAsStream("/sounds/musics/" + music_name);
            InputStream bufferedIS = new BufferedInputStream(IS);

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

            FloatControl volumeFC = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);

            volumeFC.setValue(general_volume*music_volume);

            if (loop) {
                clip.loop(Clip.LOOP_CONTINUOUSLY);
                clip.setLoopPoints((int)(clip.getFrameLength()*start_point/(clip.getMicrosecondLength()/1000000)), -1);
            }

            clip.start();

            audio_list.add(clip);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void setGeneral_volume(float general_volume) {
        this.general_volume = general_volume;
        updateClips();
    }

    public void setMusic_volume(float music_volume) {
        this.music_volume = music_volume;
        updateClips();
    }

    public void setSFX_volume(float SFX_volume) {
        this.SFX_volume = SFX_volume;
        updateClips();
    }
}
