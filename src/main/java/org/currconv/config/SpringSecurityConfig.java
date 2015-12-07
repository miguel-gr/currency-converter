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
import org.currconv.entities.user.UserTypes;

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
            .usersByUsernameQuery(getUserQuery())
            .authoritiesByUsernameQuery(getAuthoritiesQuery());
    }

    private String getUserQuery() {
        return "SELECT username as login, passhash as password, true "
                + "FROM APP_USER "
                + "WHERE username = ?";
    }

    /* At this moment all users have the same role */
    private String getAuthoritiesQuery() {
        return "SELECT username as login, '"+UserTypes.USER.name()+"' "
                + "FROM APP_USER "
                + "WHERE username = ?";
    }

    @Bean
	public PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

    /**
     * Authorization is done at more fine grained levels
     */
    public void configure(HttpSecurity http) throws Exception {
    	http.authorizeRequests()
        .anyRequest().permitAll();
    }
}