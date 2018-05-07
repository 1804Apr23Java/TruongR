package com.revature.dao;

import java.util.List;

import com.revature.domain.*;

public interface AdminDao {
	
	public BankClient getBankClient(int bankClientID);
	public List<BankClient> getAllBankClients();
	public void updateBankClientUsername(int bankClientID, String newUsername) throws UsernameAlreadyUsedException;
	public void updateBankClientPassword(int bankClientID, String newPassword);
	public void createBankClient(String username, String password) throws UsernameAlreadyUsedException;
	public void deleteBankClient(int bankClientID);
	
}
