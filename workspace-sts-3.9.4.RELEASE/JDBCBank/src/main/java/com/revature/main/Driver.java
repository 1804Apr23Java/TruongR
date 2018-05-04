package com.revature.main;

import java.util.List;

import com.revature.dao.*;
import com.revature.domain.Cave;

public class Driver {
	
	public static void main(String[] args) {
		CaveDao cd = new CaveDaoImpl();
			
		List<Cave> caves = cd.getCaves();

		//caves.stream().forEach(System.out::println);
		
		//Cave c = cd.getCaveById(2);
		//System.out.println(c);
		
		//cd.addCave("BadCaveNotAwesome", 68);

		caves.stream().forEach(System.out::println);
		
		//cd.updateCaveCapacity(6, 43);
		
		//cd.updateCaveName(5, "slightlylessterriblecave");
		
		cd.deleteCave(7);
		
		
	}
	
}
