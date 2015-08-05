package application.entities;

import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** 
 *        @hibernate.class
 *         table="T_UserAccount"
 *     
*/
public class TuserAccount implements Serializable {

    /** identifier field */
    private Integer intUserId;

    /** persistent field */
    private String chvUserAccount;

    /** persistent field */
    private String chvFirstName;

    /** nullable persistent field */
    private String chvMiddleName;

    /** persistent field */
    private String chvLastName;

    /** persistent field */
    private String chvPassword;

    /** nullable persistent field */
    private String chvNational;

    /** nullable persistent field */
    private String chvAffiliation;

    /** persistent field */
    private String chvJobTitle;

    /** nullable persistent field */
    private String chvAddress1;

    /** nullable persistent field */
    private String chvCity;

    /** nullable persistent field */
    private String chvCountry;

    /** nullable persistent field */
    private String chvPostalCode;

    /** nullable persistent field */
    private String chvPhone;

    /** nullable persistent field */
    private String chvFax;

    /** nullable persistent field */
    private String chvphone1;

    /** nullable persistent field */
    private String chvphone2;

    /** nullable persistent field */
    private String chvAddress2;

    /** nullable persistent field */
    private String chvProvince;

    /** nullable persistent field */
    private String chvPosition;

    /** nullable persistent field */
    private String chvDepartment;

    /** nullable persistent field */
    private String txtBiography;

    /** nullable persistent field */
    private Boolean bitIsMember;
    
    private String chrCountryNo;
    private String chrTypeNo;
    private String chrAffiliationTypeNo;
    

    /** full constructor */
    public TuserAccount(Integer intUserId, String chvUserAccount, String chvFirstName, String chvMiddleName, String chvLastName, String chvPassword, String chvNational, String chvAffiliation, String chvJobTitle, String chvAddress1, String chvCity, String chvCountry, String chvPostalCode, String chvPhone, String chvFax, String chvphone1, String chvphone2, String chvAddress2, String chvProvince, String chvPosition, String chvDepartment, String txtBiography, Boolean bitIsMember) {
        this.intUserId = intUserId;
        this.chvUserAccount = chvUserAccount;
        this.chvFirstName = chvFirstName;
        this.chvMiddleName = chvMiddleName;
        this.chvLastName = chvLastName;
        this.chvPassword = chvPassword;
        this.chvNational = chvNational;
        this.chvAffiliation = chvAffiliation;
        this.chvJobTitle = chvJobTitle;
        this.chvAddress1 = chvAddress1;
        this.chvCity = chvCity;
        this.chvCountry = chvCountry;
        this.chvPostalCode = chvPostalCode;
        this.chvPhone = chvPhone;
        this.chvFax = chvFax;
        this.chvphone1 = chvphone1;
        this.chvphone2 = chvphone2;
        this.chvAddress2 = chvAddress2;
        this.chvProvince = chvProvince;
        this.chvPosition = chvPosition;
        this.chvDepartment = chvDepartment;
        this.txtBiography = txtBiography;
        this.bitIsMember = bitIsMember;
    }

    /** default constructor */
    public TuserAccount() {
    }

    /** minimal constructor */
    public TuserAccount(Integer intUserId, String chvUserAccount, String chvFirstName, String chvLastName, String chvPassword, String chvJobTitle) {
        this.intUserId = intUserId;
        this.chvUserAccount = chvUserAccount;
        this.chvFirstName = chvFirstName;
        this.chvLastName = chvLastName;
        this.chvPassword = chvPassword;
        this.chvJobTitle = chvJobTitle;
    }

    /** 
     *            @hibernate.id
     *             generator-class="assigned"
     *             type="java.lang.Integer"
     *             column="intUserID"
     *         
     */
    public Integer getIntUserID() {
        return this.intUserId;
    }

    public void setIntUserID(Integer intUserId) {
        this.intUserId = intUserId;
    }

    /** 
     *            @hibernate.property
     *             column="chvUserAccount"
     *             length="100"
     *             not-null="true"
     *         
     */
    public String getChvUserAccount() {
        return this.chvUserAccount;
    }

    public void setChvUserAccount(String chvUserAccount) {
        this.chvUserAccount = chvUserAccount;
    }

    /** 
     *            @hibernate.property
     *             column="chvFirstName"
     *             length="100"
     *             not-null="true"
     *         
     */
    public String getChvFirstName() {
        return this.chvFirstName;
    }

    public void setChvFirstName(String chvFirstName) {
        this.chvFirstName = chvFirstName;
    }

    /** 
     *            @hibernate.property
     *             column="chvMiddleName"
     *             length="100"
     *         
     */
    public String getChvMiddleName() {
        return this.chvMiddleName;
    }

    public void setChvMiddleName(String chvMiddleName) {
        this.chvMiddleName = chvMiddleName;
    }

    /** 
     *            @hibernate.property
     *             column="chvLastName"
     *             length="50"
     *             not-null="true"
     *         
     */
    public String getChvLastName() {
        return this.chvLastName;
    }

    public void setChvLastName(String chvLastName) {
        this.chvLastName = chvLastName;
    }

    /** 
     *            @hibernate.property
     *             column="chvPassword"
     *             length="30"
     *             not-null="true"
     *         
     */
    public String getChvPassword() {
        return this.chvPassword;
    }

    public void setChvPassword(String chvPassword) {
        this.chvPassword = chvPassword;
    }

    /** 
     *            @hibernate.property
     *             column="chvNational"
     *             length="200"
     *         
     */
    public String getChvNational() {
        return this.chvNational;
    }

