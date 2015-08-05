package application.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** 
 *        @hibernate.class
 *         table="T_RegistrationFee"
 *     
*/
public class TregistrationFee implements Serializable {

    /** identifier field */
    private String chrRegisFeeNo;

    /** nullable persistent field */
    private BigDecimal mnyConfFeeBefore;

    /** nullable persistent field */
    private BigDecimal mnyConfFeeBetween;

    /** nullable persistent field */
    private BigDecimal mnyConfFeeVenue;

    /** nullable persistent field */
    private Integer inyAccPerson;

    /** nullable persistent field */
    private BigDecimal mnyAccPersonFee;

    /** nullable persistent field */
    private BigDecimal mnyConfDinerFee;

    /** nullable persistent field */
    private Boolean bitIsGranted;

    /** nullable persistent field */
    private BigDecimal mnyGrantSupportFee;

    /** nullable persistent field */
    private String chvPaperNumber;

    /** persistent field */
    private String chrMoneyType;

    /** nullable persistent field */
    private BigDecimal mnySumShouldPayment;

    /** nullable persistent field */
    private BigDecimal mnySumPayment;

    /** nullable persistent field */
    private BigDecimal mnyRegFeeReceived;

    /** nullable persistent field */
    private Boolean bitIsRegFeeReceived;

    /** nullable persistent field */
    private String dtmRegFeeReceivedDate;

    /** nullable persistent field */
    private String chvAccountNumber;

    /** nullable persistent field */
    private String chvAccountName;

    /** nullable persistent field */
    private String chvBankName;

    /** nullable persistent field */
    private String dtmTransDate;

    /** nullable persistent field */
    private String chvRegChequeNumber;

    /** nullable persistent field */
    private String chvCreditCardStyle;

    /** nullable persistent field */
    private String chvCardNo;

    /** nullable persistent field */
    private String chvCardholderFirstName;

    /** nullable persistent field */
    private String chvCardholderMiddleName;

    /** nullable persistent field */
    private String chvCardholderLastName;

    /** nullable persistent field */
    private String chvCardholderAddress;

    /** nullable persistent field */
    private String chrLastThreeDigit;

    /** nullable persistent field */
    private String dtmExpirationData;

    /** nullable persistent field */
    private BigDecimal mnyWishPay;

    /** nullable persistent field */
    private String dtmPayDate;

    /** nullable persistent field */
    private String chvSignature;

    /** nullable persistent field */
    private Boolean bitIsFaxed;

    /** nullable persistent field */
    private Boolean bitIsInformed;

    /** persistent field */
    private String chrPartiNo;

    /** persistent field */
    private String chrEquipmentNo;
    
    private String chrPaymentMethodNo;


    public String getChrPaymentMethodNo() {
        return this.chrPaymentMethodNo;
    }
    public void setChrPaymentMethodNo(String chrPaymentMethodNo) {
        this.chrPaymentMethodNo = chrPaymentMethodNo;
    }
    /** 
     *            @hibernate.id
     *             generator-class="assigned"
     *             type="java.lang.String"
     *             column="chrRegisFeeNo"
     *         
     */
    public String getChrRegisFeeNo() {
        return this.chrRegisFeeNo;
    }

    public void setChrRegisFeeNo(String chrRegisFeeNo) {
        this.chrRegisFeeNo = chrRegisFeeNo;
    }

    /** 
     *            @hibernate.property
     *             column="mnyConfFeeBefore"
     *             length="19"
     *         
     */
    public BigDecimal getMnyConfFeeBefore() {
        return this.mnyConfFeeBefore;
    }

    public void setMnyConfFeeBefore(BigDecimal mnyConfFeeBefore) {
        this.mnyConfFeeBefore = mnyConfFeeBefore;
    }

    /** 
     *            @hibernate.property
     *             column="mnyConfFeeBetween"
     *             length="19"
     *         
     */
    public BigDecimal getMnyConfFeeBetween() {
        return this.mnyConfFeeBetween;
    }

    public void setMnyConfFeeBetween(BigDecimal mnyConfFeeBetween) {
        this.mnyConfFeeBetween = mnyConfFeeBetween;
    }

    /** 
     *            @hibernate.property
     *             column="mnyConfFeeVenue"
     *             length="19"
     *         
     */
    public BigDecimal getMnyConfFeeVenue() {
        return this.mnyConfFeeVenue;
    }

    public void setMnyConfFeeVenue(BigDecimal mnyConfFeeVenue) {
        this.mnyConfFeeVenue = mnyConfFeeVenue;
    }

    /** 
     *            @hibernate.property
     *             column="inyAccPerson"
     *             length="3"
     *         
     */
    public Integer getInyAccPerson() {
        return this.inyAccPerson;
    }

    public void setInyAccPerson(Integer inyAccPerson) {
        this.inyAccPerson = inyAccPerson;
    }

    /** 
     *            @hibernate.property
     *             column="mnyAccPersonFee"
     *             length="19"
     *         
     */
    public BigDecimal getMnyAccPersonFee() {
        return this.mnyAccPersonFee;
    }

