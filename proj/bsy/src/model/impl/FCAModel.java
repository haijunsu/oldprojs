/*
 * @(#)FCAModel.java  2005-3-19
 * Copyright (c) 2005. All rights reserved. 
 * 
 * $Header$
 * $Log$
 */
package model.impl;

import util.ResourcesUtil;
import util.Shell;
import frame.MainJFrame;
import model.IModel;

/**
 * <p><b>Description</b></p>
 * <p></p>
 * 
 * $Revision$
 * @author su_haijun  <a href=mailto:su_hj@126.com>su_hj@126.com</a>
 */
public class FCAModel implements IModel {

    /* (non-Javadoc)
     * @see model.IModel#run(frame.MainJFrame)
     */
    public void run(MainJFrame frame) {
        frame.setVisible(false);
        String _strCmd = ResourcesUtil.getProperty("commands.fca");
        Shell.run(_strCmd);
        frame.setVisible(true);
    }

}
