package com.blackdeath.pagos.controladores;

import java.net.URI;
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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.blackdeath.pagos.entidades.Pago;
import com.blackdeath.pagos.modelos.ErrorModel;
import com.blackdeath.pagos.modelos.PagoActualizarModel;
import com.blackdeath.pagos.modelos.PagoGuardarModel;
import com.blackdeath.pagos.modelos.PagoModel;
import com.blackdeath.pagos.servicios.PagosService;
import com.blackdeath.pagos.utilerias.enumeradores.Entidad;
import com.blackdeath.pagos.utilerias.excepciones.NoEncontradoException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
	@Operation(summary = "Busca un pago por su id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Pago encontrado", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = PagoModel.class)) }),
			@ApiResponse(responseCode = "404", description = "Pago no encontrado", content = @Content) })
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
	@Operation(summary = "Guarda un pago")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Pago creado", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = PagoModel.class)) }),
			@ApiResponse(responseCode = "400", description = "Solicitud inválida", content = @Content) })
	@PostMapping
	public ResponseEntity<PagoModel> guardar(@Valid @RequestBody PagoGuardarModel pagoGuardarModel) {
		PagoModel pagoGuardado = pagosService.guardar(pagoGuardarModel);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(pagoGuardado.getId()).toUri();

		return ResponseEntity.created(location).body(pagoGuardado);
	}

	/**
	 * Actualiza un {@link Pago}
	 * 
	 * @param id
	 * @param pagoActualizarModel
	 * 
	 * @return
	 */
	@Operation(summary = "Actualiza el estatus de un pago")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Estatus del pago actualizado", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = PagoModel.class)) }),
			@ApiResponse(responseCode = "404", description = "Pago no encontrado", content = @Content) })
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
