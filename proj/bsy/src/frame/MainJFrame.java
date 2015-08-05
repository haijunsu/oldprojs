/*
 * @(#)MainJFrame.java  2005-3-15
 * Copyright (c) 2005. All rights reserved. 
 * 
 * $Header$
 * $Log$
 */
package frame;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import util.ResourcesUtil;

import model.IModel;
import control.ModelFactory;
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
public class MainJFrame extends JFrame {
    
    private static boolean m_isClosedBySelf = false;

    private javax.swing.JPanel jContentPane = null;

    private JMenuBar jJMenuBar = null;

    private JMenu jMenuFile = null;

    private JMenuItem jMenuItemOpen = null;

    private JScrollPane jScrollPane = null;

    private JTextArea jTextArea = null;

    private JLabel jLabelStatus = null;  //  @jve:decl-index=0:visual-constraint="79,437"

    private JMenuItem jMenuItemClose = null;

    private JMenu jMenuTools = null;

    private JMenuItem jMenuItemFCA = null;

    private JMenuItem jMenuItemConceptualGraphs = null;

    private JMenuItem jMenuItemProtege = null;

    private JMenu jMenuDatabase = null;

    private JMenuItem jMenuItemConnectingDatabase = null;

    private JMenuItem jMenuItemListTable = null;

    private JMenuItem jMenuItemQuery = null;

	private JPanel jPanel = null;
    /**
     * This method initializes jJMenuBar
     * 
     * @return javax.swing.JMenuBar
     */
    private JMenuBar getJJMenuBar() {
        if (jJMenuBar == null) {
            try {
                jJMenuBar = new JMenuBar();
                jJMenuBar.add(getJMenuFile()); // Generated
                jJMenuBar.add(getJMenuTools()); // Generated
                jJMenuBar.add(getJMenuDatabase()); // Generated

            } catch (java.lang.Throwable e) {
                // TODO: Something
            }
        }
        return jJMenuBar;
    }

    /**
     * This method initializes jMenu
     * 
     * @return javax.swing.JMenu
     */
    private JMenu getJMenuFile() {
        if (jMenuFile == null) {
            try {
                jMenuFile = new JMenu();
                jMenuFile.setMnemonic('F');
                jMenuFile.setText(ResourcesUtil.getProperty("menu.file")); // Generated
                jMenuFile.add(getJMenuItemOpen()); // Generated
                jMenuFile.add(getJMenuItemClose()); // Generated
            } catch (java.lang.Throwable e) {
                // TODO: Something
            }
        }
        return jMenuFile;
    }

