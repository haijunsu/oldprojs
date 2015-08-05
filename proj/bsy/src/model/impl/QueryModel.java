/*
 * @(#)QueryModel.java  2005-3-17
 * Copyright (c) 2005. All rights reserved. 
 * 
 * $Header$
 * $Log$
 */
package model.impl;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.filechooser.FileFilter;

import model.IModel;
import util.ResourcesUtil;
import util.StringUtil;
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
public class QueryModel extends JPanel implements IModel {

    private static final int OPEN_FILE_OK = 1;

    private static final int OPEN_FILE_CANCEL = 0;

    private static final int OPEN_FILE_ERROR = -1;

    private JButton jButtonBrowse = null;

    private JButton jButtonOpen = null;

    private JButton jButtonConvert = null;

    private JButton jButtonCancel = null;

    private JScrollPane jScrollPane = null;

    private JTextField jTextFieldFileName = null;

    private JSplitPane jSplitPane = null;

    private JPanel jPanel = null;

    private JLabel jLabelFile = null;

    private JSplitPane jSplitPane1 = null;

    private JScrollPane jScrollPane1 = null;

    private JScrollPane jScrollPane2 = null;

    private JTextArea jTextArea = null;

    private JTree jTree = null;

    private MainJFrame mainFrame = null;

    private JLabel jLabelStatus = null;

    private JTextArea jTextArea1 = null;

    public MainJFrame getMainFrame() {
        return mainFrame;
    }

