/*
 * @(#)AccompanyAction.java  2005-2-11
 * Copyright (c) 2005. All rights reserved.
 *
 * $Header$
 * $Log$
 */
package application.action.paper;

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
import application.dao.ParticipantDao;
import application.entities.TpaperPresented;
import application.entities.TpaperRegistation;
import application.entities.Tparticipant;
import application.entities.TregistrationFee;
import application.exception.BusinessServiceException;
import application.helper.BeansLocator;
import application.service.ApplicationServiceLocator;
import application.service.ManagerFeeService;
import application.service.SearchService;
import application.util.ServicesUtil;
import application.viewdata.PaperView;
import application.viewdata.SmallAuthorView;
import application.viewdata.SmallPaperView;
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
public class PaperFeeAction extends BaseDispachAction {
	/**
	 * Logger for this class
	 */
	private static final Log logger = LogFactory.getLog(PaperFeeAction.class);

	public ActionForward queryPaperFeeByPaperNumber(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (logger.isDebugEnabled()) {
			logger
					.debug("queryPaperFeeByPaperNumber(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse) - start");
		}

		ActionMessages _messages = new ActionMessages();
		ActionForward _forward = mapping.findForward("paperFeeInput");
		try {
			ManagerFeeService _service = ApplicationServiceLocator
					.getManagerFeeService();
			String _strPaperNumber = RequestUtil.getRequiredStringParameter(
					request, "paperNumber");
			String _strPaperLNumber = RequestUtil.getRequiredStringParameter(
					request, "paperLNumber");
			SearchService _searchService = ApplicationServiceLocator
					.getSearchService();
			PaperView _paperView = _searchService
					.serachByPaperNumber(_strPaperNumber);
			SmallPaperView _smallPaperView = _paperView.getSmallPaperView();
			SmallAuthorView[] _smallAuthorViews = _paperView.getAuthors();

			for (int i = 0; i < _smallAuthorViews.length; i++) {
				if (StringUtil.isNotBlank(_smallAuthorViews[i].getChrPartiNo())) {
					_smallAuthorViews[i]
							.setCurrentPaperLNumber(_strPaperLNumber);
					_smallAuthorViews[i].setRegFeeReceived(ServicesUtil
							.isRegFeeReceivedOfParticipant(_smallAuthorViews[i]
									.getChrPartiNo()));
				}
			}

			TpaperPresented _tpaperPresented = _paperView.getPaperPresented();
			if (_tpaperPresented != null
					&& StringUtil.isNotBlank(_tpaperPresented.getChrPresenterPartiNo())) {
				ParticipantDao _participantDao = (ParticipantDao) BeansLocator
						.getBeanResource("participantDao");
				Tparticipant _tparticipant = _participantDao.findByParticipantNo(_tpaperPresented.getChrPresenterPartiNo());
				SmallUserView _smallUserView = new SmallUserView();
				BeanUtils.copyProperties(_smallUserView,
						_tparticipant);
				_smallUserView.setCurrentPaperLNumber(_strPaperLNumber);
				_smallUserView.setRegFeeReceived(ServicesUtil
							.isRegFeeReceivedOfParticipant(_smallUserView
									.getChrPartiNo()));
				request.setAttribute("presented", _smallUserView);
			}

			request.setAttribute("paperView", _smallPaperView);
			request.setAttribute("authors", _smallAuthorViews);
			TpaperRegistation _tpaperRegistation = _service
					.loadPaperRegistrationByPaperNumber(_strPaperNumber);
			if (_tpaperRegistation == null) {
				_tpaperRegistation = new TpaperRegistation();
				_tpaperRegistation.setChvPaperLNumber(_strPaperLNumber);
				_tpaperRegistation.setChvPaperNumber(_strPaperNumber);
				_tpaperRegistation.setChrPaymentMethodNo("1");
				_tpaperRegistation.setBitIsAppJuniorAward(_paperView
						.getSmallPaperView().getBitIsJuniorAward());
				_tpaperRegistation.setBitIsAppJaegerPrize(_paperView
						.getSmallPaperView().getBitIsPaperPrize());
				_service.createPaperRegistration(_tpaperRegistation);
				_tpaperRegistation = _service
						.loadPaperRegistrationByPaperNumber(_strPaperNumber);
			}
			PaperFeeForm _form = (PaperFeeForm) form;
			_form.setPaperFee(_tpaperRegistation);

		} catch (BusinessServiceException e) {
			logger
					.error(
							"queryPaperFeeByPaperNumber(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse)",
							e);

			_messages.add("paperFeeError", new ActionMessage("errors.code."
					+ e.getErrCode()));
			saveErrors(request, _messages);
			_forward = mapping.findForward("error");
		} catch (CannotFoundRequestParameterException e) {
			logger
					.error(
							"queryPaperFeeByPaperNumber(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse)",
							e);

			_messages
					.add("paperFeeError", new ActionMessage("errors.code.132"));
			saveErrors(request, _messages);
			_forward = mapping.findForward("error");
		} catch (Exception e) {
			logger
					.error(
							"queryPaperFeeByPaperNumber(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse)",
							e);

			_messages.add("paperFeeError", new ActionMessage("errors.code.97"));
			saveErrors(request, _messages);
			_forward = mapping.findForward("error");
		}

		if (logger.isDebugEnabled()) {
			logger
					.debug("queryPaperFeeByPaperNumber(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse) - end");
		}
		return _forward;
	}

