package com.rangel.ibsc.service;

import java.util.List;

import com.rangel.ibsc.model.TransactionHistory;
import org.springframework.stereotype.Service;


@Service
public interface TransactionHistoryService {
	
	TransactionHistory createTransaction(TransactionHistory transaction);
	
	TransactionHistory updateTransaction(TransactionHistory transaction);
	
	List<TransactionHistory> getAllTransactionHistory();
	
	List<TransactionHistory> getAllTransactionHistoryByClientId(long clientId);
	
	List<TransactionHistory> getAllTransactionHistoryByClientIdAndType(long clientId, String type);
	
	TransactionHistory getTransactionHistoryById(long transactionId);
	
	void deleteTransactionHistory(long id);
	

}
