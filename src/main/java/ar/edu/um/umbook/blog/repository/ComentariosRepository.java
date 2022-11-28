package ar.edu.um.umbook.blog.repository;

import ar.edu.um.umbook.blog.domain.Comentarios;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Comentarios entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ComentariosRepository extends JpaRepository<Comentarios, Long> {}
