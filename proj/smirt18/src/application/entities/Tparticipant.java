package application.entities;

import java.io.Serializable;
import java.util.Set;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** 
 *        @hibernate.class
 *         table="T_Participant"
 *     
*/
public class Tparticipant implements Serializable {
	
	private String chrAuthorNo;

    /** identifier field */
    private String chrPartiNo;

    /** persistent field */
    private String chvPartiTitle;

    /** persistent field */
    private String chvPartiFirstName;

    /** nullable persistent field */
    private String chvPartiMiddleName;

    /** persistent field */
    private String chvPartiLastName;

    /** persistent field */
    private String chvPartiAffiliation;

    /** persistent field */
    private String chvPartiPosition;

    /** persistent field */
    private String chvPartiDepartment;

    /** persistent field */
    private String chvPartiAddress;

    /** persistent field */
    private String chvPartiCity;

    /** nullable persistent field */
    private String chvPartiProvince;

    /** persistent field */
    private String chvPartiCountry;

    /** persistent field */
    private String chvPartiPostalCode;

    /** persistent field */
    private String chvPartiTeleNumber;

    /** persistent field */
    private String chvPartiFax;

    /** persistent field */
    private String chvPartiEmail;

    /** nullable persistent field */
    private String chvPartiHomeTeleNumber;

    /** nullable persistent field */
    private String chvPartiHomeFax;

    /** nullable persistent field */
    private String chvPartiHomeEmail;

    /** nullable persistent field */
    private Boolean bitIspartiAccompanied;

    /** nullable persistent field */
    private Boolean bitIsPartiIsMember;

    /** persistent field */
    private String chvPassportNumber;

    /** nullable persistent field */
    private Boolean bitIsNeedVisa;

    /** nullable persistent field */
    private Boolean bitIsCheckin;

    /** nullable persistent field */
    private String dtmCkeckinTime;

    /** nullable persistent field */
    private Boolean bitIsDrawnFile;

    /** nullable persistent field */
    private Boolean bitIsAttendReception;

    /** nullable persistent field */
    private Boolean bitIsAttendBanquet;

    /** nullable persistent field */
    private Boolean bitIsAttendTechTour;
    
    private Boolean bitIsInformed;
    private Boolean bitIsBiography;

    /** persistent field */
    private Set tpaperPresenteds;

    /** persistent field */
    private Set tregistrationFees;

    /** persistent field */
    private Set taccommodations;

    /** persistent field */
    private Set taccompanyPersons;

    /** persistent field */
    private Set trelationBetweenParticipantAndPapers;

    /** persistent field */
    private Set tparticipantCheckins;

    /** persistent field */
    private Set tvisas;

    /** persistent field */
    private Set ttours;

    /** default constructor */
    public Tparticipant() {
    }

    /** minimal constructor */
    public Tparticipant(String chrPartiNo, String chvPartiTitle, String chvPartiFirstName, String chvPartiLastName, String chvPartiAffiliation, String chvPartiPosition, String chvPartiDepartment, String chvPartiAddress, String chvPartiCity, String chvPartiCountry, String chvPartiPostalCode, String chvPartiTeleNumber, String chvPartiFax, String chvPartiEmail, String chvPassportNumber, Set tpaperPresenteds, Set tregistrationFees, Set taccommodations, Set taccompanyPersons, Set trelationBetweenParticipantAndPapers, Set tparticipantCheckins, Set tvisas, Set ttours) {
        this.chrPartiNo = chrPartiNo;
        this.chvPartiTitle = chvPartiTitle;
        this.chvPartiFirstName = chvPartiFirstName;
        this.chvPartiLastName = chvPartiLastName;
        this.chvPartiAffiliation = chvPartiAffiliation;
        this.chvPartiPosition = chvPartiPosition;
        this.chvPartiDepartment = chvPartiDepartment;
        this.chvPartiAddress = chvPartiAddress;
        this.chvPartiCity = chvPartiCity;
        this.chvPartiCountry = chvPartiCountry;
        this.chvPartiPostalCode = chvPartiPostalCode;
        this.chvPartiTeleNumber = chvPartiTeleNumber;
        this.chvPartiFax = chvPartiFax;
        this.chvPartiEmail = chvPartiEmail;
        this.chvPassportNumber = chvPassportNumber;
        this.tpaperPresenteds = tpaperPresenteds;
        this.tregistrationFees = tregistrationFees;
        this.taccommodations = taccommodations;
        this.taccompanyPersons = taccompanyPersons;
        this.trelationBetweenParticipantAndPapers = trelationBetweenParticipantAndPapers;
        this.tparticipantCheckins = tparticipantCheckins;
        this.tvisas = tvisas;
        this.ttours = ttours;
    }

