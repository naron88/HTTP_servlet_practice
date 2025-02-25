package com.practice.servletpractice.frontcontroller.v3.controller;

import com.practice.servletpractice.domain.Member;
import com.practice.servletpractice.frontcontroller.ModelView;
import com.practice.servletpractice.frontcontroller.v3.ControllerV3;
import com.practice.servletpractice.repository.MemberRepository;

import java.util.List;
import java.util.Map;

public class MemberListControllerV3 implements ControllerV3 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public ModelView process(Map<String, String> paramMap) {
        List<Member> members = memberRepository.findAll();

        ModelView mv = new ModelView("members");
        mv.getModel().put("members", members);

        return mv;
    }
}
