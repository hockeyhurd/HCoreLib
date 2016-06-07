package com.hockeyhurd.hcorelib.api.handler.config;

import com.hockeyhurd.hcorelib.api.util.exceptions.InCompatibleTypeException;
import io.netty.buffer.ByteBuf;

/**
 * Class used for containing relevant information to
 * transfer from server to client synching.
 *
 * @author hockeyhurd
 * @version 5/29/2016.
 */
public final class ConfigData<T> {

	public final boolean clientOnly;
	public final String field;
	private T[] data;
	private DataType dataType;

	/**
	 * @param field Field name identifier.
	 * @param data Data value(s).
     */
	public ConfigData(String field, T... data) {
		this(false, field, data);
	}

	/**
	 * @param clientOnly boolean flag for whether data can only be sent server -> client.
	 * @param field Field name identifier.
	 * @param data Data value(s).
     */
	public ConfigData(boolean clientOnly, String field, T... data) {
		if (data == null || data.length == 0)
			throw new IllegalArgumentException("Illegal object data was injected. Was this an error???");

		this.dataType = DataType.getDataType(data[0]);
		this.clientOnly = clientOnly;
		this.field = field;
		this.data = data.clone();
	}

	/**
	 * Function to validate data to be sent.
	 *
	 * @return boolean result.
     */
	public boolean validate() {
		return field != null && data != null && data.length > 0;
	}

	/**
	 * Reads buffer data.
	 *
	 * @param buf ByteBuf.
     */
	@SuppressWarnings("unchecked")
	public void readBuf(ByteBuf buf) {
		final int arrLen = buf.readInt();
		dataType = DataType.values()[buf.readInt()];
		data = (T[]) new Object[arrLen];

		for (int i = 0; i < arrLen; i++) {
			data[i] = DataType.readBuf(buf, dataType);
		}
	}

	/**
	 * Writes buffer data.
	 *
	 * @param buf ByteBuf.
     */
	public void writeBuf(ByteBuf buf) {
		buf.writeInt(data.length);
		buf.writeInt(dataType.ordinal());

		for (T current : data) {
			DataType.writeBuf(buf, dataType, current);
		}
	}

	/**
	 * Private enumeration for handling primitive data.
	 *
	 * @author hockeyhurd
	 * @version 6/7/16
	 */
	private enum DataType {
		BOOLEAN, BYTE, SHORT, INT, LONG, CHAR, STRING;

		static DataType getDataType(Object sample) {
			if (sample == null) throw new NullPointerException("Sample objected was NULL!");
			else if (sample instanceof Boolean) return BOOLEAN;
			else if (sample instanceof Byte) return BYTE;
			else if (sample instanceof Short) return SHORT;
			else if (sample instanceof Integer) return INT;
			else if (sample instanceof Long) return LONG;
			else if (sample instanceof Character) return CHAR;
			else if (sample instanceof String) return STRING;
			else throw new InCompatibleTypeException("Invalid type used for interpretation!");
		}

		/**
		 * Reads and returns data from ByteBuf.
		 *
		 * @param buf ByteBuf.
		 * @param dataType The data's datatype.
         * @param <T> Return type.
         * @return read data.
         */
		@SuppressWarnings("unchecked")
		static <T> T readBuf(ByteBuf buf, DataType dataType) {
			if (dataType == BOOLEAN) return (T) (Boolean) buf.readBoolean();
			else if (dataType == BYTE) return (T) (Byte) buf.readByte();
			else if (dataType == SHORT) return (T) (Short) buf.readShort();
			else if (dataType == INT) return (T) (Integer) buf.readInt();
			else if (dataType == LONG) return (T) (Long) buf.readLong();
			else if (dataType == CHAR) return (T) (Character) buf.readChar();
			else if (dataType == STRING) {
				final int len = buf.readInt();
				final char[] arr = new char[len];

				for (int i = 0; i < len; i++) {
					arr[i] = buf.readChar();
				}

				return (T) new String(arr);
			}

			else throw new InCompatibleTypeException("Invalid type used for interpretation!");
		}

		/**
		 * Writes buffer data.
		 *
		 * @param buf ByteBuf.
		 * @param dataType Data's datatype to write.
         * @param data Data value to write.
         */
		static void writeBuf(ByteBuf buf, DataType dataType, Object data) {
			if (dataType == BOOLEAN) buf.writeBoolean((Boolean) data);
			else if (dataType == BYTE) buf.writeByte((Byte) data);
			else if (dataType == SHORT) buf.writeShort((Short) data);
			else if (dataType == INT) buf.writeInt((Integer) data);
			else if (dataType == LONG) buf.writeLong((Long) data);
			else if (dataType == CHAR) buf.writeChar((Character) data);
			else if (dataType == STRING) {
				final String string = (String) data;
				final int len = string.length();
				buf.writeInt(len);

				for (char c : string.toCharArray())
					buf.writeChar(c);
			}

			else throw new InCompatibleTypeException("Invalid type used for interpretation!");
		}
	}

}
