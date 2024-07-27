
package controllers.comun;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import controllers.AbstractController;
import domain.Actores.Academia;
import services.AcademiaService;
import services.CursoService;

@Controller
@RequestMapping("/academy")
public class AcademiaController extends AbstractController {

	/// Cargamos los servicios que emplea la vista
	AcademiaService	academiaService;
	CursoService	cursoService;


	@Autowired
	public AcademiaController(final AcademiaService academiaService, final CursoService cursoService) {
		this.academiaService = academiaService;
		this.cursoService = cursoService;
	}

	//Listar

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		final ModelAndView result;
		final Collection<Academia> academias;

		academias = this.academiaService.getAllAcademias();

		result = new ModelAndView("academy/list");
		result.addObject("academies", academias);
		return result;

	}

	@RequestMapping(value = "/listbycurso", method = RequestMethod.GET)
	public ModelAndView listByCursoId(@RequestParam(value = "courseId") final int cursoId) {
		ModelAndView result;
		Academia academia;

		academia = this.academiaService.findByCursoId(cursoId);

		result = new ModelAndView("academy/list");
		result.addObject("academies", academia);

		return result;
	}

}
