package service.impl;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import dao.UsuarioDAO;
import dao.impl.UsuarioDAOImpl;
import dao.util.ConnectionManager;
import exceptions.DataException;
import exceptions.UserNotFoundException;
import model.Usuario;
import service.UsuarioService;

public class UsuarioServiceImpl implements UsuarioService{
	 private static final Logger logger = Logger.getLogger(UsuarioServiceImpl.class.getName());
	    private final UsuarioDAO usuarioDAO = new UsuarioDAOImpl();

	    @Override
	    public Usuario login(String username, String password) throws UserNotFoundException, DataException {
	        Connection connection = null;
	        Usuario usuario = null;
	        try {
	            connection = ConnectionManager.getConnection();
	            usuario = usuarioDAO.findByEmail(connection, username);

	            if (usuario != null && usuario.getPassword().equals(password)) {
	                logger.info("Usuario autenticado: " + usuario.getUsername());
	            } else {
	                logger.warning("Autenticación fallida para el usuario: " + username);
	                throw new UserNotFoundException("Usuario no encontrado o contraseña incorrecta");
	            }
	        } catch (Exception e) {
	            logger.log(Level.SEVERE, "Error en el proceso de autenticación", e);
	            throw new DataException("Error en el proceso de autenticación", e);
	        } finally {
	            if (connection != null) {
	                try {
	                    connection.close();
	                } catch (Exception e) {
	                    e.printStackTrace();
	                }
	            }
	        }

	        return usuario;
	    }

	    @Override
	    public Usuario findByEmail(String email) throws DataException, UserNotFoundException {
	        Connection connection = null;
	        Usuario usuario = null;
	        try {
	            connection = ConnectionManager.getConnection();
	            usuario = usuarioDAO.findByEmail(connection, email);

	            if (usuario == null) {
	                logger.warning("Usuario no encontrado para el email: " + email);
	                throw new UserNotFoundException("Usuario no encontrado para el email: " + email);
	            }
	        } catch (Exception e) {
	            logger.log(Level.SEVERE, "Error al buscar usuario por email", e);
	            throw new DataException("Error al buscar usuario por email", e);
	        } finally {
	            if (connection != null) {
	                try {
	                    connection.close();
	                } catch (Exception e) {
	                    e.printStackTrace();
	                }
	            }
	        }

	        return usuario;
	    }

	    @Override
	    public Usuario findById(Long idUsuario) throws DataException, UserNotFoundException {
	        Connection connection = null;
	        Usuario usuario = null;
	        try {
	            connection = ConnectionManager.getConnection();
	            usuario = usuarioDAO.findById(connection, idUsuario);

	            if (usuario == null) {
	                logger.warning("Usuario no encontrado para el ID: " + idUsuario);
	                throw new UserNotFoundException("Usuario no encontrado para el ID: " + idUsuario);
	            }
	        } catch (Exception e) {
	            logger.log(Level.SEVERE, "Error al buscar usuario por ID", e);
	            throw new DataException("Error al buscar usuario por ID", e);
	        } finally {
	            if (connection != null) {
	                try {
	                    connection.close();
	                } catch (Exception e) {
	                    e.printStackTrace();
	                }
	            }
	        }

	        return usuario;
	    }

	    @Override
	    public ArrayList<Usuario> findAll() throws DataException {
	        Connection connection = null;
	        ArrayList<Usuario> userList = new ArrayList<>();
	        try {
	            connection = ConnectionManager.getConnection();
	            userList = usuarioDAO.findAll(connection);
	        } catch (Exception e) {
	            logger.log(Level.SEVERE, "Error al obtener la lista de usuarios", e);
	            throw new DataException("Error al obtener la lista de usuarios", e);
	        } finally {
	            if (connection != null) {
	                try {
	                    connection.close();
	                } catch (Exception e) {
	                    e.printStackTrace();
	                }
	            }
	        }

	        return userList;
	    }

	    @Override
	    public Usuario registrar(Usuario usuario) throws DataException {
	        Connection connection = null;
	        try {
	            connection = ConnectionManager.getConnection();

	            if (usuarioDAO.findByEmail(connection, usuario.getUsername()) == null) {
	                usuarioDAO.create(connection, usuario);
	                logger.info("Usuario registrado con éxito: " + usuario.getUsername());
	            } else {
	                logger.warning("El email ya está registrado: " + usuario.getUsername());
	                throw new DataException("El email ya está registrado: " + usuario.getUsername());
	            }
	        } catch (Exception e) {
	            logger.log(Level.SEVERE, "Error al registrar usuario", e);
	            throw new DataException("Error al registrar usuario", e);
	        } finally {
	            if (connection != null) {
	                try {
	                    connection.close();
	                } catch (Exception e) {
	                    e.printStackTrace();
	                }
	            }
	        }

	        return usuario;
	    }

	    @Override
	    public Usuario update(Usuario usuario) throws DataException {
	        Connection connection = null;
	        Usuario usuarioActualizado = null;
	        try {
	            connection = ConnectionManager.getConnection();
	            usuarioActualizado = usuarioDAO.update(connection, usuario);
	        } catch (Exception e) {
	            logger.log(Level.SEVERE, "Error al actualizar usuario", e);
	            throw new DataException("Error al actualizar usuario", e);
	        } finally {
	            if (connection != null) {
	                try {
	                    connection.close();
	                } catch (Exception e) {
	                    e.printStackTrace();
	                }
	            }
	        }

	        return usuarioActualizado;
	    }

	    @Override
	    public void softDelete(Long idUsuario) throws DataException {
	        Connection connection = null;
	        try {
	            connection = ConnectionManager.getConnection();
	            usuarioDAO.softDelete(connection, idUsuario);
	        } catch (Exception e) {
	            logger.log(Level.SEVERE, "Error al dar de baja al usuario", e);
	            throw new DataException("Error al dar de baja al usuario", e);
	        } finally {
	            if (connection != null) {
	                try {
	                    connection.close();
	                } catch (Exception e) {
	                    e.printStackTrace();
	                }
	            }
	        }
	    }
	}
