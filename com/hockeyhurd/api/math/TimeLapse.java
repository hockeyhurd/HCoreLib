package com.hockeyhurd.api.math;

/**
 * Time Lapse class for calculating the amount of time since class object initialization.
 * 
 * @author hockeyhurd
 * @see <a href = "https://github.com/hockeyhurd/HCoreLib/blob/master/com/hockeyhurd/api/math/TimeLapse.java">https://github.com/hockeyhurd/HCoreLib/blob/master/com/hockeyhurd/api/math/TimeLapse.java</a>
 */
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
	 * 
	 * @return effective time in ms.
	 */
	public double getEffectiveTimeSince() {
		long currentTime = System.nanoTime();
		double effTime = (currentTime - startTime) / ns;
		return effTime;
	}

	/**
	 * Gives the effective from point of init.
	 *
	 * @return effective time in ns.
	 */
	public long getEffectiveTimeSinceNS() {
		return System.nanoTime() - startTime;
	}

}
