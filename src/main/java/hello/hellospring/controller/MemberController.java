package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.stereotype.Controller;

/*
스프링 컨테이너가 스프링이 처음 뜰 때 스프링 컨테이너라는 통이 생성된다.
거기에 Controller 어노테이션이 있으면 MemberController 객체를 생성해 통에 넣어둔다. (스프링이 관리)
그래서 예전에 HelloController에서 작성했던 것처럼 기능들이 작동할 수 있었던 것.

MemberController 뿐만 아니라 다른 여러 Controller들이 MemberService를 갖다쓸수 있기 때문에
별 기능이 없는 이 MemberService를 매번 생성하는건 비효율적임. 공용으로 쓰는게 낫다.

스프링 컨테이너한테 등록을 하고 쓰면 된다.
 */
@Controller
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}
