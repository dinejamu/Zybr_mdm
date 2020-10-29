package com.example.zybr_mdm.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class UserSecurity extends WebSecurityConfigurerAdapter {

    //@Qualifier("UserDetailsImplements")
    @Autowired
    private UserDetailsImplements userDetailsImplements;

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoProvider=new DaoAuthenticationProvider();
        daoProvider.setUserDetailsService(userDetailsImplements);
        daoProvider.setPasswordEncoder(passwordEncoder());
        return daoProvider;
    }
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

         http
             .csrf().disable()
             .authorizeRequests().antMatchers("/","/registor","/login1","/signup","/welcome").permitAll()
             .anyRequest().authenticated()
             .and()
             .formLogin().loginPage("/login").permitAll();
             /*.usernameParameter("email")
             .passwordParameter(passwordEncoder().encode("password"));*/

    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/CSS/**","/JS/**", "/css/**", "/js/**", "/images/**", "/webjars/**");
    }
    /*@Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/*.css");
        web.ignoring().antMatchers("/*.js");
    }*/
}
