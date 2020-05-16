package Game;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;


public class Sounds {


    public static void main(String[] args) {

    }

     public static void sound(File sound1) {
        try{

            AudioFileFormat aff = AudioSystem.getAudioFileFormat(sound1);
            AudioInputStream ais=AudioSystem.getAudioInputStream(sound1);
            AudioFormat af=aff.getFormat();
            DataLine.Info info=new DataLine.Info(Clip.class,ais.getFormat(),((int)ais .getFrameLength()*af.getFrameSize()));
            Clip ol=(Clip)AudioSystem.getLine(info);
            ol.open(ais);
            ol.loop(0);

        }catch(UnsupportedAudioFileException ee){ee.printStackTrace();}
        catch(IOException ea){ea.printStackTrace();}
        catch(LineUnavailableException LUE){LUE.printStackTrace();};


    }

    public static Thread music(File sound1) {
        try{
            AudioFileFormat aff = AudioSystem.getAudioFileFormat(sound1);
            AudioInputStream ais=AudioSystem.getAudioInputStream(sound1);
            AudioFormat af=aff.getFormat();
            DataLine.Info info=new DataLine.Info(Clip.class,ais.getFormat(),((int)ais .getFrameLength()*af.getFrameSize()));
            Clip ol=(Clip)AudioSystem.getLine(info);
            ol.open(ais);
            //ol.loop(Clip.LOOP_CONTINUOUSLY);
            ol.start();



        }
        catch(UnsupportedAudioFileException ee){ee.printStackTrace();}
        catch(IOException ea){ea.printStackTrace();}
        catch(LineUnavailableException LUE){LUE.printStackTrace();};

        return null;
    }

    public static Thread ferma(File sound1){
        music(sound1).stop();
        return null;
    }


}
