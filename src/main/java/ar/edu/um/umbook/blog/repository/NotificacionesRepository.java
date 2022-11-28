package ar.edu.um.umbook.blog.repository;

import ar.edu.um.umbook.blog.domain.Notificaciones;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Notificaciones entity.
 */
@SuppressWarnings("unused")
@Repository
public interface NotificacionesRepository extends JpaRepository<Notificaciones, Long> {}
