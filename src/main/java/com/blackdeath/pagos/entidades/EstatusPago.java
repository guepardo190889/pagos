package com.blackdeath.pagos.entidades;

import com.blackdeath.pagos.enumeradores.EstatusPagoEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entidad que representa el estado de un {@link Pago}
 * 
 * @author Seth Karim Luis Martínez
 * @since 2024-07-20
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "estatus_pagos", indexes = { @Index(name = "idx_estatus_pagos_id", columnList = "id") })
public class EstatusPago {

	/**
	 * Identificador único de este estado de pago
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;

	/**
	 * Clave única de este estado de pago
	 */
	@Column(name = "clave", length = 16, updatable = false, nullable = false, unique = true)
	private String clave;

	/**
	 * Descripción de este estado de pago
	 */
	@Column(name = "descripcion", length = 128, nullable = false)
	private String descripcion;

	/**
	 * Constructor que permite crear un {@link EstatusPago} a partir de su
	 * enumerador equivalente {@link EstatusPagoEnum}
	 * 
	 * @param estatusPagoEnum
	 */
	public EstatusPago(EstatusPagoEnum estatusPagoEnum) {
		this.id = estatusPagoEnum.getId();
		this.clave = estatusPagoEnum.name();
	}
}
