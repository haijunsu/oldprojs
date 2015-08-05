package application.entities;

import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** 
 *        @hibernate.class
 *         table="T_Coauthor"
 *     
*/
public class Tcoauthor implements Serializable {

    /** identifier field */
    private String uidCoauthorId;
    
    private String chvPaperNumber;

    /** persistent field */
    private String chvCoauthorEmail;

    /** persistent field */
    private String chvFirstName;

    /** nullable persistent field */
    private String chvMiddleName;

    /** persistent field */
    private String chvLastName;

    /** nullable persistent field */
    private String chvAffiliation;

    /** nullable persistent field */
    private String chvAddress1;

    /** nullable persistent field */
    private String chvAddress2;

    /** nullable persistent field */
    private String chvCity;

    /** nullable persistent field */
    private String chvProvince;

    /** nullable persistent field */
    private String chvPostalCode;

    /** nullable persistent field */
    private String chrCountryNo;

    /** nullable persistent field */
    private String chvJobTitle;

    /** nullable persistent field */
    private String chvPosition;

    /** nullable persistent field */
    private String chvDepartment;

    /** nullable persistent field */
    private Boolean bitIsMember;

    /** nullable persistent field */
    private Integer inyAuthorRank;

    /** nullable persistent field */
    private String chvFax;

    /** nullable persistent field */
    private String chvPhone;

    /** nullable persistent field */
    private String txtBiography;


    /** default constructor */
    public Tcoauthor() {
    }

    /** minimal constructor */
    public Tcoauthor(String uidCoauthorId, String chvCoauthorEmail, String chvFirstName, String chvLastName) {
        this.uidCoauthorId = uidCoauthorId;
        this.chvCoauthorEmail = chvCoauthorEmail;
        this.chvFirstName = chvFirstName;
        this.chvLastName = chvLastName;
    }

    /** 
     *            @hibernate.id
     *             generator-class="assigned"
     *             type="java.lang.String"
     *             column="uidCoauthorID"
     *         
     */
    public String getUidCoauthorId() {
        return this.uidCoauthorId;
    }

    public void setUidCoauthorId(String uidCoauthorId) {
        this.uidCoauthorId = uidCoauthorId;
    }

    /** 
     *            @hibernate.property
     *             column="chvCoauthorEmail"
     *             length="100"
     *             not-null="true"
     *         
     */
    public String getChvCoauthorEmail() {
        return this.chvCoauthorEmail;
    }

    public void setChvCoauthorEmail(String chvCoauthorEmail) {
        this.chvCoauthorEmail = chvCoauthorEmail;
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
     *             length="100"
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
     *             column="chrCountryNo"
     *             length="3"
     *         
     */
    public String getChrCountryNo() {
        return this.chrCountryNo;
    }

    public void setChrCountryNo(String chrCountryNo) {
        this.chrCountryNo = chrCountryNo;
    }

    /** 
     *            @hibernate.property
     *             column="chvJobTitle"
     *             length="40"
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

    public String toString() {
        return new ToStringBuilder(this)
            .append("uidCoauthorId", getUidCoauthorId())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof Tcoauthor) ) return false;
        Tcoauthor castOther = (Tcoauthor) other;
        return new EqualsBuilder()
            .append(this.getUidCoauthorId(), castOther.getUidCoauthorId())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getUidCoauthorId())
            .toHashCode();
    }

    public String getChvPaperNumber() {
        return this.chvPaperNumber;
    }
    public void setChvPaperNumber(String chvPaperNumber) {
        this.chvPaperNumber = chvPaperNumber;
    }
}
