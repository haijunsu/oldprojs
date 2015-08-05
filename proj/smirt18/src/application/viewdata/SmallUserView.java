/*
 * @(#)SmallUserView.java  2005-2-22
 * Copyright (c) 2005. All rights reserved.
 *
 * $Header$
 * $Log$
 */
package application.viewdata;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import framework.util.StringUtil;

import application.util.ServicesUtil;

/**
 * <p>
 * <b>Description </b>
 * </p>
 * <p>
 * </p>
 * 
 * $Revision$
 * 
 * @author su_haijun <a href=mailto:su_hj@126.com>su_hj@126.com </a>
 */
public class SmallUserView implements Serializable, Comparable {
	/**
	 * Logger for this class
	 */
	private static final Log logger = LogFactory.getLog(SmallUserView.class);

	private String chrAuthorNo;

	/**
	 * @return Returns the chrAuthorNo.
	 */
	public String getChrAuthorNo() {
		return chrAuthorNo;
	}

	/**
	 * @param chrAuthorNo
	 *            The chrAuthorNo to set.
	 */
	public void setChrAuthorNo(String chrAuthorNo) {
		this.chrAuthorNo = chrAuthorNo;
	}

	private String chrPartiNo;

	private String chvPartiTitle;

	private String chvPartiFirstName;

	private String chvPartiMiddleName;

	private String chvPartiLastName;

	private String chvPartiAffiliation;

	private String chvPartiPosition;

	private String chvPartiDepartment;

	private String chvPartiAddress;

	private String chvPartiCity;

	private String chvPartiProvince;

	private String chvPartiCountry;

	private String chvPartiEmail;

	private boolean isRegistered;

	private Integer intUserID;

	private String chvPaperNumber;

	private Integer inyAuthorRank;

	private String grantedPaperLNumber;

	private boolean isRegFeeReceived;

	private String currentPaperLNumber;

	private Boolean bitIsCheckin;

	private String urlParams;

	private Boolean bitIsInformed;

	private Boolean bitIsBiography;

