package br.com.ada.biblioteca_tdd.model;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDate;

import br.com.ada.biblioteca_tdd.model.DTO.LivroDTO;

// @ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@SpringBootTest

public class LivroTest {
    @Autowired
    MockMvc mockMvc;

    @Test 
    public void listarLivros() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/livros"))
            .andExpect(status().isOk());
    }

    @Test
    public void addLivro() throws Exception {
        LivroDTO livroDTO = new LivroDTO();
        livroDTO.setTitulo("titulo");
        livroDTO.setResumo("resumo");
        livroDTO.setPreco(500d);
        livroDTO.setNumeroDePaginas(110);
        livroDTO.setIsbn("isbn");
        livroDTO.setSumario("sumario");
        livroDTO.setDataDePublicacao(LocalDate.of(2023, 06, 06));

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        String livroJson = objectMapper.writeValueAsString(livroDTO);

        mockMvc.perform(MockMvcRequestBuilders.post("/livros")
            .contentType(MediaType.APPLICATION_JSON)
            .content(livroJson))
            .andExpect(status().isOk())
            .andDo(MockMvcResultHandlers.print());

        // testando a retirada de parametros opcionais
        livroDTO.setDataDePublicacao(null);
        livroDTO.setSumario(null);
        livroJson = objectMapper.writeValueAsString(livroDTO);
        mockMvc.perform(MockMvcRequestBuilders.post("/livros")
        .contentType(MediaType.APPLICATION_JSON)
        .content(livroJson))
        .andExpect(status().isOk());
        
        // testando a retirada de atributo obrigatorio
        livroDTO.setIsbn(null);
        livroJson = objectMapper.writeValueAsString(livroDTO);
        mockMvc.perform(MockMvcRequestBuilders.post("/livros")
            .contentType(MediaType.APPLICATION_JSON)
            .content(livroJson))
            .andExpect(status().isBadRequest())
            .andDo(MockMvcResultHandlers.print());
    }

    
    
}
