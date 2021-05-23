package ru.rsreu.Chistyakov0818.datalayer.data;

import java.math.BigDecimal;

public class Stock {
	private int stockCode;
	private String issuer;
	private BigDecimal denomination;

	public Stock(int stockCode, String issuer, BigDecimal denomination) {
		super();
		this.stockCode = stockCode;
		this.issuer = issuer;
		this.denomination = denomination;
	}

	public int getStockCode() {
		return stockCode;
	}

	public void setStockCode(int stockCode) {
		this.stockCode = stockCode;
	}

	public String getIssuer() {
		return issuer;
	}

	public void setIssuer(String issuer) {
		this.issuer = issuer;
	}

	public BigDecimal getDenomination() {
		return denomination;
	}

	public void setDenomination(BigDecimal denomination) {
		this.denomination = denomination;
	}
}