    public void setMnyAccPersonFee(BigDecimal mnyAccPersonFee) {
        this.mnyAccPersonFee = mnyAccPersonFee;
    }

    /** 
     *            @hibernate.property
     *             column="mnyConfDinerFee"
     *             length="19"
     *         
     */
    public BigDecimal getMnyConfDinerFee() {
        return this.mnyConfDinerFee;
    }

    public void setMnyConfDinerFee(BigDecimal mnyConfDinerFee) {
        this.mnyConfDinerFee = mnyConfDinerFee;
    }

    /** 
     *            @hibernate.property
     *             column="bitIsGranted"
     *             length="1"
     *         
     */
    public Boolean getBitIsGranted() {
        return this.bitIsGranted;
    }

    public void setBitIsGranted(Boolean bitIsGranted) {
        this.bitIsGranted = bitIsGranted;
    }

    /** 
     *            @hibernate.property
     *             column="mnyGrantSupportFee"
     *             length="19"
     *         
     */
    public BigDecimal getMnyGrantSupportFee() {
        return this.mnyGrantSupportFee;
    }

    public void setMnyGrantSupportFee(BigDecimal mnyGrantSupportFee) {
        this.mnyGrantSupportFee = mnyGrantSupportFee;
    }

    /** 
     *            @hibernate.property
     *             column="chvPaperNumber"
     *             length="10"
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
     *             column="chrMoneyType"
     *             length="1"
     *             not-null="true"
     *         
     */
    public String getChrMoneyType() {
        return this.chrMoneyType;
    }

    public void setChrMoneyType(String chrMoneyType) {
        this.chrMoneyType = chrMoneyType;
    }

    /** 
     *            @hibernate.property
     *             column="mnySumShouldPayment"
     *             length="19"
     *         
     */
    public BigDecimal getMnySumShouldPayment() {
        return this.mnySumShouldPayment;
    }

    public void setMnySumShouldPayment(BigDecimal mnySumShouldPayment) {
        this.mnySumShouldPayment = mnySumShouldPayment;
    }

    /** 
     *            @hibernate.property
     *             column="mnySumPayment"
     *             length="19"
     *         
     */
    public BigDecimal getMnySumPayment() {
        return this.mnySumPayment;
    }

    public void setMnySumPayment(BigDecimal mnySumPayment) {
        this.mnySumPayment = mnySumPayment;
    }

    /** 
     *            @hibernate.property
     *             column="mnyRegFeeReceived"
     *             length="19"
     *         
     */
    public BigDecimal getMnyRegFeeReceived() {
        return this.mnyRegFeeReceived;
    }

    public void setMnyRegFeeReceived(BigDecimal mnyRegFeeReceived) {
        this.mnyRegFeeReceived = mnyRegFeeReceived;
    }

    /** 
     *            @hibernate.property
     *             column="bitIsRegFeeReceived"
     *             length="1"
     *         
     */
    public Boolean getBitIsRegFeeReceived() {
        return this.bitIsRegFeeReceived;
    }

    public void setBitIsRegFeeReceived(Boolean bitIsRegFeeReceived) {
        this.bitIsRegFeeReceived = bitIsRegFeeReceived;
    }

    /** 
     *            @hibernate.property
     *             column="dtmRegFeeReceivedDate"
     *             length="23"
     *         
     */
    public String getDtmRegFeeReceivedDate() {
        return this.dtmRegFeeReceivedDate;
    }

    public void setDtmRegFeeReceivedDate(String dtmRegFeeReceivedDate) {
        this.dtmRegFeeReceivedDate = dtmRegFeeReceivedDate;
    }

    /** 
     *            @hibernate.property
     *             column="chvAccountNumber"
     *             length="50"
     *         
     */
    public String getChvAccountNumber() {
        return this.chvAccountNumber;
    }

    public void setChvAccountNumber(String chvAccountNumber) {
        this.chvAccountNumber = chvAccountNumber;
    }

    /** 
     *            @hibernate.property
     *             column="chvAccountName"
     *             length="100"
     *         
     */
    public String getChvAccountName() {
        return this.chvAccountName;
    }

    public void setChvAccountName(String chvAccountName) {
        this.chvAccountName = chvAccountName;
    }

    /** 
     *            @hibernate.property
     *             column="chvBankName"
     *             length="200"
     *         
     */
    public String getChvBankName() {
        return this.chvBankName;
    }

    public void setChvBankName(String chvBankName) {
        this.chvBankName = chvBankName;
    }

    /** 
     *            @hibernate.property
     *             column="dtmTransDate"
     *             length="23"
     *         
     */
    public String getDtmTransDate() {
        return this.dtmTransDate;
    }

    public void setDtmTransDate(String dtmTransDate) {
        this.dtmTransDate = dtmTransDate;
    }

    /** 
     *            @hibernate.property
     *             column="chvRegChequeNumber"
     *             length="50"
     *         
     */
    public String getChvRegChequeNumber() {
        return this.chvRegChequeNumber;
    }

    public void setChvRegChequeNumber(String chvRegChequeNumber) {
        this.chvRegChequeNumber = chvRegChequeNumber;
    }

