package application.entities;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** 
 *        @hibernate.class
 *         table="T_Visa"
 *     
*/
public class Tvisa implements Serializable {

    /** identifier field */
    private String chrVisaNo;

    /** persistent field */
    private String chvVisaNationality;

    /** persistent field */
    private String chvBirthPlace;

    /** nullable persistent field */
    private String dtmBirthDate;

    /** persistent field */
    private String dtmArrivalDate;

    /** persistent field */
    private String dtmDepartureDate;

    /** persistent field */
    private String chvVisaPlace;

    /** nullable persistent field */
    private Boolean bitIsNeedOrgVisa;

    /** nullable persistent field */
    private String dtmVisa;

    /** nullable persistent field */
    private Boolean bitIsVisaOk;

    /** persistent field */
    private application.entities.Tparticipant tparticipant;

    /** persistent field */
    private application.entities.TaccompanyPerson taccompanyPerson;
    
    private String chrPartiNo;
    private String chrAccPersonNo;

    public String getChrAccPersonNo() {
        return this.chrAccPersonNo;
    }
    public void setChrAccPersonNo(String chrAccPersonNo) {
        this.chrAccPersonNo = chrAccPersonNo;
    }
    public String getChrPartiNo() {
        return this.chrPartiNo;
    }
    public void setChrPartiNo(String chrPartiNo) {
        this.chrPartiNo = chrPartiNo;
    }
    /** full constructor */
    public Tvisa(String chrVisaNo, String chvVisaNationality, String chvBirthPlace, String dtmBirthDate, String dtmArrivalDate, String dtmDepartureDate, String chvVisaPlace, Boolean bitIsNeedOrgVisa, String dtmVisa, Boolean bitIsVisaOk, application.entities.Tparticipant tparticipant, application.entities.TaccompanyPerson taccompanyPerson) {
        this.chrVisaNo = chrVisaNo;
        this.chvVisaNationality = chvVisaNationality;
        this.chvBirthPlace = chvBirthPlace;
        this.dtmBirthDate = dtmBirthDate;
        this.dtmArrivalDate = dtmArrivalDate;
        this.dtmDepartureDate = dtmDepartureDate;
        this.chvVisaPlace = chvVisaPlace;
        this.bitIsNeedOrgVisa = bitIsNeedOrgVisa;
        this.dtmVisa = dtmVisa;
        this.bitIsVisaOk = bitIsVisaOk;
        this.tparticipant = tparticipant;
        this.taccompanyPerson = taccompanyPerson;
    }

    /** default constructor */
    public Tvisa() {
    }

    /** minimal constructor */
    public Tvisa(String chrVisaNo, String chvVisaNationality, String chvBirthPlace, String dtmArrivalDate, String dtmDepartureDate, String chvVisaPlace, application.entities.Tparticipant tparticipant, application.entities.TaccompanyPerson taccompanyPerson) {
        this.chrVisaNo = chrVisaNo;
        this.chvVisaNationality = chvVisaNationality;
        this.chvBirthPlace = chvBirthPlace;
        this.dtmArrivalDate = dtmArrivalDate;
        this.dtmDepartureDate = dtmDepartureDate;
        this.chvVisaPlace = chvVisaPlace;
        this.tparticipant = tparticipant;
        this.taccompanyPerson = taccompanyPerson;
    }

    /** 
     *            @hibernate.id
     *             generator-class="assigned"
     *             type="java.lang.String"
     *             column="chrVisaNo"
     *         
     */
    public String getChrVisaNo() {
        return this.chrVisaNo;
    }

    public void setChrVisaNo(String chrVisaNo) {
        this.chrVisaNo = chrVisaNo;
    }

    /** 
     *            @hibernate.property
     *             column="chvVisaNationality"
     *             length="100"
     *             not-null="true"
     *         
     */
    public String getChvVisaNationality() {
        return this.chvVisaNationality;
    }

    public void setChvVisaNationality(String chvVisaNationality) {
        this.chvVisaNationality = chvVisaNationality;
    }

