package net.charlie.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import net.charlie.model.Usuario;
import net.charlie.service.IUsuariosService;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {
	
	@Autowired
	//@Qualifier("usuariosServiceJpa")
	private IUsuariosService serviceUsuario;
	
	@GetMapping("/index")
	public String mostrarIndex(Model model) {
		List<Usuario> lista = serviceUsuario.buscarTodo();
		model.addAttribute("usuarios", lista);
		return "usuarios/listUsuarios";
	}
	
	@GetMapping("/delete/{id}")
	public String eliminar(@PathVariable("id") int idUsuario, RedirectAttributes attributes) {
		serviceUsuario.eliminar(idUsuario);
		attributes.addFlashAttribute("msg", "El usuario fue eliminada!");
		return "redirect:/usuarios/index";
		
	}
}
