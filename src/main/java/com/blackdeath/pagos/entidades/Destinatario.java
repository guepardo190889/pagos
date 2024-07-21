package com.blackdeath.pagos.entidades;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

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
 * Entidad que representa un destinatario externo a quien es posible realizarle
 * un {@link Pago}
 * 
 * @author Seth Karim Luis Martínez
 * @since 2024-07-20
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "destinatarios", indexes = { @Index(name = "idx_destinatarios_id", columnList = "id") })
public class Destinatario {

	/**
	 * Identificador único de este destinatario
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;

	/**
	 * Nombre(s) de este destinatario
	 */
	@Column(name = "nombre", length = 128, nullable = false)
	private String nombre;

	/**
	 * Apellido paterno de este destinatario
	 */
	@Column(name = "apellido_paterno", length = 128)
	private String apellidoPaterno;

	/**
	 * Apellido materno de este destintario
	 */
	@Column(name = "apellido_materno", length = 128)
	private String apellidoMaterno;

	/**
	 * Registro Federal de Contribuyentes (RFC) de este destinatario
	 */
	@Column(name = "rfc", length = 13, nullable = false, unique = true)
	private String rfc;

	/**
	 * Fecha de creación de este destintario
	 */
	@CreationTimestamp
	@Column(name = "fecha_creacion", updatable = false)
	private LocalDateTime fechaCreacion;
}
