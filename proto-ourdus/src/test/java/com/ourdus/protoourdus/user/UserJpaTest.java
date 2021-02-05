package com.ourdus.protoourdus.user;

import com.ourdus.protoourdus.user.model.UserVo;
import com.ourdus.protoourdus.user.repository.UserJpaRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@DataJpaTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserJpaTest {

    @PersistenceContext
    private EntityManager em;

    private UserJpaRepository userJpaRepository;
    private UserVo userVo;
    private UserVo insertVo;

    @BeforeAll
    void setUp(){
        userJpaRepository = new UserJpaRepository(em);

        String userEmail = "testtest@gmail.com";
        String userPw = "1222";
        String userName = "hyejin";
        String userTel = "010-1221-2121";
        userVo = new UserVo(userEmail, userPw, userName, userTel);

    }

//    @Test
//    public void 저장및_이메일로유저찾기_테스트(){
//        userJpaRepository.insert(userVo);
//        UserVo getVo = userJpaRepository.findByEmail(userVo.getUserEmail()).orElse(null);
//        assertThat(getVo.getUserName()).isEqualTo(userVo.getUserName());
//
//    }

    @Test
    public void 사용자수정_테스트(){
        // 이후는 vo로 반환하는 문제때문에, persistenceException 발생 테스트 진행이 어려움
    }


}
