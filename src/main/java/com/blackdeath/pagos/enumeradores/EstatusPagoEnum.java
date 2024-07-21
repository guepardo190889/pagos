package com.blackdeath.pagos.enumeradores;

import com.blackdeath.pagos.entidades.Pago;

/**
 * Enumerador de estatus de un {@link Pago}
 * 
 * @author Seth Karim Luis Martínez
 * @since 2024-07-20
 */
public enum EstatusPagoEnum {
	/**
	 * {@link Pago} generado y guardado para su procesamiento
	 */
	GENERADO(1),
	/**
	 * {@link Pago} procesado y enviado a su destinatario
	 */
	ENVIADO(2),
	/**
	 * {@link Pago} procesado pero no enviado. Esto se puede deber a algún error en
	 * el procesamiento por inclumplir alguna regla de negocio
	 */
	NO_EFECTUADO(3);

	/**
	 * Constructor privado
	 * 
	 * @param id
	 */
	private EstatusPagoEnum(int id) {

	}
}
