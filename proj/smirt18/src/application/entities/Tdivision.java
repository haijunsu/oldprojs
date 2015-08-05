package application.entities;

import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** 
 *        @hibernate.class
 *         table="T_Division"
 *     
*/
public class Tdivision implements Serializable {

    /** identifier field */
    private String chrDivisionNo;

    /** persistent field */
    private String chvDivisionTitle;

    /** nullable persistent field */
    private String txtDivisionIntro;

    /** full constructor */
    public Tdivision(String chrDivisionNo, String chvDivisionTitle, String txtDivisionIntro) {
        this.chrDivisionNo = chrDivisionNo;
        this.chvDivisionTitle = chvDivisionTitle;
        this.txtDivisionIntro = txtDivisionIntro;
    }

    /** default constructor */
    public Tdivision() {
    }

    /** minimal constructor */
    public Tdivision(String chrDivisionNo, String chvDivisionTitle) {
        this.chrDivisionNo = chrDivisionNo;
        this.chvDivisionTitle = chvDivisionTitle;
    }

    /** 
     *            @hibernate.id
     *             generator-class="assigned"
     *             type="java.lang.String"
     *             column="chrDivisionNo"
     *         
     */
    public String getChrDivisionNo() {
        return this.chrDivisionNo;
    }

    public void setChrDivisionNo(String chrDivisionNo) {
        this.chrDivisionNo = chrDivisionNo;
    }

    /** 
     *            @hibernate.property
     *             column="chvDivisionTitle"
     *             length="100"
     *             not-null="true"
     *         
     */
    public String getChvDivisionTitle() {
        return this.chvDivisionTitle;
    }

    public void setChvDivisionTitle(String chvDivisionTitle) {
        this.chvDivisionTitle = chvDivisionTitle;
    }

    /** 
     *            @hibernate.property
     *             column="txtDivisionIntro"
     *             length="2147483647"
     *         
     */
    public String getTxtDivisionIntro() {
        return this.txtDivisionIntro;
    }

    public void setTxtDivisionIntro(String txtDivisionIntro) {
        this.txtDivisionIntro = txtDivisionIntro;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("chrDivisionNo", getChrDivisionNo())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof Tdivision) ) return false;
        Tdivision castOther = (Tdivision) other;
        return new EqualsBuilder()
            .append(this.getChrDivisionNo(), castOther.getChrDivisionNo())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getChrDivisionNo())
            .toHashCode();
    }

}
