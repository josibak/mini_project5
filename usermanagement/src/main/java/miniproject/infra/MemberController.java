package miniproject.infra;

import lombok.RequiredArgsConstructor;
import miniproject.domain.Member;
import miniproject.domain.MemberRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//<<< Clean Arch / Inbound Adaptor

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberRepository memberRepository;


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
