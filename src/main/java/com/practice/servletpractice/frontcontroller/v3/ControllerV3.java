package com.practice.servletpractice.frontcontroller.v3;

import com.practice.servletpractice.frontcontroller.ModelView;

import java.util.Map;

public interface ControllerV3 {
    ModelView process(Map<String, String> paramMap);
}
