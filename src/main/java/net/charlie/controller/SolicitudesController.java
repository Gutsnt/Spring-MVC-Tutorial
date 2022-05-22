package net.charlie.controller;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import net.charlie.model.Solicitud;
import net.charlie.model.Usuario;
import net.charlie.model.Vacante;
import net.charlie.service.ICategoriasService;
import net.charlie.service.ISolicitudesService;
import net.charlie.service.IUsuariosService;
import net.charlie.service.IVacantesService;
import net.charlie.util.Utileria;

@Controller
@RequestMapping("/solicitudes")

public class SolicitudesController {
	
	@Autowired
	private ISolicitudesService serviceSolicitud;
	
	@Autowired
	private IVacantesService serviceVacantes;
	
    @Autowired
   	private IUsuariosService serviceUsuarios;
	
	/**
	 * EJERCICIO: Declarar esta propiedad en el archivo application.properties. El valor sera el directorio
	 * en donde se guardarán los archivos de los Curriculums Vitaes de los usuarios.
	 */
	@Value("${empleosapp.ruta.cv}")
	private String ruta;
		
    /**
	 * Metodo que muestra la lista de solicitudes sin paginacion
	 * Seguridad: Solo disponible para un usuarios con perfil ADMINISTRADOR/SUPERVISOR.
	 * @return
	 */
    @GetMapping("/index") 
	public String mostrarIndex(Model model) {
    	List<Solicitud> lista = serviceSolicitud.buscarTodas();
		model.addAttribute("solicitudes", lista);
		return "solicitudes/listSolicitudes";	
	}
    
    /**
	 * Metodo que muestra la lista de solicitudes con paginacion
	 * Seguridad: Solo disponible para usuarios con perfil ADMINISTRADOR/SUPERVISOR.
	 * @return
	 */
	@GetMapping("/indexPaginate")
	public String mostrarIndexPaginado(Model model, Pageable page) {
		Page<Solicitud> lista = serviceSolicitud.buscarTodas(page);
		model.addAttribute("solicitudes", lista);
		return "solicitudes/listSolicitudes";
	}
    
	/**
	 * Método para renderizar el formulario para aplicar para una Vacante
	 * Seguridad: Solo disponible para un usuario con perfil USUARIO.
	 * @return
	 */
	@GetMapping("/create/{idVacante}")
	public String crear(Solicitud solicitud, @PathVariable("idVacante") int idVacante,Model model ) {
		Vacante vacante = serviceVacantes.buscarPorId(idVacante);
		model.addAttribute("vacante", vacante);		
		return "solicitudes/formSolicitud";
		
	}
	
	/**
	 * Método que guarda la solicitud enviada por el usuario en la base de datos
	 * Seguridad: Solo disponible para un usuario con perfil USUARIO.
	 * @return
	 */
	@PostMapping("/save")
	public String guardar(Solicitud solicitud, BindingResult result, RedirectAttributes attributes,
			@RequestParam("archivoCv") MultipartFile multiPart, Authentication authentication ) {
		// Recuperamos el username que inicio sesión
		
		String username = authentication.getName();
		
			if (result.hasErrors()){
			System.out.println("Existieron errores");
			return "solicitudes/formSolicitud";
		}
		if (!multiPart.isEmpty()) {
			//String ruta = "/home/preacher/empleos/img-vacantes/"; // Linux/MAC
			String nombreCv = Utileria.guardarArchivo(multiPart, ruta);
				if (nombreCv != null){ // La imagen si se subio
					// Procesamos la variable nombreImagen
					solicitud.setArchivo(nombreCv);
			}
		}
		// Buscamos el objeto Usuario en BD	
		Usuario usuario = serviceUsuarios.buscarPorUsername(username);		
				
		solicitud.setUsuario(usuario); // Referenciamos la solicitud con el usuario 
		solicitud.setFecha(new Date());
		
		serviceSolicitud.guardar(solicitud);
		attributes.addFlashAttribute("msg", "Gracias por enviar tu CV!");
		System.out.print("Solicitud :" + solicitud);
		return "redirect:/";	
		
	}
	
	/**
	 * Método para eliminar una solicitud
	 * Seguridad: Solo disponible para usuarios con perfil ADMINISTRADOR/SUPERVISOR. 
	 * @return
	 */
	@GetMapping("/delete/{id}")
	public String eliminar(@PathVariable("id") int idSolicitud, RedirectAttributes attributes) {
		serviceSolicitud.eliminar(idSolicitud);
		attributes.addFlashAttribute("msg", "La vacante fue eliminada!");
		return "redirect:/solicitudes/indexPaginate";
	}
			
	/**
	 * Personalizamos el Data Binding para todas las propiedades de tipo Date
	 * @param webDataBinder
	 */
	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
	
	
}
