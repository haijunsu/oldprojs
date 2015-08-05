/*
 * @(#)WindowPosition.java  2005-3-15
 * Copyright (c) 2005. All rights reserved. 
 * 
 * $Header$
 * $Log$
 */
package control;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

/**
 * <p>
 * <b>Description </b>
 * </p>
 * <p>
 * </p>
 * 
 * $Revision$
 * 
 * @author su_haijun <a href=mailto:su_hj@126.com>su_hj@126.com </a>
 */
public class WindowPosition {
    private static int m_screenWidth;

    private static int m_screenHeight;

    private static final Dimension SCREEN_SIZE = Toolkit.getDefaultToolkit()
            .getScreenSize();

    public static final int CENTER = 0;

    public static final int LEFT_TOP = 1;

    public static final int LEFT_BOTTOM = 2;

    public static final int RIGHT_TOP = 3;

    public static final int RIGHT_BOTTOM = 4;

    public static int getScreenWidth() {
        if (m_screenWidth == 0) {
            m_screenWidth = SCREEN_SIZE.width;
        }
        return m_screenWidth;
    }

    public static int getScreenHeight() {
        if (m_screenHeight == 0) {
            m_screenHeight = SCREEN_SIZE.height;
        }
        return m_screenHeight;
    }

    public static void location(JFrame frame, int position) {
        int _nWidth = 0;
        int _nHeight = 0;

        _nWidth = frame.getWidth();
        _nHeight = frame.getHeight();

        switch (position) {
        case CENTER:
            frame.setLocation((getScreenWidth() - _nWidth) / 2,
                    (getScreenHeight() - _nHeight) / 2);
            break;
        case LEFT_TOP:
            frame.setLocation(0, 0);
            break;
        case LEFT_BOTTOM:
            frame.setLocation(0, getScreenHeight() - _nHeight);
            break;
        case RIGHT_TOP:
            frame.setLocation(getScreenWidth() - _nWidth, 0);
            break;
        case RIGHT_BOTTOM:
            frame.setLocation(getScreenWidth() - _nWidth, getScreenHeight()
                    - _nHeight);
            break;
        default:
            frame.setLocation((getScreenWidth() - _nWidth) / 2,
                    (getScreenHeight() - _nHeight) / 2);
        }
    }
}