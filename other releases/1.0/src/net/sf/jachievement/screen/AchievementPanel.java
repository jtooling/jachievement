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
 * <b>AchievementPanel.java</b>: creates the achievement notification panel,
 * which contains the animation and message.
 */

package net.sf.jachievement.screen;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.Timer;

/**
 * Creates the achievement notification panel, which contains the
 * animation and message.
 * 
 * @author Paulo Roberto Massa Cereda
 * @version 1.0
 * @since 0.1
 */
public class AchievementPanel extends javax.swing.JPanel implements ActionListener {

    // the animation timer
    public Timer animationTimer;
    
    // the image frame
    private int imageFrame;

    /**
     * Constructor method, it creates a new panel.
     */
    public AchievementPanel() {

        // initialize all components
        initComponents();

        // sets the first frame
        imageFrame = 1;

        // and runs animation
        animationTimer = new Timer(50, this);
        //animationTimer.start();
    }

    /**
     * Defines the action listener implementation for the animation timer.
     * @param e The event.
     */
    public void actionPerformed(ActionEvent e) {

        // sets the image resource to null
        URL imgResource = null;

        // checks which image to display according to frame
        if (imageFrame < 10) {
            imgResource = getClass().getResource("../images/filme4000" + imageFrame + ".png");
        } else {
            imgResource = getClass().getResource("../images/filme400" + imageFrame + ".png");
        }

        // check if image was loaded
        if (imgResource != null) {

            // grab image
            ImageIcon icon = new ImageIcon(imgResource);

            // set image
            lblImage.setIcon(icon);

        } else {
            
            // image not loaded
            // something gone wrong, nothing to do
        }

        // increment frame
        imageFrame++;

        // checks if this is the last frame
        if (imageFrame == 81) {

            // stops timer
            animationTimer.stop();
        }
    }

    /**
     * Initializes the form. This method is called from within the constructor
     * method.
     */
    @SuppressWarnings("unchecked")
    private void initComponents() {

        lblTitle = new javax.swing.JLabel();
        lblMessage = new javax.swing.JLabel();
        lblImage = new javax.swing.JLabel();

        setOpaque(false);

        lblTitle.setForeground(new java.awt.Color(255, 255, 255));
        lblTitle.setText("Achievement unlocked");

        lblMessage.setForeground(new java.awt.Color(255, 255, 255));
        lblMessage.setText("10G - Finally a Java Library");

        lblImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/sf/jachievement/images/filme40001.png"))); // NOI18N
        lblImage.setAlignmentY(0.0F);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(lblImage)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTitle)
                    .addComponent(lblMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(lblImage))
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(lblTitle)
                .addGap(6, 6, 6)
                .addComponent(lblMessage))
        );
    }

    private javax.swing.JLabel lblImage;
    private javax.swing.JLabel lblMessage;
    private javax.swing.JLabel lblTitle;

    /**
     * Paints the current component.
     */
    @Override
    public void paintComponent(Graphics g) {

        // load the background image
        Image img = new ImageIcon(AchievementPanel.class.getResource("background.png")).getImage();

        // draw image to the current graphics
        g.drawImage(img, 0, 0, null);
    }

    /**
     * Sets the default message to be displayed in the achievement notification
     * window.
     * @param message The message to be displayed.
     */
    public void setMessage(String message) {
        lblMessage.setText(message);
    }


}
