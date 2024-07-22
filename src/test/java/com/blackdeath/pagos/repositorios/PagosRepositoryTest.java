package com.blackdeath.pagos.repositorios;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.blackdeath.pagos.entidades.Cuenta;
import com.blackdeath.pagos.entidades.Destinatario;
import com.blackdeath.pagos.entidades.EstatusPago;
import com.blackdeath.pagos.entidades.Pago;
import com.blackdeath.pagos.entidades.Usuario;

/**
 * Test para {@link Pago}
 * 
 * @author Seth Karim Luis Martínez
 * @since 2024-07-21
 */
@ExtendWith(MockitoExtension.class)
public class PagosRepositoryTest {

	@Mock
	private PagosRepository pagosRepository;

	private Pago pago;

	@BeforeEach
	public void setUp() {
		Cuenta cuenta = new Cuenta();
		cuenta.setId(1L);

		Usuario usuario = new Usuario();
		usuario.setId(1L);

		Destinatario destinatario = new Destinatario();
		destinatario.setId(1L);

		EstatusPago estatusPago = new EstatusPago();
		estatusPago.setId(1L);

		pago = new Pago();
		pago.setId(1L);
		pago.setMonto(new BigDecimal("1000.00"));
		pago.setConcepto("Concepto pago");
		pago.setCuenta(cuenta);
		pago.setUsuario(usuario);
		pago.setDestinatario(destinatario);
		pago.setEstatus(estatusPago);
		pago.setFechaCreacion(LocalDateTime.now());
	}

	@Test
	public void testGuardarPago() {
		when(pagosRepository.save(any(Pago.class))).thenReturn(pago);
		
		Pago resultado = pagosRepository.save(pago);
		
		assertEquals(1L, resultado.getId());
	}

	@Test
	public void consultarPago() {
		when(pagosRepository.findById(1L)).thenReturn(Optional.of(pago));
		
		Optional<Pago> resultado = pagosRepository.findById(1L);
		
		assertEquals(true, resultado.isPresent());
		assertEquals(1L, pago.getId());
	}

	@Test
	public void actualizarPago() {
		when(pagosRepository.save(any(Pago.class))).thenReturn(pago);
		
		EstatusPago estatusPago = new EstatusPago();
		estatusPago.setId(2L);
		
		pago.setEstatus(estatusPago);
		
		Pago resultado = pagosRepository.save(pago);
		
		assertEquals(2L, resultado.getEstatus().getId());
	}

	@Test
	public void testEliminarPago() {
		pagosRepository.deleteById(1L);

		verify(pagosRepository, times(1)).deleteById(1L);
	}

}
