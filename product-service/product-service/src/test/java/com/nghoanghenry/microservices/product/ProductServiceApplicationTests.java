package com.nghoanghenry.microservices.product;

import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Import;
import org.testcontainers.containers.MongoDBContainer;

@Import(TestcontainersConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductServiceApplicationTests {
	@ServiceConnection
	static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:latest");

	@LocalServerPort
	private Integer port;

	@BeforeEach
	void setUp() {
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = port;
	}

	static {
		mongoDBContainer.start();
	}

	@Test
	void shouldCreateProduct() {
		String requestBody = """
				{
					"id": "6852cda27d0eea2a00b30723",
					"name": "iPhone 16",
					"description": "iPhone 16 with A18 Pro chip, 6.3-inch OLED display, upgraded camera system, and longer battery life.",
					"price": 999
				}
				""";
		RestAssured.given()
				.body(requestBody)
				.contentType("application/json")
				.when()
				.post("/api/product")
				.then()
				.statusCode(201)
				.body("id", Matchers.notNullValue())
				.body("name", Matchers.equalTo("iPhone 16"))
				.body("description", Matchers.equalTo("iPhone 16 with A18 Pro chip, 6.3-inch OLED display, upgraded camera system, and longer battery life."))
				.body("price", Matchers.equalTo(999));

	}

}