	public ActionForward updatePaperFee(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (logger.isDebugEnabled()) {
			logger
					.debug("updatePaperFee(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse) - start");
		}

		ActionMessages _messages = new ActionMessages();
		ActionForward _forward = mapping.findForward("success");
		try {
			if (isCancelled(request)) {
				if (logger.isDebugEnabled()) {
					logger.debug("updatePaperFee() - Cancelled end.");
				}
				cancelledAction(mapping, request, response);
				return null;
			}
			saveBackButton(request, response);
			PaperFeeForm _form = (PaperFeeForm) form;
			TpaperRegistation _tpaperRegistation = _form.getPaperFee();
			ManagerFeeService _service = ApplicationServiceLocator
					.getManagerFeeService();
			_service.updatePaperRegistration(_tpaperRegistation);
			_messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"success.modify.paper.registration.message"));
			saveMessages(request, _messages);
		} catch (BusinessServiceException e) {
			logger
					.error(
							"updatePaperFee(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse)",
							e);

			_messages.add("paperFeeError", new ActionMessage("errors.code."
					+ e.getErrCode()));
			saveErrors(request, _messages);
			_forward = mapping.findForward("error");
		} catch (CannotFoundRequestParameterException e) {
			logger
					.error(
							"updatePaperFee(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse)",
							e);

			_messages
					.add("paperFeeError", new ActionMessage("errors.code.132"));
			saveErrors(request, _messages);
			_forward = mapping.findForward("error");
		} catch (Exception e) {
			logger
					.error(
							"updatePaperFee(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse)",
							e);

			_messages.add("paperFeeError", new ActionMessage("errors.code.97"));
			saveErrors(request, _messages);
			_forward = mapping.findForward("error");
		}

		if (logger.isDebugEnabled()) {
			logger
					.debug("updatePaperFee(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse) - end");
		}
		return _forward;
	}

	public ActionForward updatePaperGranted(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (logger.isDebugEnabled()) {
			logger
					.debug("updatePaperGranted(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse) - start");
		}

		ActionMessages _messages = new ActionMessages();
		ActionForward _forward = mapping.findForward("success");
		try {
			if (isCancelled(request)) {
				if (logger.isDebugEnabled()) {
					logger.debug("updatePaperGranted() - Cancelled end.");
				}
				cancelledAction(mapping, request, response);
				return null;
			}
			saveBackButton(request, response);
			String _strPaperNumber = RequestUtil.getRequiredStringParameter(
					request, "paperFee.chvPaperNumber");
			String _strPaperLNumber = RequestUtil.getRequiredStringParameter(
					request, "paperFee.chvPaperLNumber");
			String _strPartiNo = RequestUtil.getStringParameter(request,
					"partiNo", "none");
			String _strOldPartiNo = RequestUtil.getStringParameter(request,
					"oldPartiNo", null);
			boolean _isRegFeeIsRecieved = RequestUtil.getBooleanParameter(
					request, "regFeeIsRecieved", false);
			if (_isRegFeeIsRecieved) {
				throw new BusinessServiceException(141,
						"Granted author has pay all fee. Can't update granted author of paper "
								+ _strPaperLNumber);
			}
			ManagerFeeService _service = ApplicationServiceLocator
					.getManagerFeeService();
			if (StringUtil.isNotBlank(_strOldPartiNo)) {
				TregistrationFee _tregistrationFeeOld = _service
						.loadRegistrationFeeByPartiNo(_strOldPartiNo);
				_tregistrationFeeOld.setBitIsGranted(new Boolean(false));
				_tregistrationFeeOld.setChvPaperNumber("");
				_service.updateRegistrationFee(_tregistrationFeeOld);
			}
			if (!_strPartiNo.equals("none")) {
				TregistrationFee _tregistrationFeeNew = _service
						.loadRegistrationFeeByPartiNo(_strPartiNo);
				_tregistrationFeeNew.setBitIsGranted(new Boolean(true));
				_tregistrationFeeNew.setChvPaperNumber(_strPaperLNumber);
				_service.updateRegistrationFee(_tregistrationFeeNew);
			}
			TpaperRegistation _tpaperRegistation = _service
					.loadPaperRegistrationByPaperNumber(_strPaperNumber);
			if (_strPartiNo.equals("none")) {
				_tpaperRegistation.setChrGrantedPartiNo("");
			} else {
				_tpaperRegistation.setChrGrantedPartiNo(_strPartiNo);
			}
			_service.updatePaperRegistration(_tpaperRegistation);
			_messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"success.modify.paper.registration.granted.message"));
			saveMessages(request, _messages);
		} catch (BusinessServiceException e) {
			logger
					.error(
							"updatePaperGranted(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse)",
							e);

			_messages.add("paperFeeError", new ActionMessage("errors.code."
					+ e.getErrCode()));
			saveErrors(request, _messages);
			_forward = mapping.findForward("error");
		} catch (CannotFoundRequestParameterException e) {
			logger
					.error(
							"updatePaperGranted(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse)",
							e);

			_messages
					.add("paperFeeError", new ActionMessage("errors.code.132"));
			saveErrors(request, _messages);
			_forward = mapping.findForward("error");
		} catch (Exception e) {
			logger
					.error(
							"updatePaperGranted(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse)",
							e);

			_messages.add("paperFeeError", new ActionMessage("errors.code.97"));
			saveErrors(request, _messages);
			_forward = mapping.findForward("error");
		}

		if (logger.isDebugEnabled()) {
			logger
					.debug("updatePaperGranted(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse) - end");
		}
		return _forward;
	}

	private void saveBackButton(HttpServletRequest request,
			HttpServletResponse response) {
		SingleHtmlButton _btnBack = new SingleHtmlButton(request, response);
		String _strPaperNumber = RequestUtil.getRequiredStringParameter(
				request, "paperFee.chvPaperNumber");
		_btnBack.setNameKey("form.button.back");
		_btnBack.setAction("/search.do");
		LabelValueBean[] _labelValueBeans = new LabelValueBean[2];
		_labelValueBeans[0] = new LabelValueBean("action", "queryByPaperNumber");
		_labelValueBeans[1] = new LabelValueBean("paperNumber", _strPaperNumber);
		_btnBack.setLabelValueBeans(_labelValueBeans);
		request.setAttribute("backBtn", _btnBack);
	}

	private void cancelledAction(ActionMapping mapping,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (logger.isDebugEnabled()) {
			logger
					.debug("cancelledAction(ActionMapping, HttpServletRequest, HttpServletResponse) - start");
		}

		String _strPaperNumber = RequestUtil.getRequiredStringParameter(
				request, "paperFee.chvPaperNumber");
		removeFormBean(mapping, request);
		request.getRequestDispatcher(
				"/search.do?action=queryByPaperNumber&paperNumber="
						+ _strPaperNumber).forward(request, response);

		if (logger.isDebugEnabled()) {
			logger
					.debug("cancelledAction(ActionMapping, HttpServletRequest, HttpServletResponse) - end");
		}
	}

}