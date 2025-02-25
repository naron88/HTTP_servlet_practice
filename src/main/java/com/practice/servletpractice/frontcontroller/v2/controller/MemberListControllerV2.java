package com.practice.servletpractice.frontcontroller.v2.controller;

import com.practice.servletpractice.domain.Member;
import com.practice.servletpractice.frontcontroller.MyView;
import com.practice.servletpractice.frontcontroller.v2.ControllerV2;
import com.practice.servletpractice.repository.MemberRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public class MemberListControllerV2 implements ControllerV2 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public MyView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Member> members = memberRepository.findAll();
        request.setAttribute("members", members);

        return new MyView("/WEB-INF/views/members.jsp");
    }
}
