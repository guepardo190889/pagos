package com.blackdeath.pagos.servicios;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.blackdeath.pagos.entidades.EstatusPago;
import com.blackdeath.pagos.entidades.Pago;
import com.blackdeath.pagos.enumeradores.EstatusPagoEnum;
import com.blackdeath.pagos.mapeadores.PagoMapper;
import com.blackdeath.pagos.modelos.PagoActualizarModel;
import com.blackdeath.pagos.modelos.PagoGuardarModel;
import com.blackdeath.pagos.modelos.PagoModel;
import com.blackdeath.pagos.transacciones.PagosTransaction;

/**
 * {@link Service} para {@link Pago}
 * 
 * @author Seth Karim Luis Martínez
 * @since 2024-07-20
 */
@Service
public class PagosService {

	private final PagosTransaction pagosTransaction;
	private final PagoMapper pagoMapper;

	/**
	 * Constructor que inyecta las dependencias necesarias para este servicio
	 * 
	 * @param pagosTransaction
	 * @param pagoMapper
	 */
	public PagosService(PagosTransaction pagosTransaction, PagoMapper pagoMapper) {
		this.pagosTransaction = pagosTransaction;
		this.pagoMapper = pagoMapper;
	}

	/**
	 * Busca un {@link Pago}
	 * 
	 * @param id
	 * @return
	 */
	public Optional<PagoModel> buscar(Long id) {
		Optional<Pago> pago = pagosTransaction.buscarPorId(id);

		return pago.map(pagoMapper::toModel);
	}

	/**
	 * Guarda un {@link Pago}
	 * 
	 * @param pagoGuadarModel
	 * @return
	 */
	public PagoModel guardar(PagoGuardarModel pagoGuadarModel) {
		Pago pago = pagoMapper.toEntity(pagoGuadarModel);
		pago.setEstatus(new EstatusPago(EstatusPagoEnum.GENERADO));

		// TODO Validar si la cuenta tiene saldo disponible para efectuar el pago

		Pago pagoGuardado = pagosTransaction.guardar(pago);

		return pagoMapper.toModel(pagoGuardado);
	}

	/**
	 * Actualiza un {@link Pago}
	 * 
	 * @param id
	 * @param pagoActualizarModel
	 * @return
	 */
	public PagoModel actualizar(Long id, PagoActualizarModel pagoActualizarModel) {
		Pago pago = pagoMapper.toEntity(pagoActualizarModel);
		pago.setId(id);
		pago.setFechaAplicacion(LocalDateTime.now());

		// TODO que no se pueda actualizar el estatus a alguno "anterior" o a algún otro
		// estatus si ya está en alguno "final"

		Pago pagoActualizado = pagosTransaction.actualizar(pago);

		return pagoMapper.toModel(pagoActualizado);
	}

}
