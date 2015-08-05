/*
 * $Header$
 * $Revision$
 * $Date$
 *
 * ====================================================================
 * The PLOTNIX Software License, Version 1.0
 *
 *
 * Copyright (c) 2001 The PLOTNIX Software Foundation.  All rights
 * reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the
 *    distribution.
 *
 * 3. The end-user documentation included with the redistribution,
 *    if any, must include the following acknowledgment:
 *       "This product includes software developed by the
 *        PLOTNIX, Inc (http://www.plotnix.com/)."
 *    Alternately, this acknowledgment may appear in the software itself,
 *    if and wherever such third-party acknowledgments normally appear.
 *
 * 4. The name "PLOTNIX" must not be used to endorse or promote
 *    products derived from this software without prior written
 *    permission. For written permission, please contact dmitri@plotnix.com.
 *
 * 5. Products derived from this software may not be called "PLOTNIX",
 *    nor may "PLOTNIX" appear in their name, without prior written
 *    permission of the PLOTNIX, Inc.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL THE APACHE SOFTWARE FOUNDATION OR
 * ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
 * USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
 * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 * ====================================================================
 *
 * For more information on PLOTNIX, Inc, please see <http://www.plotnix.com/>.
 */

package com.navy.framework.enums;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.InvalidClassException;
import java.io.ObjectStreamException;
import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * Enum represents a value characterized by the following properties:
 * <ul>
 * <li>It has an integer value that uniquely identifies it among other Enums
 * belonging to the same class. By default, the integer value represents the
 * order in which Enums are added to the class, starting with 0. Alternatively,
 * an integer value can be assigned explicitly to the Enum in the constructor.
 * </li>
 * <li>It has a string value, which is also unique. By default, this string
 * value is the name of a <code>public static Enum</code> field declared on
 * this Enum's own class for this enum. If there is no such name and the string
 * value was not explicitly specified in the constructor, then the string value
 * is a string representation of the Enum's integer value. </li>
 * <li>The toString() method of the Enum returns a human readable description
 * of the Enum. The class attempts to locate a label for the constant in the
 * resource bundle that has the same name as the class itself. If that fails and
 * a default label was specified in the constructor, that label is returned. If
 * no such label was specified, the string value of the constant is returned.
 * </li>
 * </ul>
 *
 * Static methods of the Enum class support:
 * <ul>
 * <li>Loading an Enum from .property file</li>
 * <li>Automatic generation of Enum objects from integer constants</li>
 * <li>Retrieving an array of all constants of a given type</li>
 * <li>Retrieving an individual constant of a given type by its string or
 * integer value</li>
 * </ul>
 *
 * <a href="http://www.plotnix.com/enum/index.html">Reference doc</a>
 *
 * @author Dmitri Plotnikov
 * @version $Revision$ $Date$
 */
public class BaseEnum implements Serializable {
	private transient EnumList type;

	private String string;

	private int integer;

	private String label;

	private Locale m_locale = Locale.getDefault();

	private static HashMap byClass = new HashMap();

	public String getLabel() {
		return toString(getLocale());
	}

	public Integer getIntValue() {
		return new Integer(intValue());
	}

	public String getStrValue() {
		return stringValue();
	}

	/**
	 * Initialize the Enum, using default integer and string values.
	 */
	protected BaseEnum() {
		type = getEnumList(getClass());
		integer = type.add(this);
	}

	/**
	 * Initialize the Enum with a string value. An integer value is assigned
	 * automatically.
	 */
	protected BaseEnum(String string) {
		type = getEnumList(getClass());
		this.string = string;
		integer = type.add(this);
	}

	/**
	 * Initialize the Enum with a string value and a default label. An integer
	 * value is assigned automatically.
	 */
	protected BaseEnum(String string, String label) {
		type = getEnumList(getClass());
		this.string = string;
		this.label = label;
		integer = type.add(this);
	}

	/**
	 * Initialize the Enum with an integer value. A string value will be
	 * produced automatically.
	 */
	protected BaseEnum(int integer) {
		type = getEnumList(getClass());
		this.integer = integer;
		type.add(this);
	}

	/**
	 * Initialize the Enum with an integer value and a default label. A string
	 * value will be produced automatically.
	 */
	protected BaseEnum(int integer, String label) {
		type = getEnumList(getClass());
		this.integer = integer;
		this.label = label;
		type.add(this);
	}

	/**
	 * Returns the string value of this Enum. Depending on how the Enum was
	 * created, the method behaves differently: <ui>
	 * <li>If the constant was created as a <code>public static Enum</code>,
	 * the name of that field is returned.</li>
	 * <li>If the constant was declared as a <code>public static int</code>,
	 * and registered with <code>initIntegerEnum</code>, the name of that
	 * integer field is returned.</li>
	 * <li>If the constant was loaded from a .properties file, the
	 * corresponding property name is returned.</li>
	 * </ul>
	 */
	public String stringValue() {
		if (string == null) {
			string = type.getDefaultStringValue(this);
		}
		return string;
	}

