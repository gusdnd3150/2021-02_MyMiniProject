package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.example.demo.service.CommonService;

import lombok.RequiredArgsConstructor;

@EnableWebSecurity
public class ConfigMyProject extends WebSecurityConfigurerAdapter {

	@Autowired
	CommonService commonService;

	@Bean
	public PasswordEncoder passwordEncoder() { // 4
		return new BCryptPasswordEncoder();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(commonService).passwordEncoder(passwordEncoder());
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**");
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		
		http.csrf().disable();
		http.authorizeRequests().antMatchers("/**").permitAll();
		/*
		http.authorizeRequests()
		.antMatchers("/addConfidence.do/**", "/statics").hasRole("ADMIN")
		.antMatchers("/", "/qrcode").hasAnyRole("USER", "ADMIN")
		.antMatchers("/**").permitAll()
	.and()
		.formLogin()
		.loginPage("/loginForm.do")
		.loginProcessingUrl("/login.do")
		.defaultSuccessUrl("/main.do")
		.permitAll()
	.and()
		.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/");	
         */
	}
}
