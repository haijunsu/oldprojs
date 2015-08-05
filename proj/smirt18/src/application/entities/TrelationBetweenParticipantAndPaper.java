package application.entities;

import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** 
 *        @hibernate.class
 *         table="T_RelationBetweenParticipantAndPaper"
 *     
*/
public class TrelationBetweenParticipantAndPaper implements Serializable {

    /** identifier field */
    private String uidRelationId;

    /** persistent field */
    private Integer inyAuthorRank;

    /** persistent field */
    private Integer intUserId;

    /** persistent field */
    private String chvPaperNumber;

    /** persistent field */
    private String chrPartiNo;


    /** 
     *            @hibernate.id
     *             generator-class="assigned"
     *             type="java.lang.String"
     *             column="uidRelationID"
     *         
     */
    public String getUidRelationId() {
        return this.uidRelationId;
    }

    public void setUidRelationId(String uidRelationId) {
        this.uidRelationId = uidRelationId;
    }

    /** 
     *            @hibernate.property
     *             column="inyAuthorRank"
     *             length="3"
     *             not-null="true"
     *         
     */
    public Integer getInyAuthorRank() {
        return this.inyAuthorRank;
    }

    public void setInyAuthorRank(Integer inyAuthorRank) {
        this.inyAuthorRank = inyAuthorRank;
    }

    /** 
     *            @hibernate.property
     *             column="intUserID"
     *             length="10"
     *             not-null="true"
     *         
     */
    public Integer getIntUserID() {
        return this.intUserId;
    }

    public void setIntUserID(Integer intUserId) {
        this.intUserId = intUserId;
    }


    
    public String getChrPartiNo() {
        return this.chrPartiNo;
    }
    public void setChrPartiNo(String chrPartiNo) {
        this.chrPartiNo = chrPartiNo;
    }
    public String getChvPaperNumber() {
        return this.chvPaperNumber;
    }
    public void setChvPaperNumber(String chvPaperNumber) {
        this.chvPaperNumber = chvPaperNumber;
    }
    public String toString() {
        return new ToStringBuilder(this)
            .append("uidRelationId", getUidRelationId())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof TrelationBetweenParticipantAndPaper) ) return false;
        TrelationBetweenParticipantAndPaper castOther = (TrelationBetweenParticipantAndPaper) other;
        return new EqualsBuilder()
            .append(this.getUidRelationId(), castOther.getUidRelationId())
            .append(this.getChrPartiNo(), castOther.getChrPartiNo())
            .append(this.getChvPaperNumber(), castOther.getChvPaperNumber())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getUidRelationId())
            .toHashCode();
    }

}
