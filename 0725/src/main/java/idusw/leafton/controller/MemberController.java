package idusw.leafton.controller;

import idusw.leafton.model.DTO.MemberDTO;
import idusw.leafton.model.entity.Member;
import idusw.leafton.model.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping(value = "/member")
@Controller
public class MemberController {
    MemberService memberService;
    @GetMapping(value = "/login")
    public String goLogin() { return "/member/login"; }

    @PostMapping(value="/login")
    public ModelAndView login(@ModelAttribute MemberDTO memberDTO, HttpServletRequest request){
        ModelAndView mav = new ModelAndView();
        MemberDTO memberResult = memberService.loginCheck(memberDTO);

        if(memberResult != null)
        {
            HttpSession session = request.getSession();
            session.setAttribute("memberDTO", memberResult);
        }
        mav.setViewName("/member/result");
        return mav;
    }

    @GetMapping(value = "/register")
    public String goRegister() { return "/member/register"; }

    @PostMapping(value="/register")
    public ModelAndView register(@ModelAttribute Member member, HttpServletRequest request){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/main/index");
        return mav;
    }
}
