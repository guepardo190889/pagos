package com.blackdeath.pagos.entidades;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entidad que representa un pago
 * 
 * @author Seth Karim Luis Martínez
 * @since 2024-07-20
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "pagos", indexes = { @Index(name = "idx_pagos_id", columnList = "id") })
@Entity
public class Pago {

	/**
	 * Identificador único de este pago
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;

	/**
	 * Monto de este pago
	 */
	@Column(name = "monto", updatable = false, precision = 11, scale = 2, nullable = false)
	private BigDecimal monto;

	/**
	 * Concepto de este pago
	 */
	@Column(name = "concepto", updatable = false, length = 256, nullable = false)
	private String concepto;

	/**
	 * {@link Cuenta} desde la que realiza este pago
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_cuenta", updatable = false, nullable = false)
	private Cuenta cuenta;

	/**
	 * {@link Usuario} que realiza este pago
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_usuario", updatable = false, nullable = false)
	private Usuario usuario;

	/**
	 * {@link Destinatario} que recibe este pago
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_destinatario", updatable = false, nullable = false)
	private Destinatario destinatario;

	/**
	 * {@link EstatusPago} de este pago
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_estatus", nullable = false)
	private EstatusPago estatus;

	/**
	 * Fecha de creación de este pago
	 */
	@CreationTimestamp
	@Column(name = "fecha_creacion", updatable = false)
	private LocalDateTime fechaCreacion;

	/**
	 * Fecha de aplicación de este pago
	 */
	@Column(name = "fecha_aplicacion", columnDefinition = "datetime")
	private LocalDateTime fechaAplicacion;

}
