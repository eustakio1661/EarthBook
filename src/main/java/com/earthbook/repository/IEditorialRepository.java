package com.earthbook.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.earthbook.models.Editorial;

public interface IEditorialRepository extends JpaRepository<Editorial, Integer> {

}
