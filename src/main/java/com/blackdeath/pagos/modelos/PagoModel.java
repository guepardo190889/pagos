package com.blackdeath.pagos.modelos;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.blackdeath.pagos.entidades.EstatusPago;
import com.blackdeath.pagos.entidades.Pago;
import com.blackdeath.pagos.enumeradores.EstatusPagoEnum;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Modelo que contiene los datos que se pueden exponer de un {@link Pago}
 * 
 * @author Seth Karim Luis Martínez
 * @since 2024-07-20
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PagoModel {

	/**
	 * Identificador único de este pago
	 */
	@Schema(description = "Identificador único de este pago")
	private Long id;

	/**
	 * Monto de este pago
	 */
	@Schema(description = "Monto de este pago")
	private BigDecimal monto;

	/**
	 * Concepto de este pago
	 */
	@Schema(description = "Concepto de este pago")
	private String concepto;

	/**
	 * Identificador único de la cuenta desde la que se realiza este pago
	 */
	@Schema(description = "Identificador único de la cuenta desde la que se realiza este pago")
	private Long idCuenta;

	/**
	 * Identifiacdor único del usuario que realiza este pago
	 */
	@Schema(description = "Identifiacdor único del usuario que realiza este pago")
	private Long idUsuario;

	/**
	 * Identificador único del destinatario de este pago
	 */
	@Schema(description = "Identificador único del destinatario de este pago")
	private Long idDestinatario;

	/**
	 * {@link EstatusPago} de este pago
	 */
	@Schema(description = "Estatus de este pago")
	private EstatusPagoEnum estatus;

	/**
	 * Fecha de creación de este pago
	 */
	@Schema(description = "Fecha de creación de este pago")
	private LocalDateTime fechaCreacion;

	/**
	 * Fecha de aplicación de este pago
	 */
	@Schema(description = "Fecha de aplicación de este pago")
	private LocalDateTime fechaAplicacion;
}
