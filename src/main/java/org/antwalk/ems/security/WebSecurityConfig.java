package org.antwalk.ems.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.http.HttpMethod;

@EnableWebSecurity
@Configuration
public class WebSecurityConfig {

    @Bean
    public UserDetailsService userDetailsService() {
        return new LoginDetailsService();
    }
     
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService());
        provider.setPasswordEncoder(new BCryptPasswordEncoder());
        return provider;
    }

    // @Bean
    // public InMemoryUserDetailsManager userDetailsService(PasswordEncoder passwordEncoder) {
    //     UserDetails user = User.withUsername("user")
    //         .password(passwordEncoder.encode("password"))
    //         .roles("USER")
    //         .build();

    //     UserDetails admin = User.withUsername("admin")
    //         .password(passwordEncoder.encode("admin"))
    //         .roles("USER", "ADMIN")
    //         .build();

    //     return new InMemoryUserDetailsManager(user, admin);
    // }



    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/welcome").permitAll()
                .antMatchers(HttpMethod.GET,"/admin/*").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET,"/employee/*").hasRole("EMP")
                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/loggedin", true);
        return http.build();
    }

    // @Bean
    // protected UserDetailsService userDetailsService(){
    //     return new LoginDetailsService();
    // }
}
