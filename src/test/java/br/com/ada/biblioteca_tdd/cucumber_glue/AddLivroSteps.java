package br.com.ada.biblioteca_tdd.cucumber_glue;

import static org.junit.Assert.assertEquals;

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

import br.com.ada.biblioteca_tdd.controller.LivroController;
import br.com.ada.biblioteca_tdd.model.DTO.LivroDTO;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import io.cucumber.spring.CucumberContextConfiguration;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDate;

//@CucumberContextConfiguration
@SpringBootTest
@AutoConfigureMockMvc

public class AddLivroSteps {
    @Autowired
    MockMvc mockMvc;

    private LivroDTO livroDTO = new LivroDTO();
    ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
    private int currentStatusCode = 200;

    @Quando("o usuario adicionar um livro de titulo {string}")
    public void o_usuario_adicionar_um_livro_de_titulo(String string) {
        if(string.isBlank()){
            currentStatusCode = 400;
        }
        livroDTO.setTitulo(string);
    }
    @E("resumo {string}")
    public void resumo(String string) {
        if(string.isBlank() || string.length()>500){
            currentStatusCode = 400;
        }
        livroDTO.setResumo(string);
    }
    @E("preco {double}")
    public void preco(Double double1) {
        if(double1<20){
            currentStatusCode = 400;
        }
        livroDTO.setPreco(double1);
    }
    @E("preco ")
    public void preco() {
        currentStatusCode = 400;
    }
    @E("numero de paginas {int}")
    public void numero_de_paginas(Integer int1) {
        if(int1<100 || int1== null){
            currentStatusCode = 400;
        }
        livroDTO.setNumeroDePaginas(int1);
    }
    @E("numero de paginas ")
    public void numero_de_paginas() {
            currentStatusCode = 400;
    }
    @E("isbn {string}")
    public void isbn(String string) {
        if(string.isBlank()){
            currentStatusCode = 400;
        }
        livroDTO.setIsbn(string);
    }
    @E("sumario {string}")
    public void sumario(String string) {
        livroDTO.setSumario(string);
    }
    @E("data de publicacao {string}")
    public void data_de_publicacao(String string) {
        try {
            LocalDate localDate = LocalDate.parse(string);
            if(localDate.isBefore(LocalDate.now()) || localDate.equals(LocalDate.now())){
                currentStatusCode=400;
            }
            livroDTO.setDataDePublicacao(localDate);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    @Entao("deve ser capaz de adicionar um livro")
    public void deve_ser_capaz_de_adicionar_um_livro() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/livros")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(livroDTO)))
            .andExpect(status().is(currentStatusCode))
            .andDo(MockMvcResultHandlers.print());
    }
}
