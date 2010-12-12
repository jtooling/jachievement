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
 * Copyright (c) 2010, Paulo Roberto Massa Cereda
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
 * <b>AchievementQueue.java</b>: Handles the display of achievement
 * notifications. This class was created to act as an achievement
 * notification manager instead of allowing multiple achievments at once.
 */

package net.sf.jachievement;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.Queue;
import javax.swing.Timer;

/**
 * Handles the display of achievement notifications. This class was created to
 * act as an achievement notification manager instead of allowing multiple
 * achievements at once.
 * 
 * Please note that this achievement notification manager handles the calls
 * for methods of the <b>net.sf.jachievement.Achievement</b> class. After
 * adding an achievement notification to the queue, there is no need of
 * calling any methods of the notification itself. Check the following
 * example:
 * @code
 * Achievement achievement = new Achievement("Your first achievement");
 * AchievementQueue queue = new AchievementQueue();
 * queue.add(achievement);
 * @endcode
 * If there is no achievement notifications in the queue, the achievement
 * notification is displayed right after the call of the #add() method.
 * Otherwise, it will be queued until there is no achievement notifications
 * in display. Please note this is a simple <i>first in first out</i> queue,
 * so no priorities are estabilished when adding achievement notifications.
 *
 * @author Paulo Roberto Massa Cereda
 * @version 1.0
 * @since 1.0
 */
public class AchievementQueue implements ActionListener {

    // a queue, a timer and a notification
    private Queue queue;
    private Timer timer;
    private Achievement current;

    /**
     * Constructor method. Nothing new here, just instantiate
     * the local attributes.
     */
    public AchievementQueue() {
        queue = new LinkedList();
        timer = new Timer(50, this);
        current = null;
    }

    /**
     * Add the current achievement notification to the queue system. If this
     * is the only achievement notification in the queue, it will probably be
     * shown right away. Check the following example:
     * @code
     * Achievement achievement = new Achievement("Your first achievement");
     * AchievementQueue queue = new AchievementQueue();
     * queue.add(achievement);
     * @endcode
     * @param achievement The <code>net.sf.jcarrierpigeon.Notification</code>
     * object.
     */
    public synchronized void add(Achievement achievement) {
        // check if queue is empty and there is no
        // current notification
        if (queue.isEmpty() && (current == null)) {

            // show notification
            current = achievement;
            current.show();
        }
        else {
            // there are other notifications, so we need to wait
            queue.offer(achievement);

            // check if timer is not running
            if (timer.isRunning() == false) {

                // start the timer
                timer.start();
            }
        }
    }

    /**
     * Implements the <code>ActionListener</code> for our timer. It will trigger
     * notifications and process the queue.
     * @param e The event.
     */
    public void actionPerformed(ActionEvent e) {

        // there is a current notification going on
        if (current != null) {

            // check if queue is not empty and there is no
            // notification running
            if ((!queue.isEmpty()) && (!current.isRunning())) {

                // poll a notification from the queue
                current = (Achievement) queue.poll();

                // animate
                current.show();
            }
            else {

                // if the queue is empty
                if (queue.isEmpty()) {

                    // stop the timer
                    timer.stop();
                }
            }
        }
    }

}