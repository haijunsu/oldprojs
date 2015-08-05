package application.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** 
 *        @hibernate.class
 *         table="T_PaperRegistation"
 *     
*/
public class TpaperRegistation implements Serializable {

    /** identifier field */
    private String chrPaperRegNo;

    /** persistent field */
    private String chrPaperRegTableNo;

    /** persistent field */
    private String chvPaperLNumber;

    /** persistent field */
    private BigDecimal mnyPaperRegWishPay;

    /** persistent field */
    private BigDecimal mnyPaperRegFee;

    /** nullable persistent field */
    private BigDecimal mnyPaperRegFeeReceived;

    /** persistent field */
    private String chrMoneyType;

    /** nullable persistent field */
    private Boolean bitIsReceivedPaperRegFee;

    /** nullable persistent field */
    private String dtmReceivedPaperRegFeeDate;

    /** nullable persistent field */
    private Boolean bitIsAppJaegerPrize;

    /** nullable persistent field */
    private Boolean bitIsAppJuniorAward;

    /** nullable persistent field */
    private Boolean bitIsAppSupport;

    /** nullable persistent field */
    private String chrGrantedPartiNo;

    /** persistent field */
    private String dtmPaperRegDate;

    /** persistent field */
    private String chvPaperRegJobTitle;

    /** persistent field */
    private String chvPaperRegFirstName;

    /** nullable persistent field */
    private String chvPaperRegMiddleName;

    /** persistent field */
    private String chvPaperRegLastName;

    /** persistent field */
    private String chvPaperRegTeleNumber;

    /** nullable persistent field */
    private String chvPaperRegFax;

    /** persistent field */
    private String chvPaperRegEmail;

    /** nullable persistent field */
    private String chvPaperRegAccountNmuber;

    /** nullable persistent field */
    private String chvPaperRegBankName;

    /** nullable persistent field */
    private String chvPaperRegAccountName;

    /** nullable persistent field */
    private String dtmPaperRegTransDate;

    /** nullable persistent field */
    private String chvPaperRegSignature;

    /** nullable persistent field */
    private Boolean bitIsPaperRegFaxed;

    /** nullable persistent field */
    private Boolean bitIsAllPayed;

    /** nullable persistent field */
    private String chvPaperRegChequeNumber;

    /** nullable persistent field */
    private Boolean bitIsInformedPaperReg;
    private Boolean bitIsInformedPaperFee;

    /** persistent field */
    private String chvPaperNumber;

    /** persistent field */
    private String chrPaymentMethodNo;

    /** 
     *            @hibernate.id
     *             generator-class="assigned"
     *             type="java.lang.String"
     *             column="chrPaperRegNo"
     *         
     */
    public String getChrPaperRegNo() {
        return this.chrPaperRegNo;
    }

    public void setChrPaperRegNo(String chrPaperRegNo) {
        this.chrPaperRegNo = chrPaperRegNo;
    }

    /** 
     *            @hibernate.property
     *             column="chrPaperRegTableNo"
     *             length="4"
     *             not-null="true"
     *         
     */
    public String getChrPaperRegTableNo() {
        return this.chrPaperRegTableNo;
    }

    public void setChrPaperRegTableNo(String chrPaperRegTableNo) {
        this.chrPaperRegTableNo = chrPaperRegTableNo;
    }

    /** 
     *            @hibernate.property
     *             column="chvPaperLNumber"
     *             length="10"
     *             not-null="true"
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
     *             column="mnyPaperRegWishPay"
     *             length="19"
     *             not-null="true"
     *         
     */
    public BigDecimal getMnyPaperRegWishPay() {
        return this.mnyPaperRegWishPay;
    }

    public void setMnyPaperRegWishPay(BigDecimal mnyPaperRegWishPay) {
        this.mnyPaperRegWishPay = mnyPaperRegWishPay;
    }

    /** 
     *            @hibernate.property
     *             column="mnyPaperRegFee"
     *             length="19"
     *             not-null="true"
     *         
     */
    public BigDecimal getMnyPaperRegFee() {
        return this.mnyPaperRegFee;
    }

    public void setMnyPaperRegFee(BigDecimal mnyPaperRegFee) {
        this.mnyPaperRegFee = mnyPaperRegFee;
    }

    /** 
     *            @hibernate.property
     *             column="mnyPaperRegFeeReceived"
     *             length="19"
     *         
     */
    public BigDecimal getMnyPaperRegFeeReceived() {
        return this.mnyPaperRegFeeReceived;
    }

    public void setMnyPaperRegFeeReceived(BigDecimal mnyPaperRegFeeReceived) {
        this.mnyPaperRegFeeReceived = mnyPaperRegFeeReceived;
    }

    /** 
     *            @hibernate.property
     *             column="bitMoneyType"
     *             length="1"
     *             not-null="true"
     *         
     */
    public String getChrMoneyType() {
        if (this.chrMoneyType != null) {
            return this.chrMoneyType.trim();
        }
        return this.chrMoneyType;
    }

    public void setChrMoneyType(String chrMoneyType) {
        this.chrMoneyType = chrMoneyType;
    }

