package com.blackdeath.pagos.modelos;

import java.math.BigDecimal;

import com.blackdeath.pagos.entidades.Pago;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Modelo que permite guardar un {@link Pago}
 * 
 * @author Seth Karim Luis Martínez
 * @since 2024-07-20
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PagoGuardarModel {

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
	 * Identificador único de la cuenta desde la que se realiza el pago
	 */
	@Schema(description = "Identificador único de la cuenta desde la que se realiza el pago", example = "1", requiredMode = RequiredMode.REQUIRED)
	@NotNull(message = "Identificador único de la cuenta desde la que se realiza el pago")
	private Long idCuenta;

	/**
	 * Identificador único del usuario que realiza el pago
	 */
	@Schema(description = "Identificador único del usuario que realiza el pago", example = "1", requiredMode = RequiredMode.REQUIRED)
	@NotNull(message = "El identificador único del usuario no puede ser nulo")
	private Long idUsuario;

	/**
	 * Identificador único del destinatario del pago
	 */
	@Schema(description = "Identifiacdor único del destinatario del pago", example = "1", requiredMode = RequiredMode.REQUIRED)
	@NotNull(message = "Identificador único del destinatario no puede ser nulo")
	private Long idDestinatario;

}
