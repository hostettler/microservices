package ch.unige.api;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class CounterpartyRestServiceTest {

    @Test
    public void testCounterpartyList() {
        given()
          .when().get("/counterparties")
          .then()
             .statusCode(200)
             .body(is("[]"));
    }

    @Test
    public void testCounterpartyCount() {
        given()
          .when().get("/counterparties/count")
          .then()
             .statusCode(200)
             .body(is("0"));
    }

}