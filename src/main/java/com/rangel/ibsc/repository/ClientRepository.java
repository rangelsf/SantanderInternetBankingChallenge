package com.rangel.ibsc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rangel.ibsc.model.Client;


public interface ClientRepository extends JpaRepository<Client, Long> {

}
