package com.sony.mts.exception;

/**
 * 自定义异常
 * 
 * @author 黄龙
 */
public class CustomException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CustomException() {
		super();
	}

	public CustomException(String message) {
		super(message);
	}

}
