
/**
 *  TreeLeaf.java
 */

package com.htyz.treeBeans;

public class TreeLeaf extends TreeObject {

    private int id;
    private String name;
    private String link;

    public TreeLeaf( String name, String link ) {
        super(Tree.LEAF);
        this.id = id;
        this.name = name;
        this.link = link;
    }

    public int getId() {
        return this.id;
    }
    public void setId( int id ) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public String getLink() {
        return link;
    }
} 
