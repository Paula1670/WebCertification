
package controllers.administrador;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.AdministradorService;

@Controller
@RequestMapping("administrator/dashboard")
public class DashBoardAdministradorController {

	////Servicios
	AdministradorService administradorService;


	@Autowired
	public DashBoardAdministradorController(final AdministradorService administradorService) {
		this.administradorService = administradorService;
	}

	@RequestMapping(value = "/statistics", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		final Map<String, Map<String, Double>> estadisticas = this.administradorService.calcularEstadisticas();
		///Cargamos los Objetos

		result = new ModelAndView("administrator/dashboard/statistics");
		result.addObject("statistics", estadisticas);

		return result;
	}
}
