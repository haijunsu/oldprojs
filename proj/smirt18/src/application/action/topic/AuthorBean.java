/*
 * @(#)AuthorBean.java  2005-1-20
 * Copyright (c) 2005. All rights reserved. 
 * 
 * $Header: /navysu/Smirt18/src/application/action/topic/AuthorBean.java,v 1.1 2005/01/20 03:14:22 navy Exp $
 * $Log: AuthorBean.java,v $
 * Revision 1.1  2005/01/20 03:14:22  navy
 * Create SMiRT 18 project
 *
 */
package application.action.topic;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import application.action.topic.jo.PaperInfo;
import application.action.topic.jo.UserProfile;
import application.exception.BusinessServiceException;
import framework.service.impl.JdbcServiceImpl;

/**
 * <p>
 * <b>Description </b>
 * </p>
 * <p>
 * </p>
 * 
 * $Revision: 1.1 $
 * 
 * @author su_haijun <a href=mailto:su_hj@126.com>su_hj@126.com </a>
 */
public class AuthorBean extends JdbcServiceImpl {
    /**
     * Logger for this class
     */
    private static final Log logger = LogFactory.getLog(AuthorBean.class);

    private JdbcTemplate m_jdbcTemplate;

    private final static String USER_TABLE_QUERY = "select intUserID as userKey,"
            + "chvUserAccount as email, chvFirstName as firstName, chvMiddleName as middleName, chvLastName as lastName,"
            + "chvJobTitle as jobTitle, chvAddress1 as address, chvCountry as country,"
            + "chvPhone as tel, chvFax as fax from T_UserAccount ";

    private final static String PAPER_TABLE_QUERY = "select chvPaperNumber as topicNo, intUserID as userKey, "
            + "chvFinalPaperName as topicFullText, chvPaperTitle as topicTitle from T_Paper ";

    private final static String COAUTHOR_TABLE_QUERY = "select chvPaperNumber as topicNo, "
            + "chvFirstName as firstName, chvMiddleName as middleName, chvLastName as lastName from T_Coauthor ";

    public AuthorBean() {
        this.m_jdbcTemplate = getJdbcTemplate();
    }

    public AuthorVO findByPaperNumber(String paperNumber) {
        //        if (logger.isDebugEnabled()) {
        //            logger.debug("findByPaperNumber(String) - start");
        //        }
        //
        //        List _rows = this.m_jdbcTemplate
        //        .queryForList(PAPER_TABLE_QUERY
        //                + "where chvPaperNumber = '" + paperNumber + "'");
        //        
        //        if (_rows.size() == 0) {
        //            if (logger.isDebugEnabled()) {
        //                logger.debug("findByPaperNumber(String) - query empty. end");
        //            }
        //            return null;
        //        }
        //        
        //        HashMap _mapAuthor = new HashMap();
        //        for (Iterator iter = _rows.iterator(); iter.hasNext();) {
        //            HashMap element = (HashMap) iter.next();
        //            _mapAuthor.putAll(element);
        //        }
        //        
        //        //Coauthors
        //        _rows = this.m_jdbcTemplate
        //        .queryForList(COAUTHOR_TABLE_QUERY
        //                + "where chvPaperNumber = '" + paperNumber + "'");
        //        String _coAuthors = "";
        //        for (Iterator iter = _rows.iterator(); iter.hasNext();) {
        //            HashMap element = (HashMap) iter.next();
        //            if (element.get("firstName") != null)
        //                _coAuthors += (String) element.get("firstName");
        //            if (element.get("middleName") != null)
        //                _coAuthors += (String) element.get("middleName");
        //            if (element.get("lastName") != null)
        //                _coAuthors += (String) element.get("lastName");
        //            _coAuthors += ", ";
        //        }
        //        if (_coAuthors.endsWith(", "))
        //            _coAuthors = _coAuthors.substring(0, _coAuthors.length() - 2);
        //        _mapAuthor.put("topicOtherAuthor", _coAuthors);
        //        
        //        //user account
        //        AuthorVO _author = new AuthorVO();
        //        try {
        //            BeanUtils.copyProperties(_author, _mapAuthor);
        //        } catch (IllegalAccessException e) {
        //            logger.error("findByPaperNumber(String)", e);
        //        } catch (InvocationTargetException e) {
        //            logger.error("findByPaperNumber(String)", e);
        //        }
        //        
        //        _author = findByUserTableKey(_author.getUserProfile().getUserKey());
        //        try {
        //            BeanUtils.copyProperties(_author, _mapAuthor);
        //        } catch (IllegalAccessException e) {
        //            logger.error("findByPaperNumber(String)", e);
        //        } catch (InvocationTargetException e) {
        //            logger.error("findByPaperNumber(String)", e);
        //        }
        //        
        //        if (logger.isDebugEnabled()) {
        //            logger.debug("findByPaperNumber(String) - end");
        //        }
        //        return _author;
        return null;
    }

    public AuthorVO[] findByVisa(String visa) {
        if (logger.isDebugEnabled()) {
            logger.debug("findByVisa(String) - start");
        }

        AuthorVO[] _author = new AuthorVO[0];

        if (logger.isDebugEnabled()) {
            logger.debug("findByVisa(String) - end");
        }
        return _author;
    }

