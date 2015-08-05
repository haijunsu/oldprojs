package application.entities;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** 
 *        @hibernate.class
 *         table="T_Paper"
 *     
*/
public class Tpaper implements Serializable {

    /** identifier field */
    private String chvPaperNumber;

    /** persistent field */
    private String chvPaperTitle;

    /** persistent field */
    private String chrStatus;

    /** nullable persistent field */
    private String txtAbstract;

    /** nullable persistent field */
    private byte[] imgAbstractDoc;

    /** nullable persistent field */
    private byte[] imgAbstractRtf;

    /** nullable persistent field */
    private byte[] imgAbstractPdf;

    /** nullable persistent field */
    private String dtmAbstractTime;

    /** nullable persistent field */
    private String chvAbstractName;

    /** nullable persistent field */
    private byte[] imgDraftPaperDoc;

    /** nullable persistent field */
    private byte[] imgDraftPaperRtf;

    /** nullable persistent field */
    private byte[] imgDraftPaperPdf;

    /** nullable persistent field */
    private String dtmDraftPaperTime;

    /** nullable persistent field */
    private String chvDraftPaperName;

    /** nullable persistent field */
    private byte[] imgFinalPaperDoc;

    /** nullable persistent field */
    private byte[] imgFinalPaperPdf;

    /** nullable persistent field */
    private byte[] imgFinalPaperRtf;

    /** nullable persistent field */
    private String dtmFinalPaperTime;

    /** nullable persistent field */
    private String chvFinalPaperName;

    /** nullable persistent field */
    private String txtMemo;

    /** nullable persistent field */
    private Boolean bitIsMailCopyright;

    /** nullable persistent field */
    private Boolean bitIsMailOrgAbstract;

    /** nullable persistent field */
    private Boolean bitIsMailOrgPaper;

    /** nullable persistent field */
    private Boolean bitIsMailPaperReg;

    /** nullable persistent field */
    private Boolean bitIsRegisted;

    /** nullable persistent field */
    private String chrPaperType;

    /** nullable persistent field */
    private String dtmPaperTime;

    /** nullable persistent field */
    private String chvPaperCode;

    /** nullable persistent field */
    private Integer inyAuthorRank;

    /** nullable persistent field */
    private String chvPaperKeyword;

    /** nullable persistent field */
    private Boolean bitIsJuniorAward;

    /** nullable persistent field */
    private Boolean bitIsPaperPrize;

    /** nullable persistent field */
    private String chvPaperLNumber;

    /** nullable persistent field */
    private String chrSessionNo;

    /** nullable persistent field */
    private Boolean bitIsAddPresent;
    
    private Integer intUserID;
    private String chrFirstDivisionNo;
    private String chrSecondDivisionNo;
    private String chrThirdDivisionNo;
    private String chrDivisionNo;
    

