package com.blackdeath.pagos.entidades;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.hibernate.annotations.Comment;
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
	@Comment("Identificador único de la cuenta")
	private Long id;

	/**
	 * Número de esta cuenta
	 */
	@Column(name = "numero", updatable = false, length = 12, nullable = false, unique = true)
	@Comment("Número de la cuenta")
	private String numero;

	/**
	 * CLABE interbancaria de esta cuenta
	 */
	@Column(name = "clabe", updatable = false, length = 18, nullable = false, unique = true)
	@Comment("CLABE interbancaria de la cuenta")
	private String clabe;

	/**
	 * Saldo de esta cuenta
	 */
	@Column(name = "saldo", precision = 11, scale = 2, nullable = false)
	@Comment("Saldo de la cuenta")
	private BigDecimal saldo;

	/**
	 * {@link Usuario} al que pertenece esta cuenta
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_usuario", updatable = false, nullable = false)
	@Comment("Llave foránea que hace referencia al Usuario al que pertenece esta cuenta")
	private Usuario usuario;

	/**
	 * Fecha de creación de esta cuenta
	 */
	@CreationTimestamp
	@Column(name = "fecha_creacion", updatable = false)
	@Comment("Fecha en que se creó la cuenta")
	private LocalDateTime fechaCreacion;

	/**
	 * Fecha de activación de esta cuen ta
	 */
	@Column(name = "fecha_activacion", columnDefinition = "datetime")
	@Comment("Fecha en que se activó la cuenta")
	private LocalDateTime fechaActivacion;

}
