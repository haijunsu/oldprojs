package com.abebooks.data;

import org.junit.Test;
import org.unitils.UnitilsJUnit4;
import org.unitils.spring.annotation.SpringApplicationContext;
import org.unitils.spring.annotation.SpringBeanByType;

import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * AbeBooks Sample File
 */
@SpringApplicationContext({"classpath:applicationcontext.xml"})
public class LicenseDAOTest extends UnitilsJUnit4 {
    @SpringBeanByType
    LicenseDAO licenseDAO;

    @Test
    public void testLoadLicenses() {
        Collection<License> licenses = licenseDAO.getLicenses();
        assertNotNull(licenses);
        assertEquals(licenses.size(), 4);
    }
}
