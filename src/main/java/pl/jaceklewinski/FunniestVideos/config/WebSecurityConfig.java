package pl.jaceklewinski.FunniestVideos.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

    @Autowired
    private DataSource dataSource;

    @Autowired
    public void configAuthentication (AuthenticationManagerBuilder auth) throws Exception {
        auth
                .jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery("SELECT username, password, active FROM user WHERE username=?")
                .authoritiesByUsernameQuery("SELECT username, role FROM user_roles WHERE username=?");
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .authorizeRequests()
                .antMatchers("/", "/css/**", "/fonts/**", "/js/**", "/registration").permitAll()
                .antMatchers("/userpanel", "/video/addvideo").authenticated()
                .anyRequest().authenticated()
                .and().formLogin().loginPage("/login").permitAll().defaultSuccessUrl("/userpanel")
                .and()
                .logout()
                    //spring security default enabled csrf protection which disabled /logout link by get method
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                    .permitAll();
        http
                .exceptionHandling().accessDeniedPage("/403");

    }
}
