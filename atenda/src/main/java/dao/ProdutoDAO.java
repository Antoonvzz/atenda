package dao;

import java.sql.Connection;
import java.util.ArrayList;

import exceptions.DataException;
import model.Produto;
import service.ProdutoCriteria;

public interface ProdutoDAO {
	public Produto findById(Connection connection, Long idProduto) throws DataException; //obten produto por id
	public ArrayList<Produto> findAll(Connection connection) throws DataException; // devolve a lista de produtos
	public Results<Produto> findBy(Connection connection, ProdutoCriteria produto,int startIndex,int count) throws DataException;
	 public int create(Connection connection, Produto produto) throws DataException;//inserta produto
	 public Produto update(Connection connection,Produto produto) throws DataException;//actualiza produto
	 public void softDelete(Connection connection, Long idProduto) throws DataException;//
	 
}