    /** full constructor */
    public Tpaper(String chvPaperNumber, String chvPaperTitle, String chrStatus, String txtAbstract, byte[] imgAbstractDoc, byte[] imgAbstractRtf, byte[] imgAbstractPdf, String dtmAbstractTime, String chvAbstractName, byte[] imgDraftPaperDoc, byte[] imgDraftPaperRtf, byte[] imgDraftPaperPdf, String dtmDraftPaperTime, String chvDraftPaperName, byte[] imgFinalPaperDoc, byte[] imgFinalPaperPdf, byte[] imgFinalPaperRtf, String dtmFinalPaperTime, String chvFinalPaperName, String txtMemo, Boolean bitIsMailCopyright, Boolean bitIsMailOrgAbstract, Boolean bitIsMailOrgPaper, Boolean bitIsMailPaperReg, Boolean bitIsRegisted, String chrPaperType, String dtmPaperTime, String chvPaperCode, Integer inyAuthorRank, String chvPaperKeyword, Boolean bitIsJuniorAward, Boolean bitIsPaperPrize, String chvPaperLnumber, String chrSessionNo, Boolean bitIsAddPresent) {
        this.chvPaperNumber = chvPaperNumber;
        this.chvPaperTitle = chvPaperTitle;
        this.chrStatus = chrStatus;
        this.txtAbstract = txtAbstract;
        this.imgAbstractDoc = imgAbstractDoc;
        this.imgAbstractRtf = imgAbstractRtf;
        this.imgAbstractPdf = imgAbstractPdf;
        this.dtmAbstractTime = dtmAbstractTime;
        this.chvAbstractName = chvAbstractName;
        this.imgDraftPaperDoc = imgDraftPaperDoc;
        this.imgDraftPaperRtf = imgDraftPaperRtf;
        this.imgDraftPaperPdf = imgDraftPaperPdf;
        this.dtmDraftPaperTime = dtmDraftPaperTime;
        this.chvDraftPaperName = chvDraftPaperName;
        this.imgFinalPaperDoc = imgFinalPaperDoc;
        this.imgFinalPaperPdf = imgFinalPaperPdf;
        this.imgFinalPaperRtf = imgFinalPaperRtf;
        this.dtmFinalPaperTime = dtmFinalPaperTime;
        this.chvFinalPaperName = chvFinalPaperName;
        this.txtMemo = txtMemo;
        this.bitIsMailCopyright = bitIsMailCopyright;
        this.bitIsMailOrgAbstract = bitIsMailOrgAbstract;
        this.bitIsMailOrgPaper = bitIsMailOrgPaper;
        this.bitIsMailPaperReg = bitIsMailPaperReg;
        this.bitIsRegisted = bitIsRegisted;
        this.chrPaperType = chrPaperType;
        this.dtmPaperTime = dtmPaperTime;
        this.chvPaperCode = chvPaperCode;
        this.inyAuthorRank = inyAuthorRank;
        this.chvPaperKeyword = chvPaperKeyword;
        this.bitIsJuniorAward = bitIsJuniorAward;
        this.bitIsPaperPrize = bitIsPaperPrize;
        this.chvPaperLNumber = chvPaperLnumber;
        this.chrSessionNo = chrSessionNo;
        this.bitIsAddPresent = bitIsAddPresent;
    }

    /** default constructor */
    public Tpaper() {
    }

    /** minimal constructor */
    public Tpaper(String chvPaperNumber, String chvPaperTitle, String chrStatus) {
        this.chvPaperNumber = chvPaperNumber;
        this.chvPaperTitle = chvPaperTitle;
        this.chrStatus = chrStatus;
    }

    /** 
     *            @hibernate.id
     *             generator-class="assigned"
     *             type="java.lang.String"
     *             column="chvPaperNumber"
     *         
     */
    public String getChvPaperNumber() {
        return this.chvPaperNumber;
    }

    public void setChvPaperNumber(String chvPaperNumber) {
        this.chvPaperNumber = chvPaperNumber;
    }

    /** 
     *            @hibernate.property
     *             column="chvPaperTitle"
     *             length="500"
     *             not-null="true"
     *         
     */
    public String getChvPaperTitle() {
        return this.chvPaperTitle;
    }

    public void setChvPaperTitle(String chvPaperTitle) {
        this.chvPaperTitle = chvPaperTitle;
    }

    /** 
     *            @hibernate.property
     *             column="chrStatus"
     *             length="2"
     *             not-null="true"
     *         
     */
    public String getChrStatus() {
        return this.chrStatus;
    }

    public void setChrStatus(String chrStatus) {
        this.chrStatus = chrStatus;
    }

    /** 
     *            @hibernate.property
     *             column="txtAbstract"
     *             length="2147483647"
     *         
     */
    public String getTxtAbstract() {
        return this.txtAbstract;
    }

    public void setTxtAbstract(String txtAbstract) {
        this.txtAbstract = txtAbstract;
    }

    /** 
     *            @hibernate.property
     *             column="imgAbstract_doc"
     *             length="2147483647"
     *         
     */
    public byte[] getImgAbstractDoc() {
        return this.imgAbstractDoc;
    }

