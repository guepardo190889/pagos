package com.blackdeath.pagos.enumeradores;

import com.blackdeath.pagos.entidades.Pago;

import lombok.Getter;

/**
 * Enumerador de estatus de un {@link Pago}
 * 
 * @author Seth Karim Luis Martínez
 * @since 2024-07-20
 */
@Getter
public enum EstatusPagoEnum {
	/**
	 * {@link Pago} generado y guardado para su procesamiento.
	 */
	GENERADO(1),
	/**
	 * {@link Pago} procesado y pagado a su destinatario.
	 * <p>
	 * Este es un estatus final y no debería ser modificado.
	 * </p>
	 */
	PAGADO(2),
	/**
	 * {@link Pago} procesado pero no pagado al destinatario.
	 * <p>
	 * Esto se puede deber a algún error en el procesamiento por inclumplir alguna
	 * regla de negocio. Este es un estatus final y no debería ser modificado.
	 * </p>
	 */
	NO_PAGADO(3);

	/**
	 * Identificador único usado en la entidad {@link Pago}
	 */
	private long id;

	/**
	 * Constructor privado
	 * 
	 * @param id
	 */
	private EstatusPagoEnum(long id) {
		this.id = id;
	}
}
