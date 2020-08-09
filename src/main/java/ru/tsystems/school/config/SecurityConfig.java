package ru.tsystems.school.config;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.tsystems.school.model.Passenger;
import ru.tsystems.school.model.Role;
import ru.tsystems.school.security.AuthProviderImpl;
import ru.tsystems.school.service.PassengerService;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.time.LocalDate;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
@ComponentScan("ru.tsystems.school")
@Log4j
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final AuthProviderImpl authProvider;

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder(8);
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProvider);
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                    .authorizeRequests()
                    .antMatchers("/").permitAll()
                    .antMatchers("/trains/**").hasAuthority("ADMIN")
                    .antMatchers("/stations/add").hasAuthority("ADMIN")
                    .antMatchers("/passengers/**").hasAuthority("ADMIN")
                    .antMatchers("/login").anonymous()
                .and()
                    .formLogin().loginPage("/login")
                    .loginProcessingUrl("/authenticateTheUser")
                    .failureUrl("/login?error=true")
                .and()
                .logout()
                .and()
                .exceptionHandling().accessDeniedPage("/");

    }
}
