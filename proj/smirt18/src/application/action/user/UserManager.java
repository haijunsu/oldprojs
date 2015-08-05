/*
 * @(#)UserManager.java  2005-3-24
 * Copyright (c) 2005. All rights reserved. 
 * 
 * $Header$
 * $Log$
 */
package application.action.user;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.util.LabelValueBean;

import framework.exception.CannotFoundRequestParameterException;
import framework.util.http.RequestUtil;

import application.action.BaseDispachAction;
import application.action.SingleHtmlButton;
import application.dao.GroupDao;
import application.dao.UserDao;
import application.entity.Group;
import application.entity.User;
import application.helper.BeansLocator;

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
public class UserManager extends BaseDispachAction {
    /**
     * Logger for this class
     */
    private static final Log logger = LogFactory.getLog(UserManager.class);

    public ActionForward list(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        if (logger.isDebugEnabled()) {
            logger
                    .debug("list(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse) - start");
        }
        ActionForward _forward = mapping.findForward("listPage");
        ActionMessages _messages = new ActionMessages();
        try {
            UserDao _dao = (UserDao) BeansLocator.getBeanResource("userDao");
            GroupDao _groupDao = (GroupDao) BeansLocator
                    .getBeanResource("groupDao");
            User[] _users = _dao.listAll();
            if (_users != null && _users.length > 0) {
                UserForm[] _forms = new UserForm[_users.length];
                for (int i = 0; i < _forms.length; i++) {
                    _forms[i] = userToForm(_users[i]);
                    if (_groupDao.findByAccountAndGroup(_users[i].getAccount(),
                            "manager") != null) {
                        _forms[i].setManager(true);
                    }
                }
                request.setAttribute("smirtUsers", _forms);
            }
        } catch (CannotFoundRequestParameterException e) {
            logger
                    .error(
                            "list(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse)",
                            e);

            _messages.add("userError", new ActionMessage("errors.code.132"));
            saveErrors(request, _messages);
            _forward = mapping.findForward("error");
        } catch (Exception e) {
            logger
                    .error(
                            "list(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse)",
                            e);

            _messages.add("userError", new ActionMessage("errors.code.97"));
            saveErrors(request, _messages);
            _forward = mapping.findForward("error");
        }
        if (logger.isDebugEnabled()) {
            logger
                    .debug("list(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse) - end");
        }
        return _forward;
    }

    public ActionForward load(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        if (logger.isDebugEnabled()) {
            logger
                    .debug("load(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse) - start");
        }
        ActionForward _forward = mapping.findForward("updatePage");
        ActionMessages _messages = new ActionMessages();
        try {
            UserDao _dao = (UserDao) BeansLocator.getBeanResource("userDao");
            GroupDao _groupDao = (GroupDao) BeansLocator
                    .getBeanResource("groupDao");
            String _strId = RequestUtil.getRequiredStringParameter(
                    request, "id");
            UserForm _form = (UserForm) form;
            User _user = _dao.load(new Long(_strId));
            _form.setId(_user.getId());
            _form.setUserid(_user.getAccount());
            _form.setUserName(_user.getName());
            if (_groupDao.findByAccountAndGroup(_user.getAccount(), "manager") != null) {
                _form.setManager(true);
            }
        } catch (CannotFoundRequestParameterException e) {
            logger
                    .error(
                            "load(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse)",
                            e);

            _messages.add("userError", new ActionMessage("errors.code.132"));
            saveErrors(request, _messages);
        } catch (Exception e) {
            logger
                    .error(
                            "load(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse)",
                            e);

            _messages.add("userError", new ActionMessage("errors.code.97"));
            saveErrors(request, _messages);
            _forward = mapping.findForward("error");
        }
        if (logger.isDebugEnabled()) {
            logger
                    .debug("load(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse) - end");
        }
        return _forward;
    }

