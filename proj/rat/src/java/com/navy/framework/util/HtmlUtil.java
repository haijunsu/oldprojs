/*
 * @(#)HtmlUtil.java  2005-1-6
 * Copyright (c) 2005. All rights reserved.
 *
 * $Header: /navysu/Smirt18/src/framework/util/http/HtmlUtil.java,v 1.1 2005/01/20 03:14:28 navy Exp $
 * $Log: HtmlUtil.java,v $
 * Revision 1.1  2005/01/20 03:14:28  navy
 * Create SMiRT 18 project
 *
 */
package com.navy.framework.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.util.HtmlUtils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * <p>
 * <b>Description</b>
 * </p>
 * <p>
 * deal with html text
 * </p>
 *
 * $Revision: 1.1 $
 *
 * @author su_haijun <a href=mailto:su_hj@126.com>su_hj@126.com</a>
 */
public class HtmlUtil {

	public static String htmlEscape(String strHtml) {
		return HtmlUtils.htmlEscape(strHtml);
	}

	public static String htmlUnEscape(String strHtml) {
		return HtmlUtils.htmlUnescape(strHtml);
	}

	public static String htmlFormat(String strTxt) {
		String _strTemp = strTxt.replaceAll("\r\n", "<br>");
		_strTemp = _strTemp.replaceAll("\n", "<br>");
		_strTemp = _strTemp.replaceAll("\r", "<br>");
		return _strTemp;
	}

	/** Strip jsessionid off of a URL */
	public static String stripJsessionId(String url) {
		// Strip off jsessionid found in referer URL
		int startPos = url.indexOf(";jsessionid=");
		if (startPos != -1) {
			int endPos = url.indexOf("?", startPos);
			if (endPos == -1) {
				url = url.substring(0, startPos);
			} else {
				url = url.substring(0, startPos)
						+ url.substring(endPos, url.length());
			}
		}
		return url;
	}

	/**
	 * Escape, but do not replace HTML. The default behaviour is to escape
	 * ampersands.
	 */
	public static String escapeHTML(String s) {
		return escapeHTML(s, true);
	}

	// ------------------------------------------------------------------------
	/**
	 * Escape, but do not replace HTML.
	 *
	 * @param escapeAmpersand
	 *            Optionally escape ampersands (&amp;).
	 */
	public static String escapeHTML(String s, boolean escapeAmpersand) {
		// got to do amp's first so we don't double escape
		if (escapeAmpersand) {
			s = StringUtils.replace(s, "&", "&amp;");
		}
		s = StringUtils.replace(s, "&nbsp;", " ");
		s = StringUtils.replace(s, "\"", "&quot;");
		s = StringUtils.replace(s, "<", "&lt;");
		s = StringUtils.replace(s, ">", "&gt;");
		return s;
	}

	public static String unescapeHTML(String str) {
		return StringEscapeUtils.unescapeHtml(str);
	}

	// ------------------------------------------------------------------------
	/**
	 * Remove occurences of html, defined as any text between the characters
	 * "&lt;" and "&gt;". Replace any HTML tags with a space.
	 */
	public static String removeHTML(String str) {
		return removeHTML(str, true);
	}

	/**
	 * Remove occurences of html, defined as any text between the characters
	 * "&lt;" and "&gt;". Optionally replace HTML tags with a space.
	 *
	 * @param str
	 * @param addSpace
	 * @return
	 */
	public static String removeHTML(String str, boolean addSpace) {
		if (str == null)
			return "";
		StringBuffer ret = new StringBuffer(str.length());
		int start = 0;
		int beginTag = str.indexOf("<");
		int endTag = 0;
		if (beginTag == -1)
			return str;

		while (beginTag >= start) {
			if (beginTag > 0) {
				ret.append(str.substring(start, beginTag));

				// replace each tag with a space (looks better)
				if (addSpace)
					ret.append(" ");
			}
			endTag = str.indexOf(">", beginTag);

			// if endTag found move "cursor" forward
			if (endTag > -1) {
				start = endTag + 1;
				beginTag = str.indexOf("<", start);
			}
			// if no endTag found, get rest of str and break
			else {
				ret.append(str.substring(beginTag));
				break;
			}
		}
		// append everything after the last endTag
		if (endTag > -1 && endTag + 1 < str.length()) {
			ret.append(str.substring(endTag + 1));
		}
		return ret.toString().trim();
	}

