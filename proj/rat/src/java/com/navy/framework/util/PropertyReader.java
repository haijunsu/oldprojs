package com.navy.framework.util;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class PropertyReader {
	/**
	 * Logger for this class
	 */
	private static final org.apache.commons.logging.Log logger = org.apache.commons.logging.LogFactory
			.getLog(PropertyReader.class);

	public static String getString(ResourceBundle bundle, String key) {
		try {
			return bundle.getString(key);
		} catch (MissingResourceException e) {
			logger.warn('!' + key + '!' + " not found!", e);
			return "???" + key + "???";
		}
	}

	public static String[] getStringArray(ResourceBundle bundle, String key) {
		try {
			return bundle.getStringArray(key);
		} catch (MissingResourceException e) {
			logger.warn('!' + key + '!' + " not found!", e);
			return new String[] { "???" + key + "???" };
		}
	}

	public static Map getEnumKeys(ResourceBundle bundle) {
		Map _enumKeysMap = new HashMap();
		Enumeration _enumeration = bundle.getKeys();
		while (_enumeration.hasMoreElements()) {
			String _strKey = (String) _enumeration.nextElement();
			// SleexDebug.log(9, _strKey);
			int _iDotIndex = _strKey.indexOf('.');
			if (_iDotIndex > 0) {
				String _strEnumKey = _strKey.substring(0, _iDotIndex);
				if (_strEnumKey.endsWith("Enum")) {
					HashMap _mapEnum = (HashMap) _enumKeysMap.get(_strEnumKey);
					if (_mapEnum == null) {
						_mapEnum = new HashMap();
					}
					_mapEnum.put(_strKey, _strKey.substring(_strKey
							.indexOf('.') + 1));
					_enumKeysMap.put(_strEnumKey, _mapEnum);
				}
			}
		}
		return _enumKeysMap;
	}

	public static Map getEnumKeys(String bundleFile) {
		ResourceBundle _resourceBundle = ResourceBundle.getBundle(bundleFile);
		return getEnumKeys(_resourceBundle);
	}
}
