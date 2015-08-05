package application.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** 
 *        @hibernate.class
 *         table="T_Accommodation"
 *     
*/
public class Taccommodation implements Serializable {

    /** identifier field */
    private String chrAccommodationNo;

    /** nullable persistent field */
    private String chvHotalName;

    /** nullable persistent field */
    private String chvRoomNumber;

    /** persistent field */
    private String chvRoomtype;

    /** nullable persistent field */
    private String dtmCheckIn;

    /** nullable persistent field */
    private String dtmCheckout;

    /** nullable persistent field */
    private String dtmBookDate;

    /** nullable persistent field */
    private BigDecimal mnyBookFee;

    /** persistent field */
    private String chrPartiNo;

    /** full constructor */
    public Taccommodation(String chrAccommodationNo, String chvHotalName, String chvRoomNumber, String chvRoomtype, String dtmCheckIn, String dtmCheckout, String dtmBookDate, BigDecimal mnyBookFee, application.entities.Tparticipant tparticipant) {
        this.chrAccommodationNo = chrAccommodationNo;
        this.chvHotalName = chvHotalName;
        this.chvRoomNumber = chvRoomNumber;
        this.chvRoomtype = chvRoomtype;
        this.dtmCheckIn = dtmCheckIn;
        this.dtmCheckout = dtmCheckout;
        this.dtmBookDate = dtmBookDate;
        this.mnyBookFee = mnyBookFee;
    }

    /** default constructor */
    public Taccommodation() {
    }

    /** minimal constructor */
    public Taccommodation(String chrAccommodationNo, String chvRoomtype, application.entities.Tparticipant tparticipant) {
        this.chrAccommodationNo = chrAccommodationNo;
        this.chvRoomtype = chvRoomtype;
    }

    /** 
     *            @hibernate.id
     *             generator-class="assigned"
     *             type="java.lang.String"
     *             column="chrAccommodationNo"
     *         
     */
    public String getChrAccommodationNo() {
        return this.chrAccommodationNo;
    }

    public void setChrAccommodationNo(String chrAccommodationNo) {
        this.chrAccommodationNo = chrAccommodationNo;
    }

    /** 
     *            @hibernate.property
     *             column="chvHotalName"
     *             length="100"
     *         
     */
    public String getChvHotalName() {
        return this.chvHotalName;
    }

    public void setChvHotalName(String chvHotalName) {
        this.chvHotalName = chvHotalName;
    }

    /** 
     *            @hibernate.property
     *             column="chvRoomNumber"
     *             length="20"
     *         
     */
    public String getChvRoomNumber() {
        return this.chvRoomNumber;
    }

    public void setChvRoomNumber(String chvRoomNumber) {
        this.chvRoomNumber = chvRoomNumber;
    }

    /** 
     *            @hibernate.property
     *             column="chvRoomtype"
     *             length="1"
     *             not-null="true"
     *         
     */
    public String getChvRoomtype() {
        return this.chvRoomtype;
    }

    public void setChvRoomtype(String chvRoomtype) {
        this.chvRoomtype = chvRoomtype;
    }

    /** 
     *            @hibernate.property
     *             column="dtmCheckIn"
     *             length="23"
     *         
     */
    public String getDtmCheckIn() {
        return this.dtmCheckIn;
    }

    public void setDtmCheckIn(String dtmCheckIn) {
        this.dtmCheckIn = dtmCheckIn;
    }

    /** 
     *            @hibernate.property
     *             column="dtmCheckout"
     *             length="23"
     *         
     */
    public String getDtmCheckout() {
        return this.dtmCheckout;
    }

    public void setDtmCheckout(String dtmCheckout) {
        this.dtmCheckout = dtmCheckout;
    }

    /** 
     *            @hibernate.property
     *             column="dtmBookDate"
     *             length="23"
     *         
     */
    public String getDtmBookDate() {
        return this.dtmBookDate;
    }

    public void setDtmBookDate(String dtmBookDate) {
        this.dtmBookDate = dtmBookDate;
    }

    /** 
     *            @hibernate.property
     *             column="mnyBookFee"
     *             length="19"
     *         
     */
    public BigDecimal getMnyBookFee() {
        return this.mnyBookFee;
    }

    public void setMnyBookFee(BigDecimal mnyBookFee) {
        this.mnyBookFee = mnyBookFee;
    }


    public String getChrPartiNo() {
        return this.chrPartiNo;
    }
    public void setChrPartiNo(String chrPartiNo) {
        this.chrPartiNo = chrPartiNo;
    }
    public String toString() {
        return new ToStringBuilder(this)
            .append("chrAccommodationNo", getChrAccommodationNo())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof Taccommodation) ) return false;
        Taccommodation castOther = (Taccommodation) other;
        return new EqualsBuilder()
            .append(this.getChrAccommodationNo(), castOther.getChrAccommodationNo())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getChrAccommodationNo())
            .toHashCode();
    }

}