    public void setImgAbstractDoc(byte[] imgAbstractDoc) {
        this.imgAbstractDoc = imgAbstractDoc;
    }

    /** 
     *            @hibernate.property
     *             column="imgAbstract_rtf"
     *             length="2147483647"
     *         
     */
    public byte[] getImgAbstractRtf() {
        return this.imgAbstractRtf;
    }

    public void setImgAbstractRtf(byte[] imgAbstractRtf) {
        this.imgAbstractRtf = imgAbstractRtf;
    }

    /** 
     *            @hibernate.property
     *             column="imgAbstract_pdf"
     *             length="2147483647"
     *         
     */
    public byte[] getImgAbstractPdf() {
        return this.imgAbstractPdf;
    }

    public void setImgAbstractPdf(byte[] imgAbstractPdf) {
        this.imgAbstractPdf = imgAbstractPdf;
    }

    /** 
     *            @hibernate.property
     *             column="dtmAbstractTime"
     *             length="23"
     *         
     */
    public String getDtmAbstractTime() {
        return this.dtmAbstractTime;
    }

    public void setDtmAbstractTime(String dtmAbstractTime) {
        this.dtmAbstractTime = dtmAbstractTime;
    }

    /** 
     *            @hibernate.property
     *             column="chvAbstractName"
     *             length="200"
     *         
     */
    public String getChvAbstractName() {
        return this.chvAbstractName;
    }

    public void setChvAbstractName(String chvAbstractName) {
        this.chvAbstractName = chvAbstractName;
    }

    /** 
     *            @hibernate.property
     *             column="imgDraftPaper_doc"
     *             length="2147483647"
     *         
     */
    public byte[] getImgDraftPaperDoc() {
        return this.imgDraftPaperDoc;
    }

    public void setImgDraftPaperDoc(byte[] imgDraftPaperDoc) {
        this.imgDraftPaperDoc = imgDraftPaperDoc;
    }

    /** 
     *            @hibernate.property
     *             column="imgDraftPaper_rtf"
     *             length="2147483647"
     *         
     */
    public byte[] getImgDraftPaperRtf() {
        return this.imgDraftPaperRtf;
    }

    public void setImgDraftPaperRtf(byte[] imgDraftPaperRtf) {
        this.imgDraftPaperRtf = imgDraftPaperRtf;
    }

    /** 
     *            @hibernate.property
     *             column="imgDraftPaper_pdf"
     *             length="2147483647"
     *         
     */
    public byte[] getImgDraftPaperPdf() {
        return this.imgDraftPaperPdf;
    }

    public void setImgDraftPaperPdf(byte[] imgDraftPaperPdf) {
        this.imgDraftPaperPdf = imgDraftPaperPdf;
    }

    /** 
     *            @hibernate.property
     *             column="dtmDraftPaperTime"
     *             length="23"
     *         
     */
    public String getDtmDraftPaperTime() {
        return this.dtmDraftPaperTime;
    }

    public void setDtmDraftPaperTime(String dtmDraftPaperTime) {
        this.dtmDraftPaperTime = dtmDraftPaperTime;
    }

    /** 
     *            @hibernate.property
     *             column="chvDraftPaperName"
     *             length="200"
     *         
     */
    public String getChvDraftPaperName() {
        return this.chvDraftPaperName;
    }

    public void setChvDraftPaperName(String chvDraftPaperName) {
        this.chvDraftPaperName = chvDraftPaperName;
    }

    /** 
     *            @hibernate.property
     *             column="imgFinalPaper_doc"
     *             length="2147483647"
     *         
     */
    public byte[] getImgFinalPaperDoc() {
        return this.imgFinalPaperDoc;
    }

    public void setImgFinalPaperDoc(byte[] imgFinalPaperDoc) {
        this.imgFinalPaperDoc = imgFinalPaperDoc;
    }

