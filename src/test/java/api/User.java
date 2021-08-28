package api;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class User {
    String uri = "https://petstore.swagger.io/v2/user";
    int userId = 1984;
    String userName ="ioriyagami";
    int password = 123456;
    String token = "";

    public String lerJson (String caminhoJson) throws IOException {
        return new String(Files.readAllBytes(Paths.get(caminhoJson)));

    }
     @Test
    public void incluirUsuario() throws IOException {
       String jsonBody = lerJson("src/test/resources/data/user.json");

       given()
               .contentType("application/json")
               .log().all()
               .body(jsonBody)
       .when()
               .post(uri)
       .then()
               .log().all()
               .statusCode(200)
               .body("type",is ("unknown"))
               .body("message", is(Integer.toString(userId)));

     }
         @Test
         public void consultarUsuario(){

        given()
                .contentType("application/json")
                .log().all()
        .when()
                .get(uri+"/"+ userName)
        .then()
                .log().all()
                .statusCode(200)
                .body("id" , is(userId))
                .body("username", is(userName))
                .body("firstName", is("iori"))
                .body("lastName", is ("yagami"))
                .body("lastName", is ("yagami123@hotmail.com"));

         }

         @Test
        public void loginUsuario(){
       String mensagem =


        given()
                .contentType("application/json")
                .log().all()
        .when()
                .get(uri+"/login?username"+userName+"&password"+password)

        .then()
                .log().all()
                .statusCode(200)
                .body("type",is ("unknown"))
                .extract()
                .path("message")
              ;
                System.out.println("A mensagem é :" + mensagem);
                token = mensagem.substring(23);
                System.out.println("O token é :" + token);
         }

}



