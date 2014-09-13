package com.hockeyhurd.util;

public class TimerHelper {

	public final int resetVal;
	private int tick;
	private int buffer;
	public boolean use = false;
	
	public TimerHelper(int startVal, int buffer) {
		if (startVal > 0 && buffer > 0) {
			this.resetVal = this.tick = startVal;
			this.buffer = buffer;
		}
		else {
			LogHelper.warn("Constructor value is less than 0! Defaulting timer to 20 ticks or 1 second with no buffer!");
			this.resetVal = this.tick = 20;
			this.buffer = 0;
		}
	}
	
	public TimerHelper(int startVal) {
		this(startVal, 0);
	}
	
	public TimerHelper() {
		this(20, 0);
	}
	
	public void setUse(boolean use) {
		this.use = use;
	}
	
	public void trigger() {
		setUse(true);
	}
	
	public boolean getUse() {
		return this.use;
	}
	
	public int getCurrentTick() {
		return this.tick;
	}
	
	private boolean canExcuse() {
		return this.buffer > 0;
	}
	
	public boolean excuser() {
		return canExcuse() ? (this.tick + this.buffer > this.resetVal) : false;
	}
	
	public void update() {
		if (!getUse()) return;
		if (tick > 0 && getUse()) tick--;
		if (tick == 0 && getUse()) {
			tick = resetVal;
			setUse(false);
		}
	}
	
}
