package net.charlie.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import net.charlie.model.Vacante;

@Service
public class VacantesServiceImpl implements IVacantesService {
	private List<Vacante> lista = null;
	
	public VacantesServiceImpl() {
			SimpleDateFormat sdf = new SimpleDateFormat("dd-mm-yyyy");
			lista = new LinkedList<Vacante>();
			try {
				//Creamos la oferta de trabajo 1 
				
				Vacante vacante1 = new Vacante();
				vacante1.setId(1);
				vacante1.setNombre("Ingeniero Civil");
				vacante1.setDescripcion("Solicitamos ingeniero civil para diseñar puente peatonal");
				vacante1.setFecha(sdf.parse("08-02-2022"));
				vacante1.setSalario(15500.0);
				vacante1.setDestacada(1);
				vacante1.setImage("empresa1.png");
				//Creamos la oferta de trabajo 2
				Vacante vacante2 = new Vacante();
				vacante2.setId(2);
				vacante2.setNombre("Ingeniero Electrico");
				vacante2.setDescripcion("Solicitamos ingeniero Electrico para mantener para mantener la instalcion electrica");
				vacante2.setFecha(sdf.parse("08-02-2022"));
				vacante2.setSalario(8500.0);
				vacante2.setDestacada(0);
				vacante2.setImage("empresa2.png");
				
				//Creamos la oferta de trabajo 3 
				Vacante vacante3 = new Vacante();
				vacante3.setId(3);
				vacante3.setNombre("Contador Publico");
				vacante3.setDescripcion("Solicitamos contador con 5 años de experiencia");
				vacante3.setFecha(sdf.parse("05-02-2022"));
				vacante3.setSalario(12500.0);
				vacante3.setDestacada(0);
				
				
				//Creamos la oferta de trabajo 4 
				Vacante vacante4 = new Vacante();
				vacante4.setId(4);
				vacante4.setNombre("Diseñador Grafico");
				vacante4.setDescripcion("Solicitamos diseñador grafico con titulo para diseñar publicidad de la empresa");
				vacante4.setFecha(sdf.parse("01-02-2022"));
				vacante4.setSalario(7500.0);
				vacante4.setDestacada(1);
				vacante4.setImage("empresa3.png");
				
				// Agregamos los objetos de tipo vacante a la lista
				lista.add(vacante1);
				lista.add(vacante2);
				lista.add(vacante3);
				lista.add(vacante4);
				
			} catch (ParseException e) {
				System.out.print("Error: " + e.getMessage());
			}
	 }
	

	@Override
	public List<Vacante> buscarTodas() {
		return lista;
		
	}

	@Override
	public Vacante buscarPorId(Integer idVacante) {
		for (Vacante v: lista) {
			if (v.getId() == idVacante) {
				return v;
			}
		}
		return null;
	}

	@Override
	public void guardar(Vacante vacante) {
		lista.add(vacante);
	}


	@Override
	public List<Vacante> buscarDestacada() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void eliminar(int idVacante) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public List<Vacante> buscarByExample(Example<Vacante> example) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public Page<Vacante> buscarTodas(org.springframework.data.domain.Pageable page) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
