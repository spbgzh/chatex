package com.chatex.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/").permitAll();

        //If user don't have permission will be redirected to login page
        http.formLogin().loginPage("/doLogin")
                .usernameParameter("user").passwordParameter("pwd").
                defaultSuccessUrl("/index").failureUrl("/doLogin").permitAll()
                .and()
                .csrf().disable()
                .rememberMe();
        http.logout().logoutSuccessUrl("/")
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true)
                .clearAuthentication(true); ;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //test
        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
                .withUser("root")
                .password(new BCryptPasswordEncoder().encode("123456"))
                .roles("root");
        /*
        不再使用内存方式存储用户认证信息，而是动态从数据库中获取
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
        */
    }
    @Override
    public void configure(WebSecurity web) throws Exception {
        // 静态资源的访问不需要拦截，直接放行
        web.ignoring().antMatchers("/**/*.css", "/**/*.js", "/**/*.png", "/**/*.jpg", "/**/*.jpeg");
    }

}
