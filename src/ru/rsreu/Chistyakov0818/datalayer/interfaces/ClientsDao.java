package ru.rsreu.Chistyakov0818.datalayer.interfaces;

import java.util.Collection;

import ru.rsreu.Chistyakov0818.datalayer.data.Client;
import ru.rsreu.Chistyakov0818.exceptions.DataRequestException;

public interface ClientsDao {
	Collection<Client> getClientsBoughtStock(int index) throws DataRequestException;
}
