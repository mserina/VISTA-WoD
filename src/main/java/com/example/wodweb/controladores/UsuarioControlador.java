package com.example.wodweb.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.wodweb.dtos.UsuarioDto;
import com.example.wodweb.servicios.UsuarioServicio;

@Controller
public class UsuarioControlador {

	
	private UsuarioServicio usuarioServicio = new UsuarioServicio();
	
	  @GetMapping("/admin/obtenerUsuario")
		public String obtenerUsuarios(Model model) {
			model.addAttribute("usuarios", usuarioServicio.obtenerUsuarios());
			return "usuarios";
		}
	  
	  @GetMapping("/registro")
	  public String mostrarFormularioRegistro() {
	      return "registro"; // Nombre del template de Thymeleaf: registro.html
	  }
	  
	  @PostMapping("/registroDatos")
	    public String registrarUsuario(UsuarioDto usuarioCredenciales, Model model, RedirectAttributes redirectAttributes) {
	       
		  UsuarioDto usuarioRegistrado = usuarioServicio.registrarUsuario(usuarioCredenciales);
	        if (usuarioRegistrado != null) {
	        	redirectAttributes.addFlashAttribute("mensaje", "Registro exitoso. ¡Bienvenido!");
	            model.addAttribute("mensaje", "Registro exitoso");
	            return "bienvenida"; // O a otra vista
	        } else {
	        	redirectAttributes.addFlashAttribute("mensaje", "Error en el registro. Inténtelo de nuevo.");
	            model.addAttribute("error", "Error en el registro");
	            return "registro"; // Regresa al formulario
	        }
	    }
	  
	  @PostMapping("/admin/editarUsuario")
	  public String editarUsuario(@RequestParam String correoElectronico, @RequestParam String campo, @RequestParam String nuevoValor, RedirectAttributes redirectAttributes) {
	      boolean actualizado = usuarioServicio.editarUsuario(correoElectronico, campo, nuevoValor);
	      
	      if (actualizado) {
	          redirectAttributes.addFlashAttribute("mensaje", "Usuario actualizado con éxito.");
	          redirectAttributes.addFlashAttribute("tipoMensaje", "success");
	      } else {
	          redirectAttributes.addFlashAttribute("mensaje", "Error al actualizar usuario.");
	          redirectAttributes.addFlashAttribute("tipoMensaje", "danger");
	      }
	      
	      return "redirect:/admin/obtenerUsuario"; // Redirigir de nuevo a la lista de usuarios
	  }
	  
	  @PostMapping("/admin/borrarUsuario")
	    public String borrarUsuario(@RequestParam Long id, RedirectAttributes redirectAttributes) {
	        boolean borrado = usuarioServicio.borrarUsuario(id);
	        if (borrado) {
	            redirectAttributes.addFlashAttribute("mensaje", "Usuario borrado correctamente.");
	        } else {
	            redirectAttributes.addFlashAttribute("mensaje", "Error al borrar el usuario.");
	        }
	        return "redirect:/admin/obtenerUsuario"; // Redirige a la vista con la lista de usuarios
	    }

}