    /** 
     *            @hibernate.property
     *             column="bitIsReceivedPaperRegFee"
     *             length="1"
     *         
     */
    public Boolean getBitIsReceivedPaperRegFee() {
        return this.bitIsReceivedPaperRegFee;
    }

    public void setBitIsReceivedPaperRegFee(Boolean bitIsReceivedPaperRegFee) {
        this.bitIsReceivedPaperRegFee = bitIsReceivedPaperRegFee;
    }

    /** 
     *            @hibernate.property
     *             column="dtmReceivedPaperRegFeeDate"
     *             length="23"
     *         
     */
    public String getDtmReceivedPaperRegFeeDate() {
        return this.dtmReceivedPaperRegFeeDate;
    }

    public void setDtmReceivedPaperRegFeeDate(String dtmReceivedPaperRegFeeDate) {
        this.dtmReceivedPaperRegFeeDate = dtmReceivedPaperRegFeeDate;
    }

    /** 
     *            @hibernate.property
     *             column="bitIsAppJaegerPrize"
     *             length="1"
     *         
     */
    public Boolean getBitIsAppJaegerPrize() {
        return this.bitIsAppJaegerPrize;
    }

    public void setBitIsAppJaegerPrize(Boolean bitIsAppJaegerPrize) {
        this.bitIsAppJaegerPrize = bitIsAppJaegerPrize;
    }

    /** 
     *            @hibernate.property
     *             column="bitIsAppJuniorAward"
     *             length="1"
     *         
     */
    public Boolean getBitIsAppJuniorAward() {
        return this.bitIsAppJuniorAward;
    }

    public void setBitIsAppJuniorAward(Boolean bitIsAppJuniorAward) {
        this.bitIsAppJuniorAward = bitIsAppJuniorAward;
    }

    /** 
     *            @hibernate.property
     *             column="bitIsAppSupport"
     *             length="1"
     *         
     */
    public Boolean getBitIsAppSupport() {
        return this.bitIsAppSupport;
    }

    public void setBitIsAppSupport(Boolean bitIsAppSupport) {
        this.bitIsAppSupport = bitIsAppSupport;
    }

    /** 
     *            @hibernate.property
     *             column="chrGrantedPartiNo"
     *             length="6"
     *         
     */
    public String getChrGrantedPartiNo() {
        return this.chrGrantedPartiNo;
    }

    public void setChrGrantedPartiNo(String chrGrantedPartiNo) {
        this.chrGrantedPartiNo = chrGrantedPartiNo;
    }

    /** 
     *            @hibernate.property
     *             column="dtmPaperRegDate"
     *             length="23"
     *             not-null="true"
     *         
     */
    public String getDtmPaperRegDate() {
        return this.dtmPaperRegDate;
    }

    public void setDtmPaperRegDate(String dtmPaperRegDate) {
        this.dtmPaperRegDate = dtmPaperRegDate;
    }

    /** 
     *            @hibernate.property
     *             column="chvPaperRegJobTitle"
     *             length="20"
     *             not-null="true"
     *         
     */
    public String getChvPaperRegJobTitle() {
        return this.chvPaperRegJobTitle;
    }

    public void setChvPaperRegJobTitle(String chvPaperRegJobTitle) {
        this.chvPaperRegJobTitle = chvPaperRegJobTitle;
    }

    /** 
     *            @hibernate.property
     *             column="chvPaperRegFirstName"
     *             length="50"
     *             not-null="true"
     *         
     */
    public String getChvPaperRegFirstName() {
        return this.chvPaperRegFirstName;
    }

    public void setChvPaperRegFirstName(String chvPaperRegFirstName) {
        this.chvPaperRegFirstName = chvPaperRegFirstName;
    }

    /** 
     *            @hibernate.property
     *             column="chvPaperRegMiddleName"
     *             length="50"
     *         
     */
    public String getChvPaperRegMiddleName() {
        return this.chvPaperRegMiddleName;
    }

    public void setChvPaperRegMiddleName(String chvPaperRegMiddleName) {
        this.chvPaperRegMiddleName = chvPaperRegMiddleName;
    }

    /** 
     *            @hibernate.property
     *             column="chvPaperRegLastName"
     *             length="50"
     *             not-null="true"
     *         
     */
    public String getChvPaperRegLastName() {
        return this.chvPaperRegLastName;
    }

    public void setChvPaperRegLastName(String chvPaperRegLastName) {
        this.chvPaperRegLastName = chvPaperRegLastName;
    }

    /** 
     *            @hibernate.property
     *             column="chvPaperRegTeleNumber"
     *             length="50"
     *             not-null="true"
     *         
     */
    public String getChvPaperRegTeleNumber() {
        return this.chvPaperRegTeleNumber;
    }

    public void setChvPaperRegTeleNumber(String chvPaperRegTeleNumber) {
        this.chvPaperRegTeleNumber = chvPaperRegTeleNumber;
    }

    /** 
     *            @hibernate.property
     *             column="chvPaperRegFax"
     *             length="50"
     *         
     */
    public String getChvPaperRegFax() {
        return this.chvPaperRegFax;
    }

