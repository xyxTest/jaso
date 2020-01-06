package com.yaj.core.mvc;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import com.yaj.core.exception.BaseBusinessException;
import com.yaj.core.exception.BaseExceptionErrorEnum;

import java.util.List;

public class BaseController {

	protected void checkBindingResult(BindingResult result) {
		if (result.hasErrors()) {
			List<ObjectError> list = result.getAllErrors();
			for (int i = 0; i < list.size(); i++) {
				throw new BaseBusinessException(BaseExceptionErrorEnum.PARAMETER_VERIFICATION_ERROR,list.get(i).getDefaultMessage());
			}
		}
	}

}