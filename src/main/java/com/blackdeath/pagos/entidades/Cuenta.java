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
 * Entidad que representa una cuenta
 * 
 * @author Seth Karim Luis Martínez
 * @since 2024-07-20
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "cuentas", indexes = { @Index(name = "idx_cuenta_id", columnList = "id") })
@Entity
public class Cuenta {
	/**
	 * Identificador único de esta cuenta
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;

	/**
	 * Número de esta cuenta
	 */
	@Column(name = "numero", updatable = false, length = 12, nullable = false, unique = true)
	private String numero;

	/**
	 * CLABE interbancaria de esta cuenta
	 */
	@Column(name = "clabe", updatable = false, length = 18, nullable = false, unique = true)
	private String clabe;

	/**
	 * Saldo de esta cuenta
	 */
	@Column(name = "saldo", precision = 11, scale = 2, nullable = false)
	private BigDecimal saldo;

	/**
	 * {@link Usuario} al que pertenece esta cuenta
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_usuario", updatable = false, nullable = false)
	private Usuario usuario;

	/**
	 * Fecha de creación de esta cuenta
	 */
	@CreationTimestamp
	@Column(name = "fecha_creacion", updatable = false)
	private LocalDateTime fechaCreacion;

	/**
	 * Fecha de activación de esta cuen ta
	 */
	@Column(name = "fecha_activacion", columnDefinition = "datetime")
	private LocalDateTime fechaActivacion;

}
