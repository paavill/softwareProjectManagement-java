package ru.rsreu.Chistyakov0818.datalayer.oracle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import com.prutzkow.resourcer.Resourcer;

import ru.rsreu.Chistyakov0818.datalayer.data.Client;
import ru.rsreu.Chistyakov0818.datalayer.interfaces.ClientsDao;
import ru.rsreu.Chistyakov0818.exceptions.DataRequestException;

public class OracleClientDao implements ClientsDao {
	private Connection connection;

	public OracleClientDao(Connection connection) {
		this.connection = connection;
	}

	@Override
	public Collection<Client> getClientsBoughtStock(int index) throws DataRequestException {
		Collection<Client> result = new ArrayList<Client>();
		String query = Resourcer.getString("requests.sql.get.clients.boughtStoks");
		PreparedStatement statement;
		try {
			statement = this.connection.prepareStatement(query);
			statement.setInt(1, index);
			boolean isExecuted = statement.execute();
			if (isExecuted) {
				ResultSet resultSet = statement.getResultSet();
				while (resultSet.next()) {
					result.add(new Client(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
							resultSet.getString(4), resultSet.getString(5)));
				}
			}
		} catch (SQLException e) {
			throw new DataRequestException(Resourcer.getString("exceptions.sql.request"));
		}
		return result;
	}

}