	/**
	 * Returns the Enum's integer value. An Enum can be allocated with an
	 * explicitly specified integer value. If the integer value is not
	 * specified, the Enum's constructor gives it a default one, which is
	 * computed by adding one to the integer value of the previous Enum of the
	 * same type.
	 */
	public int intValue() {
		return integer;
	}

	/**
	 * Returns this Enum's human readable description in the default locale.
	 */
	public String toString() {
		return toString(Locale.getDefault());
	}

	/**
	 * Returns this Enum's human readable description in the specified locale.
	 * If the resource bundle is not found or does not have a value for this
	 * Enum, the value of the default label specified during construction is
	 * returned. If that value was not unspecified, the Enum's string value is
	 * returned.
	 */
	public String toString(Locale locale) {
		try {
			ResourceBundle bundle = ResourceBundle.getBundle(getClass()
					.getName(), locale, getClass().getClassLoader());
			return bundle.getString(stringValue());
		} catch (MissingResourceException e) {
		}
		if (label != null) {
			return label;
		}
		return stringValue();
	}

	/**
	 * Returns an array of all Enums of the specified class in the order they
	 * were created.
	 */
	public static BaseEnum[] enums(Class enumClass) {
		return getEnumList(enumClass).enums();
	}

	/**
	 * Returns the Enum of the specified class that has the specified st, or
	 * null if there is no such Enum.
	 */
	public static BaseEnum enums(Class enumClass, String string) {
		return getEnumList(enumClass).get(string);
	}

	/**
	 * Returns the Enum of the specified class that has the specified integer
	 * value, or null if there is no such Enum.
	 */
	public static BaseEnum enums(Class enumClass, int integer) {
		return getEnumList(enumClass).get(integer);
	}

	protected static void initIntegerEnum(Class enumClass) {
		Field fields[] = enumClass.getFields();
		for (int i = 0; i < fields.length; i++) {
			int mod = fields[i].getModifiers();
			if (Modifier.isPublic(mod) && Modifier.isStatic(mod)) {
				Class type = fields[i].getType();
				if (type == int.class || type == char.class
						|| type == short.class || type == byte.class) {
					try {
						Number n = (Number) fields[i].get(null);
						BaseEnum _enum = (BaseEnum) enumClass.newInstance();
						_enum.integer = n.intValue();
						_enum.string = fields[i].getName();
					} catch (Exception e) {
						throw new RuntimeException(
								"Cannot init integer Enum class "
										+ enumClass.getName() + "\n   " + e);
					}
				}
			}
		}
	}

	/**
	 * Loads properties from an <i>enumClass</i>.properties resource file. Uses
	 * property names as string values of the Enums.
	 */
	protected static void loadEnum(Class enumClass) {
		allocateEnums(enumClass, loadKeys(enumClass), false);
	}

	/**
	 * Loads properties from an <i>enumClass</i>.properties resource file. Uses
	 * property names as integer values of the Enums.
	 */
	protected static void loadIntegerEnum(Class enumClass) {
		allocateEnums(enumClass, loadKeys(enumClass), true);
	}

	/**
	 * Finds the EnumList registered for the specified class. Allocates one if
	 * it does not exist yet.
	 */
	private static EnumList getEnumList(Class clazz) {
		EnumList type = (EnumList) byClass.get(clazz);
		if (type == null) {
			type = new EnumList();
			byClass.put(clazz, type);
		}
		return type;
	}

	/**
	 * Ensures uniqueness of Enums during deserialization. Finds an Enum that is
	 * already declared with the same integer value and returns that Enum
	 * instead of the current instance.
	 */
	protected Object readResolve() throws ObjectStreamException {
		Class clazz = getClass();
		EnumList type = null;
		try {
			type = getEnumList(clazz);
		} catch (Exception ex) {
			throw new InvalidClassException(clazz.getName(),
					"Is not a valid Enum class, " + ex.getMessage());
		}

		BaseEnum _enum = type.get(integer);
		if (_enum == null) {
			throw new InvalidClassException(clazz.getName(),
					"Enum is not associated with its own EnumList " + integer);
		}
		return _enum;
	}

	/**
	 * Loads property names from an <i>enumClass</i>.properties file.
	 */
	private static List loadKeys(Class enumClass) {
		final List keys = new ArrayList();
		String resName = enumClass.getName().replace('.', '/') + ".properties";
		try {
			InputStream stream = null;
			ClassLoader loader = enumClass.getClassLoader();
			if (loader != null) {
				stream = loader.getResourceAsStream(resName);
			} else {
				stream = ClassLoader.getSystemResourceAsStream(resName);
			}

			if (stream == null) {
				throw new RuntimeException("No such resource");
			}

			stream = new BufferedInputStream(stream);

			// We are using the Properties class as a parser for the resource
			// file
			new Properties() {
				public Object put(Object key, Object value) {
					keys.add(key);
					return super.put(key, value);
				}
			}.load(stream);
			stream.close();
		} catch (Exception ex) {
			throw new RuntimeException("Cannot load values from: " + resName
					+ "\n  " + ex);
		}
		return keys;
	}