    /** 
     *            @hibernate.id
     *             generator-class="assigned"
     *             type="java.lang.String"
     *             column="chrPartiNo"
     *         
     */
    public String getChrPartiNo() {
        return this.chrPartiNo;
    }

    public void setChrPartiNo(String chrPartiNo) {
        this.chrPartiNo = chrPartiNo;
    }

    /** 
     *            @hibernate.property
     *             column="chvPartiTitle"
     *             length="20"
     *             not-null="true"
     *         
     */
    public String getChvPartiTitle() {
        return this.chvPartiTitle;
    }

    public void setChvPartiTitle(String chvPartiTitle) {
        this.chvPartiTitle = chvPartiTitle;
    }

    /** 
     *            @hibernate.property
     *             column="chvPartiFirstName"
     *             length="100"
     *             not-null="true"
     *         
     */
    public String getChvPartiFirstName() {
        return this.chvPartiFirstName;
    }

    public void setChvPartiFirstName(String chvPartiFirstName) {
        this.chvPartiFirstName = chvPartiFirstName;
    }

    /** 
     *            @hibernate.property
     *             column="chvPartiMiddleName"
     *             length="100"
     *         
     */
    public String getChvPartiMiddleName() {
        return this.chvPartiMiddleName;
    }

    public void setChvPartiMiddleName(String chvPartiMiddleName) {
        this.chvPartiMiddleName = chvPartiMiddleName;
    }

    /** 
     *            @hibernate.property
     *             column="chvPartiLastName"
     *             length="100"
     *             not-null="true"
     *         
     */
    public String getChvPartiLastName() {
        return this.chvPartiLastName;
    }

    public void setChvPartiLastName(String chvPartiLastName) {
        this.chvPartiLastName = chvPartiLastName;
    }

    /** 
     *            @hibernate.property
     *             column="chvPartiAffiliation"
     *             length="200"
     *             not-null="true"
     *         
     */
    public String getChvPartiAffiliation() {
        return this.chvPartiAffiliation;
    }

    public void setChvPartiAffiliation(String chvPartiAffiliation) {
        this.chvPartiAffiliation = chvPartiAffiliation;
    }

    /** 
     *            @hibernate.property
     *             column="chvPartiPosition"
     *             length="20"
     *             not-null="true"
     *         
     */
    public String getChvPartiPosition() {
        return this.chvPartiPosition;
    }

    public void setChvPartiPosition(String chvPartiPosition) {
        this.chvPartiPosition = chvPartiPosition;
    }

    /** 
     *            @hibernate.property
     *             column="chvPartiDepartment"
     *             length="60"
     *             not-null="true"
     *         
     */
    public String getChvPartiDepartment() {
        return this.chvPartiDepartment;
    }

    public void setChvPartiDepartment(String chvPartiDepartment) {
        this.chvPartiDepartment = chvPartiDepartment;
    }

    /** 
     *            @hibernate.property
     *             column="chvPartiAddress"
     *             length="300"
     *             not-null="true"
     *         
     */
    public String getChvPartiAddress() {
        return this.chvPartiAddress;
    }

    public void setChvPartiAddress(String chvPartiAddress) {
        this.chvPartiAddress = chvPartiAddress;
    }

    /** 
     *            @hibernate.property
     *             column="chvPartiCity"
     *             length="100"
     *             not-null="true"
     *         
     */
    public String getChvPartiCity() {
        return this.chvPartiCity;
    }

