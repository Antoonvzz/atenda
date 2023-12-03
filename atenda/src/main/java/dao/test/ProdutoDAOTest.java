package dao.test;

import java.util.logging.Logger;

import dao.ProdutoDAO;
import dao.impl.ProdutoDAOImpl;
import dao.util.ConnectionManager;
import model.Produto;
import service.ProdutoCriteria;

public class ProdutoDAOTest {
	private static final Logger logger = Logger.getLogger(ProdutoDAOTest.class.getName());
	
	public static void main(String[] args) {
		ProdutoDAO produtoDAO = new ProdutoDAOImpl();
			try {
				Produto produto =produtoDAO.findById(ConnectionManager.getConnection(), 2L);
				logger.info(produto.toString());
//				ProdutoCriteria produtoCriteria = new ProdutoCriteria(0.0, 100.0, 1, null, null);
//				logger.info(produtoDAO.findBy(ConnectionManager.getConnection(), produtoCriteria, 1, 3).toString());
//				logger.info(produtoDAO.findAll(ConnectionManager.getConnection()).toString());
//				Produto p = new Produto();
//				p.setBaixa(false);
//				p.setCoste(75.00);
//				p.setPrezo(100.00);
//				p.setId(41L);
//				p.setDesconto(0);
//				p.setFoto("mesa.png");
//				p.setIdCategoria(1);
//				p.setIdMarca(2L);
//				p.setIva(2);
//				p.setNome("mesa");
//				p.setStock(20L);
//				produtoDAO.create(ConnectionManager.getConnection(),p);
//				produtoDAO.softDelete(ConnectionManager.getConnection(), 1L);
//				p.setNome("tablet");
//				Produto produtoActualizado = produtoDAO.update(ConnectionManager.getConnection(), p);
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
}