	/**
	 * Creates Enums for all keys (string or integer) in the keys list.
	 */
	private static void allocateEnums(Class enumClass, List keys,
			boolean isInteger) {
		Constructor constructor = null;
		try {
			constructor = enumClass
					.getConstructor(new Class[] { isInteger ? int.class
							: String.class });
		} catch (NoSuchMethodException ex) {
		}

		for (Iterator it = keys.iterator(); it.hasNext();) {
			String key = (String) it.next();
			try {
				if (isInteger) {
					if (constructor != null) {
						Integer code = Integer.decode(key);
						constructor.newInstance(new Object[] { code });
					} else {
						int code = Integer.parseInt(key);
						BaseEnum _enum = (BaseEnum) enumClass.newInstance();
						_enum.string = key;
						_enum.integer = code;
					}
				} else {
					if (constructor != null) {
						constructor.newInstance(new Object[] { key });
					} else {
						BaseEnum _enum = (BaseEnum) enumClass.newInstance();
						_enum.string = key;
					}
				}
			} catch (Exception ex) {
				throw new RuntimeException("Cannot allocate Enum "
						+ enumClass.getName() + "." + key + "\n  " + ex);
			}
		}
	}

	/**
	 * There is one instance of EnumList per Enum type. The object maintains all
	 * sorts of per-type information about an Enum type.
	 */
	private static class EnumList {
		private ArrayList enumList = new ArrayList();

		private BaseEnum[] array;

		private HashMap byString;

		private HashMap byEnum;

		private static final BaseEnum[] ENUM_ARRAY = new BaseEnum[0];

		/**
		 * Called automatically by the Enum constructor.
		 */
		public int add(BaseEnum _enum) {
			enumList.add(_enum);
			array = null;
			byString = null;
			if (enumList.size() == 1) {
				return 0;
			} else {
				return ((BaseEnum) enumList.get(enumList.size() - 2))
						.intValue() + 1;
			}
		}

		/**
		 * Returns all array contained by the enum.
		 */
		public BaseEnum[] enums() {
			if (array == null) {
				array = (BaseEnum[]) enumList.toArray(ENUM_ARRAY);
			}
			return array;
		}

		/**
		 * Returns a Enum for the specified string value, or null if there is no
		 * such enum.
		 */
		public BaseEnum get(String string) {
			BaseEnum enums[] = enums();
			if (enums.length < 10) {
				for (int i = 0; i < enums.length; i++) {
					if (enums[i].stringValue().equals(string)) {
						return enums[i];
					}
				}
				return null;
			}

			if (byString == null) {
				byString = new HashMap();
				BaseEnum[] array = enums();
				for (int i = 0; i < array.length; i++) {
					byString.put(array[i].stringValue(), array[i]);
				}
			}
			return (BaseEnum) byString.get(string);
		}

		/**
		 * Returns an Enum for the specified integer value, or null if there is
		 * no such Enum.
		 */
		public BaseEnum get(int integer) {
			BaseEnum enums[] = enums();
			if (integer >= 0 && integer < enums.length) {
				if (enums[integer].intValue() == integer) {
					return enums[integer];
				}
			}

			for (int i = 0; i < enums.length; i++) {
				if (enums[i].intValue() == integer) {
					return enums[i];
				}
			}
			return null;
		}

		/**
		 * Returns the name of a public static field declared by the specified
		 * enum's class for the specified enum. If there is no such field,
		 * returns the enum's integer value.
		 */
		public String getDefaultStringValue(BaseEnum baseEnum) {
			String string = null;
			if (byEnum != null) {
				string = (String) byEnum.get(baseEnum);
				if (string != null) {
					return string;
				}
			} else {
				byEnum = new HashMap();
			}
			Field fields[] = baseEnum.getClass().getFields();
			for (int i = 0; i < fields.length; i++) {
				int mod = fields[i].getModifiers();
				if (Modifier.isPublic(mod) && Modifier.isStatic(mod)
						&& BaseEnum.class.isAssignableFrom(fields[i].getType())) {
					try {
						BaseEnum e = (BaseEnum) fields[i].get(null);
						byEnum.put(e, fields[i].getName());
						if (e == baseEnum) {
							string = fields[i].getName();
						}
					} catch (IllegalAccessException e) {
						// Ignore errors
					}
				}
			}
			if (string == null) {
				string = String.valueOf(baseEnum.intValue());
			}
			return string;
		}
	}

	/**
	 * @return the locale
	 */
	public Locale getLocale() {
		return this.m_locale;
	}

	/**
	 * @param locale
	 *            the locale to set
	 */
	public void setLocale(Locale locale) {
		m_locale = locale;
	}

}
