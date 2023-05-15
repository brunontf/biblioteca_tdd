package br.com.ada.biblioteca_tdd.model;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import br.com.ada.biblioteca_tdd.model.DTO.LivroDTO;
import br.com.ada.biblioteca_tdd.model.Entity.LivroEntity;

public class EqualsEHashCodeTest {

    public LivroDTO criarLivroDTO() {
        LivroDTO livroDTO = new LivroDTO();
        livroDTO.setTitulo("titulo");
        livroDTO.setResumo("resumo");
        livroDTO.setPreco(500d);
        livroDTO.setNumeroDePaginas(110);
        livroDTO.setIsbn("isbn");
        livroDTO.setSumario("sumario");
        livroDTO.setDataDePublicacao(LocalDate.of(2023, 06, 06));

        return livroDTO;
    }

    public LivroEntity criarLivroEntity() {
        LivroEntity livroEntity = new LivroEntity();
        livroEntity.setTitulo("titulo");
        livroEntity.setResumo("resumo");
        livroEntity.setPreco(500d);
        livroEntity.setNumeroDePaginas(110);
        livroEntity.setIsbn("isbn");
        livroEntity.setSumario("sumario");
        livroEntity.setDataDePublicacao(LocalDate.of(2023, 06, 06));

        return livroEntity;
    }
    
    @Test
    public void testandoEqualsDoLivroDTO1() {
        LivroDTO livroDTO1 = new LivroDTO();
        LivroDTO livroDTO2 = new LivroDTO();
        assertTrue(livroDTO1.equals(livroDTO2));
        assertTrue(livroDTO1.hashCode() == livroDTO2.hashCode());
        livroDTO1 = criarLivroDTO();
        livroDTO2 = criarLivroDTO();
        assertTrue(livroDTO1.equals(livroDTO2));
        assertTrue(livroDTO1.hashCode() == livroDTO2.hashCode());
    }

    @Test
    public void testandoEqualsDoLivroEntity() {
        LivroEntity livroEntity1 = new LivroEntity();
        LivroEntity livroEntity2 = new LivroEntity();
        assertTrue(livroEntity1.equals(livroEntity2));
        assertTrue(livroEntity1.hashCode() == livroEntity2.hashCode());
        livroEntity1 = criarLivroEntity();
        livroEntity2 = criarLivroEntity();
        assertTrue(livroEntity1.equals(livroEntity2));
        assertTrue(livroEntity1.hashCode() == livroEntity2.hashCode());
    }


}
