package com.blackdeath.pagos.modelos;

import java.math.BigDecimal;

import com.blackdeath.pagos.entidades.Pago;
import com.blackdeath.pagos.enumeradores.EstatusPagoEnum;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * Modelo que permite el envío y recepción de datos relacionados a un
 * {@link Pago}
 * 
 * @author Seth Karim Luis Martínez
 * @since 2024-07-20
 */
@Data
public class PagoModel {

	/**
	 * Monto de este pago
	 */
	@Schema(description = "Monto de este pago", example = "100.00", requiredMode = RequiredMode.REQUIRED)
	@NotNull(message = "El monto no puede ser nulo")
	@DecimalMin(value = "0.0", inclusive = false, message = "El monto debe ser mayor que cero")
	@Digits(integer = 9, fraction = 2, message = "El monto debe tener un máximo de 9 dígitos enteros y 2 decimales")
	private BigDecimal monto;

	/**
	 * Concepto de este pago
	 */
	@Schema(description = "Concepto de este pago", example = "Pago por servicios", requiredMode = RequiredMode.REQUIRED)
	@NotNull(message = "El concepto no puede ser nulo")
	@Size(max = 256, message = "El concepto no puede tener más de 256 caracteres")
	private String concepto;

	/**
	 * Identificador único del usuario que realiza el pago
	 */
	@Schema(description = "Identificador único del usuario que realiza el pago", example = "1", requiredMode = RequiredMode.REQUIRED)
	@NotNull(message = "El identificador único del usuario no puede ser nulo")
	private Long idUsuario;

	/**
	 * Identificador único del destinatario del pago
	 */
	@Schema(description = "ID del destinatario del pago", example = "2", requiredMode = RequiredMode.REQUIRED)
	@NotNull(message = "El ID del destinatario no puede ser nulo")
	private Long idDestinatario;

	/**
	 * Estatus del pago
	 */
	@Schema(description = "Estatus del pago", example = "", requiredMode = RequiredMode.REQUIRED)
	@NotNull(message = "El estatus no puede ser nulo")
	private EstatusPagoEnum estatus;

}
