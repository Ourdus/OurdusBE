package hwa.hellospring;

import hwa.hellospring.controller.MemberController;
import hwa.hellospring.repository.JdbcMemberRepository;
import hwa.hellospring.repository.JpaProductRepository;
import hwa.hellospring.repository.MemberRepository;
//import hwa.hellospring.repository.MemoryMemberRepository;
import hwa.hellospring.repository.ProductRepository;
import hwa.hellospring.service.MemberService;
import hwa.hellospring.service.ProductService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    private EntityManager em;
    private final DataSource dataSource;

    public SpringConfig(DataSource dataSource, EntityManager em) {
        this.dataSource = dataSource;
        this.em = em;
    }

    //for member
    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }
    @Bean
    public MemberRepository memberRepository() {
        return new JdbcMemberRepository(dataSource);
    }

    //for product
    @Bean
    public ProductService productService() {
        return new ProductService(productRepository());
    }
    @Bean
    public ProductRepository productRepository() {
        return new JpaProductRepository(em);
    }

}
