
package controllers.administrador;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import domain.Tutorial;
import services.TutorialService;

@Controller
@RequestMapping("administrator/tutorial")
public class TutorialAdministradorController {

	///Servicios
	TutorialService tutorialService;


	@Autowired
	public TutorialAdministradorController(final TutorialService tutorialService) {
		this.tutorialService = tutorialService;
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		final ModelAndView result;
		Collection<Tutorial> tutoriales;

		result = new ModelAndView("administrator/tutorial/list");

		try {
			tutoriales = this.tutorialService.findAll();

			final List<Tutorial> tutorialesOrdenados = tutoriales.stream().sorted(Comparator.comparingInt(Tutorial::getVisualizaciones).reversed()).collect(Collectors.toList());

			result.addObject("tutorials", tutorialesOrdenados);
		} catch (final Exception e) {

		}
		return result;
	}
}
