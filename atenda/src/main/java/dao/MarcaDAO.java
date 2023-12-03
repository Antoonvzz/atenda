package dao;

import java.util.ArrayList;

import model.Marca;

public interface MarcaDAO {
	 public ArrayList<Marca> getAllMarcas() throws Exception; // devolve a lista das marcas
	 public void actualiza(Marca m) throws Exception;  // acgtualiza marca
	 public int inserta(Marca m) throws Exception; /// inserta marca
	 public void borra (Marca m) throws Exception; // borra marca
	 public Marca getMarcaPorId (int idMarca) throws Exception; // obten marca por id
}
