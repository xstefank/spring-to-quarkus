package org.acme;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@Import(ContainersConfig.class)
public class AvengerRepositoryTests {

    @Autowired
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
