package ru.rsreu.Chistyakov0818.datalayer.oracle;

import ru.rsreu.Chistyakov0818.datalayer.StorageConfiguration;

public class OracleDbConfiguration extends StorageConfiguration {

	private final String url;
	private final String user;
	private final String password;

	public OracleDbConfiguration(String url, String user, String password) {
		super();
		this.url = url;
		this.user = user;
		this.password = password;
	}

	public String getUrl() {
		return url;
	}

	public String getUser() {
		return user;
	}

	public String getPassword() {
		return password;
	}

}
