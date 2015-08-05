package application.entities;

import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** 
 *        @hibernate.class
 *         table="T_PaperPresented"
 *     
*/
public class TpaperPresented implements Serializable {

    /** identifier field */
    private Integer intIndex;

    /** persistent field */
    private String chvPaperLnumber;

    /** nullable persistent field */
    private String chvPresenterFirstName;

    /** nullable persistent field */
    private String chvPresenterMiddleName;

    /** nullable persistent field */
    private String chvPresenterLastName;

    /** nullable persistent field */
    private Boolean bitIsPersonalBook;

    /** nullable persistent field */
    private String chvAudioNeeds;

    /** nullable persistent field */
    private String chvSpecial;

    /** nullable persistent field */
    private Boolean bitIsSubmitDemoFile;

    /** persistent field */
    private String chrEquipmentNo;

    /** persistent field */
    private String chvPaperNumber;

    /** persistent field */
    private String chrPresenterPartiNo;

    /** 
     *            @hibernate.id
     *             generator-class="assigned"
     *             type="java.lang.Integer"
     *             column="intIndex"
     *         
     */
    public Integer getIntIndex() {
        return this.intIndex;
    }

    public void setIntIndex(Integer intIndex) {
        this.intIndex = intIndex;
    }

    /** 
     *            @hibernate.property
     *             column="chvPaperLNumber"
     *             length="10"
     *             not-null="true"
     *         
     */
    public String getChvPaperLNumber() {
        return this.chvPaperLnumber;
    }

    public void setChvPaperLNumber(String chvPaperLnumber) {
        this.chvPaperLnumber = chvPaperLnumber;
    }

    /** 
     *            @hibernate.property
     *             column="chvPresenterFirstName"
     *             length="100"
     *         
     */
    public String getChvPresenterFirstName() {
        return this.chvPresenterFirstName;
    }

    public void setChvPresenterFirstName(String chvPresenterFirstName) {
        this.chvPresenterFirstName = chvPresenterFirstName;
    }

    /** 
     *            @hibernate.property
     *             column="chvPresenterMiddleName"
     *             length="100"
     *         
     */
    public String getChvPresenterMiddleName() {
        return this.chvPresenterMiddleName;
    }

    public void setChvPresenterMiddleName(String chvPresenterMiddleName) {
        this.chvPresenterMiddleName = chvPresenterMiddleName;
    }

    /** 
     *            @hibernate.property
     *             column="chvPresenterLastName"
     *             length="100"
     *         
     */
    public String getChvPresenterLastName() {
        return this.chvPresenterLastName;
    }

    public void setChvPresenterLastName(String chvPresenterLastName) {
        this.chvPresenterLastName = chvPresenterLastName;
    }

    /** 
     *            @hibernate.property
     *             column="bitIsPersonalBook"
     *             length="1"
     *         
     */
    public Boolean getBitIsPersonalBook() {
        return this.bitIsPersonalBook;
    }

    public void setBitIsPersonalBook(Boolean bitIsPersonalBook) {
        this.bitIsPersonalBook = bitIsPersonalBook;
    }

    /** 
     *            @hibernate.property
     *             column="chvAudioNeeds"
     *             length="50"
     *         
     */
    public String getChvAudioNeeds() {
        return this.chvAudioNeeds;
    }

    public void setChvAudioNeeds(String chvAudioNeeds) {
        this.chvAudioNeeds = chvAudioNeeds;
    }

    /** 
     *            @hibernate.property
     *             column="chvSpecial"
     *             length="50"
     *         
     */
    public String getChvSpecial() {
        return this.chvSpecial;
    }

    public void setChvSpecial(String chvSpecial) {
        this.chvSpecial = chvSpecial;
    }

    /** 
     *            @hibernate.property
     *             column="bitIsSubmitDemoFile"
     *             length="1"
     *         
     */
    public Boolean getBitIsSubmitDemoFile() {
        return this.bitIsSubmitDemoFile;
    }

    public void setBitIsSubmitDemoFile(Boolean bitIsSubmitDemoFile) {
        this.bitIsSubmitDemoFile = bitIsSubmitDemoFile;
    }


    public String getChrEquipmentNo() {
        return this.chrEquipmentNo;
    }
    public void setChrEquipmentNo(String chrEquipmentNo) {
        this.chrEquipmentNo = chrEquipmentNo;
    }
    public String getChrPresenterPartiNo() {
        return this.chrPresenterPartiNo;
    }
    public void setChrPresenterPartiNo(String chrPresenterPartiNo) {
        this.chrPresenterPartiNo = chrPresenterPartiNo;
    }
    public String getChvPaperNumber() {
        return this.chvPaperNumber;
    }
    public void setChvPaperNumber(String chvPaperNumber) {
        this.chvPaperNumber = chvPaperNumber;
    }
    public String toString() {
        return new ToStringBuilder(this)
            .append("intIndex", getIntIndex())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof TpaperPresented) ) return false;
        TpaperPresented castOther = (TpaperPresented) other;
        return new EqualsBuilder()
            .append(this.getIntIndex(), castOther.getIntIndex())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getIntIndex())
            .toHashCode();
    }

}
