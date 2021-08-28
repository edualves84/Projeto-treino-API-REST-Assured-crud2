package api;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class Store {

     String uri= "https://petstore.swagger.io/v2/store/order";
     public String lerJson (String caminhoJson) throws IOException {

     return new String(Files.readAllBytes(Paths.get(caminhoJson)));

}
 @Test
    public void incluirVendaPet() throws IOException {

         String jsonBody = lerJson("src/test/resources/data/store.json");
         given()
                 .contentType("application/json")
                 .log().all()
                 .body(jsonBody)
         .when()
                 .post(uri)
         .then()
                 .log().all()
                 .statusCode(200)
                 .body("id",is (2008));

 }


}
