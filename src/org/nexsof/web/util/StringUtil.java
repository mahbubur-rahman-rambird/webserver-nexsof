/*
 * StringUtil.java (May 2013)
 *
 * Author: Mahbubur 'Sumon' Rahman (mahbubur@gmail.com)
 */
package org.nexsof.web.util;

/**
 * This is a String utility class to check whether a string is empty or equal check when the subjected string can be null
 *  
 *
 * @author Mahbubur 'Sumon' Rahman (mahbubur@gmail.com)
 *
 * @version 1.0 
 */
public class StringUtil {

	/**
	 *  This is to check whether a string is empty. This also considers that a string can be null or can have whitespaces,
	 *  but it is still an empty string. There are some standard library from apache as StrignUtils which could be used.
	 *  
	 *  However, it was written here to reduce the dependency on third party library or framework.
	 *  
	 * @param str this is string to check if it is empty
	 * @return this returns boolean true/false whether parameter is empty or not
	 */
	public static boolean isEmpty(String str) {
		return (str == null || str.trim().equals(""));
	}
	
	/**
	 * This is to check whether two string are equals or not. This method considers that, for equality, both of the string
	 * needs to be non-empty and not null. Otherwise null string can cause exception standard <code>equals</code> method 
	 * 
	 * @param s1 this is the first string to check equality
	 * @param s2 this is the second string to check equality
	 * @return this returns boolean true/false whether two string equal or not
	 */
	public static boolean equal(String s1, String s2){
		return !isEmpty(s1) && !isEmpty(s2) && s1.equals(s2);
	}
	
}
