package com.hockeyhurd.hcorelib.api.util.datatypes;

/**
 * Compact storage of boolean values through 32-bit integers.
 *
 * @author hockeyhurd
 * @version 7/24/2016.
 */
public final class Bool32 extends Bool {

	private int value;

	public Bool32() {
		this.value = 0;
	}

	public Bool32(int value) {
		this.value = value;
	}

	public Bool32(boolean flag) {
		this.value = flag ? 0xffffffff : 0;
	}

	@Override
	protected Integer getOffsetValue(int index) {
		return 1 << index;
	}

	@Override
	protected int getBitWidth() {
		return 0x20;
	}

	@Override
	public Bool32 set(boolean flag) {
		this.value = flag ? 0xffffffff : 0;

		return this;
	}

	@Override
	public Bool32 set(boolean flag, int index) {
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
	public Bool32 and(Bool bool) {
		if (bool instanceof Bool32)
			value &= ((Bool32) bool).value;

		return this;
	}

	@Override
	public Bool32 or(Bool bool) {
		if (bool instanceof Bool32)
			value |= ((Bool32) bool).value;

		return this;
	}

	@Override
	public Bool32 xor(Bool bool) {
		if (bool instanceof Bool32)
			value ^= ((Bool32) bool).value;

		return this;
	}

	@Override
	public Bool32 not() {
		value = ~value;

		return this;
	}

	@Override
	public String toString() {
		return String.format("%32s", Integer.toBinaryString(value)).replace(' ', '0');
	}

	@Override
	public int hashCode() {
		return value;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null || getClass() != obj.getClass()) return false;

		return value == ((Bool32) obj).value;

	}

}
