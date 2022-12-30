package com.its.memberboard.controller;

import com.its.memberboard.dto.MemberDTO;
import com.its.memberboard.service.MemberService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/login")
    public String loginPage(@RequestParam(value = "redirectURL", defaultValue = "/member/main") String redirectURL, Model model) {
        model.addAttribute("redirect", redirectURL);
        return "memberPages/memberLogin";
    }

    @GetMapping("/main")
    public String mainPage() {
        return "memberPages/memberMain";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute MemberDTO memberDTO) {
        memberService.save(memberDTO);
        return "memberPages/memberLogin";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute MemberDTO memberDTO,  HttpSession session, @RequestParam(value = "redirectURL", defaultValue = "/member/main") String redirectURL) {
       MemberDTO loginResult = memberService.login(memberDTO);
       if (loginResult != null) {
           session.setAttribute("loginEmail", memberDTO.getMemberEmail());
           return "redirect:" + redirectURL;
       } else {
           return "memberPages/memberLogin";
       }
    }

    @GetMapping("/admin")
    public String admin() {
        return "memberPages/admin";
    }

    @GetMapping("/")
    public String findAll(Model model) {
        List<MemberDTO> memberDTOList = memberService.findAll();
        model.addAttribute("memberList", memberDTOList);
        return "memberPages/memberList";
    }

    @GetMapping("/{id}")
    private String findById(@PathVariable Long id, Model model) {
        MemberDTO memberDTO = memberService.findById(id);
        model.addAttribute("member", memberDTO);
        return "memberPages/memberDetail";
    }

    @PostMapping("/dup-check")
    public @ResponseBody String emailDuplicateCheck(@RequestParam("inputEmail") String memberEmail) {
       String checkResult = memberService.emailDupCheck(memberEmail);
       return checkResult;
    }

    @GetMapping("/update")
    public String updateForm(Model model, HttpSession session) {
        String loginEmail = (String) session.getAttribute("loginEmail");
        MemberDTO memberDTO = memberService.findByMemberEmail(loginEmail);
        model.addAttribute("member",memberDTO);
        return "memberPages/memberUpdate";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute MemberDTO memberDTO) {
        memberService.update(memberDTO);
        return "memberPages/memberMain";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        memberService.delete(id);
        return "redirect:/member/";
    }

    @GetMapping("/myPage")
    public String myPage() {
        return "memberPages/myPage";
    }
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "memberPages/memberLogin";
    }
}
