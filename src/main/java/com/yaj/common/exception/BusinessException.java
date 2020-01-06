package com.yaj.common.exception;


import com.yaj.core.exception.BaseBusinessException;


public class BusinessException extends BaseBusinessException {


	public BusinessException(Throwable throwable, BusinessExceptionErrorEnum e) {
		this.errorMessage = e.getMessage();
		this.errorCode = e.getCode();
	}

	public BusinessException(Throwable throwable, BusinessExceptionErrorEnum e, String errorMessage) {
		this.errorMessage = e.getMessage();
		this.errorCode = e.getCode();
		this.extraMessage = errorMessage;
	}

	public BusinessException(BusinessExceptionErrorEnum e) {
		this.errorMessage = e.getMessage();
		this.errorCode = e.getCode();
	}

	public BusinessException(BusinessExceptionErrorEnum e, String errorMessage) {
		this.errorMessage = e.getMessage();
		this.errorCode = e.getCode();
		this.extraMessage = errorMessage;
	}

	public BusinessException(BusinessExceptionErrorEnum e, Object extraMessage) {
		this.errorMessage = e.getMessage();
		this.extraMessage = extraMessage;
		this.errorCode = e.getCode();
	}

}
