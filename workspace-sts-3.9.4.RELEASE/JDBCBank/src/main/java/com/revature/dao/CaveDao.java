package com.revature.dao;
import java.util.List;

import com.revature.domain.*;

public interface CaveDao {

	public List<Cave> getCaves();
	
	public Cave getCaveById(int id);
	
	public void addCave(String name, int maxBears);
	
	public void updateCaveCapacity(int id, int maxBears);

	public void updateCaveName(int id, String name);
	
	public void deleteCave(int id);
}
