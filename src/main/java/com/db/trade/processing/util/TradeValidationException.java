package com.db.trade.processing.util;

/**
 * Trade Specific Exceptions
 * @author  Vikalp
 * @version 1.0
 * @since   2023-09-26
 */

public class TradeValidationException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TradeValidationException(String message) {
		super(message);
	}
}
