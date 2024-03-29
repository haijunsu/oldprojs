/*
 * @(#)ButtonHtml.java  2005-3-15
 * Copyright (c) 2005. All rights reserved. 
 * 
 * $Header$
 * $Log$
 */
package demo;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * <p><b>Description</b></p>
 * <p></p>
 * 
 * $Revision$
 * @author su_haijun  <a href=mailto:su_hj@126.com>su_hj@126.com</a>
 */
public class ButtonHtml extends JPanel
implements ActionListener {
protected JButton b1, b2, b3;

public ButtonHtml() {
ImageIcon leftButtonIcon = createImageIcon("images/right.gif");
ImageIcon middleButtonIcon = createImageIcon("images/middle.gif");
ImageIcon rightButtonIcon = createImageIcon("images/left.gif");

b1 = new JButton("<html><center><b><u>D</u>isable</b><br>"
+ "<font color=#ffffdd>middle button</font>", 
leftButtonIcon);
Font font = b1.getFont().deriveFont(Font.PLAIN);
b1.setFont(font);
b1.setVerticalTextPosition(SwingConstants.CENTER);
b1.setHorizontalTextPosition(SwingConstants.LEADING); //aka LEFT, for left-to-right locales
b1.setMnemonic(KeyEvent.VK_D);
b1.setActionCommand("disable");

b2 = new JButton("middle button", middleButtonIcon);
b2.setFont(font);
b2.setForeground(new Color(0xffffdd));
b2.setVerticalTextPosition(SwingConstants.BOTTOM);
b2.setHorizontalTextPosition(SwingConstants.CENTER);
b2.setMnemonic(KeyEvent.VK_M);

b3 = new JButton("<html><center><b><u>E</u>nable</b><br>"
+ "<font color=#ffffdd>middle button</font>", 
rightButtonIcon);
b3.setFont(font);
//Use the default text position of CENTER, TRAILING (RIGHT).
b3.setMnemonic(KeyEvent.VK_E);
b3.setActionCommand("enable");
b3.setEnabled(false);

//Listen for actions on buttons 1 and 3.
b1.addActionListener(this);
b3.addActionListener(this);

b1.setToolTipText("Click this button to disable the middle button.");
b2.setToolTipText("This middle button does nothing when you click it.");
b3.setToolTipText("Click this button to enable the middle button.");

//Add Components to this container, using the default FlowLayout.
add(b1);
add(b2);
add(b3);
}

public void actionPerformed(ActionEvent e) {
if ("disable".equals(e.getActionCommand())) {
b2.setEnabled(false);
b1.setEnabled(false);
b3.setEnabled(true);
} else {
b2.setEnabled(true);
b1.setEnabled(true);
b3.setEnabled(false);
}
}

/** Returns an ImageIcon, or null if the path was invalid. */
protected static ImageIcon createImageIcon(String path) {
java.net.URL imgURL = ButtonHtml.class.getResource(path);
if (imgURL != null) {
return new ImageIcon(imgURL);
} else {
System.err.println("Couldn't find file: " + path);
return null;
}
}

/**
* Create the GUI and show it.  For thread safety,
* this method should be invoked from the
* event-dispatching thread.
*/
private static void createAndShowGUI() {
//Make sure we have nice window decorations.
JFrame.setDefaultLookAndFeelDecorated(true);

//Create and set up the window.
JFrame frame = new JFrame("ButtonHtmlDemo");
frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

//Create and set up the content pane.
ButtonHtml newContentPane = new ButtonHtml();
newContentPane.setOpaque(true); //content panes must be opaque
frame.setContentPane(newContentPane);

//Display the window.
frame.pack();
frame.setVisible(true);
}

public static void main(String[] args) {
//Schedule a job for the event-dispatching thread:
//creating and showing this application's GUI.
javax.swing.SwingUtilities.invokeLater(new Runnable() {
public void run() {
createAndShowGUI();
}
});
}
}
