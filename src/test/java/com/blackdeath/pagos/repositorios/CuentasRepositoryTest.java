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
import com.blackdeath.pagos.entidades.Usuario;

/**
 * Test para {@link Cuenta}
 * 
 * @author Seth Karim Luis Martínez
 * @since 2024-07-21
 */
@ExtendWith(MockitoExtension.class)
public class CuentasRepositoryTest {

	@Mock
	private CuentasRepository cuentasRepository;

	private Cuenta cuenta;

	@BeforeEach
	public void setUp() {
		Usuario usuario = new Usuario();
		usuario.setId(1L);
		usuario.setNombre("Carlos");
		usuario.setApellidoPaterno("Trejo");
		usuario.setApellidoMaterno("Materno");

		cuenta = new Cuenta();
		cuenta.setId(1L);
		cuenta.setNumero("123456789012");
		cuenta.setClabe("123456789012345678");
		cuenta.setSaldo(new BigDecimal("1000.00"));
		cuenta.setFechaCreacion(LocalDateTime.now());
		cuenta.setUsuario(usuario);
	}

	@Test
    public void testGuardarCuenta() {
        when(cuentasRepository.save(any(Cuenta.class))).thenReturn(cuenta);

        Cuenta resultado = cuentasRepository.save(cuenta);

        assertEquals("123456789012", resultado.getNumero());
    }

	@Test
    public void testConsultarCuenta() {
        when(cuentasRepository.findById(1L)).thenReturn(Optional.of(cuenta));

        Optional<Cuenta> resultado = cuentasRepository.findById(1L);

        assertEquals(true, resultado.isPresent());
        assertEquals("123456789012", resultado.get().getNumero());
    }

	@Test
    public void testActualizarCuenta() {
        when(cuentasRepository.save(any(Cuenta.class))).thenReturn(cuenta);

        cuenta.setSaldo(new BigDecimal("900.00"));
        Cuenta resultado = cuentasRepository.save(cuenta);

        assertEquals(new BigDecimal("900.00"), resultado.getSaldo());
    }

	@Test
	public void testEliminarCuenta() {
		cuentasRepository.deleteById(1L);

		verify(cuentasRepository, times(1)).deleteById(1L);
	}

}
