package application.entities;

import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** 
 *        @hibernate.class
 *         table="T_AffiliationType"
 *     
*/
public class TaffiliationType implements Serializable {

    /** identifier field */
    private String chrAffiliationTypeNo;

    /** persistent field */
    private String chvAffiliationType;

    /** full constructor */
    public TaffiliationType(String chrAffiliationTypeNo, String chvAffiliationType) {
        this.chrAffiliationTypeNo = chrAffiliationTypeNo;
        this.chvAffiliationType = chvAffiliationType;
    }

    /** default constructor */
    public TaffiliationType() {
    }

    /** 
     *            @hibernate.id
     *             generator-class="assigned"
     *             type="java.lang.String"
     *             column="chrAffiliationTypeNo"
     *         
     */
    public String getChrAffiliationTypeNo() {
        return this.chrAffiliationTypeNo;
    }

    public void setChrAffiliationTypeNo(String chrAffiliationTypeNo) {
        this.chrAffiliationTypeNo = chrAffiliationTypeNo;
    }

    /** 
     *            @hibernate.property
     *             column="chvAffiliationType"
     *             length="100"
     *             not-null="true"
     *         
     */
    public String getChvAffiliationType() {
        return this.chvAffiliationType;
    }

    public void setChvAffiliationType(String chvAffiliationType) {
        this.chvAffiliationType = chvAffiliationType;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("chrAffiliationTypeNo", getChrAffiliationTypeNo())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof TaffiliationType) ) return false;
        TaffiliationType castOther = (TaffiliationType) other;
        return new EqualsBuilder()
            .append(this.getChrAffiliationTypeNo(), castOther.getChrAffiliationTypeNo())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getChrAffiliationTypeNo())
            .toHashCode();
    }

}
