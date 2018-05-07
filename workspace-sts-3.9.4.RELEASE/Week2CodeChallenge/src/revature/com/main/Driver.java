package revature.com.main;

import revature.com.dao.*;

public class Driver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		CodeChallengeDao ccd = new CodeChallengeDaoImpl();
		
		System.out.println("Before raises:");
		
		ccd.getDeptAvgSalary();
		
		ccd.giveDeptWideRaise(1);
		ccd.giveDeptWideRaise(2);
		ccd.giveDeptWideRaise(3);

		System.out.print("After raises:");
		
		ccd.getDeptAvgSalary();
	}

}
