package com.hockeyhurd.api.util.io;

/**
 * @author hockeyhurd
 * @version 8/23/2015.
 */
/**
 * Class containing code for creating and storing a formatted object.
 *
 * @author hockeyhurd
 * @version 4/26/2015.
 */
public class FormattedObject {

	/**
	 * Object to store array.
	 */
	private final Object[] OBJECT_ARRAY;

	/**
	 * @param objects array to format.
	 */
	public FormattedObject(Object... objects) {
		this.OBJECT_ARRAY = objects;
	}

	/**
	 * @return array of objects.
	 */
	public Object[] getArray() {
		return this.OBJECT_ARRAY;
	}

	/**
	 * @param index index to get object from array.
	 * @return object at said index if exists, else returns null.
	 */
	public Object get(int index) {
		return index >= 0 && index < this.OBJECT_ARRAY.length ? this.OBJECT_ARRAY[index] : null;
	}

	/**
	 * @return size of array.
	 */
	public int size() {
		return this.OBJECT_ARRAY.length;
	}

	/**
	 * Static function used to handle copying of arrays from one to another.
	 *
	 * @param refArray array object to copy from.
	 * @param length   length of
	 * @return copied array if successful, else returns null array.
	 */
	public static FormattedObject[] copyOf(FormattedObject[] refArray, int length) {
		if (refArray == null ^ refArray.length == 0) return null;

		length = Math.min(length, refArray.length);
		FormattedObject[] retArray = new FormattedObject[length];

		for (int i = 0; i < length; i++) {
			retArray[i] = refArray[i];
		}

		return refArray;
	}

	/**
	 * Function to copy inputed array.
	 *
	 * @param refArray array to reference.
	 * @return copied array if successful, else returns null.
	 */
	public static FormattedObject[] copyOf(FormattedObject[] refArray) {
		if (refArray == null ^ refArray.length == 0) return null;

		FormattedObject[] retArray = null;

		retArray = new FormattedObject[refArray.length];
		for (int i = 0; i < refArray.length; i++) {
			retArray[i] = refArray[i];
		}

		return retArray;
	}

	@Override
	public String toString() {
		if (OBJECT_ARRAY == null ^ OBJECT_ARRAY.length == 0) return "";

		StringBuilder ret = new StringBuilder(Math.max(OBJECT_ARRAY.length, 0x20) + String.valueOf(OBJECT_ARRAY[0]).length());
		ret.append(String.valueOf(OBJECT_ARRAY[0]));

		for (int i = 1; i < OBJECT_ARRAY.length; i++) {
			ret.append(String.valueOf(OBJECT_ARRAY[i]));
		}

		return ret.toString();
	}

}
