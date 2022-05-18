package net.charlie.service.db;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.charlie.model.Usuario;
import net.charlie.repository.UsuariosRepository;
import net.charlie.service.IUsuariosService;

@Service
public class UsuariosServiceJpa implements IUsuariosService {

	
	@Autowired
	private UsuariosRepository usuarioRepo;
	
	@Override
	public void guardar(Usuario usuario) {
		usuarioRepo.save(usuario);

	}

	@Override
	public void eliminar(Integer idUsuario) {
		usuarioRepo.deleteById(idUsuario);
	}

	@Override
	public List<Usuario> buscarTodo() {
		return usuarioRepo.findAll();
	}

}
