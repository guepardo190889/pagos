package com.blackdeath.pagos.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blackdeath.pagos.entidades.Usuario;

/**
 * {@link Repository} para {@link Usuario}
 * 
 * @author Seth Karim Luis Martínez
 * @since 2024-07-20
 */
public interface UsuariosRepository extends JpaRepository<Usuario, Long> {

}
