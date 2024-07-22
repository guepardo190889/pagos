package com.blackdeath.pagos.repositorios;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.blackdeath.pagos.entidades.Destinatario;

/**
 * Test para {@link Destinatario}
 * 
 * @author Seth Karim Luis Martínez
 * @since 2024-07-21
 */
@ExtendWith(MockitoExtension.class)
public class DestinatariosRepositoryTest {

	@Mock
	private DestinatariosRepository destinatariosRepository;

	private Destinatario destinatarios;

	@BeforeEach
	public void setUp() {
		destinatarios = new Destinatario();
		destinatarios.setId(1L);
		destinatarios.setNombre("Carlos");
		destinatarios.setApellidoPaterno("Trejo");
		destinatarios.setApellidoMaterno("Materno");
		destinatarios.setRfc("VECJ880326XXX");
		destinatarios.setFechaCreacion(LocalDateTime.now());
	}

	@Test
	    public void testGuardarDestinatario() {
	        when(destinatariosRepository.save(any(Destinatario.class))).thenReturn(destinatarios);

	        Destinatario resultado = destinatariosRepository.save(destinatarios);

	        assertEquals("Carlos", resultado.getNombre());
	    }

	@Test
	    public void testConsultarDestinatario() {
	        when(destinatariosRepository.findById(1L)).thenReturn(Optional.of(destinatarios));

	        Optional<Destinatario> resultado = destinatariosRepository.findById(1L);

	        assertEquals(true, resultado.isPresent());
	        assertEquals("VECJ880326XXX", resultado.get().getRfc());
	    }

	@Test
	    public void testActualizarDestinatario() {
	        when(destinatariosRepository.save(any(Destinatario.class))).thenReturn(destinatarios);

	        destinatarios.setNombre("Karlos");
	        Destinatario resultado = destinatariosRepository.save(destinatarios);

	        assertEquals("Karlos", resultado.getNombre());
	    }

	@Test
	public void testEliminarDestinatario() {
		destinatariosRepository.deleteById(1L);

		verify(destinatariosRepository, times(1)).deleteById(1L);
	}
}
