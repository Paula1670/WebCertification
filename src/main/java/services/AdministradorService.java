
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Comentario;
import domain.Curso;
import domain.Solicitud;
import domain.Tutorial;
import domain.Actores.Academia;
import domain.Actores.Actor;
import domain.Actores.Administrador;
import repositories.AdministradorRepository;

@Service
@Transactional
public class AdministradorService {

	/// Metricas que realiza
	private enum Metrica {
		MAXIMO, MINIMO, MEDIA, DESVIACION_TIPICA
	}


	/// Repositorio propio
	AdministradorRepository	administradorRepository;

	/// Servicios de apoyo
	AcademiaService			academiaService;
	CursoService			cursoService;
	TutorialService			tutorialService;
	ActorService			actorService;


	@Autowired
	public AdministradorService(final AdministradorRepository administradorRepository, final AcademiaService academiaService, final CursoService cursoService, final TutorialService tutorialService, final ActorService actorService) {
		this.administradorRepository = administradorRepository;
		this.academiaService = academiaService;
		this.cursoService = cursoService;
		this.tutorialService = tutorialService;
		this.actorService = actorService;
	}

	/// METODOS BASICOS
	public Administrador create() {
		Administrador result;

		result = new Administrador();

		return result;
	}

	public Collection<Administrador> findAll() {
		Collection<Administrador> result;

		result = this.administradorRepository.findAll();

		Assert.notEmpty(result);

		return result;
	}

	public Administrador findById(final int id) {
		Assert.isTrue(id != 0);
		Assert.isTrue(this.administradorRepository.exists(id));

		Administrador result;

		result = this.administradorRepository.findById(id);
		Assert.notNull(result);

		return result;
	}

	public Administrador save(final Administrador administrador) {
		Assert.notNull(administrador);
		Administrador result;

		result = this.administradorRepository.save(administrador);

		return result;
	}

	public void delete(final Administrador administrador) {
		Assert.notNull(administrador);
		Assert.isTrue(administrador.getId() != 0);
		Assert.isTrue(this.administradorRepository.exists(administrador.getId()));

		this.administradorRepository.delete(administrador);
	}

	/// METODOS EXTRAS

	public Map<String, Map<String, Double>> calcularEstadisticas() {
		final Map<String, Map<String, Double>> result = new HashMap<>();

		/// Tier C
		result.put("CursoPorAcademia", this.calcularCursosPorAcademia());
		result.put("SolicitudesPorCurso", this.calcularSolicitudesPorCurso());

		/// Tier B
		result.put("TutorialesPorAcademia", this.calcularTutorialesPorAcademia());
		result.put("TutorialesMostrados", this.calcularTutorialesMostrados());

		/// Tier A
		//result.put("ComentariosPorActor", this.calcularNumeroMedioComentariosPorActor());
		//result.put("SuscriptoresPorActor", this.calcularNumeroMedioComentariosPorSuscriptor());

		return result;
	}

	/// METODOS PARA EL CALCULO DE ESTADISTICAS

	// ********************************** TIER C **********************************

	public Map<String, Double> calcularCursosPorAcademia() {
		final Map<String, Double> result = new HashMap<>();
		Collection<Academia> academias;
		final List<Integer> conteoCursos = Collections.synchronizedList(new ArrayList<>());

		/// Obtenemos todas las academias
		academias = this.academiaService.getAllAcademias();

		/// Verificamos si es nulo o esta vacio
		if (academias == null || academias.isEmpty())
			return result;

		/// Realizamos un conteo del número de cursos
		for (final Academia academia : academias) {
			final Collection<Curso> cursos = academia.getCursos();
			if (cursos != null)
				conteoCursos.add(cursos.size());
			else
				conteoCursos.add(0);
		}

		/// Realizamos los cálculos necesarios
		final double media = this.calcularMedia(conteoCursos);
		final double max = this.calcularMaximo(conteoCursos);
		final double min = this.calcularMinimo(conteoCursos);
		final double desviacionTipica = this.calcularDesviacionTipica(conteoCursos);

		/// Los insertamos en el diccionario correspondiente
		result.put(Metrica.MEDIA.toString(), media);
		result.put(Metrica.MAXIMO.toString(), max);
		result.put(Metrica.MINIMO.toString(), min);
		result.put(Metrica.DESVIACION_TIPICA.toString(), desviacionTipica);

		return result;

	}
	////Falta a�adir findAll

