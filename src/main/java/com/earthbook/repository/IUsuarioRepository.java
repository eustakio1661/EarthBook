package com.earthbook.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.earthbook.models.Usuario;

public interface IUsuarioRepository extends JpaRepository<Usuario, Integer> {
	
	Usuario findByCorreoAndClave(String correo, String clave);
}
