package com.xpresspayments.ZenithBank.util;

import com.rollbar.Rollbar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

/**
 * @author Abdussamad
 */
@Service
public class RollbarService {

  private static final String UNKOWN_FILE = "UnkownFile";
  private final Boolean isEnabled;
  private Rollbar rollbar;

  @Autowired
  public RollbarService(
      @Value("${rollbar.access_token}") String accessToken,
      @Value("${zenith.environment}") String environment,
      @Value("${rollbar.enabled}") Boolean isEnabled) {
    Assert.notNull(accessToken);
    Assert.notNull(environment);
    Assert.notNull(isEnabled);
    this.rollbar = new Rollbar(accessToken, environment);
    this.isEnabled = isEnabled;
  }

  public void sendError(Throwable throwable, String s) {
    if (shouldSendNotification()) {
      for (int i = 0; i < throwable.getStackTrace().length; i++) {
        StackTraceElement element = throwable.getStackTrace()[i];
        if (StringUtils.isEmpty(element.getFileName())) {
          StackTraceElement newElement = new StackTraceElement(
              element.getClassName(), element.getMethodName(), UNKOWN_FILE,
              element.getLineNumber());
          throwable.getStackTrace()[i] = newElement;
        }
      }
      rollbar.error(throwable, s);
    }
  }

  private boolean shouldSendNotification() {
    return isEnabled;
  }
}
