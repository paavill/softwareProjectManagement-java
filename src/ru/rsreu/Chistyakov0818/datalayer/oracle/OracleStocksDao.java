package ru.rsreu.Chistyakov0818.datalayer.oracle;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import com.prutzkow.resourcer.Resourcer;

import ru.rsreu.Chistyakov0818.datalayer.data.PersonInvestment;
import ru.rsreu.Chistyakov0818.datalayer.data.Stock;
import ru.rsreu.Chistyakov0818.datalayer.interfaces.StocksDao;
import ru.rsreu.Chistyakov0818.exceptions.DataRequestException;

public class OracleStocksDao implements StocksDao {
	private Connection connection;

	public OracleStocksDao(Connection connection) {
		this.connection = connection;
	}

	@Override
	public Collection<Stock> getStocks() throws DataRequestException {
		Collection<Stock> result = new ArrayList<Stock>();
		String query = Resourcer.getString("requests.sql.get.stocks.beforeDate");
		Statement statement = null;
		try {
			statement = this.connection.createStatement();
			boolean executeResult = statement.execute(query);
			if (executeResult) {
				ResultSet resultSet = statement.getResultSet();
				while (resultSet.next()) {
					result.add(new Stock(resultSet.getInt(1), resultSet.getString(2), resultSet.getBigDecimal(3)));
				}
			}
		} catch (SQLException e) {
			throw new DataRequestException(Resourcer.getString("exceptions.sql.request"));
		}
		return result;
	}

	@Override
	public Collection<PersonInvestment> getAverageStockAmountInInvestments() throws DataRequestException {
		Collection<PersonInvestment> result = new ArrayList<PersonInvestment>();
		String query = Resourcer.getString("requests.sql.get.clients.averageStock");
		Statement statement = null;
		try {
			statement = this.connection.createStatement();
			boolean executeResult = statement.execute(query);
			if (executeResult) {
				ResultSet resultSet = statement.getResultSet();
				while (resultSet.next()) {
					result.add(new PersonInvestment(resultSet.getString(1), resultSet.getString(2), resultSet.getInt(3),
							resultSet.getBigDecimal(4),
							resultSet.getBigDecimal(5).setScale(2, BigDecimal.ROUND_HALF_UP)));
				}
			}
		} catch (SQLException e) {
			throw new DataRequestException(Resourcer.getString("exceptions.sql.request"));
		}
		return result;
	}
}
