/*
 * @(#)EntitiesTransUtil.java  2005-2-27
 * Copyright (c) 2005. All rights reserved. 
 * 
 * $Header$
 * $Log$
 */
package application.util;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import application.entities.Tauthor;
import application.entities.Tcoauthor;
import application.entities.Tparticipant;
import application.entities.TrelationBetweenParticipantAndPaper;
import application.entities.TuserAccount;
import application.viewdata.SmallAuthorView;
import application.viewdata.SmallPaperView;
import application.viewdata.SmallUserView;
import application.viewdata.VisaView;

/**
 * <p>
 * <b>Description </b>
 * </p>
 * <p>
 * </p>
 * 
 * $Revision$
 * 
 * @author su_haijun <a href=mailto:su_hj@126.com>su_hj@126.com </a>
 */
public class EntitiesTransUtil {
    /**
     * Logger for this class
     */
    private static final Log logger = LogFactory
            .getLog(EntitiesTransUtil.class);

    public static Tparticipant authorTransToParticipant(
            Tauthor userAccount) {
        if (logger.isDebugEnabled()) {
            logger.debug("userAccountTransToParticipant(TuserAccount) - start");
        }

        Tparticipant _participant = new Tparticipant();
        _participant.setChrAuthorNo(userAccount.getChrAuthorNo());
        _participant.setChvPartiTitle(userAccount.getChvJobTitle());
        _participant.setChvPartiFirstName(userAccount.getChvFirstName());
        _participant.setChvPartiMiddleName(userAccount.getChvMiddleName());
        _participant.setChvPartiLastName(userAccount.getChvLastName());
        _participant.setChvPartiAffiliation(userAccount.getChvAffiliation());
        _participant.setChvPartiPosition(userAccount.getChvPosition());
        _participant.setChvPartiDepartment(userAccount.getChvDepartment());
        _participant
                .setChvPartiAddress((userAccount.getChvAddress1() + " " + userAccount
                        .getChvAddress2()).trim());
        _participant.setChvPartiCity(userAccount.getChvCity());
        _participant.setChvPartiCountry(userAccount.getChrCountryNo());
        _participant.setChvPartiProvince(userAccount.getChvProvince());
        _participant.setChvPartiPostalCode(userAccount.getChvPostalCode());
        _participant.setChvPartiTeleNumber(userAccount.getChvPhone());
        _participant.setChvPartiFax(userAccount.getChvFax());
        _participant.setChvPartiEmail(userAccount.getChvUserAccount());
        _participant.setBitIsPartiIsMember(userAccount.getBitIsMember());

        if (logger.isDebugEnabled()) {
            logger.debug("userAccountTransToParticipant(TuserAccount) - end");
        }
        return _participant;
    }

    public static Tparticipant userAccountTransToParticipant(
            TuserAccount userAccount) {
        if (logger.isDebugEnabled()) {
            logger.debug("userAccountTransToParticipant(TuserAccount) - start");
        }

        Tparticipant _participant = new Tparticipant();
        _participant.setChvPartiTitle(userAccount.getChvJobTitle());
        _participant.setChvPartiFirstName(userAccount.getChvFirstName());
        _participant.setChvPartiMiddleName(userAccount.getChvMiddleName());
        _participant.setChvPartiLastName(userAccount.getChvLastName());
        _participant.setChvPartiAffiliation(userAccount.getChvAffiliation());
        _participant.setChvPartiPosition(userAccount.getChvPosition());
        _participant.setChvPartiDepartment(userAccount.getChvDepartment());
        _participant
                .setChvPartiAddress((userAccount.getChvAddress1() + " " + userAccount
                        .getChvAddress2()).trim());
        _participant.setChvPartiCity(userAccount.getChvCity());
        _participant.setChvPartiCountry(userAccount.getChrCountryNo());
        _participant.setChvPartiProvince(userAccount.getChvProvince());
        _participant.setChvPartiPostalCode(userAccount.getChvPostalCode());
        _participant.setChvPartiTeleNumber(userAccount.getChvPhone());
        _participant.setChvPartiFax(userAccount.getChvFax());
        _participant.setChvPartiEmail(userAccount.getChvUserAccount());
        _participant.setBitIsPartiIsMember(userAccount.getBitIsMember());

        if (logger.isDebugEnabled()) {
            logger.debug("userAccountTransToParticipant(TuserAccount) - end");
        }
        return _participant;
    }

