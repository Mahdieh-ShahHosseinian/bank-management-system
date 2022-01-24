package com.bms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bms.dao.BranchRepo;
import com.bms.model.Branch;

@Service
public class BranchService implements ServiceInterface<Branch> {

	@Autowired
	private BranchRepo repo;

	@Override
	public List<Branch> getAll() {
		return repo.findAll();
	}

	@Override
	public Branch add(Branch branch) {
		return repo.save(branch);
	}

	@Override
	public Branch get(int id) {
		return repo.findById(id).orElse(null);
	}

	@Override
	public Branch update(Branch branch, int id) {
		Branch updatedBranch = get(id);
		updatedBranch.setBranch_name(branch.getBranch_name());
		updatedBranch.setLocation(branch.getLocation());
		updatedBranch.setEmployee(branch.getEmployee());
		return add(updatedBranch);
	}

	@Override
	public void delete(int id) {
		repo.deleteById(id);
	}
}
