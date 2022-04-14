package com.sony.mts.exception;

/**
 * 未登录异常
 * 
 * @author 黄龙
 */
public class NoLoginException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NoLoginException() {

	}

	public NoLoginException(String message) {
		super(message);
	}

}
