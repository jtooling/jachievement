/**
 * JAchievement -- An achievement notification library
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
 */

package net.sf.jachievement;

import com.sun.awt.AWTUtilities;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import net.sf.jachievement.audio.JAAudioPlayer;
import net.sf.jachievement.screen.AchievementWindow;
import net.sf.jachievement.utils.AnimationFrame;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;

/**
 * Provide the achievement notification feature. Please note that
 * this library has NO MULTIPLE WINDOW SUPPORT, so be sure to use this
 * class carefully.
 * @author Paulo Roberto Massa Cereda
 * @email cereda DOT paulo AT gmail DOT com
 * @version 0.1 (Alpha 1)
 */
public class Achievement implements TimingTarget {

    // constants
    private static final int ANIMATIONSPEED = 600;
    private static final int DURATION = 5000;
    private static final int DISTANCE = 50;

    // the frame
    private AchievementWindow frame;

    // animators
    private Animator animatorHandlerOnShow;
    private Animator animatorHandlerOnDisplay;
    private Animator animatorHandlerOnClose;

    // achievement status
    private AnimationFrame animationFrame;

    // achievement message
    private String message;

    // screen dimensions
    double screenHeight, screenWidth;
    int positionX, positionY;

    // settings
    private boolean playSound;

    public Achievement(String message) {

        // set the message
        this.message = message;

        // default action: play sound
        playSound = true;

        {
            // retrieve the screen resolution and set some attributes
            Rectangle rect = getScreenResolution();
            screenWidth = rect.getWidth();
            screenHeight = rect.getHeight();
        }
    }

    /*
     * Show the achievement notification. No multiple windows support!
     */
    public void show() {

        // set the animation frame
        animationFrame = AnimationFrame.ONSHOW;

        // set the achievement window
        frame = new AchievementWindow();

        // set the message
        frame.setMessage(message);

        // calculate X and Y positions
        positionX = (int) ((screenWidth / 2) - (frame.getWidth() / 2));
        positionY = (int) ((screenHeight - frame.getHeight()) - DISTANCE);

        // set the first animator handler
        animatorHandlerOnShow = new Animator(ANIMATIONSPEED, 1, Animator.RepeatBehavior.LOOP, this);
        animatorHandlerOnShow.start();
    }

    public void timingEvent(float f) {

        // animation is in the beginning
        if (animationFrame == AnimationFrame.ONSHOW) {

            // alpha goes from zero to one
            AWTUtilities.setWindowOpacity(frame, f);
        }
         else {

            // animation is in the end
            if (animationFrame == AnimationFrame.ONCLOSE) {

                // alpha goes from one to zero
                AWTUtilities.setWindowOpacity(frame, (1 - f));
            }
         }
    }

    public void begin() {

        // if animation is in the beginning, set
        // some parameters
        if (animationFrame == AnimationFrame.ONSHOW) {

            // set alpha to zero
            AWTUtilities.setWindowOpacity(frame, 0);

            // set screen position
            frame.setLocation(positionX, positionY);

            // set always on top
            frame.setAlwaysOnTop(true);

            // set visible
            frame.setVisible(true);

            // check if the notification sound is enabled
            if (playSound == true) {

                // create a new sound thread and play it
                (new JAAudioPlayer()).start();
            }
        }
    }

    public void end() {

        // if the animation finished the first part, change
        // the current frame
        if (animationFrame == AnimationFrame.ONSHOW) {
            animationFrame = AnimationFrame.ONDISPLAY;

            // create a new animator
            animatorHandlerOnDisplay = new Animator(DURATION, 1, Animator.RepeatBehavior.LOOP, this);

            // start this new animator
            animatorHandlerOnDisplay.start();

            // start the panel timer for the animated logo
            frame.panel.animationTimer.start();
        }
        else {

            // if the animation finished the second part,
            // change the current frame
            if (animationFrame == AnimationFrame.ONDISPLAY) {
                animationFrame = AnimationFrame.ONCLOSE;

                // create a new animator
                animatorHandlerOnClose = new Animator(ANIMATIONSPEED, 1, Animator.RepeatBehavior.LOOP, this);

                // start this new animator
                animatorHandlerOnClose.start();
            }
             else {

                // the animation is finishing

                // hide the frame
                frame.setVisible(false);

                // dispose
                frame.dispose();
             }
        }

    }

    public void repeat() {
        // this method is not needed
    }

    /**
     * Calculates the screen size.
     * @return A {@code java.awt.Rectangle} with the exact size of the screen.
     */
    private Rectangle getScreenResolution() {
        GraphicsEnvironment environment = GraphicsEnvironment.getLocalGraphicsEnvironment();
        return environment.getMaximumWindowBounds();
    }

    /*
     * Set the notification sound, {@code true} if the sound will be played, or
     * {@code false} otherwise.
     */
    public void playNotificationSound(boolean flag) {
        playSound = flag;
    }

}
