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
 * Entidad que representa un usuario registrado que puede realizar un
 * {@link Pago} a un {@link Destinatario}
 * 
 * @author Seth Karim Luis Martínez
 * @since 2024-07-20
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "usuarios", indexes = { @Index(name = "idx_usuarios_id", columnList = "id") })
public class Usuario {

	/**
	 * Identificador único de este destinatario
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;

	/**
	 * Nombre(s) de este usuario
	 */
	@Column(name = "nombre", length = 128, nullable = false)
	private String nombre;

	/**
	 * Apellido paterno de este usario
	 */
	@Column(name = "apellido_paterno", length = 128)
	private String apellidoPaterno;

	/**
	 * Apellido materno de este usuario
	 */
	@Column(name = "apellido_materno", length = 128)
	private String apellidoMaterno;

	/**
	 * Registro Federal de Contribuyentes (RFC) de este usuario
	 */
	@Column(name = "rfc", length = 13, nullable = false, unique = true)
	private String rfc;

	/**
	 * Fecha de creaciòn de este usuario
	 */
	@CreationTimestamp
	@Column(name = "fecha_creacion", updatable = false)
	private LocalDateTime fechaCreacion;
}
