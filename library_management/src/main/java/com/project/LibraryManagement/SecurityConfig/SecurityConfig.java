package com.project.LibraryManagement.SecurityConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.project.LibraryManagement.Service.Impl.UserDetailsServiceImpl;
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
 private final UserDetailsServiceImpl userDetailService;
 private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
 private final JwtAuthenticationFilter jwtAuthenticationFilter;

 public SecurityConfig(UserDetailsServiceImpl userDetailService,
                       JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint,
                       JwtAuthenticationFilter jwtAuthenticationFilter) {
     this.userDetailService = userDetailService;
     this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
     this.jwtAuthenticationFilter = jwtAuthenticationFilter;
 }

 @Override
 protected void configure(AuthenticationManagerBuilder auth) throws Exception {
     auth.userDetailsService(userDetailService).passwordEncoder(passwordEncoder());
 }

 @Override
 protected void configure(HttpSecurity http) throws Exception {
     http.cors().and().csrf().disable()
             .authorizeRequests()
             .antMatchers("/authenticate").permitAll()
             .anyRequest().authenticated()
             .and()
             .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint)
             .and()
             .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

     http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
 }

 @Bean
 public PasswordEncoder passwordEncoder() {
     return new BCryptPasswordEncoder();
 }

 @Override
 @Bean
 public AuthenticationManager authenticationManagerBean() throws Exception {
     return super.authenticationManagerBean();
 }

 @Override
 public void configure(WebSecurity web) throws Exception {
     web.ignoring().antMatchers("/h2-console/**");
 }
}
