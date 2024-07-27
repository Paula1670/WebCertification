
package services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import domain.Actores.Alumno;
import repositories.AlumnoRepository;

@Service
@Transactional
public class AlumnoService {

	@Autowired
	private AlumnoRepository alumnoRepository;


	public List<Alumno> getAllAlumnos() {
		return this.alumnoRepository.findAll();
	}

	public Alumno getAlumnoById(final Long id) {
		return this.alumnoRepository.findOne(id);
	}

	public Alumno saveAlumno(final Alumno alumno) {
		return this.alumnoRepository.save(alumno);
	}

	public void deleteAlumno(final Long id) {
		this.alumnoRepository.delete(id);
	}
}
