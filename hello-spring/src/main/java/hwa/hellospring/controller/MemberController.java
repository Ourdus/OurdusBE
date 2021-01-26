package hwa.hellospring.controller;

import hwa.hellospring.domain.Member;
import hwa.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value="api",method = RequestMethod.POST)
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService)
    {
        this.memberService=memberService;
    }

    @ResponseBody
    @PostMapping("/join")
    public String join(@RequestBody Member member){

        String user_id=member.getUser_id();
        String user_password=member.getPassword();
        String name=member.getName();
        String email=member.getUser_email();
        String user_tel=member.getUser_tel();
        int point=member.getUser_point();

        Member m=new Member();
        m.setPassword(user_password);
        m.setUser_id(user_id);
        m.setName(name);
        m.setUser_email(email);
        m.setUser_point(point);
        m.setUser_tel(user_tel);

        if(memberService.validateDuplicateMember(user_id)==false){
            return "Join_failed";
        }
        memberService.join(m);
        return "Join_Success";
    }

    @ResponseBody
    @PostMapping("/login")
    public String login(@RequestBody Member member){

        String user_id=member.getUser_id();
        String user_password=member.getPassword();

        Member m=new Member();
        m.setPassword(user_password);
        m.setUser_id(user_id);

        if(memberService.login(user_id,user_password)==true){
            return "login_fail";
        }
        return "login_success";
    }

    @ResponseBody
    @PostMapping("/user/info/{user_id}")
    public String userInfo(@RequestBody Member member){

        String user_id=member.getUser_id();
        Member m=new Member();
        m.setUser_id(user_id);

        if(memberService.findInfo(user_id)==true){
            return "info_fail";
        }
        return "info_success";
    }

    @ResponseBody
    @PostMapping("/user/{user_id}")
    public String userDelete(@RequestBody Member member){

        String user_id=member.getUser_id();
        String user_pw=member.getPassword();
        Member m=new Member();
        m.setUser_id(user_id);
        m.setPassword(user_pw);

        if(memberService.userDelete(user_id,user_pw)==false){
            return "user_delete_fail";
        }
        return "user_delete_success";
    }

}
