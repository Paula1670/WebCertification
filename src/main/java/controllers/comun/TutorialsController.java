
package controllers.comun;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import controllers.AbstractController;
import domain.Curso;
import domain.Tutorial;
import services.AcademiaService;
import services.CursoService;
import services.TutorialService;

@Controller
@RequestMapping("/tutorials")
public class TutorialsController extends AbstractController {

	/// Cargamos el servicio
	TutorialService	tutorialService;
	AcademiaService	academiaService;


	@Autowired
	public TutorialsController(final TutorialService tutorialService) {
		this.tutorialService = tutorialService;
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Tutorial> tutorials;

		tutorials = this.tutorialService.findAll();
		result = new ModelAndView("tutorials/list");

		result.addObject("tutorials", tutorials);

		return result;
	}

	@RequestMapping(value = "/listByAcademiaId", method = RequestMethod.GET)
	public ModelAndView listByAcademia(@RequestParam("academyId") final int id) {
		ModelAndView result;
		Collection<Tutorial> tutorials;

		tutorials = this.tutorialService.findAllByAcademiaId(id);
		System.out.println(tutorials);
		result = new ModelAndView("tutorials/list");

		result.addObject("tutorials", tutorials);

		return result;
	}

/*	@RequestMapping(value = "/listbyestiloid", method = RequestMethod.GET)
	public ModelAndView listByEstiloId(@RequestParam(value = "styleId") final int id) {
		ModelAndView result;
		Collection<Curso> cursos;

		cursos = this.cursoService.findByEstilo(id);
		result = new ModelAndView("tutorials/list");

		result.addObject("courses", cursos);

		return result;
	}
*/
/*	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public ModelAndView view(@RequestParam("courseId") final int courseid) {
		ModelAndView result;
		Curso curso;

		curso = this.cursoService.findById(courseid);

		result = new ModelAndView("tutorials/view");
		result.addObject("course", curso);

		return result;

	}*/
}
