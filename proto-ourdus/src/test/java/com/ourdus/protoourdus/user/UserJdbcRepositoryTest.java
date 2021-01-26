package com.ourdus.protoourdus.user;

import com.ourdus.protoourdus.user.model.UserVo;
import com.ourdus.protoourdus.user.repository.UserJdbcRepository;
import com.ourdus.protoourdus.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@JdbcTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserJdbcRepositoryTest {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private UserRepository userJdbcRepository;
    private UserVo userVo;
    private UserVo insertVo;

    @BeforeAll
    void setUp(){
        String userEmail = "testtest@gmail.com";
        String userPw = "1222";
        String userName = "hyejin";
        String userTel = "010-1221-2121";
        userVo = new UserVo(userEmail, userPw, userName, userTel);

        userJdbcRepository = new UserJdbcRepository(jdbcTemplate);
        insertVo = userJdbcRepository.insert(userVo);
        //System.out.println(insertVo.toString()); //insert 후에 keyholder로 user_id만 받아왔으므로 default로 생성해준 reg_date, user_point, writer_flag는 null로 반환
    }

    @Test
    public void 이메일로유저찾기_테스트(){
        UserVo getVo = userJdbcRepository.findByEmail(userVo.getUserEmail()).orElse(null);
        // Assertions.assertThat(getVo).isEqualTo(insertVo); (insertVo에는 위에서처럼 default 값이 null로 저장되어있어서 객체끼리 isEqualTo 비교는 못함)
        assertThat(getVo.getUserId()).isEqualTo(insertVo.getUserId());
    }

    @Test
    public void 사용자수정_테스트(){
        String changeName = "change_hyejin";
        userJdbcRepository.update(
                new UserVo.Builder(insertVo)
                .userName(changeName)
                .build());
        UserVo getVo = userJdbcRepository.findByEmail(insertVo.getUserEmail()).orElse(null);
        assertThat(getVo.getUserName()).isEqualTo(changeName);
    }

    @Test
    public void 이메일로유저삭제_테스트(){
        userJdbcRepository.deleteByEmail(insertVo.getUserEmail());
        UserVo findVo = userJdbcRepository.findByEmail(insertVo.getUserEmail()).orElse(null);
        assertThat(findVo).isNull();
    }
}
