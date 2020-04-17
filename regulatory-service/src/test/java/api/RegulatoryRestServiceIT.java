package api;

import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.containsString;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import io.restassured.RestAssured;

public class RegulatoryRestServiceIT {

	@BeforeAll
	public static void setup() {
		RestAssured.baseURI = "http://localhost:28080/regulatory";
		RestAssured.port = 8080;
	}


	@Test
	public void testGet() {
		when().get("/").then().body(containsString("regulated"));
	}


}