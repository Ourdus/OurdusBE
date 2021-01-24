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

        Member m=new Member();
        m.setPassword(user_password);
        m.setUser_id(user_id);
        m.setName(name);

        if(memberService.validateDuplicateMember(user_id)!="hello"){
            return "Join_failed";
        }
        memberService.join(m);
        return "Join_Success";
    }
}
