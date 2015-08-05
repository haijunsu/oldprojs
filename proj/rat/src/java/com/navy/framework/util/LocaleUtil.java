/*
 * @(#)LocaleUtil.java  2007-1-10
 * Copyright (c) 2007. All rights reserved.
 *
 * $Header$
 */

package com.navy.framework.util;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Currency;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.navy.framework.web.data.LabelValueBean;

/**
 * <p>
 * <b>Description</b>
 * </p>
 * <p>
 * </p>
 *
 * @author Haijun Su
 */
public class LocaleUtil {

	private static Map allLocalesMap = new HashMap();

	private static Map allCurrenciesMap = new HashMap();

	private static List allCurrencies = new ArrayList();

	private static final Locale[] AVAILABLE_LOCALES = Locale
			.getAvailableLocales();

	static {
		for (int i = 0; i < AVAILABLE_LOCALES.length; i++) {
			allLocalesMap.put(AVAILABLE_LOCALES[i].toString().toLowerCase(),
					AVAILABLE_LOCALES[i]);
		}
		for (int i = 0; i < AVAILABLE_LOCALES.length; i++) {
			String country = AVAILABLE_LOCALES[i].getCountry();
			if (country.length() != 2) {
				continue;
			}

			final Currency currency = Currency
					.getInstance(AVAILABLE_LOCALES[i]);
			final String iso = currency.getCurrencyCode();
			final String name = currency.getCurrencyCode() + " ("
					+ currency.getSymbol(AVAILABLE_LOCALES[i]) + ")";

			LabelValueBean currencyBean = new LabelValueBean(name, iso);

			if (!allCurrencies.contains(currencyBean)) {
				allCurrencies.add(currencyBean);
				allCurrenciesMap.put(iso, currencyBean);
			}
		}
	}

	private LocaleUtil() {
	}

	public static boolean isAvailableLocale(String localeValue) {
		return allLocalesMap.get(localeValue.toLowerCase().trim()) != null;
	}

	public static Locale getLocaleByValue(String localeValue) throws Exception {
		String _localeValue = localeValue.trim().toLowerCase();
		if (!isAvailableLocale(_localeValue)) {
			throw new Exception(localeValue + " is not a available value.");
		}
		return (Locale) allLocalesMap.get(_localeValue);
	}

	public static List getCountries(Locale locale) {

		List countries = new ArrayList();

		for (int i = 0; i < AVAILABLE_LOCALES.length; i++) {
			String iso = AVAILABLE_LOCALES[i].getCountry();
			String name = AVAILABLE_LOCALES[i].getDisplayCountry(locale);

			if (StringUtils.isNotBlank(iso) && StringUtils.isNotBlank(name)) {
				LabelValueBean country = new LabelValueBean(name, iso);

				if (!countries.contains(country)) {
					countries.add(country);
				}
			}
		}

		Collections.sort(countries);

		return countries;
	}

	public static List getLanguages(Locale locale) {

		List languages = new ArrayList();

		for (int i = 0; i < AVAILABLE_LOCALES.length; i++) {
			String iso = AVAILABLE_LOCALES[i].toString();
			String name = AVAILABLE_LOCALES[i].getDisplayName(locale);

			LabelValueBean language = new LabelValueBean(name, iso);
			if (!languages.contains(language)) {
				languages.add(language);
			}
		}

		Collections.sort(languages);

		return languages;
	}

	public static List getCurrencies() {
		return allCurrencies;
	}

	public static LabelValueBean getCurrencyBean(String currencyCode) {
		return (LabelValueBean) allCurrenciesMap.get(currencyCode);
	}

	public static LabelValueBean getLanguageBean(String language, Locale locale)
			throws Exception {
		Locale _language = getLocaleByValue(language);
		String iso = _language.toString();
		String name = _language.getDisplayName(locale);

		LabelValueBean _languageBean = new LabelValueBean(name, iso);
		return _languageBean;
	}

	public static LabelValueBean getCountryBean(String country, Locale locale)
			throws Exception {
		Locale _country = getLocaleByValue(country);
		String iso = _country.getCountry();
		String name = _country.getDisplayCountry(locale);

		LabelValueBean _languageBean = new LabelValueBean(name, iso);
		return _languageBean;

	}

	public static String getCurrencyDisplay(double value, String currencyCode,
			Locale locale) {
		NumberFormat nf = NumberFormat.getCurrencyInstance(locale);
		Currency currency = Currency.getInstance(currencyCode);
		nf.setCurrency(currency);
		return nf.format(value);
	}
}
