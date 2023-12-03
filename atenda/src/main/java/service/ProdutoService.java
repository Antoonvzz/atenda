package service;

import java.util.ArrayList;

import exceptions.DataException;
import dao.Results;
import model.Produto;

public interface ProdutoService {
	public Produto findById(Long idProduto) throws DataException; //obten producto por id
	public ArrayList<Produto> findAll() throws DataException; //devolve a lista de productos
	public Results<Produto> findBy(ProdutoCriteria produto, int startIndex,int count) throws DataException;
	public Produto create(Produto produto) throws DataException; //inserta produto
	public Produto update(Produto produto) throws DataException; //actualiza produto
	public void softDelete(long idProduto) throws DataException; //borra soft produto
	public boolean asignarProdutoCategoria(Long idProduto, Integer idCategoria) throws DataException; //Asigna categria a un produto
	public boolean asignarProdutoMarca(Long idProduto, Long idMarca) throws DataException; //asigna marca a un produto
	
}