    /** 
     *            @hibernate.property
     *             column="imgFinalPaper_pdf"
     *             length="2147483647"
     *         
     */
    public byte[] getImgFinalPaperPdf() {
        return this.imgFinalPaperPdf;
    }

    public void setImgFinalPaperPdf(byte[] imgFinalPaperPdf) {
        this.imgFinalPaperPdf = imgFinalPaperPdf;
    }

    /** 
     *            @hibernate.property
     *             column="imgFinalPaper_rtf"
     *             length="2147483647"
     *         
     */
    public byte[] getImgFinalPaperRtf() {
        return this.imgFinalPaperRtf;
    }

    public void setImgFinalPaperRtf(byte[] imgFinalPaperRtf) {
        this.imgFinalPaperRtf = imgFinalPaperRtf;
    }

    /** 
     *            @hibernate.property
     *             column="dtmFinalPaperTime"
     *             length="23"
     *         
     */
    public String getDtmFinalPaperTime() {
        return this.dtmFinalPaperTime;
    }

    public void setDtmFinalPaperTime(String dtmFinalPaperTime) {
        this.dtmFinalPaperTime = dtmFinalPaperTime;
    }

    /** 
     *            @hibernate.property
     *             column="chvFinalPaperName"
     *             length="200"
     *         
     */
    public String getChvFinalPaperName() {
        return this.chvFinalPaperName;
    }

    public void setChvFinalPaperName(String chvFinalPaperName) {
        this.chvFinalPaperName = chvFinalPaperName;
    }

    /** 
     *            @hibernate.property
     *             column="txtMemo"
     *             length="2147483647"
     *         
     */
    public String getTxtMemo() {
        return this.txtMemo;
    }

    public void setTxtMemo(String txtMemo) {
        this.txtMemo = txtMemo;
    }

    /** 
     *            @hibernate.property
     *             column="bitIsMailCopyright"
     *             length="1"
     *         
     */
    public Boolean getBitIsMailCopyright() {
        return this.bitIsMailCopyright;
    }

    public void setBitIsMailCopyright(Boolean bitIsMailCopyright) {
        this.bitIsMailCopyright = bitIsMailCopyright;
    }

    /** 
     *            @hibernate.property
     *             column="bitIsMailOrgAbstract"
     *             length="1"
     *         
     */
    public Boolean getBitIsMailOrgAbstract() {
        return this.bitIsMailOrgAbstract;
    }

    public void setBitIsMailOrgAbstract(Boolean bitIsMailOrgAbstract) {
        this.bitIsMailOrgAbstract = bitIsMailOrgAbstract;
    }

    /** 
     *            @hibernate.property
     *             column="bitIsMailOrgPaper"
     *             length="1"
     *         
     */
    public Boolean getBitIsMailOrgPaper() {
        return this.bitIsMailOrgPaper;
    }

    public void setBitIsMailOrgPaper(Boolean bitIsMailOrgPaper) {
        this.bitIsMailOrgPaper = bitIsMailOrgPaper;
    }

    /** 
     *            @hibernate.property
     *             column="bitIsMailPaperReg"
     *             length="1"
     *         
     */
    public Boolean getBitIsMailPaperReg() {
        return this.bitIsMailPaperReg;
    }

    public void setBitIsMailPaperReg(Boolean bitIsMailPaperReg) {
        this.bitIsMailPaperReg = bitIsMailPaperReg;
    }

    /** 
     *            @hibernate.property
     *             column="bitIsRegisted"
     *             length="1"
     *         
     */
    public Boolean getBitIsRegisted() {
        return this.bitIsRegisted;
    }

    public void setBitIsRegisted(Boolean bitIsRegisted) {
        this.bitIsRegisted = bitIsRegisted;
    }

    /** 
     *            @hibernate.property
     *             column="chrPaperType"
     *             length="1"
     *         
     */
    public String getChrPaperType() {
        return this.chrPaperType;
    }

    public void setChrPaperType(String chrPaperType) {
        this.chrPaperType = chrPaperType;
    }

