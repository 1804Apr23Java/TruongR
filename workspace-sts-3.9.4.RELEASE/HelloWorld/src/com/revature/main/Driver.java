package com.revature.main;

import java.util.List;

import com.revature.dao.*;
import com.revature.domain.Cave;

public class Driver {

	public static void main(String[] args) {
		CaveDao cd = new CaveDaoImpl();

		//SPACE TOURISMMMMMMMMMMMM
		cd.addCave("TheMoon", 10000000);
		
		//cave in
		cd.updateCaveCapacity(10, 19);
		
		//need to change this to avoid getting sued by d1sn3y
		cd.updateCaveName(13, "Darsney Land");
		
		//homeowner's association made houseowner get rid of bears
		cd.deleteCave(14);

	
		//caves.stream().forEach(System.out::println);

	}

}
