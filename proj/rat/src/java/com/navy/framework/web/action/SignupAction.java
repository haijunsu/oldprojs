package com.navy.framework.web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.acegisecurity.Authentication;
import org.acegisecurity.context.SecurityContextHolder;
import org.acegisecurity.providers.ProviderManager;
import org.acegisecurity.providers.UsernamePasswordAuthenticationToken;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.util.MessageResources;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;

import com.navy.framework.service.ServiceLocator;
import com.navy.framework.service.UserService;
import com.navy.framework.models.User;
import com.navy.framework.web.action.UserForm;
import com.navy.framework.util.BeanUtilsExt;
import com.navy.framework.util.EncryptUtil;
import com.navy.framework.web.Constants;

/**
 * Action class to allow users to self-register.
 *
 * <p/> <a href="SignupAction.java.html"><i>View Source</i></a>
 * </p>
 *
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 *
 * @struts.action name="userForm" path="/signup" scope="request"
 *                validate="false" input="failure"
 *
 * @struts.action-forward name="failure" path="/WEB-INF/pages/signup.jsp"
 * @struts.action-forward name="success" path="/mainMenu.html" redirect="true"
 */
public final class SignupAction extends BaseAction {

	/**
	 * Logger for SignupAction.class
	 */

	private static final org.apache.commons.logging.Log logger = org.apache.commons.logging.LogFactory
			.getLog(SignupAction.class);

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		// if it's an HTTP GET, simply forward to jsp
		if (request.getMethod().equals("GET")) {
			if (logger.isDebugEnabled()) {
				logger.debug("Request method is get. Forward to failure.");
			}
			return mapping.findForward("failure");
			// user clicked cancel button
		} else if (isCancelled(request)) {
			if (logger.isDebugEnabled()) {
				logger.debug("Cancel request.");
			}
			return new ActionForward("/");
			// run validation
		} else {
			// run validation rules on this form
			ActionMessages errors = form.validate(mapping, request);
			if (!errors.isEmpty()) {
				saveErrors(request, errors);
				return mapping.findForward("failure");
			}
		}

		if (logger.isDebugEnabled()) {
			logger.debug("registering user...");
		}

		ActionMessages errors = new ActionMessages();
		UserForm userForm = (UserForm) form;
		User user = new User();
		BeanUtilsExt.copyProperties(user, userForm);

		Boolean encrypt = (Boolean) getConfiguration().get(
				Constants.ENCRYPT_PASSWORD);

		if (encrypt != null && encrypt.booleanValue()) {
			String algorithm = (String) getConfiguration().get(
					Constants.ENC_ALGORITHM);
			if (algorithm == null) { // should only happen for test case
				logger.debug("assuming testcase, setting algorigthm to 'SHA'");
				algorithm = "SHA";
			}

			user.setPassword(EncryptUtil.encodePassword(user.getPassword(),
					algorithm));
		}

		user.setEnabled(true);
		UserService mgr = ServiceLocator.getUserService();
		mgr.create(user);

		ActionMessages messages = new ActionMessages();
		MessageResources resources = getResources(request);

		messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
				"user.registered", userForm.getUsername()));

		saveMessages(request.getSession(), messages);
		request.getSession().setAttribute(Constants.REGISTERED, Boolean.TRUE);

		// log user in automatically
		Authentication auth = new UsernamePasswordAuthenticationToken(user
				.getUsername(), user.getConfirmPassword());
		try {
			ProviderManager authenticationManager = ServiceLocator
					.getAuthenticationManager();
			SecurityContextHolder.getContext().setAuthentication(
					authenticationManager.doAuthentication(auth));
		} catch (NoSuchBeanDefinitionException n) {
			logger.error("should only happen when testing", n);

		}

		// Send user an e-mail
//		if (logger.isDebugEnabled()) {
//			logger.debug("Sending user '" + userForm.getUsername()
//					+ "' an account information e-mail");
//		}

//		SimpleMailMessage message = (SimpleMailMessage) getBean("mailMessage");
//		message.setTo(user.getFullName() + "<" + user.getEmail() + ">");
//
//		StringBuffer msg = new StringBuffer();
//		msg.append(resources.getMessage("signup.email.message"));
//		msg.append("\n\n" + resources.getMessage("userForm.username"));
//		msg.append(": " + userForm.getUsername() + "\n");
//		msg.append(resources.getMessage("userForm.password") + ": ");
//		msg.append(userForm.getPassword());
//		msg.append("\n\nLogin at: " + RequestUtil.getAppURL(request));
//		message.setText(msg.toString());
//
//		message.setSubject(resources.getMessage("signup.email.subject"));
//
//		MailEngine engine = (MailEngine) getBean("mailEngine");
//		engine.send(message);

		return mapping.findForward("success");
	}

}
