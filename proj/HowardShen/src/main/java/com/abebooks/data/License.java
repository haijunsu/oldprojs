package com.abebooks.data;

/**
 * AbeBooks Sample File
 */
public class License {
    /**
     * License information can be available from several different locations. This enum describes where the license text
     * actually resides.
     */
    public enum Type {
        /**
         * license text exists inline in the database
         */
        INLINE,
        /**
         * license text is available at a network URL, and that URL is stored in the database
         */
        URL,
        /**
         * license text is available on the local filesystem and the path is stored in the database
         */
        FILE
    }

    private final String name;
    private final Type type;
    private final String text;

    /**
     * Construct a License object
     *
     * @param name the license's descriptive name
     * @param type enum describing where the license's text is stored
     * @param text actual license text
     */
    public License(String name, Type type, String text) {
        this.name = name;
        this.type = type;
        this.text = text;
    }

    public String getName() {
        return name;
    }

    public Type getType() {
        return type;
    }

    public String getText() {
        return text;
    }
}
