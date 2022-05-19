package net.charlie.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import net.charlie.model.Categoria;

@Service
public class CategoriasServiceImpl implements ICategoriasService  {
	
	private List<Categoria> lista= null;
	
	public CategoriasServiceImpl() {
		lista = new LinkedList<Categoria>();
		
			//Creamos la categoria 1 
			Categoria categoria1 = new Categoria();
			categoria1.setNombre("Ventas");
			categoria1.setDescripcion("Categoria relacionada con Ventas");
			categoria1.setId(1);			
			
			//Creamos la categoria 2
			Categoria categoria2 = new Categoria();
			categoria2.setNombre("Contabilidad");
			categoria2.setDescripcion("Categoria relacionada con Contabilidad");
			categoria2.setId(2);
			
			//Creamos la categoria 3 
			Categoria categoria3 = new Categoria();
			categoria3.setNombre("Transporte");
			categoria3.setDescripcion("Categoria relacionada con Transporte");
			categoria3.setId(3);
			
			
			//Creamos la categoria 4 
			Categoria categoria4 = new Categoria();
			categoria4.setNombre("Informatica");
			categoria4.setDescripcion("Categoria relacionada con informatica");
			categoria4.setId(4);

			//Creamos la categoria 5
			Categoria categoria5 = new Categoria();
			categoria5.setNombre("Construccion");
			categoria5.setDescripcion("Categoria relacionada con Construccion");
			categoria5.setId(5);
			
			// Agregamos los objetos de tipo vacante a la lista
			lista.add(categoria1);
			lista.add(categoria2);
			lista.add(categoria3);
			lista.add(categoria4);
			lista.add(categoria5);
			
		} 


	@Override
	public void guardar(Categoria categoria) {
		lista.add(categoria);
		
	}

	@Override
	public List<Categoria> buscarTodas() {
		return lista;
	}

	@Override
	public Categoria buscarPorId(Integer idCategoria) {
		for (Categoria cat: lista) {
			if (cat.getId() == idCategoria) {
				return cat;
			}
		}
		return null;
	}


	@Override
	public void eliminarCategoria(Integer idCategoria) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public Page<Categoria> buscarTodas(Pageable page) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