	// ------------------------------------------------------------------------
	/**
	 * Run both removeHTML and escapeHTML on a string.
	 *
	 * @param s
	 *            String to be run through removeHTML and escapeHTML.
	 * @return String with HTML removed and HTML special characters escaped.
	 */
	public static String removeAndEscapeHTML(String s) {
		if (s == null)
			return "";
		else
			return HtmlUtil.escapeHTML(HtmlUtil.removeHTML(s));
	}

	// ------------------------------------------------------------------------
	/**
	 * Autoformat.
	 */
	public static String autoformat(String s) {
		String ret = StringUtils.replace(s, "\n", "<br />");
		return ret;
	}

	// ------------------------------------------------------------------------
	/**
	 * Replaces occurences of non-alphanumeric characters with an underscore.
	 */
	public static String replaceNonAlphanumeric(String str) {
		return replaceNonAlphanumeric(str, '_');
	}

	// ------------------------------------------------------------------------
	/**
	 * Replaces occurences of non-alphanumeric characters with a supplied char.
	 */
	public static String replaceNonAlphanumeric(String str, char subst) {
		StringBuffer ret = new StringBuffer(str.length());
		char[] testChars = str.toCharArray();
		for (int i = 0; i < testChars.length; i++) {
			if (Character.isLetterOrDigit(testChars[i])) {
				ret.append(testChars[i]);
			} else {
				ret.append(subst);
			}
		}
		return ret.toString();
	}

	// ------------------------------------------------------------------------
	/**
	 * Remove occurences of non-alphanumeric characters.
	 */
	public static String removeNonAlphanumeric(String str) {
		StringBuffer ret = new StringBuffer(str.length());
		char[] testChars = str.toCharArray();
		for (int i = 0; i < testChars.length; i++) {
			// MR: Allow periods in page links
			if (Character.isLetterOrDigit(testChars[i]) || testChars[i] == '.') {
				ret.append(testChars[i]);
			}
		}
		return ret.toString();
	}

	/**
	 * Strips HTML and truncates.
	 */
	public static String truncate(String str, int lower, int upper,
			String appendToEnd) {
		// strip markup from the string
		String str2 = removeHTML(str, false);

		// quickly adjust the upper if it is set lower than 'lower'
		if (upper < lower) {
			upper = lower;
		}

		// now determine if the string fits within the upper limit
		// if it does, go straight to return, do not pass 'go' and collect $200
		if (str2.length() > upper) {
			// the magic location int
			int loc;

			// first we determine where the next space appears after lower
			loc = str2.lastIndexOf(' ', upper);

			// now we'll see if the location is greater than the lower limit
			if (loc >= lower) {
				// yes it was, so we'll cut it off here
				str2 = str2.substring(0, loc);
			} else {
				// no it wasnt, so we'll cut it off at the upper limit
				str2 = str2.substring(0, upper);
				loc = upper;
			}

			// the string was truncated, so we append the appendToEnd String
			str2 = str2 + appendToEnd;
		}

		return str2;
	}

