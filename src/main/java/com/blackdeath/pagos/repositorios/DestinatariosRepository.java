package com.blackdeath.pagos.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blackdeath.pagos.entidades.Destinatario;

/**
 * {@link Repository} para {@link Destinatario}
 * 
 * @author Seth Karim Luis Martínez
 * @since 2024-07-20
 */
public interface DestinatariosRepository extends JpaRepository<Destinatario, Long> {

}
