package com.example.demo.configWeb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.demo.common.CommonService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class ConfigMyProject extends WebSecurityConfigurerAdapter {

	@Autowired
	CommonService commonService;
	
	  @Bean 
	  public PasswordEncoder passwordEncoder() { // 4
	    return new BCryptPasswordEncoder();
	  }
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		 auth.userDetailsService(commonService);
		 //auth.userDetailsService(commonService).passwordEncoder(passwordEncoder());
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.csrf().disable();   //아작스 post 요청 승인
		http
        .authorizeRequests() // 7
          .antMatchers("/addConfidence.do", "/signup").authenticated() // 권한있는놈만
          //.antMatchers("/").hasRole("user") // USER, ADMIN만 접근 가능
          .antMatchers("/admin").hasRole("ADMIN") // ADMIN만 접근 가능
          .anyRequest().permitAll() // 나머지 요청들은 권한의 종류에 상관 없이 권한이 있어야 접근 가능
      .and() 
        .formLogin() // 8
          .loginPage("/loginForm.do") // 로그인 페이지 링크
          .defaultSuccessUrl("/main.do") // 로그인 성공 후 리다이렉트 주소
      .and()
        .logout() // 9
          .logoutSuccessUrl("/logout.do") // 로그아웃 성공시 리다이렉트 주소
	    .invalidateHttpSession(true) // 세션 날리기
  ;
	}
	
	

}
