/*
 * @(#)RegistrationFeeForm.java  2005-3-6
 * Copyright (c) 2005. All rights reserved. 
 * 
 * $Header$
 * $Log$
 */
package application.action.register;

import java.math.BigDecimal;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import application.entities.TregistrationFee;
import application.helper.ResourcesHelper;
import application.util.ServicesUtil;
import application.viewdata.AccommodationView;
import application.viewdata.AccompanyPersonView;
import framework.util.StringUtil;

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
public class RegistrationFeeForm extends ActionForm {
	/**
	 * Logger for this class
	 */
	private static final Log logger = LogFactory.getLog(RegisterForm.class);

	private final String PAY_RMB = ResourcesHelper.getFormsResources()
			.getMessage("form.parti.fee.rmb.item");

	private TregistrationFee m_fee;

	private AccompanyPersonView[] m_personViews;

	private AccommodationView[] m_hotels;

	private String m_partiNo;

	private String m_title;

	private String m_name;

	private String m_email;

	private String m_country;

	private String m_action;

	private Collection m_FeeTypes;

	public RegistrationFeeForm() {
		super();
		this.m_fee = new TregistrationFee();
	}

	public AccommodationView[] getHotels() {
		return m_hotels;
	}

	public void setHotels(AccommodationView[] hotels) {
		m_hotels = hotels;
	}

	public String getPartiNo() {
		return m_partiNo;
	}

	public void setPartiNo(String partiNo) {
		m_partiNo = partiNo;
	}

	public String getTitle() {
		return m_title;
	}

	public void setTitle(String title) {
		m_title = title;
	}

	public String getFeeType() {
		if (StringUtil.isNotBlank(getFee().getChrMoneyType())) {
			return getFee().getChrMoneyType();
		}
		return "0";
	}

	public void setFeeType(String feeType) {
		getFee().setChrMoneyType(feeType);
		getFee().setMnyConfFeeBefore(
				new BigDecimal(ServicesUtil.getFeeValueByType(feeType)));
	}

	public String getCountry() {
		return m_country;
	}

	public void setCountry(String country) {
		m_country = country;
	}

	public String getEmail() {
		return m_email;
	}

	public void setEmail(String email) {
		m_email = email;
	}

	public String getName() {
		return m_name;
	}

	public void setName(String name) {
		m_name = name;
	}

	public String getMoneyType() {
		if (getFeeType().equals(PAY_RMB)) {
			return "1";
		}
		return "0";
	}

	public String getMoneyTypeName() {
		return ServicesUtil.getMoneyTypeName(getMoneyType());
	}

	public AccompanyPersonView[] getPersonViews() {
		return m_personViews;
	}

	public void setPersonViews(AccompanyPersonView[] personViews) {
		m_personViews = personViews;
	}

	public TregistrationFee getFee() {
		return this.m_fee;
	}

	public void setFee(TregistrationFee fee) {
		this.m_fee = fee;
	}

	public String getAction() {
		return this.m_action;
	}

	public void setAction(String action) {
		this.m_action = action;
	}

	public Collection getFeeTypes() {
		if (this.m_FeeTypes == null || this.m_FeeTypes.size() == 0) {
			this.m_FeeTypes = ServicesUtil.getFeeTypeLabelValueBeans();
		}
		return m_FeeTypes;
	}

	public void reset(ActionMapping mapping, HttpServletRequest request) {
		super.reset(mapping, request);
		this.m_action = "";
		this.m_fee = new TregistrationFee();
		this.m_FeeTypes = null;
	}

	public int getAccPersonFeeBalance() {
		int _nBalance = 0;
		BigDecimal _bigDecimalAcc = getFee().getMnyAccPersonFee();
		if (_bigDecimalAcc != null) {
			_nBalance = _bigDecimalAcc.intValue();
		}
		return _nBalance - getAccPersonFee();
	}

	public int getHotelBalance() {
		int _nBalance = 0;
		BigDecimal _bigDecimalHotel = getFee().getMnyConfFeeBetween();
		if (_bigDecimalHotel != null) {
			_nBalance = _bigDecimalHotel.intValue();
		}
		return _nBalance - (int) getHotelFee();
	}

	public int getAccPersonFee() {
		int _nCount = 0;
		if (getPersonViews() != null) {
			for (int i = 0; i < getPersonViews().length; i++) {
				getPersonViews()[i].setMonyType(getMoneyType());
				_nCount += getPersonViews()[i].getAccFee();
			}
		}
		return _nCount;
	}

	public double getHotelFee() {
		double _d = 0d;
		if (getHotels() != null) {
			for (int i = 0; i < getHotels().length; i++) {
				BigDecimal _bigDecimalFee = getHotels()[i].getMnyBookFee();
				_d += _bigDecimalFee.doubleValue();
			}
		}
		return _d;
	}

	public double getShouldPayRegisterFee() {
		String _strPay = ServicesUtil.getFeeValueByType(getFeeType());
		//        System.out.println(getFeeType());
		double _dPay = Double.parseDouble(_strPay);
		//        if (getMoneyType().equals("0") &&
		// StringUtil.isNotBlank(getFee().getChvPaperNumber())) {
		if (StringUtil.isNotBlank(getFee().getChvPaperNumber())) {
			String _strGrant = ResourcesHelper.getFormsResources().getMessage(
					"form.parti.fee.grant." + getMoneyType());
			_dPay = _dPay - Double.parseDouble(_strGrant);
		}
		return _dPay;
	}

	public double getShouldPayTotal() {
		double _d = 0d;
		_d = getShouldPayRegisterFee() + getAccPersonFee() + getHotelFee();
		return _d;
	}

	public double getRealPayTotal() {
		double _d = 0d;
		double _dTrans = 0d;
		double _dCash = 0d;
		double _dCard = 0d;
		if (getFee().getMnyRegFeeReceived() != null) {
			_dTrans = getFee().getMnyRegFeeReceived().doubleValue();
		}
		if (getFee().getMnyConfFeeVenue() != null) {
			_dCash = getFee().getMnyConfFeeVenue().doubleValue();
		}
		if (getFee().getMnyWishPay() != null) {
			_dCard = getFee().getMnyWishPay().doubleValue();
		}
		_d = _dTrans + _dCard + _dCash;
		return _d;
	}

	public double getBalance() {
		double _d = 0d;
		double _dRealRegisterFee = getShouldPayRegisterFee();
		if (getFee().getMnyGrantSupportFee() != null
				&& getFee().getMnyGrantSupportFee().intValue() != 0) {
			_dRealRegisterFee = getFee().getMnyGrantSupportFee().doubleValue();
		}
		_d = getRealPayTotal()
				- (_dRealRegisterFee + getAccPersonFee() + getHotelFee());
		return _d;
	}

	public double getRealBalance() {
		double _d = 0d;
		_d = getRealPayTotal() - (getShouldPayRegisterFee() + getAccPersonFee() + getHotelFee());
		return _d;

	}

	public double getRegisterFeeBalance() {
		double _d = 0d;
		String _strPay = ServicesUtil.getFeeValueByType(getFeeType());
		double _dRegisterFee = Double.parseDouble(_strPay);
		BigDecimal _bigDecimalFee = getFee().getMnyGrantSupportFee();
		if (_bigDecimalFee != null) {
			_d = _bigDecimalFee.doubleValue();
		}
		_d = _d - getShouldPayRegisterFee();
		return _d;

	}
}