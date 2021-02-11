package ourdus.ourdusspring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import ourdus.ourdusspring.interceptor.JwtInterceptor;

@SpringBootApplication
public class OurdusSpringApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(OurdusSpringApplication.class, args);
	}

	@Autowired
	private JwtInterceptor jwtInterceptor;
	
//	@Override
//	public void addInterceptors(InterceptorRegistry registry){
//		registry.addInterceptor(jwtInterceptor).addPathPatterns("/api/**") //기본 적용 경로
//				.excludePathPatterns(Arrays.asList("/api/user/**")); //적용 제외 경로
//	}

	//interceptor 이용해서 처리하므로 전역의 corss origin 처리해준다
	@Override
	public void addCorsMappings(CorsRegistry registry){
		registry.addMapping("/**")
				.allowedOrigins("*")
				.allowedMethods("*")
				.allowedHeaders("*")
				.exposedHeaders("jwt-auth-token");
	}
}
