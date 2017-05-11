package com.recipe;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Created by doughepi on 3/9/17.
 */
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests().antMatchers("/create", "/recipe", "/recipes").authenticated()
                .and()
                .authorizeRequests().anyRequest().permitAll() //Authorize any users.
                .and()
                .authorizeRequests().antMatchers("/h2-console/**").permitAll()
                .and()
                .formLogin() //Enable login via form for later implementation.
                .and()
                .httpBasic().disable()  //Disable basic authentication.
                .csrf().disable() //Disable csrf
                .headers().frameOptions().sameOrigin(); //H2 console fix
    }
}
