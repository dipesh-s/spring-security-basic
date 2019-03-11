package com.example.springsecurity1.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.InMemoryUserDetailsManagerConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer.AuthorizedUrl;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    public SecurityConfiguration() {
    }

    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        ((InMemoryUserDetailsManagerConfigurer)auth.inMemoryAuthentication().withUser("user1").password("{noop}password").roles(new String[]{"USER"}).and()).withUser("admin1").password("{noop}password").roles(new String[]{"ADMIN"});
    }

    protected void configure(HttpSecurity httpSecurity) throws Exception {
        ((HttpSecurity)((AuthorizedUrl)((AuthorizedUrl)((AuthorizedUrl)httpSecurity.authorizeRequests().antMatchers(new String[]{"/rest/hello"})).hasRole("USER").antMatchers(new String[]{"/rest/helloadmin"})).hasRole("ADMIN").anyRequest()).fullyAuthenticated().and()).httpBasic();
        httpSecurity.csrf().disable();
    }
}
