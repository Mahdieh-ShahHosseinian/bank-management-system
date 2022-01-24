package com.bms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bms.dao.ClientRepo;
import com.bms.model.Client;

@Service
public class ClientService implements ServiceInterface<Client> {

	@Autowired
	private ClientRepo repo;

	@Override
	public List<Client> getAll() {
		return repo.findAll();
	}

	@Override
	public Client add(Client client) {
		return repo.save(client);
	}

	@Override
	public Client get(int id) {
		return repo.findById(id).orElse(null);
	}

	@Override
	public Client update(Client client, int id) {
		Client updatedClient = get(id);
		updatedClient.setBranch(client.getBranch());
		updatedClient.setDOB(client.getDOB());
		updatedClient.setPhone(client.getPhone());
		updatedClient.setCity(client.getCity());
		updatedClient.setStreet(client.getStreet());
		updatedClient.setPin(client.getPin());
		return add(updatedClient);
	}

	@Override
	public void delete(int id) {
		repo.deleteById(id);
	}
}
