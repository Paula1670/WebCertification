
package controllers.comun;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import controllers.AbstractController;
import domain.Estilo;
import services.EstiloService;

@Controller
@RequestMapping("/style")
public class EstiloController extends AbstractController {

	/// Cargamos el servicio
	EstiloService estiloService;


	@Autowired
	public EstiloController(final EstiloService estiloService) {
		this.estiloService = estiloService;
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Estilo> estilos;

		estilos = this.estiloService.findAll();
		result = new ModelAndView();

		result.addObject("styles", estilos);
		result.addObject("requestURI", "/style/list.do");
		return result;
	}

	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public ModelAndView view(@RequestParam("styleId") final int styleId) {
		ModelAndView result;
		Estilo estilo;

		estilo = this.estiloService.findById(styleId);
		result = new ModelAndView("style/view");

		result.addObject("style", estilo);

		return result;
	}
}
