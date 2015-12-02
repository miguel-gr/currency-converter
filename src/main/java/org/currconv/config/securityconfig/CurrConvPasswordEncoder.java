package org.currconv.config.securityconfig;
 
import javax.sql.DataSource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.currconv.entities.user.User;
 
@Component
public class CurrConvPasswordEncoder implements PasswordEncoder {

    public String encode(CharSequence rawPassword) {
        return null; // TODO implement
    }

    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return true; // TODO implement
    }
}