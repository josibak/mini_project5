package miniproject.infra;

import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import miniproject.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//<<< Clean Arch / Inbound Adaptor

@RestController
// @RequestMapping(value="/members")
@Transactional
public class MemberController {

    @Autowired
    MemberRepository memberRepository;

    @RequestMapping(
        value = "/members/openbookpoint",
        method = RequestMethod.POST,
        produces = "application/json;charset=UTF-8"
    )
    public Member openBookPoint(
        HttpServletRequest request,
        HttpServletResponse response,
        @RequestBody OpenBookPointCommand openBookPointCommand
    ) throws Exception {
        System.out.println("##### /member/openBookPoint  called #####");
        Member member = new Member();
        member.openBookPoint(openBookPointCommand);
        memberRepository.save(member);
        return member;
    }

    @RequestMapping(
        value = "/members/subscribtionrequest",
        method = RequestMethod.POST,
        produces = "application/json;charset=UTF-8"
    )
    public Member subscribtionRequest(
        HttpServletRequest request,
        HttpServletResponse response,
        @RequestBody SubscribtionRequestCommand subscribtionRequestCommand
    ) throws Exception {
        System.out.println("##### /member/subscribtionRequest  called #####");
        Member member = new Member();
        member.subscribtionRequest(subscribtionRequestCommand);
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
        value = "/members/{id}/authkt",
        method = RequestMethod.PUT,
        produces = "application/json;charset=UTF-8"
    )
    public Member authKt(
        @PathVariable(value = "id") Long id,
        @RequestBody AuthKtCommand authKtCommand,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {
        System.out.println("##### /member/authKt  called #####");
        Optional<Member> optionalMember = memberRepository.findById(id);

        optionalMember.orElseThrow(() -> new Exception("No Entity Found"));
        Member member = optionalMember.get();
        member.authKt(authKtCommand);

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
        @RequestBody BookOpenCommand bookOpenCommand
    ) throws Exception {
        System.out.println("##### /member/bookOpen  called #####");
        Member member = new Member();
        member.bookOpen(bookOpenCommand);
        memberRepository.save(member);
        return member;
    }
}
//>>> Clean Arch / Inbound Adaptor
