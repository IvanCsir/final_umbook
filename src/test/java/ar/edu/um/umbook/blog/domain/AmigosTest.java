package ar.edu.um.umbook.blog.domain;

import static org.assertj.core.api.Assertions.assertThat;

import ar.edu.um.umbook.blog.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class AmigosTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Amigos.class);
        Amigos amigos1 = new Amigos();
        amigos1.setId(1L);
        Amigos amigos2 = new Amigos();
        amigos2.setId(amigos1.getId());
        assertThat(amigos1).isEqualTo(amigos2);
        amigos2.setId(2L);
        assertThat(amigos1).isNotEqualTo(amigos2);
        amigos1.setId(null);
        assertThat(amigos1).isNotEqualTo(amigos2);
    }
}
