package com.xpresspayments.ZenithBank.model.response;

import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author Abdussamad
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class SuccessResponse extends ZenithBankApiResponse {

  private String status;
  private Map<String, Object> data;
}
