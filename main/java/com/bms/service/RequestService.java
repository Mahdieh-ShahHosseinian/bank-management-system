package com.bms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bms.dao.RequestRepo;
import com.bms.model.Request;

@Service
public class RequestService implements ServiceInterface<Request> {

	@Autowired
	private RequestRepo repo;

	@Override
	public List<Request> getAll() {
		return repo.findAll();
	}

	@Override
	public Request add(Request request) {
		return repo.save(request);
	}

	@Override
	public Request get(int id) {
		return repo.findById(id).orElse(null);
	}

	@Override
	public Request update(Request request, int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(int id) {
		repo.deleteById(id);
	}
}