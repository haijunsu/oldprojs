/*
 * @(#)PagerInfo.java  2005-1-25
 * Copyright (c) 2005. All rights reserved. 
 * 
 * $Header$
 * $Log$
 */
package application.action.topic.jo;

import java.io.Serializable;

/**
 * <p><b>Description</b></p>
 * <p></p>
 * 
 * $Revision$
 * @author su_haijun  <a href=mailto:su_hj@126.com>su_hj@126.com</a>
 */
public class PaperInfo implements Serializable {

    private String m_topicNo;

    private String m_topicHardCopy;

    private String m_topicFullText;

    private String m_topicFee;
    
    private String m_topicFeeType;

    private String m_topicAuthorization;

    private String m_topicTitle;

    private String m_topicOtherAuthor;

    private String m_topicIsReport;

    private String m_topicReportAuthor;

    private String m_authorResume;

    private String m_projectionRequire;

    private String m_topicDemo;

    private String m_enjoyPerferentialAuthor;

    
    public String getAuthorResume() {
        return this.m_authorResume;
    }
    public void setAuthorResume(String authorResume) {
        this.m_authorResume = authorResume;
    }
    public String getEnjoyPerferentialAuthor() {
        return this.m_enjoyPerferentialAuthor;
    }
    public void setEnjoyPerferentialAuthor(String enjoyPerferentialAuthor) {
        this.m_enjoyPerferentialAuthor = enjoyPerferentialAuthor;
    }
    public String getProjectionRequire() {
        return this.m_projectionRequire;
    }
    public void setProjectionRequire(String projectionRequire) {
        this.m_projectionRequire = projectionRequire;
    }
    public String getTopicAuthorization() {
        return this.m_topicAuthorization;
    }
    public void setTopicAuthorization(String topicAuthorization) {
        this.m_topicAuthorization = topicAuthorization;
    }
    public String getTopicDemo() {
        return this.m_topicDemo;
    }
    public void setTopicDemo(String topicDemo) {
        this.m_topicDemo = topicDemo;
    }
    public String getTopicFee() {
        return this.m_topicFee;
    }
    public void setTopicFee(String topicFee) {
        this.m_topicFee = topicFee;
    }
    public String getTopicFeeType() {
        return this.m_topicFeeType;
    }
    public void setTopicFeeType(String topicFeeType) {
        this.m_topicFeeType = topicFeeType;
    }
    public String getTopicFullText() {
        return this.m_topicFullText;
    }
    public void setTopicFullText(String topicFullText) {
        this.m_topicFullText = topicFullText;
    }
    public String getTopicHardCopy() {
        return this.m_topicHardCopy;
    }
    public void setTopicHardCopy(String topicHardCopy) {
        this.m_topicHardCopy = topicHardCopy;
    }
    public String getTopicIsReport() {
        return this.m_topicIsReport;
    }
    public void setTopicIsReport(String topicIsReport) {
        this.m_topicIsReport = topicIsReport;
    }
    public String getTopicNo() {
        return this.m_topicNo;
    }
    public void setTopicNo(String topicNo) {
        this.m_topicNo = topicNo;
    }
    public String getTopicOtherAuthor() {
        return this.m_topicOtherAuthor;
    }
    public void setTopicOtherAuthor(String topicOtherAuthor) {
        this.m_topicOtherAuthor = topicOtherAuthor;
    }
    public String getTopicReportAuthor() {
        return this.m_topicReportAuthor;
    }
    public void setTopicReportAuthor(String topicReportAuthor) {
        this.m_topicReportAuthor = topicReportAuthor;
    }
    public String getTopicTitle() {
        return this.m_topicTitle;
    }
    public void setTopicTitle(String topicTitle) {
        this.m_topicTitle = topicTitle;
    }
}
