package com.blackdeath.pagos.servicios;

import org.springframework.stereotype.Service;

import com.blackdeath.pagos.entidades.Pago;
import com.blackdeath.pagos.mapeadores.PagoMapper;
import com.blackdeath.pagos.modelos.PagoModel;
import com.blackdeath.pagos.repositorios.PagosRepository;

/**
 * {@link Service} para {@link Pago}
 * 
 * @author Seth Karim Luis Martínez
 * @since 2024-07-20
 */
@Service
public class PagosService {

	private final PagosRepository pagoRepository;
	private final PagoMapper pagoMapper;

	/**
	 * Constructor que inyecta las dependencias necesarias para este servicio
	 * 
	 * @param repository
	 * @param pagoMapper
	 */
	public PagosService(PagosRepository repository, PagoMapper pagoMapper) {
		this.pagoRepository = repository;
		this.pagoMapper = pagoMapper;
	}

	/**
	 * Guarda un {@link Pago}
	 * 
	 * @param pagoModel
	 * @return
	 */
	public PagoModel guardar(PagoModel pagoModel) {
		Pago pago = pagoMapper.toEntity(pagoModel);

		Pago pagoGuardado = pagoRepository.save(pago);

		return pagoMapper.toModel(pagoGuardado);
	}

}
