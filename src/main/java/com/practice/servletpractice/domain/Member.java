package com.practice.servletpractice.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Member {

  private Long id;
  private String username;
  private int age;

  public Member(String username, int age) {
    this.username = username;
    this.age = age;
  }
}
