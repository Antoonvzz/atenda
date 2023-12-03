package service.test;

import java.util.logging.Logger;

import exceptions.DataException;
import model.Produto;
import service.impl.ProdutoServiceImpl;
import service.ProdutoCriteria;
import service.ProdutoService;

public class ProdutoServiceTest {
	private static final Logger logger = Logger.getLogger(ProdutoServiceTest.class.getName());

	public static void main(String[] args) throws dao.DataException {
		ProdutoService produto = new ProdutoServiceImpl();
		try {
//			logger.info(produto.findById(1L).toString());
			logger.info(produto.findAll().toString());
//			ProdutoCriteria pc = new ProdutoCriteria(0.0, 100.0, 1, null, null);
//			logger.info(produto.findBy(pc, 1, 3).toString());
//			Produto p = new Produto();
//			p.setBaixa(false);
//			p.setCoste(75.00);
//			p.setPrezo(100.00);
//			p.setId(41L);
//			p.setDesconto(0);
//			p.setFoto("mesa.png");
//			p.setIdCategoria(1);
//			p.setIdMarca(2L);
//			p.setIva(2);
//			p.setNome("mesa");
//			p.setStock(20L);
//			produto.create(p);
//			p.setNome("tablet");
//			produto.update(p);
//			produto.softDelete(41L);
//			produto.asignarProdutoCategoria(1L, 1);
//			produto.asignarProdutoMarca(1L, 2L);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
