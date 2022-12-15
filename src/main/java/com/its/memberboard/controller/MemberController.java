package com.its.memberboard.controller;

import com.its.memberboard.dto.MemberDTO;
import com.its.memberboard.service.MemberService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/save")
    public String saveForm() {
        return "memberPages/memberSave";
    }

    @PostMapping("/login")
    public String save(@ModelAttribute MemberDTO memberDTO) {
        memberService.save(memberDTO);
        return "memberPages/memberLogin";
    }

    @PostMapping("/main")
    public String login(@ModelAttribute MemberDTO memberDTO,  HttpSession session) {
       MemberDTO loginResult = memberService.login(memberDTO);
       if (loginResult != null) {
           session.setAttribute("loginEmail", memberDTO.getMemberEmail());
           return "memberPages/memberMain";
       } else {
           return "memberPages/memberLogin";
       }
    }

    @GetMapping("/admin")
    public String admin() {
        return "memberPages/admin";
    }


}
