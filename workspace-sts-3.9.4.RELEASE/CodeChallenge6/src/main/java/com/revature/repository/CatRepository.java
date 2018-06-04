package com.revature.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.revature.beans.Cat;

@Repository
public interface CatRepository extends JpaRepository<Cat, Integer> {

	public Cat findCatById(int id);
}
