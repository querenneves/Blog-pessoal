package com.generation.blogpessoal.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;

import com.generation.blogpessoal.model.Usuario;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UsuarioRepositoryTest {
    
	@Autowired
    private UsuarioRepository usuarioRepository;
	
	@BeforeAll
    void start() {
        usuarioRepository.deleteAll();

        usuarioRepository.save(new Usuario(0L, "Root", "root@root.com", "rootroot", " "));
        usuarioRepository.save(new Usuario(0L, "Test", "test@test.com", "testtest", " "));
        usuarioRepository.save(new Usuario(0L, "Carla Souza", "carla@carla.com", "carlacarla", " "));
        usuarioRepository.save(new Usuario(0L, "Robson", "robson@robson.com", "robsonrobson", " "));
        
    }
	
	@Test
	@DisplayName("Retorna 1 usuario")
	public void deveRetornarUmUsuario() {
		
		Optional<Usuario> usuario = usuarioRepository.findByUsuario("carla@carla.com");
		assertTrue(usuario.get().getUsuario().equals("carla@carla.com"));
		
	}
	
	@Test
	@DisplayName("Retorna 3 usuario")
	public void deveRetornarTresUsuarios() {
		
		List<Usuario> listaDeUsuarios = usuarioRepository.TestfindAllByNomeContainingIgnoreCase("Souza");
		assertEquals(3, listaDeUsuarios.size());
		assertTrue(listaDeUsuarios.get(0).getNome().equals("Carla Souza"));
		assertTrue(listaDeUsuarios.get(1).getNome().equals("Robson"));
		assertTrue(listaDeUsuarios.get(2).getNome().equals("Test"));
		
	}
	
	@AfterAll
	public void end() {
		usuarioRepository.deleteAll();
	}
}
