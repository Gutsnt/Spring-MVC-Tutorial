package net.charlie.service;

import java.util.List;

import net.charlie.model.Categoria;

public interface ICategoriasService {
		void guardar (Categoria categoria);
		List<Categoria> buscarTodas();
		
		Categoria buscarPorId(Integer idCategoria);
		
		void eliminarCategoria(Integer idCategoria);
}
