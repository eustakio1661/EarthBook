package com.earthbook.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.earthbook.models.Categoria;

public interface ICategoriaRepository extends JpaRepository<Categoria, Integer>{
	
	/*public List<Categoria> findById(int id);*/

}
