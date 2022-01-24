package com.bms.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bms.model.Request;

public interface RequestRepo extends JpaRepository<Request, Integer> {

}
