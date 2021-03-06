package com.hockeyhurd.hcorelib.api.util.datatypes;

/**
 * Compact storage of boolean values through 16-bit integers.
 *
 * @author hockeyhurd
 * @version 7/25/2016.
 */
public final class Bool16 extends Bool {

	private short value;

	public Bool16() {
		this.value = 0;
	}

	public Bool16(short value) {
		this.value = value;
	}

	public Bool16(boolean flag) {
		this.value = (short) (flag ? 0xffff : 0);
	}

	@Override
	protected Short getOffsetValue(int index) {
		return (short) (1 << index);
	}

	@Override
	protected int getBitWidth() {
		return 0x10;
	}

	@Override
	public Bool16 set(boolean flag) {
		this.value = (short) (flag ? 0xffff : 0);

		return this;
	}

	@Override
	public Bool16 set(boolean flag, int index) {
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
	public Bool16 and(Bool bool) {
		if (bool instanceof Bool16)
			value &= ((Bool16) bool).value;

		return this;
	}

	@Override
	public Bool16 or(Bool bool) {
		if (bool instanceof Bool16)
			value |= ((Bool16) bool).value;

		return this;
	}

	@Override
	public Bool16 xor(Bool bool) {
		if (bool instanceof Bool16)
			value ^= ((Bool16) bool).value;

		return this;
	}

	@Override
	public Bool16 not() {
		value = (short) ~value;

		return this;
	}

	@Override
	public String toString() {
		return String.format("%16s", Integer.toBinaryString(value)).replace(' ', '0');
	}
	
	@Override
	public int hashCode() {
		return ((Short) value).hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null || getClass() != obj.getClass()) return false;

		return value == ((Bool16) obj).value;

	}

}
