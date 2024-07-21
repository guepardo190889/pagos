package com.blackdeath.pagos.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blackdeath.pagos.entidades.Pago;
import com.blackdeath.pagos.modelos.PagoModel;
import com.blackdeath.pagos.servicios.PagosService;

/**
 * {@link Controller} para {@link Pago}
 * 
 * @author Seth Karim Luis Martínez
 * @since 2024-07-20
 */
@RestController
@RequestMapping("/api/pagos")
public class PagosController {

	private final PagosService pagosService;

	/**
	 * Constructor que inyecta las dependencias necesarias para este controlador
	 * 
	 * @param pagosService
	 */
	public PagosController(PagosService pagosService) {
		this.pagosService = pagosService;
	}

	/**
	 * Guarda un {@link Pago} mediante un {@link PagoModel}
	 * 
	 * @param pago
	 * @return
	 */
	@PostMapping
	public PagoModel guardar(PagoModel pago) {
		return pagosService.guardar(pago);
	}

}