    /** 
     *            @hibernate.property
     *             column="chvBirthPlace"
     *             length="100"
     *             not-null="true"
     *         
     */
    public String getChvBirthPlace() {
        return this.chvBirthPlace;
    }

    public void setChvBirthPlace(String chvBirthPlace) {
        this.chvBirthPlace = chvBirthPlace;
    }

    /** 
     *            @hibernate.property
     *             column="dtmBirthDate"
     *             length="23"
     *         
     */
    public String getDtmBirthDate() {
        return this.dtmBirthDate;
    }

    public void setDtmBirthDate(String dtmBirthDate) {
        this.dtmBirthDate = dtmBirthDate;
    }

    /** 
     *            @hibernate.property
     *             column="dtmArrivalDate"
     *             length="23"
     *             not-null="true"
     *         
     */
    public String getDtmArrivalDate() {
        return this.dtmArrivalDate;
    }

    public void setDtmArrivalDate(String dtmArrivalDate) {
        this.dtmArrivalDate = dtmArrivalDate;
    }

    /** 
     *            @hibernate.property
     *             column="dtmDepartureDate"
     *             length="23"
     *             not-null="true"
     *         
     */
    public String getDtmDepartureDate() {
        return this.dtmDepartureDate;
    }

    public void setDtmDepartureDate(String dtmDepartureDate) {
        this.dtmDepartureDate = dtmDepartureDate;
    }

    /** 
     *            @hibernate.property
     *             column="chvVisaPlace"
     *             length="200"
     *             not-null="true"
     *         
     */
    public String getChvVisaPlace() {
        return this.chvVisaPlace;
    }

    public void setChvVisaPlace(String chvVisaPlace) {
        this.chvVisaPlace = chvVisaPlace;
    }

    /** 
     *            @hibernate.property
     *             column="bitIsNeedOrgVisa"
     *             length="1"
     *         
     */
    public Boolean getBitIsNeedOrgVisa() {
        return this.bitIsNeedOrgVisa;
    }

    public void setBitIsNeedOrgVisa(Boolean bitIsNeedOrgVisa) {
        this.bitIsNeedOrgVisa = bitIsNeedOrgVisa;
    }

    /** 
     *            @hibernate.property
     *             column="dtmVisa"
     *             length="23"
     *         
     */
    public String getDtmVisa() {
        return this.dtmVisa;
    }

    public void setDtmVisa(String dtmVisa) {
        this.dtmVisa = dtmVisa;
    }

    /** 
     *            @hibernate.property
     *             column="bitIsVisaOk"
     *             length="1"
     *         
     */
    public Boolean getBitIsVisaOk() {
        return this.bitIsVisaOk;
    }

    public void setBitIsVisaOk(Boolean bitIsVisaOk) {
        this.bitIsVisaOk = bitIsVisaOk;
    }

    /** 
     *            @hibernate.many-to-one
     *             not-null="true"
     *            @hibernate.column name="chrPartiNo"         
     *         
     */
    public application.entities.Tparticipant getTparticipant() {
        return this.tparticipant;
    }

    public void setTparticipant(application.entities.Tparticipant tparticipant) {
        this.tparticipant = tparticipant;
    }

    /** 
     *            @hibernate.many-to-one
     *             not-null="true"
     *            @hibernate.column name="chrAccPersonNo"         
     *         
     */
    public application.entities.TaccompanyPerson getTaccompanyPerson() {
        return this.taccompanyPerson;
    }

    public void setTaccompanyPerson(application.entities.TaccompanyPerson taccompanyPerson) {
        this.taccompanyPerson = taccompanyPerson;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("chrVisaNo", getChrVisaNo())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof Tvisa) ) return false;
        Tvisa castOther = (Tvisa) other;
        return new EqualsBuilder()
            .append(this.getChrVisaNo(), castOther.getChrVisaNo())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getChrVisaNo())
            .toHashCode();
    }

}
