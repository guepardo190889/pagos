package com.blackdeath.pagos.utilerias.excepciones;

import com.blackdeath.pagos.utilerias.enumeradores.Entidad;

/**
 * Excepción genérica que debe usarse cuando no se encuentra algún registro
 */
public class NoEncontradoException extends RuntimeException {

	private static final long serialVersionUID = 802836452003156749L;

	/**
	 * Constructor que permite asignar un mensaje personalizado a esta excepción
	 * 
	 * @param mensaje
	 */
	public NoEncontradoException(String mensaje) {
		super(mensaje);
	}

	/**
	 * Constructor que permite asignar un mensaje personalizado a esta excepción
	 * indicando la {@link Entidad} y su id
	 * 
	 * @param id
	 * @param entidad
	 */
	public NoEncontradoException(Long id, Entidad entidad) {
		super("No se encontró " + entidad.getTexto() + " con id " + id);
	}

}
