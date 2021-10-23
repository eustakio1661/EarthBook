package com.earthbook.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.earthbook.models.Categoria;

public interface ICategoriaRepository extends JpaRepository<Categoria, Integer>{

}
