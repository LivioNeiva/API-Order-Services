package com.livioneiva.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.livioneiva.model.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

	
}
