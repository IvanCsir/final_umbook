package ar.edu.um.umbook.blog.web.rest;

import ar.edu.um.umbook.blog.domain.Admin;
import ar.edu.um.umbook.blog.repository.AdminRepository;
import ar.edu.um.umbook.blog.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link ar.edu.um.umbook.blog.domain.Admin}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class AdminResource {

    private final Logger log = LoggerFactory.getLogger(AdminResource.class);

    private static final String ENTITY_NAME = "admin";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AdminRepository adminRepository;

    public AdminResource(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    /**
     * {@code POST  /admins} : Create a new admin.
     *
     * @param admin the admin to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new admin, or with status {@code 400 (Bad Request)} if the admin has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/admins")
    public ResponseEntity<Admin> createAdmin(@RequestBody Admin admin) throws URISyntaxException {
        log.debug("REST request to save Admin : {}", admin);
        if (admin.getId() != null) {
            throw new BadRequestAlertException("A new admin cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Admin result = adminRepository.save(admin);
        return ResponseEntity
            .created(new URI("/api/admins/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /admins/:id} : Updates an existing admin.
     *
     * @param id the id of the admin to save.
     * @param admin the admin to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated admin,
     * or with status {@code 400 (Bad Request)} if the admin is not valid,
     * or with status {@code 500 (Internal Server Error)} if the admin couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/admins/{id}")
    public ResponseEntity<Admin> updateAdmin(@PathVariable(value = "id", required = false) final Long id, @RequestBody Admin admin)
        throws URISyntaxException {
        log.debug("REST request to update Admin : {}, {}", id, admin);
        if (admin.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, admin.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!adminRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Admin result = adminRepository.save(admin);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, admin.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /admins/:id} : Partial updates given fields of an existing admin, field will ignore if it is null
     *
     * @param id the id of the admin to save.
     * @param admin the admin to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated admin,
     * or with status {@code 400 (Bad Request)} if the admin is not valid,
     * or with status {@code 404 (Not Found)} if the admin is not found,
     * or with status {@code 500 (Internal Server Error)} if the admin couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/admins/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Admin> partialUpdateAdmin(@PathVariable(value = "id", required = false) final Long id, @RequestBody Admin admin)
        throws URISyntaxException {
        log.debug("REST request to partial update Admin partially : {}, {}", id, admin);
        if (admin.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, admin.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!adminRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Admin> result = adminRepository
            .findById(admin.getId())
            .map(existingAdmin -> {
                if (admin.getNombre() != null) {
                    existingAdmin.setNombre(admin.getNombre());
                }
                if (admin.getApellido() != null) {
                    existingAdmin.setApellido(admin.getApellido());
                }
                if (admin.getMail() != null) {
                    existingAdmin.setMail(admin.getMail());
                }
                if (admin.getPassword() != null) {
                    existingAdmin.setPassword(admin.getPassword());
                }
                if (admin.getTipoUsuario() != null) {
                    existingAdmin.setTipoUsuario(admin.getTipoUsuario());
                }

                return existingAdmin;
            })
            .map(adminRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, admin.getId().toString())
        );
    }

    /**
     * {@code GET  /admins} : get all the admins.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of admins in body.
     */
    @GetMapping("/admins")
    public List<Admin> getAllAdmins() {
        log.debug("REST request to get all Admins");
        return adminRepository.findAll();
    }

    /**
     * {@code GET  /admins/:id} : get the "id" admin.
     *
     * @param id the id of the admin to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the admin, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/admins/{id}")
    public ResponseEntity<Admin> getAdmin(@PathVariable Long id) {
        log.debug("REST request to get Admin : {}", id);
        Optional<Admin> admin = adminRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(admin);
    }

    /**
     * {@code DELETE  /admins/:id} : delete the "id" admin.
     *
     * @param id the id of the admin to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/admins/{id}")
    public ResponseEntity<Void> deleteAdmin(@PathVariable Long id) {
        log.debug("REST request to delete Admin : {}", id);
        adminRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
