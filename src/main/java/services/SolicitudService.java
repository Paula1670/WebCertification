
package services;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Solicitud;
import domain.Enumeraciones.SolicitudEstado;
import repositories.SolicitudRepository;

@Service
@Transactional
public class SolicitudService {

	private final SolicitudRepository solicitudRepository;


	@Autowired
	public SolicitudService(final SolicitudRepository solicitudRepository) {
		this.solicitudRepository = solicitudRepository;
	}

	public Solicitud create() {
		Solicitud solicitud;
		solicitud = new Solicitud();
		return solicitud;
	}

	public Solicitud save(final Solicitud solicitud) {
		assert solicitud != null;

		final Collection<Solicitud> solicitudes = this.solicitudRepository.findAllByAlumnoAndCursoId(solicitud.getAlumno().getId(), solicitud.getCurso().getId());

		// Se asegura que no exista mas de dos solicitudes por el mismo alumno al mismo curso
		Assert.isTrue(solicitudes.isEmpty(), "Ya existe una solicitud para este alumno y curso");

		return this.solicitudRepository.save(solicitud);
	}

	public Solicitud findById(final int id) {
		Assert.isTrue(id > 0, "El ID debe de ser mayor que cero!");

		Solicitud result;
		result = this.solicitudRepository.findById(id);
		Assert.notNull(result);

		return result;
	}

	public Collection<Solicitud> findAll() {
		Collection<Solicitud> result;

		result = this.solicitudRepository.findAll();
		Assert.notNull(result);

		return result;
	}

	public Collection<Solicitud> findAllByEstado(final SolicitudEstado estado) {
		Collection<Solicitud> result;

		result = this.solicitudRepository.findAllByEstado(estado);
		Assert.notNull(result);

		return result;
	}

	public Collection<Solicitud> findAllByAlumnoId(final int alumnoID) {
		Collection<Solicitud> result;

		result = this.solicitudRepository.findAllByAlumnoId(alumnoID);
		Assert.notNull(result);

		return result;
	}

	public Collection<Solicitud> findAllByCursoId(final int cursoID) {
		Collection<Solicitud> result;

		result = this.solicitudRepository.findAllByCursoId(cursoID);
		Assert.notNull(result);

		return result;
	}

	public Collection<Solicitud> findAllByAlumnoAndCursoId(final int alumnoID, final int cursoID) {
		Collection<Solicitud> result;

		result = this.solicitudRepository.findAllByAlumnoAndCursoId(alumnoID, cursoID);
		Assert.notNull(result);

		return result;
	}

	public Collection<Solicitud> findAllByDate(final Date fecha) {
		Collection<Solicitud> result;

		result = this.solicitudRepository.findAllByDate(fecha);
		Assert.notNull(result);

		return result;
	}

	public Solicitud update(final Solicitud solicitud) {
		Assert.notNull(solicitud);

		if (this.solicitudRepository.exists(solicitud.getId()))
			return this.solicitudRepository.save(solicitud);
		else
			throw new IllegalArgumentException("La solicitud con ID " + solicitud.getId() + " no existe!");
	}

	public void delete(final Solicitud solicitud) {
		Assert.notNull(solicitud);

		if (this.solicitudRepository.exists(solicitud.getId()))
			this.solicitudRepository.delete(solicitud);
		else
			throw new IllegalArgumentException("La solicitud con ID " + solicitud.getId() + " no existe!");
	}

	public void deleteById(final int id) {
		Assert.isTrue(id > 0, "El ID debe de ser mayor que cero!");

		if (this.solicitudRepository.exists(id))
			this.solicitudRepository.delete(id);
		else
			throw new IllegalArgumentException("La solicitud con ID " + id + " no existe!");
	}
}
