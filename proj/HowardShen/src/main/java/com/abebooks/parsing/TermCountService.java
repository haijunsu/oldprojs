package com.abebooks.parsing;

import com.abebooks.data.License;

import java.util.Collection;
import java.util.Map;

/**
 * Service interface for performing the actual aggregation of top terms within a set of licenses
 * <p/>
 * AbeBooks Sample File
 */
public interface TermCountService {
    /**
     * Parses the passed in files using the terms provided. If any term key appears in any of the files, it will be
     * counted against the term key. A map of the top terms by count of appearances in the files will be returned.
     *
     * @param licenses the Licenses who's text will be checked for terms from the thesaurus
     * @param terms    the collection of terms to check for in the files.
     * @param count    the number of elements to be returned in the result Map
     * @return a ordered Map who's size will be the count passed in, mapping the term keys to the number of times
     *         a term term (or synonym) appears in the files
     */
    public Map<String, Integer> getTopTerms(Collection<License> licenses, Collection<String> terms, int count);
}
