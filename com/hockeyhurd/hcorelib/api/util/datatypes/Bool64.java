package com.hockeyhurd.hcorelib.api.util.datatypes;

/**
 * Compact storage of boolean values through 64-bit integers.
 *
 * @author hockeyhurd
 * @version 7/24/2016.
 */
public final class Bool64 extends Bool {

	private long value;

	public Bool64() {
		this.value = 0L;
	}

	public Bool64(long value) {
		this.value = value;
	}

	public Bool64(boolean flag) {
		this.value = flag ? 0xffffffffffffffffL : 0L;
	}

	@Override
	protected Long getOffsetValue(int index) {
		return (long) (1 << index);
	}

	@Override
	protected int getBitWidth() {
		return 0x40;
	}

	@Override
	public Bool64 set(boolean flag) {
		this.value = flag ? 0xffffffffffffffffL : 0L;

		return this;
	}

	@Override
	public Bool64 set(boolean flag, int index) {
		index %= getBitWidth() - 1;

		if (flag) value |= 1 << index;
		else value &= ~(1 << index);

		return this;
	}

	@Override
	public boolean get(int index) {
		index %= getBitWidth() - 1;
		return ((value >> index) & 1) != 0;
	}

	@Override
	public boolean[] get(int[] indicies) {
		if (indicies == null || indicies.length == 0) return new boolean[0];

		final boolean[] output = new boolean[indicies.length];

		for (int i = 0; i < output.length; i++)
			output[i] = get(indicies[i]);

		return output;
	}

	@Override
	public Bool64 and(Bool bool) {
		if (bool instanceof Bool64)
			value &= ((Bool64) bool).value;

		return this;
	}

	@Override
	public Bool64 or(Bool bool) {
		if (bool instanceof Bool64)
			value |= ((Bool64) bool).value;

		return this;
	}

	@Override
	public Bool64 xor(Bool bool) {
		if (bool instanceof Bool64)
			value ^= ((Bool64) bool).value;

		return this;
	}

	@Override
	public Bool64 not() {
		value = ~value;

		return this;
	}

	@Override
	public String toString() {
		return String.format("%64s", Long.toBinaryString(value)).replace(' ', '0');
	}

	@Override
	public int hashCode() {
		return ((Long) value).hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null || getClass() != obj.getClass()) return false;

		return value == ((Bool64) obj).value;

	}

}
