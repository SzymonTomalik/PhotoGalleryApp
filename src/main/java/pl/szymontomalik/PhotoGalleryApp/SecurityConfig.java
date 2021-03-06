package pl.szymontomalik.PhotoGalleryApp;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import pl.szymontomalik.PhotoGalleryApp.services.UserDetailsServiceImp;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsServiceImp customUserDetailsService() {
        return new UserDetailsServiceImp();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/", "/index","/album/*").authenticated()
                .antMatchers("/login","/registration").permitAll()
                .antMatchers("/create-album","/share-album/*", "/upload/*", "/album-details/*").hasRole("ADMIN")
                .antMatchers("/album-photos/*").hasRole("USER")
                .and()
                .formLogin().loginPage("/login")
                .usernameParameter("login")
                .passwordParameter("password")
                .defaultSuccessUrl("/index", true)
                .and().logout()
                .logoutSuccessUrl("/login");
    }
}
