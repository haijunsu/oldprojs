package com.abebooks.data;

import java.util.Collection;

/**
 * Encapsulates the read/write of license information between their persistent format and POJOs
 * <p/>
 * AbeBooks Sample File
 */
public interface LicenseDAO {
    /**
     * @return Collection of all available licenses
     */
    public Collection<License> getLicenses();
}
