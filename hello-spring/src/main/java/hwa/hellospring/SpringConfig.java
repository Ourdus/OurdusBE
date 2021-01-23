package hwa.hellospring;

import hwa.hellospring.controller.MemberController;
import hwa.hellospring.repository.JdbcMemberRepository;
import hwa.hellospring.repository.MemberRepository;
import hwa.hellospring.repository.MemoryMemberRepository;
import hwa.hellospring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    private final DataSource dataSource;
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }
    @Bean
    public MemberRepository memberRepository() {
        return new JdbcMemberRepository(dataSource);
    }

}
