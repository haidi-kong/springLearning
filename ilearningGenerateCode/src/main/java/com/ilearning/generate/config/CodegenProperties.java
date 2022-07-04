package com.ilearning.generate.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.BufferedReader;
import java.io.InputStream;
import java.util.Collection;
import java.util.Properties;


public class CodegenProperties {

  private final static Properties properties;

   static {
       properties = new Properties();
      try {
          // 使用ClassLoader加载properties配置文件生成对应的输入流
          InputStream in = CodegenProperties.class.getClassLoader().getResourceAsStream("generator.properties");
          // 使用properties对象加载输入流
          properties.load(in);
      } catch (Exception e) {
          System.out.println(e.getMessage());
      }
  }

  public static String getValue(String key) {
      return properties.getProperty(key);
  }


}
