/*
 * @(#)SmallPaperView.java  2005-2-21
 * Copyright (c) 2005. All rights reserved. 
 * 
 * $Header$
 * $Log$
 */
package application.viewdata;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.struts.util.MessageResources;

import application.helper.ResourcesHelper;
import framework.util.StringUtil;
import framework.util.http.HtmlUtil;

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
public class SmallPaperView implements Serializable {

    private MessageResources m_messageResources;

    private String chvPaperNumber;

    private String chvPaperTitle;

    private String chrStatus;

    private String txtAbstract;

    private String chvAbstractName;

    private String chvFinalPaperName;

    private Boolean bitIsMailCopyright;

    private Boolean bitIsMailOrgAbstract;

    private Boolean bitIsMailOrgPaper;

    private Boolean bitIsMailPaperReg;

    private Boolean bitIsRegisted;

    private String chrPaperType;

    private Integer inyAuthorRank;

    private String chvPaperKeyword;

    private Boolean bitIsJuniorAward;

    private Boolean bitIsPaperPrize;

    private String chvPaperLNumber;

    private Integer intUserID;

    private String chrFirstDivisionNo;

    private String chrDivisionNo;

    private String chrSessionNo;

    public SmallPaperView() {
        super();
        this.m_messageResources = ResourcesHelper.getFormsResources();
    }

    public String getStatusDescription() {
        try {
            String _str = this.m_messageResources
                    .getMessage("form.paper.status." + getChrStatus().trim());
            return _str;
        } catch (Exception e) {
            return "";
        }

    }

    public String getChrSessionNo() {
        return this.chrSessionNo;
    }

    public void setChrSessionNo(String chrSessionNo) {
        this.chrSessionNo = chrSessionNo;
    }

    public Boolean getBitIsJuniorAward() {
        return this.bitIsJuniorAward;
    }

    public void setBitIsJuniorAward(Boolean bitIsJuniorAward) {
        this.bitIsJuniorAward = bitIsJuniorAward;
    }

    public Boolean getBitIsMailCopyright() {
        return this.bitIsMailCopyright;
    }

    public void setBitIsMailCopyright(Boolean bitIsMailCopyright) {
        this.bitIsMailCopyright = bitIsMailCopyright;
    }

    public Boolean getBitIsMailOrgAbstract() {
        return this.bitIsMailOrgAbstract;
    }

    public void setBitIsMailOrgAbstract(Boolean bitIsMailOrgAbstract) {
        this.bitIsMailOrgAbstract = bitIsMailOrgAbstract;
    }

    public Boolean getBitIsMailOrgPaper() {
        return this.bitIsMailOrgPaper;
    }

    public void setBitIsMailOrgPaper(Boolean bitIsMailOrgPaper) {
        this.bitIsMailOrgPaper = bitIsMailOrgPaper;
    }

    public Boolean getBitIsMailPaperReg() {
        return this.bitIsMailPaperReg;
    }

    public void setBitIsMailPaperReg(Boolean bitIsMailPaperReg) {
        this.bitIsMailPaperReg = bitIsMailPaperReg;
    }

    public Boolean getBitIsPaperPrize() {
        return this.bitIsPaperPrize;
    }

    public void setBitIsPaperPrize(Boolean bitIsPaperPrize) {
        this.bitIsPaperPrize = bitIsPaperPrize;
    }

    public Boolean getBitIsRegisted() {
        return this.bitIsRegisted;
    }

    public void setBitIsRegisted(Boolean bitIsRegisted) {
        this.bitIsRegisted = bitIsRegisted;
    }

    public String getChrDivisionNo() {
        return this.chrDivisionNo;
    }

    public void setChrDivisionNo(String chrDivisionNo) {
        this.chrDivisionNo = chrDivisionNo;
    }

    public String getChrFirstDivisionNo() {
        return this.chrFirstDivisionNo;
    }

    public void setChrFirstDivisionNo(String chrFirstDivisionNo) {
        this.chrFirstDivisionNo = chrFirstDivisionNo;
    }

    public String getChrPaperType() {
        return this.chrPaperType;
    }

    public void setChrPaperType(String chrPaperType) {
        this.chrPaperType = chrPaperType;
    }

    public String getChrStatus() {
        return this.chrStatus;
    }

    public void setChrStatus(String chrStatus) {
        this.chrStatus = chrStatus;
    }

    public String getChvAbstractName() {
        return this.chvAbstractName;
    }

    public void setChvAbstractName(String chvAbstractName) {
        this.chvAbstractName = chvAbstractName;
    }

    public String getChvFinalPaperName() {
        return this.chvFinalPaperName;
    }

    public void setChvFinalPaperName(String chvFinalPaperName) {
        this.chvFinalPaperName = chvFinalPaperName;
    }

    public String getChvPaperKeyword() {
        return this.chvPaperKeyword;
    }

    public void setChvPaperKeyword(String chvPaperKeyword) {
        this.chvPaperKeyword = chvPaperKeyword;
    }

    public String getChvPaperLNumber() {
        return this.chvPaperLNumber;
    }

    public void setChvPaperLNumber(String chvPaperLNumber) {
        this.chvPaperLNumber = chvPaperLNumber;
    }

    public String getChvPaperNumber() {
        return this.chvPaperNumber;
    }

    public void setChvPaperNumber(String chvPaperNumber) {
        this.chvPaperNumber = chvPaperNumber;
    }

    public String getChvPaperTitle() {
        return this.chvPaperTitle;
    }

    public void setChvPaperTitle(String chvPaperTitle) {
        this.chvPaperTitle = chvPaperTitle;
    }

    public Integer getIntUserID() {
        return this.intUserID;
    }

    public void setIntUserID(Integer intUserID) {
        this.intUserID = intUserID;
    }

    public Integer getInyAuthorRank() {
        return this.inyAuthorRank;
    }

    public void setInyAuthorRank(Integer inyAuthorRank) {
        this.inyAuthorRank = inyAuthorRank;
    }

    public String getTxtAbstract() {
        return StringUtil.abbreviate(this.txtAbstract, 1000);
    }
    
    public String getTxtAbstractAll() {
        return this.txtAbstract;
    }

    public String getTxtAbstractHtml() {
        return HtmlUtil.htmlFormat(getTxtAbstract());
    }

    public void setTxtAbstract(String txtAbstract) {
        this.txtAbstract = txtAbstract;
    }

    public boolean equals(Object other) {
        if (!(other instanceof SmallPaperView))
            return false;
        SmallPaperView castOther = (SmallPaperView) other;
        return new EqualsBuilder().append(this.getChvPaperNumber(),
                castOther.getChvPaperNumber()).isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder().append(getChvPaperNumber()).toHashCode();
    }

    public String toString() {
        return new ToStringBuilder(this).append("getChvPaperNumber",
                getChvPaperNumber()).toString();
    }

}