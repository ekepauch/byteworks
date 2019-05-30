package com.xpresspayments.ZenithBank.model.response;



import com.xpresspayments.ZenithBank.model.constant.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author abdussamad
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ErrorResponse extends ZenithBankApiResponse {

  private ErrorCode error;
  private String message;
}

