package com.blackdeath.pagos.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blackdeath.pagos.entidades.EstatusPago;

/**
 * {@link Repository} para {@link EstatusPago}
 * 
 * @author Seth Karim Luis Martínez
 * @since 2024-07-20
 */
public interface EstatusPagosRepository extends JpaRepository<EstatusPago, Long> {

}