    public void setChvPartiCity(String chvPartiCity) {
        this.chvPartiCity = chvPartiCity;
    }

    /** 
     *            @hibernate.property
     *             column="chvPartiProvince"
     *             length="100"
     *         
     */
    public String getChvPartiProvince() {
        return this.chvPartiProvince;
    }

    public void setChvPartiProvince(String chvPartiProvince) {
        this.chvPartiProvince = chvPartiProvince;
    }

    /** 
     *            @hibernate.property
     *             column="chvPartiCountry"
     *             length="100"
     *             not-null="true"
     *         
     */
    public String getChvPartiCountry() {
        return this.chvPartiCountry;
    }

    public void setChvPartiCountry(String chvPartiCountry) {
        this.chvPartiCountry = chvPartiCountry;
    }

    /** 
     *            @hibernate.property
     *             column="chvPartiPostalCode"
     *             length="20"
     *             not-null="true"
     *         
     */
    public String getChvPartiPostalCode() {
        return this.chvPartiPostalCode;
    }

    public void setChvPartiPostalCode(String chvPartiPostalCode) {
        this.chvPartiPostalCode = chvPartiPostalCode;
    }

    /** 
     *            @hibernate.property
     *             column="chvPartiTeleNumber"
     *             length="50"
     *             not-null="true"
     *         
     */
    public String getChvPartiTeleNumber() {
        return this.chvPartiTeleNumber;
    }

    public void setChvPartiTeleNumber(String chvPartiTeleNumber) {
        this.chvPartiTeleNumber = chvPartiTeleNumber;
    }

    /** 
     *            @hibernate.property
     *             column="chvPartiFax"
     *             length="50"
     *             not-null="true"
     *         
     */
    public String getChvPartiFax() {
        return this.chvPartiFax;
    }

    public void setChvPartiFax(String chvPartiFax) {
        this.chvPartiFax = chvPartiFax;
    }

    /** 
     *            @hibernate.property
     *             column="chvPartiEmail"
     *             length="100"
     *             not-null="true"
     *         
     */
    public String getChvPartiEmail() {
        return this.chvPartiEmail;
    }

    public void setChvPartiEmail(String chvPartiEmail) {
        this.chvPartiEmail = chvPartiEmail;
    }

    /** 
     *            @hibernate.property
     *             column="chvPartiHomeTeleNumber"
     *             length="50"
     *         
     */
    public String getChvPartiHomeTeleNumber() {
        return this.chvPartiHomeTeleNumber;
    }

    public void setChvPartiHomeTeleNumber(String chvPartiHomeTeleNumber) {
        this.chvPartiHomeTeleNumber = chvPartiHomeTeleNumber;
    }

    /** 
     *            @hibernate.property
     *             column="chvPartiHomeFax"
     *             length="50"
     *         
     */
    public String getChvPartiHomeFax() {
        return this.chvPartiHomeFax;
    }

    public void setChvPartiHomeFax(String chvPartiHomeFax) {
        this.chvPartiHomeFax = chvPartiHomeFax;
    }

    /** 
     *            @hibernate.property
     *             column="chvPartiHomeEmail"
     *             length="100"
     *         
     */
    public String getChvPartiHomeEmail() {
        return this.chvPartiHomeEmail;
    }

    public void setChvPartiHomeEmail(String chvPartiHomeEmail) {
        this.chvPartiHomeEmail = chvPartiHomeEmail;
    }

    /** 
     *            @hibernate.property
     *             column="bitISPartiAccompanied"
     *             length="1"
     *         
     */
    public Boolean getBitISPartiAccompanied() {
        return this.bitIspartiAccompanied;
    }

    public void setBitISPartiAccompanied(Boolean bitIspartiAccompanied) {
        this.bitIspartiAccompanied = bitIspartiAccompanied;
    }

    /** 
     *            @hibernate.property
     *             column="bitIsPartiIsMember"
     *             length="1"
     *         
     */
    public Boolean getBitIsPartiIsMember() {
        return this.bitIsPartiIsMember;
    }

