package com.blackdeath.pagos.modelos;

import com.blackdeath.pagos.entidades.EstatusPago;
import com.blackdeath.pagos.entidades.Pago;
import com.blackdeath.pagos.enumeradores.EstatusPagoEnum;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Modelo que permite actualizar un {@link Pago}
 * 
 * @author Seth Karim Luis Martínez
 * @since 2024-07-20
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PagoActualizarModel {

	/**
	 * {@link EstatusPago} de este pago
	 */
	@Schema(description = "Estatus de este pago")
	@NotNull
	private EstatusPagoEnum estatus;

}
