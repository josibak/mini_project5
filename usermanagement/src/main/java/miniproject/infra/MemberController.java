package miniproject.infra;

import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import miniproject.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

//<<< Clean Arch / Inbound Adaptor

@RestController
// @RequestMapping(value="/members")
@Transactional
public class MemberController {

    @Autowired
    MemberRepository memberRepository;


    @RequestMapping(
        value = "/members/subscribtionrequest",
        method = RequestMethod.POST,
        produces = "application/json;charset=UTF-8"
    )
    public Member subscribtionRequest(
        HttpServletRequest request,
        HttpServletResponse response,
        @RequestBody SubscribtionRequestCommand command
    ) throws Exception {
        System.out.println("##### /member/subscribtionRequest  called #####");

        Optional<Member> optionalMember = memberRepository.findById(command.getUserId());
        if (optionalMember.isEmpty()) {
        throw new Exception("Member not found for userId: " + command.getUserId());
        }
        Member member = optionalMember.get();
        member.subscribtionRequest(command);

        memberRepository.save(member);
        return member;
    }


    @RequestMapping(
        value = "/members/registermember",
        method = RequestMethod.POST,
        produces = "application/json;charset=UTF-8"
    )
    public Member registerMember(
        HttpServletRequest request,
        HttpServletResponse response,
        @RequestBody RegisterMemberCommand registerMemberCommand
    ) throws Exception {
        System.out.println("##### /member/registerMember  called #####");
        Member member = new Member();
        member.registerMember(registerMemberCommand);
        memberRepository.save(member);
        return member;
    }


    @RequestMapping(
        value = "/members/bookopen",
        method = RequestMethod.POST,
        produces = "application/json;charset=UTF-8"
    )
    public Member bookOpen(
        HttpServletRequest request,
        HttpServletResponse response,
        @RequestBody BookOpenCommand command
    ) throws Exception {
        System.out.println("##### /member/bookOpen called #####");

        Optional<Member> optionalMember = memberRepository.findById(command.getUserId());

        if (optionalMember.isEmpty()) {
            throw new Exception("Member not found for userId: " + command.getUserId());
        }

        Member member = optionalMember.get();
        member.bookOpen(command);

        memberRepository.save(member);
        return member;
    }
}
//>>> Clean Arch / Inbound Adaptor