package com.practice.servletpractice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

// 서블릿을 직접 등록해서 사용
@ServletComponentScan
@SpringBootApplication
public class ServletPracticeApplication {

  public static void main(String[] args) {
    SpringApplication.run(ServletPracticeApplication.class, args);
  }

}
