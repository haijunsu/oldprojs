/*
 * @(#)DatabaseConfigModel.java  2005-3-16
 * Copyright (c) 2005. All rights reserved. 
 * 
 * $Header$
 * $Log$
 */
package model.impl;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import model.IModel;
import util.ResourcesUtil;
import business.jdbc.DBConnectionManager;
import control.WindowPosition;
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
public class DatabaseConfigModel extends JDialog implements IModel {

    private javax.swing.JPanel jContentPane = null;

	private JLabel jLabelJdbcDriverName = null;
	private JPanel jPanel = null;
	private JTextField jTextFieldJdbcDriverName = null;
	private JLabel jLabelJdbcURL = null;
	private JLabel jLabelJdbcUserName = null;
	private JTextField jTextFieldJdbcURL = null;
	private JTextField jTextFieldJdbcUserName = null;
	private JPanel jPanel1 = null;
	private JLabel jLabelJdbcPassword = null;
	private JButton jButtonOK = null;
	private JButton jButtonCancel = null;
	private JPasswordField jPasswordField = null;
    /**
     * @throws java.awt.HeadlessException
     */
    public DatabaseConfigModel() throws HeadlessException {
        super();
        initialize();
    }

    /**
     * @param frame
     */
    public DatabaseConfigModel(MainJFrame frame) {
        super(frame);
        initialize();
    }

    /**
     * @param frame
     */
    public DatabaseConfigModel(MainJFrame frame, boolean model) {
        super(frame, model);
        initialize();
    }

    /*
     * (non-Javadoc)
     * 
     * @see model.IModel#run(frame.MainJFrame)
     */
    public void run(MainJFrame frame) {
        DatabaseConfigModel _configModel = new DatabaseConfigModel(frame, true);
        int _nX = frame.getX();
        int _nY = frame.getY();
        int _nWidth = frame.getWidth();
        int _nHight = frame.getHeight();
        int _nPositionX = _nX + (_nWidth - _configModel.getWidth()) / 2;
        int _nPositionY = _nY + (_nHight - _configModel.getHeight()) / 2;
        if (_nPositionX > WindowPosition.getScreenWidth() - _configModel.getWidth()){
            _nPositionX = WindowPosition.getScreenWidth() - _configModel.getWidth();
        }
        if (_nPositionY > WindowPosition.getScreenHeight() - _configModel.getHeight()){
            _nPositionY = WindowPosition.getScreenHeight() - _configModel.getHeight();
        }
        _configModel.setLocation(_nPositionX, _nPositionY);
        _configModel.show();

    }

    /**
     * This method initializes this
     * 
     * @return void
     */
    private void initialize() {
        this.setSize(340, 327);
        this.setTitle(ResourcesUtil.getProperty("database.config.title"));
        this.setResizable(false);
        this.setContentPane(getJContentPane());
    }

