
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
import services.AcademiaService;
import services.CursoService;

@Controller
@RequestMapping("/course")
public class CursoController extends AbstractController {

	/// Cargamos el servicio
	CursoService	cursoService;
	AcademiaService	academiaService;


	@Autowired
	public CursoController(final CursoService cursoService) {
		this.cursoService = cursoService;
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Curso> cursos;

		cursos = this.cursoService.findAll();
		result = new ModelAndView("course/list");

		result.addObject("courses", cursos);

		return result;
	}

	@RequestMapping(value = "/listByAcademiaId", method = RequestMethod.GET)
	public ModelAndView listByAcademia(@RequestParam("academyId") final int id) {
		ModelAndView result;
		Collection<Curso> cursos;
		cursos = this.cursoService.findAllByAcademiaId(id);
		result = new ModelAndView("course/list");

		result.addObject("courses", cursos);

		return result;
	}

	@RequestMapping(value = "/listbyestiloid", method = RequestMethod.GET)
	public ModelAndView listByEstiloId(@RequestParam(value = "styleId") final int id) {
		ModelAndView result;
		Collection<Curso> cursos;

		cursos = this.cursoService.findByEstilo(id);
		result = new ModelAndView("course/list");

		result.addObject("courses", cursos);

		return result;
	}

	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public ModelAndView view(@RequestParam("courseId") final int courseid) {
		ModelAndView result;
		Curso curso;

		curso = this.cursoService.findById(courseid);

		result = new ModelAndView("course/view");
		result.addObject("course", curso);

		return result;

	}
}
