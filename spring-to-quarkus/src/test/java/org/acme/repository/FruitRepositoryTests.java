package org.acme.repository;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import org.acme.domain.Fruit;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@QuarkusTest
@TestTransaction
class FruitRepositoryTests {
	@Autowired
	FruitRepository fruitRepository;

	@Test
	public void findByName() {
		this.fruitRepository.save(new Fruit(null, "Grapefruit", "Summer fruit"));

		Optional<Fruit> fruit = this.fruitRepository.findByName("Grapefruit");
		assertThat(fruit)
			.isNotNull()
			.isPresent()
			.get()
			.extracting(Fruit::getName, Fruit::getDescription)
			.containsExactly("Grapefruit", "Summer fruit");

		assertThat(fruit.get().getId())
			.isNotNull()
			.isGreaterThan(2L);
	}
}