	/**
	 * This method based on code from the String taglib at Apache Jakarta:
	 * http://cvs.apache.org/viewcvs/jakarta-taglibs/string/src/org/apache/taglibs/string/util/StringW.java?rev=1.16&content-type=text/vnd.viewcvs-markup
	 * Copyright (c) 1999 The Apache Software Foundation. Author:
	 * timster@mac.com
	 *
	 * @param str
	 * @param lower
	 * @param upper
	 * @param appendToEnd
	 * @return
	 */
	public static String truncateNicely(String str, int lower, int upper,
			String appendToEnd) {
		// strip markup from the string
		String str2 = removeHTML(str, false);
		boolean diff = (str2.length() < str.length());

		// quickly adjust the upper if it is set lower than 'lower'
		if (upper < lower) {
			upper = lower;
		}

		// now determine if the string fits within the upper limit
		// if it does, go straight to return, do not pass 'go' and collect $200
		if (str2.length() > upper) {
			// the magic location int
			int loc;

			// first we determine where the next space appears after lower
			loc = str2.lastIndexOf(' ', upper);

			// now we'll see if the location is greater than the lower limit
			if (loc >= lower) {
				// yes it was, so we'll cut it off here
				str2 = str2.substring(0, loc);
			} else {
				// no it wasnt, so we'll cut it off at the upper limit
				str2 = str2.substring(0, upper);
				loc = upper;
			}

			// HTML was removed from original str
			if (diff) {

				// location of last space in truncated string
				loc = str2.lastIndexOf(' ', loc);

				// get last "word" in truncated string (add 1 to loc to
				// eliminate space
				String str3 = str2.substring(loc + 1);

				// find this fragment in original str, from 'loc' position
				loc = str.indexOf(str3, loc) + str3.length();

				// get truncated string from original str, given new 'loc'
				str2 = str.substring(0, loc);

				// get all the HTML from original str after loc
				str3 = extractHTML(str.substring(loc));

				// remove any tags which generate visible HTML
				// This call is unecessary, all HTML has already been stripped
				// str3 = removeVisibleHTMLTags(str3);

				// append the appendToEnd String and
				// add extracted HTML back onto truncated string
				str = str2 + appendToEnd + str3;
			} else {
				// the string was truncated, so we append the appendToEnd String
				str = str2 + appendToEnd;
			}

		}

		return str;
	}

	public static String truncateText(String str, int lower, int upper,
			String appendToEnd) {
		// strip markup from the string
		String str2 = removeHTML(str, false);
		boolean diff = (str2.length() < str.length());

		// quickly adjust the upper if it is set lower than 'lower'
		if (upper < lower) {
			upper = lower;
		}

		// now determine if the string fits within the upper limit
		// if it does, go straight to return, do not pass 'go' and collect $200
		if (str2.length() > upper) {
			// the magic location int
			int loc;

			// first we determine where the next space appears after lower
			loc = str2.lastIndexOf(' ', upper);

			// now we'll see if the location is greater than the lower limit
			if (loc >= lower) {
				// yes it was, so we'll cut it off here
				str2 = str2.substring(0, loc);
			} else {
				// no it wasnt, so we'll cut it off at the upper limit
				str2 = str2.substring(0, upper);
				loc = upper;
			}
			// the string was truncated, so we append the appendToEnd String
			str = str2 + appendToEnd;
		}
		return str;
	}

	/**
	 * @param str
	 * @return
	 */
	private static String stripLineBreaks(String str) {
		// TODO: use a string buffer, ignore case !
		str = str.replaceAll("<br>", "");
		str = str.replaceAll("<br/>", "");
		str = str.replaceAll("<br />", "");
		str = str.replaceAll("<p></p>", "");
		str = str.replaceAll("<p/>", "");
		str = str.replaceAll("<p />", "");
		return str;
	}

