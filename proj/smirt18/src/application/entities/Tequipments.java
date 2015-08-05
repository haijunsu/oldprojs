package application.entities;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** 
 *        @hibernate.class
 *         table="T_Equipments"
 *     
*/
public class Tequipments implements Serializable {

    /** identifier field */
    private String chrEquipmentNo;

    /** nullable persistent field */
    private String chvEquipmentName;

    /** nullable persistent field */
    private String txtEquipmentIntro;


    /** 
     *            @hibernate.id
     *             generator-class="assigned"
     *             type="java.lang.String"
     *             column="chrEquipmentNo"
     *         
     */
    public String getChrEquipmentNo() {
        return this.chrEquipmentNo;
    }

    public void setChrEquipmentNo(String chrEquipmentNo) {
        this.chrEquipmentNo = chrEquipmentNo;
    }

    /** 
     *            @hibernate.property
     *             column="chvEquipmentName"
     *             length="100"
     *         
     */
    public String getChvEquipmentName() {
        return this.chvEquipmentName;
    }

    public void setChvEquipmentName(String chvEquipmentName) {
        this.chvEquipmentName = chvEquipmentName;
    }

    /** 
     *            @hibernate.property
     *             column="txtEquipmentIntro"
     *             length="2147483647"
     *         
     */
    public String getTxtEquipmentIntro() {
        return this.txtEquipmentIntro;
    }

    public void setTxtEquipmentIntro(String txtEquipmentIntro) {
        this.txtEquipmentIntro = txtEquipmentIntro;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("chrEquipmentNo", getChrEquipmentNo())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof Tequipments) ) return false;
        Tequipments castOther = (Tequipments) other;
        return new EqualsBuilder()
            .append(this.getChrEquipmentNo(), castOther.getChrEquipmentNo())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getChrEquipmentNo())
            .toHashCode();
    }

}
