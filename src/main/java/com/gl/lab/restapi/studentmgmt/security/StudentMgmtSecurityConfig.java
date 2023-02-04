package com.gl.lab.restapi.studentmgmt.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.gl.lab.restapi.studentmgmt.service.StudentUserDetailsService;

@Configuration
@ComponentScan(basePackages = { "com.gl.lab.restapi.studentmgmt" })
@EnableWebSecurity
public class StudentMgmtSecurityConfig {
	@Bean
	public UserDetailsService userDetailsService() {
		return new StudentUserDetailsService();
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public DaoAuthenticationProvider authProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());
		return authProvider;
	}

	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authProvider());
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http.authorizeRequests().antMatchers("/", "/student/save", "/student/showFormForAdd", "/student/403")
				.hasAnyAuthority("USER", "ADMIN").antMatchers("/student/showFormForUpdate", "/student/delete")
				.hasAuthority("ADMIN").anyRequest().authenticated().and().formLogin().loginProcessingUrl("/login")
				.successForwardUrl("/student/list").permitAll().and().logout().logoutSuccessUrl("/login").permitAll()
				.and().exceptionHandling().accessDeniedPage("/student/403").and().cors().and().csrf().disable();

		return http.build();
	}

	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
		return (web) -> web.ignoring().antMatchers("/images/**", "/js/**", "/webjars/**");
	}

}
