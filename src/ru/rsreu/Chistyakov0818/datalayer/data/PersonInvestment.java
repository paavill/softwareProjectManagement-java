package ru.rsreu.Chistyakov0818.datalayer.data;

import java.math.BigDecimal;

public class PersonInvestment {
	private String clientName;
	private String stockIssuer;
	private int operationNumber;
	private BigDecimal investmentAmount;
	private BigDecimal averageNumberOfStock;

	public PersonInvestment(String clientName, String stockIssuer, int operationNumber, BigDecimal investmentAmount,
			BigDecimal averageNumberOfStock) {
		super();
		this.clientName = clientName;
		this.stockIssuer = stockIssuer;
		this.operationNumber = operationNumber;
		this.investmentAmount = investmentAmount;
		this.averageNumberOfStock = averageNumberOfStock;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getStockIssuer() {
		return stockIssuer;
	}

	public void setStockIssuer(String stockIssuer) {
		this.stockIssuer = stockIssuer;
	}

	public int getOperationNumber() {
		return operationNumber;
	}

	public void setOperationNumber(int operationNumber) {
		this.operationNumber = operationNumber;
	}

	public BigDecimal getInvestmentAmount() {
		return investmentAmount;
	}

	public void setInvestmentAmount(BigDecimal investmentAmount) {
		this.investmentAmount = investmentAmount;
	}

	public BigDecimal getAverageNumberOfStock() {
		return averageNumberOfStock;
	}

	public void setAverageNumberOfStock(BigDecimal averageNumberOfStock) {
		this.averageNumberOfStock = averageNumberOfStock;
	}

}
