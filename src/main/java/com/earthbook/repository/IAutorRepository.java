package com.earthbook.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.earthbook.models.Autor;

public interface IAutorRepository extends JpaRepository<Autor, Integer>{
	
    @Query("select a from Autor a where a.idEstado = 1")
    public List<Autor> findAllActive();

}
