package mataedu.jpastudy.api.controller;

import lombok.RequiredArgsConstructor;
import mataedu.jpastudy.api.service.MemberService;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
}
