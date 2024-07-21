package com.blackdeath.pagos.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blackdeath.pagos.entidades.Cuenta;

/**
 * {@link Repository} para {@link Cuenta}
 * 
 * @author Seth Karim Luis Martínez
 * @since 2024-07-20
 * 
 */
public interface CuentasRepository extends JpaRepository<Cuenta, Long> {

}
