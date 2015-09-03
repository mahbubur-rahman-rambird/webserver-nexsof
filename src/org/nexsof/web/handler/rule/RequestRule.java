/*
 * RequestRule.java (May 2013)
 *
 * Author: Mahbubur 'Sumon' Rahman (mahbubur@gmail.com)
 *
 */
package org.nexsof.web.handler.rule;


/**
 * This <code>RequestRule</code> interface has method to apply the validation rule 
 *
 * @author Mahbubur 'Sumon' Rahman (mahbubur@gmail.com)
 *
 * @version 1.0 
 */
public interface RequestRule {
	
	/**
	 * This is used to validate the request against a particular validation rule
	 * 
	 * @return this returns if validation pass the validity rule
	 */
	public boolean checkValid();
}