    /**
     * This method initializes jContentPane
     * 
     * @return javax.swing.JPanel
     */
    private javax.swing.JPanel getJContentPane() {
        if (jContentPane == null) {
            jContentPane = new javax.swing.JPanel();
            jContentPane.setLayout(new BorderLayout());
            jContentPane.add(getJPanel(), BorderLayout.CENTER);  // Generated
            jContentPane.add(getJPanel1(), java.awt.BorderLayout.SOUTH);  // Generated
        }
        return jContentPane;
    }
	/**
	 * This method initializes jPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */    
	private JPanel getJPanel() {
		if (jPanel == null) {
			try {
				jLabelJdbcUserName = new JLabel();
				jLabelJdbcURL = new JLabel();
				jLabelJdbcPassword = new JLabel();
				GridBagConstraints gridBagConstraints7 = new GridBagConstraints();
				GridBagConstraints gridBagConstraints8 = new GridBagConstraints();
				GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
				GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
				GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
				GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
				GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
				GridBagConstraints gridBagConstraints6 = new GridBagConstraints();
				jPanel = new JPanel();
				jLabelJdbcDriverName = new JLabel();
				jPanel.setLayout(new GridBagLayout());  // Generated
	            jLabelJdbcDriverName.setText(ResourcesUtil.getProperty("label.jdbc.database.driver"));  // Generated
	            jLabelJdbcURL.setText(ResourcesUtil.getProperty("label.jdbc.url"));  // Generated
	            jLabelJdbcUserName.setText(ResourcesUtil.getProperty("label.jdbc.username"));  // Generated
	            jLabelJdbcPassword.setText(ResourcesUtil.getProperty("label.jdbc.password"));  // Generated
	            gridBagConstraints1.gridx = 1;  // Generated
	            gridBagConstraints1.gridy = 0;  // Generated
	            gridBagConstraints1.insets = new java.awt.Insets(8,2,0,1);  // Generated
	            gridBagConstraints1.anchor = java.awt.GridBagConstraints.WEST;  // Generated
	            gridBagConstraints1.fill = java.awt.GridBagConstraints.NONE;  // Generated
	            
	            gridBagConstraints2.gridx = 1;  // Generated
	            gridBagConstraints2.gridy = 1;  // Generated
	            gridBagConstraints2.fill = java.awt.GridBagConstraints.HORIZONTAL;  // Generated
	            gridBagConstraints2.insets = new java.awt.Insets(4,2,7,1);  // Generated
	            gridBagConstraints2.anchor = java.awt.GridBagConstraints.WEST;  // Generated
	            gridBagConstraints2.weightx = 1.0D;  // Generated
	            
	            gridBagConstraints3.gridx = 1;  // Generated
	            gridBagConstraints3.gridy = 2;  // Generated
	            gridBagConstraints3.insets = new java.awt.Insets(8,2,0,1);  // Generated
	            gridBagConstraints3.anchor = java.awt.GridBagConstraints.WEST;  // Generated

	            gridBagConstraints4.gridx = 1;  // Generated
	            gridBagConstraints4.gridy = 3;  // Generated
	            gridBagConstraints4.fill = java.awt.GridBagConstraints.HORIZONTAL;  // Generated
	            gridBagConstraints4.insets = new java.awt.Insets(4,2,7,1);  // Generated
	            gridBagConstraints4.anchor = java.awt.GridBagConstraints.WEST;  // Generated
	            gridBagConstraints4.weightx = 1.0D;  // Generated
	            
	            gridBagConstraints5.gridx = 1;  // Generated
	            gridBagConstraints5.gridy = 4;  // Generated
	            gridBagConstraints5.insets = new java.awt.Insets(8,2,0,1);  // Generated
	            gridBagConstraints5.anchor = java.awt.GridBagConstraints.WEST;  // Generated

	            gridBagConstraints6.gridx = 1;  // Generated
	            gridBagConstraints6.gridy = 5;  // Generated
	            gridBagConstraints6.fill = java.awt.GridBagConstraints.HORIZONTAL;  // Generated
	            gridBagConstraints6.insets = new java.awt.Insets(4,2,7,1);  // Generated
	            gridBagConstraints6.anchor = java.awt.GridBagConstraints.WEST;  // Generated
	            gridBagConstraints6.weightx = 1.0D;  // Generated

	            gridBagConstraints7.gridx = 1;  // Generated
	            gridBagConstraints7.gridy = 6;  // Generated
	            gridBagConstraints7.anchor = java.awt.GridBagConstraints.WEST;  // Generated
	            gridBagConstraints7.insets = new java.awt.Insets(8,2,0,1);  // Generated
	            
	            gridBagConstraints8.gridx = 1;  // Generated
	            gridBagConstraints8.gridy = 7;  // Generated
	            gridBagConstraints8.insets = new java.awt.Insets(4,2,7,1);  // Generated
	            gridBagConstraints8.fill = java.awt.GridBagConstraints.HORIZONTAL;  // Generated
	            gridBagConstraints8.weightx = 1.0D;  // Generated

	            jPanel.add(jLabelJdbcDriverName, gridBagConstraints1);  // Generated
	            jPanel.add(getJTextFieldJdbcDriverName(), gridBagConstraints2);  // Generated
	            jPanel.add(jLabelJdbcURL, gridBagConstraints3);  // Generated
	            jPanel.add(getJTextFieldJdbcURL(), gridBagConstraints4);  // Generated
	            jPanel.add(jLabelJdbcUserName, gridBagConstraints5);  // Generated
	            jPanel.add(getJTextFieldJdbcUserName(), gridBagConstraints6);  // Generated
	            jPanel.add(jLabelJdbcPassword, gridBagConstraints7);  // Generated
	            jPanel.add(getJPasswordField(), gridBagConstraints8);  // Generated
			}
			catch (java.lang.Throwable e) {
				// TODO: Something
			}
		}
		return jPanel;
	}
	/**
	 * This method initializes jTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */    
	private JTextField getJTextFieldJdbcDriverName() {
		if (jTextFieldJdbcDriverName == null) {
			try {
				jTextFieldJdbcDriverName = new JTextField();
				jTextFieldJdbcDriverName.setText(DBConnectionManager.getJdbcClassName());
			}
			catch (java.lang.Throwable e) {
				// TODO: Something
			}
		}
		return jTextFieldJdbcDriverName;
	}
	/**
	 * This method initializes jTextField1	
	 * 	
	 * @return javax.swing.JTextField	
	 */    
	private JTextField getJTextFieldJdbcURL() {
		if (jTextFieldJdbcURL == null) {
			try {
				jTextFieldJdbcURL = new JTextField();
				jTextFieldJdbcURL.setText(DBConnectionManager.getJdbcURL());
			}
			catch (java.lang.Throwable e) {
				// TODO: Something
			}
		}
		return jTextFieldJdbcURL;
	}
	/**
	 * This method initializes jTextField2	
	 * 	
	 * @return javax.swing.JTextField	
	 */    
	private JTextField getJTextFieldJdbcUserName() {
		if (jTextFieldJdbcUserName == null) {
			try {
				jTextFieldJdbcUserName = new JTextField();
				jTextFieldJdbcUserName.setText(DBConnectionManager.getJdbcUserName());
			}
			catch (java.lang.Throwable e) {
				// TODO: Something
			}
		}
		return jTextFieldJdbcUserName;
	}
	/**
	 * This method initializes jPanel1	
	 * 	
	 * @return javax.swing.JPanel	
	 */    
	private JPanel getJPanel1() {
		if (jPanel1 == null) {
			try {
				jPanel1 = new JPanel();
				jPanel1.add(getJButtonOK(), null);  // Generated
				jPanel1.add(getJButtonCancel(), null);  // Generated
			}
			catch (java.lang.Throwable e) {
				// TODO: Something
			}
		}
		return jPanel1;
	}
	/**
	 * This method initializes jButton	
	 * 	
	 * @return javax.swing.JButton	
	 */    
	private JButton getJButtonOK() {
		if (jButtonOK == null) {
			try {
				jButtonOK = new JButton();
				jButtonOK.setMnemonic('O');
				jButtonOK.setText(ResourcesUtil.getProperty("button.ok"));  // Generated
				jButtonOK.addActionListener(new java.awt.event.ActionListener() { 
					public void actionPerformed(java.awt.event.ActionEvent e) {    
						DBConnectionManager.setJdbcClassName(getJTextFieldJdbcDriverName().getText().trim());
						DBConnectionManager.setJdbcURL(getJTextFieldJdbcURL().getText().trim());
						DBConnectionManager.setJdbcUserName(getJTextFieldJdbcUserName().getText().trim());
						char[] _password = getJPasswordField().getPassword();
						StringBuffer _sb = new StringBuffer();
						for (int i = 0; i < _password.length; i++) {
                            _sb.append(_password[i]);
                        }
						DBConnectionManager.setJdbcPasswrod(_sb.toString());
						dispose();
					}
				});
			}
			catch (java.lang.Throwable e) {
				// TODO: Something
			}
		}
		return jButtonOK;
	}
	/**
	 * This method initializes jButton1	
	 * 	
	 * @return javax.swing.JButton	
	 */    
	private JButton getJButtonCancel() {
		if (jButtonCancel == null) {
			try {
				jButtonCancel = new JButton();
				jButtonCancel.setMnemonic('C');
				jButtonCancel.setText(ResourcesUtil.getProperty("button.cancel"));  // Generated
				jButtonCancel.addActionListener(new java.awt.event.ActionListener() { 
					public void actionPerformed(java.awt.event.ActionEvent e) {    
						dispose();
					}
				});
			}
			catch (java.lang.Throwable e) {
				// TODO: Something
			}
		}
		return jButtonCancel;
	}
	/**
	 * This method initializes jPasswordField	
	 * 	
	 * @return javax.swing.JPasswordField	
	 */    
	private JPasswordField getJPasswordField() {
		if (jPasswordField == null) {
			try {
				jPasswordField = new JPasswordField();
			}
			catch (java.lang.Throwable e) {
				// TODO: Something
			}
		}
		return jPasswordField;
	}
         } //  @jve:decl-index=0:visual-constraint="10,10"
