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
public class TermDAOTest extends UnitilsJUnit4 {

    @SpringBeanByType
    TermDAO termDAO;

    @Test
    public void testLoadTerms() {
        Collection<String> terms = termDAO.getTerms();
        assertNotNull(terms);
        assertEquals(terms.size(), 24);
    }
}
