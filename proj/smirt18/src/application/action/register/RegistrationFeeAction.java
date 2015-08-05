/*
 * @(#)RegistrationFeeAction.java  2005-2-11
 * Copyright (c) 2005. All rights reserved. 
 * 
 * $Header$
 * $Log$
 */
package application.action.register;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.util.LabelValueBean;

import application.action.BaseDispachAction;
import application.action.SingleHtmlButton;
import application.action.jdbc.ExecuteSql;
import application.action.jdbc.ExecuteSqlAction;
import application.dao.AccommodationDao;
import application.dao.AccompanyPersonDao;
import application.entities.Taccommodation;
import application.entities.TaccompanyPerson;
import application.entities.TregistrationFee;
import application.exception.BusinessServiceException;
import application.helper.BeansLocator;
import application.service.ApplicationServiceLocator;
import application.service.ManagerFeeService;
import application.util.ServicesUtil;
import application.viewdata.AccommodationView;
import application.viewdata.AccompanyPersonView;
import application.viewdata.SmallUserView;
import framework.exception.CannotFoundRequestParameterException;
import framework.util.StringUtil;
import framework.util.http.RequestUtil;

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
public class RegistrationFeeAction extends BaseDispachAction {
	/**
	 * Logger for this class
	 */
	private static final Log logger = LogFactory
			.getLog(RegistrationFeeAction.class);

	public ActionForward queryRegisterFeeByParticipantNo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (logger.isDebugEnabled()) {
			logger
					.debug("queryRegisterFeeByParticipantNo(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse) - start");
		}

		ActionMessages _messages = new ActionMessages();
		ActionForward _forward = mapping.findForward("registrationFeeInput");
		try {
			RegistrationFeeForm _form = (RegistrationFeeForm) form;
			String _strPartiNo = RequestUtil.getRequiredStringParameter(
					request, "partiNo");
			String _strFeeType = RequestUtil.getStringParameter(request,
					"feeType", "");
			if (StringUtil.isBlank(_strFeeType)) {
				ManagerFeeService _feeService = ApplicationServiceLocator
						.getManagerFeeService();
				TregistrationFee _tregistrationFee = _feeService
						.loadRegistrationFeeByPartiNo(_strPartiNo);
				SmallUserView _smallUserView = ServicesUtil
						.getSmallUserViewByPartiNo(_strPartiNo);
				_form.setTitle(_smallUserView.getChvPartiTitle());
				_form.setName(_smallUserView.getUserName());
				_form.setEmail(_smallUserView.getChvPartiEmail());
				_form.setCountry(_smallUserView.getCountryName());
				_form.setFee(_tregistrationFee);
			}
			loadOtherPay(_form);
			//            int _nAccompanyPersons = ServicesUtil
			//                    .getAccompanyPersonCount(_strPartiNo);
			//            if (_tregistrationFee.getBitIsRegFeeReceived().booleanValue()
			//                    && _tregistrationFee.getInyAccPerson().intValue() !=
			// _nAccompanyPersons) {
			//                _messages.add("registerFeeError", new ActionMessage(
			//                        "errors.code.140", _tregistrationFee.getInyAccPerson(),
			//                        new Integer(_nAccompanyPersons)));
			//                saveErrors(request, _messages);
			//            }

		} catch (BusinessServiceException e) {
			logger
					.error(
							"queryRegisterFeeByParticipantNo(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse)",
							e);

			_messages.add("registerFeeError", new ActionMessage("errors.code."
					+ e.getErrCode()));
			saveErrors(request, _messages);
		} catch (CannotFoundRequestParameterException e) {
			logger
					.error(
							"queryRegisterFeeByParticipantNo(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse)",
							e);

			_messages.add("registerFeeError", new ActionMessage(
					"errors.code.132"));
			saveErrors(request, _messages);
		} catch (Exception e) {
			logger
					.error(
							"queryRegisterFeeByParticipantNo(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse)",
							e);

			_messages.add("registerFeeError", new ActionMessage(
					"errors.code.97"));
			saveErrors(request, _messages);
			_forward = mapping.findForward("error");
		}

