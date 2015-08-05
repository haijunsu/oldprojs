/*
 * @(#)FileOpenModel.java  2005-3-16
 * Copyright (c) 2005. All rights reserved. 
 * 
 * $Header$
 * $Log$
 */
package model.impl;

import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileView;

import util.ResourcesUtil;
import util.StringUtil;

import model.IModel;
import frame.MainJFrame;

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
public class FileOpenModel extends JPanel implements IModel {

    private static final int OPEN_FILE_OK = 1;

    private static final int OPEN_FILE_CANCEL = 0;

    private static final int OPEN_FILE_ERROR = -1;

    private JScrollPane jScrollPane = null;

    private JTextArea jTextArea = null;

    private JFrame m_frame = null;

    private JLabel m_jLabelStatus = null;

    public JFrame getFrame() {
        return this.m_frame;
    }

    public void setFrame(JFrame frame) {
        m_frame = frame;
    }

    public JLabel getJLabelStatus() {
        return this.m_jLabelStatus;
    }

    public void setJLabelStatus(JLabel labelStatus) {
        m_jLabelStatus = labelStatus;
    }

    /**
     *  
     */
    public FileOpenModel() {
        super();
        initialize();
    }

    /**
     * This method initializes this
     * 
     * @return void
     */
    private void initialize() {
        this.setLayout(new BorderLayout());
        this.setSize(300, 200);
        this.add(getJScrollPane(), BorderLayout.CENTER); // Generated
    }

    /**
     * This method initializes jScrollPane
     * 
     * @return javax.swing.JScrollPane
     */
    private JScrollPane getJScrollPane() {
        if (jScrollPane == null) {
            try {
                jScrollPane = new JScrollPane();
                jScrollPane.setViewportView(getJTextArea()); // Generated
            } catch (java.lang.Throwable e) {
                // TODO: Something
            }
        }
        return jScrollPane;
    }

    /**
     * This method initializes jTextArea
     * 
     * @return javax.swing.JTextArea
     */
    private JTextArea getJTextArea() {
        if (jTextArea == null) {
            try {
                jTextArea = new JTextArea();
            } catch (java.lang.Throwable e) {
                // TODO: Something
            }
        }
        return jTextArea;
    }

    public int openFile() {
        JFileChooser _fileChooser = new JFileChooser();
        _fileChooser.setCurrentDirectory(new File("."));
        _fileChooser.setDialogTitle(ResourcesUtil
                .getProperty("chooser.file.title"));
        _fileChooser.setFileFilter(new TextFileFilter());
        _fileChooser.setFileView(new TextFileView());
        int _nFileChooser = _fileChooser.showOpenDialog(getFrame());
        switch (_nFileChooser) {
        case JFileChooser.APPROVE_OPTION:
            File _file = _fileChooser.getSelectedFile();
            try {
                FileReader _fileReader = new FileReader(_file);
                BufferedReader _bufferedReader = new BufferedReader(_fileReader);
                StringBuffer _sbTxt = new StringBuffer();
                String _str = "";
                while ((_str = _bufferedReader.readLine()) != null) {
                    _sbTxt.append(_str).append(StringUtil.getLineSeparator());
                }
                getJTextArea().setText(_sbTxt.toString());
                getJTextArea().setEnabled(true);
                getJTextArea().setLineWrap(true);
                getJTextArea().setEditable(false);
                getJLabelStatus().setText(
                        ResourcesUtil.getProperty("message.file.opening") + " "
                                + _file.getName());
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
                JOptionPane.showMessageDialog(null, ResourcesUtil
                        .getProperty("message.error.file.not.found"),
                        ResourcesUtil.getProperty("error.message.box.title"),
                        JOptionPane.ERROR_MESSAGE);

                return OPEN_FILE_ERROR;
            } catch (IOException ioe) {
                ioe.printStackTrace();
                JOptionPane.showMessageDialog(null, ResourcesUtil
                        .getProperty("message.error.io.error"), ResourcesUtil
                        .getProperty("error.message.box.title"),
                        JOptionPane.ERROR_MESSAGE);
                return OPEN_FILE_ERROR;
            }
            break;
        case JFileChooser.CANCEL_OPTION:
            return OPEN_FILE_CANCEL;
        }
        return OPEN_FILE_OK;
    }

    /*
     * (non-Javadoc)
     * 
     * @see model.IModel#run(frame.MainJFrame)
     */
    public void run(MainJFrame frame) {
        FileOpenModel _panel = new FileOpenModel();
        _panel.setFrame(frame);
        _panel.setJLabelStatus(frame.getJLabelStatus());
        if (_panel.openFile() == OPEN_FILE_OK) {
            frame.getJPanel().removeAll();
            frame.getJPanel().add(_panel, BorderLayout.CENTER);
        }
    }
}

class TextFileFilter extends FileFilter {

    /*
     * (non-Javadoc)
     * 
     * @see javax.swing.filechooser.FileFilter#accept(java.io.File)
     */
    public boolean accept(File file) {
        if (file.isDirectory()) {
            return true;
        }
        String filename = file.getName().toLowerCase();
        if (filename.endsWith(".txt") || filename.endsWith(".java")
                || filename.endsWith(".log") || filename.endsWith(".c")
                || filename.endsWith(".sql") || filename.endsWith(".ini")
                || filename.endsWith(".xml") || filename.endsWith(".htm")
                || filename.endsWith(".jsp") || filename.endsWith(".html")
                || filename.endsWith(".properties")) {
            return true;
        }
        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.swing.filechooser.FileFilter#getDescription()
     */
    public String getDescription() {
        // TODO Auto-generated method stub
        return ResourcesUtil.getProperty("message.chooser.file.type");
    }

}

class TextFileView extends FileView {
    public boolean accept(File file) {
        if (file.isDirectory()) {
            return true;
        }
        String filename = file.getName().toLowerCase();
        if (filename.endsWith(".txt") || filename.endsWith(".java")
                || filename.endsWith(".log")) {
            return true;
        }
        return false;
    }

    public String getTypeDescription(File file) {
        String filename = file.getName().toLowerCase();
        String _type = null;
        if (filename.endsWith(".txt")) {
            _type = "Text File";
        } else if (filename.endsWith(".java")) {
            _type = "Java File";
        } else if (filename.endsWith(".log")) {
            _type = "Log File";
        }
        return _type;
    }

    public String getDescription(File file) {
        String filename = file.getName().toLowerCase();
        String _type = null;
        if (filename.endsWith(".txt")) {
            _type = "Text File";
        } else if (filename.endsWith(".java")) {
            _type = "Java File";
        } else if (filename.endsWith(".log")) {
            _type = "Log File";
        }
        return _type;
    }
}