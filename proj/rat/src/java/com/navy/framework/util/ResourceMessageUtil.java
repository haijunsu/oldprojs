/*
 * @(#)ResourceMessageUtil.java  Jul 4, 2006
 * Copyright (c) TrueTel Communications 2006. All rights reserved.
 *
 * $Header$
 */

package com.navy.framework.util;

import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.TreeSet;

import com.navy.framework.web.data.LabelValueBean;

/**
 * <p>
 * <b>Description</b>
 * </p>
 * <p>
 * </p>
 *
 * @author: hjsu
 * @version: $Revision$
 */
public class ResourceMessageUtil {

	/**
	 * Logger for this class
	 */
	private static final org.apache.commons.logging.Log logger = org.apache.commons.logging.LogFactory
			.getLog(ResourceMessageUtil.class);

	private static Map m_bundleMap = new HashMap();

	private static Map m_enumKeysMap = new HashMap();

	private final static String MESSAGE_BUNDLE = "properties.Messages";

	private static ResourceBundle getResourceBundle(String localeValue) {
		ResourceBundle _resourceBundle = (ResourceBundle) m_bundleMap
				.get(localeValue);
		if (_resourceBundle == null) {
			try {
				_resourceBundle = getResourceBundle(LocaleUtil
						.getLocaleByValue(localeValue));
			} catch (Exception e) {
				logger
						.warn(
								"Exception of resource bundle when get by locale. " +
								"Return default resource bundle of default locale.",
								e);
				_resourceBundle = getResourceBundle(Locale.getDefault());
		}
		}
		return _resourceBundle;
	}

	private static ResourceBundle getResourceBundle(Locale locale) {
		ResourceBundle _resourceBundle = (ResourceBundle) m_bundleMap
				.get(locale.toString());
		if (_resourceBundle == null) {
			synchronized (m_bundleMap) {
				try {
					if (locale == null) {
						_resourceBundle = ResourceBundle
								.getBundle(MESSAGE_BUNDLE);
					} else {
						_resourceBundle = ResourceBundle.getBundle(
								MESSAGE_BUNDLE, locale);
					}
				} catch (MissingResourceException e) {
					logger.warn(e.getMessage(), e);
					_resourceBundle = ResourceBundle.getBundle(MESSAGE_BUNDLE);
				}
				m_bundleMap.put(locale.toString(), _resourceBundle);
			}
		}
		return _resourceBundle;
	}

	public static String getString(String key, String localeValue) {
		return PropertyReader.getString(getResourceBundle(localeValue), key);
	}

	public static String getString(String key, Locale locale) {
		return PropertyReader.getString(getResourceBundle(locale), key);

	}

	public static Map getEnumKeys() {
		if (m_enumKeysMap.isEmpty()) {
			synchronized (m_enumKeysMap) {
				Enumeration _enumeration = getResourceBundle(
						new Locale("en", "US")).getKeys();
				while (_enumeration.hasMoreElements()) {
					String _strKey = (String) _enumeration.nextElement();
					// SleexDebug.log(9, _strKey);
					int _iDotIndex = _strKey.indexOf('.');
					if (_iDotIndex > 0) {
						String _strEnumKey = _strKey.substring(0, _iDotIndex);
						if (_strEnumKey.endsWith("Enum")) {
							HashMap _mapEnum = (HashMap) m_enumKeysMap
									.get(_strEnumKey);
							if (_mapEnum == null) {
								_mapEnum = new HashMap();
							}
							_mapEnum.put(_strKey, _strKey.substring(_strKey
									.indexOf('.') + 1));
							m_enumKeysMap.put(_strEnumKey, _mapEnum);
						}
					}
				}
			}
		}
		return m_enumKeysMap;
	}

	public static Collection getEnumLabelValueBeans(String EnumTypeName,
			Locale locale) {
		HashMap _map = (HashMap) getEnumKeys().get(EnumTypeName);
		if (_map == null) {
			logger.warn(new StringBuffer("Missing EnumType: ").append(
					EnumTypeName).toString());
			return new TreeSet();
		}
		TreeSet _set = new TreeSet();
		Set _setKey = _map.keySet();
		Iterator _iterator = _setKey.iterator();
		while (_iterator.hasNext()) {
			String _key = (String) _iterator.next();
			String _strLabel = getString(_key, locale);
			String _strValue = (String) _map.get(_key);
			_set.add(new LabelValueBean(_strLabel, _strValue));
		}
		return _set;
	}
}
