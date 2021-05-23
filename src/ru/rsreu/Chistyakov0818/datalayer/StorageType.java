package ru.rsreu.Chistyakov0818.datalayer;

import ru.rsreu.Chistyakov0818.datalayer.oracle.OracleDbDaoFactory;
import ru.rsreu.Chistyakov0818.exceptions.StorageException;

public enum StorageType {

	ORACLE_DB {

		@Override
		public DaoFactory getDaoFactory(StorageConfiguration configuraiton) throws StorageException {
			DaoFactory oracleDbDaoFactory = null;
			oracleDbDaoFactory = OracleDbDaoFactory.getInstance(configuraiton);
			return oracleDbDaoFactory;
		}

	};

	public abstract DaoFactory getDaoFactory(StorageConfiguration configuraiton) throws StorageException;
}