    /**
     * This method initializes jMenuItem
     * 
     * @return javax.swing.JMenuItem
     */
    private JMenuItem getJMenuItemOpen() {
        if (jMenuItemOpen == null) {
            try {
                jMenuItemOpen = new JMenuItem();
                jMenuItemOpen.setText(ResourcesUtil.getProperty("menu.file.open")); // Generated
                jMenuItemOpen.setMnemonic('O');
                jMenuItemOpen
                        .addMouseListener(new java.awt.event.MouseAdapter() {
                            public void mouseEntered(java.awt.event.MouseEvent e) {
                                getJLabelStatus().setText(ResourcesUtil.getProperty("menu.file.open.tips"));
                            }

                            public void mouseExited(java.awt.event.MouseEvent e) {
                                getJLabelStatus().setText(" ");
                            }
                        });
                jMenuItemOpen
                        .addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(
                                    java.awt.event.ActionEvent e) {
                                getJLabelStatus().setText(" ");
                                IModel _model = ModelFactory.getOpenFile();
                                if (_model == null) {
                                	performEmptyActionOfMenuItem(e);
                                } else {
                                    _model.run(MainJFrame.this);
                                }
                            }
                        });
            } catch (java.lang.Throwable e) {
                // TODO: Something
            }
        }
        return jMenuItemOpen;
    }

    /**
     * This method initializes jScrollPane
     * 
     * @return javax.swing.JScrollPane
     */
    public JScrollPane getJScrollPane() {
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

    /**
     * This method initializes jMenuItem1
     * 
     * @return javax.swing.JMenuItem
     */
    private JMenuItem getJMenuItemClose() {
        if (jMenuItemClose == null) {
            try {
                jMenuItemClose = new JMenuItem();
                jMenuItemClose.setText(ResourcesUtil.getProperty("menu.file.close")); // Generated
                jMenuItemClose.setMnemonic('C');
                jMenuItemClose
                        .addMouseListener(new java.awt.event.MouseAdapter() {
                            public void mouseEntered(java.awt.event.MouseEvent e) {
                                getJLabelStatus().setText(ResourcesUtil.getProperty("menu.file.close.tips"));
                            }

                            public void mouseExited(java.awt.event.MouseEvent e) {
                                getJLabelStatus().setText(" ");
                            }
                        });
                jMenuItemClose
                        .addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(
                                    java.awt.event.ActionEvent e) {
                                m_isClosedBySelf = true;
                                System.exit(0);
                            }
                        });
            } catch (java.lang.Throwable e) {
                // TODO: Something
            }
        }
        return jMenuItemClose;
    }

    /**
     * This method initializes jMenu
     * 
     * @return javax.swing.JMenu
     */
    private JMenu getJMenuTools() {
        if (jMenuTools == null) {
            try {
                jMenuTools = new JMenu();
                jMenuTools.setMnemonic('T');
                jMenuTools.add(getJMenuItemFCA()); // Generated
                jMenuTools.add(getJMenuItemConceptualGraphs()); // Generated
                jMenuTools.add(getJMenuItemProtege()); // Generated
                jMenuTools.setText(ResourcesUtil.getProperty("menu.tools")); // Generated
            } catch (java.lang.Throwable e) {
                // TODO: Something
            }
        }
        return jMenuTools;
    }

    /**
     * This method initializes jMenuItem
     * 
     * @return javax.swing.JMenuItem
     */
    private JMenuItem getJMenuItemFCA() {
        if (jMenuItemFCA == null) {
            try {
                jMenuItemFCA = new JMenuItem();
                jMenuItemFCA.setMnemonic('F');
                jMenuItemFCA.setText(ResourcesUtil.getProperty("menu.tools.fca")); // Generated
                jMenuItemFCA
                        .addMouseListener(new java.awt.event.MouseAdapter() {
                            public void mouseEntered(java.awt.event.MouseEvent e) {
                                getJLabelStatus().setText(ResourcesUtil.getProperty("menu.tools.fca.tips"));
                            }

                            public void mouseExited(java.awt.event.MouseEvent e) {
                                getJLabelStatus().setText(" ");
                            }
                        });
                jMenuItemFCA
                        .addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(
                                    java.awt.event.ActionEvent e) {
                                getJLabelStatus().setText(" ");
                                IModel _model = ModelFactory.getFCA();
                                if (_model == null) {
                                	performEmptyActionOfMenuItem(e);
                                } else {
                                    _model.run(MainJFrame.this);
                                }
                            }
                        });
            } catch (java.lang.Throwable e) {
                // TODO: Something
            }
        }
        return jMenuItemFCA;
    }

    /**
     * This method initializes jMenuItem
     * 
     * @return javax.swing.JMenuItem
     */
    private JMenuItem getJMenuItemConceptualGraphs() {
        if (jMenuItemConceptualGraphs == null) {
            try {
                jMenuItemConceptualGraphs = new JMenuItem();
                jMenuItemConceptualGraphs.setMnemonic('G');
                jMenuItemConceptualGraphs
                        .addMouseListener(new java.awt.event.MouseAdapter() {
                            public void mouseEntered(java.awt.event.MouseEvent e) {
                                getJLabelStatus().setText(ResourcesUtil.getProperty("menu.tools.conceptual.graphs.tips"));
                            }

                            public void mouseExited(java.awt.event.MouseEvent e) {
                                getJLabelStatus().setText(" ");
                            }
                        });
                jMenuItemConceptualGraphs
                        .addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(
                                    java.awt.event.ActionEvent e) {
                                getJLabelStatus().setText(" ");
                                IModel _model = ModelFactory.getConceptualGraphs();
                                if (_model == null) {
                                	performEmptyActionOfMenuItem(e);
                                } else {
                                    _model.run(MainJFrame.this);
                                }
                            }
                        });
                jMenuItemConceptualGraphs.setText(ResourcesUtil.getProperty("menu.tools.conceptual.graphs")); // Generated
            } catch (java.lang.Throwable e) {
                // TODO: Something
            }
        }
        return jMenuItemConceptualGraphs;
    }

    /**
     * This method initializes jMenuItem
     * 
     * @return javax.swing.JMenuItem
     */
    private JMenuItem getJMenuItemProtege() {
        if (jMenuItemProtege == null) {
            try {
                jMenuItemProtege = new JMenuItem();
                jMenuItemProtege.setMnemonic('P');
                jMenuItemProtege
                        .addMouseListener(new java.awt.event.MouseAdapter() {
                            public void mouseEntered(java.awt.event.MouseEvent e) {
                                getJLabelStatus().setText(ResourcesUtil.getProperty("menu.tools.protege.tips"));
                            }

                            public void mouseExited(java.awt.event.MouseEvent e) {
                                getJLabelStatus().setText(" ");
                            }
                        });
                jMenuItemProtege
                        .addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(
                                    java.awt.event.ActionEvent e) {
                                getJLabelStatus().setText(" ");
                                IModel _model = ModelFactory.getProtege();
                                if (_model == null) {
                                	performEmptyActionOfMenuItem(e);
                                } else {
                                    _model.run(MainJFrame.this);
                                }
                            }
                        });
                jMenuItemProtege.setText(ResourcesUtil.getProperty("menu.tools.protege")); // Generated
            } catch (java.lang.Throwable e) {
                // TODO: Something
            }
        }
        return jMenuItemProtege;
    }

    /**
     * This method initializes jMenu
     * 
     * @return javax.swing.JMenu
     */
    private JMenu getJMenuDatabase() {
        if (jMenuDatabase == null) {
            try {
                jMenuDatabase = new JMenu();
                jMenuDatabase.setMnemonic('D');
                jMenuDatabase.setText(ResourcesUtil.getProperty("menu.database")); // Generated
                jMenuDatabase.add(getJMenuItemConnectingDatabase()); // Generated
                jMenuDatabase.add(getJMenuItemListTable()); // Generated
                jMenuDatabase.add(getJMenuItemQuery()); // Generated
            } catch (java.lang.Throwable e) {
                // TODO: Something
            }
        }
        return jMenuDatabase;
    }

    /**
     * This method initializes jMenuItem
     * 
     * @return javax.swing.JMenuItem
     */
    private JMenuItem getJMenuItemConnectingDatabase() {
        if (jMenuItemConnectingDatabase == null) {
            try {
                jMenuItemConnectingDatabase = new JMenuItem();
                jMenuItemConnectingDatabase.setMnemonic('N');
                jMenuItemConnectingDatabase
                        .addMouseListener(new java.awt.event.MouseAdapter() {
                            public void mouseEntered(java.awt.event.MouseEvent e) {
                                getJLabelStatus().setText(ResourcesUtil.getProperty("menu.database.connecting.database.tips"));
                            }

                            public void mouseExited(java.awt.event.MouseEvent e) {
                                getJLabelStatus().setText(" ");
                            }
                        });
                jMenuItemConnectingDatabase
                        .addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(
                                    java.awt.event.ActionEvent e) {
                                getJLabelStatus().setText(" ");
                                IModel _model = ModelFactory.getConnectingDatabase();
                                if (_model == null) {
                                	performEmptyActionOfMenuItem(e);
                                } else {
                                    _model.run(MainJFrame.this);
                                }
                            }
                        });
                jMenuItemConnectingDatabase.setText(ResourcesUtil.getProperty("menu.database.connecting.database")); // Generated
            } catch (java.lang.Throwable e) {
                // TODO: Something
            }
        }
        return jMenuItemConnectingDatabase;
    }

    /**
     * This is the default constructor
     */
    public MainJFrame() {
        super();
        initialize();
        getJTextArea().setEnabled(false);
    }

    /**
     * This method initializes this
     * 
     * @return void
     */
    private void initialize() {
        this.setName("mainFrame"); // Generated
        this.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE); // Generated
        this.setJMenuBar(getJJMenuBar()); // Generated
        this.setSize(790, 444);
        this.setContentPane(getJContentPane());
        this.setTitle(ResourcesUtil.getProperty("main.title"));
    }

    /**
     * This method initializes jContentPane
     * 
     * @return javax.swing.JPanel
     */
    private javax.swing.JPanel getJContentPane() {
        if (jContentPane == null) {
            jContentPane = new javax.swing.JPanel();
            jContentPane.setLayout(new BorderLayout()); // Generated
            jContentPane.add(getJLabelStatus(), java.awt.BorderLayout.SOUTH); // Generated
            jContentPane.add(getJPanel(), java.awt.BorderLayout.CENTER);  // Generated
        }
        return jContentPane;
    }

    /**
     * This method initializes jMenuItem
     * 
     * @return javax.swing.JMenuItem
     */
    private JMenuItem getJMenuItemListTable() {
        if (jMenuItemListTable == null) {
            try {
                jMenuItemListTable = new JMenuItem();
                jMenuItemListTable.setMnemonic('L');
                jMenuItemListTable
                        .addMouseListener(new java.awt.event.MouseAdapter() {
                            public void mouseEntered(java.awt.event.MouseEvent e) {
                                getJLabelStatus().setText(ResourcesUtil.getProperty("menu.database.list.table.tips"));
                            }

                            public void mouseExited(java.awt.event.MouseEvent e) {
                                getJLabelStatus().setText(" ");
                            }
                        });
                jMenuItemListTable
                        .addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(
                                    java.awt.event.ActionEvent e) {
                                getJLabelStatus().setText(" ");
                                IModel _model = ModelFactory.getListTables();
                                if (_model == null) {
                                	performEmptyActionOfMenuItem(e);
                                } else {
                                    _model.run(MainJFrame.this);
                                }
                            }
                        });
                jMenuItemListTable.setText(ResourcesUtil.getProperty("menu.database.list.table")); // Generated
            } catch (java.lang.Throwable e) {
                // TODO: Something
            }
        }
        return jMenuItemListTable;
    }

    /**
     * This method initializes jMenuItem
     * 
     * @return javax.swing.JMenuItem
     */
    private JMenuItem getJMenuItemQuery() {
        if (jMenuItemQuery == null) {
            try {
                jMenuItemQuery = new JMenuItem();
                jMenuItemQuery.setMnemonic('Q');
                jMenuItemQuery
                        .addMouseListener(new java.awt.event.MouseAdapter() {
                            public void mouseEntered(java.awt.event.MouseEvent e) {
                                getJLabelStatus().setText(ResourcesUtil.getProperty("menu.database.query.tips"));
                            }

                            public void mouseExited(java.awt.event.MouseEvent e) {
                                getJLabelStatus().setText(" ");
                            }
                        });
                jMenuItemQuery
                        .addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(
                                    java.awt.event.ActionEvent e) {
                                getJLabelStatus().setText(" ");
                                IModel _model = ModelFactory.getQuery();
                                if (_model == null) {
                                	performEmptyActionOfMenuItem(e);
                                } else {
                                    _model.run(MainJFrame.this);
                                }
                            }
                        });
                jMenuItemQuery.setText(ResourcesUtil.getProperty("menu.database.query")); // Generated
            } catch (java.lang.Throwable e) {
                // TODO: Something
            }
        }
        return jMenuItemQuery;
    }

    private void performEmptyActionOfMenuItem(java.awt.event.ActionEvent e) {
        String _strActionID = ((JMenuItem) e.getSource()).getText();
        JOptionPane.showMessageDialog(null, ResourcesUtil.getProperty("message.warn.function.not.implement")
                + " (" + _strActionID + ")",
                ResourcesUtil.getProperty("warn.message.box.title"), JOptionPane.WARNING_MESSAGE);
    }
    public JLabel getJLabelStatus() {
        if (jLabelStatus == null) {
            jLabelStatus = new JLabel();
            jLabelStatus.setText(" ");
         }
        return jLabelStatus;
    }
	/**
	 * This method initializes jPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */    
	public JPanel getJPanel() {
		if (jPanel == null) {
			try {
				jPanel = new JPanel();
				jPanel.setLayout(new BorderLayout());
				jPanel.add(getJScrollPane(), BorderLayout.CENTER);  // Generated
			}
			catch (java.lang.Throwable e) {
				// TODO: Something
			}
		}
		return jPanel;
	}
 } //  @jve:decl-index=0:visual-constraint="94,26"

