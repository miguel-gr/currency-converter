package org.currconv.config;
 
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

//import org.currconv.entities.user.User;
 
@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource dataSource;
	
    @Autowired
    PasswordEncoder passwordEncoder;
 
	@Autowired
    public void registerGlobalAuthentication(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(dataSource)
			.passwordEncoder(passwordEncoder())
            .usersByUsernameQuery(getUserQuery());
    }

    private String getUserQuery() {
        return "SELECT login as username, passhash as password "
                + "FROM usuario "
                + "WHERE login = ?";
    }

    @Bean
	public PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}
    
    /*@Bean
    AuthenticationProvider customAuthenticationProvider() {
        return null ;
    }

    @Bean   
    UserDetailsService customUserDetailsService() {
        return null;
    }*/

    public void configure(HttpSecurity http) throws Exception {
        /*http.authorizeRequests()                                                              
	        .antMatchers("/resources/**", "/signIn").permitAll()
            .anyRequest().authenticated()
                .and()
            .formLogin()
                .and()
            .httpBasic();*/
    	http.authorizeRequests()
        .anyRequest().permitAll();
    }
}