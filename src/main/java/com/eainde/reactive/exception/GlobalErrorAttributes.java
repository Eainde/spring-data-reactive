package com.eainde.reactive.exception;

import java.util.LinkedHashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.reactive.error.DefaultErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;

@Component
public class GlobalErrorAttributes extends DefaultErrorAttributes {
  Logger LOGGER = LoggerFactory.getLogger(GlobalErrorAttributes.class);

  @Override
  public Map<String, Object> getErrorAttributes(
      ServerRequest request, ErrorAttributeOptions options) {
    Map<String, Object> map = super.getErrorAttributes(request, options);
    map.putAll(assembleError(request));
    return map;
  }

  private Map<String, Object> assembleError(ServerRequest request) {
    Map<String, Object> errorAttributes = new LinkedHashMap<>();
    Throwable error = getError(request);
    errorAttributes.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
    errorAttributes.put("message", error.getMessage());
    LOGGER.error("Error", error);
    return errorAttributes;
  }
}