	public boolean isMailAuthor() {
		if (chrPartiNo != null && intUserID == null) {
			intUserID = ServicesUtil.getIntUserIdByPartiNo(chrPartiNo,
					chvPaperNumber);
		}
		if (intUserID != null) {
			return true;
		}
		return false;
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

	public Integer getIntUserID() {
		return this.intUserID;
	}

	public void setIntUserID(Integer intUserID) {
		this.intUserID = intUserID;
	}

	public String getChvPaperNumber() {
		return this.chvPaperNumber;
	}

	public void setChvPaperNumber(String chvPaperNumber) {
		this.chvPaperNumber = chvPaperNumber;
	}

	public Integer getInyAuthorRank() {
		return this.inyAuthorRank;
	}

	public void setInyAuthorRank(Integer inyAuthorRank) {
		this.inyAuthorRank = inyAuthorRank;
	}

	public String getUrlParams() {
		try {
		if (isParticipant()) {
			this.urlParams = "action=loadUserByPartiNo&partiNo="
					+ getChrPartiNo().trim();
			return this.urlParams;
		}
		if (isAuthor()) {
			this.urlParams = "action=loadUserByAuthorNo&authorNo="
					+ getChrAuthorNo().trim();
			return this.urlParams;
		}
		if (isUserAccount()) {
			this.urlParams = "action=loadUserByUserid&userid="
					+ getIntUserID().toString();
			return this.urlParams;
		}
		if (isCoauthor()) {
			this.urlParams = "action=loadUserByCoauthor&paperNumber="
					+ getChvPaperNumber().trim() + "&eamil="
					+ getChvPartiEmail().trim();
			return this.urlParams;
		}
		return this.urlParams;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return null;
		}
	}

	public String getChrPartiNo() {
		return this.chrPartiNo;
	}

	public void setChrPartiNo(String chrPartiNo) {
		this.chrPartiNo = chrPartiNo;
	}

	public String getChvPartiAddress() {
		return this.chvPartiAddress;
	}

	public void setChvPartiAddress(String chvPartiAddress) {
		this.chvPartiAddress = chvPartiAddress;
	}

	public String getChvPartiAffiliation() {
		return this.chvPartiAffiliation;
	}

	public void setChvPartiAffiliation(String chvPartiAffiliation) {
		this.chvPartiAffiliation = chvPartiAffiliation;
	}

	public String getChvPartiCity() {
		return this.chvPartiCity;
	}

	public void setChvPartiCity(String chvPartiCity) {
		this.chvPartiCity = chvPartiCity;
	}

	public String getChvPartiCountry() {
		return this.chvPartiCountry;
	}

	public void setChvPartiCountry(String chvPartiCountry) {
		this.chvPartiCountry = chvPartiCountry;
	}

	public String getChvPartiDepartment() {
		return this.chvPartiDepartment;
	}

	public void setChvPartiDepartment(String chvPartiDepartment) {
		this.chvPartiDepartment = chvPartiDepartment;
	}

	public String getChvPartiEmail() {
		if (chvPartiEmail == null) {
			return "";
		}
		return this.chvPartiEmail;
	}

	public void setChvPartiEmail(String chvPartiEmail) {
		this.chvPartiEmail = chvPartiEmail;
	}

	public String getChvPartiFirstName() {
		if (StringUtil.isBlank(chvPartiFirstName)) {
			return "";
		}
		return this.chvPartiFirstName;
	}

	public void setChvPartiFirstName(String chvPartiFirstName) {
		this.chvPartiFirstName = chvPartiFirstName;
	}

	public String getChvPartiLastName() {
		if (StringUtil.isBlank(chvPartiLastName)) {
			return "";
		}
		return this.chvPartiLastName;
	}

	public void setChvPartiLastName(String chvPartiLastName) {
		this.chvPartiLastName = chvPartiLastName;
	}

	public String getChvPartiMiddleName() {
		return this.chvPartiMiddleName;
	}

	public void setChvPartiMiddleName(String chvPartiMiddleName) {
		this.chvPartiMiddleName = chvPartiMiddleName;
	}

	public String getChvPartiPosition() {
		return this.chvPartiPosition;
	}

	public void setChvPartiPosition(String chvPartiPosition) {
		this.chvPartiPosition = chvPartiPosition;
	}

	public String getChvPartiProvince() {
		return this.chvPartiProvince;
	}

	public void setChvPartiProvince(String chvPartiProvince) {
		this.chvPartiProvince = chvPartiProvince;
	}

	public String getChvPartiTitle() {
		return this.chvPartiTitle;
	}

	public void setChvPartiTitle(String chvPartiTitle) {
		this.chvPartiTitle = chvPartiTitle;
	}

	public boolean isRegistered() {
		if (StringUtil.isBlank(this.chrPartiNo)) {
			return false;
		}
		return true;
		//return this.isRegistered;
	}

	public void setRegistered(boolean isRegistered) {
		this.isRegistered = isRegistered;
	}

	public String getUserName() {

		String _str = "";
		if (StringUtil.isNotBlank(getChvPartiFirstName())) {
			_str = getChvPartiFirstName().trim() + " ";
		}

		if (StringUtil.isNotBlank(getChvPartiMiddleName())) {
			_str = _str + getChvPartiMiddleName().trim() + " ";
		}
		if (StringUtil.isNotBlank(getChvPartiLastName())) {
			_str = _str + getChvPartiLastName().trim() + " ";
		}
		return _str.trim();
	}

	public String getCountryName() {
		String _str = "";
		try {
			_str = ServicesUtil.getCountryName(getChvPartiCountry());
		} catch (Exception e) {

		}
		return _str;
	}

	public boolean equals(Object other) {
		if (!(other instanceof SmallUserView))
			return false;
		SmallUserView castOther = (SmallUserView) other;
		return new EqualsBuilder().append(
				this.getChvPartiEmail().trim().toUpperCase(),
				castOther.getChvPartiEmail().trim().toUpperCase()).append(
				this.getChvPartiFirstName().trim().toUpperCase(),
				castOther.getChvPartiFirstName().trim().toUpperCase()).append(
				this.getChvPartiLastName().trim().toUpperCase(),
				castOther.getChvPartiLastName().trim().toUpperCase())
				.isEquals();
	}

	public int hashCode() {
		return new HashCodeBuilder().append(
				getChvPartiEmail().trim().toUpperCase()).append(
				getChvPartiFirstName().trim().toUpperCase()).append(
				getChvPartiLastName().trim().toUpperCase()).toHashCode();
	}

	public String toString() {
		return new ToStringBuilder(this).append("getChvPartiEmail",
				getChvPartiEmail()).append("getChvPartiFirstName",
				getChvPartiFirstName()).append("getChvPartiLastName",
				getChvPartiLastName()).append("getChvPartiMiddleName",
				getChvPartiMiddleName()).toString();
	}

	public String getGrantedPaperLNumber() {
		if (StringUtil.isBlank(this.grantedPaperLNumber)
				&& StringUtil.isNotBlank(getChrPartiNo())) {
			this.grantedPaperLNumber = ServicesUtil
					.getGrantPaperLNumberOfParticipant(getChrPartiNo());
		}
		return this.grantedPaperLNumber;
	}

	public void setGrantedPaperLNumber(String grantedPaperLNumber) {
		this.grantedPaperLNumber = grantedPaperLNumber;
	}

	public boolean isRegFeeReceived() {
		return this.isRegFeeReceived;
	}

	public void setRegFeeReceived(boolean isRegFeeReceived) {
		this.isRegFeeReceived = isRegFeeReceived;
	}

	public String getCurrentPaperLNumber() {
		return this.currentPaperLNumber;
	}

	public void setCurrentPaperLNumber(String currentPaperLNumber) {
		this.currentPaperLNumber = currentPaperLNumber;
	}

	public boolean isRegFeeRecivedAndGranted() {
		return isGrantedCurrentPaper() && isRegFeeReceived();
	}

	public boolean isGrantedCurrentPaper() {
		if (isGranted()
				&& getCurrentPaperLNumber().equals(getGrantedPaperLNumber())) {
			return true;
		}
		return false;
	}

	public boolean isGranted() {
		if (StringUtil.isBlank(getChrPartiNo())) {
			return false;
		}
		if (StringUtil.isNotBlank(getGrantedPaperLNumber())) {
			return true;
		}
		return false;

	}

	/**
	 * grantedStatuts -1 - not registed 0 - not granted 1 - granted by other
	 * paper 2 - granted by current paper 3 - granted by current paper and
	 * regFeeRecived
	 * 
	 * @return
	 */
	public int getGrantedStatus() {
		if (StringUtil.isBlank(getChrPartiNo())) {
			return -1;
		}
		if (!isGranted()) {
			return 0;
		} else {
			if (isRegFeeRecivedAndGranted()) {
				return 3;
			}
			if (isGrantedCurrentPaper()) {
				return 2;
			}
			return 1;
		}
	}

	public Boolean getBitIsCheckin() {
		return bitIsCheckin;
	}

	public void setBitIsCheckin(Boolean bitIsCheckin) {
		this.bitIsCheckin = bitIsCheckin;
	}

	public boolean isParticipant() {
		if (StringUtil.isBlank(getChrPartiNo())) {
			return false;
		}
		return true;
	}

	public boolean isCoauthor() {
		if (StringUtil.isNotBlank(getChrPartiNo())
				|| StringUtil.isNotBlank(getChrAuthorNo())
				|| (getIntUserID() != null)) {
			return false;
		}
		return true;
	}

	public boolean isAuthor() {
		if (StringUtil.isBlank(getChrAuthorNo())) {
			return false;
		}
		return true;
	}

	public boolean isUserAccount() {
		if (StringUtil.isBlank(getChrPartiNo()) && getIntUserID() != null) {
			return true;
		}
		return false;
	}

	public String getStyleName() {
		if (isParticipant()) {
			return "partiLine";
		}
		if (isAuthor()) {
			return "authorLine";
		}
		if (isUserAccount()) {
			return "userAccountLine";
		}
		return "coauthorLine";
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Object o) {
		if (o instanceof SmallUserView) {
			SmallUserView _userView = (SmallUserView) o;
			if (_userView.getInyAuthorRank() == null || this.getInyAuthorRank() == null) {
				return 0;
			}
			if (_userView.getInyAuthorRank().intValue() > this.getInyAuthorRank().intValue()) {
				return -1;
			}
			return 1;
		}
		return 0;
	}
}