    /** 
     *            @hibernate.property
     *             column="chvCreditCardStyle"
     *             length="50"
     *         
     */
    public String getChvCreditCardStyle() {
        return this.chvCreditCardStyle;
    }

    public void setChvCreditCardStyle(String chvCreditCardStyle) {
        this.chvCreditCardStyle = chvCreditCardStyle;
    }

    /** 
     *            @hibernate.property
     *             column="chvCardNo"
     *             length="50"
     *         
     */
    public String getChvCardNo() {
        return this.chvCardNo;
    }

    public void setChvCardNo(String chvCardNo) {
        this.chvCardNo = chvCardNo;
    }

    /** 
     *            @hibernate.property
     *             column="chvCardholderFirstName"
     *             length="50"
     *         
     */
    public String getChvCardholderFirstName() {
        return this.chvCardholderFirstName;
    }

    public void setChvCardholderFirstName(String chvCardholderFirstName) {
        this.chvCardholderFirstName = chvCardholderFirstName;
    }

    /** 
     *            @hibernate.property
     *             column="chvCardholderMiddleName"
     *             length="50"
     *         
     */
    public String getChvCardholderMiddleName() {
        return this.chvCardholderMiddleName;
    }

    public void setChvCardholderMiddleName(String chvCardholderMiddleName) {
        this.chvCardholderMiddleName = chvCardholderMiddleName;
    }

    /** 
     *            @hibernate.property
     *             column="chvCardholderLastName"
     *             length="50"
     *         
     */
    public String getChvCardholderLastName() {
        return this.chvCardholderLastName;
    }

    public void setChvCardholderLastName(String chvCardholderLastName) {
        this.chvCardholderLastName = chvCardholderLastName;
    }

    /** 
     *            @hibernate.property
     *             column="chvCardholderAddress"
     *             length="300"
     *         
     */
    public String getChvCardholderAddress() {
        return this.chvCardholderAddress;
    }

    public void setChvCardholderAddress(String chvCardholderAddress) {
        this.chvCardholderAddress = chvCardholderAddress;
    }

    /** 
     *            @hibernate.property
     *             column="chrLastThreeDigit"
     *             length="3"
     *         
     */
    public String getChrLastThreeDigit() {
        return this.chrLastThreeDigit;
    }

    public void setChrLastThreeDigit(String chrLastThreeDigit) {
        this.chrLastThreeDigit = chrLastThreeDigit;
    }

    /** 
     *            @hibernate.property
     *             column="dtmExpirationData"
     *             length="23"
     *         
     */
    public String getDtmExpirationData() {
        return this.dtmExpirationData;
    }

    public void setDtmExpirationData(String dtmExpirationData) {
        this.dtmExpirationData = dtmExpirationData;
    }

    /** 
     *            @hibernate.property
     *             column="mnyWishPay"
     *             length="19"
     *         
     */
    public BigDecimal getMnyWishPay() {
        return this.mnyWishPay;
    }

    public void setMnyWishPay(BigDecimal mnyWishPay) {
        this.mnyWishPay = mnyWishPay;
    }

    /** 
     *            @hibernate.property
     *             column="dtmPayDate"
     *             length="23"
     *         
     */
    public String getDtmPayDate() {
        return this.dtmPayDate;
    }

    public void setDtmPayDate(String dtmPayDate) {
        this.dtmPayDate = dtmPayDate;
    }

    /** 
     *            @hibernate.property
     *             column="chvSignature"
     *             length="100"
     *         
     */
    public String getChvSignature() {
        return this.chvSignature;
    }

    public void setChvSignature(String chvSignature) {
        this.chvSignature = chvSignature;
    }

    /** 
     *            @hibernate.property
     *             column="bitIsFaxed"
     *             length="1"
     *         
     */
    public Boolean getBitIsFaxed() {
        return this.bitIsFaxed;
    }

    public void setBitIsFaxed(Boolean bitIsFaxed) {
        this.bitIsFaxed = bitIsFaxed;
    }

    /** 
     *            @hibernate.property
     *             column="bitIsInformed"
     *             length="1"
     *         
     */
    public Boolean getBitIsInformed() {
        return this.bitIsInformed;
    }

    public void setBitIsInformed(Boolean bitIsInformed) {
        this.bitIsInformed = bitIsInformed;
    }

    

    public String getChrEquipmentNo() {
        return this.chrEquipmentNo;
    }
    public void setChrEquipmentNo(String chrEquipmentNo) {
        this.chrEquipmentNo = chrEquipmentNo;
    }
    public String getChrPartiNo() {
        return this.chrPartiNo;
    }
    public void setChrPartiNo(String chrPartiNo) {
        this.chrPartiNo = chrPartiNo;
    }
    public String toString() {
        return new ToStringBuilder(this)
            .append("chrRegisFeeNo", getChrRegisFeeNo())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof TregistrationFee) ) return false;
        TregistrationFee castOther = (TregistrationFee) other;
        return new EqualsBuilder()
            .append(this.getChrRegisFeeNo(), castOther.getChrRegisFeeNo())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getChrRegisFeeNo())
            .toHashCode();
    }

}
