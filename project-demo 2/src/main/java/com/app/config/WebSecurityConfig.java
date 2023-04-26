package com.app.config;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.app.filters.JWTRequestFilter;

@EnableWebSecurity // mandatory
@Configuration // mandatory
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {

	@Autowired
	private JWTRequestFilter filter;

	// configure BCryptPassword encode bean
	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable().
		exceptionHandling().
		authenticationEntryPoint((request, response, ex) -> {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, ex.getMessage());
		}).
		and().
		authorizeRequests()
//		.antMatchers("/products/add").hasRole("ADMIN")
//		.antMatchers("/products/purchase").hasRole("CUSTOMER")
		.antMatchers("/","index","/css/*","/js/*","/swagger-ui/**","/api-docs/**","/products/**","/cart/**").permitAll()
//		.antMatchers("/products/view","/ui/**", "/auth/**", "/swagger/**" ,"/v1/api-docs/**").permitAll() // enabling global
//		 access to all
		.antMatchers("/orderItems/**").permitAll()
		.antMatchers("/products","/users","/users/{userId}","/products/{productId}","/auth/admin/signup").hasRole("ADMIN")
		.antMatchers("/products","/users","/users/{userId}","/products/{productId}","/auth/admin/signup","/categories/**").hasRole("ADMIN")
		.antMatchers("/products/purchase","/orders/**","/carts/**","/orderItems/**").hasRole("CUSTOMER")
		.antMatchers("/auth/**", "/swagger*/**", "/v*/api-docs/**","/products/product/{productId}","/categories/**","categories/{categoryId}").permitAll() 
		.antMatchers(HttpMethod.OPTIONS).permitAll()
																										// urls with
																										// /auth
				// only required for JS clnts (react / angular)
		.antMatchers(HttpMethod.OPTIONS).permitAll().
		anyRequest().authenticated().
		and().
		sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS).
		and()
		.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}

	// configure auth mgr bean : to be used in Authentication REST controller
	@Bean
	public AuthenticationManager authenticatonMgr(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}

}
