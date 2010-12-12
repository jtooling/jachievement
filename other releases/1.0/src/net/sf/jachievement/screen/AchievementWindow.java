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
 * <b>AchievementWindow.java</b>: implements the achievement notification
 * window.
 */

package net.sf.jachievement.screen;

import java.awt.BorderLayout;
import javax.swing.JWindow;
import net.sf.jachievement.utils.TransparentWindow;

/**
 * Implements the achievement notification window.
 * 
 * @author Paulo Roberto Massa Cereda
 * @version 1.0
 * @since 0.1
 */
public class AchievementWindow extends JWindow {

    public AchievementPanel panel;

    /**
     * Constructor method, creates a new achievement window.
     */
    public AchievementWindow() {
        initComponents();

    }

    /**
     * Initializes the form. This method is called from within the constructor
     * method.
     */
    @SuppressWarnings("unchecked")
    private void initComponents() {

        TransparentWindow bg = new TransparentWindow(this);
	bg.setLayout(new BorderLayout( ));

        panel = new AchievementPanel();
	panel.setOpaque(false);

	bg.add("Center",panel);
	getContentPane( ).add("Center",bg);
	pack( );
	setSize(364,60);
	setLocation(500,500);
    }

    /**
     * Sets the default message to be displayed in the achievement notification
     * window.
     * @param message The message to be displayed.
     */
    public void setMessage(String message) {
        panel.setMessage(message);
    }

}
