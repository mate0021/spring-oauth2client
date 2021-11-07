package rnd.mate00.oauth2client.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .antMatchers("/allowed", "/a", "/welcome", "/w", "/").permitAll()
            .antMatchers("/restricted", "/r").authenticated()
            .and()
            .logout().invalidateHttpSession(true).deleteCookies().logoutSuccessUrl("/welcome")
            .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
            .and().oauth2Login().loginPage("/welcome").defaultSuccessUrl("/loginOk");

    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
            .ignoring()
            .antMatchers("/h2-console/**");
    }
}
