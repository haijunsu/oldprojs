package groller.framework.common;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @author  沈瑜
 */
public class StringUtil {
	public static final String replace( String line, String oldString, String newString )
	{
		if (line == null) {
			return null;
		}
		int i=0;
		if ( ( i=line.indexOf( oldString, i ) ) >= 0 ) {
			char [] line2 = line.toCharArray();
			char [] newString2 = newString.toCharArray();
			int oLength = oldString.length();
			StringBuffer buf = new StringBuffer(line2.length);
			buf.append(line2, 0, i).append(newString2);
			i += oLength;
			int j = i;
			while( ( i=line.indexOf( oldString, i ) ) > 0 ) {
				buf.append(line2, j, i-j).append(newString2);
				i += oLength;
				j = i;
			}
			buf.append(line2, j, line2.length - j);
			return buf.toString();
		}
		return line;
	}
	
	public static final boolean isEmptyString(String s){
		if(s==null) return true;
		if("".equals(s.trim())) return true;
		return false;
	}
	
	public static final String[] split(String string, String delim) {
		StringTokenizer token = new StringTokenizer(string, delim);
		String[] result = new String[token.countTokens()];
		List tmp = new ArrayList();
		while(token.hasMoreTokens()) {
			tmp.add(token.nextToken());
		}
		tmp.toArray(result);
		return result;
	}
	
	/**
	 * 返回值定为String[2]
	 */
	public static final String[] splitStringForOracle(String s){
		if(s==null) return NullStringArray2;
		int length = s.length();
		if(length <= 650) return new String[]{s,null};
		String a = s.substring(0,650);
		String b = s.substring(650,length);
		return new String[]{a,b};
	}
	private static final String[] NullStringArray2 = new String[2];
}
