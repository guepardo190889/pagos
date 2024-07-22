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

import com.blackdeath.pagos.entidades.Usuario;

/**
 * Test para {@link Usuario}
 * 
 * @author Seth Karim Luis Martínez
 * @since 2024-07-21
 */
@ExtendWith(MockitoExtension.class)
public class UsuariosRepositoryTest {

	@Mock
	private UsuariosRepository usuariosRepository;

	private Usuario usuario;

	@BeforeEach
	public void setUp() {
		usuario = new Usuario();
		usuario.setId(1L);
		usuario.setNombre("Carlos");
		usuario.setApellidoPaterno("Trejo");
		usuario.setApellidoMaterno("Materno");
		usuario.setRfc("VECJ880326XXX");
		usuario.setFechaCreacion(LocalDateTime.now());
	}

	@Test
	    public void testGuardarUsuario() {
	        when(usuariosRepository.save(any(Usuario.class))).thenReturn(usuario);

	        Usuario resultado = usuariosRepository.save(usuario);

	        assertEquals("Carlos", resultado.getNombre());
	    }

	@Test
	    public void testConsultarUsuario() {
	        when(usuariosRepository.findById(1L)).thenReturn(Optional.of(usuario));

	        Optional<Usuario> resultado = usuariosRepository.findById(1L);

	        assertEquals(true, resultado.isPresent());
	        assertEquals("VECJ880326XXX", resultado.get().getRfc());
	    }

	@Test
	    public void testActualizarUsuario() {
	        when(usuariosRepository.save(any(Usuario.class))).thenReturn(usuario);

	        usuario.setNombre("Karlos");
	        Usuario resultado = usuariosRepository.save(usuario);

	        assertEquals("Karlos", resultado.getNombre());
	    }

	@Test
	public void testEliminarUsuario() {
		usuariosRepository.deleteById(1L);

		verify(usuariosRepository, times(1)).deleteById(1L);
	}
}
