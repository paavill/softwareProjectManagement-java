package ru.rsreu.Chistyakov0818;

import java.sql.Date;
import java.util.Collection;
import java.util.Locale;

import com.prutzkow.resourcer.Resourcer;

import ru.rsreu.Chistyakov0818.datalayer.DaoFactory;
import ru.rsreu.Chistyakov0818.datalayer.StorageConfiguration;
import ru.rsreu.Chistyakov0818.datalayer.StorageType;
import ru.rsreu.Chistyakov0818.datalayer.data.Client;
import ru.rsreu.Chistyakov0818.datalayer.data.PersonInvestment;
import ru.rsreu.Chistyakov0818.datalayer.data.Stock;
import ru.rsreu.Chistyakov0818.datalayer.data.StockOperation;
import ru.rsreu.Chistyakov0818.datalayer.interfaces.ClientsDao;
import ru.rsreu.Chistyakov0818.datalayer.interfaces.StocksDao;
import ru.rsreu.Chistyakov0818.datalayer.interfaces.StocksOperationsDao;
import ru.rsreu.Chistyakov0818.datalayer.oracle.OracleDbConfiguration;
import ru.rsreu.Chistyakov0818.exceptions.DataRequestException;
import ru.rsreu.Chistyakov0818.exceptions.StorageException;

public class ApplicationRunner {

	private ApplicationRunner() {

	}

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Date date = Date.valueOf(Resourcer.getString("requests.sql.get.stocksOperations.date"));
		DaoFactory daoFactory = null;
		StorageConfiguration configuration = new OracleDbConfiguration(Resourcer.getString("dataSorage.oracle.url"),
				Resourcer.getString("dataSorage.oracle.user"), Resourcer.getString("dataSorage.oracle.password"));
		try {
			daoFactory = DaoFactory.getInstance(StorageType.ORACLE_DB, configuration);

			StocksOperationsDao stocksOperationDao = daoFactory.getStocksOperationsDao();
			StocksDao stockDao = daoFactory.getStocksDao();
			ClientsDao clientDao = daoFactory.getClientDao();

			Collection<StockOperation> stockOperations = stocksOperationDao.getStocksOperationsRelativelyDate(date);
			StringBuilder table = new StringBuilder(CollectionToTableFormatter.format(stockOperations)).append("\n");

			Collection<Stock> stocks = stockDao.getStocks();
			table.append(CollectionToTableFormatter.format(stocks)).append("\n");

			Stock stockToSearchClients = (Stock) stocks.toArray()[2];
			Collection<Client> clients = clientDao.getClientsBoughtStock(stockToSearchClients.getStockCode());
			table.append(CollectionToTableFormatter.format(clients)).append("\n");

			Collection<PersonInvestment> personInvestments = stockDao.getAverageStockAmountInInvestments();
			table.append(CollectionToTableFormatter.format(personInvestments));

			System.out.println(table.toString());
			daoFactory.closeStorageConnection();
		} catch (StorageException | InstantiationException | IllegalAccessException | DataRequestException e) {
			System.out.println(e.getMessage());
			try {
				if (!(e instanceof StorageException)) {
					daoFactory.closeStorageConnection();
				}
			} catch (StorageException exc) {
				System.out.println(exc.getMessage());
			}
		}

	}

}
