package net.charlie.service;

import java.util.List;

import net.charlie.model.Usuario;

public interface IUsuariosService {
	
	void guardar(Usuario usuario);
	
	void eliminar(Integer idUsuario);
	
	List<Usuario> buscarTodo();
	
	Usuario buscarPorUsername(String username);
	
	int bloquear(int idUsuario);
	
	int activar(int idUsuario);
	

}