	public Map<String, Double> calcularSolicitudesPorCurso() {
		final Map<String, Double> result = new HashMap<>();
		Collection<Curso> cursos;
		final List<Integer> conteoSolicitudes = Collections.synchronizedList(new ArrayList<>());

		/// Obtenemos todas los cursos
		cursos = this.cursoService.findAll();

		/// Verificamos si es nulo o esta vacio
		if (cursos == null || cursos.isEmpty())
			return result;

		for (final Curso curso : cursos) {
			final Collection<Solicitud> solicitudes = curso.getSolicitudes();
			if (solicitudes != null)
				conteoSolicitudes.add(solicitudes.size());
			else
				conteoSolicitudes.add(0);
		}

		/// Realizamos los cálculos necesarios
		final double media = this.calcularMedia(conteoSolicitudes);
		final double max = this.calcularMaximo(conteoSolicitudes);
		final double min = this.calcularMinimo(conteoSolicitudes);
		final double desviacionTipica = this.calcularDesviacionTipica(conteoSolicitudes);

		/// Los insertamos en el diccionario correspondiente
		result.put(Metrica.MEDIA.toString(), media);
		result.put(Metrica.MAXIMO.toString(), max);
		result.put(Metrica.MINIMO.toString(), min);
		result.put(Metrica.DESVIACION_TIPICA.toString(), desviacionTipica);

		return result;
	}

	// ********************************** TIER B **********************************

	public Map<String, Double> calcularTutorialesPorAcademia() {
		final Map<String, Double> result = new HashMap<>();
		Collection<Academia> academias;
		final List<Integer> conteoTutoriales = Collections.synchronizedList(new ArrayList<>());

		/// Obtenemos todas los cursos
		academias = this.academiaService.getAllAcademias();

		/// Verificamos si es nulo o no hay academias
		if (academias == null || academias.isEmpty())
			return result;

		/// Verificamos si es null o esta vacio
		for (final Academia academia : academias) {
			final Collection<Tutorial> tutoriales = academia.getTutoriales();
			if (tutoriales != null)
				conteoTutoriales.add(tutoriales.size());
			else
				conteoTutoriales.add(0);
		}

		/// Realizamos los calculos necesarios
		final double media = this.calcularMedia(conteoTutoriales);
		final double max = this.calcularMaximo(conteoTutoriales);
		final double min = this.calcularMinimo(conteoTutoriales);

		/// Los insertamos en el diccionario correspondiente
		result.put(Metrica.MEDIA.toString(), media);
		result.put(Metrica.MAXIMO.toString(), max);
		result.put(Metrica.MINIMO.toString(), min);

		return result;
	}

	public Map<String, Double> calcularTutorialesMostrados() {
		final Map<String, Double> result = new HashMap<>();
		Collection<Tutorial> tutoriales;
		final List<Integer> visualizacionesTutorial = Collections.synchronizedList(new ArrayList<>());

		/// Obtenemos todas los cursos
		tutoriales = this.tutorialService.findAll();

		/// Verificamos si es nulo o esta vacio
		if (tutoriales == null || tutoriales.isEmpty())
			return result;

		/// Verificamos si es null o esta vacio
		for (final Tutorial tutorial : tutoriales) {
			final int visualizaciones = tutorial.getVisualizaciones();
			visualizacionesTutorial.add(visualizaciones);
		}

		/// Realizamos los calculos necesarios
		final double media = this.calcularMedia(visualizacionesTutorial);
		final double max = this.calcularMaximo(visualizacionesTutorial);
		final double min = this.calcularMinimo(visualizacionesTutorial);

		/// Los insertamos en el diccionario correspondiente
		result.put(Metrica.MEDIA.toString(), media);
		result.put(Metrica.MAXIMO.toString(), max);
		result.put(Metrica.MINIMO.toString(), min);

		return result;
	}

