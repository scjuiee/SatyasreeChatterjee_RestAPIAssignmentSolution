package com.greatlearning.employeemanageapp.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.greatlearning.employeemanageapp.service.UserDetailsServiceImpl;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Bean
	public UserDetailsService userDetailsService() {
		return new UserDetailsServiceImpl();
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());

		return authProvider;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}

	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.httpBasic()
		.and()
		.authorizeRequests()
		.antMatchers("/").hasAnyAuthority("USER","ADMIN","ELEVATED")
		.antMatchers("/employeelist").hasAnyAuthority("USER","ADMIN","ELEVATED")
		.antMatchers("/employee/**").hasAnyAuthority("USER","ADMIN","ELEVATED")
		.antMatchers("/sortedemployeelist").hasAnyAuthority("USER","ADMIN","ELEVATED")
		.antMatchers("/addemployee").hasAuthority("ADMIN")
		.antMatchers("/updateemployee").hasAuthority("ADMIN")
		.antMatchers("/delete/**").hasAuthority("ADMIN")
		.and()
		.exceptionHandling()
		.accessDeniedPage("/403")
		.and()
		.csrf()
		.disable()
		.formLogin().disable();
		
		http.headers().frameOptions().disable();
	}
	
	

}
