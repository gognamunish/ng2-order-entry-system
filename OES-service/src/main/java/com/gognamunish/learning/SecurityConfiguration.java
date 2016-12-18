package com.gognamunish.learning;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	private static final String REALM = "OES_REALM";

	@Autowired
	public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("munish").password("s3cr3t").roles("ADMIN");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests().antMatchers("/api/**").hasRole("ADMIN").and().httpBasic()
				.realmName(REALM).authenticationEntryPoint(getBasicAuthEntryPoint()).and().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}

	@Bean
	public CustomBasicAuthenticationEntryPoint getBasicAuthEntryPoint() {
		return new CustomBasicAuthenticationEntryPoint();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
	}

	class CustomBasicAuthenticationEntryPoint extends BasicAuthenticationEntryPoint {

		@Override
		public void commence(final HttpServletRequest request, final HttpServletResponse response,
				final AuthenticationException authException) throws IOException, ServletException {
			// Authentication failed, send error response.
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			response.addHeader("WWW-Authenticate", "Basic realm=" + getRealmName() + "");

			PrintWriter writer = response.getWriter();
			writer.println("HTTP Status 401 : " + authException.getMessage());
		}

		@Override
		public void afterPropertiesSet() throws Exception {
			setRealmName(SecurityConfiguration.REALM);
			super.afterPropertiesSet();
		}
	}

}
