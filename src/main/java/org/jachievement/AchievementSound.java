package org.jachievement;

import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineEvent.Type;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;

public class AchievementSound extends Thread {

	private AudioInputStream audioInputStream;

	private AudioListener listener = new AudioListener();

	public AchievementSound(AudioInputStream audioInputStream) {
		super();
		this.audioInputStream = audioInputStream;
	}

	protected void play() throws LineUnavailableException, IOException {
		Clip clip = AudioSystem.getClip();
		clip.addLineListener(listener);
		clip.open(audioInputStream);
		try {
			clip.start();
			listener.waitUntilDone();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			clip.close();
		}
		audioInputStream.close();
	}

	@Override
	public void run() {
		try {
			this.play();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	class AudioListener implements LineListener {
		private boolean done = false;

		@Override
		public synchronized void update(LineEvent event) {
			Type eventType = event.getType();
			if (eventType == Type.STOP || eventType == Type.CLOSE) {
				done = true;
				notifyAll();
			}
		}

		public synchronized void waitUntilDone() throws InterruptedException {
			while (!done) {
				wait();
			}
		}
	}

}
