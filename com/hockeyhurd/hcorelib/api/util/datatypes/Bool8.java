package com.hockeyhurd.hcorelib.api.util.datatypes;

/**
 * Compact storage of boolean values through 8-bit integers.
 *
 * @author hockeyhurd
 * @version 7/25/2016.
 */
public final class Bool8 extends Bool {

	private byte value;

	public Bool8() {
		this.value = 0;
	}

	public Bool8(byte value) {
		this.value = value;
	}

	public Bool8(boolean flag) {
		this.value = (byte) (flag ? 0xff : 0);
	}

	@Override
	protected Byte getOffsetValue(int index) {
		return (byte) (1 << index);
	}

	@Override
	protected int getBitWidth() {
		return 0x8;
	}

	@Override
	public Bool8 set(boolean flag) {
		this.value = (byte) (flag ? 0xff : 0);

		return this;
	}

	@Override
	public Bool8 set(boolean flag, int index) {
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
	public Bool8 and(Bool bool) {
		if (bool instanceof Bool8)
			value &= ((Bool8) bool).value;

		return this;
	}

	@Override
	public Bool8 or(Bool bool) {
		if (bool instanceof Bool8)
			value |= ((Bool8) bool).value;

		return this;
	}

	@Override
	public Bool8 xor(Bool bool) {
		if (bool instanceof Bool8)
			value ^= ((Bool8) bool).value;

		return this;
	}

	@Override
	public Bool8 not() {
		value = (byte) ~value;

		return this;
	}

	@Override
	public String toString() {
		return String.format("%8s", Integer.toBinaryString(value)).replace(' ', '0');
	}

	@Override
	public int hashCode() {
		return ((Byte) value).hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null || getClass() != obj.getClass()) return false;

		return value == ((Bool8) obj).value;

	}

}
