package com.cs.trading.Configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception{
		httpSecurity
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
		.authorizeRequests()
		.antMatchers("/users").hasRole("USER")
		.antMatchers("/traders/*").hasRole("ADMIN")
		.anyRequest().permitAll()
				.antMatchers("/orders").hasRole("USER")
				.anyRequest().permitAll()
		.and()
		.httpBasic()
		.and()
		.csrf().disable();
		httpSecurity.headers().frameOptions().disable();
	}
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth
		.inMemoryAuthentication()
		.withUser("0").password("{noop}123456").roles("USER")
		.and()
		.withUser("1").password("{noop}123456").roles("ADMIN");
	}
}

