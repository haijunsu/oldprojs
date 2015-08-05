/*
 * @(#)PaperView.java  2005-2-22
 * Copyright (c) 2005. All rights reserved. 
 * 
 * $Header$
 * $Log$
 */
package application.viewdata;

import java.io.Serializable;

import framework.util.StringUtil;

import application.entities.TpaperPresented;
import application.util.ServicesUtil;

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
public class PaperView implements Serializable {

    private SmallPaperView m_smallPaperView;

    private SmallAuthorView[] m_authors;

    private TpaperPresented m_paperPresented;
    
    private Boolean bitIsCheckin;
    
    private Boolean bitIsBiography;

    /**
     *  
     */
    public PaperView() {
        this.m_authors = new SmallAuthorView[0];
        this.m_smallPaperView = new SmallPaperView();
        this.m_paperPresented = new TpaperPresented();
    }

    public SmallAuthorView[] getAuthors() {
        return this.m_authors;
    }

    public void setAuthors(SmallAuthorView[] authors) {
        this.m_authors = authors;
    }

    public TpaperPresented getPaperPresented() {
        return this.m_paperPresented;
    }

    public void setPaperPresented(TpaperPresented paperPresented) {
        this.m_paperPresented = paperPresented;
    }

    public SmallPaperView getSmallPaperView() {
        return this.m_smallPaperView;
    }

    public void setSmallPaperView(SmallPaperView smallPaperView) {
        this.m_smallPaperView = smallPaperView;
    }

    public String getPaperPresentedName() {
        String _str = "";
        if (StringUtil.isNotBlank(getPaperPresented()
                .getChvPresenterFirstName())) {
            _str = getPaperPresented().getChvPresenterFirstName().trim() + " ";
        }

        if (StringUtil.isNotBlank(getPaperPresented()
                .getChvPresenterMiddleName())) {
            _str = _str
                    + getPaperPresented().getChvPresenterMiddleName().trim()
                    + " ";
        }
        if (StringUtil
                .isNotBlank(getPaperPresented().getChvPresenterLastName())) {
            _str = _str + getPaperPresented().getChvPresenterLastName().trim()
                    + " ";
        }
        return _str.trim();
    }

    public String getEquipementName() {
        return ServicesUtil.getEquipmentName(getPaperPresented()
                .getChrEquipmentNo());
    }
    
    
    public Boolean getBitIsBiography() {
        return bitIsBiography;
    }
    public void setBitIsBiography(Boolean bitIsBiography) {
        this.bitIsBiography = bitIsBiography;
    }
    public Boolean getBitIsCheckin() {
        return bitIsCheckin;
    }
    public void setBitIsCheckin(Boolean bitIsCheckin) {
        this.bitIsCheckin = bitIsCheckin;
    }
}