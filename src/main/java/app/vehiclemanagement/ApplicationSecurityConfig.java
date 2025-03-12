package app.vehiclemanagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/login", "/resources/**", "/static", "/assets/**", "/templates/**").permitAll()
                .antMatchers("/register", "/resources/**", "/static", "/assets/**", "/templates/**", "/js/**").permitAll()
                .antMatchers("/users/addNew").permitAll()
                .antMatchers("/roles/addNew").permitAll()
                .antMatchers("/index").permitAll()
                .antMatchers("/fleet/**").hasAuthority("FLEET_ADMIN")
                .antMatchers("/hr/**").hasAuthority("HR_ADMIN")
                .antMatchers("/accounts/**").hasAuthority("ACCOUNTS_ADMIN")
                .antMatchers("/parameters/**").hasAuthority("PARAMETERS_ADMIN")
                .antMatchers("/security/**").hasAuthority("SUPER_ADMIN")
                .antMatchers("/guestsprofile/**").hasAuthority("USER")
                .and()
                .formLogin()
                .loginPage("/login").permitAll()
                .defaultSuccessUrl("/")
                            .and()
                            .exceptionHandling().accessDeniedPage("/accessRestricted")
                .and()
                .logout().invalidateHttpSession(true)
                .clearAuthentication(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/").permitAll();

    }

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

}
