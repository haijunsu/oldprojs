package application.entities;

import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** 
 *        @hibernate.class
 *         table="T_UserType"
 *     
*/
public class TuserType implements Serializable {

    /** identifier field */
    private String chrTypeNo;

    /** persistent field */
    private String chvUserType;

    /** nullable persistent field */
    private String txtMemo;

    /** full constructor */
    public TuserType(String chrTypeNo, String chvUserType, String txtMemo) {
        this.chrTypeNo = chrTypeNo;
        this.chvUserType = chvUserType;
        this.txtMemo = txtMemo;
    }

    /** default constructor */
    public TuserType() {
    }

    /** minimal constructor */
    public TuserType(String chrTypeNo, String chvUserType) {
        this.chrTypeNo = chrTypeNo;
        this.chvUserType = chvUserType;
    }

    /** 
     *            @hibernate.id
     *             generator-class="assigned"
     *             type="java.lang.String"
     *             column="chrTypeNo"
     *         
     */
    public String getChrTypeNo() {
        return this.chrTypeNo;
    }

    public void setChrTypeNo(String chrTypeNo) {
        this.chrTypeNo = chrTypeNo;
    }

    /** 
     *            @hibernate.property
     *             column="chvUserType"
     *             length="30"
     *             not-null="true"
     *         
     */
    public String getChvUserType() {
        return this.chvUserType;
    }

    public void setChvUserType(String chvUserType) {
        this.chvUserType = chvUserType;
    }

    /** 
     *            @hibernate.property
     *             column="txtMemo"
     *             length="2147483647"
     *         
     */
    public String getTxtMemo() {
        return this.txtMemo;
    }

    public void setTxtMemo(String txtMemo) {
        this.txtMemo = txtMemo;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("chrTypeNo", getChrTypeNo())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof TuserType) ) return false;
        TuserType castOther = (TuserType) other;
        return new EqualsBuilder()
            .append(this.getChrTypeNo(), castOther.getChrTypeNo())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getChrTypeNo())
            .toHashCode();
    }

}
