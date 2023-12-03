package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import dao.UsuarioDAO;
import exceptions.DataException;
import model.Produto;
import model.Usuario;

public class UsuarioDAOImpl implements UsuarioDAO {
	private static final Logger logger = Logger.getLogger(UsuarioDAOImpl.class.getName());

	@Override
	public Usuario findById(Connection connection, Long idUsuario) throws DataException {
		Usuario usuario = null;
		StringBuilder sql = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			sql = new StringBuilder();
			sql.append("SELECT u.id, u.username, u.password, u.nome, u.rol, u.avatar, u.baixa ");
			sql.append("FROM usuario as u ");
			sql.append("WHERE u.id = ?");
			preparedStatement = connection.prepareStatement(sql.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			int i = 1;
			preparedStatement.setLong(i++, idUsuario);
			logger.info(preparedStatement.toString());
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				usuario = loadNext(resultSet);
			}
		} catch (SQLException e) {
		}

		return usuario;
	}

	@Override
	public Usuario findByEmail(Connection connection, String email) throws DataException {
		Usuario usuario = null;
		StringBuilder sql = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			sql = new StringBuilder();
			sql.append("SELECT u.id, u.username, u.password, u.nome, u.rol, u.avatar, u.baixa ");
			sql.append("FROM usuario as u ");
			sql.append("WHERE username = ?");
			preparedStatement = connection.prepareStatement(sql.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			int i = 1;
			preparedStatement.setString(i++, email);
			logger.info(preparedStatement.toString());
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				usuario = loadNext(resultSet);
			}
		} catch (SQLException e) {
		} finally {
		}
		return usuario;
	}

	@Override
	public ArrayList<Usuario> findAll(Connection connection) throws DataException {
		ArrayList<Usuario> userList = new ArrayList<>();
		StringBuilder sql = new StringBuilder();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			sql.append("SELECT id, username, password, nome, rol, avatar, baixa ");
			sql.append("FROM usuario");
			preparedStatement = connection.prepareStatement(sql.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			logger.info(preparedStatement.toString());
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				Usuario usuario = loadNext(resultSet);
				userList.add(usuario);
			}

		} catch (SQLException e) {
		} finally {

		}

		return userList;
	}

	@Override
	public Usuario create(Connection connection, Usuario usuario) throws DataException {
		StringBuilder sql = new StringBuilder();
		PreparedStatement preparedStatement = null;
		ResultSet generatedKeys = null;

		try {
			sql.append("INSERT INTO usuario (username, password, nome, rol, avatar, baixa) ");
			sql.append("VALUES (?, ?, ?, ?, ?, ?)");

			int i = 1;
			preparedStatement = connection.prepareStatement(sql.toString(), PreparedStatement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(i++, usuario.getUsername());
			preparedStatement.setString(i++, usuario.getPassword());
			preparedStatement.setString(i++, usuario.getNome());
			preparedStatement.setString(i++, usuario.getRol());
			preparedStatement.setString(i++, usuario.getAvatar());
			preparedStatement.setBoolean(i++, usuario.getBaixa());

			logger.info(preparedStatement.toString());
			preparedStatement.executeUpdate();
			
			logger.info("Creado el usuario: "+usuario.getNome());
		} catch (SQLException e) {
			
		} finally {

		}

		return usuario;
	}

	@Override
	public Usuario update(Connection connection, Usuario usuario) throws DataException {
		    StringBuilder sql = new StringBuilder();
		    PreparedStatement preparedStatement = null;
		    Usuario usuarioActualizado = null;

		    try {
		        sql.append("UPDATE usuario SET ");
		        if (usuario.getUsername() != null) {
		            sql.append("username=?, ");
		        }
		        if (usuario.getNome() != null) {
		            sql.append("nome=?, ");
		        }
		        if (usuario.getRol() != null) {
		            sql.append("rol=?, ");
		        }
		        if (usuario.getBaixa() != null) {
		            sql.append("baixa=?, ");
		        }

		        sql.deleteCharAt(sql.length() - 2);

		        sql.append(" WHERE id=?");

		        int i = 1;
		        preparedStatement = connection.prepareStatement(sql.toString());

		        if (usuario.getUsername() != null) {
		            preparedStatement.setString(i++, usuario.getUsername());
		        }
		        if (usuario.getNome() != null) {
		            preparedStatement.setString(i++, usuario.getNome());
		        }
		        if (usuario.getRol() != null) {
		            preparedStatement.setString(i++, usuario.getRol());
		        }
		        if (usuario.getBaixa() != null) {
		            preparedStatement.setBoolean(i++, usuario.getBaixa());
		        }

		        preparedStatement.setLong(i++, usuario.getId());

		        logger.info(preparedStatement.toString());
		        preparedStatement.executeUpdate();
		        usuarioActualizado = findById(connection, usuario.getId());
		    } catch (SQLException e) {
		        logger.log(Level.SEVERE, "Error al actualizar el usuario", e);
		        throw new DataException("Error al actualizar el usuario", e);
		    } finally {
		        if (preparedStatement != null) {
		            try {
		            	logger.info("usuario "+usuario.getNome()+" actualizado");
		                preparedStatement.close();
		            } catch (SQLException e) {
		                logger.log(Level.SEVERE, "Error al cerrar el PreparedStatement", e);
		            }
		        }
		    }
		    return usuarioActualizado;
		}

	@Override
	public void softDelete(Connection connection, Long idUsuario) throws DataException {
		StringBuilder sql = new StringBuilder();
		PreparedStatement preparedStatement = null;
		try {
			sql.append("UPDATE usuario SET baixa=true WHERE id=?");

			preparedStatement = connection.prepareStatement(sql.toString());
			preparedStatement.setLong(1, idUsuario);

			logger.info(preparedStatement.toString());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			logger.log(Level.SEVERE, e.getMessage());
		} finally {
			if (preparedStatement != null) {
				try {
					logger.info("El usuario con id "+idUsuario+" ha sido dado de baixa");
					preparedStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private Usuario loadNext(ResultSet resultSet) throws SQLException {
		Usuario usuario = new Usuario();
		usuario.setId(resultSet.getLong("id"));
		usuario.setUsername(resultSet.getString("username"));
		usuario.setPassword(resultSet.getString("password"));
		usuario.setNome(resultSet.getString("nome"));
		usuario.setRol(resultSet.getString("rol"));
		usuario.setAvatar(resultSet.getString("avatar"));
		usuario.setBaixa(resultSet.getBoolean("baixa"));
		return usuario;
	}

}