    public static Tparticipant coauthorTransToParticipant(Tcoauthor coauthor) {
        if (logger.isDebugEnabled()) {
            logger.debug("coauthorTransToParticipant(Tcoauthor) - start");
        }

        Tparticipant _participant = new Tparticipant();
        _participant.setChvPartiTitle(coauthor.getChvJobTitle());
        _participant.setChvPartiFirstName(coauthor.getChvFirstName());
        _participant.setChvPartiMiddleName(coauthor.getChvMiddleName());
        _participant.setChvPartiLastName(coauthor.getChvLastName());
        _participant.setChvPartiAffiliation(coauthor.getChvAffiliation());
        _participant.setChvPartiPosition(coauthor.getChvPosition());
        _participant.setChvPartiDepartment(coauthor.getChvDepartment());
        _participant
                .setChvPartiAddress((coauthor.getChvAddress1() + " " + coauthor
                        .getChvAddress2()).trim());
        _participant.setChvPartiCity(coauthor.getChvCity());
        _participant.setChvPartiCountry(coauthor.getChrCountryNo());
        _participant.setChvPartiProvince(coauthor.getChvProvince());
        _participant.setChvPartiPostalCode(coauthor.getChvPostalCode());
        _participant.setChvPartiTeleNumber(coauthor.getChvPhone());
        _participant.setChvPartiFax(coauthor.getChvFax());
        _participant.setChvPartiEmail(coauthor.getChvCoauthorEmail());
        _participant.setBitIsPartiIsMember(coauthor.getBitIsMember());

        if (logger.isDebugEnabled()) {
            logger.debug("coauthorTransToParticipant(Tcoauthor) - end");
        }
        return _participant;
    }

    public static SmallUserView[] removeDuplicateArray(SmallUserView[] objArray) {
        List _list = Arrays.asList(objArray);
        Set _set = new LinkedHashSet(_list);
        SmallUserView[] _objArray = (SmallUserView[]) _set
                .toArray(new SmallUserView[_set.size()]);
        return _objArray;
    }

    public static SmallAuthorView[] removeDuplicateArray(
            SmallAuthorView[] objArray) {
        List _list = Arrays.asList(objArray);
        Set _set = new LinkedHashSet(_list);
        SmallAuthorView[] _objArray = (SmallAuthorView[]) _set
                .toArray(new SmallAuthorView[_set.size()]);
        return _objArray;
    }

    public static SmallPaperView[] removeDuplicateArray(
            SmallPaperView[] objArray) {
        List _list = Arrays.asList(objArray);
        Set _set = new LinkedHashSet(_list);
        SmallPaperView[] _objArray = (SmallPaperView[]) _set
                .toArray(new SmallPaperView[_set.size()]);
        return _objArray;
    }

    public static TrelationBetweenParticipantAndPaper[] removeDuplicateArray(
            TrelationBetweenParticipantAndPaper[] objArray) {
        List _list = Arrays.asList(objArray);
        Set _set = new LinkedHashSet(_list);
        TrelationBetweenParticipantAndPaper[] _objArray = (TrelationBetweenParticipantAndPaper[]) _set
                .toArray(new TrelationBetweenParticipantAndPaper[_set.size()]);
        return _objArray;
    }

    public static VisaView[] removeDuplicateArray(VisaView[] objArray) {
        List _list = Arrays.asList(objArray);
        Set _set = new LinkedHashSet(_list);
        VisaView[] _objArray = (VisaView[]) _set.toArray(new VisaView[_set
                .size()]);
        return _objArray;
    }

}