    public void setBitIsPartiIsMember(Boolean bitIsPartiIsMember) {
        this.bitIsPartiIsMember = bitIsPartiIsMember;
    }

    /** 
     *            @hibernate.property
     *             column="chvPassportNumber"
     *             length="20"
     *             not-null="true"
     *         
     */
    public String getChvPassportNumber() {
        return this.chvPassportNumber;
    }

    public void setChvPassportNumber(String chvPassportNumber) {
        this.chvPassportNumber = chvPassportNumber;
    }

    /** 
     *            @hibernate.property
     *             column="bitIsNeedVisa"
     *             length="1"
     *         
     */
    public Boolean getBitIsNeedVisa() {
        return this.bitIsNeedVisa;
    }

    public void setBitIsNeedVisa(Boolean bitIsNeedVisa) {
        this.bitIsNeedVisa = bitIsNeedVisa;
    }

    /** 
     *            @hibernate.property
     *             column="bitIsCheckin"
     *             length="1"
     *         
     */
    public Boolean getBitIsCheckin() {
        return this.bitIsCheckin;
    }

    public void setBitIsCheckin(Boolean bitIsCheckin) {
        this.bitIsCheckin = bitIsCheckin;
    }

    /** 
     *            @hibernate.property
     *             column="dtmCkeckinTime"
     *             length="23"
     *         
     */
    public String getDtmCkeckinTime() {
        return this.dtmCkeckinTime;
    }

    public void setDtmCkeckinTime(String dtmCkeckinTime) {
        this.dtmCkeckinTime = dtmCkeckinTime;
    }

    /** 
     *            @hibernate.property
     *             column="bitIsDrawnFile"
     *             length="1"
     *         
     */
    public Boolean getBitIsDrawnFile() {
        return this.bitIsDrawnFile;
    }

    public void setBitIsDrawnFile(Boolean bitIsDrawnFile) {
        this.bitIsDrawnFile = bitIsDrawnFile;
    }

    /** 
     *            @hibernate.property
     *             column="bitIsAttendReception"
     *             length="1"
     *         
     */
    public Boolean getBitIsAttendReception() {
        return this.bitIsAttendReception;
    }

    public void setBitIsAttendReception(Boolean bitIsAttendReception) {
        this.bitIsAttendReception = bitIsAttendReception;
    }

    /** 
     *            @hibernate.property
     *             column="bitIsAttendBanquet"
     *             length="1"
     *         
     */
    public Boolean getBitIsAttendBanquet() {
        return this.bitIsAttendBanquet;
    }

    public void setBitIsAttendBanquet(Boolean bitIsAttendBanquet) {
        this.bitIsAttendBanquet = bitIsAttendBanquet;
    }

    /** 
     *            @hibernate.property
     *             column="bitIsAttendTechTour"
     *             length="1"
     *         
     */
    public Boolean getBitIsAttendTechTour() {
        return this.bitIsAttendTechTour;
    }

    public void setBitIsAttendTechTour(Boolean bitIsAttendTechTour) {
        this.bitIsAttendTechTour = bitIsAttendTechTour;
    }

    /** 
     *            @hibernate.set
     *             lazy="true"
     *             inverse="true"
     *             cascade="none"
     *            @hibernate.collection-key
     *             column="chrPresenterPartiNo"
     *            @hibernate.collection-one-to-many
     *             class="application.entities.TpaperPresented"
     *         
     */
    public Set getTpaperPresenteds() {
        return this.tpaperPresenteds;
    }

    public void setTpaperPresenteds(Set tpaperPresenteds) {
        this.tpaperPresenteds = tpaperPresenteds;
    }

    /** 
     *            @hibernate.set
     *             lazy="true"
     *             inverse="true"
     *             cascade="none"
     *            @hibernate.collection-key
     *             column="chrPartiNo"
     *            @hibernate.collection-one-to-many
     *             class="application.entities.TregistrationFee"
     *         
     */
    public Set getTregistrationFees() {
        return this.tregistrationFees;
    }

    public void setTregistrationFees(Set tregistrationFees) {
        this.tregistrationFees = tregistrationFees;
    }

