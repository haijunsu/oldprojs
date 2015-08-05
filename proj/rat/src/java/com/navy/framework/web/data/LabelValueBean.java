/*
 * @(#)LabelValueBean.java  Feb 9, 2006
 * Copyright (c) TrueTel Communications 2006. All rights reserved.
 *
 * $Header$
 */

package com.navy.framework.web.data;

import java.io.Serializable;
import java.text.CollationKey;
import java.text.Collator;
import java.util.Comparator;

public class LabelValueBean implements Comparable, Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = -43748212708705695L;

	private String m_value;

	private String m_label;

	public LabelValueBean() {
		super();
	}

	/**
	 * Construct an instance with the supplied property values.
	 *
	 * @param label
	 *            The label to be displayed to the user.
	 * @param value
	 *            The value to be returned to the server.
	 */
	public LabelValueBean(String label, String value) {
		this.m_value = value;
		this.m_label = label;
	}

	public String getLabel() {
		return this.m_label;
	}

	public void setLabel(String label) {
		m_label = label;
	}

	public String getValue() {
		return this.m_value;
	}

	public void setValue(String value) {
		m_value = value;
	}

	/**
	 * Comparator that can be used for a case insensitive sort of
	 * <code>LabelValueBean</code> objects.
	 */
	public static final Comparator CASE_INSENSITIVE_ORDER = new Comparator() {
		public int compare(Object o1, Object o2) {
			String label1 = ((LabelValueBean) o1).getLabel();
			String label2 = ((LabelValueBean) o2).getLabel();
			return label1.compareToIgnoreCase(label2);
		}
	};

	public static final Comparator VALUE_ORDER = new Comparator() {
		public int compare(Object o1, Object o2) {
			String value1 = ((LabelValueBean) o1).getValue();
			String value2 = ((LabelValueBean) o2).getValue();
			return value1.compareTo(value2);
		}
	};

	public static final Comparator INT_VALUE_ORDER = new Comparator() {
		public int compare(Object o1, Object o2) {
			Integer value1 = new Integer(((LabelValueBean) o1).getValue());
			Integer value2 = new Integer(((LabelValueBean) o2).getValue());
			return value1.compareTo(value2);
		}
	};

	// --------------------------------------------------------- Public Methods

	/**
	 * Compare LabelValueBeans based on the label, because that's the human
	 * viewable part of the object.
	 *
	 * @see Comparable
	 */
	public int compareTo(Object o) {
		// Implicitly tests for the correct type, throwing
		// ClassCastException as required by interface
		Collator collator = Collator.getInstance();
		CollationKey otherLabel = collator.getCollationKey(((LabelValueBean) o)
				.getLabel());
		CollationKey label = collator.getCollationKey(getLabel());

		return label.compareTo(otherLabel);
	}

	/**
	 * Return a string representation of this object.
	 */
	public String toString() {
		StringBuffer sb = new StringBuffer("LabelValueBean[");
		sb.append(this.m_label);
		sb.append(", ");
		sb.append(this.m_value);
		sb.append("]");
		return (sb.toString());
	}

	/**
	 * LabelValueBeans are equal if their values are both null or equal.
	 *
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}

		if (!(obj instanceof LabelValueBean)) {
			return false;
		}

		LabelValueBean bean = (LabelValueBean) obj;
		int nil = (this.getValue() == null) ? 1 : 0;
		nil += (bean.getValue() == null) ? 1 : 0;

		if (nil == 2) {
			return true;
		} else if (nil == 1) {
			return false;
		} else {
			return this.getValue().equals(bean.getValue());
		}

	}

	/**
	 * The hash code is based on the object's value.
	 *
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return (this.getValue() == null) ? 17 : this.getValue().hashCode();
	}
}
