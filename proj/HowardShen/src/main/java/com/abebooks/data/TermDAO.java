package com.abebooks.data;

import java.util.Collection;

/**
 * Encapsulates the read/write of term information between their persistent format and POJOs
 * <p/>
 * AbeBooks Sample File
 */
public interface TermDAO {
    /**
     * @return Collection of all available Terms
     */
    public Collection<String> getTerms();
}
