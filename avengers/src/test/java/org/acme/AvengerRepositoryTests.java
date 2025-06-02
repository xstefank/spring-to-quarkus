package org.acme;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@QuarkusTest
@Transactional
public class AvengerRepositoryTests {

    @Inject
    AvengerRepository avengerRepository;

    @Test
    public void findByName() {
        avengerRepository.save(new Avenger("Falcon", "Sam Wilson", true));

        Optional<Avenger> avenger = avengerRepository.findByName("Falcon");
        assertThat(avenger)
            .isPresent()
            .isNotNull()
            .get()
            .extracting(Avenger::getName, Avenger::getCivilname, Avenger::isSnapped)
            .containsExactly("Falcon", "Sam Wilson", true);

        assertThat(avenger.get().getId())
            .isNotNull()
            .isGreaterThan(6L);
    }
}
