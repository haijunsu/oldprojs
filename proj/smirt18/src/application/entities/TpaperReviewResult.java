package application.entities;

import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** 
 *        @hibernate.class
 *         table="T_PaperReviewResult"
 *     
*/
public class TpaperReviewResult implements Serializable {

    /** identifier field */
    private String uidPaperReviewResult;

    /** nullable persistent field */
    private Integer inyPaperReviewResult;

    /** nullable persistent field */
    private String txtPaperReviewMemo;

    /** nullable persistent field */
    private Boolean bitIsInformed;

    /** nullable persistent field */
    private Boolean bitIsMail;

    /** persistent field */
    private String chvPaperNumber;


    /** 
     *            @hibernate.id
     *             generator-class="assigned"
     *             type="java.lang.String"
     *             column="uidPaperReviewResult"
     *         
     */
    public String getUidPaperReviewResult() {
        return this.uidPaperReviewResult;
    }

    public void setUidPaperReviewResult(String uidPaperReviewResult) {
        this.uidPaperReviewResult = uidPaperReviewResult;
    }

    /** 
     *            @hibernate.property
     *             column="inyPaperReviewResult"
     *             length="3"
     *         
     */
    public Integer getInyPaperReviewResult() {
        return this.inyPaperReviewResult;
    }

    public void setInyPaperReviewResult(Integer inyPaperReviewResult) {
        this.inyPaperReviewResult = inyPaperReviewResult;
    }

    /** 
     *            @hibernate.property
     *             column="txtPaperReviewMemo"
     *             length="2147483647"
     *         
     */
    public String getTxtPaperReviewMemo() {
        return this.txtPaperReviewMemo;
    }

    public void setTxtPaperReviewMemo(String txtPaperReviewMemo) {
        this.txtPaperReviewMemo = txtPaperReviewMemo;
    }

    /** 
     *            @hibernate.property
     *             column="bitIsInformed"
     *             length="1"
     *         
     */
    public Boolean getBitIsInformed() {
        return this.bitIsInformed;
    }

    public void setBitIsInformed(Boolean bitIsInformed) {
        this.bitIsInformed = bitIsInformed;
    }

    /** 
     *            @hibernate.property
     *             column="bitIsMail"
     *             length="1"
     *         
     */
    public Boolean getBitIsMail() {
        return this.bitIsMail;
    }

    public void setBitIsMail(Boolean bitIsMail) {
        this.bitIsMail = bitIsMail;
    }


    public String getChvPaperNumber() {
        return this.chvPaperNumber;
    }
    public void setChvPaperNumber(String chvPaperNumber) {
        this.chvPaperNumber = chvPaperNumber;
    }
    public String toString() {
        return new ToStringBuilder(this)
            .append("uidPaperReviewResult", getUidPaperReviewResult())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof TpaperReviewResult) ) return false;
        TpaperReviewResult castOther = (TpaperReviewResult) other;
        return new EqualsBuilder()
            .append(this.getUidPaperReviewResult(), castOther.getUidPaperReviewResult())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getUidPaperReviewResult())
            .toHashCode();
    }

}
