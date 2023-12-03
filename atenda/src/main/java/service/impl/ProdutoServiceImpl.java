package service.impl;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import dao.ProdutoDAO;
import dao.Results;
import dao.impl.ProdutoDAOImpl;
import dao.util.ConnectionManager;
import exceptions.DataException;
import model.Produto;
import service.ProdutoCriteria;
import service.ProdutoService;

public class ProdutoServiceImpl implements ProdutoService {
	private static final Logger logger = Logger.getLogger(ProdutoServiceImpl.class.getName());
	private ProdutoDAO produtoDAO = new ProdutoDAOImpl() ;

	@Override
	public Produto findById(Long idProduto) throws DataException {
		try (Connection connection = ConnectionManager.getConnection()) {
			return produtoDAO.findById(connection, idProduto);
		} catch (Exception e) {
			throw new DataException("Error al buscar el produto por ID", e);
		}
	}

	@Override
	public ArrayList<Produto> findAll() throws DataException {
		try (Connection connection = ConnectionManager.getConnection()) {
			return produtoDAO.findAll(connection);
		} catch (Exception e) {
			throw new DataException("Error al buscar todos los produtos", e);
		}
	}

	@Override
	public Results<Produto> findBy(ProdutoCriteria produtoCriteria, int startIndex, int count) throws DataException {
		try (Connection connection = ConnectionManager.getConnection()) {
			return produtoDAO.findBy(connection, produtoCriteria, startIndex, count);
		} catch (Exception e) {
			throw new DataException("Error al buscar produtos por criterios", e);
		}
	}

	@Override
	public Produto create(Produto produto) throws DataException {
		try (Connection connection = ConnectionManager.getConnection()) {
			produtoDAO.create(connection, produto);
		} catch (Exception e) {
			throw new DataException("Error al crear el produto", e);
		}
		return produto;
	}

	@Override
	public Produto update(Produto produto) throws DataException {
		try (Connection connection = ConnectionManager.getConnection()) {
			return produtoDAO.update(connection, produto);
		} catch (Exception e) {
			throw new DataException("Error al actualizar el produto", e);
		}
	}

	@Override
	public void softDelete(long idProduto) throws DataException {
		try (Connection connection = ConnectionManager.getConnection()) {
			produtoDAO.softDelete(connection, idProduto);
		} catch (Exception e) {
			throw new DataException("Error al dar de baja el produto", e);
		}
	}

	@Override
	public boolean asignarProdutoCategoria(Long idProduto, Integer idCategoria) throws DataException {
		Connection connection = null;
		try {
			connection = ConnectionManager.getConnection();
			Produto produto = produtoDAO.findById(connection, idProduto);

			if (produto != null) {
				produto.setIdCategoria(idCategoria);
				produtoDAO.update(connection, produto);
				logger.info("Producto " + idProduto + " asignado a la categoría " + idCategoria + " con éxito.");
				return true;
			} else {
				logger.warning("No se encontró el producto con ID " + idProduto);
				return false;
			}
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Error al asignar producto a la categoría", e);
			throw new DataException("Error al asignar producto a la categoría", e);
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

	@Override
	public boolean asignarProdutoMarca(Long idProduto, Long idMarca) throws DataException {
		Connection connection = null;
		try {
			connection = ConnectionManager.getConnection();
			Produto produto = produtoDAO.findById(connection, idProduto);

			if (produto != null) {
				produto.setIdMarca(idMarca);
				produtoDAO.update(connection, produto);
				logger.info("Producto " + idProduto + " asignado a la marca " + idMarca + " con éxito.");
				return true;
			} else {
				logger.warning("No se encontró el producto con ID " + idProduto);
				return false;
			}
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Error al asignar producto a la marca", e);
			throw new DataException("Error al asignar producto a la marca", e);
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