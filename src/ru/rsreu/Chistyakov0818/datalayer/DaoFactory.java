package ru.rsreu.Chistyakov0818.datalayer;

import ru.rsreu.Chistyakov0818.datalayer.interfaces.ClientsDao;
import ru.rsreu.Chistyakov0818.datalayer.interfaces.StocksDao;
import ru.rsreu.Chistyakov0818.datalayer.interfaces.StocksOperationsDao;
import ru.rsreu.Chistyakov0818.exceptions.StorageException;

public abstract class DaoFactory {

	public static DaoFactory getInstance(StorageType dbType, StorageConfiguration configuraiton)
			throws StorageException {
		DaoFactory result = dbType.getDaoFactory(configuraiton);
		return result;
	}

	public abstract StocksOperationsDao getStocksOperationsDao();

	public abstract StocksDao getStocksDao();

	public abstract ClientsDao getClientDao();

	public abstract void closeStorageConnection() throws StorageException;

}
