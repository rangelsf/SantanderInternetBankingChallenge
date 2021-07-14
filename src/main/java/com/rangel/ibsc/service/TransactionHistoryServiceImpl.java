package com.rangel.ibsc.service;

import java.util.List;

import java.util.Optional;

import javax.persistence.SequenceGenerator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rangel.ibsc.exceptions.TransactionHistoryNotFoundException;
import com.rangel.ibsc.model.TransactionHistory;
import com.rangel.ibsc.repository.TransactionHistoryRepository;

@Service
@Transactional
public class TransactionHistoryServiceImpl implements TransactionHistoryService {

	@Autowired
	private TransactionHistoryRepository transactionRepository;

	@Override
	public TransactionHistory createTransaction(TransactionHistory transaction) {
		return transactionRepository.save(transaction);
	}

	@Override
	public TransactionHistory updateTransaction(TransactionHistory transaction) {
		Optional<TransactionHistory> transactionDb = this.transactionRepository.findById(transaction.getId());

		if (transactionDb.isPresent()) {
			TransactionHistory transactionUpdate = transactionDb.get();
			transactionUpdate.setId(transaction.getId());
			transactionUpdate.setOldBalance(transaction.getOldBalance());
			transactionUpdate.setNewBalance(transaction.getNewBalance());
			transactionUpdate.setType(transaction.getType());

			transactionRepository.save(transactionUpdate);

			return transactionUpdate;
		} else {
			throw new TransactionHistoryNotFoundException("Cannot update transaction with id: " + transaction.getId());
		}

	}

	@Override
	public List<TransactionHistory> getAllTransactionHistory() {
		return this.transactionRepository.findAll();
	}

	@Override
	public TransactionHistory getTransactionHistoryById(long transactiontId) {
		Optional<TransactionHistory> transactionDb = this.transactionRepository.findById(transactiontId);

		if (transactionDb.isPresent()) {
			return transactionDb.get();
		} else {
			throw new TransactionHistoryNotFoundException("Cannot get transaction with id: " + transactiontId);
		}

	}

	@Override
	public void deleteTransactionHistory(long id) {
		Optional<TransactionHistory> transactionDb = this.transactionRepository.findById(id);

		if (transactionDb.isPresent()) {
			this.transactionRepository.delete(transactionDb.get());
		} else {
			throw new TransactionHistoryNotFoundException("Cannot delete transaction with id: " + id);
		}

	}

	@Override
	public List<TransactionHistory> getAllTransactionHistoryByClientId(long clientId) {
		try {
			return this.transactionRepository.getAllTransactionHistoryByClientId(clientId);
		} catch(Exception e) {
			throw new TransactionHistoryNotFoundException("Cannot find transactions with Client id: " + clientId);
		}	
	}
	
	
	@Override
	public List<TransactionHistory> getAllTransactionHistoryByClientIdAndType(long clientId, String type) {
		try {
			return this.transactionRepository.getAllTransactionHistoryByClientIdAndType(clientId, type);
		} catch(Exception e) {
			String messageType;
			switch(type) {
			case "D":
				messageType = "deposit";
				break;
			case "W":
				messageType = "withdrawal";
			break;
			default:
				messageType = "invalid type";
				break;
			}
			throw new TransactionHistoryNotFoundException("Cannot find transactions of type '"+messageType+"' with Client id: " + clientId);
		}
	}
	
	
	

}
