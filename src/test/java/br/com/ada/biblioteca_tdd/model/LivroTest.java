package br.com.ada.biblioteca_tdd.model;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDate;

import br.com.ada.biblioteca_tdd.controller.LivroController;
import br.com.ada.biblioteca_tdd.model.DTO.LivroDTO;
import br.com.ada.biblioteca_tdd.repository.LivroRepository;
import io.restassured.module.mockmvc.RestAssuredMockMvc;


@AutoConfigureMockMvc
@SpringBootTest

public class LivroTest {
    @Autowired
    MockMvc mockMvc;
    
    @Autowired
    LivroRepository livroRepository;
    
    ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
    
    public String criarLivroPadraoJson() throws JsonProcessingException {
        LivroDTO livroDTO = new LivroDTO();
        livroDTO.setTitulo("titulo");
        livroDTO.setResumo("resumo");
        livroDTO.setPreco(500d);
        livroDTO.setNumeroDePaginas(110);
        livroDTO.setIsbn("isbn");
        livroDTO.setSumario("sumario");
        livroDTO.setDataDePublicacao(LocalDate.of(2023, 06, 06));

        return objectMapper.writeValueAsString(livroDTO);
    }

    @Test 
    public void listarLivros() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/livros"))
            .andExpect(status().isOk());
        // RestAssured.when().get("/livros").then().statusCode(200);
        // RestAssuredMockMvc.given().standaloneSetup(new LivroController())
        //             .when().get("/livros")
        //             .then().statusCode(200);
    }

    @Test
    public void addLivro() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post("/livros")
            .contentType(MediaType.APPLICATION_JSON)
            .content(criarLivroPadraoJson()))
            .andExpect(status().isOk())
            .andDo(MockMvcResultHandlers.print());
    }
        
    // testando a retirada de parametros opcionais
    @Test
    public void addLivroSimples() throws Exception {
        
        LivroDTO livroDTO = objectMapper.readValue(criarLivroPadraoJson(), LivroDTO.class);
        livroDTO.setDataDePublicacao(null);
        livroDTO.setSumario(null);
        String livroJson = objectMapper.writeValueAsString(livroDTO);
        
        mockMvc.perform(MockMvcRequestBuilders.post("/livros")
            .contentType(MediaType.APPLICATION_JSON)
            .content(livroJson))
            .andExpect(status().isOk());
        
    }
    
    // testando a retirada de atributos obrigatorios
    @Test
    public void addLivroSemAtributosObrigatorios() throws Exception {

        LivroDTO livroDTO = objectMapper.readValue(criarLivroPadraoJson(), LivroDTO.class);
        livroDTO.setIsbn(null);
        String livroJson = objectMapper.writeValueAsString(livroDTO);
        
        mockMvc.perform(MockMvcRequestBuilders.post("/livros")
            .contentType(MediaType.APPLICATION_JSON)
            .content(livroJson))
            .andExpect(status().isBadRequest())
            .andDo(MockMvcResultHandlers.print());
            
        livroDTO = objectMapper.readValue(criarLivroPadraoJson(), LivroDTO.class);
        livroDTO.setNumeroDePaginas(null);
        livroJson = objectMapper.writeValueAsString(livroDTO);
            
        mockMvc.perform(MockMvcRequestBuilders.post("/livros")
            .contentType(MediaType.APPLICATION_JSON)
            .content(livroJson))
            .andExpect(status().isBadRequest())
            .andDo(MockMvcResultHandlers.print());
        
        livroDTO = objectMapper.readValue(criarLivroPadraoJson(), LivroDTO.class);
        livroDTO.setPreco(null);
        livroJson = objectMapper.writeValueAsString(livroDTO);
            
        mockMvc.perform(MockMvcRequestBuilders.post("/livros")
            .contentType(MediaType.APPLICATION_JSON)
            .content(livroJson))
            .andExpect(status().isBadRequest())
            .andDo(MockMvcResultHandlers.print());
        
        livroDTO = objectMapper.readValue(criarLivroPadraoJson(), LivroDTO.class);
        livroDTO.setResumo(null);
        livroJson = objectMapper.writeValueAsString(livroDTO);
            
        mockMvc.perform(MockMvcRequestBuilders.post("/livros")
            .contentType(MediaType.APPLICATION_JSON)
            .content(livroJson))
            .andExpect(status().isBadRequest())
            .andDo(MockMvcResultHandlers.print());

        livroDTO = objectMapper.readValue(criarLivroPadraoJson(), LivroDTO.class);
        livroDTO.setTitulo(null);
        livroJson = objectMapper.writeValueAsString(livroDTO);
            
        mockMvc.perform(MockMvcRequestBuilders.post("/livros")
            .contentType(MediaType.APPLICATION_JSON)
            .content(livroJson))
            .andExpect(status().isBadRequest())
            .andDo(MockMvcResultHandlers.print());


    }

    // testando data presente ou passada
    @Test
    public void addLivroComDataSemSerNoFuturo() throws Exception {
        
        LivroDTO livroDTO = objectMapper.readValue(criarLivroPadraoJson(), LivroDTO.class);
        livroDTO.setDataDePublicacao(LocalDate.of(2022, 01, 10));
        String livroJson = objectMapper.writeValueAsString(livroDTO);

        mockMvc.perform(MockMvcRequestBuilders.post("/livros")
            .contentType(MediaType.APPLICATION_JSON)
            .content(livroJson))
            .andExpect(status().isBadRequest());

        livroDTO = objectMapper.readValue(criarLivroPadraoJson(), LivroDTO.class);
        livroDTO.setDataDePublicacao(LocalDate.now());
        livroJson = objectMapper.writeValueAsString(livroDTO);

        mockMvc.perform(MockMvcRequestBuilders.post("/livros")
            .contentType(MediaType.APPLICATION_JSON)
            .content(livroJson))
            .andExpect(status().isBadRequest());

    }
    
    // testando resumo com maximo de 500 caracteres
    @Test
    public void addLivroComResumoMaxDe500Caracteres() throws Exception {

        String string500 = "*";
        LivroDTO livroDTO = objectMapper.readValue(criarLivroPadraoJson(), LivroDTO.class);
        livroDTO.setResumo(string500.repeat(500));
        String livroJson = objectMapper.writeValueAsString(livroDTO);

        mockMvc.perform(MockMvcRequestBuilders.post("/livros")
            .contentType(MediaType.APPLICATION_JSON)
            .content(livroJson))
            .andExpect(status().isOk())
            .andDo(MockMvcResultHandlers.print());

        livroDTO = objectMapper.readValue(criarLivroPadraoJson(), LivroDTO.class);
        livroDTO.setResumo(string500.repeat(501));
        livroJson = objectMapper.writeValueAsString(livroDTO);

        mockMvc.perform(MockMvcRequestBuilders.post("/livros")
            .contentType(MediaType.APPLICATION_JSON)
            .content(livroJson))
            .andExpect(status().isBadRequest());

    }
    
    // testando preço com valor mínimo de 20
    @Test
    public void addLivroComPrecoMinimoDe20() throws Exception {

        LivroDTO livroDTO = objectMapper.readValue(criarLivroPadraoJson(), LivroDTO.class);
        livroDTO.setPreco(20d);
        String livroJson = objectMapper.writeValueAsString(livroDTO);

        mockMvc.perform(MockMvcRequestBuilders.post("/livros")
            .contentType(MediaType.APPLICATION_JSON)
            .content(livroJson))
            .andExpect(status().isOk());

        livroDTO = objectMapper.readValue(criarLivroPadraoJson(), LivroDTO.class);
        livroDTO.setPreco(20.1d);
        livroJson = objectMapper.writeValueAsString(livroDTO);

        mockMvc.perform(MockMvcRequestBuilders.post("/livros")
            .contentType(MediaType.APPLICATION_JSON)
            .content(livroJson))
            .andExpect(status().isOk());


        livroDTO = objectMapper.readValue(criarLivroPadraoJson(), LivroDTO.class);
        livroDTO.setPreco(19.9d);
        livroJson = objectMapper.writeValueAsString(livroDTO);

        mockMvc.perform(MockMvcRequestBuilders.post("/livros")
            .contentType(MediaType.APPLICATION_JSON)
            .content(livroJson))
            .andExpect(status().isBadRequest());

    }

    // testando número de página mínimo de 100
    @Test
    public void addLivroComNumeroDePaginaMinimoDe100() throws Exception {

        LivroDTO livroDTO = objectMapper.readValue(criarLivroPadraoJson(), LivroDTO.class);
        livroDTO.setNumeroDePaginas(99);
        String livroJson = objectMapper.writeValueAsString(livroDTO);

        mockMvc.perform(MockMvcRequestBuilders.post("/livros")
            .contentType(MediaType.APPLICATION_JSON)
            .content(livroJson))
            .andExpect(status().isBadRequest());

        livroDTO = objectMapper.readValue(criarLivroPadraoJson(), LivroDTO.class);
        livroDTO.setNumeroDePaginas(100);
        livroJson = objectMapper.writeValueAsString(livroDTO);

        mockMvc.perform(MockMvcRequestBuilders.post("/livros")
            .contentType(MediaType.APPLICATION_JSON)
            .content(livroJson))
            .andExpect(status().isOk());

        livroDTO = objectMapper.readValue(criarLivroPadraoJson(), LivroDTO.class);
        livroDTO.setNumeroDePaginas(101);
        livroJson = objectMapper.writeValueAsString(livroDTO);

        mockMvc.perform(MockMvcRequestBuilders.post("/livros")
            .contentType(MediaType.APPLICATION_JSON)
            .content(livroJson))
            .andExpect(status().isOk());

    }
    
    
}
