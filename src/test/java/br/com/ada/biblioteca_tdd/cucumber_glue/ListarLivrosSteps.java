package br.com.ada.biblioteca_tdd.cucumber_glue;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import br.com.ada.biblioteca_tdd.controller.LivroController;
import io.cucumber.java.Before;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import io.cucumber.spring.CucumberContextConfiguration;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// @CucumberContextConfiguration
@SpringBootTest
@AutoConfigureMockMvc

public class ListarLivrosSteps {
    @Autowired
    LivroController livroController = new LivroController();

    // @Before
    // public void initialiseRestAssuredMockMvcStandalone() {
    // RestAssuredMockMvc.standaloneSetup(new LivroController());
    // }

    @Autowired
    MockMvc mockMvc;
    
    @Quando("o usuario chama a funçao listarLivros")
    public void o_usuario_chama_a_funçao_listarLivros() {
        // livroController.listarLivros();
        // assertTrue(true);
    }

    @Entao("o usuario deve obter status code {int}")
    public void o_usuario_deve_obter_status_code(int statusCode) throws Exception {
        // HttpStatusCode currentStatusCode = livroController.listarLivros().getStatusCode();
        // assertTrue(currentStatusCode == HttpStatusCode.valueOf(statusCode));
        mockMvc.perform(MockMvcRequestBuilders.get("/livros"))
            .andExpect(status().isOk());
        
        // RestAssuredMockMvc//.given()//.standaloneSetup(new LivroController())
        //             .when().get("/livros")
        //             .then().statusCode(statusCode);//.assertThat(status().isOk());
    }

    // @E("o usuario deve obter a lista de {string}")
    // public void o_usuario_deve_obter_a_lista_de(String palavra) {
    // assertEquals("livros",palavra);
    // }

    
}
