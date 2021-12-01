package com.earthbook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.earthbook.models.Contactos;

@Repository
public interface IContactosRepository extends JpaRepository<Contactos, Integer> {
	
}
