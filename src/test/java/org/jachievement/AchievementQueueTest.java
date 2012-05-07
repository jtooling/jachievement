package org.jachievement;

import junit.framework.Assert;

import org.junit.Test;

public class AchievementQueueTest {

	@Test
	public void showAchievementTest() {
		try {
			Achievement a = new Achievement("#1", "Test 1");
			AchievementQueue q = new AchievementQueue();
			Achievement b = new Achievement("#2", "Test 2");
			Achievement c = new Achievement("#3", "Test 3");
			Achievement d = new Achievement("#4", "Test 4");
			q.add(a);
			q.add(b);
			q.add(c);
			q.add(d);
			q.join();
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

}