    public void setMainFrame(MainJFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    public JLabel getJLabelStatus() {
        return jLabelStatus;
    }

    public void setJLabelStatus(JLabel labelStatus) {
        jLabelStatus = labelStatus;
    }

    /*
     * (non-Javadoc)
     * 
     * @see model.IModel#run(frame.MainJFrame)
     */
    public void run(MainJFrame frame) {
        QueryModel _model = new QueryModel();
        _model.setMainFrame(frame);
        _model.setJLabelStatus(frame.getJLabelStatus());
        frame.getJPanel().removeAll();
        frame.getJPanel().add(_model, BorderLayout.CENTER);

    }

    /**
     * This is the default constructor
     */
    public QueryModel() {
        super();
        initialize();
    }

    /**
     * This method initializes this
     * 
     * @return void
     */
    private void initialize() {
        GridBagConstraints gridBagConstraints20 = new GridBagConstraints();
        GridBagConstraints gridBagConstraints19 = new GridBagConstraints();
        this.setLayout(new GridBagLayout()); // Generated
        this.setSize(550, 360);
        gridBagConstraints19.gridx = 0; // Generated
        gridBagConstraints19.gridy = 0; // Generated
        gridBagConstraints19.weightx = 1.0; // Generated
        gridBagConstraints19.weighty = 1.0; // Generated
        gridBagConstraints19.fill = java.awt.GridBagConstraints.BOTH; // Generated
        gridBagConstraints19.ipadx = 483; // Generated
        gridBagConstraints19.ipady = 65; // Generated
        gridBagConstraints19.insets = new java.awt.Insets(4, 3, 0, 5); // Generated
        gridBagConstraints19.anchor = java.awt.GridBagConstraints.NORTH; // Generated
        gridBagConstraints20.gridx = 0; // Generated
        gridBagConstraints20.gridy = 1; // Generated
        gridBagConstraints20.weightx = 1.0; // Generated
        gridBagConstraints20.weighty = 1.0; // Generated
        gridBagConstraints20.fill = java.awt.GridBagConstraints.BOTH; // Generated
        gridBagConstraints20.insets = new java.awt.Insets(3, 4, 4, 6); // Generated
        gridBagConstraints20.anchor = java.awt.GridBagConstraints.CENTER; // Generated
        gridBagConstraints20.ipadx = 483; // Generated
        gridBagConstraints20.ipady = 234; // Generated
        this.add(getJSplitPane1(), gridBagConstraints20); // Generated
        this.add(getJSplitPane(), gridBagConstraints19); // Generated
    }

    /**
     * This method initializes jButton
     * 
     * @return javax.swing.JButton
     */
    private JButton getJButtonBrowse() {
        if (jButtonBrowse == null) {
            try {
                jButtonBrowse = new JButton();
                jButtonBrowse.setMnemonic('B');
                jButtonBrowse.setBounds(193, 16, 82, 26); // Generated
                jButtonBrowse.setText(ResourcesUtil
                        .getProperty("button.browse"));
                jButtonBrowse
                        .addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(
                                    java.awt.event.ActionEvent e) {
                                selectXMLFile();
                            }
                        });
            } catch (java.lang.Throwable e) {
                // TODO: Something
            }
        }
        return jButtonBrowse;
    }

    /**
     * This method initializes jButton1
     * 
     * @return javax.swing.JButton
     */
    private JButton getJButtonOpen() {
        if (jButtonOpen == null) {
            try {
                jButtonOpen = new JButton();
                jButtonOpen.setMnemonic('O');
                jButtonOpen.setBounds(193, 52, 82, 26); // Generated
                jButtonOpen.setText(ResourcesUtil.getProperty("button.open"));
                jButtonOpen.addActionListener(new java.awt.event.ActionListener() { 
                	public void actionPerformed(java.awt.event.ActionEvent e) {    
                		System.out.println("open xml file"); // TODO Auto-generated Event stub actionPerformed()
                	}
                });
            } catch (java.lang.Throwable e) {
                // TODO: Something
            }
        }
        return jButtonOpen;
    }

    /**
     * This method initializes jButton2
     * 
     * @return javax.swing.JButton
     */
    private JButton getJButtonConvert() {
        if (jButtonConvert == null) {
            try {
                jButtonConvert = new JButton();
                jButtonConvert.setBounds(283, 16, 82, 26); // Generated
                jButtonConvert.setMnemonic('T');
                jButtonConvert.setText(ResourcesUtil
                        .getProperty("button.convert"));

                jButtonConvert.addActionListener(new java.awt.event.ActionListener() { 
                	public void actionPerformed(java.awt.event.ActionEvent e) {    
                		System.out.println("Convert action"); // TODO Auto-generated Event stub actionPerformed()
                	}
                });
            } catch (java.lang.Throwable e) {
                // TODO: Something
            }
        }
        return jButtonConvert;
    }

    /**
     * This method initializes jButton3
     * 
     * @return javax.swing.JButton
     */
    private JButton getJButtonCancel() {
        if (jButtonCancel == null) {
            try {
                jButtonCancel = new JButton();
                jButtonCancel.setBounds(283, 52, 82, 26); // Generated
                jButtonCancel.setMnemonic('C');
                jButtonCancel.setText(ResourcesUtil
                        .getProperty("button.cancel"));
                jButtonCancel
                        .addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(
                                    java.awt.event.ActionEvent e) {
                                getJTextArea1().setText("");
                                getJTextArea1().setEditable(false);
                                getMainFrame().getJPanel().removeAll();
                                getMainFrame().getJPanel().add(
                                        getMainFrame().getJScrollPane(),
                                        BorderLayout.CENTER);
                                getMainFrame().repaint();
                            }
                        });
            } catch (java.lang.Throwable e) {
                // TODO: Something
            }
        }
        return jButtonCancel;
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
                jScrollPane.setViewportView(getJTextArea1()); // Generated
            } catch (java.lang.Throwable e) {
                // TODO: Something
            }
        }
        return jScrollPane;
    }

    /**
     * This method initializes jTextField1
     * 
     * @return javax.swing.JTextField
     */
    private JTextField getJTextFieldFileName() {
        if (jTextFieldFileName == null) {
            try {
                jTextFieldFileName = new JTextField();
                jTextFieldFileName.setBounds(34, 16, 152, 22); // Generated
            } catch (java.lang.Throwable e) {
                // TODO: Something
            }
        }
        return jTextFieldFileName;
    }

    /**
     * This method initializes jSplitPane
     * 
     * @return javax.swing.JSplitPane
     */
    private JSplitPane getJSplitPane() {
        if (jSplitPane == null) {
            try {
                jSplitPane = new JSplitPane();
                jSplitPane.setLeftComponent(getJPanel()); // Generated
                jSplitPane.setDividerLocation(375); // Generated
                jSplitPane.setDividerSize(0); // Generated
                jSplitPane.setRightComponent(getJScrollPane()); // Generated
            } catch (java.lang.Throwable e) {
                // TODO: Something
            }
        }
        return jSplitPane;
    }

    /**
     * This method initializes jPanel
     * 
     * @return javax.swing.JPanel
     */
    private JPanel getJPanel() {
        if (jPanel == null) {
            try {
                jPanel = new JPanel();
                jLabelFile = new JLabel();
                jPanel.setLayout(null); // Generated
                jLabelFile.setBounds(4, 16, 29, 25); // Generated
                jLabelFile.setText(ResourcesUtil.getProperty("label.file"));// Generated
                jPanel.setPreferredSize(new java.awt.Dimension(50, 50)); // Generated
                jPanel.add(getJTextFieldFileName(), null); // Generated
                jPanel.add(getJButtonBrowse(), null); // Generated
                jPanel.add(getJButtonOpen(), null); // Generated
                jPanel.add(getJButtonConvert(), null); // Generated
                jPanel.add(getJButtonCancel(), null); // Generated
                jPanel.add(jLabelFile, null); // Generated
            } catch (java.lang.Throwable e) {
                // TODO: Something
            }
        }
        return jPanel;
    }

    /**
     * This method initializes jSplitPane1
     * 
     * @return javax.swing.JSplitPane
     */
    private JSplitPane getJSplitPane1() {
        if (jSplitPane1 == null) {
            try {
                jSplitPane1 = new JSplitPane();
                jSplitPane1.setDividerLocation(200); // Generated
                jSplitPane1.setDividerSize(5); // Generated
                jSplitPane1.setLeftComponent(getJScrollPane1()); // Generated
                jSplitPane1.setRightComponent(getJScrollPane2()); // Generated
            } catch (java.lang.Throwable e) {
                // TODO: Something
            }
        }
        return jSplitPane1;
    }

    /**
     * This method initializes jScrollPane1
     * 
     * @return javax.swing.JScrollPane
     */
    private JScrollPane getJScrollPane1() {
        if (jScrollPane1 == null) {
            try {
                jScrollPane1 = new JScrollPane();
                jScrollPane1.setViewportView(getJTree()); // Generated
            } catch (java.lang.Throwable e) {
                // TODO: Something
            }
        }
        return jScrollPane1;
    }

    /**
     * This method initializes jScrollPane2
     * 
     * @return javax.swing.JScrollPane
     */
    private JScrollPane getJScrollPane2() {
        if (jScrollPane2 == null) {
            try {
                jScrollPane2 = new JScrollPane();
                jScrollPane2.setViewportView(getJTextArea()); // Generated
            } catch (java.lang.Throwable e) {
                // TODO: Something
            }
        }
        return jScrollPane2;
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

    /**
     * This method initializes jTree
     * 
     * @return javax.swing.JTree
     */
    private JTree getJTree() {
        if (jTree == null) {
            try {
                jTree = new JTree();
            } catch (java.lang.Throwable e) {
                // TODO: Something
            }
        }
        return jTree;
    }

    public int selectXMLFile() {
        JFileChooser _fileChooser = new JFileChooser();
        _fileChooser.setCurrentDirectory(new File("."));
        _fileChooser.setDialogTitle(ResourcesUtil
                .getProperty("chooser.xml.file.title"));
        _fileChooser.setFileFilter(new XMLFileFilter());
        int _nFileChooser = _fileChooser.showOpenDialog(getMainFrame());
        switch (_nFileChooser) {
        case JFileChooser.APPROVE_OPTION:
            try {
                File _file = _fileChooser.getSelectedFile();
                String _strFileName = _file.getAbsolutePath()
                        + StringUtil.getFileSeparator() + _file.getName();
                if (!_file.exists()) {
                    throw new FileNotFoundException("File not found. ("
                            + _strFileName + ")");
                }
                getJTextFieldFileName().setText(_strFileName);
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
                JOptionPane.showMessageDialog(null, ResourcesUtil
                        .getProperty("message.error.file.not.found"),
                        ResourcesUtil.getProperty("error.message.box.title"),
                        JOptionPane.ERROR_MESSAGE);

                return OPEN_FILE_ERROR;
            }
            break;
        case JFileChooser.CANCEL_OPTION:
            return OPEN_FILE_CANCEL;
        }
        return OPEN_FILE_OK;
    }

    /**
     * This method initializes jTextArea1
     * 
     * @return javax.swing.JTextArea
     */
    private JTextArea getJTextArea1() {
        if (jTextArea1 == null) {
            try {
                jTextArea1 = new JTextArea();
            } catch (java.lang.Throwable e) {
                // TODO: Something
            }
        }
        return jTextArea1;
    }
} //  @jve:decl-index=0:visual-constraint="19,63"

class XMLFileFilter extends FileFilter {

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
        if (filename.endsWith(".xml")) {
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
        return ResourcesUtil.getProperty("message.chooser.xml.file.type");
    }
}