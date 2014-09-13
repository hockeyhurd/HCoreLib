package com.hockeyhurd.math;

public class TimeLapse {

	private long startTime;
	private static final double ns = 1e6;
	
	/**
	 * Inits start of time tracking.
	 */
	public TimeLapse() {
		startTime = System.nanoTime();
	}
	
	/**
	 * Gives effective time from point of init.
	 * @return effective time in ms. 
	 */
	public double getEffectiveTimeSince() {
		long currentTime = System.nanoTime();
		double effTime = (currentTime - startTime) / ns;
		return Math.ceil(effTime);
	}

}
