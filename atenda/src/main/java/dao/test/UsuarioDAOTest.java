package dao.test;

import java.util.logging.Logger;

import dao.UsuarioDAO;
import dao.impl.UsuarioDAOImpl;
import dao.util.ConnectionManager;
import exceptions.DataException;
import model.Usuario;

public class UsuarioDAOTest {
	private static final Logger logger = Logger.getLogger(ProdutoDAOTest.class.getName());
	public static void main(String[] args) {
		UsuarioDAO usuarioDAO = new UsuarioDAOImpl();
		
		try {
			Usuario usuario =usuarioDAO.findById(ConnectionManager.getConnection(), 1L);
			logger.info(usuario.toString());
//			usuario = usuarioDAO.findByEmail(ConnectionManager.getConnection(), "dep@gmail.com");
//			logger.info(usuario.toString());
//			logger.info(usuarioDAO.findAll(ConnectionManager.getConnection()).toString());
//			Usuario usuario2 = new Usuario();
//			usuario2.setAvatar("anton.png");
//			usuario2.setBaixa(false);
//			usuario2.setId(42L);
//			usuario2.setNome("anton");
//			usuario2.setPassword("abc123.");
//			usuario2.setRol("BASIC");
//			usuario2.setUsername("anton@gmail.com");
//			usuarioDAO.create(ConnectionManager.getConnection(),usuario2);
//			usuario2.setUsername("adminanton@gmail.com");
//			Usuario usuarioActualizado =usuarioDAO.update(ConnectionManager.getConnection(), usuario2);
//			usuarioDAO.softDelete(ConnectionManager.getConnection(), 41L);
			
		} catch (DataException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

}
