package com.earthbook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.earthbook.models.Categoria;

public interface ICategoriaRepository extends JpaRepository<Categoria, Integer>{
	
	/*public List<Categoria> findById(int id);*/

	 @Query("select c from Categoria c where c.idEstado = 1")
	    public List<Categoria> findAllActive();
}
