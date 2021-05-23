package ru.rsreu.Chistyakov0818.datalayer.data;

import java.math.BigDecimal;
import java.sql.Date;

public class StockOperation {

	private int operationCode;
	private String stockCode;
	private String clientCode;
	private BigDecimal quotation;
	private Date operationDate;
	private String sign;

	public StockOperation() {

	}

	public StockOperation(int operationCode, String stockCode, String clientCode, BigDecimal quotation,
			Date operationDate, String sign) {
		super();
		this.operationCode = operationCode;
		this.stockCode = stockCode;
		this.clientCode = clientCode;
		this.quotation = quotation;
		this.operationDate = operationDate;
		this.sign = sign;
	}

	public int getOperationCode() {
		return operationCode;
	}

	public void setOperationCode(int operationCode) {
		this.operationCode = operationCode;
	}

	public String getStockCode() {
		return stockCode;
	}

	public void setStockCode(String stockCode) {
		this.stockCode = stockCode;
	}

	public String getClientCode() {
		return clientCode;
	}

	public void setClientCode(String clientCode) {
		this.clientCode = clientCode;
	}

	public BigDecimal getQuotation() {
		return quotation;
	}

	public void setQuotation(BigDecimal quotation) {
		this.quotation = quotation;
	}

	public Date getOperationDate() {
		return operationDate;
	}

	public void setOperationDate(Date operationDate) {
		this.operationDate = operationDate;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append(this.clientCode).append(" ").append(this.operationCode).append(" ").append(this.sign).append(" ")
				.append(this.stockCode).append(" ").append(this.quotation).append(" ")
				.append(this.operationDate.toString()).append(" ");
		return result.toString();

	}

}
