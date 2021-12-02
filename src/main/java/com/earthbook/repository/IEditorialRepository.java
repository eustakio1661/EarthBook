package com.earthbook.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.earthbook.models.Editorial;

public interface IEditorialRepository extends JpaRepository<Editorial, Integer> {
	
	 @Query("select e from Editorial e where e.idEstado = 1")
	    public List<Editorial> findAllActive();

}
