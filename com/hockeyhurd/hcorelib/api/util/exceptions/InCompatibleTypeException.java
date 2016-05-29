package com.hockeyhurd.hcorelib.api.util.exceptions;

/**
 * Exception for in compatible data types.
 *
 * @author hockeyhurd
 * @version 5/29/2016.
 */
public final class InCompatibleTypeException extends RuntimeException {

	public InCompatibleTypeException(String message) {
		super(message);
	}

}