    public AuthorVO[] findByEmail(String email) {
        if (logger.isDebugEnabled()) {
            logger.debug("findByEmail(String) - start");
        }

        List _rows = this.m_jdbcTemplate.queryForList(USER_TABLE_QUERY
                + "where chvUserAccount like '%" + email + "%'");
        if (logger.isDebugEnabled()) {
            logger.debug("findByEmail(String) - rows = " + _rows.size());
        }
        if (_rows.size() == 0) {
            if (logger.isDebugEnabled()) {
                logger.debug("findByEmail(String) - end");
            }
            throw new BusinessServiceException(120,
                    "can't find author by email");
        }

        AuthorVO[] _authors = listToAuthorVOs(_rows);
        if (logger.isDebugEnabled()) {
            logger.debug("findByEmail(String) - end");
        }
        return _authors;
    }

    public AuthorVO[] findByFirstName(String firstName) {
        if (logger.isDebugEnabled()) {
            logger.debug("findByFirstName(String) - start");
        }
        List _rows = this.m_jdbcTemplate.queryForList(USER_TABLE_QUERY
                + "where chvFirstName like '%" + firstName + "%'");
        AuthorVO[] _authors = listToAuthorVOs(_rows);

        if (logger.isDebugEnabled()) {
            logger.debug("findByFirstName(String) - end");
        }
        return _authors;
    }

    public AuthorVO[] findByLastName(String lastName) {
        if (logger.isDebugEnabled()) {
            logger.debug("findByLastName(String) - start");
        }

        List _rows = this.m_jdbcTemplate.queryForList(USER_TABLE_QUERY
                + "where chvLastName like '%" + lastName + "%'");
        AuthorVO[] _authors = listToAuthorVOs(_rows);

        if (logger.isDebugEnabled()) {
            logger.debug("findByLastName(String) - end");
        }
        return _authors;
    }

    public AuthorVO[] findByPaperName(String paperName) {
        if (logger.isDebugEnabled()) {
            logger.debug("findByPaperName(String) - start");
        }

        AuthorVO[] _authors = null;

        if (logger.isDebugEnabled()) {
            logger.debug("findByPaperName(String) - end");
        }
        return _authors;
    }

    public AuthorVO findByUserTableKey(String key) {
        List _rows = this.m_jdbcTemplate.queryForList(USER_TABLE_QUERY
                + "where intUserID = " + key + "");
        if (_rows.size() == 0) {
            throw new BusinessServiceException(120,
                    "can't find author by user table key.");
        }
        AuthorVO[] _authors = listToAuthorVOs(_rows);
        return _authors[0];
    }

    public void update(AuthorVO author) {
        if (logger.isDebugEnabled()) {
            logger.debug("update(AuthorVO) - start");
        }

        if (logger.isDebugEnabled()) {
            logger.debug("update(AuthorVO) - end");
        }
    }

    private AuthorVO[] listToAuthorVOs(List list) {
        if (logger.isDebugEnabled()) {
            logger.debug("listToAuthorVOs(List) - start");
        }

        AuthorVO[] _authors = new AuthorVO[list.size()];
        UserProfile[] _userProfiles = new UserProfile[list.size()];
        int _i = 0;
        for (Iterator iter = list.iterator(); iter.hasNext();) {
            HashMap _hm = (HashMap) iter.next();
            _authors[_i] = new AuthorVO();
            _userProfiles[_i] = new UserProfile();
            try {
                BeanUtils.copyProperties(_userProfiles[_i], _hm);
                _authors[_i].setUserProfile(_userProfiles[_i]);
                _authors[_i].setPaperInfos(findPapersByUserKey(_userProfiles[_i]
                        .getUserKey()));
//                if (logger.isDebugEnabled()) {
//                    logger.debug("listToAuthorVOs(List) - "
//                            + _authors[_i].getPaperInfos()[0].getTopicTitle());
//                }

            } catch (IllegalAccessException e) {
                logger.error("listToAuthorVOs(List)", e);
                throw new BusinessServiceException(122,
                        "copy properties to userProfile fail.");
            } catch (InvocationTargetException e) {
                logger.error("listToAuthorVOs(List)", e);
                throw new BusinessServiceException(122,
                        "copy properties to userProfile fail.");
            }
            _i++;
        }

        if (logger.isDebugEnabled()) {
            logger.debug("listToAuthorVOs(List) - end");
        }
        return _authors;
    }

    public PaperInfo[] findPapersByUserKey(String userKey) {
        if (logger.isDebugEnabled()) {
            logger.debug("findPapersByUserKey(String) - start, userKey = "
                    + userKey);
        }

        List _rows = this.m_jdbcTemplate.queryForList(PAPER_TABLE_QUERY
                + "where intUserID = " + userKey);

        if (logger.isDebugEnabled()) {
            logger
                    .debug("findPapersByUserKey() - Query result count: : _rows = "
                            + _rows.size());
        }

        PaperInfo[] _paperInfoArray = listToPaperInfos(_rows);
        if (logger.isDebugEnabled()) {
            logger.debug("findPapersByUserKey(String) - end, find papers total = " + _paperInfoArray.length);
        }
        return _paperInfoArray;
    }

    private PaperInfo[] listToPaperInfos(List list) {
        PaperInfo[] _papers = new PaperInfo[list.size()];
        int _i = 0;
        for (Iterator iter = list.iterator(); iter.hasNext();) {
            HashMap _hm = (HashMap) iter.next();
            _papers[_i] = new PaperInfo();
            try {
                BeanUtils.copyProperties(_papers[_i], _hm);
            } catch (IllegalAccessException e) {
                logger.error("listToPaperInfos(List)", e);
                throw new BusinessServiceException(122,
                        "copy properties to paperInfo fail.");
            } catch (InvocationTargetException e) {
                logger.error("listToPaperInfos(List)", e);
                throw new BusinessServiceException(122,
                        "copy properties to paperInfo fail.");
            }
            _i++;

        }
        return _papers;
    }

}