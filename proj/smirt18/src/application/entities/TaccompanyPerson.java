package application.entities;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import framework.util.StringUtil;

/** 
 *        @hibernate.class
 *         table="T_AccompanyPerson"
 *     
*/
public class TaccompanyPerson implements Serializable {

    /** identifier field */
    private String chrAccPersonNo;

    /** persistent field */
    private String chvAccPersonTitle;

    /** persistent field */
    private String chvAccPersonFirstName;

    /** nullable persistent field */
    private String chvAccPersonMiddleName;

    /** persistent field */
    private String chvAccPersonLastName;

    /** persistent field */
    private String chvPassportNumber;

    /** nullable persistent field */
    private Boolean bitIsAttendReception;

    /** nullable persistent field */
    private Boolean bitIsAttendBanquet;

    /** nullable persistent field */
    private Boolean bitIsAttendTechTour;

    /** nullable persistent field */
    private Boolean bitIsCheckin;

    /** persistent field */
    private String chrPartiNo;
    
    public String getPeronName() {
        String _strName = "";
        if (StringUtil.isNotBlank(getChvAccPersonFirstName())) {
            _strName += getChvAccPersonFirstName().trim();
        }
        if (StringUtil.isNotBlank(getChvAccPersonMiddleName())) {
            _strName += " " + getChvAccPersonMiddleName().trim();
        }
        if (StringUtil.isNotBlank(getChvAccPersonLastName())) {
            _strName += " " + getChvAccPersonLastName().trim();
        }
        return _strName;
    }

    /** default constructor */
    public TaccompanyPerson() {
    }


    /** 
     *            @hibernate.id
     *             generator-class="assigned"
     *             type="java.lang.String"
     *             column="chrAccPersonNo"
     *         
     */
    public String getChrAccPersonNo() {
        return this.chrAccPersonNo;
    }

    public void setChrAccPersonNo(String chrAccPersonNo) {
        this.chrAccPersonNo = chrAccPersonNo;
    }

    /** 
     *            @hibernate.property
     *             column="chvAccPersonTitle"
     *             length="20"
     *             not-null="true"
     *         
     */
    public String getChvAccPersonTitle() {
        return this.chvAccPersonTitle;
    }

    public void setChvAccPersonTitle(String chvAccPersonTitle) {
        this.chvAccPersonTitle = chvAccPersonTitle;
    }

    /** 
     *            @hibernate.property
     *             column="chvAccPersonFirstName"
     *             length="100"
     *             not-null="true"
     *         
     */
    public String getChvAccPersonFirstName() {
        return this.chvAccPersonFirstName;
    }

    public void setChvAccPersonFirstName(String chvAccPersonFirstName) {
        this.chvAccPersonFirstName = chvAccPersonFirstName;
    }

    /** 
     *            @hibernate.property
     *             column="chvAccPersonMiddleName"
     *             length="100"
     *         
     */
    public String getChvAccPersonMiddleName() {
        return this.chvAccPersonMiddleName;
    }

    public void setChvAccPersonMiddleName(String chvAccPersonMiddleName) {
        this.chvAccPersonMiddleName = chvAccPersonMiddleName;
    }

    /** 
     *            @hibernate.property
     *             column="chvAccPersonLastName"
     *             length="100"
     *             not-null="true"
     *         
     */
    public String getChvAccPersonLastName() {
        return this.chvAccPersonLastName;
    }

    public void setChvAccPersonLastName(String chvAccPersonLastName) {
        this.chvAccPersonLastName = chvAccPersonLastName;
    }

    /** 
     *            @hibernate.property
     *             column="chvPassportNumber"
     *             length="20"
     *             not-null="true"
     *         
     */
    public String getChvPassportNumber() {
        return this.chvPassportNumber;
    }

    public void setChvPassportNumber(String chvPassportNumber) {
        this.chvPassportNumber = chvPassportNumber;
    }

    /** 
     *            @hibernate.property
     *             column="bitIsAttendReception"
     *             length="1"
     *         
     */
    public Boolean getBitIsAttendReception() {
        return this.bitIsAttendReception;
    }

    public void setBitIsAttendReception(Boolean bitIsAttendReception) {
        this.bitIsAttendReception = bitIsAttendReception;
    }

    /** 
     *            @hibernate.property
     *             column="bitIsAttendBanquet"
     *             length="1"
     *         
     */
    public Boolean getBitIsAttendBanquet() {
        return this.bitIsAttendBanquet;
    }

    public void setBitIsAttendBanquet(Boolean bitIsAttendBanquet) {
        this.bitIsAttendBanquet = bitIsAttendBanquet;
    }

    /** 
     *            @hibernate.property
     *             column="bitIsAttendTechTour"
     *             length="1"
     *         
     */
    public Boolean getBitIsAttendTechTour() {
        return this.bitIsAttendTechTour;
    }

    public void setBitIsAttendTechTour(Boolean bitIsAttendTechTour) {
        this.bitIsAttendTechTour = bitIsAttendTechTour;
    }

    /** 
     *            @hibernate.property
     *             column="bitIsCheckin"
     *             length="1"
     *         
     */
    public Boolean getBitIsCheckin() {
        return this.bitIsCheckin;
    }

    public void setBitIsCheckin(Boolean bitIsCheckin) {
        this.bitIsCheckin = bitIsCheckin;
    }

    public String getChrPartiNo() {
        return this.chrPartiNo;
    }
    public void setChrPartiNo(String chrPartiNo) {
        this.chrPartiNo = chrPartiNo;
    }
    public String toString() {
        return new ToStringBuilder(this)
            .append("chrAccPersonNo", getChrAccPersonNo())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof TaccompanyPerson) ) return false;
        TaccompanyPerson castOther = (TaccompanyPerson) other;
        return new EqualsBuilder()
            .append(this.getChrAccPersonNo(), castOther.getChrAccPersonNo())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getChrAccPersonNo())
            .toHashCode();
    }

}
