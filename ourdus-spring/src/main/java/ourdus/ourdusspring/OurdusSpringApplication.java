package ourdus.ourdusspring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import ourdus.ourdusspring.interceptor.JwtInterceptor;

import java.util.LinkedList;
import java.util.List;

@SpringBootApplication
public class OurdusSpringApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(OurdusSpringApplication.class, args);
	}

	@Autowired
	private JwtInterceptor jwtInterceptor;

	private List<String> excludeList = new LinkedList<>();

	@Override
	public void addInterceptors(InterceptorRegistry registry){
		addExcludeList();
		registry.addInterceptor(jwtInterceptor)
				.addPathPatterns("/api/t/**");
//				.addPathPatterns("/api/w/product/**/comment/**")
//				.addPathPatterns("/api/w/review/**")
//				.excludePathPatterns(excludeList); //적용 제외 경로
	}

//	interceptor 이용해서 처리하므로 전역의 corss origin 처리해준다
	@Override
	public void addCorsMappings(CorsRegistry registry){
		registry.addMapping("/**")
				.allowedOrigins("*")
				.allowedMethods("*")
				.allowedHeaders("*")
				.exposedHeaders("jwt-auth-token");
	}

	public void addExcludeList(){
		excludeList.add("/api/user/join");
		excludeList.add("/api/user/login");
		excludeList.add("/api/user/id-finding");
		excludeList.add("/api/user/pw-finding");
		excludeList.add("/api/user/id-finding");
		excludeList.add("/api/w/product/\\d+/comment/\\d+");
		excludeList.add("/api/w/review/\\d+");

	}

}
