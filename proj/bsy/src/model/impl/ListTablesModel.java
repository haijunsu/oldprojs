/*
 * @(#)ListTablesModel.java  2005-3-17
 * Copyright (c) 2005. All rights reserved. 
 * 
 * $Header$
 * $Log$
 */
package model.impl;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

import model.IModel;
import util.ResourcesUtil;
import business.jdbc.DBConnectionManager;
import business.jdbc.DBDescription;
import business.jdbc.Query;
import business.tree.DatabaseTableTreeNode;
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
public class ListTablesModel extends JPanel implements IModel {

    private JSplitPane jSplitPane = null;

    private JLabel jLabelStatus = null;

    private JFrame jFrame = null;

    private JTree jTree = null;

    private JScrollPane jScrollPaneTree = null;

    private JScrollPane jScrollPaneTable = null;

    /*
     * (non-Javadoc)
     * 
     * @see model.IModel#run(frame.MainJFrame)
     */
    public void run(MainJFrame frame) {
        if (DBConnectionManager.getConnection() == null) {
            JOptionPane.showMessageDialog(null, ResourcesUtil
                    .getProperty("message.warn.connection.database.first"),
                    ResourcesUtil.getProperty("warn.message.box.title"),
                    JOptionPane.WARNING_MESSAGE);
            return;
        }
        ListTablesModel _model = new ListTablesModel();
        _model.setJFrame(frame);
        _model.setJLabelStatus((frame.getJLabelStatus()));
        _model.getJScrollPaneTable().setViewportView(
                DBDescription.getDBInformationTable());
        _model.repaint();
        frame.getJPanel().removeAll();
        frame.getJPanel().add(_model, BorderLayout.CENTER);

    }

    /**
     * This is the default constructor
     */
    public ListTablesModel() {
        super();
        initialize();
    }

    /**
     * This method initializes this
     * 
     * @return void
     */
    private void initialize() {
        this.setLayout(new BorderLayout(0, 2));
        this.setSize(300, 200);
        this.add(getJSplitPane(), java.awt.BorderLayout.CENTER); // Generated
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
                jSplitPane.setLeftComponent(getJScrollPaneTree()); // Generated
                jSplitPane.setRightComponent(getJScrollPaneTable()); // Generated
                jSplitPane.setDividerLocation(150); // Generated
            } catch (java.lang.Throwable e) {
                // TODO: Something
            }
        }
        return jSplitPane;
    }

    public JLabel getJLabelStatus() {
        return jLabelStatus;
    }

    public void setJLabelStatus(JLabel labelStatus) {
        jLabelStatus = labelStatus;
    }

    public JFrame getJFrame() {
        return jFrame;
    }

    public void setJFrame(JFrame frame) {
        jFrame = frame;
    }

    /**
     * This method initializes jTree
     * 
     * @return javax.swing.JTree
     */
    private JTree getJTree() {
        if (jTree == null) {
            try {
                jTree = new JTree(DatabaseTableTreeNode.getNode());
                jTree.setEditable(false);
                jTree
                        .addTreeSelectionListener(new javax.swing.event.TreeSelectionListener() {
                            public void valueChanged(
                                    javax.swing.event.TreeSelectionEvent e) {
                                DefaultMutableTreeNode _node = (DefaultMutableTreeNode) getJTree()
                                        .getLastSelectedPathComponent();

                                if (_node == null)
                                    return;
                                Object _nodeInfo = _node.getUserObject();
                                getJLabelStatus().setText((String) _nodeInfo);
                                if (_node.isLeaf()) {
                                    queryTable((String) _nodeInfo);
                                }
                            }

                            private void queryTable(String tableName) {
                                String _strSql = "SELECT * FROM " + tableName;
                                Query _query = new Query();
                                JTable _table = _query.queryForTable(_strSql);
                                _table
                                        .setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                                _table.setOpaque(true);
                                getJLabelStatus().setText(
                                        getJLabelStatus().getText() + ": "
                                                + _query.getResultList().size()
                                                + "rows");
                                getJScrollPaneTable().setViewportView(_table);
                            }
                        });
            } catch (java.lang.Throwable e) {
                // TODO: Something
            }
        }
        return jTree;
    }

    /**
     * This method initializes jScrollPane1
     * 
     * @return javax.swing.JScrollPane
     */
    private JScrollPane getJScrollPaneTree() {
        if (jScrollPaneTree == null) {
            try {
                jScrollPaneTree = new JScrollPane();
                jScrollPaneTree.setViewportView(getJTree()); // Generated
            } catch (java.lang.Throwable e) {
                // TODO: Something
            }
        }
        return jScrollPaneTree;
    }

    /**
     * This method initializes jScrollPane
     * 
     * @return javax.swing.JScrollPane
     */
    private JScrollPane getJScrollPaneTable() {
        if (jScrollPaneTable == null) {
            try {
                jScrollPaneTable = new JScrollPane();
            } catch (java.lang.Throwable e) {
                // TODO: Something
            }
        }
        return jScrollPaneTable;
    }

    public void setJTree(JTree tree) {
        jTree = tree;
    }
}

