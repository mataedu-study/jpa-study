package mataedu.jpastudy.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@Transactional
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    @DisplayName("유저를 추가한다.")
    void addUser() {

        //given
        String userName = "서예린";
        String email = "asjs11@naver.com";

        //when
        Long id = userService.addUser(userName, email);

        //then
        assertThat(id).isNotNull();
    }

}