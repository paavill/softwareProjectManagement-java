package ru.rsreu.Chistyakov0818.datalayer.oracle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.prutzkow.resourcer.Resourcer;

import ru.rsreu.Chistyakov0818.datalayer.DaoFactory;
import ru.rsreu.Chistyakov0818.datalayer.StorageConfiguration;
import ru.rsreu.Chistyakov0818.datalayer.interfaces.ClientsDao;
import ru.rsreu.Chistyakov0818.datalayer.interfaces.StocksDao;
import ru.rsreu.Chistyakov0818.datalayer.interfaces.StocksOperationsDao;
import ru.rsreu.Chistyakov0818.exceptions.StorageException;

public class OracleDbDaoFactory extends DaoFactory {
	private static volatile OracleDbDaoFactory instance;
	private Connection connection;

	private OracleDbDaoFactory() {

	}

	public static OracleDbDaoFactory getInstance(StorageConfiguration configuraiton) throws StorageException {
		OracleDbDaoFactory factory = instance;
		if (instance == null) {
			synchronized (OracleDbDaoFactory.class) {
				factory = new OracleDbDaoFactory();
				instance = factory;
				factory.connected(configuraiton);
			}
		}
		return factory;
	}

	private void connected(StorageConfiguration configuraiton) throws StorageException {
		String url = ((OracleDbConfiguration) configuraiton).getUrl();
		String user = ((OracleDbConfiguration) configuraiton).getUser();
		String password = ((OracleDbConfiguration) configuraiton).getPassword();
		try {
			connection = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			throw new StorageException(Resourcer.getString("exceptions.sql.connect"));
		}
	}

	@Override
	public StocksOperationsDao getStocksOperationsDao() {
		return new OracleStocksOperationsDao(connection);
	}

	@Override
	public StocksDao getStocksDao() {
		return new OracleStocksDao(connection);
	}

	@Override
	public ClientsDao getClientDao() {
		return new OracleClientDao(connection);
	}

	@Override
	public void closeStorageConnection() throws StorageException {
		try {
			this.connection.close();
		} catch (SQLException e) {
			throw new StorageException(Resourcer.getString("exceptions.sql.disconnect"));
		}
	}

}
