package ru.rsreu.Chistyakov0818.datalayer.data;

public class Client {

	private int clientCode;
	private String clientName;
	private String clientAdress;
	private String clientType;
	private String phoneNumber;

	public Client(int clientCode, String clientName, String clientAdress, String clientType, String phoneNumber) {
		super();
		this.clientCode = clientCode;
		this.clientName = clientName;
		this.clientAdress = clientAdress;
		this.clientType = clientType;
		this.phoneNumber = phoneNumber;
	}

	public int getClientCode() {
		return clientCode;
	}

	public void setClientCode(int clientCode) {
		this.clientCode = clientCode;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getClientAdress() {
		return clientAdress;
	}

	public void setClientAdress(String clientAdress) {
		this.clientAdress = clientAdress;
	}

	public String getClientType() {
		return clientType;
	}

	public void setClientType(String clientType) {
		this.clientType = clientType;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

}
