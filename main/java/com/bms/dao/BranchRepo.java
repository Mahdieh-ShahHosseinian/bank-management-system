package com.bms.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bms.model.Branch;

public interface BranchRepo extends JpaRepository<Branch, Integer> {

}