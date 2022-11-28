package ar.edu.um.umbook.blog.repository;

import ar.edu.um.umbook.blog.domain.Amigos;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Amigos entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AmigosRepository extends JpaRepository<Amigos, Long> {}
