package com.xpresspayments.ZenithBank.validator;

import com.xpresspayments.ZenithBank.exception.BadRequestException;
import java.util.List;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

/**
 * @author Abdussamad
 */
public class InputValidator {

  public static void validate(BindingResult bindingResult) throws BadRequestException {
    if (!bindingResult.hasErrors()) {
      return;
    }
    List<FieldError> fieldErrors = bindingResult.getFieldErrors();
    StringBuilder messageBuilder = new StringBuilder();
    for (FieldError fieldError : fieldErrors) {
      messageBuilder.append(fieldError.getField());
      messageBuilder.append(" ");
      messageBuilder.append(fieldError.getDefaultMessage());
      messageBuilder.append("; ");
    }
    throw new BadRequestException(messageBuilder.toString().trim());
  }

}
