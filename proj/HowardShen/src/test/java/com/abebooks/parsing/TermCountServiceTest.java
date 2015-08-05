package com.abebooks.parsing;

import com.abebooks.data.License;
import com.abebooks.data.LicenseDAO;
import com.abebooks.data.TermDAO;
import org.junit.Test;
import org.unitils.UnitilsJUnit4;
import org.unitils.spring.annotation.SpringApplicationContext;
import org.unitils.spring.annotation.SpringBeanByType;

import java.util.Collection;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * AbeBooks Sample File
 */
@SpringApplicationContext({"classpath:applicationcontext.xml"})
public class TermCountServiceTest extends UnitilsJUnit4 {
    @SpringBeanByType
    TermDAO termDAO;
    @SpringBeanByType
    LicenseDAO licenseDAO;

    @Test
    public void countTermsIntegrationTest() {
        Collection<String> terms = termDAO.getTerms();
        Collection<License> licenses = licenseDAO.getLicenses();
        TermCountService termCountService = new TermCountServiceImpl();

        Map<String, Integer> topTerms = termCountService.getTopTerms(licenses, terms, 5);
        assertEquals(topTerms.size(), 5);
        assertEquals(topTerms.get("work"), new Integer(34));
        assertEquals(topTerms.get("copyright"), new Integer(20));
        assertEquals(topTerms.get("source"), new Integer(17));
        assertEquals(topTerms.get("software"), new Integer(14));
        assertEquals(topTerms.get("notice"), new Integer(13));
    }
}
