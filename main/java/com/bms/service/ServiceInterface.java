package com.bms.service;

import java.util.List;

public interface ServiceInterface<T> {

	public List<T> getAll();
	
	public T add(T object);
	
	public T get(int id);
	
	public T update(T object, int id);
	
	public void delete(int id);
}
