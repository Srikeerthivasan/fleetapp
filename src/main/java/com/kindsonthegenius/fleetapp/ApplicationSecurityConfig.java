package com.kindsonthegenius.fleetapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationSecurityConfig {

	
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
			.csrf().disable()
			.authorizeRequests()
			.requestMatchers("/login", "/resources/**", "/css/**", "/fonts/**", "/img/**").permitAll()
			.requestMatchers("/register", "/resources/**", "/css/**", "/fonts/**", "/img/**", "/js/**").permitAll()
			.requestMatchers("/users/addNew").permitAll()
			.requestMatchers("/security/user/Edit/**").hasAuthority("ADMIN")
			.anyRequest().authenticated()
			.and()
			.formLogin()
			.loginPage("/login").permitAll()
			.defaultSuccessUrl("/index")
			.and()
			.exceptionHandling().accessDeniedPage("/accessdenied")
			.and()
			.logout().invalidateHttpSession(true)
			.clearAuthentication(true)
			.logoutUrl("/logout")
			.logoutSuccessUrl("/login").permitAll();
		
		return http.build();
			
	}
	
	@SuppressWarnings("deprecation")
	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	
	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService);
		provider.setPasswordEncoder(bCryptPasswordEncoder());
		return provider;
	}

	/*public String getUsername() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        if (authentication == null)
            return null;
        Object principal = authentication.getPrincipal();
        if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername();
        } else {
            return principal.toString();
        }
    }*/
}
