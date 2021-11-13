package com.earthbook.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.earthbook.models.Libro;

public interface ILibroRepository extends JpaRepository<Libro, Integer> {
	
	public List<Libro> findByIdCategoria(int idCategoria);

}
