package org.currconv.entities.currencies;

import org.joda.money.Money;
import org.joda.money.CurrencyUnit;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.PrePersist;


/**
 *  Entity for currency conversion
 */
@Entity
@Table(name = "CURRENCY_CONVERSION")
public class CurrencyConversion {

	private int id;
	private int userId;
	private String originalString;
    private String convertedString;
	private Money originalMoney;
    private Money convertedMoney;
    private Date conversionDate;

	public CurrencyConversion() {
	}

	public CurrencyConversion(final Money originalMoney, final Money convertedMoney) {
		this.originalMoney = originalMoney;
        this.originalString = originalMoney.toString();
		this.convertedMoney = convertedMoney;
        this.convertedString = convertedMoney.toString();
	}

    @PrePersist
    protected void onCreate() {
        conversionDate = new Date();
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

    @Transient
	public Money getOriginalMoney() {
		return Money.parse(this.originalString);
	}

    @Transient
	public Money getConvertedMoney() {
		return Money.parse(this.convertedString);
	}

	@Column(name = "user_id", unique = false, nullable = false, precision = 5, scale = 0)
	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Column(name = "original_string", unique = false, nullable = false, length = 100)
	public String getOriginalString() {
		return this.originalString;
	}

	public void setOriginalString(String originalString) {
		this.originalString = originalString;
	}

	@Column(name = "converted_string", unique = false, nullable = false, length = 100)
	public String getConvertedString() {
		return this.convertedString;
	}

	public void setConvertedString(String convertedString) {
		this.convertedString = convertedString;
	}

	@Column(name = "conversion_date", unique = false, nullable = true)
	public Date getConversionDate() {
		return this.conversionDate;
	}

	public void setConversionDate(Date conversionDate) {
		this.conversionDate = conversionDate;
	}

}
