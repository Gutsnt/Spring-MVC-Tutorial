package net.charlie.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import net.charlie.model.Vacante;
import net.charlie.service.ICategoriasService;
import net.charlie.service.IVacantesService;
import net.charlie.util.Utileria;

@Controller
@RequestMapping("/vacantes")
public class VacantesController {
	@Autowired
	private IVacantesService serviceVacantes;	
	
	@Autowired
	private ICategoriasService serviceCategorias;
	
		@GetMapping("/index")
		public String listVacantes(Model model) {
			List<Vacante> lista = serviceVacantes.buscarTodas();
			model.addAttribute("vacantes", lista);
			return"vacantes/listVacante";
		}
	
		@GetMapping("/create")
		public String crear(Vacante vacante, Model model) {
			model.addAttribute("categorias", serviceCategorias.buscarTodas());	
			return "vacantes/formVacante";
		}
		
		@PostMapping("/save")
		public String guardar (Vacante vacante, BindingResult result, RedirectAttributes attributes,
				@RequestParam("archivoImagen") MultipartFile multiPart) {
			
			if (result.hasErrors()) {
				for (ObjectError error: result.getAllErrors()){
					System.out.println("Ocurrio un error: " + error.getDefaultMessage());
					}
				return "vacantes/formVacante";
				}
			if (!multiPart.isEmpty()) {
				String ruta = "/home/preacher/empleos/img-vacantes/"; // Linux/MAC
				String nombreImagen = Utileria.guardarArchivo(multiPart, ruta);
					if (nombreImagen != null){ // La imagen si se subio
						// Procesamos la variable nombreImagen
						vacante.setImage(nombreImagen);
				}
				}
			
			serviceVacantes.guardar(vacante);
			attributes.addFlashAttribute("msg", "Registro Guardado");
			System.out.print("Vacante " + vacante);
			return "redirect:/vacantes/index";
		}
		/*
		@PostMapping("/save")
		public String guardar ( @RequestParam ("nombre") String nombre, @RequestParam("descripcion") String descripcion,
				@RequestParam("estatus") String estatus, @RequestParam("fecha") String fecha, @RequestParam("destacado") int destacado,
				@RequestParam("salario") double salario, @RequestParam("detalles") String detalles) {
			System.out.print("Nombre Vacante" + nombre);
			System.out.print("Descripcion " + descripcion);
			System.out.print("estatus " + estatus);
			System.out.print("fecha " + fecha);
			System.out.print("destacado " + destacado);
			System.out.print("salario " + salario);
			System.out.print("detalles " + detalles);
			return "vacantes/listVacante";
		}
	*/
		@GetMapping("/delete")
		public String eliminar(@RequestParam("id") int idVacante, Model model) {
			System.out.print("Borrando vacante con Id: " +idVacante);
			model.addAttribute("id", idVacante);
			return "mensaje";
			
		}
		
		@GetMapping("/view/{id}")
		public String verDetalle( @PathVariable("id") int idVacante, Model model) {
			Vacante vacante = serviceVacantes.buscarPorId(idVacante);
			System.out.println("Vacante: " + vacante);
			model.addAttribute("vacante", vacante);
			return "detalle";
			
		}
		@InitBinder
		public void initBinder(WebDataBinder webDataBinder) {
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
		}
}
