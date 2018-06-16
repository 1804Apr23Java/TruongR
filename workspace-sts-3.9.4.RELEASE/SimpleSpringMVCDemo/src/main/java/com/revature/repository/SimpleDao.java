package com.revature.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.beans.Simple;

@Repository
public interface SimpleDao extends JpaRepository<Simple, Integer> {

	public Simple findSimpleById(int id);
	
}
