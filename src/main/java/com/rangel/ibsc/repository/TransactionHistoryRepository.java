package com.rangel.ibsc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.rangel.ibsc.model.TransactionHistory;

public interface TransactionHistoryRepository extends JpaRepository<TransactionHistory, Long> {
	
	@Query(value = "select * from transactions where client_id = :clientId order by updated_at desc", nativeQuery = true)
	List<TransactionHistory> getAllTransactionHistoryByClientId(@Param("clientId") long clientId);
	
	@Query(value = "select * from transactions where client_id = :clientId and type = :type order by updated_at desc", nativeQuery = true)
	List<TransactionHistory> getAllTransactionHistoryByClientIdAndType(@Param("clientId") long clientId, @Param("type") String type);
	
}
