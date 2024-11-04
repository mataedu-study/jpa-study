package mataedu.jpastudy.api.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mataedu.jpastudy.domain.member.Member;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class MemberCreateRequest {

    private String name;

    private String email;

    public Member toEntity() {
        return Member.of(name, email);
    }

}
