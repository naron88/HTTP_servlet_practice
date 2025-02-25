package com.practice.servletpractice.frontcontroller.v3.controller;

import com.practice.servletpractice.domain.Member;
import com.practice.servletpractice.frontcontroller.ModelView;
import com.practice.servletpractice.frontcontroller.v3.ControllerV3;
import com.practice.servletpractice.repository.MemberRepository;

import java.util.Map;

public class MemberSaveControllerV3 implements ControllerV3 {
    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public ModelView process(Map<String, String> paramMap) {
        String username = paramMap.get("username"); // paramMap에서 필요한 요청 파라미터 조회
        int age = Integer.parseInt(paramMap.get("age"));

        Member member = new Member(username, age);
        memberRepository.save(member);

        ModelView mv = new ModelView("save-result");
        mv.getModel().put("member", member); // 모델에 뷰에서 필요한 객체를 담고 반환
        return mv;
    }
}
