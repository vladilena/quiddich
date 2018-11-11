package Audio;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * This class open an Audio track in .mp3 using external Library
 */
public class AudioThread implements Runnable {
    @Override
    public void run() {
        try {
            Player p = new Player(new FileInputStream("src\\main\\resources\\John Williams - The Chess Game.mp3"));
            p.play();
        } catch (JavaLayerException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
