/*
 * @(#)Console.java  2005-3-15
 * Copyright (c) 2005. All rights reserved. 
 * 
 * $Header$
 * $Log$
 */
package control;

import javax.swing.JFrame;

/**
 * <p><b>Description</b></p>
 * <p></p>
 * 
 * $Revision$
 * @author su_haijun  <a href=mailto:su_hj@126.com>su_hj@126.com</a>
 */
public class Console {
    
    public static void run(JFrame frame) {
        run(frame, WindowPosition.CENTER);
    }
    public static void run(JFrame frame, int position){
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JFrame.setDefaultLookAndFeelDecorated(true);
        WindowPosition.location(frame, position);
        frame.setVisible(true);
    }
}
