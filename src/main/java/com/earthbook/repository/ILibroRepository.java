package com.earthbook.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.earthbook.models.Libro;

public interface ILibroRepository extends JpaRepository<Libro, Integer> {
	
	public List<Libro> findByIdCategoria(int idCategoria);
	public List<Libro> findByIdAutor(int idAutor);
	public List<Libro> findByIdEditorial(int idEditorial);
    @Query("select l from Libro l where l.idEstado = 1")
    public List<Libro> findAllActive();
    
}
