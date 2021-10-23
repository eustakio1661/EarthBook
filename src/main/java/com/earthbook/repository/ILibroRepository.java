package com.earthbook.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.earthbook.models.Libro;

public interface ILibroRepository extends JpaRepository<Libro, Integer> {

}