		if (logger.isDebugEnabled()) {
			logger
					.debug("queryRegisterFeeByParticipantNo(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse) - end");
		}
		return _forward;
	}

	public ActionForward updateRegisterFee(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (logger.isDebugEnabled()) {
			logger
					.debug("updateRegisterFee(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse) - start");
		}

		ActionMessages _messages = new ActionMessages();
		ActionForward _forward = mapping.findForward("success");
		try {
			if (isCancelled(request)) {
				if (logger.isDebugEnabled()) {
					logger.debug("updateRegisterFee() - Cancelled end.");
				}
				cancelledAction(mapping, request, response);
				return null;
			}
			saveBackButton(request, response);
			RegistrationFeeForm _form = (RegistrationFeeForm) form;
			TregistrationFee _tregistrationFee = _form.getFee();
			ManagerFeeService _feeService = ApplicationServiceLocator
					.getManagerFeeService();
			_feeService.updateRegistrationFee(_tregistrationFee);
			_messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"success.modify.register.fee.message"));
			saveMessages(request, _messages);
		} catch (BusinessServiceException e) {
			logger
					.error(
							"updateRegisterFee(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse)",
							e);

			_messages.add("registerFeeError", new ActionMessage("errors.code."
					+ e.getErrCode()));
			saveErrors(request, _messages);
		} catch (CannotFoundRequestParameterException e) {
			logger
					.error(
							"updateRegisterFee(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse)",
							e);

			_messages.add("registerFeeError", new ActionMessage(
					"errors.code.132"));
			saveErrors(request, _messages);
		} catch (Exception e) {
			logger
					.error(
							"updateRegisterFee(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse)",
							e);

			_messages.add("registerFeeError", new ActionMessage(
					"errors.code.97"));
			saveErrors(request, _messages);
			_forward = mapping.findForward("error");
		}

		if (logger.isDebugEnabled()) {
			logger
					.debug("updateRegisterFee(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse) - end");
		}
		return _forward;
	}

	private void cancelledAction(ActionMapping mapping,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (logger.isDebugEnabled()) {
			logger
					.debug("cancelledAction(ActionMapping, HttpServletRequest, HttpServletResponse) - start");
		}

		String _strPartiNo = RequestUtil.getRequiredStringParameter(request,
				"fee.chrPartiNo");
		removeFormBean(mapping, request);
		request.getRequestDispatcher(
				"/register.do?action=loadUserByPartiNo&partiNo=" + _strPartiNo)
				.forward(request, response);

		if (logger.isDebugEnabled()) {
			logger
					.debug("cancelledAction(ActionMapping, HttpServletRequest, HttpServletResponse) - end");
		}
	}

	private void saveBackButton(HttpServletRequest request,
			HttpServletResponse response) {
		SingleHtmlButton _btnBack = new SingleHtmlButton(request, response);
		String _strPartiNo = RequestUtil.getRequiredStringParameter(request,
				"fee.chrPartiNo");
		_btnBack.setNameKey("form.button.back");
		_btnBack.setAction("/register.do");
		LabelValueBean[] _labelValueBeans = new LabelValueBean[2];
		_labelValueBeans[0] = new LabelValueBean("action", "loadUserByPartiNo");
		_labelValueBeans[1] = new LabelValueBean("partiNo", _strPartiNo);
		_btnBack.setLabelValueBeans(_labelValueBeans);
		request.setAttribute("backBtn", _btnBack);
	}

	private void loadOtherPay(RegistrationFeeForm form)
			throws BusinessServiceException {
		try {
			AccompanyPersonDao _accompanyPersonDao = (AccompanyPersonDao) BeansLocator
					.getBeanResource("accompanyPersonDao");
			TaccompanyPerson[] _persons = _accompanyPersonDao
					.findByParticipantNo(form.getFee().getChrPartiNo());
			if (_persons != null && _persons.length > 0) {
				AccompanyPersonView[] _personViews = new AccompanyPersonView[_persons.length];
				for (int i = 0; i < _personViews.length; i++) {
					_personViews[i] = new AccompanyPersonView();
					BeanUtils.copyProperties(_personViews[i], _persons[i]);
				}
				form.setPersonViews(_personViews);
			}
			AccommodationDao _accommodationDao = (AccommodationDao) BeansLocator
					.getBeanResource("accommodationDao");
			Taccommodation[] _taccommodations = _accommodationDao
					.findByPartiNo(form.getFee().getChrPartiNo());
			if (_taccommodations != null && _taccommodations.length > 0) {
				AccommodationView[] _accommodationViews = new AccommodationView[_taccommodations.length];
				for (int i = 0; i < _accommodationViews.length; i++) {
					_accommodationViews[i] = new AccommodationView();
					BeanUtils.copyProperties(_accommodationViews[i],
							_taccommodations[i]);
				}
				form.setHotels(_accommodationViews);
			}
		} catch (Exception e) {
			logger.error("loadOtherPay() - Exception", e);
			throw new BusinessServiceException(97, e.getMessage());
		}

	}

	public ActionForward queryPresentedPaper(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (logger.isDebugEnabled()) {
			logger
					.debug("queryPresentedPaper(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse) - start");
		}

		ActionMessages _messages = new ActionMessages();
		ActionForward _forward = mapping.findForward("listPresented");
		try {
			saveBackButton(request, response);
			String _strPartiNo = RequestUtil.getRequiredStringParameter(
					request, "partiNo");
			ExecuteSql _sqlHelper = (ExecuteSql) BeansLocator
					.getBeanResource("sqlService");
			String _stringSql = "SELECT p.chvPaperLNumber AS Paper_LNumber, p.chvPaperTitle AS Paper_Title FROM T_Paper p, T_PaperPresented pp"
					+ " WHERE p.chvPaperLNumber = pp.chvPaperLNumber AND pp.chrPresenterPartiNo = '"
					+ _strPartiNo.trim() + "'";
			List _list = _sqlHelper.query(_stringSql);
			Object[][] _objects =null;
			if (_list != null && !_list.isEmpty()) {
				_objects = ExecuteSqlAction.populateResult(_list);
			}
			_stringSql = "SELECT chvPartiFirstName, chvPartiMiddleName, chvPartiLastName FROM T_Participant WHERE chrPartiNo = '"
					+ _strPartiNo.trim() + "'";
			_list = _sqlHelper.query(_stringSql);
			Iterator _iterator = _list.iterator();
			String _stringName = "";
			while (_iterator.hasNext()) {
				Map _map = (Map) _iterator.next();
				_stringName = (String) _map.get("chvPartiFirstName")
						+ " " + (String) _map.get("chvPartiMiddleName") + " "
						+ (String) _map.get("chvPartiLastName");
				break;

			}
			request.setAttribute("papers", _objects);
			request.setAttribute("partiNo", _strPartiNo);
			request.setAttribute("partiName", _stringName); 
		} catch (BusinessServiceException e) {
			logger
					.error(
							"queryPresentedPaper(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse)",
							e);

			_messages.add("registerFeeError", new ActionMessage("errors.code."
					+ e.getErrCode()));
			saveErrors(request, _messages);
		} catch (CannotFoundRequestParameterException e) {
			logger
					.error(
							"queryPresentedPaper(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse)",
							e);

			_messages.add("queryPresentedPaper", new ActionMessage(
					"errors.code.132"));
			saveErrors(request, _messages);
		} catch (Exception e) {
			logger
					.error(
							"queryPresentedPaper(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse)",
							e);

			_messages.add("queryPresentedPaper", new ActionMessage(
					"errors.code.97"));
			saveErrors(request, _messages);
			_forward = mapping.findForward("error");
		}

		if (logger.isDebugEnabled()) {
			logger
					.debug("queryPresentedPaper(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse) - end");
		}
		return _forward;
	}

}