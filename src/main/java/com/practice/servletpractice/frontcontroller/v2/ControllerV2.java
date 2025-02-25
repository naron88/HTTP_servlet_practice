package com.practice.servletpractice.frontcontroller.v2;

import com.practice.servletpractice.frontcontroller.MyView;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface ControllerV2 {
    MyView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException; // 컨트롤러가 뷰를 반환한다.
}
