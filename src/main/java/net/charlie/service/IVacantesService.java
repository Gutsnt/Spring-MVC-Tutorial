package net.charlie.service;

import java.util.List;

import org.springframework.data.domain.Example;

import net.charlie.model.Vacante;

public interface IVacantesService {
	List<Vacante> buscarTodas();
	Vacante buscarPorId(Integer idVacante);
	void guardar (Vacante vacante);
	List<Vacante> buscarDestacada();
	void eliminar(int idVacante);
	List <Vacante> buscarByExample(Example<Vacante> example);

}
