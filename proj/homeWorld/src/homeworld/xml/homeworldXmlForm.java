/*
 * @(#)homeworldXmlForm.java  2004-3-29
 * 
 * Copyright (c) Beijing IDN Systems Integration Co., Ltd. 2004 
 * All rights reserved. 
 */
package homeworld.xml;


			import javax.servlet.http.HttpServletRequest;
			import org.apache.struts.action.ActionErrors;
			import org.apache.struts.action.ActionForm;
			import org.apache.struts.action.ActionMapping;

			/**
			 * <P> </P>
			 * 
			 * @version 0.1
			 * @author ¿Ó”¿≥ı
			 */
			public class homeworldXmlForm extends ActionForm {

				
				public homeworldXmlForm  () {
					super();
				}

				/* (non-Javadoc)
				 * @see org.apache.struts.action.ActionForm#reset(org.apache.struts.action.ActionMapping, javax.servlet.http.HttpServletRequest)
				 */
				public void reset(ActionMapping mapping, HttpServletRequest request) {
				}
				/* (non-Javadoc)
				 * @see org.apache.struts.action.ActionForm#validate(org.apache.struts.action.ActionMapping, javax.servlet.http.HttpServletRequest)
				 */
				public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
					ActionErrors errors = new ActionErrors();
					return errors;
				}


			}
