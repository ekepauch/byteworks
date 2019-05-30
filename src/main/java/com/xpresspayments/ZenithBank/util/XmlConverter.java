package com.xpresspayments.ZenithBank.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.xpresspayments.ZenithBank.exception.ProcessingException;


import java.io.IOException;

/**
 * @author Abdussamad
 */
public class XmlConverter {

  private static XmlMapper xmlMapper;

  public static String objectToXml(Object object) {
    if (xmlMapper == null) {
      xmlMapper = new XmlMapper();
      xmlMapper.registerModule(new Jdk8Module());
      xmlMapper.setPropertyNamingStrategy(PropertyNamingStrategy.UPPER_CAMEL_CASE);
    }
    try {
      return xmlMapper.writeValueAsString(object);
    } catch (JsonProcessingException e) {
      throw new ProcessingException(e);
    }
  }

  public static <T> T xmlToObject(String xmlString, Class<T> valueType) {
    if (xmlMapper == null) {
      xmlMapper = new XmlMapper();
      xmlMapper.registerModule(new Jdk8Module());
      xmlMapper.setPropertyNamingStrategy(PropertyNamingStrategy.UPPER_CAMEL_CASE);
    }
    try {
      return xmlMapper.readValue(xmlString, valueType);
    } catch (IOException e) {
      throw new ProcessingException(e);
    }
  }
}
