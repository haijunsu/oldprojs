/*
 * @(#)RegistrationView.java  2005-2-21
 * Copyright (c) 2005. All rights reserved. 
 * 
 * $Header$
 * $Log$
 */
package application.viewdata;

import java.io.Serializable;

import application.entities.Tparticipant;

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
public class RegistrationView implements Serializable {
    private Tparticipant m_participant;

    private AccompanyPersonView[] m_accompanyPerson;

    private SmallPaperView[] m_papers;

    /**
     *  
     */
    public RegistrationView() {
        this.m_papers = new SmallPaperView[0];
        this.m_participant = new Tparticipant();
        this.m_accompanyPerson = new AccompanyPersonView[0];
    }

    public AccompanyPersonView[] getAccompanyPerson() {
        return this.m_accompanyPerson;
    }

    public void setAccompanyPerson(AccompanyPersonView[] accompanyPerson) {
        this.m_accompanyPerson = accompanyPerson;
    }

    public SmallPaperView[] getPapers() {
        return this.m_papers;
    }

    public void setPapers(SmallPaperView[] papers) {
        this.m_papers = papers;
    }

    public Tparticipant getParticipant() {
        return this.m_participant;
    }

    public void setParticipant(Tparticipant participant) {
        this.m_participant = participant;
    }
}