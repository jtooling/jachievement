/**
 * \cond LICENSE
 * ********************************************************************
 * This is a conditional block for preventing the DoxyGen documentation
 * tool to include this license header within the description of each
 * source code file. If you want to include this block, please define
 * the LICENSE parameter into the provided DoxyFile.
 * ********************************************************************
 *
 * JAchievement - An achievement notification library
 * Copyright (c) 2012, Paulo Roberto Massa Cereda
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or
 * without modification, are permitted provided that the following
 * conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the
 *    distribution.
 *
 * 3. Neither the name of the project's author nor the names of its
 *    contributors may be used to endorse or promote products derived
 *    from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS
 * FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE
 * COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT,
 * INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING,
 * BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS
 * OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR
 * TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE
 * USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 * ********************************************************************
 * End of the LICENSE conditional block
 * ********************************************************************
 * \endcond
 * 
 * Achievement.java: This class packs the window and the animation configuration.
 * Please dispose the object if you don't add it to a queue.
 */

// package definition
package org.jachievement;

import junit.framework.Assert;

import org.junit.Test;

/**
 * This test class allows to define test cases for the {@link AchievementQueue}
 * class.
 * 
 * @author Antoine Neveux
 * @version 2.1
 * @since 2.1
 */
public class AchievementQueueTest {

	/**
	 * This simple test case allows to display 4 different achievements. You can
	 * manually validate that the 4 achievements are properly displayed.
	 */
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
