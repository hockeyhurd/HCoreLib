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
	public void set(boolean flag) {
		this.value = (byte) (flag ? 0xff : 0);
	}

	@Override
	public void set(boolean flag, int index) {
		index %= 7;

		if (flag) value |= 1 << index;
		else value &= ~(1 << index);
	}

	@Override
	public boolean get(int index) {
		return value << (index % 7) == 1;
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
	public boolean[] get(int startIndex, int endIndex) {
		if (startIndex < 0) startIndex = 0;
		if (endIndex > 7) endIndex = 7;
		if (startIndex > endIndex) {
			startIndex ^= endIndex;
			endIndex ^= startIndex;
			startIndex ^= endIndex;
		}

		final boolean[] output = new boolean[endIndex - startIndex + 1];
		int counter = 0;

		for (int i = startIndex; i <= endIndex; i++)
			output[counter++] = get(i);

		return output;
	}

	@Override
	public boolean[] values() {
		final boolean[] output = new boolean[8];

		for (int i = 0; i < output.length; i++)
			output[i] = get(i);

		return output;
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
