package org.currconv.entities.user;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Entity
@Table(name = "APP_USER")
public class User implements java.io.Serializable {

	private int userId;
	private String username;
	private String passHash;
    private static final String SALT="uwO4U=$(%6KfN0J(/v6UCKNX\"$&kvidvbAo0SU$&YoWqLf0jLhCEA(67S75Mu%!XFZ10M0%#rcMY";

	public User() {
	}

    private String getHashPass(final String original){
        String salted = original+User.SALT;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(salted.getBytes());
            byte[] digest = messageDigest.digest();
            return String.format("%064x", new java.math.BigInteger(1, digest));
        }catch(NoSuchAlgorithmException e){
            // Should not happen
        }
        return null;
    }
    
	public User(String username, String password) {
		this.username = username;
		this.passHash = this.getHashPass(password);
	}

	@Id
	@Column(name = "id", unique = true, nullable = false, precision = 5, scale = 0)
	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Column(name = "username", unique = true, nullable = false, length = 20)
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "passhash", nullable = false, length = 65)
	public String getPassHash() {
		return this.passHash;
	}

	public void setPassHash(String passHash) {
		this.passHash = passHash;
	}


}
