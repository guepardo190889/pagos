package com.blackdeath.pagos.servicios;

import org.springframework.stereotype.Service;

import com.blackdeath.pagos.entidades.EstatusPago;
import com.blackdeath.pagos.entidades.Pago;
import com.blackdeath.pagos.enumeradores.EstatusPagoEnum;
import com.blackdeath.pagos.mapeadores.PagoMapper;
import com.blackdeath.pagos.modelos.PagoGuardarModel;
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
	 * Guarda un {@link Pago}
	 * 
	 * @param pagoGuadarModel
	 * @return
	 */
	public PagoGuardarModel guardar(PagoGuardarModel pagoGuadarModel) {
		Pago pago = pagoMapper.toEntity(pagoGuadarModel);
		pago.setEstatus(new EstatusPago(EstatusPagoEnum.GENERADO));

		Pago pagoGuardado = pagosTransaction.guardar(pago);

		return pagoMapper.toModel(pagoGuardado);
	}

}
