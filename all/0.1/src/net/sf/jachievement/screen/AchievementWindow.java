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

package net.sf.jachievement.screen;

import java.awt.BorderLayout;
import javax.swing.JWindow;
import net.sf.jachievement.utils.TransparentWindow;

/**
 * The achievement notification frame.
 * @author Paulo Roberto Massa Cereda
 * @email cereda DOT paulo AT gmail DOT com
 * @version 0.1 (Alpha 1)
 */
public class AchievementWindow extends JWindow {

    public AchievementPanel panel;

    /** Creates new form tela3 */
    public AchievementWindow() {
        initComponents();

    }

    /** This method is called from within the constructor to
     * initialize the form.
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

    public void setMessage(String message) {
        panel.setMessage(message);
    }

}
