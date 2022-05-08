package net.charlie.service;

import java.util.List;
import net.charlie.model.Vacante;

public interface IVacantesService {
	List<Vacante> buscarTodas();
	Vacante buscarPorId(Integer idVacante);
	void guardar (Vacante vacante);

}
