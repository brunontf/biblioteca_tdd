package br.com.ada.biblioteca_tdd.cucumber_glue;

import io.restassured.http.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import br.com.ada.biblioteca_tdd.controller.LivroController;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import io.cucumber.spring.CucumberContextConfiguration;
import io.restassured.module.mockmvc.RestAssuredMockMvc;

@CucumberContextConfiguration
@SpringBootTest
@AutoConfigureMockMvc

public class ListarLivrosSteps {
    @Autowired
    LivroController livroController = new LivroController();

     @Quando("o usuario clicar em listar livros")
     public void oUsuarioClicarEmListarLivros() {
     }

     @Entao("o usuario deve obter a lista de livros cadastrados")
     public void oUsuarioDeveObterAListaDeLivrosCadastrados() {
         RestAssuredMockMvc.given().standaloneSetup(livroController)
                 .when().get("/livros")
                 .then().statusCode(200)
                        .contentType(ContentType.JSON);
     }

}
