package com.sj.board.controller;

import com.sj.board.dto.MemberDto;
import com.sj.board.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/rest/api/v1")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    /**
     * 로그인
     * @param user 로그인 유저 정보
     * @param session 세션 정보
     * @return 성공 시 200 user정보 / 실패 시 403 fail message
     */
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody MemberDto user, HttpSession session) {

        MemberDto member = memberService.finById(user);
        if (member == null) {
            return ResponseEntity.status(403).body("아이디 또는 비밀번호가 일치하지 않습니다.");
        } else {
            session.setAttribute(member.getEmail(), member);
            return ResponseEntity.ok().body(member);
        }
    }

    /**
     * 로그아웃
     * @param user 유저정보 email
     * @param session 세션 정보
     * @return 성공 시 status 200 / ok message
     */
    @PostMapping("/logout")
    public ResponseEntity logout(@RequestBody MemberDto user, HttpSession session) {

        if(session.getAttribute(user.getEmail()) != null) {
            session.invalidate();
            return ResponseEntity.ok().body("success");

        } else {
            return ResponseEntity.status(500).body("fail");
        }
    }
}
