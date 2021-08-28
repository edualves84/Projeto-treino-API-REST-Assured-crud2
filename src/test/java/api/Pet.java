package api;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class Pet {
   int petId = 2007;
    String uri = "https://petstore.swagger.io/v2/pet";
    public String lerJson (String caminhoJson) throws IOException {
        return new String(Files.readAllBytes(Paths.get(caminhoJson)));
    }
   @Test
    public void incluirPet() throws IOException {
        String jsonBody = lerJson ("src/test/resources/data/pet.json");
        given()
                .contentType("application/json")
                .log().all()
                .body(jsonBody)
        .when()
                .post(uri)
        .then()
                .log().all()
                .statusCode(200)
                .body("id", is (2007));


   }
   @Test
    public void alterarStatusPet() throws IOException {
       String jsonBody = lerJson("src/test/resources/data/statuspet.json");
       given()
               .contentType("application/json")
               .log().all()
               .body(jsonBody)
       .when()
               .put(uri)
       .then()
               .log().all()
               .statusCode(200)
               .body("status", is ("sold"));


   }

}
    /*Observação importante: a inclusão do usuario, animal e venda realizada com sucesso, porém plataforma de treino API
      swagger esta com falha na inclusao da venda, pois não libera opção para armazenar a variavel na memoria do usuario
      que ira realizar a compra do animal
     */