	// ********************************** TIER A **********************************

	public Map<String, Double> calcularNumeroMedioComentariosPorActor() {
		final Map<String, Double> result = new HashMap<>();
		Collection<Actor> actores;
		final List<Integer> conteoComentarios = Collections.synchronizedList(new ArrayList<>());

		/// Obtenemos todas los cursos
		actores = this.actorService.findAll();

		/// Verificamos si es nulo o esta vacio
		if (actores == null || actores.isEmpty())
			return result;

		/// Verificamos si es null o esta vacio
		for (final Actor actor : actores) {
			final Collection<Comentario> comentarios = actor.getComentarios();
			if (comentarios != null)
				conteoComentarios.add(comentarios.size());
			else
				conteoComentarios.add(0);

		}

		/// Realizamos los calculos necesarios
		final double media = (int) this.calcularMedia(conteoComentarios);

		/// Los insertamos en el diccionario correspondiente
		result.put(Metrica.MEDIA.toString(), media);

		return result;
	}

	public Map<String, Double> calcularNumeroMedioComentariosPorSuscriptor() {
		final Map<String, Double> result = new HashMap<>();
		Collection<Actor> actores;
		final List<Integer> conteoSuscriptores = Collections.synchronizedList(new ArrayList<>());

		/// Obtenemos todas los cursos
		actores = this.actorService.findAll();

		/// Verificamos si es nulo o esta vacio
		if (actores == null || actores.isEmpty())
			return result;

		/// Verificamos si es null o esta vacio
		for (final Actor actor : actores) {
			final Collection<Actor> suscriptores = actor.getSuscriptores();
			if (suscriptores != null)
				conteoSuscriptores.add(suscriptores.size());
			else
				conteoSuscriptores.add(0);

		}

		/// Realizamos los calculos necesarios
		final double media = (int) this.calcularMedia(conteoSuscriptores);

		/// Los insertamos en el diccionario correspondiente
		result.put(Metrica.MEDIA.toString(), media);

		return result;
	}

	// CALCULO DE METRICAS
	private double calcularMedia(final List<Integer> conteo) {
		double result;

		final double sum = conteo.stream().mapToInt(Integer::intValue).sum();
		final double numElementos = conteo.size();

		try {
			result = sum / numElementos;
		} catch (final Exception e) {
			result = 0;
		}
		return result;
	}

	private int calcularMinimo(final List<Integer> conteo) {
		int result;

		result = conteo.stream().mapToInt(Integer::intValue).min().getAsInt();

		return result;
	}

	private int calcularMaximo(final List<Integer> conteo) {
		int result;

		result = conteo.stream().mapToInt(Integer::intValue).max().getAsInt();

		return result;
	}

	@SuppressWarnings("unused")
	private double calcularDesviacionTipica(final List<Integer> conteo, final double media) {
		double sumaDiferenciasCuadrado = 0;
		final int numElementos = conteo.size();

		for (final int elemento : conteo) {
			final double calculoaux = elemento - media;
			sumaDiferenciasCuadrado += Math.pow(calculoaux, 2);
		}

		final double varianza = sumaDiferenciasCuadrado / numElementos;

		return Math.sqrt(varianza);
	}

	private double calcularDesviacionTipica(final List<Integer> conteo) {
		final double media = this.calcularMedia(conteo);
		double sumaDiferenciasCuadrado = 0;
		final int numElementos = conteo.size();

		for (final int elemento : conteo) {
			final double calculoaux = elemento - media;
			sumaDiferenciasCuadrado += Math.pow(calculoaux, 2);
		}

		final double varianza = sumaDiferenciasCuadrado / numElementos;

		return Math.sqrt(varianza);
	}
}
