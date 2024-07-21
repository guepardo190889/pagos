package com.blackdeath.pagos.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blackdeath.pagos.entidades.Pago;

/**
 * {@link Repository} para {@link Pago}
 * 
 * @author Seth Karim Luis Martínez
 * @since 2024-07-20
 */
@Repository
public interface PagosRepository extends JpaRepository<Pago, Long> {

}
