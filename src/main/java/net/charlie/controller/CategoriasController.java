package net.charlie.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import net.charlie.model.Categoria;
import net.charlie.model.Vacante;
import net.charlie.service.ICategoriasService;

@Controller
@RequestMapping("/categorias")
public class CategoriasController {
	
	@Autowired
	//@Qualifier("categoriasServiceJpa")
	private ICategoriasService serviceCategoria;	
	
	 @GetMapping("/index")
	public String listCategorias(Model model) {	 
		 	List<Categoria> lista = serviceCategoria.buscarTodas();
			model.addAttribute("categorias", lista);
			return "categorias/listCategorias";
	}
	 
	 @GetMapping(value = "/indexPaginate")
	 public String mostrarIndexPaginado(Model model, Pageable page) {
	 Page<Categoria> lista = serviceCategoria.buscarTodas(page);
	 model.addAttribute("categorias", lista);
	 return "categorias/listCategorias";
	 }
	 
	 
	 @GetMapping("/create")	
	public String crear(Categoria categoria) { 
		 	return "categorias/formCategoria";
	}
	
	 
	 @PostMapping("/save")
	public String guardar(Categoria categoria, BindingResult result, RedirectAttributes attributes) {
		 if (result.hasErrors()) {
				for (ObjectError error: result.getAllErrors()){
					System.out.println("Ocurrio un error: " + error.getDefaultMessage());
					}
				return "categorias/formVacante";
				}
		 
		 serviceCategoria.guardar(categoria);
		 attributes.addFlashAttribute("msg", "Los datos de la categoría fueron guardados!");
		System.out.print("Categoria " + categoria);
		return "redirect:/categorias/index";
	}
	 @GetMapping("/edit/{id}")
		public String editar(@PathVariable("id") int idCategoria, Model model ) {
			Categoria categoria = serviceCategoria.buscarPorId(idCategoria);
			model.addAttribute("categoria", categoria);
			return"categorias/formCategoria";
			
		}
	 
	 @GetMapping("/delete/{id}")
		public String eliminar(@PathVariable("id") int idCategoria, RedirectAttributes attributes) {
			serviceCategoria.eliminarCategoria(idCategoria);
			attributes.addFlashAttribute("msg", "La vacante fue eliminada!");
			return "redirect:/categorias/index";
	 }
	
}
