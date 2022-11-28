package ar.edu.um.umbook.blog.repository;

import ar.edu.um.umbook.blog.domain.Fotos;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Fotos entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FotosRepository extends JpaRepository<Fotos, Long> {}
