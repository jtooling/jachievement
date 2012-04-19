package org.jachievement;

import org.junit.Test;

public class JAchievementTest {

	@Test
	public void showAchievementTest() throws Exception {
		Achievement a = new Achievement("Achievement unlocked !",
				"First JUnit test successful !");
		AchievementQueue q = new AchievementQueue();
		q.add(a);
		// Now, we need to wait a little for the animation to display...
		Thread.sleep(5000);
	}

}
