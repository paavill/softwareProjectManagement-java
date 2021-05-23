package ru.rsreu.Chistyakov0818.datalayer.interfaces;

import java.util.Collection;

import ru.rsreu.Chistyakov0818.datalayer.data.PersonInvestment;
import ru.rsreu.Chistyakov0818.datalayer.data.Stock;
import ru.rsreu.Chistyakov0818.exceptions.DataRequestException;

public interface StocksDao {

	Collection<Stock> getStocks() throws DataRequestException;

	Collection<PersonInvestment> getAverageStockAmountInInvestments() throws DataRequestException;

}