    public void setChvPaperRegFax(String chvPaperRegFax) {
        this.chvPaperRegFax = chvPaperRegFax;
    }

    /** 
     *            @hibernate.property
     *             column="chvPaperRegEmail"
     *             length="100"
     *             not-null="true"
     *         
     */
    public String getChvPaperRegEmail() {
        return this.chvPaperRegEmail;
    }

    public void setChvPaperRegEmail(String chvPaperRegEmail) {
        this.chvPaperRegEmail = chvPaperRegEmail;
    }

    /** 
     *            @hibernate.property
     *             column="chvPaperRegAccountNmuber"
     *             length="50"
     *         
     */
    public String getChvPaperRegAccountNmuber() {
        return this.chvPaperRegAccountNmuber;
    }

    public void setChvPaperRegAccountNmuber(String chvPaperRegAccountNmuber) {
        this.chvPaperRegAccountNmuber = chvPaperRegAccountNmuber;
    }

    /** 
     *            @hibernate.property
     *             column="chvPaperRegBankName"
     *             length="200"
     *         
     */
    public String getChvPaperRegBankName() {
        return this.chvPaperRegBankName;
    }

    public void setChvPaperRegBankName(String chvPaperRegBankName) {
        this.chvPaperRegBankName = chvPaperRegBankName;
    }

    /** 
     *            @hibernate.property
     *             column="chvPaperRegAccountName"
     *             length="100"
     *         
     */
    public String getChvPaperRegAccountName() {
        return this.chvPaperRegAccountName;
    }

    public void setChvPaperRegAccountName(String chvPaperRegAccountName) {
        this.chvPaperRegAccountName = chvPaperRegAccountName;
    }

    /** 
     *            @hibernate.property
     *             column="dtmPaperRegTransDate"
     *             length="23"
     *         
     */
    public String getDtmPaperRegTransDate() {
        return this.dtmPaperRegTransDate;
    }

    public void setDtmPaperRegTransDate(String dtmPaperRegTransDate) {
        this.dtmPaperRegTransDate = dtmPaperRegTransDate;
    }

    /** 
     *            @hibernate.property
     *             column="chvPaperRegSignature"
     *             length="100"
     *         
     */
    public String getChvPaperRegSignature() {
        return this.chvPaperRegSignature;
    }

    public void setChvPaperRegSignature(String chvPaperRegSignature) {
        this.chvPaperRegSignature = chvPaperRegSignature;
    }

    /** 
     *            @hibernate.property
     *             column="bitIsPaperRegFaxed"
     *             length="1"
     *         
     */
    public Boolean getBitIsPaperRegFaxed() {
        return this.bitIsPaperRegFaxed;
    }

    public void setBitIsPaperRegFaxed(Boolean bitIsPaperRegFaxed) {
        this.bitIsPaperRegFaxed = bitIsPaperRegFaxed;
    }

    /** 
     *            @hibernate.property
     *             column="bitIsAllPayed"
     *             length="1"
     *         
     */
    public Boolean getBitIsAllPayed() {
        return this.bitIsAllPayed;
    }

    public void setBitIsAllPayed(Boolean bitIsAllPayed) {
        this.bitIsAllPayed = bitIsAllPayed;
    }

    /** 
     *            @hibernate.property
     *             column="chvPaperRegChequeNumber"
     *             length="50"
     *         
     */
    public String getChvPaperRegChequeNumber() {
        return this.chvPaperRegChequeNumber;
    }

    public void setChvPaperRegChequeNumber(String chvPaperRegChequeNumber) {
        this.chvPaperRegChequeNumber = chvPaperRegChequeNumber;
    }

    /** 
     *            @hibernate.property
     *             column="bitIsInformed"
     *             length="1"
     *         
     */


    public String getChrPaymentMethodNo() {
        return this.chrPaymentMethodNo;
    }
    public void setChrPaymentMethodNo(String chrPaymentMethodNo) {
        this.chrPaymentMethodNo = chrPaymentMethodNo;
    }
    public String getChvPaperNumber() {
        return this.chvPaperNumber;
    }
    public void setChvPaperNumber(String chvPaperNumber) {
        this.chvPaperNumber = chvPaperNumber;
    }
    public String toString() {
        return new ToStringBuilder(this)
            .append("chrPaperRegNo", getChrPaperRegNo())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof TpaperRegistation) ) return false;
        TpaperRegistation castOther = (TpaperRegistation) other;
        return new EqualsBuilder()
            .append(this.getChrPaperRegNo(), castOther.getChrPaperRegNo())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getChrPaperRegNo())
            .toHashCode();
    }

    public Boolean getBitIsInformedPaperFee() {
        return this.bitIsInformedPaperFee;
    }
    public void setBitIsInformedPaperFee(Boolean bitIsInformedPaperFee) {
        this.bitIsInformedPaperFee = bitIsInformedPaperFee;
    }
    public Boolean getBitIsInformedPaperReg() {
        return this.bitIsInformedPaperReg;
    }
    public void setBitIsInformedPaperReg(Boolean bitIsInformedPaperReg) {
        this.bitIsInformedPaperReg = bitIsInformedPaperReg;
    }
}