    public ActionForward add(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        if (logger.isDebugEnabled()) {
            logger
                    .debug("add(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse) - start");
        }
        if (isCancelled(request)) {
            removeFormBean(mapping, request);
            if (logger.isDebugEnabled()) {
                logger
                        .debug("add(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse)"
                                + " - cancelled end");
            }
            return mapping.findForward("home");
        }
        ActionForward _forward = mapping.findForward("success");
        ActionMessages _messages = new ActionMessages();
        try {
            saveBackButton(request, response);
            UserDao _dao = (UserDao) BeansLocator.getBeanResource("userDao");
            GroupDao _groupDao = (GroupDao) BeansLocator
                    .getBeanResource("groupDao");
            UserForm _form = (UserForm) form;
            if (!_form.getUserPass().equals(_form.getRePass())) {
                _messages.add("userError", new ActionMessage(
                "errors.passowrd.not.match"));
		        saveErrors(request, _messages);
		        return mapping.findForward("updatePage");
            }
            User _user = new User();
            _user.setAccount(_form.getUserid());
            _user.setName(_form.getUserName());
            _user.setPassword(_form.getUserPass());
            _dao.create(_user);
            Group _group = new Group();
            _group.setAccount(_user.getAccount());
            _group.setGroup("smirtuser");
            _groupDao.create(_user, _group);
            if (_form.isManager()) {
                _group.setGroup("manager");
                _groupDao.create(_user, _group);
            }
            _messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
                    "success.add.user"));
            saveMessages(request, _messages);
        } catch (CannotFoundRequestParameterException e) {
            logger
                    .error(
                            "add(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse)",
                            e);

            _messages.add("userError", new ActionMessage("errors.code.132"));
            saveErrors(request, _messages);
            _forward = mapping.findForward("error");
        } catch (Exception e) {
            logger
                    .error(
                            "add(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse)",
                            e);

            _messages.add("userError", new ActionMessage("errors.code.97"));
            saveErrors(request, _messages);
            _forward = mapping.findForward("error");
        }
        if (logger.isDebugEnabled()) {
            logger
                    .debug("add(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse) - end");
        }
        return _forward;
    }

    public ActionForward update(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        if (logger.isDebugEnabled()) {
            logger
                    .debug("update(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse) - start");
        }
        if (isCancelled(request)) {
            removeFormBean(mapping, request);
            if (logger.isDebugEnabled()) {
                logger
                        .debug("update(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse)"
                                + " - cancelled end");
            }
            return mapping.findForward("home");
        }

        ActionForward _forward = mapping.findForward("success");
        ActionMessages _messages = new ActionMessages();
        try {
            saveBackButton(request, response);
            UserDao _dao = (UserDao) BeansLocator.getBeanResource("userDao");
            GroupDao _groupDao = (GroupDao) BeansLocator
                    .getBeanResource("groupDao");
            UserForm _form = (UserForm) form;
            User _user = _dao.load(_form.getId());
            _user.setAccount(_form.getUserid());
            _user.setName(_form.getUserName());
            _dao.update(_user);
            if (_form.isManager()) {
                Group _group = _groupDao.findByAccountAndGroup(_user
                        .getAccount(), "manager");
                if (_group == null) {
                    _group = new Group();
                    _group.setAccount(_user.getAccount());
                    _group.setGroup("manager");
                    _groupDao.create(_user, _group);
                }
            } else {
                Group _group = _groupDao.findByAccountAndGroup(_user
                        .getAccount(), "manager");
                if (_group != null) {
                    _groupDao.remove(_group);
                }
            }
            _messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
                    "success.update.user"));
            saveMessages(request, _messages);
        } catch (CannotFoundRequestParameterException e) {
            logger
                    .error(
                            "update(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse)",
                            e);

            _messages.add("userError", new ActionMessage("errors.code.132"));
            saveErrors(request, _messages);
            _forward = mapping.findForward("error");
        } catch (Exception e) {
            logger
                    .error(
                            "update(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse)",
                            e);

            _messages.add("userError", new ActionMessage("errors.code.97"));
            saveErrors(request, _messages);
            _forward = mapping.findForward("error");
        }
        if (logger.isDebugEnabled()) {
            logger
                    .debug("update(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse) - end");
        }
        return _forward;
    }

    public ActionForward remove(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        if (logger.isDebugEnabled()) {
            logger
                    .debug("remove(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse) - start");
        }
        if (isCancelled(request)) {
            removeFormBean(mapping, request);
            if (logger.isDebugEnabled()) {
                logger
                        .debug("remove(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse)"
                                + " - cancelled end");
            }
            return mapping.findForward("home");
        }

        ActionForward _forward = mapping.findForward("success");
        ActionMessages _messages = new ActionMessages();
        try {
            saveBackButton(request, response);
            String _strId = RequestUtil.getRequiredStringParameter(
                    request, "id");
            UserDao _dao = (UserDao) BeansLocator.getBeanResource("userDao");
            GroupDao _groupDao = (GroupDao) BeansLocator
                    .getBeanResource("groupDao");
            User _user = _dao.load(new Long(_strId));
            Group[] _groups = _groupDao.findByAccount(_user.getAccount());
            for (int i = 0; i < _groups.length; i++) {
                _groupDao.remove(_groups[i]);
            }
            _dao.remove(_user);
            _messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
                    "success.delete.user"));
            saveMessages(request, _messages);
        } catch (CannotFoundRequestParameterException e) {
            logger
                    .error(
                            "remove(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse)",
                            e);

            _messages.add("userError", new ActionMessage("errors.code.132"));
            saveErrors(request, _messages);
            _forward = mapping.findForward("error");
        } catch (Exception e) {
            logger
                    .error(
                            "remove(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse)",
                            e);

            _messages.add("userError", new ActionMessage("errors.code.97"));
            saveErrors(request, _messages);
            _forward = mapping.findForward("error");
        }
        if (logger.isDebugEnabled()) {
            logger
                    .debug("remove(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse) - end");
        }
        return _forward;
    }

    public ActionForward updatePass(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        if (logger.isDebugEnabled()) {
            logger
                    .debug("updatePass(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse) - start");
        }
        if (isCancelled(request)) {
            removeFormBean(mapping, request);
            if (logger.isDebugEnabled()) {
                logger
                        .debug("updatePass(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse)"
                                + " - cancelled end");
            }
            return mapping.findForward("home");
        }

        ActionForward _forward = mapping.findForward("changePass");
        ActionMessages _messages = new ActionMessages();
        try {
            UserDao _dao = (UserDao) BeansLocator.getBeanResource("userDao");
            UserForm _form = (UserForm) form;
            if (!_form.getNewPass().equals(_form.getRePass())) {
                _messages.add("matchError", new ActionMessage(
                        "errors.passowrd.not.match"));
                saveErrors(request, _messages);
                return _forward;
            }
            User _user = _dao.findByAccount(_form.getUserid());
            if (!_user.getPassword().trim().equals(_form.getUserPass().trim())) {
                _messages.add("passError", new ActionMessage(
                        "errors.password.error"));
                saveErrors(request, _messages);
                return _forward;
            }
            _user.setPassword(_form.getNewPass());
            _dao.update(_user);

            _messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
                    "success.change.password"));
            saveMessages(request, _messages);
            _forward = mapping.findForward("success");
        } catch (CannotFoundRequestParameterException e) {
            logger
                    .error(
                            "updatePass(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse)",
                            e);

            _messages.add("userError", new ActionMessage("errors.code.132"));
            saveErrors(request, _messages);
            _forward = mapping.findForward("error");
        } catch (Exception e) {
            logger
                    .error(
                            "updatePass(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse)",
                            e);

            _messages.add("userError", new ActionMessage("errors.code.97"));
            saveErrors(request, _messages);
            _forward = mapping.findForward("error");
        }
        if (logger.isDebugEnabled()) {
            logger
                    .debug("updatePass(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse) - end");
        }
        return _forward;
    }

    private UserForm userToForm(User user) {
        UserForm _form = new UserForm();
        _form.setId(user.getId());
        _form.setUserid(user.getAccount());
        _form.setUserName(user.getName());
        _form.setUserPass(user.getPassword());
        return _form;
    }

    private User formToUser(UserForm form) {
        User _user = new User();
        _user.setId(form.getId());
        _user.setAccount(form.getUserid());
        _user.setName(form.getUserName());
        _user.setPassword(form.getUserPass());
        return _user;
    }

    private Group userToGroup(User user) {
        Group _group = new Group();
        _group.setAccount(user.getAccount());
        _group.setGroup("smirtuser");
        return _group;
    }

    private void saveBackButton(HttpServletRequest request, HttpServletResponse response) {
        SingleHtmlButton _btnBack = new SingleHtmlButton(request, response);
        _btnBack.setNameKey("form.button.back");
        _btnBack.setAction("/adminUser.do");
        LabelValueBean[] _labelValueBeans = new LabelValueBean[1];
        _labelValueBeans[0] = new LabelValueBean("action", "list");
        _btnBack.setLabelValueBeans(_labelValueBeans);
        request.setAttribute("backBtn", _btnBack);
    }

}