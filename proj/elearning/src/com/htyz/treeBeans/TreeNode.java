
/**
 *  TreeNode.java
 */

package com.htyz.treeBeans;

import java.util.Vector;

public class TreeNode extends TreeObject implements TreeInterface {

    private String name;
    private String link;
    private boolean visible;
    private Tree children;
    private int id;

    public TreeNode( int id, String name ) {
        super(Tree.NODE);
        this.id = id;
        this.name = name;
        visible = false;
        children = new Tree();
    }
    public TreeNode( int id, String name, String link ) {
        super(Tree.NODE);
        this.id = id;
        this.name = name;
        this.link = link;
        visible = false;
        children = new Tree();
    }
    public void addChild(TreeObject child) {
        children.addChild(child);
    }
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getLink() {
        return link;
    }
    public Tree getChildren() {
        return children;
    }
    public boolean isVisible() {
        return visible;
    }
    public void setId( int id ) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setLink( String link ) {
        this.link = link;
    }
    public void setVisible(boolean value) {
        visible = value;
    }
    public void toggleVisible() {
        visible = !visible;
    }
}
