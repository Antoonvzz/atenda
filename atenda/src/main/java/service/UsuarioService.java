package service;

import java.util.ArrayList;

import exceptions.DataException;
import exceptions.UserNotFoundException;
import model.Usuario;

public interface UsuarioService {
	public Usuario login(String username, String password) throws UserNotFoundException, DataException;// autentica usuario
	public Usuario findByEmail(String email) throws DataException, UserNotFoundException;// obten usuario polo email
	public Usuario findById(Long idUsuario) throws DataException, UserNotFoundException;// obten usuario polo id
	public ArrayList<Usuario> findAll() throws DataException;// devolve a lista de usuarios
	public Usuario registrar(Usuario usuario) throws DataException;// crea usuario comprobando que email non repetido e encriptando password
	public Usuario update(Usuario usuario) throws DataException; // actualiza usuario
	public void softDelete(Long idUsuario) throws DataException; // borra soft usuario

}
