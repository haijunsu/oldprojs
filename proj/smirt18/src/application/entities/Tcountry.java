package application.entities;

import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** 
 *        @hibernate.class
 *         table="T_Country"
 *     
*/
public class Tcountry implements Serializable {

    /** identifier field */
    private String chrCountryNo;

    /** persistent field */
    private String chvCountryName;

    /** full constructor */
    public Tcountry(String chrCountryNo, String chvCountryName) {
        this.chrCountryNo = chrCountryNo;
        this.chvCountryName = chvCountryName;
    }

    /** default constructor */
    public Tcountry() {
    }

    /** 
     *            @hibernate.id
     *             generator-class="assigned"
     *             type="java.lang.String"
     *             column="chrCountryNo"
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
     *             column="chvCountryName"
     *             length="200"
     *             not-null="true"
     *         
     */
    public String getChvCountryName() {
        return this.chvCountryName;
    }

    public void setChvCountryName(String chvCountryName) {
        this.chvCountryName = chvCountryName;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("chrCountryNo", getChrCountryNo())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof Tcountry) ) return false;
        Tcountry castOther = (Tcountry) other;
        return new EqualsBuilder()
            .append(this.getChrCountryNo(), castOther.getChrCountryNo())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getChrCountryNo())
            .toHashCode();
    }

}
