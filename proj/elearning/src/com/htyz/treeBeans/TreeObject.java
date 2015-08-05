
/**
 *  TreeObject.java
 */

package com.htyz.treeBeans;

public abstract class TreeObject {

    private int type;

    public TreeObject(int type) {
        this.type = type;
    }
    public int getType() {
        return type;
    }
    public abstract String getName();
} 