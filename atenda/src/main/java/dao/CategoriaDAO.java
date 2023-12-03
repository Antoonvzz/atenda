package dao;

import java.util.ArrayList;

import model.Categoria;

public interface CategoriaDAO {
	 public ArrayList<Categoria> getAllCategorias() throws Exception; // devolve a lista das categorias
	 public void actualiza(Categoria c) throws Exception;  // acgtualiza categoria
	 public int inserta(Categoria c) throws Exception; /// inserta categoria
	 public void borra (Categoria c) throws Exception; // borra categoria
	 public Categoria getCategoriaPorId (int idCategoria) throws Exception; // obten categoria por id
}
