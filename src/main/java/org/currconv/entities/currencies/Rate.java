package org.currconv.entities.currencies;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Rates
 */
@Entity
@Table(name = "RATE")
public class Rate {

	private int id;
	private String code;
    private BigDecimal rate;
    private Date rateTime;

	public Rate() {
	}

	public Rate(String code, BigDecimal rate, Date rateTime) {
        this.code = code;
		this.rate = rate;
        this.rateTime = rateTime;
	}

	public Rate(String code, BigDecimal rate, long timestamp) {
        this.code = code;
		this.rate = rate;
        this.rateTime = new Date(timestamp);
	}

	@Id
	@Column(name = "id", unique = true, nullable = false, precision = 5, scale = 0)
    @GeneratedValue(strategy=GenerationType.AUTO)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "code", unique = false, nullable = false, length = 3)
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "rate", unique = false, nullable = false)
	public BigDecimal getRate() {
		return this.rate;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}

	@Column(name = "rate_time", unique = false, nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @GeneratedValue(strategy=GenerationType.AUTO)
	public Date getRateTime() {
		return this.rateTime;
	}

	public void setRateTime(Date rateTime) {
		this.rateTime = rateTime;
	}

}
