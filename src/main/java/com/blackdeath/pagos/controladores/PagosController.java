package com.blackdeath.pagos.controladores;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blackdeath.pagos.entidades.Pago;
import com.blackdeath.pagos.modelos.ErrorModel;
import com.blackdeath.pagos.modelos.PagoActualizarModel;
import com.blackdeath.pagos.modelos.PagoGuardarModel;
import com.blackdeath.pagos.modelos.PagoModel;
import com.blackdeath.pagos.servicios.PagosService;
import com.blackdeath.pagos.utilerias.enumeradores.Entidad;
import com.blackdeath.pagos.utilerias.excepciones.NoEncontradoException;

import jakarta.validation.Valid;

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
	 * Busca un {@link Pago}
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/{id}")
	public ResponseEntity<?> buscar(@PathVariable Long id) {
		Optional<PagoModel> pagoModel = pagosService.buscar(id);

		if (pagoModel.isPresent()) {
			return ResponseEntity.ok(pagoModel.get());
		} else {
			ErrorModel error = new ErrorModel("No se encontró " + Entidad.PAGO.name() + " con id " + id);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
		}
	}

	/**
	 * Guarda un {@link Pago} mediante un {@link PagoGuardarModel}
	 * 
	 * @param pagoGuardarModel
	 * @return
	 */
	@PostMapping
	public PagoModel guardar(@Valid @RequestBody PagoGuardarModel pagoGuardarModel) {
		return pagosService.guardar(pagoGuardarModel);
	}

	/**
	 * Actualiza un @link{Pago}
	 * 
	 * @param id
	 * @param pagoActualizarModel
	 * 
	 * @return
	 */
	@PatchMapping("/{id}")
	public ResponseEntity<?> actualizar(@PathVariable Long id,
			@Valid @RequestBody PagoActualizarModel pagoActualizarModel) {
		try {
			PagoModel pagoActualizado = pagosService.actualizar(id, pagoActualizarModel);
			return ResponseEntity.ok(pagoActualizado);
		} catch (NoEncontradoException e) {
			ErrorModel error = new ErrorModel(e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
		}
	}

}
