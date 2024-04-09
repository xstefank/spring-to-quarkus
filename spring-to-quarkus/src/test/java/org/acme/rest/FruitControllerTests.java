package org.acme.rest;

import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.acme.domain.Fruit;
import org.acme.repository.FruitRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

@QuarkusTest
class FruitControllerTests {

	@InjectMock
	FruitRepository fruitRepository;

	@Test
	public void getAll() {
		Mockito.when(this.fruitRepository.findAll())
			.thenReturn(List.of(new Fruit(1L, "Apple", "Hearty Fruit")));

		given().get("/fruits")
			.then()
			.statusCode(200)
			.contentType(ContentType.JSON)
			.body("size()", is(1));
//			.andExpect(jsonPath("[0].id").value(1))
//			.andExpect(jsonPath("[0].name").value("Apple"))
//			.andExpect(jsonPath("[0].description").value("Hearty Fruit"));

		Mockito.verify(this.fruitRepository).findAll();
		Mockito.verifyNoMoreInteractions(this.fruitRepository);
	}


//	@Test
//	public void getFruitFound() throws Exception {
//		Mockito.when(this.fruitRepository.findByName(Mockito.eq("Apple")))
//			.thenReturn(Optional.of(new Fruit(1L, "Apple", "Hearty Fruit")));
//
//		this.mockMvc.perform(get("/fruits/Apple"))
//			.andExpect(status().isOk())
//			.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
//			.andExpect(jsonPath("id").value(1))
//			.andExpect(jsonPath("name").value("Apple"))
//			.andExpect(jsonPath("description").value("Hearty Fruit"));
//
//		Mockito.verify(this.fruitRepository).findByName(Mockito.eq("Apple"));
//		Mockito.verifyNoMoreInteractions(this.fruitRepository);
//	}
//
//	@Test
//	public void getFruitNotFound() throws Exception {
//		Mockito.when(this.fruitRepository.findByName(Mockito.eq("Apple")))
//			.thenReturn(Optional.empty());
//
//		this.mockMvc.perform(get("/fruits/Apple"))
//			.andExpect(status().isNotFound());
//
//		Mockito.verify(this.fruitRepository).findByName(Mockito.eq("Apple"));
//		Mockito.verifyNoMoreInteractions(this.fruitRepository);
//	}
//
//	@Test
//	public void addFruit() throws Exception {
//		Mockito.when(this.fruitRepository.save(Mockito.any(Fruit.class)))
//			.thenReturn(new Fruit(1L, "Grapefruit", "Summer fruit"));
//
//		this.mockMvc.perform(
//			post("/fruits")
//				.contentType(MediaType.APPLICATION_JSON)
//				.content("{\"name\":\"Grapefruit\",\"description\":\"Summer fruit\"}")
//		)
//			.andExpect(status().isOk())
//			.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
//			.andExpect(jsonPath("name").value("Grapefruit"))
//			.andExpect(jsonPath("description").value("Summer fruit"));
//
//		Mockito.verify(this.fruitRepository).save(Mockito.any(Fruit.class));
//		Mockito.verifyNoMoreInteractions(this.fruitRepository);
//	}
}