    /** 
     *            @hibernate.property
     *             column="dtmPaperTime"
     *             length="23"
     *         
     */
    public String getDtmPaperTime() {
        return this.dtmPaperTime;
    }

    public void setDtmPaperTime(String dtmPaperTime) {
        this.dtmPaperTime = dtmPaperTime;
    }

    /** 
     *            @hibernate.property
     *             column="chvPaperCode"
     *             length="10"
     *         
     */
    public String getChvPaperCode() {
        return this.chvPaperCode;
    }

    public void setChvPaperCode(String chvPaperCode) {
        this.chvPaperCode = chvPaperCode;
    }

    /** 
     *            @hibernate.property
     *             column="inyAuthorRank"
     *             length="3"
     *         
     */
    public Integer getInyAuthorRank() {
        return this.inyAuthorRank;
    }

    public void setInyAuthorRank(Integer inyAuthorRank) {
        this.inyAuthorRank = inyAuthorRank;
    }

    /** 
     *            @hibernate.property
     *             column="chvPaperKeyword"
     *             length="200"
     *         
     */
    public String getChvPaperKeyword() {
        return this.chvPaperKeyword;
    }

    public void setChvPaperKeyword(String chvPaperKeyword) {
        this.chvPaperKeyword = chvPaperKeyword;
    }

    /** 
     *            @hibernate.property
     *             column="bitIsJuniorAward"
     *             length="1"
     *         
     */
    public Boolean getBitIsJuniorAward() {
        return this.bitIsJuniorAward;
    }

    public void setBitIsJuniorAward(Boolean bitIsJuniorAward) {
        this.bitIsJuniorAward = bitIsJuniorAward;
    }

    /** 
     *            @hibernate.property
     *             column="bitIsPaperPrize"
     *             length="1"
     *         
     */
    public Boolean getBitIsPaperPrize() {
        return this.bitIsPaperPrize;
    }

    public void setBitIsPaperPrize(Boolean bitIsPaperPrize) {
        this.bitIsPaperPrize = bitIsPaperPrize;
    }

    /** 
     *            @hibernate.property
     *             column="chvPaperLNumber"
     *             length="10"
     *         
     */
    public String getChvPaperLNumber() {
        return this.chvPaperLNumber;
    }

    public void setChvPaperLNumber(String chvPaperLnumber) {
        this.chvPaperLNumber = chvPaperLnumber;
    }

    /** 
     *            @hibernate.property
     *             column="chrSessionNo"
     *             length="2"
     *         
     */
    public String getChrSessionNo() {
        return this.chrSessionNo;
    }

    public void setChrSessionNo(String chrSessionNo) {
        this.chrSessionNo = chrSessionNo;
    }

    /** 
     *            @hibernate.property
     *             column="bitIsAddPresent"
     *             length="1"
     *         
     */
    public Boolean getBitIsAddPresent() {
        return this.bitIsAddPresent;
    }

    public void setBitIsAddPresent(Boolean bitIsAddPresent) {
        this.bitIsAddPresent = bitIsAddPresent;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("chvPaperNumber", getChvPaperNumber())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof Tpaper) ) return false;
        Tpaper castOther = (Tpaper) other;
        return new EqualsBuilder()
            .append(this.getChvPaperNumber(), castOther.getChvPaperNumber())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getChvPaperNumber())
            .toHashCode();
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
    public String getChrSecondDivisionNo() {
        return this.chrSecondDivisionNo;
    }
    public void setChrSecondDivisionNo(String chrSecondDivisionNo) {
        this.chrSecondDivisionNo = chrSecondDivisionNo;
    }
    public String getChrThirdDivisionNo() {
        return this.chrThirdDivisionNo;
    }
    public void setChrThirdDivisionNo(String chrThirdDivisionNo) {
        this.chrThirdDivisionNo = chrThirdDivisionNo;
    }
    public Integer getIntUserID() {
        return this.intUserID;
    }
    public void setIntUserID(Integer intUserID) {
        this.intUserID = intUserID;
    }
}
