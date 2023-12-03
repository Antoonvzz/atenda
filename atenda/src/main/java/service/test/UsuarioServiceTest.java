package service.test;

import java.util.logging.Logger;

import exceptions.DataException;
import exceptions.UserNotFoundException;
import model.Usuario;
import service.UsuarioService;
import service.impl.*;

public class UsuarioServiceTest {
	private static final Logger logger = Logger.getLogger(UsuarioServiceTest.class.getName());
	public static void main(String[] args) throws UserNotFoundException {
		UsuarioService usuario = new UsuarioServiceImpl();
		try {
			logger.info(usuario.findByEmail("admin@gmail.com").toString());
			logger.info(usuario.findById(2L).toString());
//			logger.info(usuario.findAll().toString());
			Usuario usuario2 = new Usuario();
			usuario2.setAvatar("anton.png");
			usuario2.setBaixa(false);
			usuario2.setId(45L);
			usuario2.setNome("anton");
			usuario2.setPassword("abc123.");
			usuario2.setRol("BASIC");
			usuario2.setUsername("anton@gmail.com");
//			usuario.registrar(usuario2);
//			usuario.login("anton@gmail.com", "abc123.");
//			usuario2.setUsername("adminanton@gmail.com");
//			usuario.update(usuario2);
//			usuario.softDelete(45L);
		} catch (DataException e) {
			e.printStackTrace();
		}

	}

}
