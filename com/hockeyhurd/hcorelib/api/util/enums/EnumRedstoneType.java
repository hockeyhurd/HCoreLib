package com.hockeyhurd.hcorelib.api.util.enums;

/**
 * Simple enumeration for controlling redstone signal types.
 * 
 * @author hockeyhurd
 * @version Jun 4, 2015
 */
public enum EnumRedstoneType {

	DISABLED(-1), LOW(0x0), HIGH(0xf), CUSTOM(0xf);
	
	public static final EnumRedstoneType[] types = new EnumRedstoneType[] {
		DISABLED, LOW, HIGH, CUSTOM
	};
	
	private int strength;
	
	/**
	 * @param strength max strength or threshold this type can handle.
	 */
	private EnumRedstoneType(int strength) {
		this.strength = strength;
	}
	
	/**
	 * Sets the custom redstone type to a configured numerical value.
	 * 
	 * @param type type to change as reference such that it must be of type 'CUSTOM'.
	 * @param strength strength to set value of.
	 */
	public void setCustom(EnumRedstoneType type, int strength) {
		if (type == CUSTOM) type.strength = strength;
	}
	
	/**
	 * Gets strength (threshold) for which this type handles.
	 * 
	 * @return strength (threshold).
	 */
	public int getThreshold() {
		return strength;
	}
	
	/**
	 * @param receive amount of redstone signal currently receiving.
	 * @return true if under threshold, else returns false.
	 */
	public boolean isActive(final int receive) {
		return this == DISABLED || (receive >= 0 && receive < getThreshold());
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString() {
		return "TYPE: " + name() + ", strength: " + getThreshold();
	}
	
}