    public void setChvNational(String chvNational) {
        this.chvNational = chvNational;
    }

    /** 
     *            @hibernate.property
     *             column="chvAffiliation"
     *             length="200"
     *         
     */
    public String getChvAffiliation() {
        return this.chvAffiliation;
    }

    public void setChvAffiliation(String chvAffiliation) {
        this.chvAffiliation = chvAffiliation;
    }

    /** 
     *            @hibernate.property
     *             column="chvJobTitle"
     *             length="100"
     *             not-null="true"
     *         
     */
    public String getChvJobTitle() {
        return this.chvJobTitle;
    }

    public void setChvJobTitle(String chvJobTitle) {
        this.chvJobTitle = chvJobTitle;
    }

    /** 
     *            @hibernate.property
     *             column="chvAddress1"
     *             length="300"
     *         
     */
    public String getChvAddress1() {
        return this.chvAddress1;
    }

    public void setChvAddress1(String chvAddress1) {
        this.chvAddress1 = chvAddress1;
    }

    /** 
     *            @hibernate.property
     *             column="chvCity"
     *             length="100"
     *         
     */
    public String getChvCity() {
        return this.chvCity;
    }

    public void setChvCity(String chvCity) {
        this.chvCity = chvCity;
    }

    /** 
     *            @hibernate.property
     *             column="chvCountry"
     *             length="200"
     *         
     */
    public String getChvCountry() {
        return this.chvCountry;
    }

    public void setChvCountry(String chvCountry) {
        this.chvCountry = chvCountry;
    }

    /** 
     *            @hibernate.property
     *             column="chvPostalCode"
     *             length="20"
     *         
     */
    public String getChvPostalCode() {
        return this.chvPostalCode;
    }

    public void setChvPostalCode(String chvPostalCode) {
        this.chvPostalCode = chvPostalCode;
    }

    /** 
     *            @hibernate.property
     *             column="chvPhone"
     *             length="50"
     *         
     */
    public String getChvPhone() {
        return this.chvPhone;
    }

    public void setChvPhone(String chvPhone) {
        this.chvPhone = chvPhone;
    }

    /** 
     *            @hibernate.property
     *             column="chvFax"
     *             length="50"
     *         
     */
    public String getChvFax() {
        return this.chvFax;
    }

    public void setChvFax(String chvFax) {
        this.chvFax = chvFax;
    }

    /** 
     *            @hibernate.property
     *             column="chvphone1"
     *             length="50"
     *         
     */
    public String getChvphone1() {
        return this.chvphone1;
    }

    public void setChvphone1(String chvphone1) {
        this.chvphone1 = chvphone1;
    }

    /** 
     *            @hibernate.property
     *             column="chvphone2"
     *             length="50"
     *         
     */
    public String getChvphone2() {
        return this.chvphone2;
    }

    public void setChvphone2(String chvphone2) {
        this.chvphone2 = chvphone2;
    }

    /** 
     *            @hibernate.property
     *             column="chvAddress2"
     *             length="300"
     *         
     */
    public String getChvAddress2() {
        return this.chvAddress2;
    }

    public void setChvAddress2(String chvAddress2) {
        this.chvAddress2 = chvAddress2;
    }

    /** 
     *            @hibernate.property
     *             column="chvProvince"
     *             length="100"
     *         
     */
    public String getChvProvince() {
        return this.chvProvince;
    }

    public void setChvProvince(String chvProvince) {
        this.chvProvince = chvProvince;
    }

    /** 
     *            @hibernate.property
     *             column="chvPosition"
     *             length="100"
     *         
     */
    public String getChvPosition() {
        return this.chvPosition;
    }

    public void setChvPosition(String chvPosition) {
        this.chvPosition = chvPosition;
    }

    /** 
     *            @hibernate.property
     *             column="chvDepartment"
     *             length="200"
     *         
     */
    public String getChvDepartment() {
        return this.chvDepartment;
    }

    public void setChvDepartment(String chvDepartment) {
        this.chvDepartment = chvDepartment;
    }

    /** 
     *            @hibernate.property
     *             column="txtBiography"
     *             length="2147483647"
     *         
     */
    public String getTxtBiography() {
        return this.txtBiography;
    }

    public void setTxtBiography(String txtBiography) {
        this.txtBiography = txtBiography;
    }

    /** 
     *            @hibernate.property
     *             column="bitIsMember"
     *             length="1"
     *         
     */
    public Boolean getBitIsMember() {
        return this.bitIsMember;
    }

    public void setBitIsMember(Boolean bitIsMember) {
        this.bitIsMember = bitIsMember;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("intUserId", getIntUserID())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof TuserAccount) ) return false;
        TuserAccount castOther = (TuserAccount) other;
        return new EqualsBuilder()
            .append(this.getIntUserID(), castOther.getIntUserID())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getIntUserID())
            .toHashCode();
    }

    public String getChrAffiliationTypeNo() {
        return this.chrAffiliationTypeNo;
    }
    public void setChrAffiliationTypeNo(String chrAffiliationTypeNo) {
        this.chrAffiliationTypeNo = chrAffiliationTypeNo;
    }
    public String getChrCountryNo() {
        return this.chrCountryNo;
    }
    public void setChrCountryNo(String chrCountryNo) {
        this.chrCountryNo = chrCountryNo;
    }
    public String getChrTypeNo() {
        return this.chrTypeNo;
    }
    public void setChrTypeNo(String chrTypeNo) {
        this.chrTypeNo = chrTypeNo;
    }
}
