package com.blackdeath.pagos.transacciones;

import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.blackdeath.pagos.entidades.Cuenta;
import com.blackdeath.pagos.entidades.Destinatario;
import com.blackdeath.pagos.entidades.EstatusPago;
import com.blackdeath.pagos.entidades.Pago;
import com.blackdeath.pagos.entidades.Usuario;
import com.blackdeath.pagos.repositorios.CuentasRepository;
import com.blackdeath.pagos.repositorios.DestinatariosRepository;
import com.blackdeath.pagos.repositorios.EstatusPagosRepository;
import com.blackdeath.pagos.repositorios.PagosRepository;
import com.blackdeath.pagos.repositorios.UsuariosRepository;
import com.blackdeath.pagos.utilerias.enumeradores.Entidad;
import com.blackdeath.pagos.utilerias.excepciones.NoEncontradoException;

/**
 * Transacciones para {@link Pago}
 * 
 * @author Seth Karim Luis Martínez
 * @since 2024-07-20
 */
@Transactional
@Component
public class PagosTransaction {

	private final PagosRepository pagosRepository;
	private final CuentasRepository cuentasRepository;
	private final UsuariosRepository usuariosRepository;
	private final DestinatariosRepository destinatariosRepository;
	private final EstatusPagosRepository estatusPagosRepository;

	/**
	 * Constructor privado que inyecta todas las dependnecias necesarias para este
	 * servicio
	 * 
	 * @param pagosRepository
	 * @param cuentasRepository;
	 * @param usuariosRepository
	 * @param destinatariosRepository
	 * @param estatusPagosRepository
	 */
	public PagosTransaction(PagosRepository pagosRepository, CuentasRepository cuentasRepository,
			UsuariosRepository usuariosRepository, DestinatariosRepository destinatariosRepository,
			EstatusPagosRepository estatusPagosRepository) {
		this.pagosRepository = pagosRepository;
		this.cuentasRepository = cuentasRepository;
		this.usuariosRepository = usuariosRepository;
		this.destinatariosRepository = destinatariosRepository;
		this.estatusPagosRepository = estatusPagosRepository;
	}

	/**
	 * Busca un {@link Pago} por su identificador único
	 * 
	 * @param id
	 * @return
	 */
	public Optional<Pago> buscarPorId(Long id) {
		return pagosRepository.findById(id);
	}

	/**
	 * Guarda un {@link Pago}
	 * 
	 * @param pago
	 * @return
	 */
	public Pago guardar(Pago pago) {
		EstatusPago estatusPago = estatusPagosRepository.findById(pago.getEstatus().getId())
				.orElseThrow(() -> new NoEncontradoException(pago.getEstatus().getId(), Entidad.ESTATUS_PAGO));
		pago.setEstatus(estatusPago);

		Cuenta cuenta = cuentasRepository.findById(pago.getCuenta().getId())
				.orElseThrow(() -> new NoEncontradoException(pago.getCuenta().getId(), Entidad.CUENTA));
		pago.setCuenta(cuenta);

		Usuario usuario = usuariosRepository.findById(pago.getUsuario().getId())
				.orElseThrow(() -> new NoEncontradoException(pago.getUsuario().getId(), Entidad.USUARIO));
		pago.setUsuario(usuario);

		Destinatario destinatario = destinatariosRepository.findById(pago.getDestinatario().getId())
				.orElseThrow(() -> new NoEncontradoException(pago.getDestinatario().getId(), Entidad.DESTINATARIO));
		pago.setDestinatario(destinatario);

		return pagosRepository.save(pago);
	}

	/**
	 * Actualiza un {@link Pago}
	 * 
	 * @param pago
	 * @return
	 */
	public Pago actualizar(Pago pago) {
		Pago pagoEncontrado = pagosRepository.findById(pago.getId())
				.orElseThrow(() -> new NoEncontradoException(pago.getId(), Entidad.PAGO));

		if (pago.getFechaAplicacion() != null) {
			pagoEncontrado.setFechaAplicacion(pago.getFechaAplicacion());
		}

		EstatusPago estatusPago = estatusPagosRepository.findById(pago.getEstatus().getId())
				.orElseThrow(() -> new NoEncontradoException(pago.getEstatus().getId(), Entidad.ESTATUS_PAGO));
		pagoEncontrado.setEstatus(estatusPago);

		return pagosRepository.save(pagoEncontrado);
	}
}
