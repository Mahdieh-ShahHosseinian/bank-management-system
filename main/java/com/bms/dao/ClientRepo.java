package com.bms.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bms.model.Client;

public interface ClientRepo extends JpaRepository<Client, Integer> {

}
