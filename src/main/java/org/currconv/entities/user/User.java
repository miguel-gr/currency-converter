package org.currconv.entities.user;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Entity
@Table(name = "APP_USER")
public class User implements java.io.Serializable {

	private static final long serialVersionUID = -401659923746661618L;
	private int userId;
	private String username;
	private String email;
	private String passHash;
    private Date dateOfBirth;
    private String street;
    private String zip;
    private String city;
    private String country;
    private String password;
    private String passwordVer;

	public User() {
	}

    private String getEncodedPass(final String original){
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		return passwordEncoder.encode(original);
    }
    
	public User(String username, String password) {
		this.username = username;
		this.setPassword(password);
	}

	@Id
	@Column(name = "id", unique = true, nullable = false, precision = 5, scale = 0)
    @GeneratedValue(strategy=GenerationType.AUTO)
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

	@Column(name = "email", unique = true, nullable = false, length = 50)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "date_of_birth", unique = false, nullable = false)
	public Date getDateOfBirth() {
		return this.dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	@Column(name = "street", unique = false, nullable = false)
	public String getStreet() {
		return this.street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	@Column(name = "zip", unique = false, nullable = false)
	public String getZip() {
		return this.zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	@Column(name = "city", unique = false, nullable = false)
	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Column(name = "country", unique = false, nullable = false)
	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

    @Transient
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.passHash = this.getEncodedPass(password);
		this.password = password;
	}

    @Transient
	public String getPasswordVer() {
		return this.passwordVer;
	}

	public void setPasswordVer(String passwordVer) {
		this.passwordVer = passwordVer;
	}
    
	@Column(name = "passhash", nullable = false, length = 65)
	public String getPassHash() {
		return this.passHash;
	}

	public void setPassHash(String passHash) {
		this.passHash = passHash;
	}


}
