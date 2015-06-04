package ca.johnson.game.gfx;
import javax.sound.sampled.*;

public class Sound {
	
	private Clip clip;
	
	// Change file name to match yours, of course
//	public static Sound mainGameMusic = new Sound("/sounds/sound.wav");
	public static Sound punch = new Sound("/sounds/punch.wav");
	public static Sound swing = new Sound("/sounds/woosh.wav");
	public static Sound walking_grass = new Sound("/sounds/walking_grass.wav");
	public static Sound running_grass = new Sound("/sounds/running_grass.wav");
	
	public Sound (String fileName) {
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(Sound.class.getResource(fileName));
			clip = AudioSystem.getClip();
			clip.open(ais);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void play(final int pos) {
		try {
			if (clip != null) {
				new Thread() {
					public void run() {
						synchronized (clip) {
							clip.stop();
							clip.setFramePosition(pos);
							clip.start();
						}
					}
				}.start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void stop(){
		if(clip == null) return;
		clip.stop();
	}
	public int getSize() {
		return clip.getFrameLength();
	}
	
	public void loop() {
		try {
			if (clip != null) {
				new Thread() {
					public void run() {
						synchronized (clip) {
							clip.stop();
							clip.setFramePosition(0);
							clip.loop(Clip.LOOP_CONTINUOUSLY);
						}
					}
				}.start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean isActive(){
		return clip.isActive();
	}}
