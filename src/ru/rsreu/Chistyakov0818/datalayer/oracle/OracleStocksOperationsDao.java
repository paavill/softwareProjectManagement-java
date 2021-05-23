package ru.rsreu.Chistyakov0818.datalayer.oracle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.prutzkow.resourcer.Resourcer;

import ru.rsreu.Chistyakov0818.datalayer.data.StockOperation;
import ru.rsreu.Chistyakov0818.datalayer.interfaces.StocksOperationsDao;
import ru.rsreu.Chistyakov0818.exceptions.DataRequestException;

public class OracleStocksOperationsDao implements StocksOperationsDao {
	private Connection connection;

	public OracleStocksOperationsDao(Connection connection) {
		this.connection = connection;
	}

	@Override
	public List<StockOperation> getStocksOperations() {

		return null;
	}

	@Override
	public Collection<StockOperation> getStocksOperationsRelativelyDate(Date date) throws DataRequestException {
		Collection<StockOperation> result = new ArrayList<StockOperation>();
		String query = Resourcer.getString("requests.sql.get.stocksOperations.beforeDate");
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setDate(1, date);
			boolean isExecuted = statement.execute();
			if (isExecuted) {
				ResultSet resultSet = statement.getResultSet();
				while (resultSet.next()) {
					result.add(new StockOperation(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
							resultSet.getBigDecimal(4), resultSet.getDate(5), resultSet.getString(6)));
				}
			}
		} catch (SQLException e) {
			throw new DataRequestException(Resourcer.getString("exceptions.sql.request"));
		}
		return result;
	}

}
