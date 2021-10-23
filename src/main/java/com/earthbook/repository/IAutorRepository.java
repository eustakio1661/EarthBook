package com.earthbook.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.earthbook.models.Autor;

public interface IAutorRepository extends JpaRepository<Autor, Integer>{

}
