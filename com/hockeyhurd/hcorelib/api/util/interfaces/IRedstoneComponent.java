package com.hockeyhurd.hcorelib.api.util.interfaces;

import com.hockeyhurd.hcorelib.api.util.enums.EnumRedstoneType;

/**
 * Simple interface for controlling redstone signals.
 * 
 * @author hockeyhurd
 * @version Jun 4, 2015
 */
public interface IRedstoneComponent {

	/**
	 * @return redstone configured type.
	 */
	EnumRedstoneType getRedstoneType();
	
	/**
	 * Sets redstone type to new type.
	 * 
	 * @param type type to set.
	 */
	void setRedstoneType(EnumRedstoneType type);
	
	/**
	 * @return redstone signal this object is receiving.
	 */
	int getRedstoneSignal();
	
	/**
	 * @return true if redstone signal != redstone threshold (getRedstoneSignal() != type.getThreshold()).
	 */
	boolean isActiveFromRedstoneSignal();
	
}
