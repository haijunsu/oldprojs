package application.entities;

import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** 
 *        @hibernate.class
 *         table="T_PresentedPapers"
 *     
*/
public class TpresentedPapers implements Serializable {

    /** identifier field */
    private Integer intPaperPresenterId;

    /** identifier field */
    private Integer intPresentationNumber;

    /** identifier field */
    private String chrEquipmentNo;

    /** identifier field */
    private Boolean bitIsPresent;

    private String chvPaperNumber;
    
    private String chrSessionNo;

    /** default constructor */
    public TpresentedPapers() {
    }

    /** 
     *                @hibernate.property
     *                 column="intPaperPresenterID"
     *                 length="10"
     *             
     */
    public Integer getIntPaperPresenterId() {
        return this.intPaperPresenterId;
    }

    public void setIntPaperPresenterId(Integer intPaperPresenterId) {
        this.intPaperPresenterId = intPaperPresenterId;
    }

    /** 
     *                @hibernate.property
     *                 column="intPresentationNumber"
     *                 length="10"
     *             
     */
    public Integer getIntPresentationNumber() {
        return this.intPresentationNumber;
    }

    public void setIntPresentationNumber(Integer intPresentationNumber) {
        this.intPresentationNumber = intPresentationNumber;
    }

    /** 
     *                @hibernate.property
     *                 column="chrEquipmentNo"
     *                 length="1"
     *             
     */
    public String getChrEquipmentNo() {
        return this.chrEquipmentNo;
    }

    public void setChrEquipmentNo(String chrEquipmentNo) {
        this.chrEquipmentNo = chrEquipmentNo;
    }

    /** 
     *                @hibernate.property
     *                 column="bitIsPresent"
     *                 length="1"
     *             
     */
    public Boolean getBitIsPresent() {
        return this.bitIsPresent;
    }

    public void setBitIsPresent(Boolean bitIsPresent) {
        this.bitIsPresent = bitIsPresent;
    }


    public String getChrSessionNo() {
        return this.chrSessionNo;
    }
    public void setChrSessionNo(String chrSessionNo) {
        this.chrSessionNo = chrSessionNo;
    }
    public String getChvPaperNumber() {
        return this.chvPaperNumber;
    }
    public void setChvPaperNumber(String chvPaperNumber) {
        this.chvPaperNumber = chvPaperNumber;
    }
    public String toString() {
        return new ToStringBuilder(this)
            .append("intPaperPresenterId", getIntPaperPresenterId())
            .append("intPresentationNumber", getIntPresentationNumber())
            .append("chrEquipmentNo", getChrEquipmentNo())
            .append("bitIsPresent", getBitIsPresent())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof TpresentedPapers) ) return false;
        TpresentedPapers castOther = (TpresentedPapers) other;
        return new EqualsBuilder()
            .append(this.getIntPaperPresenterId(), castOther.getIntPaperPresenterId())
            .append(this.getIntPresentationNumber(), castOther.getIntPresentationNumber())
            .append(this.getChrEquipmentNo(), castOther.getChrEquipmentNo())
            .append(this.getBitIsPresent(), castOther.getBitIsPresent())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getIntPaperPresenterId())
            .append(getIntPresentationNumber())
            .append(getChrEquipmentNo())
            .append(getBitIsPresent())
            .toHashCode();
    }

}