    /** 
     *            @hibernate.set
     *             lazy="true"
     *             inverse="true"
     *             cascade="none"
     *            @hibernate.collection-key
     *             column="chrPartiNo"
     *            @hibernate.collection-one-to-many
     *             class="application.entities.Taccommodation"
     *         
     */
    public Set getTaccommodations() {
        return this.taccommodations;
    }

    public void setTaccommodations(Set taccommodations) {
        this.taccommodations = taccommodations;
    }

    /** 
     *            @hibernate.set
     *             lazy="true"
     *             inverse="true"
     *             cascade="none"
     *            @hibernate.collection-key
     *             column="chrPartiNo"
     *            @hibernate.collection-one-to-many
     *             class="application.entities.TaccompanyPerson"
     *         
     */
    public Set getTaccompanyPersons() {
        return this.taccompanyPersons;
    }

    public void setTaccompanyPersons(Set taccompanyPersons) {
        this.taccompanyPersons = taccompanyPersons;
    }

    /** 
     *            @hibernate.set
     *             lazy="true"
     *             inverse="true"
     *             cascade="none"
     *            @hibernate.collection-key
     *             column="chrPartiNo"
     *            @hibernate.collection-one-to-many
     *             class="application.entities.TrelationBetweenParticipantAndPaper"
     *         
     */
    public Set getTrelationBetweenParticipantAndPapers() {
        return this.trelationBetweenParticipantAndPapers;
    }

    public void setTrelationBetweenParticipantAndPapers(Set trelationBetweenParticipantAndPapers) {
        this.trelationBetweenParticipantAndPapers = trelationBetweenParticipantAndPapers;
    }

    /** 
     *            @hibernate.set
     *             lazy="true"
     *             inverse="true"
     *             cascade="none"
     *            @hibernate.collection-key
     *             column="chrPartiNo"
     *            @hibernate.collection-one-to-many
     *             class="application.entities.TparticipantCheckin"
     *         
     */
    public Set getTparticipantCheckins() {
        return this.tparticipantCheckins;
    }

    public void setTparticipantCheckins(Set tparticipantCheckins) {
        this.tparticipantCheckins = tparticipantCheckins;
    }

    /** 
     *            @hibernate.set
     *             lazy="true"
     *             inverse="true"
     *             cascade="none"
     *            @hibernate.collection-key
     *             column="chrPartiNo"
     *            @hibernate.collection-one-to-many
     *             class="application.entities.Tvisa"
     *         
     */
    public Set getTvisas() {
        return this.tvisas;
    }

    public void setTvisas(Set tvisas) {
        this.tvisas = tvisas;
    }

    /** 
     *            @hibernate.set
     *             lazy="true"
     *             inverse="true"
     *             cascade="none"
     *            @hibernate.collection-key
     *             column="chrPartiNo"
     *            @hibernate.collection-one-to-many
     *             class="application.entities.Ttour"
     *         
     */
    public Set getTtours() {
        return this.ttours;
    }

    public void setTtours(Set ttours) {
        this.ttours = ttours;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("chrPartiNo", getChrPartiNo())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof Tparticipant) ) return false;
        Tparticipant castOther = (Tparticipant) other;
        return new EqualsBuilder()
            .append(this.getChrPartiNo(), castOther.getChrPartiNo())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getChrPartiNo())
            .toHashCode();
    }

    public Boolean getBitIsBiography() {
        return bitIsBiography;
    }
    public void setBitIsBiography(Boolean bitIsBiography) {
        this.bitIsBiography = bitIsBiography;
    }
    public Boolean getBitIsInformed() {
        return bitIsInformed;
    }
    public void setBitIsInformed(Boolean bitIsInformed) {
        this.bitIsInformed = bitIsInformed;
    }
	/**
	 * @return Returns the chrAuthorNo.
	 */
	public String getChrAuthorNo() {
		return chrAuthorNo;
	}
	/**
	 * @param chrAuthorNo The chrAuthorNo to set.
	 */
	public void setChrAuthorNo(String chrAuthorNo) {
		this.chrAuthorNo = chrAuthorNo;
	}
}
