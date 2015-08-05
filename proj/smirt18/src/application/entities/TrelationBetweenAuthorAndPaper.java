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
public class TrelationBetweenAuthorAndPaper implements Serializable {

    /** identifier field */
    private String uidRelationId;

    /** persistent field */
    private Integer intAuthorRank;

    /** persistent field */
    private String chvPaperNumber;

    /** persistent field */
    private String chvPaperLNumber;

    /** persistent field */
    private String chrAuthorNo;


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
    public Integer getIntAuthorRank() {
        return this.intAuthorRank;
    }

    public void setIntAuthorRank(Integer inyAuthorRank) {
        this.intAuthorRank = inyAuthorRank;
    }

   
	/**
	 * @return Returns the chvPaperLNumber.
	 */
	public String getChvPaperLNumber() {
		return chvPaperLNumber;
	}
	/**
	 * @param chvPaperLNumber The chvPaperLNumber to set.
	 */
	public void setChvPaperLNumber(String chvPaperLNumber) {
		this.chvPaperLNumber = chvPaperLNumber;
	}
    public String getChrAuthorNo() {
        return this.chrAuthorNo;
    }
    public void setChrAuthorNo(String chrAuthorNo) {
        this.chrAuthorNo = chrAuthorNo;
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
        if ( !(other instanceof TrelationBetweenAuthorAndPaper) ) return false;
        TrelationBetweenAuthorAndPaper castOther = (TrelationBetweenAuthorAndPaper) other;
        return new EqualsBuilder()
            .append(this.getUidRelationId(), castOther.getUidRelationId())
            .append(this.getChrAuthorNo(), castOther.getChrAuthorNo())
            .append(this.getChvPaperNumber(), castOther.getChvPaperNumber())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getUidRelationId())
            .toHashCode();
    }

}
