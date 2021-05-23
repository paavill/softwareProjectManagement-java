package ru.rsreu.Chistyakov0818.datalayer.interfaces;

import java.sql.Date;
import java.util.Collection;
import java.util.List;

import ru.rsreu.Chistyakov0818.datalayer.data.StockOperation;
import ru.rsreu.Chistyakov0818.exceptions.DataRequestException;

public interface StocksOperationsDao {

	List<StockOperation> getStocksOperations();

	Collection<StockOperation> getStocksOperationsRelativelyDate(Date date) throws DataRequestException;
}