	/**
	 * Need need to get rid of any user-visible HTML tags once all text has been
	 * removed such as &lt;BR&gt;. This sounds like a better approach than
	 * removing all HTML tags and taking the chance to leave some tags
	 * un-closed.
	 *
	 * WARNING: this method has serious performance problems a
	 *
	 * @author Alexis Moussine-Pouchkine
	 *         <alexis.moussine-pouchkine@france.sun.com>
	 * @author Lance Lavandowska
	 * @param str
	 *            the String object to modify
	 * @return the new String object without the HTML "visible" tags
	 */
	private static String removeVisibleHTMLTags(String str) {
		str = stripLineBreaks(str);
		StringBuffer result = new StringBuffer(str);
		StringBuffer lcresult = new StringBuffer(str.toLowerCase());

		// <img should take care of smileys
		String[] visibleTags = { "<img" }; // are there others to add?
		int stringIndex;
		for (int j = 0; j < visibleTags.length; j++) {
			while ((stringIndex = lcresult.indexOf(visibleTags[j])) != -1) {
				if (visibleTags[j].endsWith(">")) {
					result.delete(stringIndex, stringIndex
							+ visibleTags[j].length());
					lcresult.delete(stringIndex, stringIndex
							+ visibleTags[j].length());
				} else {
					// need to delete everything up until next closing '>', for
					// <img for instance
					int endIndex = result.indexOf(">", stringIndex);
					if (endIndex > -1) {
						// only delete it if we find the end! If we don't the
						// HTML may be messed up, but we
						// can't safely delete anything.
						result.delete(stringIndex, endIndex + 1);
						lcresult.delete(stringIndex, endIndex + 1);
					}
				}
			}
		}

		// TODO: This code is buggy by nature. It doesn't deal with nesting of
		// tags properly.
		// remove certain elements with open & close tags
		String[] openCloseTags = { "li", "a", "div", "h1", "h2", "h3", "h4" }; // more
																				// ?
		for (int j = 0; j < openCloseTags.length; j++) {
			// could this be better done with a regular expression?
			String closeTag = "</" + openCloseTags[j] + ">";
			int lastStringIndex = 0;
			while ((stringIndex = lcresult.indexOf("<" + openCloseTags[j],
					lastStringIndex)) > -1) {
				lastStringIndex = stringIndex;
				// Try to find the matching closing tag (ignores possible
				// nesting!)
				int endIndex = lcresult.indexOf(closeTag, stringIndex);
				if (endIndex > -1) {
					// If we found it delete it.
					result.delete(stringIndex, endIndex + closeTag.length());
					lcresult.delete(stringIndex, endIndex + closeTag.length());
				} else {
					// Try to see if it is a self-closed empty content tag, i.e.
					// closed with />.
					endIndex = lcresult.indexOf(">", stringIndex);
					int nextStart = lcresult.indexOf("<", stringIndex + 1);
					if (endIndex > stringIndex
							&& lcresult.charAt(endIndex - 1) == '/'
							&& (endIndex < nextStart || nextStart == -1)) {
						// Looks like it, so remove it.
						result.delete(stringIndex, endIndex + 1);
						lcresult.delete(stringIndex, endIndex + 1);

					}
				}
			}
		}

		return result.toString();
	}

	/**
	 * Extract (keep) JUST the HTML from the String.
	 *
	 * @param str
	 * @return
	 */
	public static String extractHTML(String str) {
		if (str == null)
			return "";
		StringBuffer ret = new StringBuffer(str.length());
		int start = 0;
		int beginTag = str.indexOf("<");
		int endTag = 0;
		if (beginTag == -1)
			return str;

		while (beginTag >= start) {
			endTag = str.indexOf(">", beginTag);

			// if endTag found, keep tag
			if (endTag > -1) {
				ret.append(str.substring(beginTag, endTag + 1));

				// move start forward and find another tag
				start = endTag + 1;
				beginTag = str.indexOf("<", start);
			}
			// if no endTag found, break
			else {
				break;
			}
		}
		return ret.toString();
	}

	/**
	 * URL encoding.
	 *
	 * @param s
	 *            a string to be URL-encoded
	 * @return URL encoding of s using character encoding UTF-8; null if s is
	 *         null.
	 */
	public static final String urlEncode(String s) {
		try {
			if (s != null)
				return URLEncoder.encode(s, "UTF-8");
			else
				return s;
		} catch (UnsupportedEncodingException e) {
			// Java Spec requires UTF-8 be in all Java environments, so this
			// should not happen
			return s;
		}
	}

	/**
	 * URL decoding.
	 *
	 * @param s
	 *            a URL-encoded string to be URL-decoded
	 * @return URL decoded value of s using character encoding UTF-8; null if s
	 *         is null.
	 */
	public static final String urlDecode(String s) {
		try {
			if (s != null)
				return URLDecoder.decode(s, "UTF-8");
			else
				return s;
		} catch (UnsupportedEncodingException e) {
			// Java Spec requires UTF-8 be in all Java environments, so this
			// should not happen
			return s;
		}
	}

}
