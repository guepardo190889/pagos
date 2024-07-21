package com.blackdeath.pagos.transacciones;

import org.springframework.stereotype.Component;

import com.blackdeath.pagos.entidades.Destinatario;
import com.blackdeath.pagos.entidades.EstatusPago;
import com.blackdeath.pagos.entidades.Pago;
import com.blackdeath.pagos.entidades.Usuario;
import com.blackdeath.pagos.repositorios.DestinatariosRepository;
import com.blackdeath.pagos.repositorios.EstatusPagosRepository;
import com.blackdeath.pagos.repositorios.PagosRepository;
import com.blackdeath.pagos.repositorios.UsuariosRepository;

/**
 * Transacciones para {@link Pago}
 * 
 * @author Seth Karim Luis Martínez
 * @since 2024-07-20
 */
@Component
public class PagosTransaction {

	private final PagosRepository pagosRepository;
	private final UsuariosRepository usuariosRepository;
	private final DestinatariosRepository destinatariosRepository;
	private final EstatusPagosRepository estatusPagosRepository;

	/**
	 * Constructor privado que inyecta todas las dependnecias necesarias para este
	 * servicio
	 * 
	 * @param pagosRepository
	 * @param usuariosRepository
	 * @param destinatariosRepository
	 * @param estatusPagosRepository
	 */
	public PagosTransaction(PagosRepository pagosRepository, UsuariosRepository usuariosRepository,
			DestinatariosRepository destinatariosRepository, EstatusPagosRepository estatusPagosRepository) {
		this.pagosRepository = pagosRepository;
		this.usuariosRepository = usuariosRepository;
		this.destinatariosRepository = destinatariosRepository;
		this.estatusPagosRepository = estatusPagosRepository;
	}

	/**
	 * Guarda un {@link Pago}
	 * 
	 * @param pago
	 * @return
	 */
	public Pago guardar(Pago pago) {
		EstatusPago estatusPago = estatusPagosRepository.findById(pago.getEstatus().getId())
				.orElseThrow(() -> new IllegalArgumentException("EstatusPago no encontrado"));
		pago.setEstatus(estatusPago);

		Usuario usuario = usuariosRepository.findById(pago.getUsuario().getId())
				.orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));
		pago.setUsuario(usuario);

		Destinatario destinatario = destinatariosRepository.findById(pago.getDestinatario().getId())
				.orElseThrow(() -> new IllegalArgumentException("Destinatario no encontrado"));
		pago.setDestinatario(destinatario);

		return pagosRepository.save(pago);
	}
}
