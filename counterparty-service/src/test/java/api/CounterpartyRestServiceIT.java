package api;

import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.containsString;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import io.restassured.RestAssured;

public class CounterpartyRestServiceIT {

	@BeforeAll
	public static void setup() {
		RestAssured.baseURI = "http://localhost:28080/counterparties";
		RestAssured.port = 8080;
	}

	@Test
	public void testGetAll() {
		when().get("/").then().body(containsString("984500C2EEUEB4A0C629"));

	}

	@Test
	public void testCount() {
		when().get("/count").then().body(containsString("5"));
	}

	@Test
	public void testGet() {
		when().get("/984500C2EEUEB4A0C629").then().body(containsString("984500C2EEUEB4A0C629"));
	}

}