package com.hockeyhurd.api.math;

/**
 * Time Lapse class for calculating the amount of time since class object initialization.
 * 
 * @author hockeyhurd
 * @see <a href = "https://github.com/hockeyhurd/HCoreLib/blob/master/com/hockeyhurd/api/math/TimeLapse.java">https://github.com/hockeyhurd/HCoreLib/blob/master/com/hockeyhurd/api/math/TimeLapse.java</a>
 */
public final class TimeLapse {

	private long startTime;
	private static final double ns = 1e6;

	/**
	 * Inits start of time tracking.
	 */
	public TimeLapse() {
		startTime = System.nanoTime();
	}

	/**
	 * Method used to simply reset the start time to current time.
	 */
	public void resetStartTime() {
		this.startTime = System.nanoTime();
	}

	/**
	 * Use this setter method with caution and if you are certain of what you are doing.
	 *
	 * @param startTime start time to set to.
	 */
	public void setStartTime(long startTime) {
		if (startTime <= System.nanoTime()) this.startTime = startTime;
	}

	/**
	 * @return startTime.
	 */
	public long getStartTime() {
		return startTime;
	}

	/**
	 * Gives effective time from point of init.
	 * 
	 * @return effective time in ms.
	 */
	public double getEffectiveTimeSince() {
		return (System.nanoTime() - startTime) / ns;
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
