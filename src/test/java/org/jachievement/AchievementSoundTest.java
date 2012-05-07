package org.jachievement;

import javax.sound.sampled.AudioSystem;

import junit.framework.Assert;

import org.junit.Test;

public class AchievementSoundTest {

	@Test
	public void testPlay() throws Exception {
		try {
			AchievementSound sound = new AchievementSound(
					AudioSystem.getAudioInputStream(getClass()
							.getResourceAsStream("/notify.wav")));
			sound.start();
			sound.join();
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

}
